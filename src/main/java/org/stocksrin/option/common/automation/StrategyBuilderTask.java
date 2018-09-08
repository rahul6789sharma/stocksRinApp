package org.stocksrin.option.common.automation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TimerTask;

import org.stocksrin.option.common.priceUtils;
import org.stocksrin.option.common.model.OptionModle;
import org.stocksrin.option.common.model.OptionModles;
import org.stocksrin.option.common.model.Strategy;
import org.stocksrin.option.common.model.StrategyModel;
import org.stocksrin.option.common.model.StrategyModel.OptionType;
import org.stocksrin.option.nifty.NiftyData;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.FileUtils;

public class StrategyBuilderTask extends TimerTask {

	@Override
	public void run() {
		try {
			priceUtils.fetchData();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println(NiftyData.shortedExpiry);
			createStrategyFile();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
		try {
			priceUtils.fetchData();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(NiftyData.shortedExpiry);
		createStrategyFile();
	}

	public static Strategy createStrategyFile() throws Exception {
		String dir = APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR;
		String strategyName = "Strangle@Monthly-Strategy_NIFTY";
		String header = "strategySerial,expiry,type,strike,close_price,quantity,target,stopLoss,spot_close,desc,status,traded_IV,tradedDate";
		Strategy strategy = buildNiftyStrangle();
		String file = dir + strategyName + ".csv";
		File f = new File(file);
		if (f.exists()) {
			f.delete();
		}
		FileUtils.makeFile(file);
		appendData(header, file);

		List<StrategyModel> m = strategy.getStrategyModels();
		for (StrategyModel strategyModel : m) {
			appendData(strategyModel.toCSV(), file);
		}
		return strategy;
	}

	private static Strategy buildNiftyStrangle() throws Exception {
		String currentExpiry = NiftyData.shortedExpiry.first();

		String today = DateUtils.dateToString(new Date(), "ddMMMyyyy").toUpperCase();
		if (currentExpiry.equalsIgnoreCase(today)) {
			SortedSet<String> shortedExpiry = NiftyData.shortedExpiry;
			int i = 0;
			for (String string : shortedExpiry) {
				if (i == 1) {
					currentExpiry = string;
					break;
				}
				i++;
			}
		}

		OptionModles optionModles = NiftyData.optionData.get(currentExpiry);
		double atmStrike = getATMStrike(optionModles);
		double putStrike = atmStrike - 400;
		double callStrike = atmStrike + 200;

		List<OptionModle> lst = optionModles.getOptionModle();
		Strategy strategy = new Strategy("NIFTY");
		Strategy strategyPUT = buildStrategy(lst, putStrike, OptionType.PUT, currentExpiry, optionModles.getSpot());
		Strategy strategyCALL = buildStrategy(lst, callStrike, OptionType.CALL, currentExpiry, optionModles.getSpot());
		strategy.getStrategyModels().addAll(strategyPUT.getStrategyModels());
		strategy.getStrategyModels().addAll(strategyCALL.getStrategyModels());
		return strategy;
	}

	private static Strategy buildStrategy(List<OptionModle> optionModles, double strike, OptionType optionType, String expiry, double spotPrice) throws Exception {
		Strategy strategy = new Strategy("NIFTY");

		for (OptionModle optionModle : optionModles) {
			if (optionModle.getStrike_price().equals(strike)) {
				if (OptionType.PUT.equals(optionType)) {
					StrategyModel strategyModel = StrategyModelBuilderPUT(optionModle, expiry, strike, spotPrice);
					strategy.getStrategyModels().add(strategyModel);
				} else if (OptionType.CALL.equals(optionType)) {
					StrategyModel strategyModel = StrategyModelBuilderCALL(optionModle, expiry, strike, spotPrice);
					strategy.getStrategyModels().add(strategyModel);
				}
			}
		}
		return strategy;
	}

	private static StrategyModel StrategyModelBuilderPUT(OptionModle optionModle, String expiry, double strike, double spotPrice) throws Exception {
		StrategyModel strategyModel = new StrategyModel();
		strategyModel.setStrategySerial("1");
		strategyModel.setExpiry(expiry);
		strategyModel.setType(OptionType.PUT);
		strategyModel.setStrike(strike);
		strategyModel.setClose_price(optionModle.getP_ltp());
		strategyModel.setQuantity(-150);
		strategyModel.setTarget(800);
		strategyModel.setStopLoss(-1000);
		strategyModel.setDes("Automated Trade");
		strategyModel.setSpot_close(spotPrice);
		strategyModel.setTraded_IV(optionModle.getP_iv());
		strategyModel.setTradeDate(DateUtils.dateToString(new Date(), "ddMMMyyyy").toUpperCase());
		return strategyModel;
	}

	private static StrategyModel StrategyModelBuilderCALL(OptionModle optionModle, String expiry, double strike, double spotPrice) throws Exception {
		StrategyModel strategyModel = new StrategyModel();
		strategyModel.setStrategySerial("2");
		strategyModel.setExpiry(expiry);
		strategyModel.setType(OptionType.CALL);
		strategyModel.setStrike(strike);
		strategyModel.setClose_price(optionModle.getC_ltp());
		strategyModel.setQuantity(-150);
		strategyModel.setSpot_close(spotPrice);
		strategyModel.setTarget(800);
		strategyModel.setStopLoss(-1000);
		strategyModel.setDes("Automated Trade");
		strategyModel.setTraded_IV(optionModle.getC_iv());
		strategyModel.setTradeDate(DateUtils.dateToString(new Date(), "ddMMMyyyy").toUpperCase());
		return strategyModel;
	}

	private static double getATMStrike(OptionModles optionModles) {

		double spot = optionModles.getSpot();
		List<OptionModle> lst = optionModles.getOptionModle();

		double atmStrike = 0.0;
		for (OptionModle optionModle : lst) {

			if (spot < optionModle.getStrike_price()) {
				atmStrike = optionModle.getStrike_price();
				break;
			}
		}
		return atmStrike;
	}

	private static void appendData(String data, String fileName) {

		File file = new File(fileName);
		// if file doesnt exists, then create it
		if (!file.exists()) {
			throw new RuntimeException(fileName + " File not exist");
		}

		try (FileWriter fw = new FileWriter(file.getAbsoluteFile(), true); BufferedWriter bw = new BufferedWriter(fw);) {

			bw.write(data + "\n");

		} catch (IOException e) {
			e.printStackTrace();

		}
	}
}

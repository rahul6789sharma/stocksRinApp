package org.stocksrin.option.common.automation;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TimeZone;

import org.stocksrin.email.SendEmail;
import org.stocksrin.option.banknifty.BankNiftyData2;
import org.stocksrin.option.common.model.OptionModle;
import org.stocksrin.option.common.model.OptionModles;
import org.stocksrin.option.common.model.Strategy;
import org.stocksrin.option.common.model.Strategy.UnderLying;
import org.stocksrin.option.common.model.StrategyModel;
import org.stocksrin.option.common.model.StrategyModel.OptionType;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.FileUtils;

public class Utils {

	private static String line = "----------------------------------------------------------------------------------------------";

	public static void createStrategyFile(Strategy strategy, String dir, String fileName) {
		try {
			Utils.createStrategyFile(dir, strategy, fileName);
			List<StrategyModel> lst = strategy.getStrategyModels();
			StringBuilder string = new StringBuilder();
			string.append(line + "\n");
			double totalPremium = 0.0;
			for (StrategyModel strategyModel : lst) {
				string.append(strategyModel.toCSV());
				string.append("\n");
				totalPremium = totalPremium + strategyModel.getAvgPrice();
			}
			string.append(line + "\n");
			Integer totalp = (int) Math.round(totalPremium); // 3
			string.append("Total Premium : " + totalp);
			SendEmail.sentMail("Premium : [" + totalp + "] ," + fileName, string.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static double getATMStrike(OptionModles optionModles, int strikediff) {

		double spot = optionModles.getSpot();
		// System.out.println(" getATMStrike spot : " + spot);
		List<OptionModle> lst = optionModles.getOptionModle();

		double atmStrike = 0.0;
		for (OptionModle optionModle : lst) {

			double diff = Math.abs(spot - optionModle.getStrike_price());
			if (diff < strikediff) {
				atmStrike = optionModle.getStrike_price();
				break;
			}
		}

		return atmStrike;
	}

	public static String getNiftyExpiry(SortedSet<String> shortedExpiry, String currentExpiry) throws Exception {

		Calendar time = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		int hr = time.get(Calendar.HOUR);
		if (hr < 14) {
			return currentExpiry;
		} else {
			String today = DateUtils.dateToString(new Date(), "ddMMMyyyy");
			if (currentExpiry.equalsIgnoreCase(today)) {
				System.out.println("Expiry Day Moving to new Expiry");
				int i = 0;
				for (String item : BankNiftyData2.shortedExpiry) {
					if (i == 1) {
						currentExpiry = item;
						break;
					}
					i++;
				}
			}
			return currentExpiry;
		}

	}

	public static String getNextExpiry(SortedSet<String> shortedExpiry) throws Exception {

		List<String> expiryList = new ArrayList<>(BankNiftyData2.shortedExpiry);
		return expiryList.get(1);

	}

	public static String getExpiry(SortedSet<String> shortedExpiry) throws Exception {

		Calendar time = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		int hr = time.get(Calendar.HOUR);
		if (hr < 14) {
			return BankNiftyData2.shortedExpiry.first();
		} else {
			String currentExpiry = BankNiftyData2.shortedExpiry.first();
			String today = DateUtils.dateToString(new Date(), "ddMMMyyyy");

			if (currentExpiry.equalsIgnoreCase(today)) {
				System.out.println("Expiry Day Moving to new Expiry");
				int i = 0;
				for (String item : BankNiftyData2.shortedExpiry) {
					if (i == 1) {
						currentExpiry = item;
						break;
					}
					i++;
				}
			}
			return currentExpiry;
		}

	}

	public static void createStrategyFile(String dir, Strategy strategy, String strategyName) {

		String header = "strategySerial,expiry,type,strike,close_price,quantity,target,stopLoss,spot_close,desc,status,traded_IV,tradedDate";
		String file = dir + strategyName + ".csv";
		File f = new File(file);
		if (f.exists()) {
			f.delete();
		}
		FileUtils.makeFile(file);
		FileUtils.appendData(header, file);

		List<StrategyModel> m = strategy.getStrategyModels();
		for (StrategyModel strategyModel : m) {
			FileUtils.appendData(strategyModel.toCSV(), file);
		}

	}

	public static Strategy buildStrategy(String name, List<OptionModle> optionModles, double strike, OptionType optionType, String expiry, double spotPrice, int qnt) throws Exception {
		Strategy strategy = new Strategy(UnderLying.NIFTY);
		strategy.setStrategyName(name);

		for (OptionModle optionModle : optionModles) {
			if (optionModle.getStrike_price().equals(strike)) {
				if (OptionType.PUT.equals(optionType)) {
					StrategyModel strategyModel = Utils.StrategyModelBuilderPUT(optionModle, expiry, strike, spotPrice, qnt);
					strategy.getStrategyModels().add(strategyModel);
				} else if (OptionType.CALL.equals(optionType)) {
					StrategyModel strategyModel = Utils.StrategyModelBuilderCALL(optionModle, expiry, strike, spotPrice, qnt);
					strategy.getStrategyModels().add(strategyModel);
				}
			}
		}
		return strategy;
	}

	private static StrategyModel StrategyModelBuilderPUT(OptionModle optionModle, String expiry, double strike, double spotPrice, int qnt) throws Exception {
		StrategyModel strategyModel = new StrategyModel();
		strategyModel.setStrategySerial("1");
		strategyModel.setExpiry(expiry);
		strategyModel.setType(OptionType.PUT);
		strategyModel.setStrike(strike);
		strategyModel.setAvgPrice(optionModle.getP_ltp());
		strategyModel.setQuantity(qnt);
		strategyModel.setTarget(800);
		strategyModel.setStopLoss(-1000);
		strategyModel.setDes("Automated Trade");
		strategyModel.setSpot_close(spotPrice);
		if (optionModle.getP_iv() != null) {
			strategyModel.setTraded_IV(optionModle.getP_iv());
		}

		strategyModel.setTradeDate(DateUtils.dateToString(new Date(), "ddMMMyyyy").toUpperCase());
		return strategyModel;
	}

	private static StrategyModel StrategyModelBuilderCALL(OptionModle optionModle, String expiry, double strike, double spotPrice, int qnt) throws Exception {
		StrategyModel strategyModel = new StrategyModel();
		strategyModel.setStrategySerial("2");
		strategyModel.setExpiry(expiry);
		strategyModel.setType(OptionType.CALL);
		strategyModel.setStrike(strike);
		System.out.println(optionModle.getC_ltp());
		strategyModel.setAvgPrice(optionModle.getC_ltp());
		strategyModel.setQuantity(qnt);
		strategyModel.setSpot_close(spotPrice);
		strategyModel.setTarget(800);
		strategyModel.setStopLoss(-1000);
		strategyModel.setDes("Automated Trade");
		if (optionModle.getC_iv() != null) {
			strategyModel.setTraded_IV(optionModle.getC_iv());
		}

		strategyModel.setTradeDate(DateUtils.dateToString(new Date(), "ddMMMyyyy").toUpperCase());
		return strategyModel;
	}

	public static double getCurrentLevels(double spot, double currentLevel) {
		if (spot > currentLevel) {
			currentLevel = currentLevel + 100.0;
		} else if (spot <= currentLevel) {
			currentLevel = currentLevel - 100.0;
		}

		return currentLevel;

		/*
		 * if (spot % 100 == 0) { return spot; } else { return null; }
		 */
	}

	static double currentLevel = 0;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("running " + currentLevel);
		double start = 24685;
		currentLevel = 24600;
		while (true) {
			start++;
			Thread.sleep(1000);
			System.out.println("start" + start);
			System.out.println("currentLevel " + getCurrentLevels(start,currentLevel));
			
		}
	}

}

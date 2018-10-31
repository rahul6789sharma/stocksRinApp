package org.stocksrin.option.common;

import java.util.List;

import org.stocksrin.email.SendEmail;
import org.stocksrin.option.common.automation.Utils;
import org.stocksrin.option.common.model.OptionModle;
import org.stocksrin.option.common.model.OptionModles;
import org.stocksrin.option.common.model.Strategy;
import org.stocksrin.option.common.model.Strategy.UnderLying;
import org.stocksrin.option.common.model.StrategyModel;
import org.stocksrin.option.common.model.StrategyModel.OptionType;
import org.stocksrin.option.nifty.NiftyData;
import org.stocksrin.utils.FileUtils;

public class NiftyStrategies {

	private static String line = "----------------------------------------------------------------------------------------------";

	public static void Strategy3StraddleNifty(String fileName, String dir) {

		String file = dir + fileName + ".csv";
		try {

			boolean status = FileUtils.isTodayFileExist(file);
			if (!status) {
				Strategy strategy = buildNiftyStrangle();
				createStrategyFile(strategy, dir, fileName);
			} else {
				SendEmail.sentMail("IntraDayFile already exist " + dir, "");
			}

		} catch (Exception e) {
			e.printStackTrace();
			SendEmail.sentMail("Critical Error in startegy file " + file, "");
		}

	}

	private static Strategy buildNiftyStrangle() throws Exception {

		String currentExpiry = NiftyData.shortedExpiry.first();

		currentExpiry = Utils.getNiftyExpiry(NiftyData.shortedExpiry, currentExpiry);

		OptionModles optionModles = NiftyData.optionData.get(currentExpiry);
		if (optionModles == null) {
			return null;
		}
		double atmStrike = Utils.getATMStrike(optionModles, 25);
		double lowerStrike = atmStrike - 50;
		double uperStrike = atmStrike + 50;

		List<OptionModle> lst = optionModles.getOptionModle();
		Strategy strategy = new Strategy(UnderLying.NIFTY);
		strategy.setStrategyName("NIFTY");

		int qnt = -75;
		Strategy leg1Put = Utils.buildStrategy("BNF", lst, lowerStrike, OptionType.PUT, currentExpiry, optionModles.getSpot(), qnt);
		Strategy leg1Call = Utils.buildStrategy("BNF", lst, lowerStrike, OptionType.CALL, currentExpiry, optionModles.getSpot(), qnt);

		Strategy leg2Put = Utils.buildStrategy("BNF", lst, atmStrike, OptionType.PUT, currentExpiry, optionModles.getSpot(), qnt);
		Strategy leg2Call = Utils.buildStrategy("BNF", lst, atmStrike, OptionType.CALL, currentExpiry, optionModles.getSpot(), qnt);

		Strategy leg3Put = Utils.buildStrategy("BNF", lst, uperStrike, OptionType.PUT, currentExpiry, optionModles.getSpot(), qnt);
		Strategy leg3Call = Utils.buildStrategy("BNF", lst, uperStrike, OptionType.CALL, currentExpiry, optionModles.getSpot(), qnt);

		strategy.getStrategyModels().addAll(leg1Put.getStrategyModels());
		strategy.getStrategyModels().addAll(leg1Call.getStrategyModels());

		strategy.getStrategyModels().addAll(leg2Put.getStrategyModels());
		strategy.getStrategyModels().addAll(leg2Call.getStrategyModels());

		strategy.getStrategyModels().addAll(leg3Put.getStrategyModels());
		strategy.getStrategyModels().addAll(leg3Call.getStrategyModels());
		return strategy;
	}

	private static void createStrategyFile(Strategy strategy, String dir, String fileName) {
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
}

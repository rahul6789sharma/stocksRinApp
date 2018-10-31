package org.stocksrin.option.common;

import java.util.List;

import org.stocksrin.email.SendEmail;
import org.stocksrin.option.banknifty.BankNiftyData2;
import org.stocksrin.option.common.automation.Utils;
import org.stocksrin.option.common.model.OptionModle;
import org.stocksrin.option.common.model.OptionModles;
import org.stocksrin.option.common.model.Strategy;
import org.stocksrin.option.common.model.Strategy.UnderLying;
import org.stocksrin.option.common.model.StrategyModel.OptionType;
import org.stocksrin.utils.FileUtils;

public class BNFStrategies {

	public static void main(String[] args) {
	}

	private static final int lot = 20;

	public static void Strategy300PointAwaystrangle(String fileName, String dir) {

		String file = dir + fileName + ".csv";
		try {

			boolean status = FileUtils.isTodayFileExist(file);
			if (!status) {
				Strategy strategy = BNFStrategies.buildStrategy300PointAwaystrangle();
				Utils.createStrategyFile(strategy, dir, fileName);
			} else {
				SendEmail.sentMail("Intra DayFile strategy already exist " + file, "");
			}

		} catch (Exception e) {
			e.printStackTrace();
			SendEmail.sentMail("Critical Error in startegy file " + file, "");
		}
	}

	public static void BuildStrategy3Straddle(String fileName, String dir) {
		String file = dir + fileName + ".csv";
		try {

			boolean status = FileUtils.isTodayFileExist(file);
			if (!status) {
				Strategy strategy = BNFStrategies.Strategy3Straddle();
				Utils.createStrategyFile(strategy, dir, fileName);
			} else {
				SendEmail.sentMail("Intra DayFile strategy already exist " + file, "");
			}

		} catch (Exception e) {
			e.printStackTrace();
			SendEmail.sentMail("Critical Error in startegy file " + file, "");
		}
	}

	public static void ironButterfly(String fileName, String dir) {
		String file = dir + fileName + ".csv";
		try {

			boolean status = FileUtils.isTodayFileExist(file);
			if (!status) {
				Strategy strategy = BNFStrategies.buildStrategyIronFly();
				Utils.createStrategyFile(strategy, dir, fileName);
			} else {
				SendEmail.sentMail("DayFile strategy already exist " + file, "");
			}

		} catch (Exception e) {
			e.printStackTrace();
			SendEmail.sentMail("Critical Error in startegy file " + file, "");
		}

	}

	public static void doubleCalender(String fileName, String dir) {
		String file = dir + fileName + ".csv";
		try {

			boolean status = FileUtils.isTodayFileExist(file);
			if (!status) {
				Strategy strategy = BNFStrategies.buildStrategyDoubleCalender();
				Utils.createStrategyFile(strategy, dir, fileName);
			} else {
				SendEmail.sentMail("DayFile strategy already exist " + file, "");
			}

		} catch (Exception e) {
			e.printStackTrace();
			SendEmail.sentMail("Critical Error in startegy file " + file, "");
		}

	}

	private static Strategy buildStrategyIronFly() throws Exception {

		String currentExpiry = Utils.getExpiry(BankNiftyData2.shortedExpiry);

		OptionModles optionModles = BankNiftyData2.bnOptionData.get(currentExpiry);
		double atmStrike = Utils.getATMStrike(optionModles, 50);

		double peLeg = atmStrike - 200;
		double ceLeg = atmStrike + 200;

		List<OptionModle> lst = optionModles.getOptionModle();
		Strategy strategy = new Strategy(UnderLying.BANKNIFTY);
		strategy.setStrategyName("BNF");

		Strategy legPeBuy = Utils.buildStrategy("BNF", lst, peLeg, OptionType.PUT, currentExpiry, optionModles.getSpot(), lot);
		Strategy legPeSell = Utils.buildStrategy("BNF", lst, atmStrike, OptionType.PUT, currentExpiry, optionModles.getSpot(), -lot);

		Strategy legCeSell = Utils.buildStrategy("BNF", lst, atmStrike, OptionType.CALL, currentExpiry, optionModles.getSpot(), -lot);
		Strategy legCeBuy = Utils.buildStrategy("BNF", lst, ceLeg, OptionType.CALL, currentExpiry, optionModles.getSpot(), lot);

		// order is imp to visulization
		strategy.getStrategyModels().addAll(legPeBuy.getStrategyModels());
		strategy.getStrategyModels().addAll(legPeSell.getStrategyModels());
		strategy.getStrategyModels().addAll(legCeSell.getStrategyModels());
		strategy.getStrategyModels().addAll(legCeBuy.getStrategyModels());

		return strategy;
	}

	private static Strategy Strategy3Straddle() throws Exception {
		String currentExpiry = Utils.getExpiry(BankNiftyData2.shortedExpiry);

		OptionModles optionModles = BankNiftyData2.bnOptionData.get(currentExpiry);
		double atmStrike = Utils.getATMStrike(optionModles, 50);

		double lowerStrike = atmStrike - 100;
		double uperStrike = atmStrike + 100;

		List<OptionModle> lst = optionModles.getOptionModle();
		Strategy strategy = new Strategy(UnderLying.BANKNIFTY);
		strategy.setStrategyName("BNF");

		// int qnt = -40;
		Strategy legPut1 = Utils.buildStrategy("BNF", lst, lowerStrike, OptionType.PUT, currentExpiry, optionModles.getSpot(), -lot);
		Strategy legCall1 = Utils.buildStrategy("BNF", lst, lowerStrike, OptionType.CALL, currentExpiry, optionModles.getSpot(), -lot);

		Strategy legPut2 = Utils.buildStrategy("BNF", lst, atmStrike, OptionType.PUT, currentExpiry, optionModles.getSpot(), -lot);
		Strategy legCall2 = Utils.buildStrategy("BNF", lst, atmStrike, OptionType.CALL, currentExpiry, optionModles.getSpot(), -lot);

		Strategy legPut3 = Utils.buildStrategy("BNF", lst, uperStrike, OptionType.PUT, currentExpiry, optionModles.getSpot(), -lot);
		Strategy legCall3 = Utils.buildStrategy("BNF", lst, uperStrike, OptionType.CALL, currentExpiry, optionModles.getSpot(), -lot);

		strategy.getStrategyModels().addAll(legPut1.getStrategyModels());
		strategy.getStrategyModels().addAll(legCall1.getStrategyModels());

		strategy.getStrategyModels().addAll(legPut2.getStrategyModels());
		strategy.getStrategyModels().addAll(legCall2.getStrategyModels());

		strategy.getStrategyModels().addAll(legPut3.getStrategyModels());
		strategy.getStrategyModels().addAll(legCall3.getStrategyModels());

		return strategy;

	}

	private static Strategy buildStrategy300PointAwaystrangle_protected() throws Exception {

		String currentExpiry = Utils.getExpiry(BankNiftyData2.shortedExpiry);

		OptionModles optionModles = BankNiftyData2.bnOptionData.get(currentExpiry);
		double atmStrike = Utils.getATMStrike(optionModles, 50);

		double putStrike = atmStrike - 300;
		double callStrike = atmStrike + 300;

		double putStrikeProtection = atmStrike - 500;
		double callStrikeProtection = atmStrike + 500;

		List<OptionModle> lst = optionModles.getOptionModle();
		Strategy strategy = new Strategy(UnderLying.BANKNIFTY);
		strategy.setStrategyName("BNF");

		Strategy putlegProtect = Utils.buildStrategy("BNF", lst, putStrikeProtection, OptionType.PUT, currentExpiry, optionModles.getSpot(), (lot * 3));
		Strategy putleg = Utils.buildStrategy("BNF", lst, putStrike, OptionType.PUT, currentExpiry, optionModles.getSpot(), -(lot * 3));

		Strategy callLeg = Utils.buildStrategy("BNF", lst, callStrike, OptionType.CALL, currentExpiry, optionModles.getSpot(), -(lot * 3));
		Strategy callLegProtected = Utils.buildStrategy("BNF", lst, callStrikeProtection, OptionType.CALL, currentExpiry, optionModles.getSpot(), (lot * 3));

		strategy.getStrategyModels().addAll(putlegProtect.getStrategyModels());
		strategy.getStrategyModels().addAll(putleg.getStrategyModels());
		strategy.getStrategyModels().addAll(callLeg.getStrategyModels());
		strategy.getStrategyModels().addAll(callLegProtected.getStrategyModels());

		return strategy;
	}

	private static Strategy buildStrategy300PointAwaystrangle() throws Exception {

		String currentExpiry = Utils.getExpiry(BankNiftyData2.shortedExpiry);

		OptionModles optionModles = BankNiftyData2.bnOptionData.get(currentExpiry);
		double atmStrike = Utils.getATMStrike(optionModles, 50);

		double putStrike = atmStrike - 300;
		double callStrike = atmStrike + 300;

		List<OptionModle> lst = optionModles.getOptionModle();
		Strategy strategy = new Strategy(UnderLying.BANKNIFTY);
		strategy.setStrategyName("BNF");

		Strategy putleg = Utils.buildStrategy("BNF", lst, putStrike, OptionType.PUT, currentExpiry, optionModles.getSpot(), -(lot * 3));

		Strategy callLeg = Utils.buildStrategy("BNF", lst, callStrike, OptionType.CALL, currentExpiry, optionModles.getSpot(), -(lot * 3));
		strategy.getStrategyModels().addAll(putleg.getStrategyModels());
		strategy.getStrategyModels().addAll(callLeg.getStrategyModels());

		return strategy;
	}

	private static Strategy buildStrategyDoubleCalender() throws Exception {

		String currentExpiry = Utils.getExpiry(BankNiftyData2.shortedExpiry);
		String nextExpiry = Utils.getNextExpiry(BankNiftyData2.shortedExpiry);
		// getNextExpiry

		OptionModles optionModles = BankNiftyData2.bnOptionData.get(currentExpiry);
		double atmStrike = Utils.getATMStrike(optionModles, 50);

		double peLeg = atmStrike - 200;
		double ceLeg = atmStrike + 200;

		List<OptionModle> lst = optionModles.getOptionModle();
		Strategy strategy = new Strategy(UnderLying.BANKNIFTY);
		strategy.setStrategyName("BNF");

		Strategy legPeBuy = Utils.buildStrategy("BNF", lst, peLeg, OptionType.PUT, nextExpiry, optionModles.getSpot(), lot);
		Strategy legPeSell = Utils.buildStrategy("BNF", lst, peLeg, OptionType.PUT, currentExpiry, optionModles.getSpot(), -lot);

		Strategy legCeSell = Utils.buildStrategy("BNF", lst, ceLeg, OptionType.CALL, currentExpiry, optionModles.getSpot(), -lot);
		Strategy legCeBuy = Utils.buildStrategy("BNF", lst, ceLeg, OptionType.CALL, nextExpiry, optionModles.getSpot(), lot);

		// order is imp to visulization
		strategy.getStrategyModels().addAll(legPeBuy.getStrategyModels());
		strategy.getStrategyModels().addAll(legPeSell.getStrategyModels());
		strategy.getStrategyModels().addAll(legCeSell.getStrategyModels());
		strategy.getStrategyModels().addAll(legCeBuy.getStrategyModels());

		return strategy;
	}

}

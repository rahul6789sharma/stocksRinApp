package org.stocksrin.option.common.automation.strategies;

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

public class BNFStrangle {

	private static final int lot = 20;

	public static void strangle_300PointAway_protected_build(String fileName, String dir) {

		String file = dir + fileName + ".csv";
		try {

			boolean status = FileUtils.isTodayFileExist(file);
			if (!status) {
				Strategy strategy = strangle_300PointAway_protected();
				Utils.createStrategyFile(strategy, dir, fileName);
			} else {
				SendEmail.sentMail("Intra DayFile strategy already exist " + file, "");
			}

		} catch (Exception e) {
			e.printStackTrace();
			SendEmail.sentMail("Critical Error in startegy file " + file, "");
		}
	}

	public static void otmStrangle3(String fileName, String dir) {
		String file = dir + fileName + ".csv";
		try {

			boolean status = FileUtils.isTodayFileExist(file);
			if (!status) {
				Strategy strategy = strangle_3OTM();
				Utils.createStrategyFile(strategy, dir, fileName);
			} else {
				SendEmail.sentMail("DayFile strategy already exist " + file, "");
			}

		} catch (Exception e) {
			e.printStackTrace();
			SendEmail.sentMail("Critical Error in startegy file " + file, "");
		}
	}
	
	public static void itmStrangle3(String fileName, String dir) {
		String file = dir + fileName + ".csv";
		try {

			boolean status = FileUtils.isTodayFileExist(file);
			if (!status) {
				Strategy strategy = strangle_3ITM();
				Utils.createStrategyFile(strategy, dir, fileName);
			} else {
				SendEmail.sentMail("DayFile strategy already exist " + file, "");
			}

		} catch (Exception e) {
			e.printStackTrace();
			SendEmail.sentMail("Critical Error in startegy file " + file, "");
		}
	}
	public static void itm2Strangle3(String fileName, String dir) {
		String file = dir + fileName + ".csv";
		try {

			boolean status = FileUtils.isTodayFileExist(file);
			if (!status) {
				Strategy strategy = strangle_3ITM2();
				Utils.createStrategyFile(strategy, dir, fileName);
			} else {
				SendEmail.sentMail("DayFile strategy already exist " + file, "");
			}

		} catch (Exception e) {
			e.printStackTrace();
			SendEmail.sentMail("Critical Error in startegy file " + file, "");
		}
	}


	private static Strategy strangle_3ITM() throws Exception {

		String currentExpiry = Utils.getExpiry(BankNiftyData2.shortedExpiry);

		OptionModles optionModles = BankNiftyData2.bnOptionData.get(currentExpiry);
		double atmStrike = Utils.getATMStrike(optionModles, 50);

		double ceLeg1 = atmStrike - 300;
		double ceLeg2 = atmStrike - 200;
		double ceLeg3 = atmStrike - 100;

		double peLeg1 = atmStrike + 100;
		double peLeg2 = atmStrike + 200;
		double peLeg3 = atmStrike + 300;

		List<OptionModle> lst = optionModles.getOptionModle();
		Strategy strategy = new Strategy(UnderLying.BANKNIFTY);
		strategy.setStrategyName("BNF");

		Strategy leg1ce = Utils.buildStrategy("BNF", lst, ceLeg1, OptionType.CALL, currentExpiry, optionModles.getSpot(), -lot);
		Strategy leg2ce = Utils.buildStrategy("BNF", lst, ceLeg2, OptionType.CALL, currentExpiry, optionModles.getSpot(), -lot);
		Strategy leg3ce = Utils.buildStrategy("BNF", lst, ceLeg3, OptionType.CALL, currentExpiry, optionModles.getSpot(), -lot);

		Strategy leg4pe = Utils.buildStrategy("BNF", lst, peLeg1, OptionType.PUT, currentExpiry, optionModles.getSpot(), -lot);
		Strategy leg5pe = Utils.buildStrategy("BNF", lst, peLeg2, OptionType.PUT, currentExpiry, optionModles.getSpot(), -lot);
		Strategy leg6pe = Utils.buildStrategy("BNF", lst, peLeg3, OptionType.PUT, currentExpiry, optionModles.getSpot(), -lot);

		// order is imp to visulization
		strategy.getStrategyModels().addAll(leg1ce.getStrategyModels());
		strategy.getStrategyModels().addAll(leg2ce.getStrategyModels());
		strategy.getStrategyModels().addAll(leg3ce.getStrategyModels());

		strategy.getStrategyModels().addAll(leg4pe.getStrategyModels());
		strategy.getStrategyModels().addAll(leg5pe.getStrategyModels());
		strategy.getStrategyModels().addAll(leg6pe.getStrategyModels());

		return strategy;
	}
	
	private static Strategy strangle_3ITM2() throws Exception {

		String currentExpiry = Utils.getExpiry(BankNiftyData2.shortedExpiry);

		OptionModles optionModles = BankNiftyData2.bnOptionData.get(currentExpiry);
		double atmStrike = Utils.getATMStrike(optionModles, 50);

		double ceLeg1 = atmStrike + 100;
		double ceLeg2 = atmStrike + 200;
		// double ceLeg3 = atmStrike + 300;

		double peLeg1 = atmStrike - 100;
		double peLeg2 = atmStrike - 200;
		// double peLeg3 = atmStrike - 300;

		List<OptionModle> lst = optionModles.getOptionModle();
		Strategy strategy = new Strategy(UnderLying.BANKNIFTY);
		strategy.setStrategyName("BNF");

		Strategy leg1ce = Utils.buildStrategy("BNF", lst, ceLeg1, OptionType.CALL, currentExpiry, optionModles.getSpot(), -lot);
		Strategy leg2ce = Utils.buildStrategy("BNF", lst, ceLeg2, OptionType.CALL, currentExpiry, optionModles.getSpot(), -lot);
		Strategy leg3ce = Utils.buildStrategy("BNF", lst, atmStrike, OptionType.CALL, currentExpiry, optionModles.getSpot(), -lot);

		Strategy leg1pe = Utils.buildStrategy("BNF", lst, peLeg1, OptionType.PUT, currentExpiry, optionModles.getSpot(), -lot);
		Strategy leg2pe = Utils.buildStrategy("BNF", lst, peLeg2, OptionType.PUT, currentExpiry, optionModles.getSpot(), -lot);
		Strategy leg3pe = Utils.buildStrategy("BNF", lst, atmStrike, OptionType.PUT, currentExpiry, optionModles.getSpot(), -lot);

		strategy.getStrategyModels().addAll(leg2pe.getStrategyModels());
		strategy.getStrategyModels().addAll(leg1pe.getStrategyModels());
		strategy.getStrategyModels().addAll(leg3pe.getStrategyModels());

		// order is imp to visulization
		strategy.getStrategyModels().addAll(leg3ce.getStrategyModels());
		strategy.getStrategyModels().addAll(leg1ce.getStrategyModels());
		strategy.getStrategyModels().addAll(leg2ce.getStrategyModels());

		return strategy;
	}

	private static Strategy strangle_300PointAway_protected() throws Exception {

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
	
	private static Strategy strangle_3OTM() throws Exception {

		String currentExpiry = Utils.getExpiry(BankNiftyData2.shortedExpiry);

		OptionModles optionModles = BankNiftyData2.bnOptionData.get(currentExpiry);
		double atmStrike = Utils.getATMStrike(optionModles, 50);

		double ceLeg1 = atmStrike + 100;
		double ceLeg2 = atmStrike + 200;
		double ceLeg3 = atmStrike + 300;

		double peLeg1 = atmStrike - 100;
		double peLeg2 = atmStrike - 200;
		double peLeg3 = atmStrike - 300;

		List<OptionModle> lst = optionModles.getOptionModle();
		Strategy strategy = new Strategy(UnderLying.BANKNIFTY);
		strategy.setStrategyName("BNF");

		Strategy leg1ce = Utils.buildStrategy("BNF", lst, ceLeg1, OptionType.CALL, currentExpiry, optionModles.getSpot(), -lot);
		Strategy leg2ce = Utils.buildStrategy("BNF", lst, ceLeg2, OptionType.CALL, currentExpiry, optionModles.getSpot(), -lot);
		Strategy leg3ce = Utils.buildStrategy("BNF", lst, ceLeg3, OptionType.CALL, currentExpiry, optionModles.getSpot(), -lot);

		Strategy leg1pe = Utils.buildStrategy("BNF", lst, peLeg1, OptionType.PUT, currentExpiry, optionModles.getSpot(), -lot);
		Strategy leg2pe = Utils.buildStrategy("BNF", lst, peLeg2, OptionType.PUT, currentExpiry, optionModles.getSpot(), -lot);
		Strategy leg3pe = Utils.buildStrategy("BNF", lst, peLeg3, OptionType.PUT, currentExpiry, optionModles.getSpot(), -lot);

		strategy.getStrategyModels().addAll(leg3pe.getStrategyModels());
		strategy.getStrategyModels().addAll(leg2pe.getStrategyModels());
		strategy.getStrategyModels().addAll(leg1pe.getStrategyModels());

		// order is imp to visulization
		strategy.getStrategyModels().addAll(leg1ce.getStrategyModels());
		strategy.getStrategyModels().addAll(leg2ce.getStrategyModels());
		strategy.getStrategyModels().addAll(leg3ce.getStrategyModels());

		return strategy;
	}

}

package org.stocksrin.option.common;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.stocksrin.option.common.automation.StrategyResult;
import org.stocksrin.option.common.model.Strategy;
import org.stocksrin.option.common.model.StrategyModel;
import org.stocksrin.option.common.model.StrategyModel.OptionType;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;

public class StrategyWrite {

	private static DecimalFormat df = new DecimalFormat("#00.00");
	private static DecimalFormat signFormate = new DecimalFormat("+#,##00;-#");
	private static String line = "---------------------------------------------------------------------------------------------------------------------------";

	public static StringBuilder print(Strategy strategy) throws Exception {

		String tradedDay = DateUtils.getDayFromDate(strategy.getTradeDate(), "ddMMMyyyy");
		String todayDate = DateUtils.dateToString(new Date(), "ddMMMyyyy");
		String todayDay = DateUtils.getDayFromDate(todayDate, "ddMMMyyyy");
		List<StrategyModel> strategyModels = strategy.getStrategyModels();

		String diff = df.format(strategy.getUnderlying_ltp() - strategy.getTradeSpotPrice());

		StringBuilder result = new StringBuilder();
		result.append(strategy.getUnderlying() + ": " + strategy.getStrategyName() + " updated at " + strategy.getDataUpdatedAt());
		result.append("\n");
		result.append(line);
		result.append("\n");
		result.append("Trade Date: " + strategy.getTradeDate() + " (" + tradedDay + "), Traded Price:  " + strategy.getTradeSpotPrice() + "\n");
		result.append("Status  At: " + todayDate + " (" + todayDay + "), Current Price: " + strategy.getUnderlying_ltp() + " [" + diff + "] Diff from Trade");
		result.append("\n");
		result.append(line);
		result.append("\n");
		result.append("Type    Expiry    Strike   Qty  AvgPrice   ltp    change    P&L    tradeIV   currentIV   IVDiff" + "\n");
		result.append(line + "\n");

		double totalPremiumRecived = 0;
		double currentPremiumRecived = 0;
		double totalPL = 0;
		for (StrategyModel strategyModel : strategyModels) {

			String type = "";
			OptionType optionType = strategyModel.getType();
			if (optionType.equals(OptionType.CALL)) {
				type = "CALL";
			} else if (optionType.equals(OptionType.PUT)) {
				type = "PUT ";
			}

			double change = strategyModel.getLtp() - strategyModel.getAvgPrice();
			double pl = (strategyModel.getLtp() - strategyModel.getAvgPrice()) * strategyModel.getQuantity();
			totalPL = totalPL + pl;
			totalPremiumRecived = totalPremiumRecived + strategyModel.getAvgPrice();
			currentPremiumRecived = currentPremiumRecived + strategyModel.getLtp();

			double ivdiff = strategyModel.getCurrent_IV() - strategyModel.getTraded_IV();

			result.append(type + "   " + addZero(strategyModel.getExpiry()) + "   " + ((int) strategyModel.getStrike()) + "   " + signFormate.format(strategyModel.getQuantity()) + "   "
					+ foramteTradedPrice(df.format(strategyModel.getAvgPrice())) + "   " + foramteTradedPrice(df.format(strategyModel.getLtp())) + "    " + signFormate.format(change) + "    "
					+ foramtePL(pl) + "     " + strategyModel.getTraded_IV() + "     " + strategyModel.getCurrent_IV() + "    " + df.format(ivdiff));
			result.append("\n");

		}
		result.append(line + "\n");
		result.append("Points :                         [" + (int) totalPremiumRecived + "]   [" + (int) currentPremiumRecived + "]    [" + (int) (currentPremiumRecived - totalPremiumRecived) + "]");
		result.append("\n" + line + "\n");
		result.append("Total PL : [" + df.format(totalPL) + "],    At Spot: [" + strategy.getUnderlying_ltp() + "] moved : [" + (diff) + "] - " + strategy.getDataUpdatedAt() + "\n");
		result.append("Min PL " + " :  [" + df.format(strategy.getTotalPLMin()) + "],    At Spot: [" + strategy.getTotalPLMinSpot() + "] moved : ["
				+ df.format(strategy.getTotalPLMinSpot() - strategy.getTradeSpotPrice()) + "] - " + strategy.getTotalPLMinTime() + "\n");
		result.append("Max PL " + " :  [" + df.format(strategy.getTotalPLMax()) + "],    At Spot: [" + strategy.getTotalPLMaxSpot() + "] moved : ["
				+ df.format(strategy.getTotalPLMaxSpot() - strategy.getTradeSpotPrice()) + "] - " + strategy.getTotalPLMaxTime() + "\n");
		result.append(line + "\n");

		result.append("BreakEven : " + "[" + strategy.getLowerBreakEven() + " : " + strategy.getUpperBreakEven() + "], Range : " + "[" + (strategy.getUpperBreakEven() - strategy.getLowerBreakEven())
				+ "], " + "BreakEvenRangeFromTrade [" + strategy.getTradeSpotPrice() + "] [" + (int) (strategy.getLowerBreakEven() - strategy.getTradeSpotPrice()) + " : "
				+ (int) ( strategy.getUpperBreakEven()-strategy.getTradeSpotPrice() ) + "]");

		result.append("\n");
		result.append("MaxLosses : " + "[" + strategy.getMaxLossLowerSide() + " : " + strategy.getMaxLossUpperSide() + "]");
		result.append("\n");
		result.append("MaxProfit : " + "[" + strategy.getMaxProfit() + "]");
		result.append("\n");
		result.append(line + "\n");
		System.out.println(result);
		return result;

	}

	private static String foramtePL(Double price) {
		DecimalFormat signFormate = new DecimalFormat("+#,##0000;-#");
		return signFormate.format(price);
	}

	private static String foramteTradedPrice(String price) {
		if (price.length() == 6) {
			return price;
		} else {
			return " " + price;
		}
	}

	// add blank space in date is single digit
	private static String addZero(String date) {
		if (date.length() == 9) {
			return date;
		} else {
			return " " + date;
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Straing ");
		Map<String, Strategy> strategyMap = CommonUtils.getStrategy2(APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_INTRADAY_TradeStrategy);
		// System.out.println(strategyMap);
		Set<String> keys = strategyMap.keySet();
		for (String string : keys) {
			Strategy strategy = strategyMap.get(string);
			// System.out.println(strategy.getUnderlying());
			StrategyWrite.print(strategy);
			if (CommonUtils.isTimeLessThen(15, 38)) {
				StrategyResult.writeStrategyFile2(strategy);
			}

			System.out.println(strategyMap.get(string));
		}
		System.out.println(CommonUtils.isTimeLessThen(15, 38));

	}

}

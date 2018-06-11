package org.stocksrin.banknifty.option.alog2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.option.currency.models.Column;
import org.option.currency.models.Columns;
import org.stocksrin.banknifty.BankNiftyData;
import org.stocksrin.banknifty.BankNiftyUtils;
import org.stocksrin.banknifty.TickData;
import org.stocksrin.banknifty.TickData.Type;
import org.stocksrin.banknifty.option.alog2.StrategyModel.OptionType;
import org.stocksrin.email.SendEmail;
import org.stocksrin.utils.LoggerSysOut;

public class BNiftyAlgo {

	private static double targetProfit = 800;
	private static double targetLoss = -800;
	public static boolean flag = true;

	public static synchronized List<TickData> getData(List<StrategyModel> strategyModels, String expiry) throws Exception {

		Columns columns = BankNiftyData.getBankNiftyCurrentTimeData2().get(expiry);
		List<TickData> values = new ArrayList<>(3);
		List<Column> data = columns.getDataset();
		double bankNiftySpotPrice = BankNiftyUtils.getLSpotPriceUnderlyingSpotPrice(columns.getUnderlyingSpotPrice());
		String datatime = BankNiftyUtils.getLastUpdatedPriceFromUnderlyingSpotPrice(columns.getUnderlyingSpotPrice());

		for (StrategyModel strategyModel2 : strategyModels) {

			for (Column column : data) {

				double strikePrice = Double.parseDouble(column.getStrike_Price());

				if (strikePrice == strategyModel2.getStrike()) {

					if (strategyModel2.getType().equals(OptionType.PUT)) {
						TickData tickData = new TickData(TickData.Type.PUT, strategyModel2.getStrike(), Float.parseFloat(column.getPE_LTP()), Float.parseFloat(column.getPE_Net_Change()),
								strategyModel2.getClose_price(), bankNiftySpotPrice, strategyModel2.getSpot_close());
						tickData.setQuantity(strategyModel2.getQuantity());
						tickData.setLastUpdateTime(datatime);
						values.add(tickData);
					} else {
						TickData tickData = new TickData(TickData.Type.CALL, strategyModel2.getStrike(), Float.parseFloat(column.getCE_LTP()), Float.parseFloat(column.getCE_Net_Change()),
								strategyModel2.getClose_price(), bankNiftySpotPrice, strategyModel2.getSpot_close());
						tickData.setQuantity(strategyModel2.getQuantity());
						tickData.setLastUpdateTime(datatime);
						values.add(tickData);
					}
				}
			}
		}

		return values;
	}

	private static void targetMail(Double totalPandL, String string, String algoName) {
		if (totalPandL > targetProfit) {

			SendEmail.sentMail(algoName + " Profit " + totalPandL, string);

			LoggerSysOut.print("Target Achived of : " + targetProfit);
			targetProfit = totalPandL + 200;
			LoggerSysOut.print("Next target is Target : " + targetProfit);
		} else if (totalPandL < targetLoss) {

			SendEmail.sentMail(algoName + " Loss " + totalPandL, string);
			targetLoss = totalPandL - 200;
		}
		if (flag) {
			SendEmail.sentMail(algoName + " Market Open Status:" + totalPandL, string);
			flag = false;
		}

	}

	public static StringBuilder algo2(List<TickData> tickDatas, List<StrategyModel> lst, String algoName) {
		StringBuilder string = new StringBuilder();

		DecimalFormat df = new DecimalFormat("#00.00");
		TickData dat = tickDatas.get(0);
		double bankNiftyDiff = dat.getUnderlyingIndexSpotPrice() - dat.getUnderlyingIndexPreviousDayClosePrice();

		MaxMinValues.setMaxMinForBankNIftyPoint(bankNiftyDiff);
		MaxMinValues.setMaxMinForBankNIfty(dat.getUnderlyingIndexSpotPrice());

		String bankNIftyRange = "[" + df.format(MaxMinValues.bankNiftyMinPoint) + " -- " + df.format(MaxMinValues.bankNiftyMaxPoint) + "]";

		double totalSellPoints = 0.00;
		double totalltp = 0.00;
		double totalPL = 0.00;

		string.append(algoName + ": Time: " + dat.getLastUpdateTime() + "              BankNifty Spot : " + dat.getUnderlyingIndexSpotPrice() + " [" + df.format(bankNiftyDiff) + "]" + " L/H:"
				+ bankNIftyRange + "\n");
		string.append("-------------------------------------------------------------------------------------" + "\n");
		string.append("type      sell      ltp        P&L        change      Qty      strike      strikeDiff" + "\n");
		string.append("-------------------------------------------------------------------------------------" + "\n");

		for (TickData tickData : tickDatas) {

			String type = null;
			if (tickData.getType().equals(Type.PUT)) {
				type = "PUT ";
			} else {
				type = "CALL";
			}
			double strikediff = tickData.getStrike() - tickData.getUnderlyingIndexSpotPrice();
			double pl = (tickData.getLtp() - tickData.getPreviousDayClosePrice()) * tickData.getQuantity();
			totalPL = totalPL + pl;
			totalSellPoints = totalSellPoints + tickData.getPreviousDayClosePrice();
			totalltp = totalltp + tickData.getLtp();
			double myTradechange = tickData.getLtp() - tickData.getPreviousDayClosePrice();
			string.append(type + "      " + df.format(tickData.getPreviousDayClosePrice()) + "     " + df.format(tickData.getLtp()) + "      " + df.format(pl) + "     " + df.format(myTradechange)
					+ "     " + tickData.getQuantity() + "      " + tickData.getStrike() + "      " + df.format(strikediff) + "\n");
		}

		string.append("-------------------------------------------------------------------------------------" + "\n");
		double mytradechnage = totalltp - totalSellPoints;

		if (totalPL > MaxMinValues.maxProfit) {
			MaxMinValues.maxProfit = totalPL;

		}
		if (totalPL < MaxMinValues.minProfit) {
			MaxMinValues.minProfit = totalPL;
		}

		String profitRange = "[ " + df.format(MaxMinValues.minProfit) + " -- " + df.format(MaxMinValues.maxProfit) + "]";
		string.append("Total     " + df.format(totalSellPoints) + "     " + df.format(totalltp) + "      " + df.format(totalPL) + "      " + df.format(mytradechnage) + "       " + "P&LHighLow "
				+ profitRange + "\n");
		string.append("-------------------------------------------------------------------------------------" + "\n");
		LoggerSysOut.print(string);
		targetMail(totalPL, string.toString(), algoName);
		return string;
	}

}

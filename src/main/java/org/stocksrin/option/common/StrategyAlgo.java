package org.stocksrin.option.common;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.stocksrin.email.SendEmail;
import org.stocksrin.option.common.automation.StrategyResult;
import org.stocksrin.option.common.model.OptionModle;
import org.stocksrin.option.common.model.OptionModles;
import org.stocksrin.option.common.model.StrategyModel;
import org.stocksrin.option.common.model.StrategyModel.OptionType;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.ExceptionUtils;
import org.stocksrin.utils.LoggerSysOut;

public class StrategyAlgo extends Thread {

	private static DecimalFormat df = new DecimalFormat("#00.00");
	private String line = "-------------------------------------------------------------------------------------------------------------------------------------------------";
	private double targetProfit = 800;
	private double targetLoss = -800;

	private double minProfit;
	private double maxProfit;

	private String maxPLTime;
	private String minPLTime;

	private String maxPLSpot;
	private String minPLSpot;

	private double underlyingdayHigh;
	private double underlyingdayLow;

	private Map<String, OptionModles> dataModle;
	private List<StrategyModel> lst;
	private String algoName;
	// Strangle@NextWeekly-Strategy_BANKNIFTY
	private String fileName;

	private long timeInteval;
	private String strategyDIR;

	public StrategyAlgo(Map<String, OptionModles> dataModle, List<StrategyModel> lst, String algoName, long timeInteval, String fileName, String strategyDIR) {
		this.dataModle = dataModle;
		this.lst = lst;
		this.algoName = algoName;
		this.timeInteval = timeInteval;
		this.fileName = fileName;
		this.strategyDIR = strategyDIR;
	}

	@Override
	public void run() {

		while (CommonUtils.getEveningTimeForStrategy()) {
			try {
				StringBuilder result = algo();
				// use sysout only
				System.out.println(result);
				Thread.sleep(timeInteval);
				StrategyResult.map.put(fileName, result.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			StrategyResult.writeStrategyFile(fileName, strategyDIR);
		} catch (Exception e) {
			e.printStackTrace();
			SendEmail.sentMail("CRITICAL! Strategy Result Export Issue", "ERROR " + ExceptionUtils.getStackTrace(e));
		}
	}

	public StringBuilder algo() throws Exception {

		StringBuilder string = new StringBuilder();

		DecimalFormat signFormate = new DecimalFormat("+#,##00;-#");
		StrategyModel strategyModel1 = lst.get(0);
		String expiry = strategyModel1.getExpiry();

		double spotAtTrade = strategyModel1.getSpot_close();

		OptionModles data = dataModle.get(expiry);
		if (data == null) {
			return string;
		}
		String diff = df.format(data.getSpot() - spotAtTrade);
		double totalPL = 0.00;
		double todayRange = underlyingdayLow - underlyingdayHigh;
		String tradedDay = "";
		String todayDate = "";
		String todayDay = "";
		try {
			tradedDay = DateUtils.getDayFromDate(strategyModel1.getTradeDate(), "ddMMMyyyy");
			todayDate = DateUtils.dateToString(new Date(), "ddMMMyyyy");
			todayDay = DateUtils.getDayFromDate(todayDate, "ddMMMyyyy");
		} catch (Exception e) {
			e.printStackTrace();
		}
		string.append(algoName + " Time: " + data.getLastDataUpdated() + "    Spot : " + data.getSpot() + " L/H: " + "[" + df.format(underlyingdayLow) + " -- " + df.format(underlyingdayHigh) + "]"
				+ " Range [" + df.format(todayRange) + "]\n");
		string.append(line + "\n");
		string.append("Trade Date: " + strategyModel1.getTradeDate() + " (" + tradedDay + "), Traded Price:  " + spotAtTrade + "\n");
		string.append("Status  At: " + todayDate + " (" + todayDay + "), Current Price: " + data.getSpot() + " [" + diff + "] Diff from Trade \n");

		string.append(line + "\n");
		string.append("type   expiry    Qty    tradePrice    ltp     change   P&L   tradedIV   currentIV  IVDiff   strike   tradeSDiff   currentSDiff  moved" + "\n");
		string.append(line + "\n");

		for (StrategyModel strategyModel : lst) {

			OptionModles currntData = dataModle.get(strategyModel.getExpiry());

			if (currntData == null) {
				throw new Exception(strategyModel.getExpiry() + " Expiry Data is not avaiable");
			}
			List<OptionModle> price = currntData.getOptionModle();

			double iv = 0.0;
			double ltp = 0.0;
			String type = null;

			if (strategyModel.getType().equals(OptionType.PUT)) {
				type = "PUT ";
				for (OptionModle optionModle : price) {
					if (optionModle.getStrike_price().equals(strategyModel.getStrike())) {
						ltp = optionModle.getP_ltp();
						if (optionModle.getP_iv() != null) {
							iv = optionModle.getP_iv();
						}

					}
				}
			} else {
				type = "CALL";
				for (OptionModle optionModle : price) {
					if (optionModle.getStrike_price().equals(strategyModel.getStrike())) {
						ltp = optionModle.getC_ltp();
						if (optionModle.getC_iv() != null) {
							iv = optionModle.getC_iv();
						}

					}
				}
			}

			double ivdiff = iv - strategyModel.getTraded_IV();
			double currentstrikeDiff = strategyModel.getStrike() - data.getSpot();
			double tradeStrikeDiff = strategyModel.getStrike() - spotAtTrade;
			double moved = currentstrikeDiff - tradeStrikeDiff;
			double pl = (ltp - strategyModel.getClose_price()) * strategyModel.getQuantity();
			totalPL = totalPL + pl;
			double change = ltp - strategyModel.getClose_price();
			string.append(type + "   " + addZero(strategyModel.getExpiry()) + "   " + signFormate.format(strategyModel.getQuantity()) + "   "
					+ foramteTradedPrice(df.format(strategyModel.getClose_price())) + "     " + foramteTradedPrice(df.format(ltp)) + "    " + signFormate.format(change) + "    " + foramtePL(pl)
					+ "   " + df.format(strategyModel.getTraded_IV()) + "    " + df.format(iv) + "    " + ivdiffFormate(ivdiff) + "    " + strategyModel.getStrike() + "    "
					+ signFormate.format(tradeStrikeDiff) + "    " + signFormate.format(currentstrikeDiff) + "    " + signFormate.format(moved) + "\n");
		}

		string.append(line + "\n");

		if (underlyingdayHigh == 0.0 && underlyingdayLow == 0.0) {
			maxProfit = totalPL;
			minProfit = totalPL;

			underlyingdayHigh = data.getSpot();
			underlyingdayLow = data.getSpot();

			maxPLTime = data.getLastDataUpdated();
			maxPLSpot = data.getSpot().toString() + "[" + diff + "], point moved from traded time ";

			minPLTime = data.getLastDataUpdated();
			minPLSpot = data.getSpot().toString() + "[" + diff + "], point moved from traded time";

		}

		if (totalPL > maxProfit) {
			maxProfit = totalPL;
			maxPLTime = data.getLastDataUpdated();
			maxPLSpot = data.getSpot().toString() + "[" + diff + "], point moved from traded time";

		}
		if (totalPL < minProfit) {
			minProfit = totalPL;
			minPLTime = data.getLastDataUpdated();
			minPLSpot = data.getSpot().toString() + "[" + diff + "], point moved from traded time ";

		}

		if (underlyingdayHigh < data.getSpot()) {
			underlyingdayHigh = data.getSpot();

		}
		if (underlyingdayLow > data.getSpot()) {
			underlyingdayLow = data.getSpot();
		}

		// String profitRange = "[ " + df.format(minProfit) + " -- " +
		// df.format(maxProfit) + "]";
		String pl = "[ Total PL :" + df.format(totalPL) + " ]";
		string.append(pl + "\n");
		string.append("Min PL " + "[" + df.format(minProfit) + "], At Spot: " + minPLSpot + " - " + minPLTime + "\n");
		string.append("Max PL " + "[" + df.format(maxProfit) + "], At Spot: " + maxPLSpot + " - " + maxPLTime + "\n");

		string.append(line + "\n");

		WebTest.data.put(algoName, pl);
		targetMail(totalPL, string.toString(), algoName);
		return string;
	}

	private void targetMail(Double totalPandL, String string, String algoName) {
		if (totalPandL > targetProfit) {

			SendEmail.sentMail(algoName + " Profit " + totalPandL, string);

			LoggerSysOut.print("Target Achived of : " + targetProfit);
			targetProfit = totalPandL + 300;
			LoggerSysOut.print("Next target is Target : " + targetProfit);
		} else if (totalPandL < targetLoss) {

			SendEmail.sentMail(algoName + " Loss " + totalPandL, string);
			targetLoss = totalPandL - 300;
		}
	}

	private static String ivdiffFormate(double ivdiff) {

		String diff = df.format(ivdiff);
		if (!diff.contains("-")) {
			diff = " " + diff;
		}
		return diff;

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

	private static String foramtePL(Double price) {
		DecimalFormat signFormate = new DecimalFormat("+#,##0000;-#");
		return signFormate.format(price);
	}
}

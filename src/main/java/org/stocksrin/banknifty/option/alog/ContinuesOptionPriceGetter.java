package org.stocksrin.banknifty.option.alog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TimeZone;

import org.option.currency.models.Column;
import org.option.currency.models.Columns;
import org.stocksrin.banknifty.BankNiftyUtils;
import org.stocksrin.banknifty.TickData;
import org.stocksrin.email.SendEmail;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;

public class ContinuesOptionPriceGetter {

	//private final static int MARKET_HR=15;
	private static final int MARKET_HR=15;
	private static final int MARKET_MINUTE=35;
	private double targetProfit;
	private double targetLoss;
	private double putLastTradedPrice;
	private double callLastTradedPrice;
	private BNiftyOptionLastDayTrade lastDayTrade;
	private double maxProfit;
	private double minProfit;
	private String maxProfitString;
	private String maxLossString;
	
	private static LinkedHashMap<String, List<TickData>> optionData = new LinkedHashMap<>();

	public ContinuesOptionPriceGetter(BNiftyOptionLastDayTrade lastDayTrade) {
		this.lastDayTrade = lastDayTrade;
		putLastTradedPrice = lastDayTrade.getPut_close_price();
		callLastTradedPrice =lastDayTrade.getCall_close_price();
		targetProfit = lastDayTrade.getTarget();
		targetLoss = lastDayTrade.getLoss();
	}

	public StringBuilder algo(List<TickData> tickData) {

		TickData putData = tickData.get(0);
		TickData callData = tickData.get(1);

		double totalSellPoints = putLastTradedPrice + callLastTradedPrice;
		double totalltp = putData.getLtp() + callData.getLtp();
		double totalchange = putData.getChange() + callData.getChange();
		DecimalFormat df = new DecimalFormat("###.##");

		double putProfit = (putLastTradedPrice - putData.getLtp()) * 40;
		double callProfit = (callLastTradedPrice - callData.getLtp()) * 40;

		double totalPandL = putProfit + callProfit;

		double bniftyDiff = putData.getUnderlyingIndexSpotPrice() - lastDayTrade.getSpot_close();

		String bniftyDiff2 = df.format(bniftyDiff);

		double putstrikeDiff = putData.getUnderlyingIndexSpotPrice() - putData.getStrike();
		double callstrikeDiff = callData.getStrike() - callData.getUnderlyingIndexSpotPrice();

		if (totalPandL > maxProfit) {
			maxProfit = totalPandL;
			maxProfitString="[ profit: "+df.format(maxProfit)+", At BankNifty: "+putData.getUnderlyingIndexSpotPrice()+" ]";
		}
		if (totalPandL < minProfit) {
			minProfit = totalPandL;
		}

		String profitRange = "[ " + df.format(minProfit) + " -- " + df.format(maxProfit) + "]";

		StringBuilder string = new StringBuilder();
		string.append("                              BankNifty Spot : " + putData.getUnderlyingIndexSpotPrice() + " [" + bniftyDiff2 + "] " + "\n");
		string.append("-------------------------------------------------------------------" + "\n");
		string.append("type     sell     ltp     change    P&L     strike    strikeDiff" + "\n");
		string.append("-------------------------------------------------------------------" + "\n");
		string.append("PUT      " + putLastTradedPrice + "     " +  df.format(putData.getLtp()) + "    " +  df.format(putData.getChange()) + "    " + df.format(putProfit) + "      " + putData.getStrike() + "    "
				+ df.format(putstrikeDiff) + "\n");
		string.append("CALL     " + callLastTradedPrice + "     " +  df.format(callData.getLtp()) + "    " +  df.format(callData.getChange()) + "    " + df.format(callProfit) + "      " + callData.getStrike() + "    "
				+ df.format(callstrikeDiff) + "\n");
		string.append("-------------------------------------------------------------------" + "\n");
		string.append(
				"Total    " + totalSellPoints + "    " + df.format(totalltp) + "    " + df.format(totalchange) + "    [" + df.format(totalPandL) + "]" + "   " + "P&LHighLow " + profitRange + "\n");
		string.append("-------------------------------------------------------------------" + "\n");

		System.out.println(string);
		//System.out.println(maxProfitString);

		if (totalPandL > targetProfit) {

			SendEmail.sentMail(" Profit " + totalPandL, string.toString());

			System.out.println("Target Achived of : " + targetProfit);
			targetProfit = targetProfit + 250;
			System.out.println("Next target is Target : " + targetProfit);
		} else if (totalPandL < targetLoss) {

			SendEmail.sentMail( " Loss " + totalPandL, string.toString());
			targetLoss = targetLoss - 250;
		}
		return string;
	}

	public synchronized void fetchOptionData() {
		StringBuilder result = new StringBuilder();
		optionData.clear();
		while (true) {
			try {

				if (!getEveningTime()) {
					SendEmail.sentMail( "Trading DayEnd BankNifty Report ", result.toString());
					break;
				}
				String url = APPConstant.BANKNIFTY_WEEKLY_OPTION_URL_BY_Expiry + lastDayTrade.getExpiry();
				Columns columns = BankNiftyUtils.getBankNiftyOptionChain(url, lastDayTrade.getExpiry());

				String datatime = BankNiftyUtils.getLastUpdatedPriceFromUnderlyingSpotPrice(columns.getUnderlyingSpotPrice());
				double bankNiftySpotPrice = BankNiftyUtils.getLSpotPriceUnderlyingSpotPrice(columns.getUnderlyingSpotPrice());
				System.out.println("Last Data time " + datatime);
				List<Column> data = columns.getDataset();
				List<TickData> values = new ArrayList<>(2);
				for (Column column : data) {

					double strikePrice = Double.parseDouble(column.getStrike_Price());
					if (strikePrice == lastDayTrade.getPut_strike()) {
						TickData tickData = new TickData(TickData.Type.PUT, lastDayTrade.getPut_strike(), Float.parseFloat(column.getPE_LTP()), Float.parseFloat(column.getPE_Net_Change()), bankNiftySpotPrice);
						values.add(tickData);
					}

					if (strikePrice == lastDayTrade.getCall_strike()) {

						TickData tickData = new TickData(TickData.Type.CALL, lastDayTrade.getCall_strike(), Float.parseFloat(column.getCE_LTP()), Float.parseFloat(column.getCE_Net_Change()), bankNiftySpotPrice);
						values.add(tickData);
					}
				}
				result = algo(values);
				optionData.put(datatime, values);
			} catch (Exception e) {
				System.out.println("ERROR!");
				e.printStackTrace();
			}

			try {
				Thread.sleep(120000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		System.out.println("********** Day End *********");
	}

	public static boolean getEveningTime() {
		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.HOUR_OF_DAY, MARKET_HR);
		today.set(Calendar.MINUTE, MARKET_MINUTE);
		today.set(Calendar.SECOND, 0);

		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("IST"));

		if (now.after(today)) {
			System.out.println("Time is more then 3:30 pm");
			return false;
		} else {
			// System.out.println("IST Time " + now.get(Calendar.HOUR_OF_DAY) +
			// ":" + now.get(Calendar.MINUTE));
			return true;
		}

	}

	public static void main(String[] args) throws InterruptedException {

		/*
		 * double input = 32; System.out.println("double : " + input);
		 * System.out.println("double : " + String.format("%.2f", input));
		 */

		List<BNiftyOptionLastDayTrade> lastDayTrade = CommonUtils.getBankNiftyLastTradeData2();
		// System.out.println("BNiftyOptionLastDayTrade" + lastDayTrade);
		ContinuesOptionPriceGetter continuesOptionPriceGetter = new ContinuesOptionPriceGetter(lastDayTrade.get(0));
		continuesOptionPriceGetter.fetchOptionData();

	}

	public static LinkedHashMap<String, List<TickData>> getOptionData() {
		return optionData;
	}

}
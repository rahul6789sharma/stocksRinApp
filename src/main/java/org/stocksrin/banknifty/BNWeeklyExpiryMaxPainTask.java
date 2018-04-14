package org.stocksrin.banknifty;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TimerTask;
import java.util.TreeSet;

import org.option.currency.models.MaxPains;
import org.stocksrin.option.model.BankNiftyDailyMaxPain;
import org.stocksrin.option.model.OptionModles;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;

public class BNWeeklyExpiryMaxPainTask extends TimerTask {

	@Override
	public void run() {

		Map<String, MaxPains> data = BankNiftyData.getBankNiftyMaxPainData();
		Set<String> expiryies = data.keySet();
		SortedSet<String> sortedExpiry = new TreeSet<>();
		for (String string : expiryies) {
			sortedExpiry.add(string);
		}
		String nearWeeklyExpiry = sortedExpiry.first();

		OptionModles ob = BankNiftyData.getBankNiftyCurrentTimeData().get(nearWeeklyExpiry);
		Double bankNiftySpotPrice;
		try {
			bankNiftySpotPrice = BankNiftyUtils.getLSpotPriceUnderlyingSpotPrice(ob.getUnderlyingSpotPrice());
			OptionAnalysisModle optionAnalysisModle = BankNiftyData.getMaxPainSerieas().get(nearWeeklyExpiry);
			BankNiftyDailyMaxPain bankNiftyDailyMaxPain = BankNiftyUtils.convertData(optionAnalysisModle, nearWeeklyExpiry, bankNiftySpotPrice.toString());
			System.out.println(bankNiftyDailyMaxPain.toCSVForWeeklyEOD());
			String csv = bankNiftyDailyMaxPain.toCSVForWeeklyEOD() + "," + DateUtils.getTodayString() + "," + isExpiry(nearWeeklyExpiry);
			CommonUtils.appendData(csv, APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_DAILYMAXPAIN_FILE2);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
		/*
		 * OptionDataCollectorTask2 optionDataCollectorTask2 = new
		 * OptionDataCollectorTask2(); optionDataCollectorTask2.run();
		 * 
		 * BNWeeklyExpiryMaxPainTask bnWeeklyExpiryMaxPainTask = new
		 * BNWeeklyExpiryMaxPainTask(); bnWeeklyExpiryMaxPainTask.run();
		 * 
		 * // String data = "12APR2018"; // System.out.println(isExpiry(data));
		 */
	}

	private static Boolean isExpiry(String expiry) throws Exception {

		String d = DateUtils.dateToString(new Date(), APPConstant.DATEFORMATE_BN_EXPIRY);
		return d.toUpperCase().equals(expiry);
	}
}

package org.stocksrin.option.banknifty;

import org.option.currency.models.MaxPains;
import org.smarttrade.options.utils.Calculation;
import org.stocksrin.option.common.model.OptionModles;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.OptionUtils;

public class BankNiftyUtils {

	// can be passed null , null means current expiry
	public static OptionModles getBankNiftyOptionData(String expiry) throws Exception {

		OptionModles optionModles = BankNiftyUtils.getOptionChain(expiry);	
		MaxPains maxPain = Calculation.calMaxPain(optionModles.getOptionModle(), APPConstant.BNF_STRIKE_DIFF, optionModles.getExpiry());
		optionModles.setMaxPainStrick(maxPain.getMaxPainStrick());
		return optionModles;
	}
	
	private static synchronized OptionModles getOptionChain(String expiry) throws Exception {
		String url = null;
		if (expiry == null) {
			url = APPConstant.BANKNIFTY_WEEKLY_OPTION_URL;
		} else {
			url = APPConstant.BANKNIFTY_WEEKLY_OPTION_URL_BY_Expiry + expiry;
		}
		return OptionUtils.getOptionChain(url, expiry);
	}


/*
	private static double getLSpotPriceUnderlyingSpotPrice(String getDateFromUnderlyingSpotPrice) throws Exception {
		// String price="BANKNIFTY 24440.70  As on Mar 09 2018 11:19:53 IST";

		try {
			String[] a = getDateFromUnderlyingSpotPrice.split("As on");
			String b = a[0].trim();
			String[] c = b.split("BANKNIFTY");
			String d = c[1].trim();
			int length = d.length();
			String e = d.substring(0, length - 1);
			return Double.parseDouble(e);
		} catch (Exception e) {
			throw new Exception("ERROR! getLSpotPriceUnderlyingSpotPrice for string:  '" + getDateFromUnderlyingSpotPrice + "'  \n" + e.getMessage());
		}

	}

	private static String getLastUpdatedPriceFromUnderlyingSpotPrice(String getDateFromUnderlyingSpotPrice) throws Exception {
		// Underlying Index: BANKNIFTY 26399.80  As on May 16, 2018 13:37:26 IST
		try {
			String a[] = getDateFromUnderlyingSpotPrice.split(",");
			String d = a[1].trim();
			return d.substring(5, 13);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error! for " + getDateFromUnderlyingSpotPrice);
		}

	}*/

	/*
	 * private static Date getDateFromUnderlyingSpotPrice2(String
	 * getDateFromUnderlyingSpotPrice) {
	 * 
	 * try { String dateForamte = "MMM dd yyyy hh:mm:ss Z"; String a[] =
	 * getDateFromUnderlyingSpotPrice.split("As on"); String d = a[1].trim();
	 * Date date = DateUtils.stringToDate(d, dateForamte); return date; } catch
	 * (Exception e) { e.printStackTrace(); } return null;
	 * 
	 * }
	 */
	/*
	 * private static String getDateFromUnderlyingSpotPrice(String
	 * getDateFromUnderlyingSpotPrice) { String dateString = null; try { String
	 * dateForamte = "MMM dd yyyy hh:mm:ss Z"; String a[] =
	 * getDateFromUnderlyingSpotPrice.split("As on"); String d = a[1].trim();
	 * Date date = DateUtils.stringToDate(d, dateForamte); dateString =
	 * DateUtils.dateToString(date, APPConstant.DATEFORMATE_dd_MM_yyyy); return
	 * dateString; } catch (Exception e) { e.printStackTrace(); } return new
	 * Date().toString(); }
	 */





/*	private static DailyMaxPain convertData(LiveMaxPainModle optionAnalysisModle, String expiry, String spotPrice) throws Exception {
		Double maxPain = optionAnalysisModle.getMaxPains().get(optionAnalysisModle.getMaxPains().size() - 1);
		Integer totalCE = optionAnalysisModle.getTotalCE();
		Integer totalPE = optionAnalysisModle.getTotalPE();

		String date = DateUtils.dateToString(new Date(), APPConstant.DATEFORMATE_BN_EXPIRY);
		DailyMaxPain bankNiftyDailyMaxPain = new DailyMaxPain(date, expiry, getExpiryType(expiry), maxPain, totalCE, totalPE);
		bankNiftyDailyMaxPain.setSpot(Double.parseDouble(spotPrice));
		Double pcr = ((double) totalPE / (double) totalCE);
		bankNiftyDailyMaxPain.setPcr(Double.parseDouble(NumFormater.formatNumber1(pcr)));
		return bankNiftyDailyMaxPain;
	}

	private static String getExpiryType(String expiry) throws Exception {
		int a = DateUtils.getWeekOfMonth(expiry);
		if (a < 4) {
			return "W";
		} else {
			return "M";
		}
	}*/

}
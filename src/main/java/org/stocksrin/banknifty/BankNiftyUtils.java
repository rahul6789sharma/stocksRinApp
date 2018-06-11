package org.stocksrin.banknifty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.option.currency.models.Column;
import org.option.currency.models.Columns;
import org.option.currency.models.MaxPains;
import org.smarttrade.options.utils.Calculation;
import org.stocksrin.option.model.BankNiftyDailyMaxPain;
import org.stocksrin.option.model.OptionModle;
import org.stocksrin.option.model.OptionModles;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonHTMLDocParsher;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.HTMLPageDocumentDownloader;
import org.stocksrin.utils.LoggerSysOut;
import org.stocksrin.utils.NumFormater;

public class BankNiftyUtils {

	public static void main(String[] args) throws Exception {
		String b = "May 23, 2018 12:58:55 IST";
		String[] c = b.replaceAll(",", "").split(" ");
		LoggerSysOut.print(c[0]);
		LoggerSysOut.print(c[1]);
		LoggerSysOut.print(c[2]);
		LoggerSysOut.print(c[3]);

		LoggerSysOut.print(c[1] + "_" + c[0] + "_" + c[2]);
		// dateString 23_05_2018

		// String a ="Underlying Index: BANKNIFTY 25881.85  As on May 23, 2018
		// 12:11:24 IST";

		// LoggerSysOut.print(getDate(a));
	}

	private static String getDateStringCustom(String date) {
		String[] c = date.replaceAll(",", "").split(" ");
		return c[1] + "_" + c[0] + "_" + c[2];
	}

	public static String getDate(String dateInString) throws Exception {
		// Underlying Index: BANKNIFTY 26496.25  As on May 14, 2018 15:10:29 IST
		// LoggerSysOut.print("Processing " + dateInString);
		String dateString = null;
		try {
			//String dateForamte = "MMM dd, yyyy hh:mm:ss Z";
			String a[] = dateInString.split("As on");
			String d = a[1].trim();
			// LoggerSysOut.print("d " + d);

			// Date date = DateUtils.stringToDate(d, dateForamte);
			// LoggerSysOut.print("date " + date);

			// dateString = DateUtils.dateToString(date,
			// APPConstant.DATEFORMATE_dd_MMM_yyyy);

			dateString = getDateStringCustom(d);
			// LoggerSysOut.print("dateString " +dateString);

			Date todayDate = new Date();
			String today = DateUtils.dateToString(todayDate, APPConstant.DATEFORMATE_dd_MMM_yyyy);

			if (!dateString.equals(today)) {

				throw new Exception("BankNifty Date and Today Date is different " + dateInString);
			}
			return dateString;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ERROR getDate " + dateInString);
		}
		// return new Date().toString();
	}

	public static double getLSpotPriceUnderlyingSpotPrice(String getDateFromUnderlyingSpotPrice) throws Exception {
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

	public static String getLastUpdatedPriceFromUnderlyingSpotPrice(String getDateFromUnderlyingSpotPrice) throws Exception {
		// Underlying Index: BANKNIFTY 26399.80  As on May 16, 2018 13:37:26 IST
		try {
			String a[] = getDateFromUnderlyingSpotPrice.split(",");
			String d = a[1].trim();
			return d.substring(5, 13);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error! for " + getDateFromUnderlyingSpotPrice);
		}

	}

	public static Date getDateFromUnderlyingSpotPrice2(String getDateFromUnderlyingSpotPrice) {

		try {
			String dateForamte = "MMM dd yyyy hh:mm:ss Z";
			String a[] = getDateFromUnderlyingSpotPrice.split("As on");
			String d = a[1].trim();
			Date date = DateUtils.stringToDate(d, dateForamte);
			return date;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static String getDateFromUnderlyingSpotPrice(String getDateFromUnderlyingSpotPrice) {
		String dateString = null;
		try {
			String dateForamte = "MMM dd yyyy hh:mm:ss Z";
			String a[] = getDateFromUnderlyingSpotPrice.split("As on");
			String d = a[1].trim();
			Date date = DateUtils.stringToDate(d, dateForamte);
			dateString = DateUtils.dateToString(date, APPConstant.DATEFORMATE_dd_MM_yyyy);
			return dateString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date().toString();
	}

	public static synchronized Columns getOptionDataData(String expiry) {
		String url = null;
		if (expiry == null) {
			url = APPConstant.BANKNIFTY_WEEKLY_OPTION_URL;
		} else {
			url = APPConstant.BANKNIFTY_WEEKLY_OPTION_URL_BY_Expiry + expiry;
		}
		return BankNiftyUtils.getBankNiftyOptionChain(url, expiry);
	}

	private static Columns getBankNiftyOptionChain(String url, String expiry) {

		Document doc = HTMLPageDocumentDownloader.getDocument(url);
		try {
			Elements c = CommonHTMLDocParsher.getOptionChainTable(doc, "octable", 0);
			Columns columns = CommonHTMLDocParsher.parseNSEColumn(doc, c);

			String spotPrice = columns.getUnderlyingSpotPrice().substring(24, 31);
			columns.setSpotPrice(spotPrice);

			List<String> expiryList = CommonHTMLDocParsher.getSelectBoxById(doc, "date", 0);
			List<String> firstExpirys = new ArrayList<>();
			for (int i = 0; i < 4; i++) {
				firstExpirys.add(expiryList.get(i));
			}
			if (expiry != null) {
				columns.setExpiry(expiry);
			} else {
				columns.setExpiry(firstExpirys.get(0));
			}

			columns.setExpiryList(firstExpirys);

			return columns;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static OptionModles getBankNiftyOptionData(String expiry) throws Exception {

		Columns columns = BankNiftyUtils.getOptionDataData(expiry);
		OptionModles optionModles = BankNiftyUtils.bankNiftyOptionDataWrapper(columns);
		MaxPains maxPain = Calculation.calMaxPain(optionModles.getOptionModle(), APPConstant.BNF_STRIKE_DIFF, optionModles.getExpiry());
		optionModles.setMaxPainStrick(maxPain.getMaxPainStrick());
		return optionModles;
	}

	public static OptionModles bankNiftyOptionDataWrapper(Columns columns) throws Exception {
		OptionModles optionModles = new OptionModles();
		List<Column> colums = columns.getDataset();
		List<OptionModle> lst = new ArrayList<>();

		for (Column column : colums) {
			OptionModle optionModle = new OptionModle();

			String ce_Change_in_OI = column.getCE_Change_in_OI();

			if (ce_Change_in_OI.equals("-")) {
				ce_Change_in_OI = "0";
			} else {
				ce_Change_in_OI = column.getCE_Change_in_OI().replaceAll(",", "");
			}

			optionModle.setC_change_oi(Integer.parseInt(ce_Change_in_OI));

			String CE_LTP = column.getCE_LTP();

			if (CE_LTP.equals("-")) {
				CE_LTP = "0";
			} else {
				CE_LTP = column.getCE_LTP().replaceAll(",", "");
			}

			optionModle.setC_ltp(Double.parseDouble(CE_LTP));

			String CE_Net_Change = column.getCE_Net_Change();

			if (CE_Net_Change.equals("-")) {
				CE_Net_Change = "0";
			} else {
				CE_Net_Change = column.getCE_Net_Change().replaceAll(",", "");
			}

			optionModle.setC_net_change(Double.parseDouble(CE_Net_Change));

			String c_oi = column.getCE_OI();

			if (c_oi.equals("-")) {
				c_oi = "0";
			} else {
				c_oi = column.getCE_OI().replaceAll(",", "");
			}

			optionModle.setC_oi(Integer.parseInt(c_oi));

			String ce_Volume = column.getCE_Volume();

			if (ce_Volume.equals("-")) {
				ce_Volume = "0";
			} else {
				ce_Volume = column.getCE_Volume().replaceAll(",", "");
			}
			optionModle.setC_volume(Integer.parseInt(ce_Volume));

			String PE_Change_in_OI = column.getPE_Change_in_OI();

			if (PE_Change_in_OI.equals("-")) {
				PE_Change_in_OI = "0";
			} else {
				PE_Change_in_OI = column.getPE_Change_in_OI().replaceAll(",", "");
			}

			optionModle.setP_change_oi(Integer.parseInt(PE_Change_in_OI));

			String PE_Net_Change = column.getPE_Net_Change();

			if (PE_Net_Change.equals("-")) {
				PE_Net_Change = "0";
			} else {
				PE_Net_Change = column.getPE_Net_Change().replaceAll(",", "");
			}

			optionModle.setP_net_change(Double.parseDouble(PE_Net_Change));

			String PE_LTP = column.getPE_LTP();

			if (PE_LTP.equals("-")) {
				PE_LTP = "0";
			} else {
				PE_LTP = column.getPE_LTP().replaceAll(",", "");
			}

			optionModle.setP_ltp(Double.parseDouble(PE_LTP));

			String PE_OI = column.getPE_OI();

			if (PE_OI.equals("-")) {
				PE_OI = "0";
			} else {
				PE_OI = column.getPE_OI().replaceAll(",", "");
			}
			optionModle.setP_oi(Integer.parseInt(PE_OI));

			String PE_Volume = column.getPE_Volume();
			if (PE_Volume.equals("-")) {
				PE_Volume = "0";
			} else {
				PE_Volume = column.getPE_Volume().replaceAll(",", "");
			}
			optionModle.setP_volume(Integer.parseInt(PE_Volume));

			String Strike_Price = column.getStrike_Price();
			if (Strike_Price.equals("-")) {
				Strike_Price = "0";
			} else {
				Strike_Price = column.getStrike_Price().replaceAll(",", "");
			}

			optionModle.setStrike_price(Double.parseDouble(Strike_Price));
			lst.add(optionModle);
		}
		optionModles.setOptionModle(lst);
		optionModles.setTotal_ce_oi(columns.getTotal_ce_oi());
		optionModles.setTotal_pe_oi(columns.getTotal_pe_oi());
		optionModles.setExpiry(columns.getExpiry());
		optionModles.setExpiryList(columns.getExpiryList());
		optionModles.setUnderlyingSpotPrice(columns.getUnderlyingSpotPrice());
		optionModles.setSpot(getLSpotPriceUnderlyingSpotPrice(columns.getUnderlyingSpotPrice()));
		optionModles.setLastDataUpdated(columns.getLastDataUpdated());
		String date = BankNiftyUtils.getDate(optionModles.getUnderlyingSpotPrice());
		optionModles.setDate(date);
		return optionModles;
	}

	public static BankNiftyDailyMaxPain convertData(LiveMaxPainModle optionAnalysisModle, String expiry, String spotPrice) throws Exception {
		Double maxPain = optionAnalysisModle.getMaxPains().get(optionAnalysisModle.getMaxPains().size() - 1);
		Integer totalCE = optionAnalysisModle.getTotalCE();
		Integer totalPE = optionAnalysisModle.getTotalPE();

		String date = DateUtils.dateToString(new Date(), APPConstant.DATEFORMATE_BN_EXPIRY);
		BankNiftyDailyMaxPain bankNiftyDailyMaxPain = new BankNiftyDailyMaxPain(date, expiry, getExpiryType(expiry), maxPain, totalCE, totalPE);
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
	}

}
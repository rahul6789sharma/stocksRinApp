package org.stocksrin.banknifty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.option.currency.models.Column;
import org.option.currency.models.Columns;
import org.stocksrin.option.model.BankNiftyDailyMaxPain;
import org.stocksrin.option.model.OptionModle;
import org.stocksrin.option.model.OptionModles;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonHTMLDocParsher;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.HTMLPageDocumentDownloader;
import org.stocksrin.utils.NumFormater;

public class BankNiftyUtils {

	public static double getLSpotPriceUnderlyingSpotPrice(String getDateFromUnderlyingSpotPrice) throws Exception {
		// String price="BANKNIFTY 24440.70  As on Mar 09, 2018 11:19:53 IST";

		try {
			String[] a = getDateFromUnderlyingSpotPrice.split("As on");
			String b = a[0].trim();
			String[] c = b.split("BANKNIFTY");
			String d = c[1].trim();
			int length = d.length();
			String e = d.substring(0, length - 1);
			return Double.parseDouble(e);
		} catch (Exception e) {
			throw new Exception("ERROR! getLSpotPriceUnderlyingSpotPrice for string  '" + getDateFromUnderlyingSpotPrice + "'  \n" + e.getMessage());
		}

	}

	public static String getLastUpdatedPriceFromUnderlyingSpotPrice(String getDateFromUnderlyingSpotPrice) {
		String a[] = getDateFromUnderlyingSpotPrice.split(",");
		String d = a[1].trim();
		return d.substring(5, 13);
	}

	public static Date getDateFromUnderlyingSpotPrice2(String getDateFromUnderlyingSpotPrice) {

		try {
			String dateForamte = "MMM dd, yyyy hh:mm:ss Z";
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
			String dateForamte = "MMM dd, yyyy hh:mm:ss Z";
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

	public static Columns getBankNiftyOptionChain(String url, String expiry) {

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

	public static OptionModles bankNiftyOptionDataWrapper(Columns columns) {
		OptionModles optionModles = new OptionModles();
		List<Column> colums = columns.getDataset();
		List<OptionModle> lst = new ArrayList<>();

		for (Column column : colums) {
			OptionModle optionModle = new OptionModle();
			optionModle.setC_change_oi(column.getCE_Change_in_OI());
			optionModle.setC_ltp(column.getCE_LTP());
			optionModle.setC_net_change(column.getCE_Net_Change());

			optionModle.setC_oi(column.getCE_OI());
			optionModle.setC_volume(column.getCE_Volume());
			optionModle.setP_change_oi(column.getPE_Change_in_OI());
			optionModle.setP_net_change(column.getPE_Net_Change());
			optionModle.setP_ltp(column.getPE_LTP());
			optionModle.setP_oi(column.getPE_OI());
			optionModle.setP_volume(column.getPE_Volume());
			optionModle.setStrike_price(column.getStrike_Price());
			lst.add(optionModle);
		}
		optionModles.setOptionModle(lst);
		optionModles.setTotal_ce_oi(columns.getTotal_ce_oi());
		optionModles.setTotal_pe_oi(columns.getTotal_pe_oi());
		optionModles.setExpiry(columns.getExpiry());
		optionModles.setExpiryList(columns.getExpiryList());
		optionModles.setUnderlyingSpotPrice(columns.getUnderlyingSpotPrice());
		optionModles.setLastDataUpdated(columns.getLastDataUpdated());
		return optionModles;
	}
	
	public static BankNiftyDailyMaxPain convertData(OptionAnalysisModle optionAnalysisModle, String expiry, String spotPrice) throws Exception {
		Double maxPain = optionAnalysisModle.getMaxPains().get(optionAnalysisModle.getMaxPains().size() - 1);
		Integer totalCE = optionAnalysisModle.getTotalCE();
		Integer totalPE = optionAnalysisModle.getTotalPE();

		String date = DateUtils.dateToString(new Date(), APPConstant.DATEFORMATE_BN_EXPIRY);
		BankNiftyDailyMaxPain bankNiftyDailyMaxPain = new BankNiftyDailyMaxPain(date, expiry, getExpiryType(expiry), maxPain, totalCE.toString(), totalPE.toString());
		bankNiftyDailyMaxPain.setSpot(spotPrice);
		Double pcr = ((double) totalPE / (double) totalCE);
		bankNiftyDailyMaxPain.setPcr(NumFormater.formatNumber1(pcr));
		return bankNiftyDailyMaxPain;
	}

	private static String getExpiryType(String expiry) throws Exception {
		int a = DateUtils.getExpiryWeekOfMonth(expiry);
		if (a < 4) {
			return "W";
		} else {
			return "M";
		}
	}
}
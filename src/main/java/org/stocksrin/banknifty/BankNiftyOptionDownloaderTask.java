package org.stocksrin.banknifty;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.option.currency.models.Column;
import org.option.currency.models.Columns;
import org.stocksrin.email.SendEmail;
import org.stocksrin.option.model.OptionModle;
import org.stocksrin.option.model.OptionModles;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonHTMLDocParsher;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.FileUtils;
import org.stocksrin.utils.HTMLPageDocumentDownloader;

public class BankNiftyOptionDownloaderTask extends TimerTask {

	@Override
	public void run() {
		if (!DateUtils.isWeekEndDay()) {
			try {
				List<String> fileNames = getdataFaced();
				SendEmail.sentMail( "SUCCESS! BankNifty Weekly Option Chain downloaded,  ",
						"File downloaded" + fileNames);
			} catch (Exception e) {
				SendEmail.sentMail( "BankNiftyOptionDownloaderTask Exception !",
						"ERROR " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			System.out.println(getdataFaced());
			System.out.println("done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<String> getdataFaced() throws Exception {

		Columns columns = getBankNiftyOptionChain(APPConstant.BANKNIFTY_WEEKLY_OPTION_URL);
		OptionModles optionModles = bankNiftyOptionDataWrapper(columns);

		List<String> expiryList = optionModles.getExpiryList();
		return otherExpiry(expiryList);
	}

	private static List<String> otherExpiry(List<String> expiryList) {
		int size = expiryList.size();
		List<String> files = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			FileUtils.makeDir(APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY + expiryList.get(i));
			String url = APPConstant.BANKNIFTY_WEEKLY_OPTION_URL_BY_Expiry + expiryList.get(i);
			Columns columns = getBankNiftyOptionChain(url);
			OptionModles optionModles = bankNiftyOptionDataWrapper(columns);
			String date = getDate(optionModles.getUnderlyingSpotPrice());
			String optionFileDir = APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY + expiryList.get(i) + File.separator+ date + ".json";

			FileUtils.writeDataAsJson(optionModles, optionFileDir);
			files.add(optionFileDir);

		}
		return files;
	}

	private static String getDate(String dateInString) {
		String dateString = null;
		try {
			String dateForamte = "MMM dd, yyyy hh:mm:ss Z";
			String a[] = dateInString.split("As on");
			String d = a[1].trim();
			Date date = DateUtils.stringToDate(d, dateForamte);
			dateString = DateUtils.dateToString(date, APPConstant.DATEFORMATE_dd_MM_yyyy);
			return dateString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date().toString();
	}

	private static OptionModles bankNiftyOptionDataWrapper(Columns columns) {
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

	private static Columns getBankNiftyOptionChain(String url) {

		Document doc = HTMLPageDocumentDownloader.getDocument(url);
		try {
			Elements c = CommonHTMLDocParsher.getOptionChainTable(doc, "octable", 0);
			Columns columns = CommonHTMLDocParsher.parseNSEColumn(doc, c);

			String spotPrice = columns.getUnderlyingSpotPrice().substring(24, 31);
			columns.setSpotPrice(spotPrice);

			List<String> expiryList = CommonHTMLDocParsher.getSelectBoxById(doc, "date", 0);
			List<String> firstThreeExpiry = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				firstThreeExpiry.add(expiryList.get(i));
			}
			columns.setExpiry(firstThreeExpiry.get(0));
			columns.setExpiryList(firstThreeExpiry);

			return columns;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}

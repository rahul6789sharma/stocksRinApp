package org.stocksrin.banknifty;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
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
import org.stocksrin.utils.FileUtils;
import org.stocksrin.utils.HTMLPageDocumentDownloader;

public class BankNiftyOptionDownloaderTask extends TimerTask {

	public static boolean isWeekEndDay() {
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("IST"));

		if (now.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || now.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void run() {
		if (!isWeekEndDay()) {
			try {
				String fileName = getdataFaced();
				SendEmail.sentMail("stocksrin@gmail.com",
						"SUCCESS! BankNifty Weekly Option Chain downloaded, " + fileName + "",
						"File downloaded" + fileName);
			} catch (Exception e) {
				SendEmail.sentMail("stocksrin@gmail.com", "BankNiftyOptionDownloaderTask Exception !",
						"ERROR " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			getdataFaced();
			System.out.println("done");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getdataFaced() throws Exception {
		Columns columns = getBankNiftyOptionChain(APPConstant.BANKNIFTY_WEEKLY_OPTION_URL);
		OptionModles optionModles = bankNiftyOptionDataWrapper(columns);
		String file = APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY + APPConstant.FILE_NAME_BANKNIFTY_OPTION;

		String date = getDate(optionModles.getLastDataUpdated());

		String fileName = file + date + ".json";
		FileUtils.writeDataAsJson(optionModles, fileName);
		return fileName;
	}

	private static String getDate(String date) {
		String[] a = date.split(" ");
		return a[0];
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

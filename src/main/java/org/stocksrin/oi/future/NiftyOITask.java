package org.stocksrin.oi.future;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.smarttrade.options.utils.DocumentParser;
import org.smarttrade.options.utils.HTMLPageUtils;
import org.stocksrin.email.SendEmail;
import org.stocksrin.nifty.indices.NSEIndice;
import org.stocksrin.nifty.indices.NiftyIndicesDataColloctor;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.NumFormater;
import org.stocksrin.utils.StocksRinException;

public class NiftyOITask extends TimerTask {

	@Override
	public void run() {
		if (!DateUtils.isWeekEndDay()) {
			try {
				String fromDate = DateUtils.dateToString(new Date(), "dd-MMM-yyyy");

				Document doc = HTMLPageUtils.getHTMLDocument(APPConstant.NIFTY_URL);
				List<String> expiry = DocumentParser.getInstance().getNiftyExpiryList(doc);
				NiftyOIDataModle niftyOIDataModle = getTwoMOnthOIData(fromDate, fromDate, expiry.get(0), expiry.get(1));
				System.out.println(niftyOIDataModle.toCsv());
				CommonUtils.appendData(niftyOIDataModle.toCsv(), APPConstant.FILE_NAME_NIFTY_OI_FILE);
				NiftyOIDataModleMap.addData(niftyOIDataModle.getDate(), niftyOIDataModle);
				SendEmail.sentMail(
						"OI Changes [" + niftyOIDataModle.getOi1PercentageChange() + ", " + niftyOIDataModle.getOi2PercentageChange() + "," + niftyOIDataModle.getOiTotalPercentageChange() + "]",
						niftyOIDataModle.toMail());
			} catch (Exception e) {
				SendEmail.sentMail("ERROR! Nifty OI Future Data ", e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private static NiftyOIDataModle getTwoMOnthOIData(String fromDate, String toDate, String currentMonthExpiry, String nextMonthExpiry) throws Exception {
		NiftyOIDataModle niftyOIDataModle = new NiftyOIDataModle();

		TempOI tempOI1 = NiftyOITask.getNiftyOIData("https://www.nseindia.com/live_market/dynaContent/live_watch/get_quote/getFOHistoricalData.jsp?underlying=NIFTY&instrument=FUTIDX&expiry="
				+ currentMonthExpiry + "&type=-&strike=-&fromDate=" + fromDate + "&toDate=" + toDate + "&datePeriod");
		niftyOIDataModle.setDate(tempOI1.getDate());

		niftyOIDataModle.setVol1(tempOI1.getVol1());
		niftyOIDataModle.setOi1(tempOI1.getOi1());

		TempOI tempOI2 = NiftyOITask.getNiftyOIData("https://www.nseindia.com/live_market/dynaContent/live_watch/get_quote/getFOHistoricalData.jsp?underlying=NIFTY&instrument=FUTIDX&expiry="
				+ nextMonthExpiry + "&type=-&strike=-&fromDate=" + fromDate + "&toDate=" + toDate + "&datePeriod");

		niftyOIDataModle.setVol2(tempOI2.getVol1());
		niftyOIDataModle.setOi2(tempOI2.getOi1());

		NSEIndice nifty = NiftyIndicesDataColloctor.getData("NIFTY 50");
		niftyOIDataModle.setNifty(Double.parseDouble(nifty.getLastPrice()));
		niftyOIDataModle.setNiftyChange(Double.parseDouble(nifty.getChange()));
		updateNiftyOIDataModle(niftyOIDataModle);
		return niftyOIDataModle;
	}

	private static void updateNiftyOIDataModle(NiftyOIDataModle niftyOIDataModle) {

		niftyOIDataModle.setVolTotal(niftyOIDataModle.getVol1() + niftyOIDataModle.getVol2());
		niftyOIDataModle.setOiTotal(niftyOIDataModle.getOi1() + niftyOIDataModle.getOi2());

		NiftyOIDataModle previousNiftyOIDataModle = pullLastDayOIdata(APPConstant.FILE_NAME_NIFTY_OI_FILE);
		Integer previousDayVolTotal = previousNiftyOIDataModle.getVolTotal();
		Integer previousDayOITotal = previousNiftyOIDataModle.getOiTotal();

		Integer previousDayVol1 = previousNiftyOIDataModle.getVol1();
		Integer previousDayOi1 = previousNiftyOIDataModle.getOi1();

		Integer previousDayVol2 = previousNiftyOIDataModle.getVol2();
		Integer previousDayOi2 = previousNiftyOIDataModle.getOi2();

		Integer todayVol1 = niftyOIDataModle.getVol1();
		Integer todayOi1 = niftyOIDataModle.getOi1();

		Integer todayVol2 = niftyOIDataModle.getVol2();
		Integer todayOi2 = niftyOIDataModle.getOi2();

		double vol1Change = (todayVol1 - previousDayVol1);
		double vol1PercentageChange = (vol1Change / previousDayVol1) * 100;
		niftyOIDataModle.setVol1PercentageChange(Double.parseDouble(NumFormater.formatNumber(vol1PercentageChange)));

		double vol2Change = (todayVol2 - previousDayVol2);
		double vol2PercentageChange = (vol2Change / previousDayVol2) * 100;
		niftyOIDataModle.setVol2PercentageChange(Double.parseDouble(NumFormater.formatNumber(vol2PercentageChange)));

		double oi1Change = (todayOi1 - previousDayOi1);
		double oi1PercentageChange = (oi1Change / previousDayOi1) * 100;
		niftyOIDataModle.setOi1PercentageChange(Double.parseDouble(NumFormater.formatNumber(oi1PercentageChange)));

		double oi2Change = (todayOi2 - previousDayOi2);
		double oi2PercentageChange = (oi2Change / previousDayOi2) * 100;
		niftyOIDataModle.setOi2PercentageChange(Double.parseDouble(NumFormater.formatNumber(oi2PercentageChange)));

		Integer toDayVolTotal = niftyOIDataModle.getVolTotal();
		Integer toDayOiTotal = niftyOIDataModle.getOiTotal();

		double a = (toDayVolTotal - previousDayVolTotal);
		double volTotalPercentage = (a / previousDayVolTotal) * 100;

		double b = (toDayOiTotal - previousDayOITotal);

		double oiTotalPercentage = (b / previousDayOITotal) * 100;

		niftyOIDataModle.setVolTotalPercentageChange(Double.parseDouble(NumFormater.formatNumber(volTotalPercentage)));

		niftyOIDataModle.setOiTotalPercentageChange(Double.parseDouble(NumFormater.formatNumber(oiTotalPercentage)));

	}

	public static void main(String[] args) {
		NiftyOITask niftyOITask = new NiftyOITask();
		niftyOITask.run();

		// NiftyOIDataModle niftyOIDataModle =
		// pullLastDayOIdata(APPConstant.FILE_NAME_NIFTY_OI_FILE);
		// System.out.println(niftyOIDataModle);

	}

	private static NiftyOIDataModle pullLastDayOIdata(String csvFile) {
		int lastLine = CommonUtils.totalNumberOfLine(csvFile) - 1;
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile));) {
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (i == lastLine) {
					// use comma as separator
					return CommonUtils.getOIModelFromCSV(line);
				}
				i++;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static TempOI getNiftyOIData(String url) throws StocksRinException, InterruptedException {
		System.out.println("URL " + url);
		TempOI tempOI = new TempOI();
		Document doc = HTMLPageUtils.getHTMLDocument(url);

		for (int i = 0; i < 5; i++) {
			if (doc.toString().contains("No Data")) {
				System.out.println("will retry after 30 min" + "");
				Thread.sleep(1800000);
				doc = HTMLPageUtils.getHTMLDocument(url);
			} else {
				break;
			}
		}

		try {
			Elements table = HTMLPageUtils.getTable(doc, 0);

			Elements colsforverify = table.get(0).getAllElements();

			Elements todayDatecols = table.get(1).getAllElements();
			// Elements previousDatecols = table.get(2).getAllElements();

			tempOI = getTempOI(todayDatecols);

		} catch (Exception e) {

			throw new StocksRinException("ERROR! nifty OI Data Exception " + e.getMessage());
		}

		return tempOI;
	}

	private static TempOI getTempOI(Elements cols) {
		TempOI tempOI = new TempOI();
		String result = null;
		for (int j = 1; j < cols.size(); j++) {
			Element col = cols.get(j);

			result = col.text();

			if (j == 1) {

				tempOI.setDate(result);
			}
			if (j == 7) {

				tempOI.setVol1(Integer.parseInt(result));
			}
			if (j == 9) {

				tempOI.setOi1(Integer.parseInt(result));
			}
		}
		return tempOI;
	}
}

class TempOI {
	private String date;
	private int vol1;
	private int oi1;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getVol1() {
		return vol1;
	}

	public void setVol1(int vol1) {
		this.vol1 = vol1;
	}

	public int getOi1() {
		return oi1;
	}

	public void setOi1(int oi1) {
		this.oi1 = oi1;
	}

}

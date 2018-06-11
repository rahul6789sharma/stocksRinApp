package org.stocksrin.fiidii;

import java.util.Date;
import java.util.Map;
import java.util.TimerTask;

import org.stocksrin.init.AppGlobalInit;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.FileUtils;
import org.stocksrin.utils.LoggerSysOut;
import org.stocksrin.utils.NumFormater;

public class CollectMonthlyDataTask extends TimerTask {

	private static final String YEAR = "2018";

	@Override
	public void run() {

	}

	public static void main(String[] args) throws Exception {

		AppGlobalInit appGlobalInit = new AppGlobalInit();
		appGlobalInit.init();
		// String file = copyDataToMonthly();
		String file2 = "C:\\Users\\rahulksh\\stocksRin_CONF\\stocksRinData\\FII_DII_BUY_SELL\\monthly\\May_2018.csv";
		copyDataToYearly("Mar");
		// backup();
	}

	
	
	public static void backup() throws Exception {
		String fiiDiiFile = APPConstant.FILE_NAME_FII_DIR_MONTHLY + DateUtils.getCurrentMonth() + "_" + DateUtils.getCurrentYear() + ".csv";
		LoggerSysOut.print(fiiDiiFile);
		String dir = APPConstant.FILE_NAME_FII_DIR_BACKUP;
		String targetFile = dir + DateUtils.dateToString(new Date(), APPConstant.DATEFORMATE_dd_MM_yyyy) + ".csv";
		FileUtils.backup(APPConstant.FILE_NAME_FII_DIR_MONTHLY + DateUtils.getCurrentMonth() + "_" + DateUtils.getCurrentYear() + ".csv", targetFile);
	}


	public static void copyDataToYearly(String month) throws Exception {
		Map<String, FIIDIIDataModle> previousMOnthData = FIIDIIdataModelMap.getfIIDIIPreviousMOnthData();

		String previousthirdMonth = month;

		Double total_fii_BuyValue = 0.0;
		Double total_fii_SellValue = 0.0;
		Double total_dii_BuyValue = 0.0;
		Double total_dii_SellValue = 0.0;
		String date = null;
		Double niftyAtMonthStart = 0.0;
		Double niftyAtMonthEnd = 0.0;

		int i = 0;
		for (Map.Entry<String, FIIDIIDataModle> entry : previousMOnthData.entrySet()) {

			String key = entry.getKey();

			if (key.contains(previousthirdMonth)) {
				FIIDIIDataModle fIIDIIDataModle = entry.getValue();
				total_fii_BuyValue = total_fii_BuyValue + Double.parseDouble(fIIDIIDataModle.getFii_BuyValue());
				total_fii_SellValue = total_fii_SellValue + Double.parseDouble(fIIDIIDataModle.getFii_SellValue());
				total_dii_BuyValue = total_dii_BuyValue + Double.parseDouble(fIIDIIDataModle.getDii_BuyValue());
				total_dii_SellValue = total_dii_SellValue + Double.parseDouble(fIIDIIDataModle.getDii_SellValue());
				date = key;
				if (i == 0) {
					niftyAtMonthStart = Double.parseDouble(fIIDIIDataModle.getNiftyprice());
				}
				niftyAtMonthEnd = Double.parseDouble(fIIDIIDataModle.getNiftyprice());
				i++;
			}

		}

		LoggerSysOut.print("niftyAtMonthStart " + niftyAtMonthStart);
		LoggerSysOut.print("niftyAtMonthEnd " + niftyAtMonthEnd);
		String fileName = APPConstant.FILE_NAME_FII_DIR_YEARLY + YEAR + ".csv";
		LoggerSysOut.print(fileName);

		Double fiiNet = total_fii_SellValue - total_fii_BuyValue;
		Double diiNet = total_dii_SellValue - total_dii_BuyValue;
		Double niftyChange = niftyAtMonthEnd - niftyAtMonthStart;

		String value = getMonth(date) + "" + NumFormater.formatNumber(total_fii_BuyValue) + "" + NumFormater.formatNumber(total_fii_SellValue) + "" + NumFormater.formatNumber(fiiNet) + ""
				+ NumFormater.formatNumber(total_dii_BuyValue) + "" + NumFormater.formatNumber(total_dii_SellValue) + "" + NumFormater.formatNumber(diiNet) + ""
				+ NumFormater.formatNumber(niftyAtMonthStart) + "" + NumFormater.formatNumber(niftyAtMonthEnd) + "" + NumFormater.formatNumber(niftyChange);
		LoggerSysOut.print(value);
		CommonUtils.appendData(value, fileName);
	}

	public static String getMonth(String date) throws Exception {
		String[] a = date.split("-");
		return a[1];
	}

	public static String getYear(String date) throws Exception {
		String[] a = date.split("-");
		return a[2];
	}
}
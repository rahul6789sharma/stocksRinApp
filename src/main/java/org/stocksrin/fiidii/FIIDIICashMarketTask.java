package org.stocksrin.fiidii;

import java.util.Date;
import java.util.TimerTask;

import org.stocksrin.email.SendEmail;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.FileUtils;
import org.stocksrin.utils.LoggerSysOut;
import org.stocksrin.utils.StocksRinException;

public class FIIDIICashMarketTask extends TimerTask {

	@Override
	public void run() {

		if (!DateUtils.isWeekEndDay()) {

			LoggerSysOut.print(" FIIDIITask FII Data Downloading ... " + new Date());
			FIIDIIDataModle data;
			try {
				data = FIIDIIDailyReportUtils.getData();
				String file = getFile();
				CommonUtils.appendData(data.toCsv(), file);
				FIIDIIDataModle fiiDIIDataModle = CommonUtils.getFIIModelFromCSV(data.toCsv());
				FIIDIIdataModelMap.addData(fiiDIIDataModle.getDate(), fiiDIIDataModle);
				SendEmail.sentMail("FII/DII Data[ FII_NET" + fiiDIIDataModle.getFii_net() + " DII_NET " + fiiDIIDataModle.getDii_net() + " ]", fiiDIIDataModle.toString() + "\n" + "File name " + file);
			} catch (StocksRinException e) {
				SendEmail.sentMail("FIIDIITask Exception !", "ERROR " + e.getMessage());
				e.printStackTrace();
			}

		} else {
			LoggerSysOut.print("Not Downloading FII data its weekEnd ... " + new Date());
		}

	}

	public static String getFile() {
		String currentMonthFile = APPConstant.FILE_NAME_FII_DIR_MONTHLY + DateUtils.getCurrentMonth() + "_" + DateUtils.getCurrentYear() + ".csv";

		boolean status = FileUtils.makeFile(currentMonthFile);
		if (status) {
			String firstINdex = "DateFII_buyValueFII_sellValueFII_netDII_buyValueDII_sellValueDII_netniftynifty_changestocks_Advancestocks_Decline";
			CommonUtils.appendData2(firstINdex + "\n", currentMonthFile);
		}
		return currentMonthFile;
	}

}
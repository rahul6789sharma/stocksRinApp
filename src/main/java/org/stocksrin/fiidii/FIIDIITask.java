package org.stocksrin.fiidii;

import java.util.Date;
import java.util.TimerTask;

import org.stocksrin.email.SendEmail;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.FileUtils;
import org.stocksrin.utils.StocksRinException;

public class FIIDIITask extends TimerTask {

	@Override
	public void run() {

		if (!DateUtils.isWeekEndDay()) {

			System.out.println(" FIIDIITask FII Data Downloading ... " + new Date());
			FIIDIIDataModle data;
			try {
				data = FIIDIIDailyReportUtils.getData();
				String file = getFile();
				CommonUtils.appendData(data.toCsv(), file);
				FIIDIIDataModle fiiDIIDataModle = CommonUtils.getFIIModelFromCSV(data.toCsv());
				FIIDIIdataModelMap.addData(fiiDIIDataModle.getDate(), fiiDIIDataModle);
				SendEmail.sentMail("FII/DII Data[ FII_NET" + fiiDIIDataModle.getFii_net() + ", DII_NET " + fiiDIIDataModle.getDii_net() + " ]", fiiDIIDataModle.toString()+"\n"+
				"File name "+file);
			} catch (StocksRinException e) {
				SendEmail.sentMail("FIIDIITask Exception !", "ERROR " + e.getMessage());
				e.printStackTrace();
			}

		} else {
			System.out.println("Not Downloading FII data its weekEnd ... " + new Date());
		}

	}

	public static String getFile() {
		String currentMonthFile = APPConstant.FILE_NAME_FII_DIR_MONTHLY + DateUtils.getCurrentMonth() + "_" + DateUtils.getCurrentYear() + ".csv";

		boolean status = FileUtils.makeFile(currentMonthFile);
		if (status) {
			String firstINdex = "Date,FII_buyValue,FII_sellValue,FII_net,DII_buyValue,DII_sellValue,DII_net,nifty,nifty_change,stocks_Advance,stocks_Decline";
			CommonUtils.appendData2(firstINdex + "\n", currentMonthFile);
		}
		return currentMonthFile;
	}

	public static void main(String[] args) {
		System.out.println(getFile());
	}
}

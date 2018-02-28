package org.stocksrin.fiidii;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.TimerTask;

import org.stocksrin.Exception.StocksRinException;
import org.stocksrin.email.SendEmail;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;

public class FIIDIITask extends TimerTask {

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

			System.out.println(" FIIDIITask FII Data Downloading ... " + new Date());
			FIIDIIDataModle data;
			try {
				data = FIIDIIDailyReportUtils.getData();
				CommonUtils.appendData(data.toCsv(), APPConstant.FILE_NAME_FII_DII);
				FIIDIIDataModle fiiDIIDataModle = CommonUtils.getModelFromCSV(data.toCsv());
				FIIDIIdataModelMap.addData(fiiDIIDataModle.getDate(), fiiDIIDataModle);
				SendEmail.sentMail("stocksrin@gmail.com", "FII/DII Data", fiiDIIDataModle.getDate());
			} catch (StocksRinException e) {
				SendEmail.sentMail("stocksrin@gmail.com", "FIIDIITask Exception !", "ERROR " + e.getMessage());
				e.printStackTrace();
			}

		} else {
			System.out.println("Not Downloading FII data its weekEnd ... " + new Date());
		}

	}

}

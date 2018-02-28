package org.stocksrin.excel;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.TimerTask;

import org.stocksrin.email.SendEmail;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.ExcelUtils;

public class DownloadDailyBhavCopyTask extends TimerTask {

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
			System.out.println("Downloading ... " + new Date());
			
			System.out.println("conf dir-------------->" + APPConstant.STOCKSRIN_NSE_CONF_DIR_BHAVDIR);
			String fileName = null;
			try {
				fileName = APPConstant.STOCKSRIN_NSE_CONF_DIR_BHAVDIR+ "sec_bhavdata_full_"
						+ DateUtils.dateToString(new Date(), APPConstant.DATEFORMATE_dd_MM_yyyy) + ".csv";
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("Start Downloading " + fileName);
			try {
				NSEBhavCopyDownloadUtil.downloadBhavCopy(fileName);
				SendEmail.sentMail("stocksrin@gmail.com", "SUCCESS! Bhav Copy Downloaded",
						"File downloaded" + fileName);
			} catch (Exception e) {
				SendEmail.sentMail("stocksrin@gmail.com", "ERROR! Bhav Copy not Downloaded",
						"ERROR " + e.getMessage());
				e.printStackTrace();
			}
			System.out.println("complete Downloading " + fileName);
			try {
				ExcelUtils.verifyFileAndInternalDate(new File(fileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Not Downloading its weekEnd ... " + new Date());
		}

	}

}

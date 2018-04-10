package org.stocksrin.excel;

import java.io.File;
import java.util.Date;
import java.util.TimerTask;

import org.stocksrin.email.SendEmail;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.ExcelUtils;

public class DownloadDailyBhavCopyTask extends TimerTask {

	@Override
	public void run() {
		if (!DateUtils.isWeekEndDay()) {
			System.out.println("Downloading ... " + new Date());

			System.out.println("conf dir-------------->" + APPConstant.STOCKSRIN_NSE_CONF_DIR_BHAVDIR);
			String fileName = null;
			try {
				fileName = APPConstant.STOCKSRIN_NSE_CONF_DIR_BHAVDIR + "sec_bhavdata_full_" + DateUtils.dateToString(new Date(), APPConstant.DATEFORMATE_dd_MM_yyyy) + ".csv";
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("Start Downloading " + fileName);
			try {
				NSEBhavCopyDownloadUtil.downloadBhavCopy(fileName);
			} catch (Exception e) {
				SendEmail.sentMail("ERROR! Bhav Copy not Downloaded", "ERROR " + e.getMessage());
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

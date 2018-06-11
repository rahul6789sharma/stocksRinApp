package org.stocksrin.excel;

import java.io.File;
import java.util.Date;
import java.util.TimerTask;

import org.stocksrin.email.SendEmail;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.ExcelUtils;
import org.stocksrin.utils.LoggerSysOut;

public class DownloadDailyBhavCopyTask extends TimerTask {

	@Override
	public void run() {
		if (!DateUtils.isWeekEndDay()) {
			LoggerSysOut.print("Downloading ... " + new Date());

			LoggerSysOut.print("conf dir-------------->" + APPConstant.STOCKSRIN_NSE_CONF_DIR_BHAVDIR);
			String fileName = null;
			try {
				fileName = APPConstant.STOCKSRIN_NSE_CONF_DIR_BHAVDIR + "sec_bhavdata_full_" + DateUtils.dateToString(new Date(), APPConstant.DATEFORMATE_dd_MM_yyyy) + ".csv";
			} catch (Exception e) {
				e.printStackTrace();
			}

			LoggerSysOut.print("Start Downloading " + fileName);
			try {
				NSEBhavCopyDownloadUtil.downloadBhavCopy(fileName);
			} catch (Exception e) {
				SendEmail.sentMail("ERROR! Bhav Copy not Downloaded", "ERROR " + e.getMessage());
				e.printStackTrace();
			}
			LoggerSysOut.print("complete Downloading " + fileName);
			try {
				ExcelUtils.verifyFileAndInternalDate(new File(fileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			LoggerSysOut.print("Not Downloading its weekEnd ... " + new Date());
		}

	}

}

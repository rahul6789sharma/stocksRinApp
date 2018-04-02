package org.stocksrin.fiidii;

import java.util.Date;
import java.util.TimerTask;

import org.stocksrin.Exception.StocksRinException;
import org.stocksrin.email.SendEmail;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;

public class FIIDIITask extends TimerTask {


	@Override
	public void run() {

		if (!DateUtils.isWeekEndDay()) {

			System.out.println(" FIIDIITask FII Data Downloading ... " + new Date());
			FIIDIIDataModle data;
			try {
				data = FIIDIIDailyReportUtils.getData();
				CommonUtils.appendData(data.toCsv(), APPConstant.FILE_NAME_FII_DII);
				FIIDIIDataModle fiiDIIDataModle = CommonUtils.getFIIModelFromCSV(data.toCsv());
				FIIDIIdataModelMap.addData(fiiDIIDataModle.getDate(), fiiDIIDataModle);
				SendEmail.sentMail("FII/DII Data Date " + fiiDIIDataModle.getDate(), fiiDIIDataModle.toString());
			} catch (StocksRinException e) {
				SendEmail.sentMail("FIIDIITask Exception !", "ERROR " + e.getMessage());
				e.printStackTrace();
			}

		} else {
			System.out.println("Not Downloading FII data its weekEnd ... " + new Date());
		}

	}

}

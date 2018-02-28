package org.stocksrin.fiidii.derivatives;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.TimerTask;

import org.stocksrin.fiidii.FIIDIIDailyReportUtils;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;

public class FIIDIIDerivaticsTask extends TimerTask {

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
			FIIDIIDataDerivativesModle fiiDIIDataDerivativesModle = IndexFuture
					.getFIIDIIDerivaticeData(APPConstant.FIIDII_DERIVATIVES_DATA_URL);
			CommonUtils.appendData(fiiDIIDataDerivativesModle.toCsv(), APPConstant.FILE_NAME_DERIVATIVES);
		}
	}

}

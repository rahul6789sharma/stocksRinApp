package org.stocksrin.live;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.TimerTask;

import org.stocksrin.utils.LoggerSysOut;

public class LiveDataCollectortask extends TimerTask {
	
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

			if (istradingHrStatus()) {

				try {

					Rows row = LiveMarketAdvancedDecline.getData();

					AdvancedDeclined.addAdvanced(row.getAdvances());
					AdvancedDeclined.addDeclined(row.getDeclines());

					LoggerSysOut.print("Advanced " + AdvancedDeclined.getAdvanced());
					LoggerSysOut.print("Declined " + AdvancedDeclined.getDeclined());
					LoggerSysOut.print("********");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			else if (isClearTime()) {
				LoggerSysOut.print("clearing all Advanced and declined data");
				AdvancedDeclined.creaAall();
			}
		}
	}

	public static boolean isClearTime() {

		Calendar morningTime = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		morningTime.set(Calendar.HOUR_OF_DAY, 8);
		morningTime.set(Calendar.MINUTE, 0);
		morningTime.set(Calendar.SECOND, 0);

		Calendar eveningTime = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		eveningTime.set(Calendar.HOUR_OF_DAY, 8);
		eveningTime.set(Calendar.MINUTE, 30);
		eveningTime.set(Calendar.SECOND, 0);

		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("IST"));

		/*
		 * now.set(Calendar.HOUR_OF_DAY 8); now.set(Calendar.MINUTE 30);
		 * now.set(Calendar.SECOND 0);
		 */

		if (now.getTime().after(morningTime.getTime()) && now.getTime().before(eveningTime.getTime())) {
			LoggerSysOut.print("isClearTime IN Between");
			return true;
		} else {

			return false;
		}

	}

	public static boolean istradingHrStatus() {

		Calendar morningTime = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		morningTime.set(Calendar.HOUR_OF_DAY, 9);
		morningTime.set(Calendar.MINUTE, 15);
		morningTime.set(Calendar.SECOND, 0);

		Calendar eveningTime = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		eveningTime.set(Calendar.HOUR_OF_DAY, 15);
		eveningTime.set(Calendar.MINUTE, 30);
		eveningTime.set(Calendar.SECOND, 0);

		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("IST"));

		/*
		 * now.set(Calendar.HOUR_OF_DAY 8); now.set(Calendar.MINUTE 30);
		 * now.set(Calendar.SECOND 0);
		 */

		if (now.getTime().after(morningTime.getTime()) && now.getTime().before(eveningTime.getTime())) {
			return true;

		} else {
			return false;

		}

	}

}

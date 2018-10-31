package org.stocksrin.option.notification;

import java.util.TimerTask;

import org.stocksrin.email.SendEmail;
import org.stocksrin.option.nifty.NiftyData;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.LoggerSysOut;

public class NiftyNotificationTask extends TimerTask {

	// 1 min
	private long timeInteval = 60000;

	private static double recordedPrice = 0;
	private static double alertPriceDiff = 25;


	@Override
	public void run() {
		LoggerSysOut.print("BankNiftyNotificationTask Started");
		try {
			if (!DateUtils.isWeekEndDay()) {

				while (CommonUtils.getEveningTime()) {
					if (!NiftyData.shortedExpiry.isEmpty()) {
						String currentExpiry = NiftyData.shortedExpiry.first();
						checkSpotAlert(NiftyData.optionData.get(currentExpiry).getSpot());
					}
					Thread.sleep(timeInteval);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void checkSpotAlert(Double spot) {

		try {

			//LoggerSysOut.print("recordedPrice " + recordedPrice);
			//LoggerSysOut.print("spot " + spot);

			Double d = spot - recordedPrice;

			LoggerSysOut.print("diff " + d);
			if (recordedPrice == 0.0) {
				LoggerSysOut.print("NiftyOpenning Price Alert");
				SendEmail.sentMail("Openning status [" + spot + "]", "Open [" + spot + "]");
				recordedPrice = spot;
				return;
			}

			if (Math.abs(d) > alertPriceDiff) {
				LoggerSysOut.print("Nifty price Alart Triggered");
				SendEmail.sentMail("Nifty Price Alert : " + spot, "change [" + d.toString() + "]");
				recordedPrice = spot;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
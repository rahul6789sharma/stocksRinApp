package org.stocksrin.option.banknifty.notification;

import java.util.SortedSet;
import java.util.TimerTask;

import org.option.currency.models.MaxPains;
import org.smarttrade.options.utils.Calculation;
import org.stocksrin.email.SendEmail;
import org.stocksrin.option.banknifty.BankNiftyData2;
import org.stocksrin.option.common.model.OptionModles;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.LoggerSysOut;

public class BankNiftyNotificationTask extends TimerTask {

	// 1 min
	private long timeInteval = 60000;

	// private long timeInteval = 10000;

	private static double recordedPrice = 0;
	private static double alertPriceDiff = 50;

	private static int oiLimit = 1000000;

	@Override
	public void run() {
		LoggerSysOut.print("BankNiftyNotificationTask Started");
		try {
			if (!DateUtils.isWeekEndDay()) {

				while (CommonUtils.getEveningTime()) {
					if (!BankNiftyData2.shortedExpiry.isEmpty()) {
						String currentExpiry = BankNiftyData2.shortedExpiry.first();

						checkSpotAlert(BankNiftyData2.bnOptionData.get(currentExpiry).getSpot());

						checkForMaxPainChange();
						//BankNiftyOINotification.checkOIChange();
					}

					Thread.sleep(timeInteval);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void checkForMaxPainChange() {
		try {
			SortedSet<String> expiryes = BankNiftyData2.shortedExpiry;
			for (String expiry : expiryes) {
				OptionModles optionModles = BankNiftyData2.bnOptionData.get(expiry);

				// only check when total oi is greater then 1 lacks
				if (optionModles.getTotal_ce_oi() > oiLimit || optionModles.getTotal_pe_oi() > oiLimit) {

					MaxPains maxPain = Calculation.calMaxPain(optionModles.getOptionModle(), APPConstant.BNF_STRIKE_DIFF, optionModles.getExpiry());
					
					//LoggerSysOut.print("************ Expiry ***************" + expiry + " - " +Thread.currentThread().getName());
					//LoggerSysOut.print("Bank Nifty MaxPain1  " + BankNiftyData2.maxPains);
					
					
					Double currentMaxPain = maxPain.getMaxPainStrick();
					//LoggerSysOut.print("currentMaxPain " + currentMaxPain);
					Double previousMaxPain = BankNiftyData2.maxPains.get(expiry);
					//LoggerSysOut.print("previousMaxPain " + previousMaxPain);
					//LoggerSysOut.print(previousMaxPain != null);
					if (previousMaxPain != null) {

						if (!previousMaxPain.equals(currentMaxPain)) {
							LoggerSysOut.print(" *************** ******************* ***************** *************** ");
							LoggerSysOut.print("Bank Nifty MaxPain Alert Triggered");
							SendEmail.sentMail("BN MaxPain Changed Expiry: " + expiry, previousMaxPain + "-->" + currentMaxPain);
							BankNiftyData2.maxPains.put(expiry, currentMaxPain);
						}
					} else {
						BankNiftyData2.maxPains.put(expiry, currentMaxPain);
					}
				}
			}
			//LoggerSysOut.print("maxPains " + BankNiftyData2.maxPains);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void checkSpotAlert(Double spot) {

		try {

			// LoggerSysOut.print("recordedPrice " + recordedPrice);
			// LoggerSysOut.print("spot " + spot);

			Double d = spot - recordedPrice;

			// LoggerSysOut.print("diff " + d);
			if (recordedPrice == 0.0) {
				LoggerSysOut.print("Bank NiftyOpenning price Alert");
				SendEmail.sentMail("BN Openning status [" + spot + "]", "Open [" + spot + "]");
				recordedPrice = spot;
				return;
			}

			if (Math.abs(d) > alertPriceDiff) {
				LoggerSysOut.print("Bank Nifty price Alart Triggered");
				SendEmail.sentMail("BN Price Alert : " + spot, "change [" + d.toString() + "]");
				recordedPrice = spot;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
package org.stocksrin.banknifty.notification;

import java.util.List;
import java.util.SortedSet;

import org.stocksrin.banknifty.dataStore.BankNiftyData2;
import org.stocksrin.email.SendEmail;
import org.stocksrin.option.model.OptionModle;
import org.stocksrin.option.model.OptionModles;
import org.stocksrin.utils.DateUtils;

public class BankNiftyOINotification {

	public static void main(String[] args) {

		System.out.println(getDate());
	}

	private static int oiLimit = 1000000;
	private static int positiveOIDiffLit = 500000;
	private static int negitiveOIDiffLit = 300000;

	private static int positiveOIDiffLit_for_expiryDay = 800000;
	private static int negitiveOIDiffLit_for_expiryDay = 500000;

	private static void setExpiryDayLimit() {
		SortedSet<String> expiryes = BankNiftyData2.shortedExpiry;
		String firstExpiry = expiryes.first();

		if (getDate().equals(firstExpiry)) {

			System.out.println("############ Expiry Day new Limts are set #########");
			positiveOIDiffLit = positiveOIDiffLit_for_expiryDay;
			negitiveOIDiffLit = negitiveOIDiffLit_for_expiryDay;
		}
	}

	public static void checkOIChange() {
		setExpiryDayLimit();
		OptionModles ob = null;
		try {
			SortedSet<String> expiryes = BankNiftyData2.shortedExpiry;

			for (String expiry : expiryes) {

				OptionModles optionModles = BankNiftyData2.bnOptionData.get(expiry);
				ob = optionModles;
				// only check when total oi is greater then 1 lacks
				if (optionModles.getTotal_ce_oi() > oiLimit || optionModles.getTotal_pe_oi() > oiLimit) {
					checkStrikeOI(expiry, optionModles.getOptionModle());
				}
			}
		} catch (Exception e) {
			System.err.println(ob);
			e.printStackTrace();
		}
	}

	private static String getDate() {
		return DateUtils.getCurrentDay() + DateUtils.getCurrentMonth().toUpperCase() + DateUtils.getCurrentYear();
	}

	private static void checkStrikeOI(String expiry, List<OptionModle> data) {
		// String expiry=BankNiftyData2.shortedExpiry.first();
		List<OptionModle> result = BankNiftyData2.bnOptionLiveNotificationChnageData.get(expiry);
		if (result != null) {

			for (OptionModle optionModle : result) {

				for (OptionModle da : data) {
					if (optionModle.getStrike_price().equals(da.getStrike_price())) {

						if (!optionModle.getC_change_oi().equals(da.getC_change_oi())) {
							callOINotification(expiry, optionModle, da);

						}

						if (!optionModle.getP_change_oi().equals(da.getP_change_oi())) {

							putOINotification(expiry, optionModle, da);

						}

					}
				}

			}

		} else {
			BankNiftyData2.bnOptionLiveNotificationChnageData.put(expiry, data);
		}

	}

	private static void putOINotification(String expiry, OptionModle oldOptionModle, OptionModle newOptionModle) {

		Integer diff = oldOptionModle.getP_change_oi() - newOptionModle.getP_change_oi();
		if (oldOptionModle.getP_change_oi() < newOptionModle.getP_change_oi()) {

			if (Math.abs(diff) > positiveOIDiffLit) {

				String subject = "PUT: Fresh Addition of '" + Math.abs(diff) + "' OI at [Expiry :" + expiry + " @Strike -> " + oldOptionModle.getStrike_price() + "]";
				String body = "Expiry : " + expiry + "\nStrike : " + oldOptionModle.getStrike_price() + "\nChange in OI [" + oldOptionModle.getP_change_oi() + " --> " + newOptionModle.getP_change_oi()
						+ "] \n" + "OI Diff " + (0 - diff);

				System.out.println("Subject: " + subject);
				System.out.println("Body: " + body);

				SendEmail.sentMail(subject, body);
				oldOptionModle.setP_change_oi(newOptionModle.getP_change_oi());
				System.out.println("*********");
			}

		} else {

			if (Math.abs(diff) > negitiveOIDiffLit) {

				String subject = "PUT: Unwinding of '" + (0 - diff) + "' OI at [Expiry :" + expiry + " @Strike -> " + oldOptionModle.getStrike_price() + "]";
				String body = "Expiry : " + expiry + "\nStrike : " + oldOptionModle.getStrike_price() + "\nChange in OI [" + oldOptionModle.getP_change_oi() + " --> " + newOptionModle.getP_change_oi()
						+ "] \n" + "OI Diff " + (0 - diff);

				System.out.println("Subject: " + subject);
				System.out.println("Body: " + body);
				SendEmail.sentMail(subject, body);
				oldOptionModle.setP_change_oi(newOptionModle.getP_change_oi());
				System.out.println("*********");
			}
		}
	}

	private static void callOINotification(String expiry, OptionModle oldOptionModle, OptionModle newOptionModle) {

		Integer diff = oldOptionModle.getC_change_oi() - newOptionModle.getC_change_oi();
		if (oldOptionModle.getC_change_oi() < newOptionModle.getC_change_oi()) {

			if (Math.abs(diff) > positiveOIDiffLit) {

				String subject = "CALL: Fresh Addition of '" + Math.abs(diff) + "' OI at [Expiry :" + expiry + " @Strike -> " + oldOptionModle.getStrike_price() + "]";
				String body = "Expiry : " + expiry + "\nStrike : " + oldOptionModle.getStrike_price() + "\nChange in OI [" + oldOptionModle.getC_change_oi() + " --> " + newOptionModle.getC_change_oi()
						+ "] \n" + "OI Diff " + (0 - diff);

				System.out.println("Subject: " + subject);
				System.out.println("Body: " + body);

				SendEmail.sentMail(subject, body);
				oldOptionModle.setC_change_oi(newOptionModle.getC_change_oi());
				System.out.println("*********");
			}

		} else {

			if (Math.abs(diff) > negitiveOIDiffLit) {

				String subject = "CALL: Unwinding of '" + (0 - diff) + "' OI at [Expiry :" + expiry + " @Strike -> " + oldOptionModle.getStrike_price() + "]";
				String body = "Expiry : " + expiry + "\nStrike : " + oldOptionModle.getStrike_price() + "\nChange in OI [" + oldOptionModle.getC_change_oi() + " --> " + newOptionModle.getC_change_oi()
						+ "] \n" + "OI Diff " + (0 - diff);

				System.out.println("Subject: " + subject);
				System.out.println("Body: " + body);
				SendEmail.sentMail(subject, body);
				oldOptionModle.setC_change_oi(newOptionModle.getC_change_oi());
				System.out.println("*********");
			}
		}
	}
}

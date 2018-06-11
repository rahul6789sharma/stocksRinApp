package org.stocksrin.banknifty;

import java.util.Date;
import java.util.Set;
import java.util.TimerTask;

import org.stocksrin.email.SendEmail;
import org.stocksrin.option.model.BankNiftyDailyMaxPain;
import org.stocksrin.option.model.OptionModles;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.LoggerSysOut;

public class BNExpiryMaxPainSaverTask extends TimerTask {

	@Override
	public void run() {
		if (!DateUtils.isWeekEndDay()) {
			while (BankNiftyData.getShortedSet().isEmpty()) {
				try {
					for (int i = 0; i < 10; i++) {
						if (!BankNiftyData.getShortedSet().isEmpty()) {
							break;
						}
						Thread.sleep(1000);
						LoggerSysOut.print("Wating for data.." + i);
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			String nearWeeklyExpiry = BankNiftyData.getShortedSet().first();

			// weekly option
			saveMaxPain(APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_DAILYMAXPAIN_FILE2, nearWeeklyExpiry, "W");

			Set<String> allExpiry = BankNiftyData.getShortedSet();
			for (String string : allExpiry) {
				try {
					if (DateUtils.getWeekOfMonth(string) == 4) {
						// Monthly option
						saveMaxPain(APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_MONTHLYMAXXPAIN_FILE, string, "M");
					}
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}
	}

	private static void saveMaxPain(String file, String expiry, String type) {

		OptionModles ob = BankNiftyData.getBankNiftyCurrentTimeData().get(expiry);
		//Double bankNiftySpotPrice;

		try {
			//bankNiftySpotPrice = BankNiftyUtils.getLSpotPriceUnderlyingSpotPrice(ob.getUnderlyingSpotPrice());
			LiveMaxPainModle optionAnalysisModle = BankNiftyData.getMaxPainSerieas().get(expiry);
			BankNiftyDailyMaxPain bankNiftyDailyMaxPain = BankNiftyUtils.convertData(optionAnalysisModle, expiry, ob.getSpot().toString());
			bankNiftyDailyMaxPain.setDay(DateUtils.getTodayString());
			bankNiftyDailyMaxPain.setIsExpiryDay(isExpiry(expiry).toString());

			if (type.equals("W")) {
				BankNiftyData.getWeeklyMaxPain().add(bankNiftyDailyMaxPain);
			}
			String csv = bankNiftyDailyMaxPain.toCSVForWeeklyEOD();
			LoggerSysOut.print(csv);
			CommonUtils.appendData(csv, file);
			SendEmail.sentMail("MP: " + bankNiftyDailyMaxPain.getMaxPain() + " Expiry " + "SUCCESS! BN Max Pain File downloaded", file + "\n data \n" + csv);
		} catch (Exception e) {
			e.printStackTrace();
			SendEmail.sentMail("ERROR! BN Max Pain MaxPain  ", "File downloaded" + file + "\n data \n");
		}
	}

	public static void main(String[] args) throws Exception {
		/*
		 * AppGlobalInit appGlobalInit = new AppGlobalInit();
		 * appGlobalInit.init(); BNWeeklyExpiryMaxPainTask
		 * BNWeeklyExpiryMaxPainTask = new BNWeeklyExpiryMaxPainTask();
		 * BNWeeklyExpiryMaxPainTask.run();
		 */

	}

	private static Boolean isExpiry(String expiry) throws Exception {

		String d = DateUtils.dateToString(new Date(), APPConstant.DATEFORMATE_BN_EXPIRY);
		return d.toUpperCase().equalsIgnoreCase(expiry);
	}
}
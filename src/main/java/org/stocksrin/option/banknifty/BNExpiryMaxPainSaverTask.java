package org.stocksrin.option.banknifty;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.TimerTask;

import org.stocksrin.email.SendEmail;
import org.stocksrin.option.common.model.DailyMaxPain;
import org.stocksrin.option.common.model.OptionModle;
import org.stocksrin.option.common.model.OptionModles;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.LoggerSysOut;
import org.stocksrin.utils.ExceptionUtils;

// save banknifty weekly Option expiry Maix pain and OI data
public class BNExpiryMaxPainSaverTask extends TimerTask {

	@Override
	public void run() {
		if (!DateUtils.isWeekEndDay()) {

			try {
				LoggerSysOut.print(" shortedExpiry test " + BankNiftyData2.shortedExpiry);
				String nearWeeklyExpiry = BankNiftyData2.shortedExpiry.first();
				// save only weekly option
				DailyMaxPain dailyMaxPain = saveMaxPain(APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_DAILYMAXPAIN_FILE, nearWeeklyExpiry);
				BankNiftyData2.dailyMaxPain.put(dailyMaxPain.getDate(), dailyMaxPain);
				SendEmail.sentMail("Success! BN Max Pain MaxPain Expiry " + nearWeeklyExpiry, dailyMaxPain + "\n data \n");
			} catch (Exception e) {
				e.printStackTrace();
				SendEmail.sentMail("ERROR! BN Max Pain MaxPain  ", "File downloaded" + "\n data \n " + ExceptionUtils.getStackTrace(e));
			}
		}
	}

	private static DailyMaxPain saveMaxPain(String file, String expiry) throws IOException {

		OptionModles ob = BankNiftyData2.bnOptionData.get(expiry);

		DailyMaxPain dailyMaxPain = new DailyMaxPain(ob.getDate(), ob.getExpiry(), ob.getMaxPainStrick(), ob.getTotal_ce_oi(), ob.getTotal_pe_oi());
		dailyMaxPain.setSpot(ob.getSpot());
		setHighestValues(ob, dailyMaxPain);
		String csv = dailyMaxPain.toCSVForWeeklyEOD();
		LoggerSysOut.print(csv);
		newExpiryCheck();
		CommonUtils.appendData(csv, file);
		return dailyMaxPain;
	}

	private static void setHighestValues(OptionModles optionModles, DailyMaxPain dailyMaxPain) {
		List<OptionModle> lst = optionModles.getOptionModle();

		Double highest_oi_ce_strike = 0.0;
		Integer highest_oi_ce_value = 0;

		Double highest_oi_pe_strike = 0.0;
		Integer highest_oi_pe_value = 0;

		Double highest_oi_change_ce_strike = 0.0;
		Integer highest_oi_change_ce_value = 0;

		Double highest_oi_change_pe_strike = 0.0;
		Integer highest_oi_change_pe_value = 0;

		for (OptionModle optionModle : lst) {
			if (optionModle.getC_oi() != null) {
				if (highest_oi_ce_value < optionModle.getC_oi()) {
					highest_oi_ce_value = optionModle.getC_oi();
					highest_oi_ce_strike = optionModle.getStrike_price();
				}
			}

			if (optionModle.getP_oi() != null) {
				if (highest_oi_pe_value < optionModle.getP_oi()) {
					highest_oi_pe_value = optionModle.getP_oi();
					highest_oi_pe_strike = optionModle.getStrike_price();
				}
			}

			if (optionModle.getC_change_oi() != null) {
				if (highest_oi_change_ce_value < optionModle.getC_change_oi()) {
					highest_oi_change_ce_value = optionModle.getC_change_oi();
					highest_oi_change_ce_strike = optionModle.getStrike_price();
				}
			}

			if (optionModle.getP_change_oi() != null) {
				if (highest_oi_change_pe_value < optionModle.getP_change_oi()) {
					highest_oi_change_pe_value = optionModle.getP_change_oi();
					highest_oi_change_pe_strike = optionModle.getStrike_price();
				}
			}
		}
		dailyMaxPain.setHighest_oi_ce(highest_oi_ce_strike);
		dailyMaxPain.setHighest_oi_ce_value(highest_oi_ce_value);

		dailyMaxPain.setHighest_oi_pe(highest_oi_pe_strike);
		dailyMaxPain.setHighest_oi_pe_value(highest_oi_pe_value);

		dailyMaxPain.setHighest_change_oi_ce(highest_oi_change_ce_strike);
		dailyMaxPain.setHighest_change_oi_ce_value(highest_oi_change_ce_value);

		dailyMaxPain.setHighest_change_oi_pe(highest_oi_change_pe_strike);
		dailyMaxPain.setHighest_change_oi_pe_value(highest_oi_change_pe_value);

	}

	private static void newExpiryCheck() throws IOException {

		if (DateUtils.getCurrentDay().equals("FRI")) {
			backUpFiles();
			boolean newFileStatus = createWeeklyMaxPainFile();
			BankNiftyData2.dailyMaxPain.clear();
		}

	}

	private static void backUpFiles() {

		File file = new File(APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_DAILYMAXPAIN_FILE);
		File file2 = new File(APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_DAILYMAXPAIN_FILE_Previous1);
		File file3 = new File(APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_DAILYMAXPAIN_FILE_Previous2);

		if (file3.exists()) {
			file3.delete();
		}
		file2.renameTo(file3);

		if (file2.exists()) {
			file2.delete();
		}

		file.renameTo(file2);
	}

	private static boolean createWeeklyMaxPainFile() throws IOException {
		File file = new File(APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_DAILYMAXPAIN_FILE);
		if (!file.exists()) {
			String csvHeaders = "date,expiry,maxPain,totalCEOI,totalPEOI,spot,change,highest_oi_ce,highest_oi_ce_value,highest_oi_pe,highest_oi_pe_value,highest_change_oi_ce,highest_change_oi_ce_value,highest_change_oi_pe,highest_change_oi_pe_value";
			boolean status = file.createNewFile();
			if (status) {
				CommonUtils.appendData2(csvHeaders, APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_DAILYMAXPAIN_FILE);
			}
			return true;
		} else {
			return false;
		}
	}

}
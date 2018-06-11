package org.stocksrin.banknifty.dataStore;

import java.io.File;
import java.util.TimerTask;

import org.stocksrin.banknifty.BankNiftyUtils;
import org.stocksrin.email.SendEmail;
import org.stocksrin.option.model.OptionModles;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.FileUtils;
import org.stocksrin.utils.LoggerSysOut;

public class WeeklyOptionSaverTask extends TimerTask {

	@Override
	public void run() {
		String optionFileDir = null;
		try {
			if (!DateUtils.isWeekEndDay()) {
				OptionModles optionModles = BankNiftyUtils.getBankNiftyOptionData(null);
				String expiry = optionModles.getExpiry();

				String date = BankNiftyUtils.getDate(optionModles.getUnderlyingSpotPrice());
				String expiryDir = APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY + "Weekly" + File.separator + expiry;
				File file = new File(expiryDir);
				if (!file.exists()) {
					if (file.mkdir()) {
						LoggerSysOut.print("new Directory is created! " + file);
					} else {
						LoggerSysOut.print("Failed to create directory!");
					}
				}

				optionFileDir = expiryDir + File.separator + date + ".json";

				FileUtils.writeDataAsJson(optionModles, optionFileDir);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SendEmail.sentMail("ERROR! BN Weekly File Saving  ", "File downloaded" + optionFileDir + "\n data \n");
		}
	}

	public static void main(String[] args) {
		WeeklyOptionSaverTask weeklyOptionSaverTask = new WeeklyOptionSaverTask();
		weeklyOptionSaverTask.run();
	}

}
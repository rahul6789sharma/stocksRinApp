package org.stocksrin.option.banknifty;

import java.util.List;
import java.util.TimerTask;

import org.stocksrin.option.common.model.OptionModles;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.LoggerSysOut;

public class LiveMarketOptionDataColloctorTask extends TimerTask {


	// 2min
	long timeInteval = 120000;

	// 1min
	// long timeInteval = 10000;

	@Override
	public void run() {
		try {

			// clear data in morning
			if (BankNiftyData2.shortedExpiry.isEmpty()) {
				// handle null data over weekend and after market time
				// deployment
				System.out.println("after makrket hr");
				fetchData();
			}
			if (!DateUtils.isWeekEndDay()) {

				while (!CommonUtils.getEveningTime()) {
					fetchData();
					Thread.sleep(timeInteval);
				}
				LoggerSysOut.print("End of trading Hour BNF Data Collection Stopped");
			} else {
				LoggerSysOut.print("WeekEnd Not Collecting Data");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void fetchData() {
		try {
			//BankNiftyData2.clear();
			OptionModles optionModles = BankNiftyUtils.getBankNiftyOptionData(null);
			LoggerSysOut.print("Collecting BN Data ... " + optionModles.getUnderlyingSpotPriceString());
			BankNiftyData2.bnWeeklyOptionChain2.put(optionModles.getDate(), optionModles);

			List<String> expiryList = optionModles.getExpiryList();
			BankNiftyData2.shortedExpiry.addAll(expiryList);
			BankNiftyData2.bnOptionData.put(optionModles.getExpiry(), optionModles);

			for (int i = 1; i < expiryList.size(); i++) {
				OptionModles om = BankNiftyUtils.getBankNiftyOptionData(expiryList.get(i));
				BankNiftyData2.bnOptionData.put(expiryList.get(i), om);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
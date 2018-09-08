package org.stocksrin.option.common;

import java.util.TimerTask;

import org.stocksrin.option.banknifty.BankNiftyData2;
import org.stocksrin.option.nifty.NiftyData;
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
			BankNiftyData2.clear();
			// clear data in morning
			if (BankNiftyData2.shortedExpiry.isEmpty() || NiftyData.shortedExpiry.isEmpty()) {
				// handle null data over weekend and after market time
				// deployment
				LoggerSysOut.print("Data loader if data is not present");
				priceUtils.fetchData();
			}
			if (!DateUtils.isWeekEndDay()) {

				while (CommonUtils.getEveningTime()) {
					priceUtils.fetchData();
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

}
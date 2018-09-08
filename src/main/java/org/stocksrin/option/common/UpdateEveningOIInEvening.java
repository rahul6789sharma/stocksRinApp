package org.stocksrin.option.common;

import java.util.TimerTask;

import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.LoggerSysOut;

public class UpdateEveningOIInEvening extends TimerTask {

	@Override
	public void run() {
		try {

			if (!DateUtils.isWeekEndDay()) {
				LoggerSysOut.print("*********** Evening Data Updater ************ ");
				priceUtils.fetchData();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

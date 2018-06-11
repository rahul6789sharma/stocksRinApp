package org.stocks.price;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.TimerTask;

import org.smarttrade.options.utils.DateUtils;
import org.stocksrin.utils.LoggerSysOut;

public class NSEPriceTask  extends TimerTask{

	@Override
	public void run() {
		LoggerSysOut.print("********** Started Executing NSEPriceTask DB Task at ********  " + DateUtils.getTodayDateTime());
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("IST"));

		if (now.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || now.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			LoggerSysOut.print(" WeekEnds Not Inserting Data to DB");

		} else {
			GoogleData[] data= HTTPClient.getData();
			for (GoogleData googleData : data) {
				//NsePriceFacade.saveNsePrice(googleData);
			}
		}
		
	}

}

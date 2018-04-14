package org.stocksrin.common.schedulers;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.stocksrin.fiidii.CollectMonthlyDataTask;

public class MonthlyScheduler {

	private Timer timer = new Timer();

	@PostConstruct
	public void init() {
		System.out.println(" ***** MorningTaskSchedular Starting *********");

		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.DAY_OF_MONTH, 1);
		today.set(Calendar.HOUR_OF_DAY, 6);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

		try {
			// run every 4 AM
			timer.schedule(new CollectMonthlyDataTask(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

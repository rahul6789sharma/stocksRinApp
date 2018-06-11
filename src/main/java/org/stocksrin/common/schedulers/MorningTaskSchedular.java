package org.stocksrin.common.schedulers;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.stocksrin.banknifty.dataStore.LiveMarketOptionDataColloctorTask;
import org.stocksrin.banknifty.notification.BankNiftyNotificationTask;
import org.stocksrin.utils.LoggerSysOut;

@Singleton
@Startup
public class MorningTaskSchedular {

	private Timer timer = new Timer();
	private Timer timer2 = new Timer();

	@PostConstruct
	public void init() {
		LoggerSysOut.print(" ***** MorningTaskSchedular Starting *********");

		Calendar today3 = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today3.set(Calendar.HOUR_OF_DAY, 9);
		today3.set(Calendar.MINUTE, 19);
		today3.set(Calendar.SECOND, 0);

		Calendar today4 = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today4.set(Calendar.HOUR_OF_DAY, 9);
		today4.set(Calendar.MINUTE, 19);
		today4.set(Calendar.SECOND, 0);

		Calendar calendarCurrentTIme = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		if (calendarCurrentTIme.get(Calendar.HOUR_OF_DAY) >= 15) {
			// market is done for today scheduled for tomo
			today3.add(Calendar.DATE, 1);
			today4.add(Calendar.DATE, 1);
			try {
				timer.schedule(new LiveMarketOptionDataColloctorTask(), today3.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				timer2.schedule(new BankNiftyNotificationTask(), today4.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
			} catch (Exception e) {
				e.printStackTrace();
			}
			// to handel after market deployment
			LiveMarketOptionDataColloctorTask liveMarketOptionDataColloctorTask = new LiveMarketOptionDataColloctorTask();
			liveMarketOptionDataColloctorTask.run();
		} else {
			System.out.println("################# For Testing Only ####################");
			
		
			/*try {
				timer.schedule(new LiveMarketOptionDataColloctorTask(), today3.getTime());
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				timer2.schedule(new BankNiftyNotificationTask(), today4.getTime());
			} catch (Exception e) {
				e.printStackTrace();
			}*/
		}

		// run every 4 AM

	}

	@PreDestroy
	public void shutDown() {
		LoggerSysOut.print("**** MorningTaskSchedular Stoping*****");
		timer.cancel();
		timer2.cancel();

	}

}
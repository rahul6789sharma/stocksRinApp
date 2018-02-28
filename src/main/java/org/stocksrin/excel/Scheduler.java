package org.stocksrin.excel;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class Scheduler {

	Timer timer = new Timer();
	
	@PostConstruct
	public void init() {
		System.out.println("Conf Bhav Copy Downloader");

		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.HOUR_OF_DAY, 17);
		today.set(Calendar.MINUTE, 30);
		today.set(Calendar.SECOND, 0);

		// run every 5 PM
		
		timer.schedule(new DownloadDailyBhavCopyTask(), today.getTime(),
				TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));

	}
	
	@PreDestroy
	public void shutDown() {
		System.out.println("Scheduler Stoping*****");
		timer.cancel();
	}
}

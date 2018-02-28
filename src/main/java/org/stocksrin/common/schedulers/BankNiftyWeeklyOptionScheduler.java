package org.stocksrin.common.schedulers;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.stocksrin.banknifty.BankNiftyOptionDownloaderTask;

@Singleton
@Startup
public class BankNiftyWeeklyOptionScheduler {

	private Timer timer = new Timer();

	@PostConstruct
	public void init() {
		System.out.println(" ***** BankNiftyWeeklyOptionScheduler starting *********");

		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.HOUR_OF_DAY, 17);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

		// run every 5 PM

		timer.schedule(new BankNiftyOptionDownloaderTask(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
	}

	@PreDestroy
	public void shutDown() {
		System.out.println("**** BankNiftyWeeklyOptionScheduler Stoping*****");
		timer.cancel();
	}
}

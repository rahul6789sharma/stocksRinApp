package org.stocksrin.common.schedulers;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.stocksrin.fiidii.FIIDIITask;

@Singleton
@Startup
public class FIIDIIScheduler {

	private Timer timer = new Timer();

	@PostConstruct
	public void init() {
		System.out.println(" ***** FIIDIIScheduler starting *********");

		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.HOUR_OF_DAY, 18);
		today.set(Calendar.MINUTE, 45);
		today.set(Calendar.SECOND, 0);

		// run every 5 PM

		timer.schedule(new FIIDIITask(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
	}

	@PreDestroy
	public void shutDown() {
		System.out.println("**** FIIDIIScheduler Stoping*****");
		timer.cancel();
	}
}
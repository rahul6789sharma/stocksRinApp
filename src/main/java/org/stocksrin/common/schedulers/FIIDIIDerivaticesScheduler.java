package org.stocksrin.common.schedulers;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.stocksrin.fiidii.derivatives.FIIDIIDerivaticsTask;

@Singleton
@Startup
public class FIIDIIDerivaticesScheduler {

	// run every 5 PM
	Timer timer = new Timer();

	@PostConstruct
	public void init() {

		System.out.println(" ***** FIIDIIDerivaticesScheduler starting *********");

		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.HOUR_OF_DAY, 18);
		today.set(Calendar.MINUTE, 30);
		today.set(Calendar.SECOND, 0);

		timer.schedule(new FIIDIIDerivaticsTask(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
	}

	@PreDestroy
	public void shutDown() {
		System.out.println("Stoping*****");
		timer.cancel();
	}
}

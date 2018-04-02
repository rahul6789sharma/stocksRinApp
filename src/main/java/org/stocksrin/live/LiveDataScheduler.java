package org.stocksrin.live;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class LiveDataScheduler {

	// run after evey 5 min
	Timer timer = new Timer();

	@PostConstruct
	public void init() {

		System.out.println(" ***** LiveDataScheduler starting *********");

		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.HOUR_OF_DAY, 17);
		today.set(Calendar.MINUTE, 50);
		today.set(Calendar.SECOND, 0);

		//timer.scheduleAtFixedRate(new LiveDataCollectortask(), 500, 300000);
	}

	@PreDestroy
	public void shutDown() {
		System.out.println("Stoping*****");
		timer.cancel();
	}
}
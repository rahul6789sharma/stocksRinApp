package org.stocksrin.live;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;

import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.stocksrin.utils.LoggerSysOut;

@Singleton
@Startup
public class LiveDataScheduler {

	// run after evey 5 min
	Timer timer = new Timer();

	// @PostConstruct
	public void init() {

		LoggerSysOut.print(" ***** LiveDataScheduler starting *********");

		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.HOUR_OF_DAY, 17);
		today.set(Calendar.MINUTE, 50);
		today.set(Calendar.SECOND, 0);

		// timer.scheduleAtFixedRate(new LiveDataCollectortask() 500 300000);
	}

	@PreDestroy
	public void shutDown() {
		LoggerSysOut.print("Stoping*****");
		timer.cancel();
	}
}
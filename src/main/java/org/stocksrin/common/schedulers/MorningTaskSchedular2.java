package org.stocksrin.common.schedulers;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.stocksrin.option.common.automation.InMemeoryStrategyBuilder;
import org.stocksrin.option.common.automation.IntraDayStrategyFileBuilder;
import org.stocksrin.option.common.automation.dynamic.DyanmicStrategy;
import org.stocksrin.utils.LoggerSysOut;

@Singleton
@Startup
public class MorningTaskSchedular2 {

	private Timer timer = new Timer();
	private Timer timer2 = new Timer();

	@PostConstruct
	public void init() {

		LoggerSysOut.print(" ***** MorningTaskSchedular2 Starting *********");

		Calendar t1 = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		t1.set(Calendar.HOUR_OF_DAY, 9);
		t1.set(Calendar.MINUTE, 20);
		t1.set(Calendar.SECOND, 0);

		Calendar t2 = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		t2.set(Calendar.HOUR_OF_DAY, 9);
		t2.set(Calendar.MINUTE, 23);
		t2.set(Calendar.SECOND, 0);

		try {
			Scheduler.scheduleTask(9, 20, new IntraDayStrategyFileBuilder());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Scheduler.scheduleTask(9, 21, new DyanmicStrategy());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// need to start after strategy build
		try {
			Scheduler.scheduleTask(9, 22, new InMemeoryStrategyBuilder());
		} catch (Exception e) {
			e.printStackTrace();
		}
		LoggerSysOut.print(" ***** MorningTaskSchedular2 Started *********");
	}

	@PreDestroy
	public void destory() {
		timer.cancel();
		timer2.cancel();
	}

}

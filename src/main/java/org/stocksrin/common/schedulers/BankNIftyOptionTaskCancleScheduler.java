package org.stocksrin.common.schedulers;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.stocksrin.banknifty.option.alog2.OptionTask;

@Singleton
@Startup
public class BankNIftyOptionTaskCancleScheduler {

	private Timer timer = new Timer();

	@PostConstruct
	public void init() {
		System.out.println(" ***** BankNIftyOptionTaskCancleScheduler Starting *********");

		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.HOUR_OF_DAY, 15);
		today.set(Calendar.MINUTE, 35);
		today.set(Calendar.SECOND, 0);

		// run every 4 AM
		timer.schedule(new OptionTask(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));

	}

	@PreDestroy
	public void shutDown() {
		System.out.println("**** BankNIftyOptionTaskCancleScheduler Stoping*****");
		OptionTask.cancleAllTimerTask();
		timer.cancel();
	}
}

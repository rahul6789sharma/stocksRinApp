package org.stocksrin.common.schedulers;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.stocksrin.banknifty.option.alog2.OptionTask;
import org.stocksrin.utils.LoggerSysOut;

@Singleton
@Startup
public class BankNIftyOptionTaskCancleScheduler {

	private Timer timer = new Timer();

	//@PostConstruct
	public void init() {
		LoggerSysOut.print(" ***** BankNIftyOptionTaskCancleScheduler Starting *********");

		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.HOUR_OF_DAY, 15);
		today.set(Calendar.MINUTE, 38);
		today.set(Calendar.SECOND, 0);

		// run every 4 AM
		try {
			timer.schedule(new OptionTask(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PreDestroy
	public void shutDown() {
		LoggerSysOut.print("**** BankNIftyOptionTaskCancleScheduler Stoping*****");
		OptionTask.cancleAllTimerTask();
		timer.cancel();
	}
}

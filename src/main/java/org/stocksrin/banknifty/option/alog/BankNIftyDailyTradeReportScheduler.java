package org.stocksrin.banknifty.option.alog;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

//@Singleton
//@Startup
public class BankNIftyDailyTradeReportScheduler {

	private Timer timer = new Timer();

	@PostConstruct
	public void init() {

		System.out.println(" ***** BankNIftyDailyTradeReportScheduler Starting *********");

		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.HOUR_OF_DAY, 16);
		today.set(Calendar.MINUTE, 5);
		today.set(Calendar.SECOND, 0);

		// run every 4 PM

		//timer.schedule(new BankNiftyCsvReportTask(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
	}

	@PreDestroy
	public void shutDown() {
		System.out.println("**** BankNIftyDailyTradeReportScheduler Stoping*****");
		timer.cancel();
	}
}

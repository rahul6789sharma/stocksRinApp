package org.stocksrin.banknifty.option.alog;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.stocksrin.email.SendEmail;

//@Singleton
//@Startup
public class BankNiftyLastDayTradeGetDataScheduler {

	private Timer timer = new Timer();

	@PostConstruct
	public void init() {
		System.out.println(" ***** BankNiftyLastDayTraderGetDataScheduler Starting *********");

		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.HOUR_OF_DAY, 9);
		today.set(Calendar.MINUTE, 20);
		today.set(Calendar.SECOND, 0);

		// run every 4 AM
		System.out.println(" ***** BankNiftyLastDayTraderGetDataScheduler Starting ********* " + today);
		//timer.schedule(new BNiftyOptionTask(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
	}

	@PreDestroy
	public void shutDown() {
		System.out.println("**** BankNiftyLastDayTraderGetDataScheduler Stoping*****");
		SendEmail.sentMail(" Server STOPPED! " , "Alert");
		timer.cancel();
	}

}

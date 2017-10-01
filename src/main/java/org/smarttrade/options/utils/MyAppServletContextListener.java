package org.smarttrade.options.utils;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.option.currency.tasks.USDINROptionDBTask;
import org.option.db.HibernateUtil;
import org.stocks.price.NSEPriceTask;

public class MyAppServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("*************** ServletContextListener destroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("*************** ServletContextListener starting  **************");
		try {
			//HibernateUtil.init();
			//USDINRDBTASKScheduler();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("*************** ServletContextListener Started  **************");
	}

	private void USDINRDBTASKScheduler() {
		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.HOUR_OF_DAY, 19);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

		
		Calendar today2 = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today2.set(Calendar.HOUR_OF_DAY, 19);
		today2.set(Calendar.MINUTE, 30);
		today2.set(Calendar.SECOND, 0);
		
		// every night at 8pm you run your task
		Timer timer = new Timer();
		timer.schedule(new USDINROptionDBTask(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
		
		timer.schedule(new NSEPriceTask(), today2.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
		
	}

}
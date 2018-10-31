package org.stocksrin.common.schedulers;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.stocksrin.live.LiveDataCollectortask;
import org.stocksrin.option.banknifty.notification.BankNiftyNotificationTask;
import org.stocksrin.option.common.priceUtils;
import org.stocksrin.option.notification.NiftyNotificationTask;
import org.stocksrin.utils.LoggerSysOut;

@Singleton
@Startup
public class MorningTaskSchedular {

	@PostConstruct
	public void init() {
		LoggerSysOut.print(" ***** MorningTaskSchedular Starting *********");

		try {
			priceUtils.fetchData();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			Scheduler.scheduleTask(9, 18, new LiveDataCollectortask());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Scheduler.scheduleTask(9, 19, new BankNiftyNotificationTask());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Scheduler.scheduleTask(9, 20, new NiftyNotificationTask());
		} catch (Exception e) {
			e.printStackTrace();
		}

		LoggerSysOut.print(" ***** MorningTaskSchedular Started *********");
	}

	@PreDestroy
	public void shutDown() {
		LoggerSysOut.print("**** MorningTaskSchedular Stoping*****");

	}

}
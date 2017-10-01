package org.option.currency.tasks;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {

	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		scheduledExecutorService.scheduleAtFixedRate(new USDINRDataCollector(), 1, 10, TimeUnit.SECONDS);
		
		//scheduledExecutorService.scheduleAtFixedRate(new TestThread(), 1, 5, TimeUnit.SECONDS);
	}
}

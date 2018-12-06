package org.stocksrin.common.schedulers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
	public static ScheduledExecutorService scheduleTask2(int hr, int min, Runnable task) {
		LocalDateTime localNow = LocalDateTime.now();
		ZoneId currentZone = ZoneId.of("Asia/Kolkata");
		ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);

		ZonedDateTime zonedNext5 = zonedNow.withHour(hr).withMinute(min).withSecond(0);
		if (zonedNow.compareTo(zonedNext5) > 0) {
			zonedNext5 = zonedNext5.plusDays(1L);
		}
		Duration duration = Duration.between(zonedNow, zonedNext5);
		long initalDelay = duration.getSeconds();

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(task, initalDelay, 86400L, TimeUnit.SECONDS);
		return scheduler;
	}

	public static ScheduledExecutorService scheduleTask(int hr, int min, Runnable task) {
		ZoneId currentZone = ZoneId.of("Asia/Kolkata");

		LocalDateTime localNow = LocalDateTime.now(currentZone);
		ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);

		ZonedDateTime zonedNext5 = zonedNow.withHour(hr).withMinute(min).withSecond(0);
		if (zonedNow.compareTo(zonedNext5) > 0) {
			System.out.println("Time is passed : " + task.getClass());
			zonedNext5 = zonedNext5.plusDays(1L);
			ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
			scheduler.schedule(task, 1L, TimeUnit.SECONDS);
		} else {
			System.out.println("will  be run task: " + task.getClass());
		}
		Duration duration = Duration.between(zonedNow, zonedNext5);
		long initalDelay = duration.getSeconds();

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(task, initalDelay, 86400L, TimeUnit.SECONDS);
		return scheduler;
	}
}

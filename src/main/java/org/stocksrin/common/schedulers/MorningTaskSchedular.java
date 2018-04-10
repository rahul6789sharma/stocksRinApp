package org.stocksrin.common.schedulers;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.stocksrin.banknifty.option.alog2.BNiftyAlgoThreadSponingTask;
import org.stocksrin.banknifty.option.alog2.BankNiftyMaxPainThread;

@Singleton
@Startup
public class MorningTaskSchedular {

	private Timer timer = new Timer();
	private Timer timer2 = new Timer();

	@Inject
	private BNiftyAlgoThreadSponingTask niftyAlgoThreadSponingTask;

	@Inject
	private BankNiftyMaxPainThread bankNiftyMaxPainThread;

	@PostConstruct
	public void init() {
		System.out.println(" ***** MorningTaskSchedular Starting *********");

		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.HOUR_OF_DAY, 9);
		today.set(Calendar.MINUTE, 18);
		today.set(Calendar.SECOND, 0);

		timer2.schedule(bankNiftyMaxPainThread, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
		// run every 4 AM
		timer.schedule(niftyAlgoThreadSponingTask, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));

	}

	@PreDestroy
	public void shutDown() {
		System.out.println("**** MorningTaskSchedular Stoping*****");
		timer.cancel();
		timer2.cancel();

		if (niftyAlgoThreadSponingTask != null) {
			List<Timer> allTimers = niftyAlgoThreadSponingTask.getAllTimers();
			for (Timer timer1 : allTimers) {
				timer1.cancel();
			}
		}

		if (bankNiftyMaxPainThread != null) {
			List<Timer> allTimers = bankNiftyMaxPainThread.getAllTimers();
			for (Timer timer1 : allTimers) {
				timer1.cancel();
			}
		}

	}

}

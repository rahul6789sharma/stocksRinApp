package org.stocksrin.common.schedulers;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.stocksrin.banknifty.BankNiftyOptionDownloaderTask;
import org.stocksrin.banknifty.DailyMaxPainSaveTask;
import org.stocksrin.fiidii.FIIDIITask;
import org.stocksrin.fiidii.derivatives.FIIDIIDerivaticsTask;
import org.stocksrin.oi.allParticapent.FoOiTask;
import org.stocksrin.oi.future.NiftyOITask;

@Singleton
@Startup
public class EveningOneTimeTaskScheduler {

	private Timer timer = new Timer();
	private Timer timer2 = new Timer();
	private Timer timer3 = new Timer();
	private Timer timer4 = new Timer();
	private Timer timer5 = new Timer();
	private Timer timer6 = new Timer();
	private Timer timer7 = new Timer();

	@PostConstruct
	public void init() {
		System.out.println(" ***** EveningOneTimeTaskScheduler starting *********");

		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.HOUR_OF_DAY, 17);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

		// run every 5 PM

		timer.schedule(new BankNiftyOptionDownloaderTask(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));

		Calendar today2 = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today2.set(Calendar.HOUR_OF_DAY, 17);
		today2.set(Calendar.MINUTE, 10);
		today2.set(Calendar.SECOND, 0);

		timer2.schedule(new DailyMaxPainSaveTask(), today2.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));

		Calendar today4 = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today4.set(Calendar.HOUR_OF_DAY, 18);
		today4.set(Calendar.MINUTE, 10);
		today4.set(Calendar.SECOND, 0);

		timer4.schedule(new FoOiTask(), today4.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));

		Calendar today5 = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today5.set(Calendar.HOUR_OF_DAY, 18);
		today5.set(Calendar.MINUTE, 45);
		today5.set(Calendar.SECOND, 0);

		// run every 5 PM

		timer5.schedule(new FIIDIITask(), today5.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));

		Calendar today3 = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today3.set(Calendar.HOUR_OF_DAY, 20);
		today3.set(Calendar.MINUTE, 00);
		today3.set(Calendar.SECOND, 0);

		timer3.schedule(new NiftyOITask(), today3.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));

		Calendar today6 = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today6.set(Calendar.HOUR_OF_DAY, 18);
		today6.set(Calendar.MINUTE, 30);
		today6.set(Calendar.SECOND, 0);

		timer6.schedule(new FIIDIIDerivaticsTask(), today6.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));

		Calendar today7 = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today7.set(Calendar.HOUR_OF_DAY, 17);
		today7.set(Calendar.MINUTE, 30);
		today7.set(Calendar.SECOND, 0);

		// run every 5 PM

		//timer7.schedule(new DownloadDailyBhavCopyTask(), today7.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));

	}

	@PreDestroy
	public void shutDown() {
		System.out.println("**** EveningOneTimeTaskScheduler Stoping*****");
		timer.cancel();
		timer2.cancel();
		timer3.cancel();
		timer4.cancel();
		timer5.cancel();
		timer6.cancel();
	}
}

package org.stocksrin.common.schedulers;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.stocksrin.bhavcopy.DerivativesBhavCopyOIDownloaderTask;
import org.stocksrin.fiidii.FIIDIICashMarketTask;
import org.stocksrin.oi.allparticapent.ParticapentFNOOITask;
import org.stocksrin.option.banknifty.BNExpiryMaxPainSaverTask;
import org.stocksrin.option.banknifty.BankNiftyOptionDownloaderTask;
import org.stocksrin.option.common.UpdateEveningOIInEvening;
import org.stocksrin.utils.LoggerSysOut;

@Singleton
@Startup
public class EveningOneTimeTaskScheduler {

	private Timer timer = new Timer();
	private Timer timer2 = new Timer();
	private Timer timer3 = new Timer();
	private Timer timer4 = new Timer();
	private Timer timer5 = new Timer();
	private Timer timer6 = new Timer();

	@PostConstruct
	public void init() {

		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.HOUR_OF_DAY, 17);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

		// run every 5 PM

		try {
			timer.schedule(new BankNiftyOptionDownloaderTask(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Calendar today4 = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today4.set(Calendar.HOUR_OF_DAY, 19);
		today4.set(Calendar.MINUTE, 5);
		today4.set(Calendar.SECOND, 0);

		try {
			// Participant wise Open Interest (csv) data download from NSE
			timer2.schedule(new ParticapentFNOOITask(), today4.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Calendar today5 = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today5.set(Calendar.HOUR_OF_DAY, 18);
		today5.set(Calendar.MINUTE, 45);
		today5.set(Calendar.SECOND, 0);

		// run every 5 PM

		try {
			timer3.schedule(new FIIDIICashMarketTask(), today5.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Calendar today7 = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today7.set(Calendar.HOUR_OF_DAY, 18);
		today7.set(Calendar.MINUTE, 5);
		today7.set(Calendar.SECOND, 0);

		try {
			// option chain updated at around 6 in evening
			timer5.schedule(new UpdateEveningOIInEvening(), today7.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Calendar today6 = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today6.set(Calendar.HOUR_OF_DAY, 18);
		today6.set(Calendar.MINUTE, 22);
		today6.set(Calendar.SECOND, 0);

		try {
			// saving banknifty weekly option max pain on daily basis
			timer4.schedule(new BNExpiryMaxPainSaverTask(), today6.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Calendar today8 = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today8.set(Calendar.HOUR_OF_DAY, 19);
		today8.set(Calendar.MINUTE, 5);
		today8.set(Calendar.SECOND, 0);

		try {
			// saving banknifty weekly option max pain on daily basis
			timer6.schedule(new DerivativesBhavCopyOIDownloaderTask(), today8.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@PreDestroy
	public void shutDown() {
		LoggerSysOut.print("**** EveningOneTimeTaskScheduler Stoping*****");
		timer.cancel();
		timer2.cancel();
		timer3.cancel();
		timer4.cancel();
		timer5.cancel();
		timer5.cancel();
		timer6.cancel();

	}
}

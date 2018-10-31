package org.stocksrin.common.schedulers;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.stocksrin.email.SendEmail;

@Singleton
@Startup
public class MorningTaskSchedular3 {

	@PostConstruct
	public void init() {

		try {
			Scheduler.scheduleTask(6, 0, new WishTask("Hello Morning 6:00"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Scheduler.scheduleTask(8, 0, new WishTask("Hello Morning 8:00"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Scheduler.scheduleTask(9, 0, new WishTask("Hello Morning 9:00"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Scheduler.scheduleTask(23, 30, new WishTask("Hello Night 23:30"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Scheduler.scheduleTask(1, 0, new WishTask("Hello Night 1:00"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@PreDestroy
	public void destory() {

	}

}

class WishTask implements Runnable {
	String msg;

	public WishTask(String msg) {
		this.msg = msg;
	}

	public void run() {
		System.out.println(this.msg);
		SendEmail.sentMail(this.msg, "");
	}
}

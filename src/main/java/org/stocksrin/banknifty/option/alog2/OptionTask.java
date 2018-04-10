package org.stocksrin.banknifty.option.alog2;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class OptionTask extends TimerTask {

	private static List<Timer> timers = new ArrayList<>(4);

	public static void addTimerTask(Timer timer) {
		timers.add(timer);
	}

	public static void cancleAllTimerTask() {
		BNiftyAlgo.flag = true;
		for (Timer timer : timers) {
			timer.cancel();
		}
	}

	@Override
	public void run() {
		System.out.println("Cancle all Task");
		cancleAllTimerTask();
	}

}

package org.stocksrin.banknifty.option.alog2;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.stocksrin.utils.LoggerSysOut;

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
		LoggerSysOut.print("Cancling  all Task statred ");
		LoggerSysOut.print("Number of task : " + timers.size());
		cancleAllTimerTask();
		LoggerSysOut.print("Cancling  all Task completed ");
	}

}

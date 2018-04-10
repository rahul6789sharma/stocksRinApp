package org.stocksrin.banknifty.option.alog2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PreDestroy;
import javax.ejb.Singleton;

import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;

@Singleton
public class BNiftyAlgoThreadSponingTask extends TimerTask {

	private List<Timer> allTimers = new ArrayList<>(2);

	@Override
	public void run() {
		if (!DateUtils.isWeekEndDay()) {
			if (CommonUtils.getEveningTime()) {
				try {
					Map<Integer, List<StrategyModel>> strategyMap = CommonUtils.getBankNiftyStrategy();
					Set<Integer> keys = strategyMap.keySet();
					long initDelay = 10;
					Timer timer = null;
					for (Integer in : keys) {
						timer = new Timer();
						OptionDataCollectorTask optionDataCollectorTask = new OptionDataCollectorTask("Algo_" + in, in);
						timer.scheduleAtFixedRate(optionDataCollectorTask, initDelay, 120000l);
						initDelay = initDelay + 10000;
						allTimers.add(timer);

					}
					if (timer != null) {
						OptionTask.addTimerTask(timer);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				BNiftyAlgo.flag = true;
				System.out.println("Market Closed");
			}
		}
	}

	public List<Timer> getAllTimers() {
		return allTimers;
	}

	@PreDestroy
	public void shutDown() {
		for (Timer timer : allTimers) {
			timer.cancel();
		}
	}

}
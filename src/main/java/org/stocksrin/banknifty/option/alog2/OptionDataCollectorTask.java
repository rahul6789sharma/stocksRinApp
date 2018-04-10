package org.stocksrin.banknifty.option.alog2;

import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.stocksrin.banknifty.TickData;
import org.stocksrin.utils.CommonUtils;

public class OptionDataCollectorTask extends TimerTask {

	private String threadName;
	private Integer strategySerial;

	public OptionDataCollectorTask(String threadName, Integer strategySerial) {
		this.threadName = threadName;
		this.strategySerial = strategySerial;
	}

	@Override
	public void run() {
		System.out.println(" Data Collection " + threadName);
		if (CommonUtils.getEveningTime()) {
			Map<Integer, List<StrategyModel>> strategyMap = CommonUtils.getBankNiftyStrategy();
			List<StrategyModel> lst = strategyMap.get(strategySerial);
			String expiry = lst.get(0).getExpiry();
			try {
				List<TickData> result = BNiftyAlgo.getData(lst, expiry);
				BNiftyAlgo.algo2(result, lst);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
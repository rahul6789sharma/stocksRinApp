package org.stocksrin.banknifty.option.alog2;

import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.stocksrin.banknifty.TickData;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.LoggerSysOut;

public class OptionDataCollectorTask extends TimerTask {

	private String threadName;
	private Integer strategySerial;

	public OptionDataCollectorTask(String threadName, Integer strategySerial) {
		this.threadName = threadName;
		this.strategySerial = strategySerial;
	}

	@Override
	public void run() {
		LoggerSysOut.print(" Data Collection " + threadName);
		if (CommonUtils.getEveningTime()) {
			Map<Integer, List<StrategyModel>> strategyMap = CommonUtils.getBankNiftyStrategy();
			List<StrategyModel> lst = strategyMap.get(strategySerial);
			String expiry = lst.get(0).getExpiry();
			String name = lst.get(0).getDes();
			try {
				List<TickData> result = BNiftyAlgo.getData(lst, expiry);
				if (result != null) {
					BNiftyAlgo.algo2(result, lst, name);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

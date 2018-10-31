package org.stocksrin.option.common;

import java.util.List;

import org.stocksrin.option.common.model.Strategy;
import org.stocksrin.option.common.model.Strategy.UnderLying;
import org.stocksrin.option.common.model.StrategyModel;
import org.stocksrin.utils.CommonUtils;

public class TaskV2 extends Thread {

	static long timeInteval = 120000;
	public String filepath;
	public String fileName;

	public TaskV2(String filepath, String fileName) {
		this.filepath = filepath;
		this.fileName = fileName;
	}

	@Override
	public void run() {
		System.out.println(filepath);

		while (true) {
			System.out.println("i");
			try {
				List<StrategyModel> strategyModels = CommonUtils.getStrategyModel(filepath);

				Strategy strategy1 = TradeDB.trade.get(fileName);
				if (strategy1 == null) {
					Strategy strategy = new Strategy(UnderLying.BANKNIFTY);
					strategy.setStrategyName(fileName);

					strategy.setStrategyModels(strategyModels);
					TradeDB.trade.put(fileName, strategy);
				} else {
					List<StrategyModel> strategyModels1 = strategy1.getStrategyModels();

				}

				PriceUpdater.update(strategyModels, "BANKNIFTY", fileName);

				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}

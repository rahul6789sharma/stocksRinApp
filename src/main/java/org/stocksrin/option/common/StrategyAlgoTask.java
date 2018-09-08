package org.stocksrin.option.common;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

import org.stocksrin.option.banknifty.BankNiftyData2;
import org.stocksrin.option.common.model.OptionModles;
import org.stocksrin.option.common.model.StrategyModel;
import org.stocksrin.option.nifty.NiftyData;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;

public class StrategyAlgoTask extends TimerTask {
	static long timeInteval = 120000;

	// long timeInteval = 30000;

	@Override
	public void run() {

		if (!DateUtils.isWeekEndDay()) {
			startManualStrategies(APPConstant.STOCKSRIN__STRATEGY_DIR);
			startManualStrategies(APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR);
		}
	}

	public static void startManualStrategies(String starttegyDir) {
		try {

			Map<String, List<StrategyModel>> strategyMap = CommonUtils.getStrategy(starttegyDir);
			Set<String> strategies = strategyMap.keySet();

			for (String string : strategies) {
				List<StrategyModel> startgy = strategyMap.get(string);

				String underlaying = string.split("_")[1];
				String name = string.split("-")[0];

				if (underlaying.equals("BANKNIFTY.csv")) {
					Map<String, OptionModles> dataModle = BankNiftyData2.bnOptionData;

					if (!startgy.isEmpty()) {
						StrategyAlgo strategyAlgo2 = new StrategyAlgo(dataModle, startgy, "BNifty-" + name, timeInteval, string.split("\\.")[0], starttegyDir);
						strategyAlgo2.start();
					}
				} else if (underlaying.equals("NIFTY.csv")) {
					Map<String, OptionModles> dataModle = NiftyData.optionData;
					if (!startgy.isEmpty()) {
						StrategyAlgo strategyAlgo2 = new StrategyAlgo(dataModle, startgy, "Nifty-" + name, timeInteval, string.split("\\.")[0],starttegyDir);
						strategyAlgo2.start();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		priceUtils.fetchData();
		StrategyAlgoTask algoTask = new StrategyAlgoTask();
		algoTask.run();
	}
}
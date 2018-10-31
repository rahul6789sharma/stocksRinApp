package org.stocksrin.option.common;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.stocksrin.option.banknifty.BankNiftyData2;
import org.stocksrin.option.common.model.OptionModles;
import org.stocksrin.option.common.model.StrategyModel;
import org.stocksrin.option.nifty.NiftyData;
import org.stocksrin.utils.CommonUtils;

// start alog 
public class StrategyAlgoTaskIntraDay {

	static long timeInteval = 120000;

	public static void startManualStrategies(String starttegyDir) {
		try {

			if (CommonUtils.getEveningTimeForStrategy()) {
				Map<String, List<StrategyModel>> strategyMap = CommonUtils.getStrategy(starttegyDir);
				Set<String> strategies = strategyMap.keySet();

				for (String string : strategies) {
					List<StrategyModel> startgy = strategyMap.get(string);

					String underlaying = string.split("_")[1];
					String name = string.split("-")[0];

					if (underlaying.equals("BANKNIFTY.csv")) {
						Map<String, OptionModles> dataModle = BankNiftyData2.bnOptionData;

						if (!startgy.isEmpty()) {
							StrategyAlgo strategyAlgo2 = new StrategyAlgo(dataModle, startgy, "BNF-" + name, timeInteval, string.split("\\.")[0], starttegyDir);
							strategyAlgo2.start();
						}
					} else if (underlaying.equals("NIFTY.csv")) {
						Map<String, OptionModles> dataModle = NiftyData.optionData;
						if (!startgy.isEmpty()) {
							StrategyAlgo strategyAlgo2 = new StrategyAlgo(dataModle, startgy, "NF-" + name, timeInteval, string.split("\\.")[0], starttegyDir);
							strategyAlgo2.start();
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

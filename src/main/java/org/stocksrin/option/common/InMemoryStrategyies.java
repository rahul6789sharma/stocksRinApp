package org.stocksrin.option.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.stocksrin.option.common.model.LockObject;
import org.stocksrin.option.common.model.Strategy;
import org.stocksrin.option.common.model.StrategyModel;
import org.stocksrin.utils.APPConstant;

public class InMemoryStrategyies {

	private static Map<String, Strategy> strategies = new HashMap<>();
	private static Map<String, Strategy> strategiesIntraDay = new HashMap<>();
	//public static Map<String, Strategy> strategiesIntraDayDyanmic = new HashMap<>();

	public static synchronized void put(String key, Strategy strategy, String dir) {

		if (dir.equals(APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_INTRADAY_TradeStrategy)) {
			putIntraDay(key, strategy);
		} else {
			put(key, strategy);
		}

	}

	// strategy here ltp would be 0
	public static synchronized void putIntraDay(String key, Strategy strategy) {

		// already existing strategy
		Strategy data = strategiesIntraDay.get(key);

		if (data == null) {
			strategiesIntraDay.put(key, strategy);

		} else {
			strategy.setUnderlying_ltp(data.getUnderlying_ltp());
			strategy.setTotalPL(data.getTotalPL());
			strategy.setTotalPLMax(data.getTotalPLMax());
			strategy.setTotalPLMaxSpot(data.getTotalPLMaxSpot());
			strategy.setTotalPLMaxTime(data.getTotalPLMaxTime());
			strategy.setTotalPLMin(data.getTotalPLMin());
			strategy.setTotalPLMinSpot(data.getTotalPLMinSpot());
			strategy.setTotalPLMinTime(data.getTotalPLMinTime());

			List<StrategyModel> lst = data.getStrategyModels();

			for (StrategyModel strategyModel : lst) {
				for (StrategyModel s : strategy.getStrategyModels()) {

					if (strategyModel.getType().equals(s.getType()) && strategyModel.getStrike() == s.getStrike() && strategyModel.getExpiry().equals(s.getExpiry())) {
						s.setLtp(strategyModel.getLtp());
						s.setCurrent_IV(strategyModel.getCurrent_IV());
					}

				}
			}
			strategiesIntraDay.put(key, strategy);
		}
	}

	private static synchronized void put(String key, Strategy strategy) {

		Strategy data = strategies.get(key);
		if (data == null) {
			strategies.put(key, strategy);

		} else {
			strategy.setUnderlying_ltp(data.getUnderlying_ltp());
			strategy.setTotalPL(data.getTotalPL());
			strategy.setTotalPLMax(data.getTotalPLMax());
			strategy.setTotalPLMaxSpot(data.getTotalPLMaxSpot());
			strategy.setTotalPLMaxTime(data.getTotalPLMaxTime());
			strategy.setTotalPLMin(data.getTotalPLMin());
			strategy.setTotalPLMinSpot(data.getTotalPLMinSpot());
			strategy.setTotalPLMinTime(data.getTotalPLMinTime());

			List<StrategyModel> lst = data.getStrategyModels();

			for (StrategyModel strategyModel : lst) {
				for (StrategyModel s : strategy.getStrategyModels()) {
					if (strategyModel.getType().equals(s.getType()) && strategyModel.getStrike() == s.getStrike() && strategyModel.getExpiry().equals(s.getExpiry())) {
						s.setLtp(strategyModel.getLtp());
						s.setCurrent_IV(strategyModel.getCurrent_IV());
					}
				}
			}
			strategies.put(key, strategy);
		}
	}

	public static synchronized Map<String, Strategy> getStrategies() {
		Map<String, Strategy> result;
		try {
			LockObject.getWriteLock();
			result = strategies;
		} finally {
			LockObject.realseWriteLock();
		}
		return result;
	}

	public static synchronized Map<String, Strategy> getStrategiesIntraDay() {
		Map<String, Strategy> result;
		try {
			LockObject.getWriteLock();
			result = strategiesIntraDay;
		} finally {
			LockObject.realseWriteLock();
		}
		return result;
	}

	public static synchronized void clear() {

		try {
			LockObject.getWriteLock();
			strategiesIntraDay.clear();
			strategies.clear();
		} finally {
			LockObject.realseWriteLock();
		}
	}
}
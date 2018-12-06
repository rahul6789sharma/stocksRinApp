package org.stocksrin.option.common.automation.dynamic;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;
import java.util.TreeSet;

import org.stocksrin.option.banknifty.BankNiftyData2;
import org.stocksrin.option.common.BNFStrategies;
import org.stocksrin.option.common.InMemoryStrategyies;
import org.stocksrin.option.common.priceUtils;
import org.stocksrin.option.common.model.Strategy;
import org.stocksrin.option.common.model.StrategyModel;
import org.stocksrin.option.nifty.NiftyData;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.LoggerSysOut;

public class DyanmicStrategy extends TimerTask {

	static long timeInteval = 60000;

	@Override
	public void run() {
		// StrategyModel strategy = new StrategyModel();

		if (BankNiftyData2.shortedExpiry.isEmpty() || NiftyData.shortedExpiry.isEmpty()) {
			// handle null data over weekend and after market time
			// deployment
			LoggerSysOut.print("Data loader if data is not present");
			try {
				priceUtils.fetchData();
			} catch (Exception e) {
				e.printStackTrace();
			}
			String name = "DyanmicStraddle3@INTADAY";
			Strategy strategy = null;
			try {
				strategy = BNFStrategies.StrategyATMStraddle(name);
				strategy.setDir(APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_INTRADAY_TradeStrategy);
				strategy.setFileName(name + "~AUTO-Strategy_BANKNIFTY.csv");
				String tradeDate = DateUtils.dateToString(new Date(), "ddMMMyyyy");
				strategy.setTradeDate(tradeDate);
				InMemoryStrategyies.putIntraDay(name, strategy);
				double spot = 25680;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			while (CommonUtils.getEveningTimeForStrategy()) {
				// priceUtils.fetchData();

				Double result = getNextString(BankNiftyData2.getBNFSpot(), name);

				if (result != null) {
					boolean status = isStrikePresent(result, name);
					if (!status) {
						List<StrategyModel> newModels;
						try {
							newModels = BNFStrategies.getStradelByStrikeStrategy(result);
							strategy.getStrategyModels().addAll(newModels);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				} else {
				}

				try {
					Thread.sleep(timeInteval);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private boolean isStrikePresent(double strike, String name) {
		Strategy strategy = InMemoryStrategyies.getStrategiesIntraDay().get(name);
		List<StrategyModel> strategyModels = strategy.getStrategyModels();
		for (StrategyModel strategyModel : strategyModels) {
			if (strategyModel.getStrike() == strike) {
				return true;
			}
		}
		return false;
	}

	private Double getNextString(double spot, String strategyName) {
		Strategy strategy = InMemoryStrategyies.getStrategiesIntraDay().get(strategyName);
		Set<Double> allStrikes = new TreeSet<>();

		if (strategy != null) {
			List<StrategyModel> strategyModels = strategy.getStrategyModels();

			for (StrategyModel strategyModel : strategyModels) {
				allStrikes.add(strategyModel.getStrike());
			}
		}

		Double maxStrike = Collections.max(allStrikes);
		Double minStrike = Collections.min(allStrikes);
		Double result = null;

		System.out.println("spot " + spot);
		System.out.println("maxStrike " + maxStrike);
		System.out.println("minStrike " + minStrike);
		System.out.println(Math.abs(spot - maxStrike));

		if (spot > maxStrike && Math.abs(spot - maxStrike) > 100) {
			result = maxStrike + 100;
		} else if (spot < minStrike && Math.abs(spot - minStrike) > 100) {
			result = minStrike - 100;
		} else {
			result = null;
		}

		return result;
	}

	public static void main(String[] args) throws Exception {
		DyanmicStrategy dyanmicStrategy = new DyanmicStrategy();
		dyanmicStrategy.run();
	}
}

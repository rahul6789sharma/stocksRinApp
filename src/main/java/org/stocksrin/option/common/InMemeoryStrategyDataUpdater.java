package org.stocksrin.option.common;

import java.util.List;
import java.util.Set;

import org.stocksrin.option.banknifty.BankNiftyData2;
import org.stocksrin.option.common.automation.StrategyResult;
import org.stocksrin.option.common.model.LockObject;
import org.stocksrin.option.common.model.Strategy;
import org.stocksrin.option.common.model.Strategy.UnderLying;
import org.stocksrin.option.common.model.StrategyModel;
import org.stocksrin.option.nifty.NiftyData;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.LoggerSysOut;

public class InMemeoryStrategyDataUpdater extends Thread {

	static long timeInteval = 120000;
	@Override
	public void run() {
		if (BankNiftyData2.shortedExpiry.isEmpty() || NiftyData.shortedExpiry.isEmpty()) {
			// handle null data over weekend and after market time
			// deployment
			LoggerSysOut.print("Data loader if data is not present");
			try {
				priceUtils.fetchData();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		while (CommonUtils.getEveningTimeForStrategy()) {
			try {
				try {
					LockObject.getWriteLock();

					// update option ltp and spot price
					Set<String> keys = InMemoryStrategyies.getStrategies().keySet();

					for (String string2 : keys) {
						Strategy lst = InMemoryStrategyies.getStrategies().get(string2);
						updatePrice(lst);
					}

					Set<String> keys2 = InMemoryStrategyies.getStrategiesIntraDay().keySet();
					for (String string2 : keys2) {
						Strategy lst = InMemoryStrategyies.getStrategiesIntraDay().get(string2);
						updatePrice(lst);
					}
					print();
				} finally {
					LockObject.realseWriteLock();
				}
				Thread.sleep(timeInteval);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// only write result between 3:15 to 3:35
		if (CommonUtils.isTimeLessThen(15, 35)) {
			writeResult();
		}

	}

	private synchronized void writeResult() {
		Set<String> keys = InMemoryStrategyies.getStrategiesIntraDay().keySet();
		for (String string : keys) {
			Strategy lst = InMemoryStrategyies.getStrategiesIntraDay().get(string);
			try {
				StrategyResult.writeStrategyFile2(lst);
			} catch (Exception e) {
				System.out.println("Critical error in writing Strategy result");
				e.printStackTrace();
			}

		}
	}

	private synchronized void print() throws Exception {
		Set<String> keys2 = InMemoryStrategyies.getStrategiesIntraDay().keySet();
		for (String string2 : keys2) {
			Strategy lst = InMemoryStrategyies.getStrategiesIntraDay().get(string2);
			StrategyWrite.print(lst);
		}
	}

	private synchronized void updatePrice(Strategy strategy) throws Exception {

		if (strategy.getUnderlying().equals(UnderLying.BANKNIFTY)) {
			List<StrategyModel> strategyModels = strategy.getStrategyModels();

			strategy.setUnderlying_ltp(BankNiftyData2.getBNFSpot());
			// System.out.println("----------------- " +
			// strategy.getStrategyName());

			double totalPL = 0;
			double ltp = 0;
			Double iv = null;
			String lastDataUpdateTime = "";

			for (StrategyModel strategyModel : strategyModels) {

				ltp = BankNiftyData2.getLtp(strategyModel.getStrike(), strategyModel.getType(), strategyModel.getExpiry());
				iv = BankNiftyData2.getIV(strategyModel.getStrike(), strategyModel.getType(), strategyModel.getExpiry());
				lastDataUpdateTime = BankNiftyData2.getlastDataUpdated(strategyModel.getExpiry());

				strategyModel.setLtp(ltp);
				if (iv != null) {
					strategyModel.setCurrent_IV(iv);
				}

				totalPL = totalPL + (strategyModel.getQuantity() * (strategyModel.getLtp() - strategyModel.getAvgPrice()));
			}
			updateData(strategy, lastDataUpdateTime, totalPL);

		} else if (strategy.getUnderlying().equals(UnderLying.NIFTY)) {
			List<StrategyModel> strategyModels = strategy.getStrategyModels();
			strategy.setUnderlying_ltp(NiftyData.getNFSpot());

			double totalPL = 0;
			Double iv = null;
			double ltp = 0;
			String lastDataUpdateTime = "";

			for (StrategyModel strategyModel : strategyModels) {
				ltp = NiftyData.getLtp(strategyModel.getStrike(), strategyModel.getType(), strategyModel.getExpiry());
				iv = NiftyData.getIV(strategyModel.getStrike(), strategyModel.getType(), strategyModel.getExpiry());
				strategyModel.setLtp(ltp);
				if (iv != null) {
					strategyModel.setCurrent_IV(iv);
				}

				totalPL = totalPL + (strategyModel.getQuantity() * (strategyModel.getLtp() - strategyModel.getAvgPrice()));
				lastDataUpdateTime = NiftyData.getlastDataUpdated(strategyModel.getExpiry());
			}
			updateData(strategy, lastDataUpdateTime, totalPL);
			// strategy.setTotalPL(totalPL);
		}
	}

	private void updateData(Strategy strategy, String lastDataUpdateTime, double totalPL) throws Exception {

		strategy.setDataUpdatedAt(lastDataUpdateTime);
		strategy.setTotalPL(totalPL);

		// starting
		if (strategy.getTotalPLMax() == 0 && strategy.getTotalPLMin() == 0) {
			strategy.setTotalPLMax(strategy.getTotalPL());
			strategy.setTotalPLMin(strategy.getTotalPL());
			strategy.setTotalPLMaxSpot(strategy.getUnderlying_ltp());
			strategy.setTotalPLMinSpot(strategy.getUnderlying_ltp());
			strategy.setTotalPLMaxTime(lastDataUpdateTime);
			strategy.setTotalPLMinTime(lastDataUpdateTime);

		}

		if (strategy.getTotalPL() > strategy.getTotalPLMax()) {
			strategy.setTotalPLMax(strategy.getTotalPL());
			strategy.setTotalPLMaxSpot(strategy.getUnderlying_ltp());
			strategy.setTotalPLMaxTime(lastDataUpdateTime);

		}
		if (strategy.getTotalPL() < strategy.getTotalPLMin()) {
			strategy.setTotalPLMin(strategy.getTotalPL());
			strategy.setTotalPLMinSpot(strategy.getUnderlying_ltp());
			strategy.setTotalPLMinTime(lastDataUpdateTime);
		}

	}

}

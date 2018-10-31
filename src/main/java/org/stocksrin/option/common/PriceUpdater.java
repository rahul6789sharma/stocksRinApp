package org.stocksrin.option.common;

import java.util.List;
import java.util.Map;

import org.stocksrin.option.banknifty.BankNiftyData2;
import org.stocksrin.option.common.model.OptionModle;
import org.stocksrin.option.common.model.OptionModles;
import org.stocksrin.option.common.model.StrategyModel;
import org.stocksrin.option.common.model.StrategyModel.OptionType;

public class PriceUpdater {

	public synchronized static void update(List<StrategyModel> strategyModels, String insturement, String name) {

		for (StrategyModel strategyModel : strategyModels) {
			System.out.println(getltp(strategyModel.getExpiry(), strategyModel.getStrike(), strategyModel.getType(), "BANKNIFTY"));
			
			System.out.println(TradeDB.trade.get(name));
			
		}
	}

	private static Double getltp(String expiry, double strike, OptionType optionType, String insturement) {

		OptionModles currntData = null;
		if (insturement.equalsIgnoreCase("BANKNIFTY")) {
			Map<String, OptionModles> dataModle = BankNiftyData2.bnOptionData;
			currntData = dataModle.get(expiry);
		} else if (insturement.equalsIgnoreCase("NIFTY")) {

		}

		if (currntData != null) {

			List<OptionModle> price = currntData.getOptionModle();

			if (optionType.equals(OptionType.PUT)) {

				for (OptionModle optionModle : price) {
					if (optionModle.getStrike_price().equals(strike)) {
						return optionModle.getP_ltp();
					}
				}
			} else {

				for (OptionModle optionModle : price) {
					if (optionModle.getStrike_price().equals(strike)) {
						return optionModle.getC_ltp();

					}
				}
			}

		}
		return null;
	}

}

package org.stocksrin.option.common.model;

import java.util.ArrayList;
import java.util.List;

public class Strategy {

	private String underlying;
	private List<StrategyModel> strategyModels = new ArrayList<>(2);

	public Strategy(String underlying) {
		this.underlying = underlying;
	}

	public String getUnderlying() {
		return underlying;
	}

	public void setUnderlying(String underlying) {
		this.underlying = underlying;
	}

	public List<StrategyModel> getStrategyModels() {
		return strategyModels;
	}

	public void setStrategyModels(List<StrategyModel> strategyModels) {
		this.strategyModels = strategyModels;
	}

	@Override
	public String toString() {
		return "Strategy [underlying=" + underlying + ", strategyModels=" + strategyModels + "]";
	}

}

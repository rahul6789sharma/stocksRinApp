package org.stocksrin.option.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.stocksrin.option.common.model.Strategy;

public class TradeDB {

	// name and Strategy
	public static Map<String, Strategy> trade = new ConcurrentHashMap<>();

}

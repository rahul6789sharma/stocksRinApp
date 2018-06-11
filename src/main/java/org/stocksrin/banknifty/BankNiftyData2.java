package org.stocksrin.banknifty;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.stocksrin.option.model.OptionModles;

public class BankNiftyData2 {

	// Expiry and its data
	// will store data for all expiry for current month
	private static Map<String, OptionModles> bnOptionData = new ConcurrentHashMap<>();
}

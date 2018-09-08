package org.stocksrin.option.banknifty.algo;

import java.util.HashMap;
import java.util.Map;

public class MaxMinValues {

	private String strategyID;

	public static double minProfit;
	public static double maxProfit;

	public static double bankNiftyMin;
	public static double bankNiftyMax;

	// strategy id and its value
	public static Map<String, Double> strategyMinProfit = new HashMap<>();

	public static void setMaxMinForBankNIfty(double spotPrice) {

		if (spotPrice > bankNiftyMax) {
			bankNiftyMax = spotPrice;

		}
		if (spotPrice < bankNiftyMin) {
			bankNiftyMin = spotPrice;
		}

		if (bankNiftyMin == 0) {
			bankNiftyMin = spotPrice;

		}

		if (bankNiftyMax == 0) {
			bankNiftyMax = spotPrice;

		}
	}
}

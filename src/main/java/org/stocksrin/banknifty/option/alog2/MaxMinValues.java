package org.stocksrin.banknifty.option.alog2;

import java.util.HashMap;
import java.util.Map;

public class MaxMinValues {

	private String strategyID;
	private double minProfit1;
	private double minProfit12;

	public static double minProfit;
	public static double maxProfit;

	public static double bankNiftyMin;
	public static double bankNiftyMax;

	public static double bankNiftyMinPoint;
	public static double bankNiftyMaxPoint;

	// strategy id and its value
	public static Map<String, Double> strategyMinProfit = new HashMap<>();

	public static void setMaxMinForBankNIftyPoint(double bankNiftyDiff) {

		if (bankNiftyDiff > bankNiftyMaxPoint) {
			bankNiftyMaxPoint = bankNiftyDiff;

		}
		if (bankNiftyDiff < bankNiftyMinPoint) {
			bankNiftyMinPoint = bankNiftyDiff;
		}

		if (bankNiftyMinPoint == 0) {
			bankNiftyMinPoint = bankNiftyDiff;

		}

		if (bankNiftyMaxPoint == 0) {
			bankNiftyMaxPoint = bankNiftyDiff;

		}

	}

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

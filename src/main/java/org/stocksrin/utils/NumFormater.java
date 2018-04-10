package org.stocksrin.utils;

import java.text.DecimalFormat;

public class NumFormater {

	private static DecimalFormat formatter = new DecimalFormat("000.00");
	private static DecimalFormat formatter1 = new DecimalFormat("#0.00");

	public static String formatNumber(double value) {
		return formatter.format(value);
	}
	
	public static String formatNumber1(double value) {
		return formatter1.format(value);
	}
}

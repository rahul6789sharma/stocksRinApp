package org.stocksrin.utils;

import java.text.DecimalFormat;

public class Test {

	public static void main(String[] args) {

		double a = 1.00;
		double b = 228.00;
		double c = -2.00;
		// int length = b.length();
		System.out.println(foramteTradedPrice(a));

	}

	private static String foramteTradedPrice(Double price) {
		DecimalFormat df = new DecimalFormat("#0");
		String a = df.format(price);
		if (price < 0) {
			System.out.println("negitive");

			System.out.println(a.length());

			if (a.length() == 1) {
				a = "    " + a;
			} else if (a.length() == 2) {
				a = "   " + a;
			} else if (a.length() == 3) {
				a = "  " + a;
			} else if (a.length() == 4) {
				a = " " + a;
			}
			System.out.println("final " + a.length());
			return a;

		} else {
			System.out.println("Positive");

			System.out.println(a.length());

			if (a.length() == 1) {
				a = "   " + a;
			} else if (a.length() == 2) {
				a = "  " + a;
			} else if (a.length() == 3) {
				a = " " + a;
			}
			System.out.println("final " + a.length());
			return a;
		}
	}

}

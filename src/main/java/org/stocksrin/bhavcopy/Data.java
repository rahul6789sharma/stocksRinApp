package org.stocksrin.bhavcopy;

import java.util.ArrayList;
import java.util.List;

public class Data {

	private static List<BhavForRestModle> bankNIftystocksrinOIModels = new ArrayList<>();
	private static List<BhavForRestModle> nsIftystocksrinOIModels = new ArrayList<>();

	public static synchronized List<BhavForRestModle> getBankNIftystocksrinOIModels() {
		return bankNIftystocksrinOIModels;
	}

	public static synchronized void setBankNIftystocksrinOIModels(List<BhavForRestModle> bankNIftystocksrinOIModels) {
		Data.bankNIftystocksrinOIModels = bankNIftystocksrinOIModels;
	}

	public static synchronized List<BhavForRestModle> getNsIftystocksrinOIModels() {
		return nsIftystocksrinOIModels;
	}

	public static synchronized void setNsIftystocksrinOIModels(List<BhavForRestModle> nsIftystocksrinOIModels) {
		Data.nsIftystocksrinOIModels = nsIftystocksrinOIModels;
	}

}

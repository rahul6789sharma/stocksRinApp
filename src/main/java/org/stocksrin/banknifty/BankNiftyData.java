package org.stocksrin.banknifty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import org.option.currency.models.Columns;
import org.option.currency.models.MaxPains;
import org.stocksrin.option.model.BankNiftyDailyMaxPain;
import org.stocksrin.option.model.OptionModles;
import org.stocksrin.utils.ComparatorBasedOnDate;

// data for intraday and data for interday
public class BankNiftyData {

	private BankNiftyData() {
	}
	// expiry and its dataModel live data


	private static SortedSet<String> shortedSet = new TreeSet<>(new ComparatorBasedOnDate());

	private static Map<String, OptionModles> bankNiftyCurrentTimeData = new ConcurrentHashMap<>();
	private static Map<String, Columns> bankNiftyCurrentTimeData2 = new ConcurrentHashMap<>();
	private static Map<String, MaxPains> bankNiftyMaxPainData = new ConcurrentHashMap<>();

	// simple expiry and its max pain Strike price
	private static Map<String, String> maxPain = new ConcurrentHashMap<>();

	private static Map<String, LiveMaxPainModle> maxPainSerieas = new ConcurrentHashMap<>();

	// expiry->SortedSet -- daily historical
	private static Map<String, List<BankNiftyDailyMaxPain>> maxpPainSortedData = new ConcurrentHashMap<>();

	private static List<BankNiftyDailyMaxPain> weeklyMaxPain = new ArrayList<>();

	// private static Map<String BankNiftyDailyMaxPain> bnDailyMaPain = new
	// ConcurrentHashMap<>();

	public static void addData(String expiry, LiveMaxPainModle optionAnalysisModle) {
		shortedSet.add(expiry);
		maxPainSerieas.put(expiry, optionAnalysisModle);
	}

	public static void addDate(BankNiftyDailyMaxPain bankNiftyDailyMaxPain) {
		if (BankNiftyData.getMaxpPainSortedData().containsKey(bankNiftyDailyMaxPain.getExpiry())) {
			List<BankNiftyDailyMaxPain> lst = BankNiftyData.getMaxpPainSortedData().get(bankNiftyDailyMaxPain.getExpiry());
			lst.add(bankNiftyDailyMaxPain);
		} else {
			List<BankNiftyDailyMaxPain> lst = new ArrayList<>();
			lst.add(bankNiftyDailyMaxPain);
			BankNiftyData.getMaxpPainSortedData().put(bankNiftyDailyMaxPain.getExpiry(), lst);
		}
	}

	public static void dailyMorningCleanData() {
		bankNiftyCurrentTimeData.clear();
		bankNiftyCurrentTimeData2.clear();
		bankNiftyMaxPainData.clear();
		maxPain.clear();
		maxPainSerieas.clear();
		maxpPainSortedData.clear();
	}

	public static void addData(String expiry , String maxPainData) {
		shortedSet.add(expiry);
		maxPain.put(expiry, maxPainData);
	}

	public static void addData(String expiry, Columns columns) {
		shortedSet.add(expiry);
		bankNiftyCurrentTimeData2.put(expiry, columns);
	}

	public static void addData(String expiry, MaxPains maxPains) {
		shortedSet.add(expiry);
		bankNiftyMaxPainData.put(expiry, maxPains);
	}

	public static Map<String, LiveMaxPainModle> getMaxPainSerieas() {
		return maxPainSerieas;
	}

	public static void setMaxPainSerieas(Map<String, LiveMaxPainModle> maxPainSerieas) {
		BankNiftyData.maxPainSerieas = maxPainSerieas;
	}

	public static Map<String, Columns> getBankNiftyCurrentTimeData2() {
		return bankNiftyCurrentTimeData2;
	}

	public static void setBankNiftyCurrentTimeData2(Map<String, Columns> bankNiftyCurrentTimeData2) {
		BankNiftyData.bankNiftyCurrentTimeData2 = bankNiftyCurrentTimeData2;
	}

	public static Map<String, MaxPains> getBankNiftyMaxPainData() {
		return bankNiftyMaxPainData;
	}

	public static void setBankNiftyMaxPainData(Map<String, MaxPains> bankNiftyMaxPainData) {
		BankNiftyData.bankNiftyMaxPainData = bankNiftyMaxPainData;
	}

	public static void addData(String expiry, OptionModles optionModles) {
		bankNiftyCurrentTimeData.put(expiry, optionModles);
	}

	public static Map<String, OptionModles> getBankNiftyCurrentTimeData() {
		return bankNiftyCurrentTimeData;
	}

	public static void setBankNiftyCurrentTimeData(Map<String, OptionModles> bankNiftyCurrentTimeData) {
		BankNiftyData.bankNiftyCurrentTimeData = bankNiftyCurrentTimeData;
	}

	public static Map<String, String> getMaxPain() {
		return maxPain;
	}

	public static void setMaxPain(Map<String, String> maxPain) {
		BankNiftyData.maxPain = maxPain;
	}

	public static Map<String, List<BankNiftyDailyMaxPain>> getMaxpPainSortedData() {
		return maxpPainSortedData;
	}

	public static void setMaxpPainSortedData(Map<String, List<BankNiftyDailyMaxPain>> maxpPainSortedData) {
		BankNiftyData.maxpPainSortedData = maxpPainSortedData;
	}

	public static SortedSet<String> getShortedSet() {
		return shortedSet;
	}

	public static void setShortedSet(SortedSet<String> shortedSet) {
		BankNiftyData.shortedSet = shortedSet;
	}

	public static List<BankNiftyDailyMaxPain> getWeeklyMaxPain() {
		return weeklyMaxPain;
	}

	public static void setWeeklyMaxPain(List<BankNiftyDailyMaxPain> weeklyMaxPain) {
		BankNiftyData.weeklyMaxPain = weeklyMaxPain;
	}

}
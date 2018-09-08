package org.stocksrin.option.banknifty;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import org.stocksrin.option.common.model.DailyMaxPain;
import org.stocksrin.option.common.model.OptionModle;
import org.stocksrin.option.common.model.OptionModles;
import org.stocksrin.utils.ComparatorBasedOnDate;

public class BankNiftyData2 {

	public static SortedSet<String> shortedExpiry = new TreeSet<>(new ComparatorBasedOnDate());
	// Expiry and its data
	// will store data for all expiry for current month

	// expiry And its live option chain
	public static Map<String, OptionModles> bnOptionData = new ConcurrentHashMap<>();

	// expiry and its maxpain
	public static Map<String, Double> maxPains = new HashMap<>();

	// Weekly Option Chain by date
	public static Map<String, OptionModles> bnWeeklyOptionChain = new ConcurrentHashMap<>();

	// for testing only todays date-> its current week expiry
	public static Map<String, OptionModles> bnWeeklyOptionChain2 = new ConcurrentHashMap<>();

	// data will be only updated on any change in strike price
	public static Map<String, List<OptionModle>> bnOptionLiveNotificationChnageData = new ConcurrentHashMap<>();

	// Date and its dailyMaixPain
	public static Map<String, DailyMaxPain> dailyMaxPain = new LinkedHashMap<>();

	public static void clear() {
		// LoggerSysOut.print("Clearing All BN Data");
		shortedExpiry.clear();
		bnOptionData.clear();
		maxPains.clear();
		bnWeeklyOptionChain.clear();
		bnWeeklyOptionChain2.clear();
		bnOptionLiveNotificationChnageData.clear();
	}
}

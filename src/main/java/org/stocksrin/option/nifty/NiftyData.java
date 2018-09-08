package org.stocksrin.option.nifty;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import org.stocksrin.option.common.model.OptionModles;
import org.stocksrin.utils.ComparatorBasedOnDate;

public class NiftyData {

	public static SortedSet<String> shortedExpiry = new TreeSet<>(new ComparatorBasedOnDate());
	
	// Current expiry And its live optionChain
	public static Map<String, OptionModles> optionData = new ConcurrentHashMap<>();

	// expiry and its maxpain
	public static Map<String, Double> maxPains = new HashMap<>();

	public static void clear() {
		//LoggerSysOut.print("Clearing All BN Data");
		shortedExpiry.clear();
		optionData.clear();
		maxPains.clear();

	}
}

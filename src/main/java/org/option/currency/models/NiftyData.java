package org.option.currency.models;

import java.util.concurrent.ConcurrentHashMap;

import org.stocksrin.option.common.model.OptionModles;

public class NiftyData {

	// map of Expire Date and option chain for that particular Expiry 
	private static ConcurrentHashMap<String, OptionModles>  data = new ConcurrentHashMap<>();

	public static ConcurrentHashMap<String, OptionModles> getData() {
		return data;
	}

	public static void setData(ConcurrentHashMap<String, OptionModles> data) {
		NiftyData.data = data;
	}
	
}

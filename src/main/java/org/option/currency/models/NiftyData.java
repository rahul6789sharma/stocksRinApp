package org.option.currency.models;

import java.util.concurrent.ConcurrentHashMap;

public class NiftyData {

	// map of Expire Date and option chain for that particular Expiry 
	private static ConcurrentHashMap<String, Columns>  data = new ConcurrentHashMap<String, Columns>();

	public static ConcurrentHashMap<String, Columns> getData() {
		return data;
	}

	public static void setData(ConcurrentHashMap<String, Columns> data) {
		NiftyData.data = data;
	}
	
}

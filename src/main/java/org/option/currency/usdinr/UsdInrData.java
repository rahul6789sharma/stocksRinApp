package org.option.currency.usdinr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.option.currency.models.Columns;

public class UsdInrData {

	private static List<String> expiry = new ArrayList<String>(Arrays.asList("29MAY2017", "28JUN2017", "27JUL2017"));
	// map of Expire Date and option chain for that particular Expire
	private static ConcurrentHashMap<String, Columns> data = new ConcurrentHashMap<>();

	public static ConcurrentHashMap<String, Columns> getData() {
		return data;
	}

	public static void setData(ConcurrentHashMap<String, Columns> data) {
		UsdInrData.data = data;
	}

	public static List<String> getExpiry() {
		return expiry;
	}

	public static void setExpiry(List<String> expiry) {
		UsdInrData.expiry = expiry;
	}
}

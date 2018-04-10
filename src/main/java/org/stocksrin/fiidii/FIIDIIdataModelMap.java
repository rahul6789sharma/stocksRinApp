package org.stocksrin.fiidii;

import java.util.LinkedHashMap;
import java.util.Map;

public class FIIDIIdataModelMap {
	
	private FIIDIIdataModelMap() {

	}

	private static Map<String, FIIDIIDataModle> fIIDIIDataModleData = new LinkedHashMap<>();

	public static void addData(String date, FIIDIIDataModle fIIDIIDataModle) {
		fIIDIIDataModleData.put(date, fIIDIIDataModle);

	}

	public static Map<String, FIIDIIDataModle> getfIIDIIDataModleData() {
		return fIIDIIDataModleData;
	}

	public static void setfIIDIIDataModleData(Map<String, FIIDIIDataModle> fIIDIIDataModleData) {
		FIIDIIdataModelMap.fIIDIIDataModleData = fIIDIIDataModleData;
	}

}
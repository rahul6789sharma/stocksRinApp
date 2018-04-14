package org.stocksrin.fiidii;

import java.util.LinkedHashMap;
import java.util.Map;

public class FIIDIIdataModelMap {

	private FIIDIIdataModelMap() {
	}

	// private static List<Map<String, FIIDIIDataModle>> monthlyData = new
	// ArrayList<>();

	private static Map<String, FIIDIIDataModle> fIIDIIPreviousMOnthData = new LinkedHashMap<>();

	// current month data
	private static Map<String, FIIDIIDataModle> fIIDIIDataModleData = new LinkedHashMap<>();

	public static void addPreviousMonthData(String date, FIIDIIDataModle fIIDIIDataModle) {
		fIIDIIPreviousMOnthData.put(date, fIIDIIDataModle);
	}

	public static void addData(String date, FIIDIIDataModle fIIDIIDataModle) {
		fIIDIIDataModleData.put(date, fIIDIIDataModle);
	}

	public static Map<String, FIIDIIDataModle> getfIIDIIDataModleData() {
		return fIIDIIDataModleData;
	}

	public static void setfIIDIIDataModleData(Map<String, FIIDIIDataModle> fIIDIIDataModleData) {
		FIIDIIdataModelMap.fIIDIIDataModleData = fIIDIIDataModleData;
	}

	public static Map<String, FIIDIIDataModle> getfIIDIIPreviousMOnthData() {
		return fIIDIIPreviousMOnthData;
	}

	public static void setfIIDIIPreviousMOnthData(Map<String, FIIDIIDataModle> fIIDIIPreviousMOnthData) {
		FIIDIIdataModelMap.fIIDIIPreviousMOnthData = fIIDIIPreviousMOnthData;
	}

	/*
	 * public static List<Map<String, FIIDIIDataModle>> getMonthlyData() {
	 * return monthlyData; }
	 * 
	 * public static void setMonthlyData(List<Map<String, FIIDIIDataModle>>
	 * monthlyData) { FIIDIIdataModelMap.monthlyData = monthlyData; }
	 */

}
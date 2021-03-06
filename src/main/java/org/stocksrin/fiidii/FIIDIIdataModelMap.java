package org.stocksrin.fiidii;

import java.util.LinkedHashMap;
import java.util.Map;

public class FIIDIIdataModelMap {

	private FIIDIIdataModelMap() {
	}

	private static Map<String, FIIDIIDataYrModle> fIIDIIYearlyData = new LinkedHashMap<>();

	private static Map<String, FIIDIIDataModle> fIIDIIPreviousMOnthData = new LinkedHashMap<>();

	// current month data
	private static Map<String, FIIDIIDataModle> fIIDIIDataModleData = new LinkedHashMap<>();

	public static void addYearlyData(String date, FIIDIIDataYrModle fiiDIIDataYrModle) {
		fIIDIIYearlyData.put(date, fiiDIIDataYrModle);
	}

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

	public static Map<String, FIIDIIDataYrModle> getfIIDIIYearlyData() {
		return fIIDIIYearlyData;
	}

	public static void setfIIDIIYearlyData(Map<String, FIIDIIDataYrModle> fIIDIIYearlyData) {
		FIIDIIdataModelMap.fIIDIIYearlyData = fIIDIIYearlyData;
	}

	/*
	 * public static List<Map<String FIIDIIDataModle>> getMonthlyData() {
	 * return monthlyData; }
	 * 
	 * public static void setMonthlyData(List<Map<String FIIDIIDataModle>>
	 * monthlyData) { FIIDIIdataModelMap.monthlyData = monthlyData; }
	 */

}
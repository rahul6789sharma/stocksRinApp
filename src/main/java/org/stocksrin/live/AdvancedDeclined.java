package org.stocksrin.live;

import java.util.ArrayList;
import java.util.List;

public class AdvancedDeclined {

	private static List<String> advanced = new ArrayList<>();
	private static List<String> declined = new ArrayList<>();

	static {
		advanced.add("1");
		advanced.add("2");
		advanced.add("3");
		advanced.add("4");
		advanced.add("5");
		advanced.add("6");
		advanced.add("7");
		advanced.add("8");
		advanced.add("9");
		advanced.add("10");
		advanced.add("11");
		advanced.add("12");
		advanced.add("13");
		advanced.add("14");
		advanced.add("15");
		advanced.add("16");
		advanced.add("17");
		advanced.add("18");
		advanced.add("19");
		advanced.add("20");
		advanced.add("21");
		advanced.add("22");
		advanced.add("23");
		advanced.add("24");
		advanced.add("25");
		advanced.add("26");
		advanced.add("27");
		advanced.add("28");
		advanced.add("29");
		
		declined.add("25");
		declined.add("24");
		declined.add("23");
		declined.add("22");
		declined.add("21");
		declined.add("20");
		declined.add("19");
		declined.add("18");
		declined.add("17");
		declined.add("16");
		declined.add("15");
		declined.add("14");
		declined.add("13");
		declined.add("12");
		declined.add("11");
		declined.add("10");
		declined.add("9");
		declined.add("8");
		declined.add("7");
		declined.add("6");
		declined.add("5");
		declined.add("4");
		declined.add("3");
		declined.add("2");
		declined.add("1");
	}

	public static void addAdvanced(String value) {
		advanced.add(value);
	}

	public static void addDeclined(String value) {
		declined.add(value);
	}

	public static void creaAall() {
		advanced.clear();
		declined.clear();
	}

	public static List<String> getAdvanced() {
		return advanced;
	}

	public static List<String> getDeclined() {
		return declined;
	}

}

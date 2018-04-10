package org.stocksrin.websockets;

import java.util.LinkedHashMap;
import java.util.Map;

public class Data {
	
	private static Map<Integer, String> map = new LinkedHashMap<>();

	public static Map<Integer, String> getMap() {
		return map;
	}

	public static void setMap(Map<Integer, String> map) {
		Data.map = map;
	}
	
	
	public static String getData(int i){
		StringBuilder string = new StringBuilder();
		string.append("                              BankNifty Spot : " + 0 + " [" + 0 + "] " + "\n");
		string.append("-------------------------------------------------------------------" + "\n");
		string.append("type     sell     ltp     change    P&L     strike    strikeDiff" + "\n");
		string.append("-------------------------------------------------------------------" + "\n");
		string.append("PUT      " + i + "     " + i + "    " + i+ "    " + i + "      " + i+ "    "
				+ 0 + "\n");
		string.append("CALL     " + i + "     " +i + "    " + i + "    " + i+ "      " +i + "    "
				+0 + "\n");
		string.append("-------------------------------------------------------------------" + "\n");
		string.append(
				"Total    " + i + "    " + i+ "    " + i + "    [" + i + "]" + "   " + "P&LHighLow " + i + "\n");
		string.append("-------------------------------------------------------------------" + "\n");

		System.out.println(string);
		return string.toString();
	}

}

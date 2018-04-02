package org.stocksrin.banknifty;

import java.util.Date;

import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.DateUtils;

class Test {

	public static void main(String ar[]) {

		String underlyingSpotPrice="Underlying Index: BANKNIFTY 24902.55  As on Mar 01, 2018 15:30:30 IST";
		String a[]=underlyingSpotPrice.split("As on");

		
		String dateInString= a[1].trim();
		
		String dateForamte="MMM dd, yyyy hh:mm:ss Z";
		try {
			Date date =DateUtils.stringToDate(dateInString, dateForamte);
			String dateString=DateUtils.dateToString(date, APPConstant.DATEFORMATE_dd_MM_yyyy);
			System.out.println(dateString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
}
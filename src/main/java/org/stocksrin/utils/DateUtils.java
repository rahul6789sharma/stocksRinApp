package org.stocksrin.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private DateUtils() {
	}

	public static String dateToString(Date date, String formate) throws Exception {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(formate);
			return formatter.format(date);
		} catch (Exception e) {
			throw new Exception("ERROR! dateToString " + e.getMessage());
		}

	}

	public static Date stringToDate(String sDate, String formate) throws Exception {
		try {
			return new SimpleDateFormat(formate).parse(sDate);
		} catch (Exception e) {
			throw new Exception("ERROR! StringToDate " + e.getMessage());
		}

	}

	public static String getDateOutofFileName(String fileName) {
		int last = fileName.lastIndexOf(".csv");
		return fileName.substring(25, last).trim();
	}

	public static Date getActualDateOutofFileName(String fileName) throws Exception {
		String stringDate = getDateOutofFileName(fileName);
		return stringToDate(stringDate, APPConstant.DATEFORMATE_dd_MM_yyyy);
	}

}

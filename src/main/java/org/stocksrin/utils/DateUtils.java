package org.stocksrin.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {


	private DateUtils() {
	}


	public static String getCurrentMonth() {
		SimpleDateFormat format = new SimpleDateFormat("MMM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 0);
		return format.format(cal.getTime());
	}
	
	public static String getCurrentYear() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 0);
		return format.format(cal.getTime());
	}
	
	public static String getPreviousMonth(int previousMonth) {
		SimpleDateFormat format = new SimpleDateFormat("MMM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, previousMonth);
		return format.format(cal.getTime());
	}

	public static int getExpiryWeekOfMonth(String expiry) throws Exception {

		String formate = "ddMMMyyyy";
		Date date = stringToDate(expiry, formate);

		String d = dateToString(date, "dd");

		Integer date1 = Integer.parseInt(d);
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		now.set(Calendar.DATE, date1);
		return now.get(Calendar.WEEK_OF_MONTH);
	}

	public static String previousDayDate(String formate) {
		DateFormat dateFormat = new SimpleDateFormat(formate);
		return dateFormat.format(yesterday());
	}

	private static Date yesterday() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	public static boolean isWeekEndDay() {
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		if (now.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || now.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		} else {
			return false;
		}
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

	public static String getTodayString() {
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("IST"));

		int i = now.get(Calendar.DAY_OF_WEEK);
		System.out.println(i);
		if (i == 1) {
			return "SUNDAY";
		} else if (i == 2) {
			return "MONDAY";
		} else if (i == 3) {
			return "TUESDAY";
		} else if (i == 4) {
			return "WEDNESDAY";
		} else if (i == 5) {
			return "THURSDAY";
		} else if (i == 6) {
			return "FRIDAY";
		} else if (i == 7) {
			return "SATURDAY";
		}

		return null;
	}

	public static void main(String[] args) {
		System.out.println(getCurrentYear());
	}
}

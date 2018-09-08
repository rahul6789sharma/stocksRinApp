package org.stocksrin.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

	public static void main(String[] args) {
		// LoggerSysOut.print(getCurrentYear());
		try {
			String date = dateToString(new Date(), "ddMMMyyyy");
			System.out.println(date);
			System.out.println(getDayFromDate(date, "ddMMMyyyy"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getDayFromDate(String input_date, String formate) throws Exception {

		SimpleDateFormat format1 = new SimpleDateFormat(formate);
		Date dt1 = format1.parse(input_date);
		DateFormat format2 = new SimpleDateFormat("EEEE");
		return format2.format(dt1);

	}

	private DateUtils() {
	}

	public static String getCurrentDay() {
		SimpleDateFormat format = new SimpleDateFormat("EEE");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 0);
		return format.format(cal.getTime()).toUpperCase();
	}

	public static String getCurrentDatetwoDigit() {
		SimpleDateFormat format = new SimpleDateFormat("dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 0);
		 return format.format(cal.getTime()).toUpperCase();
		//return "12";
	}

	public static String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("d");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 0);
		return format.format(cal.getTime()).toUpperCase();
	}

	public static String getFullCurrentMonth() {
		SimpleDateFormat format = new SimpleDateFormat("MMMM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 0);
		return format.format(cal.getTime());
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

	public static int getCurrentWeek() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.WEEK_OF_MONTH);
	}

	public static String getPreviousMonth(int previousMonth) {
		SimpleDateFormat format = new SimpleDateFormat("MMM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, previousMonth);
		return format.format(cal.getTime());
	}

	public static int getWeekOfMonth(String expiry) throws Exception {

		String formate = "ddMMMyyyy";
		Date date = stringToDate(expiry, formate);

		String d = dateToString(date, "dd");

		Integer date1 = Integer.parseInt(d);
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		now.set(Calendar.DATE, date1);
		return now.get(Calendar.WEEK_OF_MONTH);
	}

	public static int getNumberOfWeekInMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);

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
			formatter.setTimeZone(TimeZone.getTimeZone("IST"));
			return formatter.format(date);
		} catch (Exception e) {
			throw new Exception("ERROR! dateToString " + e.getMessage());
		}

	}

	public static Date stringToDate(String sDate, String formate) throws Exception {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(formate);
			formatter.setTimeZone(TimeZone.getTimeZone("IST"));
			return formatter.parse(sDate);
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
		LoggerSysOut.print(i);
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

	public static String getMonth(String s) throws Exception {
		int month = Integer.parseInt(s);
		if (month == 1) {
			return "Jan";
		} else if (month == 2) {
			return "Feb";
		} else if (month == 3) {
			return "Mar";
		} else if (month == 4) {
			return "Apr";
		} else if (month == 5) {
			return "May";
		} else if (month == 6) {
			return "Jun";
		} else if (month == 7) {
			return "Jul";
		} else if (month == 8) {
			return "Aug";
		} else if (month == 9) {
			return "Sep";
		} else if (month == 10) {
			return "Oct";
		} else if (month == 11) {
			return "Nov";
		} else if (month == 12) {
			return "Dec";
		} else {
			throw new Exception("Error Month Out of range");
		}

	}

	public static long getEpocTime(String dateString) throws ParseException {
		// String str = "13-Jul-2018 15:30 UTC";
		String str = dateString + " 15:30 UTC";
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm zzz");
		Date date = df.parse(str);
		long epoch = date.getTime();
		return epoch;
	}
}

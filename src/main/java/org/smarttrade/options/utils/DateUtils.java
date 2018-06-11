package org.smarttrade.options.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.stocksrin.utils.LoggerSysOut;

public class DateUtils {
	
	private static long priceUpdatepriod=60l; // in minitues
	private static DateUtils instance = new DateUtils();
	

	public static DateUtils getInstance() {
		return instance;
	}

	private DateUtils(){
		
	}

	public static String getDateString(String expiryDate) {
		String oldstring = expiryDate + " 17:30:00";
		Date date = null;
		try {
			date = new SimpleDateFormat("ddMMMyyyy HH:mm:ss").parse(oldstring);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// expiry = "2017-05-02 23:59:00"
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss"); // Or
																			// whatever
																			// format
																			// fits
																			// best
																			// your
																			// needs.
		String dateStr = sdf.format(date);

		return dateStr;
	}

	public static String getTodayDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyyyy");
		String dateStr = sdf.format(date).toUpperCase();
		// 02May2017
		return dateStr;
	}
	//2017-05-16 00:00:00.0

	public String getDateStringBYDate(Date date){
		 
		String newstring = new SimpleDateFormat("ddMMMyyyy").format(date);
		return newstring.toUpperCase();
	}
	
	public static void main(String[] args) {
		
		 DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		 Date myDate = new Date();
		 java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
		LoggerSysOut.print(sqlDate);
	}

	public static String getTodayDateTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss z");
		sdf.setTimeZone(TimeZone.getTimeZone("IST"));
		String dateStr = sdf.format(date).toUpperCase();
		return dateStr;
	}
	
	public static Date getSqlDateFromString(String dateString) {
		// Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("IST"));
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return date;
	}
	
	public static Date getDateFromString(String dateString) {
		// Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("IST"));
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return date;
	}

	private static Date getTodayDateTime(String dateString) {
		// Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss z");
		sdf.setTimeZone(TimeZone.getTimeZone("IST"));
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return date;
	}

	private static long getTimeDifferenceInMinitues(String dateString) {
		TimeZone.setDefault(TimeZone.getTimeZone("IST"));
		Date startDate = DateUtils.getTodayDateTime(dateString);
		Date now = new Date();
		long differenceInMinitues = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - startDate.getTime());
		LoggerSysOut.print(differenceInMinitues);
		return differenceInMinitues;
	}

	/**
	 * check for logic data have to update or not
	 * 
	 * @param lastUpdatedtime
	 * @return
	 */
	public boolean featchData(String lastUpdatedtime, long closingTime) {

		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("IST"));

		if (now.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || now.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			LoggerSysOut.print("featchData Request but today is WeekEnds so returning false");

			return false;
		} else {
			if (now.get(Calendar.HOUR_OF_DAY) > closingTime || now.get(Calendar.HOUR_OF_DAY) < 9) {
				// update only in market hrs
				LoggerSysOut.print("Market is closed returning false");
				return false;
			} else {
				long timeDifference = getTimeDifferenceInMinitues(lastUpdatedtime);
				if (timeDifference >= priceUpdatepriod) {
					LoggerSysOut.print("Update Data Requested data was updated on " + lastUpdatedtime + "  So returning true");
					LoggerSysOut.print("time difference in minuets is more then 60 min  :" + timeDifference);
					return true;
				} else {
					LoggerSysOut.print("time difference in minuets is less then 60 min  :" + timeDifference);
					return false;
				}
			}
		}
	}

}
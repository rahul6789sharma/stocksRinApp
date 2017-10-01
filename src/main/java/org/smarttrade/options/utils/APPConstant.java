package org.smarttrade.options.utils;

public class APPConstant {

	public static String NSE_URL_INIT="https://www.nseindia.com/live_market/dynaContent/live_watch/fxTracker/optChainDataByExpDates.jsp";
	private final static String NSE_URL = "https://www.nseindia.com/live_market/dynaContent/live_watch/fxTracker/optChainDataByExpDates.jsp?symbol=USDINR&instrument=OPTCUR&expiryDt=";
	
	//private final static String NSE_FUTURE_URL = "https://www.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuoteCID.jsp?underlying=USDINR&instrument=FUTCUR&expiry=28JUN2017&key=FUTCURUSDINR28JUN2017--02MAY2017";
	private final static String NSE_FUTURE_URL = "https://www.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuoteCID.jsp?underlying=USDINR&instrument=FUTCUR&expiry=";
	
	
	public static String getUSDINRFutureURL(String expiryDate){
		String url=NSE_FUTURE_URL+expiryDate+"&key=FUTCURUSDINR"+expiryDate+ "--" +DateUtils.getTodayDate();
		return url;
	}
	public static String getUSDIINROptionChainURL(String expiryDate) {
		String url = NSE_URL + expiryDate;
		return url;
	}
	
}

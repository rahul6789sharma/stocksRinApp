package org.stocksrin.utils;

import java.io.File;

public class APPConstant {

	private APPConstant() {

	}

	public static final String NSE_bhavdata_URL = "https://www.nseindia.com/products/content/sec_bhavdata_full.csv";

	private static final String STOCKSRIN_NSE_CONF_DIR = System.getProperty("user.home") + File.separator + "stocksRin_CONF" + File.separator;
	public static final String STOCKSRIN_NSE_CONF_DIR_BHAVDIR = STOCKSRIN_NSE_CONF_DIR + File.separator + "nseOriginalData" + File.separator;
	public static final String STOCKSRIN_NSE_CONF_DIR_StocksRInData = STOCKSRIN_NSE_CONF_DIR + File.separator + "stocksRinData" + File.separator;
	
	public static final String STOCKSRIN_NSE_CONF_DIR_BANKNIFTY = STOCKSRIN_NSE_CONF_DIR + File.separator + "bankNifty" + File.separator;
	
	public static final String FILE_NAME_BANKNIFTY_OPTION = "option_";
	
	public static final String FILE_NAME_updatedBhavCopy = "updatedBhavCopy.xlsx";
	
	public static final String DATEFORMATE_dd_MM_yyyy = "dd_MM_yyyy";
	
	public static final String FILE_NAME_FII = STOCKSRIN_NSE_CONF_DIR_StocksRInData+"FII.csv";
	public static final String FILE_NAME_DII = STOCKSRIN_NSE_CONF_DIR_StocksRInData+"DII.csv";
	public static final String FILE_NAME_FII_DII = STOCKSRIN_NSE_CONF_DIR_StocksRInData+"FII_DII.csv";
	
	public static final String FILE_NAME_DERIVATIVES = STOCKSRIN_NSE_CONF_DIR_StocksRInData+"DERIVATIVES.csv";
	
	public static final String FII_DATA_URL = "https://www.nseindia.com/products/dynaContent/equities/equities/htms/fiiEQ.htm";
	public static final String DII_DATA_URL = "https://www.nseindia.com/products/dynaContent/equities/equities/htms/DiiEQ.htm";
	
	public static final String FIIDII_DERIVATIVES_DATA_URL ="https://www.fpi.nsdl.co.in/web/Reports/Latest.aspx";
	
	public static final String GOOGLEURL ="https://finance.google.com/finance?q=NSE:NIFTY&output=json";
	
	public static final String  NIFTY_INDICES="https://nseindia.com/homepage/Indices1.json";
	
	
	public static final String stocksList="stocks.txt";

	public static final String BANKNIFTY_WEEKLY_OPTION_URL = "https://www.nseindia.com/live_market/dynaContent/live_watch/option_chain/optionKeys.jsp?symbolCode=-9999&symbol=BANKNIFTY&symbol=BANKNIFTY&instrument=-&date=-&segmentLink=17&symbolCount=2&segmentLink=17";


}

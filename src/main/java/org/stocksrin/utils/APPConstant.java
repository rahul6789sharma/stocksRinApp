package org.stocksrin.utils;

import java.io.File;

public class APPConstant {

	private APPConstant() {

	}

	public static double BNF_STRIKE_DIFF = 100d;
	public static double NF_STRIKE_DIFF = 50d;
	
	public static final String NSE_DERIVATIVES_OI_URL = "https://www.nseindia.com/content/historical/DERIVATIVES/";
	public static final String NSE_bhavdata_URL = "https://www.nseindia.com/products/content/sec_bhavdata_full.csv";

	private static final String STOCKSRIN_NSE_CONF_DIR = System.getProperty("user.home") + File.separator + "stocksRin_CONF" + File.separator;
	public static final String STOCKSRIN_NSE_CONF_DIR_BHAVDIR = STOCKSRIN_NSE_CONF_DIR + File.separator + "nseOriginalData" + File.separator;
	public static final String STOCKSRIN_NSE_CONF_DIR_StocksRInData = STOCKSRIN_NSE_CONF_DIR + File.separator + "stocksRinData" + File.separator;
	public static final String STOCKSRIN_NSE_CONF_DIR_DERIVATIVES_OI = STOCKSRIN_NSE_CONF_DIR + File.separator+"DERIVATIVES_OI"+File.separator;

	public static final String STOCKSRIN_NSE_CONF_DIR_BANKNIFTY = STOCKSRIN_NSE_CONF_DIR + "bankNifty" + File.separator;
	public static final String STOCKSRIN_INDICES_NIFTY = STOCKSRIN_NSE_CONF_DIR_StocksRInData + "indices" + File.separator+"Nifty.csv";
	public static final String STOCKSRIN_INDICES_BANK_NIFTY = STOCKSRIN_NSE_CONF_DIR_StocksRInData + "indices" + File.separator+"BankNifty.csv";

	public static final String STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_TRADE_FILE = STOCKSRIN_NSE_CONF_DIR + "bankNifty" + File.separator + "trade.csv";

	public static final String STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_BNIFTY_STRATEGY_DIR = STOCKSRIN_NSE_CONF_DIR + "bankNifty" + File.separator + "Strategy" + File.separator;
	public static final String STOCKSRIN__STRATEGY_DIR = STOCKSRIN_NSE_CONF_DIR_StocksRInData + "Strategy" + File.separator;
	public static final String STOCKSRIN__STRATEGY_AUTO_DIR = STOCKSRIN_NSE_CONF_DIR_StocksRInData + "Strategy_AutoMated" + File.separator;
	public static final String STOCKSRIN__STRATEGY_DIR_RESULT = STOCKSRIN_NSE_CONF_DIR_StocksRInData + "Strategy_Result" + File.separator;
	public static final String STOCKSRIN__STRATEGY_AUTO_DIR_RESULT = STOCKSRIN_NSE_CONF_DIR_StocksRInData + "Strategy_AutoMated_Result" + File.separator;
	//public static final String STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_OUT_FILE = STOCKSRIN_NSE_CONF_DIR + "bankNifty" + File.separator + "BankNiftyOptionPrice.csv";
	
	public static final String STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_DAILYMAXPAIN_FILE = STOCKSRIN_NSE_CONF_DIR + "bankNifty" + File.separator + "BankNiftyWeeklyExpiryMaxPain.csv";
	public static final String STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_DAILYMAXPAIN_FILE_Previous1 = STOCKSRIN_NSE_CONF_DIR + "bankNifty" + File.separator + "BankNiftyWeeklyExpiryMaxPain_PreviousWeek1.csv";
	public static final String STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_DAILYMAXPAIN_FILE_Previous2 = STOCKSRIN_NSE_CONF_DIR + "bankNifty" + File.separator + "BankNiftyWeeklyExpiryMaxPain_PreviousWeek2.csv";
	
	public static final String FILE_NAME_updatedBhavCopy = "updatedBhavCopy.xlsx";

	public static final String DATEFORMATE_Logger = "dd-MMM hh:mm:ss";

	public static final String DATEFORMATE_BN_EXPIRY = "ddMMMyyyy";
	public static final String DATEFORMATE_dd_MM_yyyy = "dd_MM_yyyy";
	public static final String DATEFORMATE_dd_MMM_yyyy = "dd_MMM_yyyy";
	public static final String FILE_NAME_FII_DIR = STOCKSRIN_NSE_CONF_DIR_StocksRInData + "FII_DII_BUY_SELL" + File.separator;
	public static final String FILE_NAME_FII_DIR_BACKUP = FILE_NAME_FII_DIR + "backup" + File.separator;
	public static final String FILE_NAME_FII_DIR_MONTHLY = FILE_NAME_FII_DIR + "monthly" + File.separator;

	public static final String FILE_NAME_FII_DIR_YEARLY = FILE_NAME_FII_DIR + "yearly" + File.separator;

	public static final String FILE_NAME_NIFTY_OI_FILE = STOCKSRIN_NSE_CONF_DIR_StocksRInData + "niftyOI" + File.separator + "NiftyOI.csv";
	public static final String FO_OI_DIR = STOCKSRIN_NSE_CONF_DIR_StocksRInData + File.separator + "Participant_OI_Data" + File.separator + "NSE_FO_OI" + File.separator;
	public static final String FUTURE_OI_Participant_FILE = STOCKSRIN_NSE_CONF_DIR_StocksRInData + File.separator + "Participant_OI_Data" + File.separator + "FUTURE_INDEX" + File.separator
			+ "FUTURE_OI.csv";
	public static final String OPTION_OI_Participant_FILE = STOCKSRIN_NSE_CONF_DIR_StocksRInData + File.separator + "Participant_OI_Data" + File.separator + "OPTION_INDEX" + File.separator
			+ "OPTION_OI.csv";

	public static final String FILE_NAME_DERIVATIVES = STOCKSRIN_NSE_CONF_DIR_StocksRInData + "DERIVATIVES.csv";

	public static final String FII_DATA_URL = "https://www.nseindia.com/products/dynaContent/equities/equities/htms/fiiEQ.htm";
	public static final String DII_DATA_URL = "https://www.nseindia.com/products/dynaContent/equities/equities/htms/DiiEQ.htm";

	public static final String FIIDII_DERIVATIVES_DATA_URL = "https://www.fpi.nsdl.co.in/web/Reports/Latest.aspx";

	public static final String GOOGLEURL = "https://finance.google.com/finance?q=NSE:NIFTY&output=json";

	public static final String NIFTY_INDICES = "https://nseindia.com/homepage/Indices1.json";

	public static final String stocksList = "stocks.txt";
	public static final String Live_Market_URL = "https://www.nseindia.com/live_market/dynaContent/live_analysis/changePercentage.json";

	public static final String BANKNIFTY_WEEKLY_OPTION_URL = "https://www.nseindia.com/live_market/dynaContent/live_watch/option_chain/optionKeys.jsp?symbolCode=-9999&symbol=BANKNIFTY&symbol=BANKNIFTY&instrument=-&date=-&segmentLink=17&symbolCount=2&segmentLink=17";

	public static final String BANKNIFTY_WEEKLY_OPTION_URL_BY_Expiry = "https://www.nseindia.com/live_market/dynaContent/live_watch/option_chain/optionKeys.jsp?segmentLink=17&instrument=OPTIDX&symbol=BANKNIFTY&date=";

	public static final String NIFTY_URL = "https://www.nseindia.com/live_market/dynaContent/live_watch/option_chain/optionKeys.jsp?segmentLink=17&instrument=OPTIDX&symbol=NIFTY";
	public static final String NIFTY_URL_URL_BY_Expiry = "https://www.nseindia.com/live_market/dynaContent/live_watch/option_chain/optionKeys.jsp?segmentLink=17&instrument=OPTIDX&symbol=NIFTY&date=";

	public static final String NIFTY_FUTURE_URL = "https://www.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuoteFO.jsp?underlying=NIFTY&instrument=FUTIDX&type=-&strike=-&expiry=28MAR2018";

	public static final String NSE_FO_OI_ALLPARTICAPENT = "https://www.nseindia.com/content/nsccl/";
}

package org.option.currency.tasks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.jsoup.nodes.Document;
import org.option.currency.models.Columns;
import org.option.currency.models.USDINRFuture;
import org.smarttrade.options.utils.APPConstant;
import org.smarttrade.options.utils.DateUtils;
import org.smarttrade.options.utils.DocumentParser;
import org.stocksrin.utils.HTMLPageDocumentDownloader;

public class USDINRDataCollector implements Runnable {

	public static boolean isUpdateTime() {
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		System.out.println("Now :" + now.get(Calendar.HOUR_OF_DAY));
		// do not update data when time is more then 6 pm and data is already updated 
		if (now.get(Calendar.HOUR_OF_DAY) > 18) {
			/*if(!USDINRData.getExpiryList().isEmpty()){
				return false;
			}else{
				return true;
			}*/
		}else{
			return true;	
		}
		return false;
	}

	@Override
	public void run() {
		System.out.println(" ************ Start Featching Data @ " + DateUtils.getTodayDateTime() + "**************");
		if(isUpdateTime()){

		Document doc = HTMLPageDocumentDownloader.getDocument(APPConstant.NSE_URL_INIT);

		List<String> lst = new ArrayList<String>();
		try {
			lst = DocumentParser.getInstance().getExpiryList(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
/*
		USDINRData.getExpiryList().clear();
		USDINRData.setExpiryList(lst);

		getAllExpirOptionData(lst, doc);
		USDINRData.ClearOldData(lst);*/
	
		}else{
			System.out.println("Data is already updated for last market ");
		}
		System.out.println(" ************ Completed Featching Data @ " + DateUtils.getTodayDateTime() + "**************");
		

	}

	public void getAllExpirOptionData(List<String> expiryList, Document firstExpiryDoc) {
		try {

			// current month expiry
			String firstExpiry = expiryList.get(0);
			String url1 = APPConstant.getUSDIINROptionChainURL(firstExpiry);
			Document firstDoc = HTMLPageDocumentDownloader.getDocument(url1);
			// DocumentParser.getInstance().getOptionChainTable(firstDoc);
			Columns columns1 = DocumentParser.getInstance().getOptionData(firstDoc);

			columns1.setExpiryList(expiryList);
			columns1.setExpiry(expiryList.get(0));

			//USDINRData.updateOptionData(expiryList.get(0), columns1);

			String futureUrl = APPConstant.getUSDINRFutureURL(expiryList.get(0));
			//System.out.println(futureUrl);
			Document futureDc = HTMLPageDocumentDownloader.getDocument(futureUrl);
			USDINRFuture futurePrice = DocumentParser.getInstance().getFuturePrice(futureDc);
			columns1.setuSDINRFuture(futurePrice);
			columns1.setLastDataUpdated(DateUtils.getTodayDateTime());

			// leave first expiry
			for (int i = 1; i < expiryList.size(); i++) {

				String url = APPConstant.getUSDIINROptionChainURL(expiryList.get(i));
				Document doc = HTMLPageDocumentDownloader.getDocument(url);
				// System.out.println("File URL " + url);
				Columns columns = DocumentParser.getInstance().getOptionData(doc);

				columns.setExpiryList(expiryList);
				columns.setExpiry(expiryList.get(i));

				// System.out.println("columns" + columns);
				//USDINRData.updateOptionData(expiryList.get(i), columns);

				String futureUrl2 = APPConstant.getUSDINRFutureURL(expiryList.get(i));
				Document futureDc2 = HTMLPageDocumentDownloader.getDocument(futureUrl2);
				USDINRFuture futurePrice2 = DocumentParser.getInstance().getFuturePrice(futureDc2);
				columns.setuSDINRFuture(futurePrice2);
				columns.setLastDataUpdated(DateUtils.getTodayDateTime());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
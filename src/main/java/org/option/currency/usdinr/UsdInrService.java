package org.option.currency.usdinr;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.option.currency.models.Columns;
import org.option.currency.models.USDINRFuture;
import org.smarttrade.options.utils.APPConstant;
import org.smarttrade.options.utils.DateComparator;
import org.smarttrade.options.utils.DateUtils;
import org.smarttrade.options.utils.DocumentParser;
import org.stocksrin.utils.CommonHTMLDocParsher;
import org.stocksrin.utils.HTMLPageDocumentDownloader;
import org.stocksrin.utils.LoggerSysOut;

public class UsdInrService {
	
	private static UsdInrService instance = new UsdInrService();
	
	public static UsdInrService getInstance(){
		return instance;
	}

	private UsdInrService(){}
	
	public TreeSet<String> getExpiries(){
		Set<String> expireis = UsdInrData.getData().keySet();
		
		TreeSet<String> lst = new TreeSet<String>(new DateComparator());
		LoggerSysOut.print(expireis.size());
		for (String string : expireis) {
			LoggerSysOut.print("string" + string);
			LoggerSysOut.print("string" + string.length());
			
			if(!string.equals("-") && !(string.length()==0)){
				LoggerSysOut.print("string" + string);
				lst.add(string);
			}
		}
		
		if(lst.size() < 3 ){
			LoggerSysOut.print("All Expiry Data is not available ");
			
			Columns columns = getUSDINROC("-");
			List<String> expiryList=columns.getExpiryList();
			for (int i = 0; i < expiryList.size(); i++) {
				if(!expireis.contains(expiryList.get(i))){
					
					getUSDINROC(expiryList.get(i));
					if(!expiryList.get(i).equals("-")){
						lst.add(expiryList.get(i));	
					}
				}
				
				
			}
			lst.add(expiryList.get(0));
		}
		
		return lst;
		
	}
	
	public Columns getUSDINROC(String expiry) {
		
		Columns optionChain = UsdInrData.getData().get(expiry);
		if (optionChain == null) {
			// go and fetch data from nse
			LoggerSysOut.print("Data is null request Data");
			return getUSDINROptionChain(expiry);
		}else {
			LoggerSysOut.print("Data is  Not null get Cached request Data");
			String lastUpdateDate = optionChain.getLastDataUpdated();
			// long diff =DateUtils.getTimeDifferenceInMinitues(lastUpdateDate);
			boolean status = DateUtils.getInstance().featchData(lastUpdateDate, 18l);
			if (status) {
				return getUSDINROptionChain(expiry);
			} else {
				return optionChain;
			}
		}
		
	}
	
	private Columns getUSDINROptionChain(String expiry){
		String nseUrl="https://www.nseindia.com/live_market/dynaContent/live_watch/fxTracker/optChainDataByExpDates.jsp?symbol=USDINR&instrument=OPTCUR&expiryDt="+expiry;
		Document doc = HTMLPageDocumentDownloader.getDocument(nseUrl);
		
		//String file="C:\\Users\\rahulksh\\Desktop\\nsedata\\NIFTY.html";
		//Document doc=DomainFacade.getInstance().getDocumentFromFile(file);
		
		try {			
			Elements c=CommonHTMLDocParsher.getOptionChainTable(doc, "octable", 0);
			Columns columns = CommonHTMLDocParsher.parseUSDINRColumn(doc, c);
			List<String> expiryList=CommonHTMLDocParsher.getSelectBoxById(doc, "expirydate", 0);
			
			List<String> firstThreeExpiry=new ArrayList<String>();
			for (int i = 0; i < 3; i++) {
				firstThreeExpiry.add(expiryList.get(i));
			}
			columns.setExpiry(firstThreeExpiry.get(0));
			columns.setExpiryList(firstThreeExpiry);
			String futureUrl = null;
			if(expiry.equals("-")){
				UsdInrData.getData().put("-", columns);
				UsdInrData.getData().put(firstThreeExpiry.get(0), columns);
				futureUrl = APPConstant.getUSDINRFutureURL(firstThreeExpiry.get(0));
			}else{
				futureUrl = APPConstant.getUSDINRFutureURL(expiry);
			}
			
			Document futureDc = HTMLPageDocumentDownloader.getDocument(futureUrl);
			USDINRFuture futurePrice = DocumentParser.getInstance().getFuturePrice(futureDc);
			columns.setuSDINRFuture(futurePrice);
			
			UsdInrData.getData().put(expiry, columns);
			return columns;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	

}

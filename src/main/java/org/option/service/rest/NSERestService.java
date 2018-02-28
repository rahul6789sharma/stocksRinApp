package org.option.service.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.option.currency.models.Columns;
import org.option.currency.models.NiftyData;
import org.option.currency.tasks.USDINROptionDBTask;
import org.smarttrade.options.utils.DateUtils;
import org.stocks.price.NseModel;
import org.stocks.price.NsePrice;
import org.stocksrin.utils.CommonHTMLDocParsher;
import org.stocksrin.utils.HTMLPageDocumentDownloader;

@Path("/nseservice")
public class NSERestService {
	
	private void USDINRDBTask(){
		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.HOUR_OF_DAY, 15);
		today.set(Calendar.MINUTE, 46);
		today.set(Calendar.SECOND, 0);

		// every night at 2am you run your task
		Timer timer = new Timer();
		timer.schedule(new USDINROptionDBTask(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
	}
	
	
	@POST
	@Path("/niftyOptionChain")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Columns getNiftyOptionChainService(String expiry){
		return getNIftyOC(expiry);
	}
	
	public Columns getNIftyOC(String expiry) {
		
		System.out.println(NiftyData.getData().keySet());
		
		Columns optionChain = NiftyData.getData().get(expiry);

		if (optionChain == null) {
			// go and fetch data from nse
			System.out.println("Data is null request Data");
			return getNiftyOptionChain(expiry);
		} else {
			String lastUpdateDate = optionChain.getLastDataUpdated();
			// long diff =DateUtils.getTimeDifferenceInMinitues(lastUpdateDate);
			boolean status = DateUtils.getInstance().featchData(lastUpdateDate, 18l);
			if (status) {
				return getNiftyOptionChain(expiry);
			} else {
				return optionChain;
			}
		}

	}

	public Columns getNiftyOptionChain(String expiry){
		String nseUrl = "https://www.nseindia.com/live_market/dynaContent/live_watch/option_chain/optionKeys.jsp?segmentLink=17&instrument=OPTIDX&symbol=NIFTY&date="+expiry;
		//String file="C:\\Users\\rahulksh\\Desktop\\nsedata\\NIFTY.html";
		//Document doc=DomainFacade.getInstance().getDocumentFromFile(file);
		Document doc = HTMLPageDocumentDownloader.getDocument(nseUrl);
		try {			
			Elements c=CommonHTMLDocParsher.getOptionChainTable(doc, "octable", 0);
			Columns columns = CommonHTMLDocParsher.parseNSEColumn(doc, c);
			
			String spotPrice= columns.getUnderlyingSpotPrice().substring(24, 31);
			columns.setSpotPrice(spotPrice);
			
			List<String> expiryList=CommonHTMLDocParsher.getSelectBoxById(doc, "date", 0);
			List<String> firstThreeExpiry=new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				firstThreeExpiry.add(expiryList.get(i));
			}
			columns.setExpiry(firstThreeExpiry.get(0));
			columns.setExpiryList(firstThreeExpiry);
			if(expiry.equals("-")){
				NiftyData.getData().put("-", columns);
				NiftyData.getData().put(firstThreeExpiry.get(0), columns);
			}
			NiftyData.getData().put(expiry, columns);
			return columns;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private List<NseModel> converNSEPriceToModel(List<NsePrice> lst) {
		List<NseModel> resultList = new ArrayList<NseModel>();
		TreeSet<Date> dates = new TreeSet<Date>();
		
		for (NsePrice nsePrice : lst) {
			dates.add(nsePrice.getId().getNseDate());
		}
		System.out.println(dates);
		for (Date date : dates) {
			NseModel nseModel = new NseModel();
			nseModel.setDate(DateUtils.getInstance().getDateStringBYDate(date));
			
			for (NsePrice nsePrice : lst) {
				if(date.equals(nsePrice.getId().getNseDate())){
					
					if(nsePrice.getId().getNseSymbol().equals("INDIAVIX")){
						nseModel.setNifty_vol(nsePrice.getNseClose());
					}else if(nsePrice.getId().getNseSymbol().equals("NIFTY")){
						
						nseModel.setNifty_nseOpen(nsePrice.getNseOpen());
						nseModel.setNifty_nseHigh(nsePrice.getNseHigh());
						nseModel.setNifty_nseLow(nsePrice.getNseLow());
						nseModel.setNifty_nseClose(nsePrice.getNseClose());
						nseModel.setNifty_nseChange(nsePrice.getNseChange());
						nseModel.setNifty_percantageChange(nsePrice.getPercantageChange());
						nseModel.setComments(nsePrice.getComments());
						
					}else if(nsePrice.getId().getNseSymbol().equals("BANKNIFTY")){
						nseModel.setbNifty_nseOpen(nsePrice.getNseOpen());
						nseModel.setbNifty_nseHigh(nsePrice.getNseHigh());
						nseModel.setbNifty_nseLow(nsePrice.getNseLow());
						nseModel.setbNifty_nseClose(nsePrice.getNseClose());
						nseModel.setbNifty_nseChange(nsePrice.getNseChange());
						nseModel.setbNifty_percantageChange(nsePrice.getPercantageChange());
						nseModel.setComments(nsePrice.getComments());
					}
				}
			}
			resultList.add(nseModel);
		}
		
		
		System.out.println(resultList);
		return resultList;
	}
}
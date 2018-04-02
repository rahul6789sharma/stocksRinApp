package org.option.service.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.option.currency.models.Columns;
import org.option.currency.models.NiftyData;
import org.smarttrade.options.utils.DateUtils;
import org.stocks.price.NseModel;
import org.stocks.price.NsePrice;
import org.stocksrin.banknifty.BankNiftyDataFileUtils;
import org.stocksrin.banknifty.TickData;
import org.stocksrin.banknifty.option.alog.ContinuesOptionPriceGetter;
import org.stocksrin.option.model.OptionModles;
import org.stocksrin.utils.CommonHTMLDocParsher;
import org.stocksrin.utils.HTMLPageDocumentDownloader;

@Path("/nseservice")
public class NSERestService {

	@GET
	@Path("/bankNiftyOptionTickData")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public LinkedHashMap<String, List<TickData>> getBankNiftyOptionTickData() throws Exception {
		return ContinuesOptionPriceGetter.getOptionData();
	}

	@POST
	@Path("/bankniftyOptionChain")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<OptionModles> getBankNiftyData(String expiry) throws Exception {
		System.out.println("expiry " + expiry);
		return BankNiftyDataFileUtils.getBankNiftyAllData(expiry);
	}

	@GET
	@Path("/bankniftyOptionChain")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<OptionModles> getBankNiftyData() throws Exception {
		return BankNiftyDataFileUtils.getBankNiftyAllData("1MAR2018");
	}


	@POST
	@Path("/niftyOptionChain")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Columns getNiftyOptionChainService(String expiry) {
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

	public Columns getNiftyOptionChain(String expiry) {
		String nseUrl = "https://www.nseindia.com/live_market/dynaContent/live_watch/option_chain/optionKeys.jsp?segmentLink=17&instrument=OPTIDX&symbol=NIFTY&date=" + expiry;
		// String file="C:\\Users\\rahulksh\\Desktop\\nsedata\\NIFTY.html";
		// Document doc=DomainFacade.getInstance().getDocumentFromFile(file);
		Document doc = HTMLPageDocumentDownloader.getDocument(nseUrl);
		try {
			Elements c = CommonHTMLDocParsher.getOptionChainTable(doc, "octable", 0);
			Columns columns = CommonHTMLDocParsher.parseNSEColumn(doc, c);

			String spotPrice = columns.getUnderlyingSpotPrice().substring(24, 31);
			columns.setSpotPrice(spotPrice);

			List<String> expiryList = CommonHTMLDocParsher.getSelectBoxById(doc, "date", 0);
			List<String> firstThreeExpiry = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				firstThreeExpiry.add(expiryList.get(i));
			}
			columns.setExpiry(firstThreeExpiry.get(0));
			columns.setExpiryList(firstThreeExpiry);
			if (expiry.equals("-")) {
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
				if (date.equals(nsePrice.getId().getNseDate())) {

					if (nsePrice.getId().getNseSymbol().equals("INDIAVIX")) {
						nseModel.setNifty_vol(nsePrice.getNseClose());
					} else if (nsePrice.getId().getNseSymbol().equals("NIFTY")) {

						nseModel.setNifty_nseOpen(nsePrice.getNseOpen());
						nseModel.setNifty_nseHigh(nsePrice.getNseHigh());
						nseModel.setNifty_nseLow(nsePrice.getNseLow());
						nseModel.setNifty_nseClose(nsePrice.getNseClose());
						nseModel.setNifty_nseChange(nsePrice.getNseChange());
						nseModel.setNifty_percantageChange(nsePrice.getPercantageChange());
						nseModel.setComments(nsePrice.getComments());

					} else if (nsePrice.getId().getNseSymbol().equals("BANKNIFTY")) {
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
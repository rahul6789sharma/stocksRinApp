package org.option.service.rest;

import java.util.Map;
import java.util.SortedSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.stocksrin.option.banknifty.BankNiftyData;
import org.stocksrin.option.banknifty.BankNiftyData2;
import org.stocksrin.option.common.WebTest;
import org.stocksrin.option.common.model.DailyMaxPain;
import org.stocksrin.option.common.model.OptionModles;

@Path("/bankNiftyService")
public class BankNiftyService {

	// http://localhost:8080/rest/bankNiftyService/strategyTest
	@GET
	@Path("/strategyTest")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Map<String, String> dataTest() throws Exception {
		return WebTest.data;
	}

	// http://localhost:8080/rest/bankNiftyService/maxPainWeekly
	@GET
	@Path("/maxPainWeekly")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Map<String, DailyMaxPain> getWeeklyMaxPainData() throws Exception {
		return BankNiftyData2.dailyMaxPain;
	}

	// http://localhost:8080/rest/bankNiftyService/bankNiftyWeeklyOptionChain
	@GET
	@Path("/bankNiftyWeeklyOptionChain")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Map<String, OptionModles> getBankNiftyWeeklyOptionChain() throws Exception {
		return BankNiftyData2.bnWeeklyOptionChain2;
	}

	// http://localhost:8080/rest/bankNiftyService/bankNiftyExpiry
	@GET
	@Path("/bankNiftyExpiry")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public SortedSet<String> getBNExpiry() throws Exception {
		return BankNiftyData.getShortedSet();
	}

	/*
	 * // http://localhost:8080/rest/bankNiftyService/bankNiftyMaxPain
	 * 
	 * @GET
	 * 
	 * @Path("/bankNiftyMaxPain")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8") public
	 * Map<String, LiveMaxPainModle> getBankNiftyMaxPainData() throws Exception
	 * { return getData(); }
	 */

	/*
	 * // http://localhost:8080/rest/bankNiftyService/bankNiftyDailyMaxPain
	 * 
	 * @GET
	 * 
	 * @Path("/bankNiftyDailyMaxPain")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8") public
	 * Map<String, List<DailyMaxPain>> getBankNiftyDailyMaxPainData() throws
	 * Exception { Map<String, List<DailyMaxPain>> map =
	 * BankNiftyData.getMaxpPainSortedData(); Set<String> expiryies =
	 * BankNiftyData.getMaxpPainSortedData().keySet(); SortedSet<String>
	 * shortedSet = new TreeSet<>(new ComparatorBasedOnDate());
	 * 
	 * for (String string : expiryies) { shortedSet.add(string); } Map<String,
	 * List<DailyMaxPain>> result = new LinkedHashMap<>();
	 * 
	 * for (String string : shortedSet) { result.put(string, map.get(string)); }
	 * return result; }
	 */

	/*
	 * private Map<String, LiveMaxPainModle> getData() { Map<String,
	 * LiveMaxPainModle> map = new LinkedHashMap<>();
	 * 
	 * Set<String> shortedSet = BankNiftyData.getShortedSet();
	 * 
	 * for (String string : shortedSet) { LiveMaxPainModle optionAnalysisModle =
	 * BankNiftyData.getMaxPainSerieas().get(string); map.put(string,
	 * optionAnalysisModle); } return map; }
	 */
}

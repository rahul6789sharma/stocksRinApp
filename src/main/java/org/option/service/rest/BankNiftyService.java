package org.option.service.rest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.stocksrin.banknifty.BankNiftyData;
import org.stocksrin.banknifty.LiveMaxPainModle;
import org.stocksrin.banknifty.dataStore.BankNiftyData2;
import org.stocksrin.option.model.BankNiftyDailyMaxPain;
import org.stocksrin.option.model.OptionModles;
import org.stocksrin.utils.ComparatorBasedOnDate;

@Path("/bankNiftyService")
public class BankNiftyService {

	// http://localhost:8080/rest/bankNiftyService/bankNiftyWeeklyOptionChain
	@GET
	@Path("/bankNiftyWeeklyOptionChain")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Map<String, OptionModles> getBankNiftyWeeklyOptionChain() throws Exception {
		return BankNiftyData2.bnWeeklyOptionChain2;
	}

	// http://localhost:8080/rest/bankNiftyService/bankNiftyWeeklyMaxPain
	@GET
	@Path("/bankNiftyWeeklyMaxPain")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<BankNiftyDailyMaxPain> getBankNiftyWeeklyMaxPainData() throws Exception {
		return BankNiftyData.getWeeklyMaxPain();
	}

	// http://localhost:8080/rest/bankNiftyService/bankNiftyExpiry
	@GET
	@Path("/bankNiftyExpiry")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public SortedSet<String> getBNExpiry() throws Exception {
		return BankNiftyData.getShortedSet();
	}

	// http://localhost:8080/rest/bankNiftyService/bankNiftyMaxPain
	@GET
	@Path("/bankNiftyMaxPain")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Map<String, LiveMaxPainModle> getBankNiftyMaxPainData() throws Exception {
		return getData();
	}

	// http://localhost:8080/rest/bankNiftyService/bankNiftyDailyMaxPain
	@GET
	@Path("/bankNiftyDailyMaxPain")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Map<String, List<BankNiftyDailyMaxPain>> getBankNiftyDailyMaxPainData() throws Exception {
		Map<String, List<BankNiftyDailyMaxPain>> map = BankNiftyData.getMaxpPainSortedData();
		Set<String> expiryies = BankNiftyData.getMaxpPainSortedData().keySet();
		SortedSet<String> shortedSet = new TreeSet<>(new ComparatorBasedOnDate());

		for (String string : expiryies) {
			shortedSet.add(string);
		}
		Map<String, List<BankNiftyDailyMaxPain>> result = new LinkedHashMap<>();

		for (String string : shortedSet) {
			result.put(string, map.get(string));
		}
		return result;
	}

	private Map<String, LiveMaxPainModle> getData() {
		Map<String, LiveMaxPainModle> map = new LinkedHashMap<>();

		Set<String> shortedSet = BankNiftyData.getShortedSet();

		for (String string : shortedSet) {
			LiveMaxPainModle optionAnalysisModle = BankNiftyData.getMaxPainSerieas().get(string);
			map.put(string, optionAnalysisModle);
		}
		return map;
	}
}

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
import org.stocksrin.banknifty.OptionAnalysisModle;
import org.stocksrin.option.model.BankNiftyDailyMaxPain;

@Path("/bankNiftyService")
public class BankNiftyService {

	// http://localhost:8080/rest/bankNiftyService/bankNiftyMaxPain
	@GET
	@Path("/bankNiftyMaxPain")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Map<String, OptionAnalysisModle> getBankNiftyMaxPainData() throws Exception {
		return getData();
	}

	//http://localhost:8080/rest/bankNiftyService/bankNiftyDailyMaxPain
	@GET
	@Path("/bankNiftyDailyMaxPain")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Map<String, List<BankNiftyDailyMaxPain>> getBankNiftyDailyMaxPainData() throws Exception {
		return BankNiftyData.getMaxpPainSortedData();
	}

	private Map<String, OptionAnalysisModle> getData() {
		Map<String, OptionAnalysisModle> map = new LinkedHashMap<>();

		Set<String> expiryies = BankNiftyData.getMaxPainSerieas().keySet();
		SortedSet<String> shortedSet = new TreeSet<>();
		for (String string : expiryies) {
			shortedSet.add(string);
		}

		for (String string : shortedSet) {
			OptionAnalysisModle optionAnalysisModle = BankNiftyData.getMaxPainSerieas().get(string);
			map.put(string, optionAnalysisModle);
		}
		return map;
	}
}

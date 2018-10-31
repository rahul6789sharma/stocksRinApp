package org.option.service.rest;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.stocksrin.option.common.model.OptionModles;
import org.stocksrin.option.nifty.NiftyData;

@Path("/niftyService")
public class NiftyRest {

	// http://localhost:8080/rest/niftyService/currentMonthOptionChain
	@GET
	@Path("/currentMonthOptionChain")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Map<String, OptionModles> getCurrentMonthOptionChain() throws Exception {
		return NiftyData.optionData;
	}


}

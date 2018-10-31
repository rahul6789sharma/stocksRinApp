package org.option.service.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.stocksrin.live.AdvancedDeclined;

@Path("/liveMarketData")
public class LiveMarketDataService {

	// http://localhost:8080/rest/liveMarketData/advancedDecline
	@GET
	@Path("/advancedDecline")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String getAdvancedDecline() {
		try {
			return AdvancedDeclined.getAdvanced() + "~" + AdvancedDeclined.getDeclined();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
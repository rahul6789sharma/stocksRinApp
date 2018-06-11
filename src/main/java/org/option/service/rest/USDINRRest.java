package org.option.service.rest;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.option.currency.models.Columns;
import org.option.currency.models.MaxPains;
import org.option.currency.usdinr.UsdInrData;
import org.option.currency.usdinr.UsdInrService;
import org.smarttrade.options.utils.Calculation;
import org.smarttrade.options.utils.DateUtils;
import org.stocksrin.utils.LoggerSysOut;

@Path("/usdinrService")
public class USDINRRest {

	@POST
	@Path("/usdinrOptionChain")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Columns getUSDINROptionChainService(String expiry) {
		UsdInrService usdInrService = UsdInrService.getInstance();
		return usdInrService.getUSDINROC(expiry);
	}

	@GET
	@Path("/expiries")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Set<String> getExpiryList() {
		Set<String> expiry = UsdInrService.getInstance().getExpiries();
		LoggerSysOut.print("Getting expiry :" + expiry);
		return expiry;
	}

	@GET
	@Path("/env")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String environmentDetails() {
		return dispMemory();
	}

	@POST
	@Path("/maxPain")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.TEXT_PLAIN)
	public MaxPains calMaxPain(String expiryDt) {
		String currentMonthExpiry = UsdInrService.getInstance().getExpiries().first();
		Columns columns = null;
		if (expiryDt == null || expiryDt.isEmpty()) {
			columns = UsdInrData.getData().get(currentMonthExpiry);
		} else {
			columns = UsdInrData.getData().get(expiryDt);
		}

		MaxPains result = Calculation.maxPain(columns.getDataset());
		return result;
	}

	public static String dispMemory() {
		String result = " Processor : " + Runtime.getRuntime().availableProcessors();
		Runtime runtime = Runtime.getRuntime();
		long memory = runtime.totalMemory() - runtime.freeMemory();
		result = result + "\n Total memory : " + bytesToMegabytes(Runtime.getRuntime().totalMemory()) + " MB";
		result = result + "\n Free memory : " + bytesToMegabytes(Runtime.getRuntime().freeMemory()) + " MB";
		result = result + "\n Used memory : " + bytesToMegabytes(memory) + " MB";
		result = result + "\n Date: " + DateUtils.getTodayDateTime();
		return result;
	}

	private static long bytesToMegabytes(long bytes) {
		long MEGABYTE = 1024L * 1024L;
		return bytes / MEGABYTE;
	}

}
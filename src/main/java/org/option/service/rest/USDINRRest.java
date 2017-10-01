package org.option.service.rest;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.option.currency.models.Column;
import org.option.currency.models.Columns;
import org.option.currency.models.MaxPains;
import org.option.currency.usdinr.HistoricalOI;
import org.option.currency.usdinr.UsdInrData;
import org.option.currency.usdinr.UsdInrService;
import org.option.db.USDINRDbFacade;
import org.option.db.Usdinr;
import org.smarttrade.options.utils.Calculation;
import org.smarttrade.options.utils.DateUtils;

@Path("/usdinrService")
public class USDINRRest {


	@POST
	@Path("/historyOITest")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<Usdinr> getHistoricalDataByExpiry(String expiry) {
		return USDINRDbFacade.getInstance().getDataByExpiry(expiry);
	}
	
	@POST
	@Path("/historyOI")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public HistoricalOI getHistoricalData(String expiry) {
		System.out.println("expiry :" + expiry);

		if (expiry.equals("-")) {

			System.out.println("Load Page first time");
			TreeSet<String> lst = UsdInrService.getInstance().getExpiries();
			String firstExpiry = lst.first();
			Columns column = UsdInrService.getInstance().getUSDINROC(firstExpiry);
			List<Column> data = column.getDataset();

			Column d = data.get(data.size() / 2);
			String strickPrice = "66";

			System.out.println("firstExpiry" + firstExpiry);
			System.out.println("strickPrice" + strickPrice);

			return UsdInrService.getInstance().getOIHistoricalData(firstExpiry, Float.parseFloat(strickPrice));
		} else {
			String[] a = expiry.split(";");
			if (a.length == 2) {
				String ex = a[0];
				String str = a[1];
				return UsdInrService.getInstance().getOIHistoricalData(ex, Float.parseFloat(str));
			} else {
				return null;
			}

		}

	}

	public static void main(String[] args) {
		String expiry = "29MAY2017;66f";
		String[] a = expiry.split(";");
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);

		}
	}

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
		System.out.println("Getting expiry :" + expiry);
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
		// String currentMonthExpiry = USDINRData.getExpiryList().get(0);
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
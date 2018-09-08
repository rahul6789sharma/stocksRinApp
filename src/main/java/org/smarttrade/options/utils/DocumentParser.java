package org.smarttrade.options.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.option.currency.models.Data;
import org.option.currency.models.Future;
import org.option.currency.models.UsdInrFutureJson;
import org.stocksrin.oi.future.NiftyFutureModel;
import org.stocksrin.option.common.model.OptionModle;
import org.stocksrin.option.common.model.OptionModles;
import org.stocksrin.utils.LoggerSysOut;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DocumentParser {

	private static DocumentParser instance = new DocumentParser();

	private DocumentParser() {
	}

	public static DocumentParser getInstance() {
		return instance;
	}

	public NiftyFutureModel getNiftyFutureData(Document doc) throws Exception {
		String data=null;
		NiftyFutureModel obj=null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			Element elementSelectBox = doc.getElementById("responseDiv");
			LoggerSysOut.print(elementSelectBox);
			LoggerSysOut.print("data " + elementSelectBox.ownText());

			 obj = mapper.readValue(elementSelectBox.ownText(), NiftyFutureModel.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return obj;
	}
	
	public List<String> getNiftyExpiryList(Document doc) throws Exception {
		List<String> lst = new ArrayList<>();
		try {
			Element elementSelectBox = doc.getElementById("date");
			List<Node> childs = elementSelectBox.childNodes();
			for (int i = 2; i < 8; i++) {
				String value = childs.get(i).attr("value");
				if (!value.isEmpty()) {
					lst.add(value);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (lst.isEmpty()) {
			throw new Exception("Expiry Select Box Borkan! Check expiry Select Box in web Page");
		}
		return lst;
	}

	public OptionModles getOptionData(Document doc) throws Exception {
		List<OptionModle> lst = getOptionChainTable(doc);
		OptionModles columns = updateColumns(lst);
		return columns;

	}

	public List<String> getExpiryList(Document doc) throws Exception {
		List<String> lst = new ArrayList<>();
		try {
			Elements dropbox = doc.select("select[class=goodTextBox]").get(1).children();
			lst = new ArrayList<>();
			for (int i = 1; i < dropbox.size(); i++) {
				lst.add(dropbox.get(i).text());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (lst.isEmpty()) {
			throw new Exception("Expiry Select Box Borkan! Check expiry Select Box in web Page");
		}
		return lst;
	}

	public List<OptionModle> getOptionChainTable(Document doc) throws Exception {
		Element table = doc.select("table[id=octable]").get(0); // select
		Elements rows = table.select("tr");
		List<OptionModle> lst = new ArrayList<>();

		for (int i = 2; i < rows.size() - 1; i++) { // first second and last
													// row is the col names so
													// skip it.

			Element row = rows.get(i);
			Elements cols = row.select("td");

			OptionModle column = new OptionModle();
			for (int j = 0; j < cols.size(); j++) {
				Element col = cols.get(j);

				if (cols.size() == 21) {
					column.setValue(j, col.text());
				}

			}
			lst.add(column);
		}
		if (lst.isEmpty()) {
			throw new Exception("Option Chain Table is brokean! Check the table id='octable' in Web Page ");
		}
		return lst;
	}

	public OptionModles updateColumns(List<OptionModle> column) {
		OptionModles columns = new OptionModles();
		columns.setOptionModle(column);

		

		// int max_ce_oi = 0;
		// int max_pe_oi = 0;
		int total_ce_oi = 0;
		int total_pe_oi = 0;
		for (OptionModle c : column) {
			int ce_oi = 0;
			int pu_oi = 0;
			try {
			//	ce_oi = Integer.parseInt(c.getCE_OI().replaceAll("[]", ""));
				ce_oi = c.getC_oi();
				/*
				 * if (max_ce_oi < ce_oi) { max_ce_oi = ce_oi; }
				 */

			} catch (Exception e) {

			}
			try {
				//pu_oi = Integer.parseInt(c.getPE_OI().replaceAll("[]", ""));
				pu_oi = c.getP_oi();
				/*
				 * if (max_pe_oi < pu_oi) { max_pe_oi = pu_oi; }
				 */
			} catch (Exception e) {

			}
			total_ce_oi = total_ce_oi + ce_oi;
			total_pe_oi = total_pe_oi + pu_oi;
		}
		columns.setTotal_ce_oi(total_ce_oi);
		columns.setTotal_pe_oi(total_pe_oi);
		// columns.setMax_ce_oi(max_ce_oi);
		// columns.setMax_pe_oi(max_pe_oi);
		return columns;
	}

	public Future getFuturePrice(Document doc) {
		try {
			Elements strong = doc.select("div[id=responseDiv]");
			String dataJson = strong.get(0).text();
			Future USDINRFuture = getUSDINRFuturefromJsonString(dataJson);
			return USDINRFuture;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public Future getUSDINRFuturefromJsonString(String jsonData) {
		Future USDINRFuture = new Future();

		ObjectMapper mapper = new ObjectMapper();
		UsdInrFutureJson UsdInrFuture = null;
		try {
			UsdInrFuture = mapper.readValue(jsonData, UsdInrFutureJson.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		USDINRFuture.setRBIrr(UsdInrFuture.getRBIrr());
		USDINRFuture.setRBIrr_last_updated(UsdInrFuture.getLast_updated());

		for (Data d : UsdInrFuture.getData()) {
			USDINRFuture.setChange(d.getChange());
			USDINRFuture.setExpiryDate(d.getExpiryDate());
			USDINRFuture.setHighPrice(d.getHighPrice());
			USDINRFuture.setLastPrice(d.getLastPrice());
			USDINRFuture.setLowPrice(d.getLowPrice());
			USDINRFuture.setPercentageChange(d.getPChange());
			USDINRFuture.setPrevClose(d.getPrevClose());
		}
		return USDINRFuture;
	}

}


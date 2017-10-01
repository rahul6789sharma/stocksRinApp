package org.smarttrade.options.utils;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.option.currency.models.Column;
import org.option.currency.models.Columns;
import org.option.currency.models.Data;
import org.option.currency.models.USDINRFuture;
import org.option.currency.models.UsdInrFutureJson;




import com.google.gson.Gson;

public class DocumentParser {

	private static DocumentParser instance = new DocumentParser();

	private DocumentParser() {
	}

	public static DocumentParser getInstance() {
		return instance;
	}

	public Columns getOptionData(Document doc) throws Exception {
		List<Column> lst = getOptionChainTable(doc);
		Columns columns = updateColumns(lst);
		return columns;

	}

	public List<String> getExpiryList(Document doc) throws Exception {
		List<String> lst = new ArrayList<String>();
		try {
			Elements dropbox = doc.select("select[class=goodTextBox]").get(1).children();
			lst = new ArrayList<String>();
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

	public List<Column> getOptionChainTable(Document doc) throws Exception {
		Element table = doc.select("table[id=octable]").get(0); // select
		Elements rows = table.select("tr");
		List<Column> lst = new ArrayList<Column>();

		for (int i = 2; i < rows.size() - 1; i++) { // first, second and last
													// row is the col names so
													// skip it.

			Element row = rows.get(i);
			Elements cols = row.select("td");

			Column column = new Column();
			for (int j = 0; j < cols.size(); j++) {
				Element col = cols.get(j);

				if (cols.size() == 21) {
					column.setVal(j, col.text());
				}

			}
			lst.add(column);
		}
		if (lst.isEmpty()) {
			throw new Exception("Option Chain Table is brokean! Check the table id='octable' in Web Page ");
		}
		return lst;
	}

	public Columns updateColumns(List<Column> column) {
		Columns columns = new Columns();
		columns.setDataset(column);

		columns.setInterestRate("6.7");

		//int max_ce_oi = 0;
		//int max_pe_oi = 0;
		int total_ce_oi = 0;
		int total_pe_oi = 0;
		for (Column c : column) {
			int ce_oi = 0;
			int pu_oi = 0;
			try {
				ce_oi = Integer.parseInt(c.getCE_OI().replaceAll("[,]", ""));
			/*	if (max_ce_oi < ce_oi) {
					max_ce_oi = ce_oi;
				}*/

			} catch (Exception e) {

			}
			try {
				pu_oi = Integer.parseInt(c.getPE_OI().replaceAll("[,]", ""));
				/*if (max_pe_oi < pu_oi) {
					max_pe_oi = pu_oi;
				}*/
			} catch (Exception e) {

			}
			total_ce_oi = total_ce_oi + ce_oi;
			total_pe_oi = total_pe_oi + pu_oi;
		}
		columns.setTotal_ce_oi(total_ce_oi);
		columns.setTotal_pe_oi(total_pe_oi);
		//columns.setMax_ce_oi(max_ce_oi);
		//columns.setMax_pe_oi(max_pe_oi);
		return columns;
	}

	public USDINRFuture getFuturePrice(Document doc) {
		try {
			Elements strong = doc.select("div[id=responseDiv]");
			String dataJson = strong.get(0).text();
			USDINRFuture USDINRFuture = getUSDINRFuturefromJsonString(dataJson);
			return USDINRFuture;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public USDINRFuture getUSDINRFuturefromJsonString(String jsonData) {
		USDINRFuture USDINRFuture = new USDINRFuture();
		Gson gson = new Gson();
		UsdInrFutureJson UsdInrFuture = gson.fromJson(jsonData, UsdInrFutureJson.class);
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
	// getFutureObejctFromJson(data);
}

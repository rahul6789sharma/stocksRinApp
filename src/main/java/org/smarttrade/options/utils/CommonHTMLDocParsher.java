package org.smarttrade.options.utils;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.option.currency.models.Column;
import org.option.currency.models.Columns;

public class CommonHTMLDocParsher {

	public static List<String> getSelectBoxById(Document doc, String id, int index) throws Exception {
		List<String> lst = new ArrayList<String>();
		try {
			Elements dropbox = doc.select("select[id=" + id + "]").get(index).children();
			lst = new ArrayList<String>();
			// not adding first Element[select]
			for (int i = 1; i < dropbox.size(); i++) {
				lst.add(dropbox.get(i).text());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (lst.isEmpty()) {
			throw new Exception("Expiry Select Box broken! Check expiry Select Box in web Page, ID does not exists : " + id);
		}
		return lst;
	}

	public static List<String> getSelectBoxByClass(Document doc, String classs, int index) throws Exception {
		List<String> lst = new ArrayList<String>();
		try {
			Elements dropbox = doc.select("select[class=" + classs + "]").get(index).children();
			lst = new ArrayList<String>();
			// not adding first Element[select]
			for (int i = 1; i < dropbox.size(); i++) {
				lst.add(dropbox.get(i).text());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (lst.isEmpty()) {
			throw new Exception("Expiry Select Box broken! Check expiry Select Box in web Page, class does not exists : " + classs);
		}
		return lst;
	}

	public static Elements getTable(Document doc, int index) throws Exception {

		Element table = doc.select("table").get(index); // select
		Elements rows = table.select("tr");

		if (rows.isEmpty() || rows == null) {
			throw new Exception("Option Chain Table is brokean! Check the table id in Web Page :");
		}
		return rows;
	}

	public static Elements getOptionChainTable(Document doc, String id, int index) throws Exception {

		Element table = doc.select("table[id=" + id + "]").get(0); // select
		Elements rows = table.select("tr");

		if (rows.isEmpty() || rows == null) {
			throw new Exception("Option Chain Table is brokean! Check the table id in Web Page :" + id);
		}
		return rows;
	}

	public static Columns parseNiftyColumn(Document doc, Elements rows) {

		List<Column> lst = parseNiftyOptionTableColumn(doc, rows);
		Columns result = updateColumns(doc, lst);

		try {
			Elements elements = CommonHTMLDocParsher.getTable(doc, 0);
			result.setUnderlyingSpotPrice(getSpotPrice(elements));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public static Columns parseUSDINRColumn(Document doc, Elements rows) {
		List<Column> lst= new ArrayList<Column>();
		try {
			lst = parseUSDINROptionTableColumn(doc, rows);
		} catch (Exception e) {

			e.printStackTrace();
		}
		Columns result = updateColumns(doc, lst);
		return result;
	}

	private static List<Column> parseUSDINROptionTableColumn(Document doc, Elements rows) throws Exception {

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

	private static List<Column> parseNiftyOptionTableColumn(Document doc, Elements rows) {

		List<Column> lst = new ArrayList<Column>();

		for (int i = 2; i < rows.size() - 1; i++) { // first, second and last
			// row is the col names so
			// skip it.

			Element row = rows.get(i);
			Elements cols = row.select("td");

			Column c = new Column();
			for (int j = 0; j < cols.size(); j++) {
				Element col = cols.get(j);

				if (cols.size() == 23) {
					c.setValueForNifty(j, col.text());
				}

			}
			lst.add(c);
		}

		return lst;
	}

	private static Columns updateColumns(Document doc, List<Column> column) {
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
				
			} catch (Exception e) {

			}
			try {
				pu_oi = Integer.parseInt(c.getPE_OI().replaceAll("[,]", ""));
				
			} catch (Exception e) {

			}
			total_ce_oi = total_ce_oi + ce_oi;
			total_pe_oi = total_pe_oi + pu_oi;
		}
		columns.setTotal_ce_oi(total_ce_oi);
		columns.setTotal_pe_oi(total_pe_oi);
		
		columns.setLastDataUpdated(DateUtils.getTodayDateTime());

		return columns;
	}

	private static String getSpotPrice(Elements elements) {

		String result = null;
		for (Element element : elements) {
			// System.out.println(element);
			Elements cols = element.select("td");
			// System.out.println(cols);
			for (int j = 1; j < cols.size(); j++) {
				Element col = cols.get(j);

				result = col.text();

			}
		}
		// Elements cols = row.select("td");
		return result;

	}
}

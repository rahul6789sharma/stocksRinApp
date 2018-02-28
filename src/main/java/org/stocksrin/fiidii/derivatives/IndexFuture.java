package org.stocksrin.fiidii.derivatives;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.smarttrade.options.utils.HTMLPageUtils;

public class IndexFuture {
	


	public static FIIDIIDataDerivativesModle getFIIDIIDerivaticeData(String url) {
		Document doc = HTMLPageUtils.getHTMLDocument(url);

		// Document doc =
		// HTMLPageUtils.getDocumentFromFile("C:\\Users\\rahulksh\\Desktop\\graph\\FII.html");

		Element e = doc.getElementById("rpt");

		Elements table = e.children();
		Element t = table.get(2);
		Elements rows = t.select("tr");

		Element finalRow = rows.get(4);
	
		FIIDIIDataDerivativesModle fiiDIIDataDerivativesModle = new FIIDIIDataDerivativesModle(finalRow.child(0).text(),
				finalRow.child(1).text(), finalRow.child(2).text(), finalRow.child(3).text(), finalRow.child(4).text(),
				finalRow.child(5).text());

		return fiiDIIDataDerivativesModle;
	}

	public static void main(String[] args) {
		Document doc = HTMLPageUtils.getHTMLDocument("https://www.nseindia.com/live_market/dynaContent/live_watch/equities_stock_watch.htm");
		Element e = doc.getElementById("topRightText");
		System.out.println(e);
	}

}
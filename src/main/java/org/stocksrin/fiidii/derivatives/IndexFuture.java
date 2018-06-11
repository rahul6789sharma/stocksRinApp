package org.stocksrin.fiidii.derivatives;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.smarttrade.options.utils.HTMLPageUtils;
import org.stocksrin.oi.allparticapent.ParticipantOIFutureModel;
import org.stocksrin.utils.LoggerSysOut;

public class IndexFuture {
	


	public static ParticipantOIFutureModel getFIIDIIDerivaticeData(String url) {
		Document doc = HTMLPageUtils.getHTMLDocument(url);

		// Document doc =
		// HTMLPageUtils.getDocumentFromFile("C:\\Users\\rahulksh\\Desktop\\graph\\FII.html");

		Element e = doc.getElementById("rpt");

		Elements table = e.children();
		Element t = table.get(2);
		Elements rows = t.select("tr");

		Element finalRow = rows.get(4);


		return null;
	}

	public static void main(String[] args) {
		Document doc = HTMLPageUtils.getHTMLDocument("https://www.nseindia.com/live_market/dynaContent/live_watch/equities_stock_watch.htm");
		Element e = doc.getElementById("topRightText");
		LoggerSysOut.print(e);
	}

}
package org.stocksrin.oi.future;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.smarttrade.options.utils.DocumentParser;
import org.smarttrade.options.utils.HTMLPageUtils;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.DateUtils;

public class NiftyFutureLiveOI {

	private static Map<String, NiftyFutureModel> niftyFutureData = new LinkedHashMap<>();

	public static void main(String[] args) {

	}

	public static void getData() {
		try {
			String fromDate = DateUtils.dateToString(new Date(), "dd-MMM-yyyy");
			// Document doc =
			// HTMLPageUtils.getHTMLDocument(APPConstant.NIFTY_URL);
			// List<String> expiry =
			// DocumentParser.getInstance().getNiftyExpiryList(doc);

			Document doc = HTMLPageUtils.getHTMLDocument(APPConstant.NIFTY_FUTURE_URL);
			NiftyFutureModel niftyFutureModel = DocumentParser.getInstance().getNiftyFutureData(doc);
			System.out.println(niftyFutureModel);
			niftyFutureData.put(niftyFutureModel.getLastUpdateTime(), niftyFutureModel);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

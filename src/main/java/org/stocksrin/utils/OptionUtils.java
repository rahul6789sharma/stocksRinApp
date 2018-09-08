package org.stocksrin.utils;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.stocksrin.option.common.model.OptionModles;

public class OptionUtils {

	public static OptionModles getOptionChain(String url, String expiry) throws Exception {

		Document doc = HTMLPageDocumentDownloader.getDocument(url);
		try {
			Elements c = CommonHTMLDocParsher.getOptionChainTable(doc, "octable", 0);
			OptionModles columns = CommonHTMLDocParsher.parseNSEColumn(doc, c);

			List<String> expiryList = CommonHTMLDocParsher.getSelectBoxById(doc, "date", 0);
			List<String> firstExpirys = new ArrayList<>();
			for (int i = 0; i < 6; i++) {
				firstExpirys.add(expiryList.get(i));
			}
			if (expiry != null) {
				columns.setExpiry(expiry);
			} else {
				columns.setExpiry(firstExpirys.get(0));
			}
			columns.setExpiryList(firstExpirys);
			return columns;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ERROR OptionUtils !" + e.getMessage());
		}

	}

}
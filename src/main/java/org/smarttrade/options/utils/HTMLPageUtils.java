package org.smarttrade.options.utils;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLPageUtils {

	private HTMLPageUtils() {

	}

	public static Document getDocumentFromFile(String filePath) {
		try {
			Document doc = Jsoup.parse(new File(filePath), "utf-8");
			return doc;
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}

	public static Document getHTMLDocument(String url) {
		try {
			System.out.println("HTTP request :" + url);
			Document doc = Jsoup.connect(url).get();
			return doc;
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}

	public static Elements getTable(Document doc, int index) throws Exception {

		Element table = doc.select("table").get(index); // select
		Elements rows = table.select("tr");

		if (rows.isEmpty() || rows == null) {
			throw new Exception("Cant get Table out of Document");
		}
		return rows;
	}

}

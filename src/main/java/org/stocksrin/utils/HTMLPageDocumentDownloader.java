package org.stocksrin.utils;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLPageDocumentDownloader {

	public static Document getDocument(String url) {
		try {
			//System.out.println("HTTP request :" + url);
			Document doc = Jsoup.connect(url).get();
			return doc;
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
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

}

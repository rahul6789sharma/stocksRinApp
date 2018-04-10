package org.stocksrin.utils;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLPageDocumentDownloader {

	private static final int RETRY = 3;

	private HTMLPageDocumentDownloader() {

	}

	public static Document getDocument(String url) {
		int retryCounter = 0;
		while (retryCounter < RETRY) {
			try {
				return Jsoup.connect(url).get();

			} catch (IOException e) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				retryCounter++;
				System.out.println("FAILED - Command failed on retry " + retryCounter + " of " + RETRY + " error: " + e);
				e.printStackTrace();
				if (retryCounter >= RETRY) {
					System.out.println("Max retries exceeded.");
					break;
				}
			}
		}

		throw new RuntimeException("Command failed on all of " + RETRY + " retries");
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

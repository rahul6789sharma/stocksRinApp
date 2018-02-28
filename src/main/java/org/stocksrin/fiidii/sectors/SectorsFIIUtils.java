package org.stocksrin.fiidii.sectors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.smarttrade.options.utils.HTMLPageUtils;
import org.stocksrin.fiidii.derivatives.FIIDIIDataDerivativesModle;

public class SectorsFIIUtils {

	public static FIIDIIDataDerivativesModle getFIIDIIDerivaticeData(String url) {
		//Document doc = HTMLPageUtils.getHTMLDocument(url);
		 Document doc = HTMLPageUtils.getDocumentFromFile("C:\\Users\\rahulksh\\Desktop\\graph\\sector.html");
		 
		 System.out.println(doc.body());
		Element e = doc.getElementById("dvFortnightly");
		System.out.println(e);


		return null;
	}
	
	public static void main(String[] args) {
		getFIIDIIDerivaticeData("https://www.fpi.nsdl.co.in/web/StaticReports/Fortnightly_Sector_wise_FII_Investment_Data/FIIInvestSector_Jan312018.html");
	}
}

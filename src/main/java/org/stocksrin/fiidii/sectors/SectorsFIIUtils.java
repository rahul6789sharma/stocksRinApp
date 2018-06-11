package org.stocksrin.fiidii.sectors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.smarttrade.options.utils.HTMLPageUtils;
import org.stocksrin.oi.allparticapent.ParticipantOIFutureModel;
import org.stocksrin.utils.LoggerSysOut;

public class SectorsFIIUtils {

	public static ParticipantOIFutureModel getFIIDIIDerivaticeData(String url) {
		//Document doc = HTMLPageUtils.getHTMLDocument(url);
		 Document doc = HTMLPageUtils.getDocumentFromFile("C:\\Users\\rahulksh\\Desktop\\graph\\sector.html");
		 
		 LoggerSysOut.print(doc.body());
		Element e = doc.getElementById("dvFortnightly");
		LoggerSysOut.print(e);


		return null;
	}
	
	public static void main(String[] args) {
		getFIIDIIDerivaticeData("https://www.fpi.nsdl.co.in/web/StaticReports/Fortnightly_Sector_wise_FII_Investment_Data/FIIInvestSector_Jan312018.html");
	}
}

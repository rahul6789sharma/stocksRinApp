package org.stocksrin.fii.sectorwise;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.smarttrade.options.utils.HTMLPageUtils;
import org.stocksrin.utils.LoggerSysOut;
import org.stocksrin.utils.StocksRinException;

public class GetDataUtils {

	private static String url="https://www.cdslindia.com/publications/FII/FortnightlySecWisePages/March%2031%202018.htm";
	
	//private static String url="http://www.stocksrin.com/fiiDiiActivity.html";
	public static void main(String[] args) {
		try {
			getData(url);
		} catch (StocksRinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void getData(String url) throws StocksRinException {
		Document doc = HTMLPageUtils.getDocumentFromFile("C:\\Users\\rahulksh\\stocksRin_CONF\\stocksRinData\\Fortnightly_Sector_wise_FII_Investment_Data\\NSDL _ Fortnightly Sector-wise FII Investment data.html");
	//	Document doc = HTMLPageUtils.getHTMLDocument(url);
		//LoggerSysOut.print(doc.childNodes());
		LoggerSysOut.print("title :  " + doc.title());
		//LoggerSysOut.print(doc.attributes());
		
		//LoggerSysOut.print(doc.body());
		Elements table1=doc.select("table");
		LoggerSysOut.print(table1);
		org.jsoup.nodes.Element div=doc.getElementById("dvFortnightly");
		LoggerSysOut.print(div);
		//LoggerSysOut.print(doc);
		try {
			//Elements table = HTMLPageUtils.getTable(doc);
			//LoggerSysOut.print(table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new StocksRinException("ERROR! FPI Sector Wise GetDataUtils " + e.getMessage());
		}
	}
}

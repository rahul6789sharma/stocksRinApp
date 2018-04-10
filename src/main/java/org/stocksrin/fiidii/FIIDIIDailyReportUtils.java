package org.stocksrin.fiidii;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.smarttrade.options.utils.HTMLPageUtils;
import org.stocksrin.live.LiveMarketAdvancedDecline;
import org.stocksrin.live.Rows;
import org.stocksrin.nifty.indices.NSEIndice;
import org.stocksrin.nifty.indices.NiftyIndicesDataColloctor;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.StocksRinException;

public class FIIDIIDailyReportUtils {

	public static FIIDIIDataModle getData() throws StocksRinException {
		FIIDIIDataModle fIIDIIDataModle = new FIIDIIDataModle();

		getFII_Data(APPConstant.FII_DATA_URL, fIIDIIDataModle);
		getDII_Data(APPConstant.DII_DATA_URL, fIIDIIDataModle);
		getAdvancedDeclineData(fIIDIIDataModle);

		try {
			NSEIndice nifty = NiftyIndicesDataColloctor.getData("NIFTY 50");
			fIIDIIDataModle.setNiftyChange(nifty.getChange());
			fIIDIIDataModle.setNiftyprice(nifty.getLastPrice());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fIIDIIDataModle;

	}

	private static void getAdvancedDeclineData(FIIDIIDataModle fIIDIIDataModle) throws StocksRinException {

		try {
			Rows row = LiveMarketAdvancedDecline.getData();
			fIIDIIDataModle.setStocks_Advance(row.getAdvances());
			fIIDIIDataModle.setStocks_Decline(row.getDeclines());
		} catch (Exception e) {
			throw new StocksRinException("ERROR! advanced Decline Exception " + e.getMessage());
		}

	}

	private static void getDII_Data(String url, FIIDIIDataModle fIIDIIDataModle) throws StocksRinException {
		Document doc = HTMLPageUtils.getHTMLDocument(url);

		// Document doc =
		// HTMLPageUtils.getDocumentFromFile("C:\\Users\\rahulksh\\Desktop\\graph\\DiiEQ.htm");
		try {
			Elements table = HTMLPageUtils.getTable(doc, 0);

			String result = null;

			Elements cols = table.get(2).getAllElements();

			for (int j = 1; j < cols.size(); j++) {
				Element col = cols.get(j);

				result = col.text();
				if (j == 3) {
					fIIDIIDataModle.setDii_BuyValue(result);

				} else if (j == 4) {
					fIIDIIDataModle.setDii_SellValue(result);

				} else if (j == 5) {
					fIIDIIDataModle.setDii_net(result);
				}

			}

		} catch (Exception e) {

			throw new StocksRinException("ERROR! FII DII Data Exception " + e.getMessage());
		}

	}

	private static void getFII_Data(String url, FIIDIIDataModle fIIDIIDataModle) throws StocksRinException {
		Document doc = HTMLPageUtils.getHTMLDocument(url);

		// Document doc =
		// HTMLPageUtils.getDocumentFromFile("C:\\Users\\rahulksh\\Desktop\\graph\\fiiEQ.htm");
		try {
			Elements table = HTMLPageUtils.getTable(doc, 0);

			String result = null;

			Elements cols = table.get(2).getAllElements();

			for (int j = 1; j < cols.size(); j++) {
				Element col = cols.get(j);

				result = col.text();
				if (j == 2) {
					fIIDIIDataModle.setDate(result);

				} else if (j == 3) {
					fIIDIIDataModle.setFii_BuyValue(result);

				} else if (j == 4) {
					fIIDIIDataModle.setFii_SellValue(result);

				} else if (j == 5) {
					fIIDIIDataModle.setFii_net(result);
				}

			}

		} catch (Exception e) {

			throw new StocksRinException("ERROR! FII DII Data Exception " + e.getMessage());
		}

	}

}

package org.stocksrin.nifty.indices;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.stocksrin.live.Nifty;

public class NiftyIndicesDataColloctor {

	private static final String NIFTY_INDICES = "https://nseindia.com/homepage/Indices1.json";

	public static void main(String[] args) {
		getData();
	}

	public static Nifty getData() {

		String data;
		try {
			data = getLiveAdvacnedDeclineData();

			String[] nifty = data.split("\"NIFTY 50\"");

			String[] lastPrice = nifty[1].split("\"change\"");

			Nifty niftyData = new Nifty();
			niftyData.setChange(
					lastPrice[1].split("\"pChange\"")[0].replaceAll("\"", "").replaceAll(",", "").replaceAll(":", ""));
			niftyData.setLastTradedPrice(lastPrice[0].split("\"lastPrice\"")[1].replaceAll("\"", "").replaceAll(",", "")
					.replaceAll(":", ""));

			return niftyData;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static String getLiveAdvacnedDeclineData() throws Exception {
		return sendGet(NIFTY_INDICES);
	}

	// HTTP GET request
	private static String sendGet(String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();

		if (responseCode != 200) {
			throw new Exception("Status code is not 200");
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuilder response = new StringBuilder();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
		// print result

	}
}

package org.stocksrin.live;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.stocksrin.utils.APPConstant;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LiveMarketAdvancedDecline {

	public static Rows getData() {

		ObjectMapper mapper = new ObjectMapper();
		String data;
		try {
			data = LiveMarketAdvancedDecline.getLiveAdvacnedDeclineData();
			AdvancedDeclinePojo advancedDeclinePojo = mapper.readValue(data, AdvancedDeclinePojo.class);
			Rows[] rows = advancedDeclinePojo.getRows();
			return rows[0];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getLiveAdvacnedDeclineData() throws Exception {
		return sendGet(APPConstant.Live_Market_URL);
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
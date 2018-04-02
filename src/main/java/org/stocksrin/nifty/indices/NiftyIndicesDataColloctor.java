package org.stocksrin.nifty.indices;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.stocksrin.utils.APPConstant;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NiftyIndicesDataColloctor {


	public static NSEIndice getData(String indice) throws Exception {

		String data = sendGet(APPConstant.NIFTY_INDICES);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		NSEIndicesData obj = mapper.readValue(data, NSEIndicesData.class);
		NSEIndice[] d = obj.getData();
		for (NSEIndice data2 : d) {
			if (data2.getName().equals(indice)) {
				return data2;
			}
		}
		return null;
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

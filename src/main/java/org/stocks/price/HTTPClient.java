package org.stocks.price;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.stocksrin.utils.LoggerSysOut;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HTTPClient {

	public static GoogleData[] getData() {
		String data = getDateFromGoogle("NSE:NIFTYNSE:BANKNIFTYNSE:INDIAVIX");
		String data2 = data.replaceAll("//", "");
		GoogleData[] object = parseDatString(data2);
		return object;
	}

	private static GoogleData[] parseDatString(String dataString) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			GoogleData[] obj = mapper.readValue(dataString, GoogleData[].class);
			return obj;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		LoggerSysOut.print(getDateFromGoogle("NSE:NIFTY"));
	}
	private static String getDateFromGoogle(String query) {
		try {
			//
			URL url = new URL("https://finance.google.com/finance?q=NSE:NIFTY&output=json");
			String urlString = "";
			String current;
			boolean status = true;
			int retryCount = 0;
			while (status) {
				URLConnection urlConnection = url.openConnection();
				Map<String, List<String>> header = urlConnection.getHeaderFields();
				BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
				while ((current = in.readLine()) != null) {
					urlString += current;
				}
				List<String> responseHeader = header.get(null);
				if (!responseHeader.get(0).contains("200 OK") && retryCount < 10) {
					retryCount++;
					try {
						LoggerSysOut.print("Cant make Connection to Google Retrying after 10 second");
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					status = false;
				}
			}
			return urlString;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
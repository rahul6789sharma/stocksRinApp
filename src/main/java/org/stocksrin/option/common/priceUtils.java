package org.stocksrin.option.common;

import java.util.List;

import org.stocksrin.option.banknifty.BankNiftyData2;
import org.stocksrin.option.banknifty.BankNiftyUtils;
import org.stocksrin.option.common.model.OptionModles;
import org.stocksrin.option.nifty.NiftyData;
import org.stocksrin.option.nifty.NiftyUtils;

public class priceUtils {

	public static synchronized void fetchData() throws Exception {

		fetchBNData();
		Thread.sleep(10);
		fetchNiftyData();
	}

	private static void fetchBNData() {
		try {

			OptionModles optionModles = BankNiftyUtils.getBankNiftyOptionData(null);
			BankNiftyData2.bnWeeklyOptionChain2.put(optionModles.getDate(), optionModles);

			List<String> expiryList = optionModles.getExpiryList();
			BankNiftyData2.shortedExpiry.addAll(expiryList);
			BankNiftyData2.bnOptionData.put(optionModles.getExpiry(), optionModles);

			for (int i = 1; i < expiryList.size(); i++) {
				OptionModles om = BankNiftyUtils.getBankNiftyOptionData(expiryList.get(i));
				BankNiftyData2.bnOptionData.put(expiryList.get(i), om);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void fetchNiftyData() throws Exception {
		NiftyData.clear();
		OptionModles optionModles = NiftyUtils.getNiftyOptionData(null);
		NiftyData.optionData.put(optionModles.getExpiry(), optionModles);
		NiftyData.shortedExpiry.addAll(optionModles.getExpiryList());
		NiftyData.maxPains.put(optionModles.getExpiry(), optionModles.getMaxPainStrick());

	}
	
	public static void main(String[] args) throws Exception {
		priceUtils.fetchData();
		System.out.println("Done");
	}
}

package org.stocksrin.banknifty;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TimerTask;
import java.util.TreeSet;

import javax.ejb.Singleton;

import org.stocksrin.banknifty.option.alog2.OptionDataCollectorTask2;
import org.stocksrin.email.SendEmail;
import org.stocksrin.option.model.BankNiftyDailyMaxPain;
import org.stocksrin.option.model.OptionModles;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.NumFormater;

@Singleton
public class DailyMaxPainSaveTask extends TimerTask {

	public static void main(String[] args) {
		OptionDataCollectorTask2 optionDataCollectorTask = new OptionDataCollectorTask2();
		optionDataCollectorTask.run();

		DailyMaxPainSaveTask dailyMaxPainSaveTask = new DailyMaxPainSaveTask();
		dailyMaxPainSaveTask.run();
	}

	@Override
	public void run() {
		if (!DateUtils.isWeekEndDay()) {
			System.out.println("New Bank Nifty Daily Trade report Exprting");
			try {
				Map<String, BankNiftyDailyMaxPain> data = getData();
				StringBuilder mailBody = new StringBuilder();
				Set<String> key = data.keySet();
				for (String string : key) {
					mailBody = mailBody.append(string + ", MaxPain : " + data.get(string).getMaxPain() + "\n");
				}
				System.out.println(mailBody);
				SendEmail.sentMail("BankNifty max Pain", mailBody.toString());
			} catch (Exception e) {
				SendEmail.sentMail("ERROR ! BankNifty max Pain", e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private Map<String, BankNiftyDailyMaxPain> getData() throws Exception {

		Map<String, BankNiftyDailyMaxPain> map = new LinkedHashMap<>();

		Set<String> expiryies = BankNiftyData.getMaxPainSerieas().keySet();
		SortedSet<String> shortedSet = new TreeSet<>();
		for (String string : expiryies) {
			shortedSet.add(string);
		}

		for (String string : shortedSet) {
			OptionModles ob = BankNiftyData.getBankNiftyCurrentTimeData().get(string);
			Double bankNiftySpotPrice = BankNiftyUtils.getLSpotPriceUnderlyingSpotPrice(ob.getUnderlyingSpotPrice());

			OptionAnalysisModle optionAnalysisModle = BankNiftyData.getMaxPainSerieas().get(string);
			BankNiftyDailyMaxPain bankNiftyDailyMaxPain = convertData(optionAnalysisModle, string, bankNiftySpotPrice.toString());
			map.put(string, bankNiftyDailyMaxPain);
			CommonUtils.appendData(bankNiftyDailyMaxPain.toCSV(), APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_DAILYMAXPAIN_FILE);
		}

		return map;
	}

	private BankNiftyDailyMaxPain convertData(OptionAnalysisModle optionAnalysisModle, String expiry, String spotPrice) throws Exception {
		Double maxPain = optionAnalysisModle.getMaxPains().get(optionAnalysisModle.getMaxPains().size() - 1);
		Integer totalCE = optionAnalysisModle.getTotalCE();
		Integer totalPE = optionAnalysisModle.getTotalPE();

		String date = DateUtils.dateToString(new Date(), "ddMMMyyyy");
		BankNiftyDailyMaxPain bankNiftyDailyMaxPain = new BankNiftyDailyMaxPain(date, expiry, getExpiryType(expiry), maxPain, totalCE.toString(), totalPE.toString());
		bankNiftyDailyMaxPain.setSpot(spotPrice);
		Double pcr = ((double) totalPE / (double) totalCE);
		bankNiftyDailyMaxPain.setPcr(NumFormater.formatNumber1(pcr));
		return bankNiftyDailyMaxPain;
	}

	private String getExpiryType(String expiry) throws Exception {
		int a = DateUtils.getExpiryWeekOfMonth(expiry);
		if (a < 4) {
			return "W";
		} else {
			return "M";
		}
	}
}

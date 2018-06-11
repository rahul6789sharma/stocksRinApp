package org.stocksrin.banknifty.option.alog2;

import java.util.List;
import java.util.TimerTask;

import org.option.currency.models.Columns;
import org.option.currency.models.MaxPains;
import org.smarttrade.options.utils.Calculation;
import org.stocksrin.banknifty.BankNiftyData;
import org.stocksrin.banknifty.BankNiftyUtils;
import org.stocksrin.banknifty.LiveMaxPainModle;
import org.stocksrin.option.model.OptionModles;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;

public class OptionDataCollectorTask2 extends TimerTask {

	private double strikePriceDiff = 100d;

	@Override
	public void run() {
		try {
			if (!DateUtils.isWeekEndDay()) {
				if (CommonUtils.getEveningTime()) {
					// bankNifty first expiry data without providing expiry
					Columns columns = BankNiftyUtils.getOptionDataData(null);
					OptionModles optionModles = BankNiftyUtils.bankNiftyOptionDataWrapper(columns);

					MaxPains maxPains = Calculation.calMaxPain(optionModles.getOptionModle(), strikePriceDiff, optionModles.getExpiry());

					List<String> expiryList = optionModles.getExpiryList();

					BankNiftyData.addData(optionModles.getExpiry(), optionModles);
					BankNiftyData.addData(optionModles.getExpiry(), maxPains);
					BankNiftyData.addData(optionModles.getExpiry(), columns);
					BankNiftyData.addData(optionModles.getExpiry(), maxPains.getMaxPainStrick().toString());
					setOptionPainDataModel(optionModles.getExpiry());

					for (int i = 1; i < expiryList.size(); i++) {

						Columns col = BankNiftyUtils.getOptionDataData(expiryList.get(i));
						BankNiftyData.addData(expiryList.get(i), col);

						OptionModles optionModle = BankNiftyUtils.bankNiftyOptionDataWrapper(col);
						BankNiftyData.addData(expiryList.get(i), optionModle);

						MaxPains maxPain = Calculation.calMaxPain(optionModle.getOptionModle(), strikePriceDiff, expiryList.get(i));

						BankNiftyData.addData(expiryList.get(i), maxPain);

						BankNiftyData.addData(expiryList.get(i), maxPain.getMaxPainStrick().toString());
						setOptionPainDataModel(expiryList.get(i));
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setOptionPainDataModel(String string) {

		LiveMaxPainModle optionAnalysisModle = BankNiftyData.getMaxPainSerieas().get(string);

		String maxPainStrike = BankNiftyData.getMaxPain().get(string);
		OptionModles optionModles = BankNiftyData.getBankNiftyCurrentTimeData().get(string);
		if (optionAnalysisModle != null) {
			optionAnalysisModle.getMaxPains().add(Double.parseDouble(maxPainStrike));
			optionAnalysisModle.setTotalCE(optionModles.getTotal_ce_oi());
			optionAnalysisModle.setTotalPE(optionModles.getTotal_pe_oi());
		} else {
			LiveMaxPainModle obj = new LiveMaxPainModle();
			obj.getMaxPains().add(Double.parseDouble(maxPainStrike));
			obj.setTotalCE(optionModles.getTotal_ce_oi());
			obj.setTotalPE(optionModles.getTotal_pe_oi());
			BankNiftyData.addData(string, obj);
		}
	}

	public static void main(String[] args) {
		OptionDataCollectorTask2 optionDataCollectorTask2 = new OptionDataCollectorTask2();
		optionDataCollectorTask2.run();
	}

}
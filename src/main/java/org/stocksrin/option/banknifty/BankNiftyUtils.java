package org.stocksrin.option.banknifty;

import org.option.currency.models.MaxPains;
import org.smarttrade.options.utils.Calculation;
import org.stocksrin.option.common.model.OptionModles;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.OptionUtils;

public class BankNiftyUtils {

	// can be passed null , null means current expiry
	public static OptionModles getBankNiftyOptionData(String expiry) throws Exception {

		OptionModles optionModles = BankNiftyUtils.getOptionChain(expiry);	
		MaxPains maxPain = Calculation.calMaxPain(optionModles.getOptionModle(), APPConstant.BNF_STRIKE_DIFF, optionModles.getExpiry());
		optionModles.setMaxPainStrick(maxPain.getMaxPainStrick());
		return optionModles;
	}
	
	private static synchronized OptionModles getOptionChain(String expiry) throws Exception {
		String url = null;
		if (expiry == null) {
			url = APPConstant.BANKNIFTY_WEEKLY_OPTION_URL;
		} else {
			url = APPConstant.BANKNIFTY_WEEKLY_OPTION_URL_BY_Expiry + expiry;
		}
		return OptionUtils.getOptionChain(url, expiry);
	}


}
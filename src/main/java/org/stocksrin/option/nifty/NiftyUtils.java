package org.stocksrin.option.nifty;

import org.option.currency.models.MaxPains;
import org.smarttrade.options.utils.Calculation;
import org.stocksrin.option.common.model.OptionModles;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.OptionUtils;

public class NiftyUtils {

	public static void main(String[] args) {
		try {
			System.out.println(NiftyUtils.getOptionChain(null));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// can be passed null , null means current expiry
	public static OptionModles getNiftyOptionData(String expiry) throws Exception {

		OptionModles optionModles = NiftyUtils.getOptionChain(expiry);	
		MaxPains maxPain = Calculation.calMaxPain(optionModles.getOptionModle(), APPConstant.NF_STRIKE_DIFF, optionModles.getExpiry());
		optionModles.setMaxPainStrick(maxPain.getMaxPainStrick());
		return optionModles;
	}
	
	private static synchronized OptionModles getOptionChain(String expiry) throws Exception {
		String url = null;
		if (expiry == null) {
			url = APPConstant.NIFTY_URL;
		} else {
			url = APPConstant.NIFTY_URL_URL_BY_Expiry + expiry;
		}
		return OptionUtils.getOptionChain(url, expiry);
	}

	
}

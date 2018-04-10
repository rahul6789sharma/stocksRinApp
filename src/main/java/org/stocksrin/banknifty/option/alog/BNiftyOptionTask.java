package org.stocksrin.banknifty.option.alog;

import java.util.List;
import java.util.TimerTask;

import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;

public class BNiftyOptionTask extends TimerTask {

	@Override
	public void run() {
		if (!DateUtils.isWeekEndDay()) {

			List<BNiftyOptionLastDayTrade> lastDayTrades = CommonUtils.getBankNiftyLastTradeData2();
			ContinuesOptionPriceGetter continuesOptionPriceGetter = new ContinuesOptionPriceGetter(lastDayTrades.get(0));
			continuesOptionPriceGetter.fetchOptionData();
		}

	}

}

package org.stocksrin.banknifty.option.alog;

import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.stocksrin.banknifty.TickData;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;

public class BankNiftyCsvReportTask extends TimerTask {

	@Override
	public void run() {
		if (!DateUtils.isWeekEndDay()) {
			System.out.println("New Bank Nifty Daily Trade report Exprting");
			exportdata(ContinuesOptionPriceGetter.getOptionData());
			ContinuesOptionPriceGetter.getOptionData().clear();
		}
	}

	public synchronized void exportdata(Map<String, List<TickData>> optionDatatemp) {
		List<BNiftyOptionLastDayTrade> lastDayTrades = CommonUtils.getBankNiftyLastTradeData2();
		BNiftyOptionLastDayTrade lastDayTrade = lastDayTrades.get(0);
		for (Map.Entry<String, List<TickData>> entry : optionDatatemp.entrySet()) {

			List<TickData> data = entry.getValue();
			TickData putData = data.get(0);
			TickData callData = data.get(1);
			String csvData = entry.getKey() + "," + lastDayTrade.getPut_strike() + "," + putData.getLtp() + "," + lastDayTrade.getCall_strike() + "," + callData.getLtp();
			CommonUtils.appendData(csvData, APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_OUT_FILE);
		}

	}

}

package org.stocksrin.option.common.automation;

import java.util.TimerTask;

import org.stocksrin.email.SendEmail;
import org.stocksrin.option.common.InMemeoryStrategyDataUpdater;
import org.stocksrin.option.common.InMemoryStrategyies;
import org.stocksrin.option.common.priceUtils;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;

public class InMemeoryStrategyBuilder extends TimerTask {

	// static long timeInteval = 120000;

	static long timeInteval = 90000;

	@Override
	public void run() {

		SendEmail.sentMail("InMemeoryStrategyBuilder Intra daya Auto Build Strategy started", "");
		System.out.println("&&&&&&&&&&&&&&& InMemeoryStrategyBuilder &&&&&&&&&&&&");
		InMemoryStrategyies.clear();

		if (!DateUtils.isWeekEndDay()) {
			// update ltp
			try {
				priceUtils.fetchData();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			// InMemeoryStrategyDataUpdater inMemeoryStrategyDataUpdater = new
			// InMemeoryStrategyDataUpdater();
			// inMemeoryStrategyDataUpdater.start();

			while (CommonUtils.getEveningTimeForStrategy()) {
				try {

					// StrategyFileReader.startManualStrategies(APPConstant.STOCKSRIN__STRATEGY_DIR_TradeStrategy);
					// StrategyFileReader.startManualStrategies(APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_TradeStrategy);
					StrategyFileReader.startManualStrategies(APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_INTRADAY_TradeStrategy);
					Thread.sleep(timeInteval);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("starting");
		// priceUtils.fetchData();

		InMemeoryStrategyBuilder inMemeoryStrategyBuilder = new InMemeoryStrategyBuilder();
		inMemeoryStrategyBuilder.run();

	}
}

package org.stocksrin.option.common.automation;

import java.util.TimerTask;

import org.stocksrin.email.SendEmail;
import org.stocksrin.option.banknifty.BankNiftyData2;
import org.stocksrin.option.common.BNFStrategies;
import org.stocksrin.option.common.NiftyStrategies;
import org.stocksrin.option.common.StrategyAlgoTaskIntraDay;
import org.stocksrin.option.common.priceUtils;
import org.stocksrin.option.common.automation.strategies.BNFStrangle;
import org.stocksrin.option.nifty.NiftyData;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.DateUtils;

public class IntraDayStrategyFileBuilder extends TimerTask {

	@Override
	public void run() {
		SendEmail.sentMail("Morning Intra daya Auto Build Strategy started", "");
	
		try {
			priceUtils.fetchData();
			boolean isdata = BankNiftyData2.shortedExpiry.isEmpty();
			boolean isdata2 = NiftyData.shortedExpiry.isEmpty();
			if (isdata || isdata2) {

				priceUtils.fetchData();
			}

			if (!DateUtils.isWeekEndDay()) {
				// intra day old dir
				BNFStrangle.itmStrangle3("ITMStrangle3@INTADAY~AUTO-Strategy_BANKNIFTY", APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_INTRADAY);
				BNFStrategies.BuildStrategy3Straddle("Straddle3@INTADAY~AUTO-Strategy_BANKNIFTY", APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_INTRADAY);
				NiftyStrategies.Strategy3StraddleNifty("Straddle3@INTADAY~AUTO-Strategy_NIFTY", APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_INTRADAY);

				// intra day new dir

				BNFStrangle.itmStrangle3("ITMStrangle3@INTADAY~AUTO-Strategy_BANKNIFTY", APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_INTRADAY_TradeStrategy);
				BNFStrangle.itm2Strangle3("ITM2Strangle3@INTADAY~AUTO-Strategy_BANKNIFTY", APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_INTRADAY_TradeStrategy);
				BNFStrangle.otmStrangle3("OTMStrangle3@INTADAY~AUTO-Strategy_BANKNIFTY", APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_INTRADAY_TradeStrategy);

				BNFStrategies.BuildStrategy3Straddle("Straddle3@INTADAY~AUTO-Strategy_BANKNIFTY", APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_INTRADAY_TradeStrategy);
				NiftyStrategies.Strategy3StraddleNifty("Straddle3@INTADAY~AUTO-Strategy_NIFTY", APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_INTRADAY_TradeStrategy);
				BNFStrategies.ironButterfly("ITMIronFly@INTADAY~AUTO-Strategy_BANKNIFTY", APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_INTRADAY_TradeStrategy);
				BNFStrategies.doubleCalender("DoubleCalendar@INTADAY~AUTO-Strategy_BANKNIFTY", APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_INTRADAY_TradeStrategy);
			}
		} catch (Exception e) {
			SendEmail.sentMail("Auto Build Strategy Failed", e.getMessage());
		}

		// start calculating pnl for old dirs
		if (!DateUtils.isWeekEndDay()) {

			StrategyAlgoTaskIntraDay.startManualStrategies(APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_INTRADAY);
			StrategyAlgoTaskIntraDay.startManualStrategies(APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR);
		}

		System.out.println("Building Strategy Automatic completed");
	}

	public static void main(String[] args) {
		IntraDayStrategyFileBuilder intraDayStrategyFileBuilder = new IntraDayStrategyFileBuilder();
		intraDayStrategyFileBuilder.run();
	}
}
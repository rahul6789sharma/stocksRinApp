package org.stocksrin.option.common.automation;

import java.util.TimerTask;

import org.stocksrin.email.SendEmail;
import org.stocksrin.option.banknifty.BankNiftyData2;
import org.stocksrin.option.common.BNFStrategies;
import org.stocksrin.option.common.NiftyStrategies;
import org.stocksrin.option.common.priceUtils;
import org.stocksrin.option.common.automation.strategies.BNFStrangle;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.DateUtils;

public class BNFStrategyBuilder extends TimerTask {

	@Override
	public void run() {
		System.out.println("Building Strategy Automatic");
		try {
			boolean isdata = BankNiftyData2.shortedExpiry.isEmpty();
			if (isdata) {
				System.out.println("Data is not availbe Featching data");
				priceUtils.fetchData();
			}

			if (!DateUtils.isWeekEndDay()) {
				// Strategy3Straddle();

				BNFStrategies.BuildStrategy3Straddle("Straddle3@AUTO-Strategy_BANKNIFTY", APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR);
				BNFStrategies.BuildStrategy3Straddle("Straddle3@AUTO-Strategy_BANKNIFTY", APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_TradeStrategy);
			}
		} catch (Exception e) {
			SendEmail.sentMail("Auto Build Strategy Failed", e.getMessage());
		}

		try {
			if (!DateUtils.isWeekEndDay()) {
				BNFStrategies.Strategy300PointAwaystrangle("Strangle300Away@AUTO-Strategy_BANKNIFTY", APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR);
				BNFStrategies.Strategy300PointAwaystrangle("Strangle300Away@AUTO-Strategy_BANKNIFTY", APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_TradeStrategy);

				// protected
				BNFStrangle.strangle_300PointAway_protected_build("Strangle300AwaySafe@AUTO-Strategy_BANKNIFTY", APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR);
				BNFStrangle.strangle_300PointAway_protected_build("Strangle300AwaySafe@AUTO-Strategy_BANKNIFTY", APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_TradeStrategy);
			}
		} catch (Exception e) {
			SendEmail.sentMail("Auto Build Strategy Failed", e.getMessage());
		}

		try {
			if (!DateUtils.isWeekEndDay()) {
				BNFStrangle.itmStrangle3("ITM3Strangle@AUTO-Strategy_BANKNIFTY", APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR);
				BNFStrangle.itmStrangle3("ITM3Strangle@AUTO-Strategy_BANKNIFTY", APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_TradeStrategy);
			}
		} catch (Exception e) {
			SendEmail.sentMail("Auto Build Strategy Failed", e.getMessage());
		}

		try {
			if (!DateUtils.isWeekEndDay()) {
				NiftyStrategies.Strategy3StraddleNifty("Straddle3@Monthly-Strategy_NIFTY", APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR);
				NiftyStrategies.Strategy3StraddleNifty("Straddle3@Monthly-Strategy_NIFTY", APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_TradeStrategy);
			}
		} catch (Exception e) {
			SendEmail.sentMail("Auto Build Strategy Failed", e.getMessage());
		}
		System.out.println("Building Strategy Automatic completed");
	}

	public static void main(String[] args) throws Exception {
		BNFStrategyBuilder BNFStrategyBuilder = new BNFStrategyBuilder();
		BNFStrategyBuilder.run();
	}

}

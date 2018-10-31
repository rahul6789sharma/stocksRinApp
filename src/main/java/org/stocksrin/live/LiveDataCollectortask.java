package org.stocksrin.live;

import java.util.TimerTask;

import org.stocksrin.email.SendEmail;
import org.stocksrin.option.banknifty.BankNiftyData2;
import org.stocksrin.option.common.priceUtils;
import org.stocksrin.option.nifty.NiftyData;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.LoggerSysOut;

public class LiveDataCollectortask extends TimerTask {
	private long timeInteval = 120000;

	public static void main(String[] args) {
		LiveDataCollectortask LiveDataCollectortask = new LiveDataCollectortask();
		LiveDataCollectortask.run();
	}

	@Override
	public void run() {
		System.out.println("******* LiveDataCollectortask Started**********88");
		SendEmail.sentMail("LiveDataCollectortask Started", "");
		if (!DateUtils.isWeekEndDay()) {
			AdvancedDeclined.creaAall();
			BankNiftyData2.clear();

			if (BankNiftyData2.shortedExpiry.isEmpty() || NiftyData.shortedExpiry.isEmpty()) {
				// handle null data over weekend and after market time
				// deployment
				LoggerSysOut.print("Data loader if data is not present");
				try {
					priceUtils.fetchData();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			getData();
			while (CommonUtils.getEveningTimeForStrategy()) {

				try {
					getData();
					priceUtils.fetchData();
					Thread.sleep(timeInteval);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void getData() {
		Rows row = LiveMarketAdvancedDecline.getData();
		AdvancedDeclined.addAdvanced(row.getAdvances());
		AdvancedDeclined.addDeclined(row.getDeclines());
	}

}

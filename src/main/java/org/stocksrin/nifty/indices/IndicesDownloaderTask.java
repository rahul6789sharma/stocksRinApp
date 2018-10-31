package org.stocksrin.nifty.indices;

import java.util.List;
import java.util.TimerTask;

import org.stocksrin.email.SendEmail;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;

public class IndicesDownloaderTask extends TimerTask {

	
	@Override
	public void run() {
		if (!DateUtils.isWeekEndDay()) {
			try {
				List<NSEIndice> niftys = NiftyIndicesDataColloctor.getAllData();
				for (NSEIndice data : niftys) {

					if (data.getName().equals("NIFTY 50")) {
						CommonUtils.appendData(data.toCsv(), APPConstant.STOCKSRIN_INDICES_NIFTY);

					} else if (data.getName().equals("NIFTY BANK")) {
						CommonUtils.appendData(data.toCsv(), APPConstant.STOCKSRIN_INDICES_BANK_NIFTY);
					}
				}
			} catch (Exception e) {

				SendEmail.sentMail("CRITICAL! Index Data is not downloaded !", "ERROR " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {


	}
}

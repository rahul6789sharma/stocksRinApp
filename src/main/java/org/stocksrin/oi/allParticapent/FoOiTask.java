package org.stocksrin.oi.allParticapent;

import java.util.Date;
import java.util.TimerTask;

import org.stocksrin.email.SendEmail;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;

public class FoOiTask extends TimerTask {

	public static void main(String[] args) {
		try {
			download();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void download() throws Exception {
		String date = DateUtils.dateToString(new Date(), "ddMMyyyy");
		String fileName = "fao_participant_oi_" + date + ".csv";
		String fileDIR = APPConstant.FO_OI_DIR + fileName;
		String url = APPConstant.NSE_FO_OI_ALLPARTICAPENT + fileName;
		CommonUtils.downloadFile(url, fileDIR);
	}

	@Override
	public void run() {
		try {
			if (!DateUtils.isWeekEndDay()) {
				download();
			}
		} catch (Exception e) {
			SendEmail.sentMail("ERROR! FO OI All Particapent Data", "ERROR " + e.getMessage());
			e.printStackTrace();
		}
	}
}
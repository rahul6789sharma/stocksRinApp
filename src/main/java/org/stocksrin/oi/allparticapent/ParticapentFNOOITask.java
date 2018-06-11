package org.stocksrin.oi.allparticapent;

import java.io.File;
import java.util.Date;
import java.util.TimerTask;

import org.stocksrin.email.SendEmail;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.FileUtils;
import org.stocksrin.utils.LoggerSysOut;

public class ParticapentFNOOITask extends TimerTask {

	public static void main(String[] args) {
		try {
			String dateMatch = DateUtils.dateToString(new Date(), "MMMM dd");
			System.out.println("dateMatch " + dateMatch);
			// String file = download();
			// String file =
			// "C:\\Users\\rahulksh\\stocksRin_CONF\\stocksRinData\\Participant_OI_Data\\NSE_FO_OI\\May2018\\fao_participant_oi_04062018.csv";
			// getDatefromFileName(file);
			// OIUtils.collectAllDateForDay(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			if (!DateUtils.isWeekEndDay()) {
				String file = download();
				OIUtils.collectAllDateForDay(file);
				SendEmail.sentMail("FNO OI All Particapent Data SUCCESS", "SUCCESS file " + file);
			}
		} catch (Exception e) {
			SendEmail.sentMail("CRITICAL! FO OI All Particapent Data", "ERROR " + e.getMessage());
			e.printStackTrace();
		}

	}

	// return file dir
	private static String download() throws Exception {

		String date = DateUtils.dateToString(new Date(), "ddMMyyyy");
		String fileName = "fao_participant_oi_" + date + ".csv";
		String url = APPConstant.NSE_FO_OI_ALLPARTICAPENT + fileName;
		// String url =
		// "https://www.nseindia.com/content/nsccl/fao_participant_oi_04062018.csv";
		System.out.println("url" + url);
		String date2 = DateUtils.dateToString(new Date(), "MMMyyyy");
		String dir = APPConstant.FO_OI_DIR + date2;

		boolean status = FileUtils.makeDir(dir);

		if (status) {
			LoggerSysOut.print("New Dir is create for Month " + dir);
		} else {
			LoggerSysOut.print("Dir is already presnt " + dir);
		}

		String file = dir + File.separator + fileName;

		if (!FileUtils.isFileExits(file)) {
			CommonUtils.downloadFile(url, file);
			if (matchFileDate(file)) {
				return file;
			}
		} else {
			LoggerSysOut.print("Already downloaded " + file);
		}
		return null;
	}

	private static boolean matchFileDate(String file) throws Exception {
		String[] line = CommonUtils.getCSVData_FirstLine(file);
		String[] a = line[0].split("as on");
		String dateMatch = DateUtils.dateToString(new Date(), "MMMM dd");
		System.out.println("dateMatch " + dateMatch);
		System.out.println("a[1].trim() " + a[1].trim());
		boolean status = dateMatch.equalsIgnoreCase(a[1].trim());
		if (!status) {
			FileUtils.delete(file);
			throw new Exception("Market is closed or its holiday Today or File is not generated in NSE! file " + file + ", will be delted");
		}
		return status;
	}
}
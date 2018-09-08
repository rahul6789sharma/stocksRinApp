package org.stocksrin.bhavcopy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.stocksrin.email.SendEmail;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.ExceptionUtils;
import org.stocksrin.utils.FileUtils;
import org.stocksrin.utils.UnzipUtility;

public class DerivativesBhavCopyOIDownloaderTask extends TimerTask {

	public static void main(String[] args) throws Exception {
		downloadLoadPast();
	}

	@Override
	public void run() {
		download();
	}

	public static void download() {
		if (!DateUtils.isWeekEndDay()) {
			try {
				String yr = DateUtils.getCurrentYear();
				String month = DateUtils.getCurrentMonth().toUpperCase();
				String date = DateUtils.getCurrentDatetwoDigit();
				String fileName = "fo" + date + month + yr + "bhav.csv.zip";
				String urlPart = yr + "/" + month + "/" + fileName;
				String yearDir = APPConstant.STOCKSRIN_NSE_CONF_DIR_DERIVATIVES_OI + yr;
				String url = APPConstant.NSE_DERIVATIVES_OI_URL + urlPart;

				String completedir = APPConstant.STOCKSRIN_NSE_CONF_DIR_DERIVATIVES_OI + yr + File.separator;
				String completeFileName = completedir + fileName;
				FileUtils.makeDir(yearDir);
				System.out.println("url  " + url);
				boolean status = FileUtils.downloadFile(url, completeFileName);
				System.out.println("Bhav Copy url " + url);
				System.out.println("Bhav Copy Downloaded " + completeFileName);
				if (status) {
					UnzipUtility.unzip(completeFileName, completedir);
				} else {
					SendEmail.sentMail("CRITICAL! Bhav Copy is not downloaded", "url " + url + "\n completeFileName" + completeFileName);
				}
			} catch (Exception e) {
				e.printStackTrace();
				SendEmail.sentMail("CRITICAL! Bhav Copy is not downloaded", "ERROR " + ExceptionUtils.getStackTrace(e));
			}
		}

	}

	public static void downloadLoadPast() throws Exception {
		String month = "JUL";
		String dir = "C:\\Users\\rahulksh\\stocksRin_CONF\\stocksRinData\\DERIVATIVES_OI\\2018\\" + month;
		for (Integer i = 1; i <= 31; i++) {
			if (i < 10) {
				String val = "0" + i;
				String fileName = "fo" + val + month + "2018bhav.csv";
				String completeFileName = dir + "\\" + fileName;
				System.out.println(completeFileName);
				File file = new File(completeFileName);
				if (file.exists()) {
					filterData(completeFileName,"NIFTY");
				}
				System.out.println();
			} else {
				String val = i.toString();
				String fileName = "fo" + val + month + "2018bhav.csv";
				String completeFileName = dir + "\\" + fileName;
				System.out.println(completeFileName);
				File file = new File(completeFileName);
				System.out.println(file.exists());
				if (file.exists()) {
					filterData(completeFileName,"NIFTY");
				}
			}
		}

	}

	public static void filterData(String file, String insturement) throws Exception {

		List<BhavCopyOIModel> bhavCopyOIModels = new ArrayList<>(3);
		// List<String[]> lst =getCSVData("C:\\Users\\rahulksh\\stocksRin_CONF\\stocksRinData\\DERIVATIVES_OI\\2018\\JUN\\fo01JUN2018bhav.csv");
		List<String[]> lst = CommonUtils.getCSVData(file);
		for (String[] strings : lst) {
			if (strings.length != 15) {
				throw new Exception("Bhav Derivative file broken");
			}
			if (strings[0].equals("FUTIDX") && strings[1].equals("NIFTY")) {
				BhavCopyOIModel bhavCopyOIModel = new BhavCopyOIModel();
				bhavCopyOIModel.setInstrument(strings[0]);
				bhavCopyOIModel.setSymbol(strings[1]);
				bhavCopyOIModel.setExpiry_dt(strings[2]);
				bhavCopyOIModel.setStrike_pr(strings[3]);
				bhavCopyOIModel.setOption_typ(strings[4]);
				bhavCopyOIModel.setOpen(Double.parseDouble(strings[5]));
				bhavCopyOIModel.setHigh(Double.parseDouble(strings[6]));
				bhavCopyOIModel.setLow(Double.parseDouble(strings[7]));
				bhavCopyOIModel.setClose(Double.parseDouble(strings[8]));
				bhavCopyOIModel.setSettle_pr(Double.parseDouble(strings[9]));
				bhavCopyOIModel.setContracts(Integer.parseInt(strings[10]));
				bhavCopyOIModel.setVal_inlakh(Double.parseDouble(strings[11]));
				bhavCopyOIModel.setOpen_int(Integer.parseInt(strings[12]));
				bhavCopyOIModel.setChg_in_oi(Integer.parseInt(strings[13]));
				bhavCopyOIModel.setTimestamp(strings[14]);
				bhavCopyOIModels.add(bhavCopyOIModel);
			}

		}

		convert(bhavCopyOIModels);

	}

	public static StocksrinOIModel convert(List<BhavCopyOIModel> bhavCopyOIModels) throws Exception {
		StocksrinOIModel stocksrinOIModel = new StocksrinOIModel();
		if (bhavCopyOIModels.size() != 3) {
			throw new Exception("Derivatives Bhav Copy broken");
		}

		int oiTotal = 0;
		int totalVol = 0;
		for (int i = 0; i < 3; i++) {
			BhavCopyOIModel obj = bhavCopyOIModels.get(i);

			if (i == 0) {
				stocksrinOIModel.setDate(obj.getTimestamp());
				stocksrinOIModel.setTimeStamp(DateUtils.getEpocTime(obj.getTimestamp()));
				stocksrinOIModel.setInstrument(obj.getInstrument());
				stocksrinOIModel.setSymbol(obj.getSymbol());
				stocksrinOIModel.setClose(obj.getClose());
				stocksrinOIModel.setExpiry_dt1(obj.getExpiry_dt());
				stocksrinOIModel.setOpen_int1(obj.getOpen_int());
				stocksrinOIModel.setVolume1(obj.getContracts());
			} else if (i == 1) {
				stocksrinOIModel.setExpiry_dt2(obj.getExpiry_dt());
				stocksrinOIModel.setOpen_int2(obj.getOpen_int());
				stocksrinOIModel.setVolume2(obj.getContracts());
			} else if (i == 2) {
				stocksrinOIModel.setExpiry_dt3(obj.getExpiry_dt());
				stocksrinOIModel.setOpen_int3(obj.getOpen_int());
				stocksrinOIModel.setVolume3(obj.getContracts());
			} else {

			}
			oiTotal = oiTotal + obj.getOpen_int();
			totalVol = totalVol + obj.getContracts();
		}

		stocksrinOIModel.setTotalOI(oiTotal);
		stocksrinOIModel.setTotalVol(totalVol);
		// System.out.println(stocksrinOIModel.toCSV());
		CommonUtils.appendData(stocksrinOIModel.toCSV(), "C:\\Users\\rahulksh\\stocksRin_CONF\\stocksRinData\\DERIVATIVES_OI\\2018\\NIFTY_FUTURE_OI.csv");
		return stocksrinOIModel;
	}

	public static void downloadLoadPast2() {
		String month = "JUL";

		String url = "https://www.nseindia.com/content/historical/DERIVATIVES/2018/" + month + "/";
		String dir = "C:\\Users\\rahulksh\\stocksRin_CONF\\stocksRinData\\DERIVATIVES_OI\\2018\\" + month;

		for (Integer i = 1; i <= 31; i++) {
			if (i < 10) {
				// System.out.println("0" + i);
				String val = "0" + i;
				String fileName = "fo" + val + month + "2018bhav.csv.zip";
				String url1 = url + fileName;
				System.out.println(url1);
				String completeFileName = dir + "\\" + fileName;
				try {
					boolean status = FileUtils.downloadFile(url1, completeFileName);
					if (status) {
						UnzipUtility.unzip(completeFileName, dir);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// System.out.println(i.toString());
				String val = i.toString();
				String fileName = "fo" + val + month + "2018bhav.csv.zip";
				String url1 = url + fileName;
				System.out.println(url1);
				String completeFileName = dir + "\\" + fileName;
				try {
					boolean status = FileUtils.downloadFile(url1, completeFileName);
					if (status) {
						UnzipUtility.unzip(completeFileName, dir);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

}

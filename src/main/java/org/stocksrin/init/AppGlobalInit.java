package org.stocksrin.init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.stocksrin.bhavcopy.BhavForRestModle;
import org.stocksrin.bhavcopy.Data;
import org.stocksrin.fiidii.FIIDIIDataModle;
import org.stocksrin.fiidii.FIIDIIDataYrModle;
import org.stocksrin.fiidii.FIIDIIdataModelMap;
import org.stocksrin.nifty.indices.NSEIndice;
import org.stocksrin.oi.allparticapent.OIUtils;
import org.stocksrin.oi.allparticapent.ReadData;
import org.stocksrin.option.banknifty.BankNiftyData2;
import org.stocksrin.option.common.model.DailyMaxPain;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.FileUtils;
import org.stocksrin.utils.LoggerSysOut;

@Singleton
@Startup
public class AppGlobalInit {

	private boolean initStatus;

	public boolean isInitStatus() {
		return initStatus;
	}

	@PostConstruct
	public void init() {
		LoggerSysOut.print("########## AppGlobalInit Starting ###############");
		String csvFile = APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_DAILYMAXPAIN_FILE;
		pullMAXPAIN_WEEKLY_data(csvFile);

		pullFII_DII_data();
		String month = DateUtils.getPreviousMonth(-1);
		String yr = DateUtils.getCurrentYear();
		String file = APPConstant.FILE_NAME_FII_DIR_MONTHLY + month + "_" + yr + ".csv";

		pullPreviousMonthFII_DII_data(file);

		String yrfile = APPConstant.FILE_NAME_FII_DIR_YEARLY + yr + ".csv";

		pullYearlyFII_DII_data(yrfile);
		initStatus = true;

		ReadData.fetchData();
		pullFutureOIData();
		pullParticipantWiseOIData();
		pullNiftyIndicesData();
		pullBankNiftyIndicesData();
		LoggerSysOut.print("########## AppGlobalInit Started ###############");
	}

	private static void pullYearlyFII_DII_data(String file) {

		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(file));) {
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (i != 0) {
					// use comma as separator
					FIIDIIDataYrModle fiiDIIDataYrModle = CommonUtils.getFIIModelYearlyFromCSV(line);
					FIIDIIdataModelMap.addYearlyData(fiiDIIDataYrModle.getMonth(), fiiDIIDataYrModle);
				}
				i++;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}

	}

	private static void pullPreviousMonthFII_DII_data(String file) {

		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(file));) {
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (i != 0) {
					// use comma as separator
					FIIDIIDataModle fIIDIIDataModle = CommonUtils.getFIIModelFromCSV(line);
					FIIDIIdataModelMap.addPreviousMonthData(fIIDIIDataModle.getDate(), fIIDIIDataModle);
				}
				i++;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}

	}

	private static void pullFII_DII_data() {

		String line = "";
		String file = APPConstant.FILE_NAME_FII_DIR_MONTHLY + DateUtils.getCurrentMonth() + "_" + DateUtils.getCurrentYear() + ".csv";
		try (BufferedReader br = new BufferedReader(new FileReader(file));) {
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (i != 0) {
					// use comma as separator
					LoggerSysOut.print("Reading file " + file);
					if (!line.isEmpty()) {
						FIIDIIDataModle fIIDIIDataModle = CommonUtils.getFIIModelFromCSV(line);
						FIIDIIdataModelMap.addData(fIIDIIDataModle.getDate(), fIIDIIDataModle);
					}
				}
				i++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}

	}

	private static synchronized void pullMAXPAIN_WEEKLY_data(String csvFile) {
		System.out.println("start");
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile));) {
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (i != 0) {
					// use comma as separator
					if (!line.isEmpty()) {
						DailyMaxPain bankNiftyDailyMaxPain = CommonUtils.getMAXPAINFromCSV(line);
						BankNiftyData2.dailyMaxPain.put(bankNiftyDailyMaxPain.getDate(), bankNiftyDailyMaxPain);
					}
				}
				i++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static synchronized void pullFutureOIData() {
		String yr = DateUtils.getCurrentYear();
		String bnf = APPConstant.STOCKSRIN_NSE_CONF_DIR_DERIVATIVES_OI + yr + File.separator + "BANKNIFTY_FUTURE_OI.csv";
		String nf = APPConstant.STOCKSRIN_NSE_CONF_DIR_DERIVATIVES_OI + yr + File.separator + "NIFTY_FUTURE_OI.csv";

		List<BhavForRestModle> bnfData = CommonUtils.getStocksrinOIModel(bnf);
		List<BhavForRestModle> niftydata = CommonUtils.getStocksrinOIModel(nf);
		Data.getBankNIftystocksrinOIModels().addAll(bnfData);
		Data.getNsIftystocksrinOIModels().addAll(niftydata);
	}

	private static synchronized void pullParticipantWiseOIData() {
		String month;
		try {
			month = DateUtils.dateToString(new Date(), "MMMyyyy");
			String dir = APPConstant.FO_OI_DIR + month;
			System.out.println(dir);
			List<String> files = FileUtils.listFilesForFolder(new File(dir));
			for (String file : files) {
				System.out.println(dir + File.separator + file);
				OIUtils.collectAllDateForDay(dir + File.separator + file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static synchronized void pullNiftyIndicesData() {
		List<String[]> lst = CommonUtils.getCSVData(APPConstant.STOCKSRIN_INDICES_NIFTY);
		List<NSEIndice> index = new ArrayList<>();
		for (String[] strings : lst) {
			NSEIndice nseIndice = new NSEIndice();
			nseIndice.setDate(strings[0]);
			nseIndice.setChange(strings[1]);
			nseIndice.setpChange(strings[2]);
			nseIndice.setLastPrice(strings[3]);
			index.add(nseIndice);
			org.stocksrin.nifty.indices.Data.niftyData.put(nseIndice.getDate(), nseIndice);
		}
	}

	private static synchronized void pullBankNiftyIndicesData() {
		List<String[]> lst = CommonUtils.getCSVData(APPConstant.STOCKSRIN_INDICES_BANK_NIFTY);
		List<NSEIndice> index = new ArrayList<>();
		for (String[] strings : lst) {
			NSEIndice nseIndice = new NSEIndice();
			nseIndice.setDate(strings[0]);
			nseIndice.setChange(strings[1]);
			nseIndice.setpChange(strings[2]);
			nseIndice.setLastPrice(strings[3]);
			index.add(nseIndice);
			org.stocksrin.nifty.indices.Data.bankNIftyData.put(nseIndice.getDate(), nseIndice);
		}
	}

	public static void main(String[] args) {
		String csvFile = APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_DAILYMAXPAIN_FILE;
		AppGlobalInit.pullMAXPAIN_WEEKLY_data(csvFile);
	}
}
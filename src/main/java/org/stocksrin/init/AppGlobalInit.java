package org.stocksrin.init;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.stocksrin.banknifty.BankNiftyData;
import org.stocksrin.fiidii.FIIDIIDataModle;
import org.stocksrin.fiidii.FIIDIIDataYrModle;
import org.stocksrin.fiidii.FIIDIIdataModelMap;
import org.stocksrin.oi.allparticapent.ReadData;
import org.stocksrin.option.model.BankNiftyDailyMaxPain;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.LoggerSysOut;

@Singleton
@Startup
public class AppGlobalInit {

	private boolean initStatus;

	public boolean isInitStatus() {
		return initStatus;
	}

	public static void main(String[] args) {

		AppGlobalInit appGlobalInit = new AppGlobalInit();
		appGlobalInit.init();
		LoggerSysOut.print(FIIDIIdataModelMap.getfIIDIIPreviousMOnthData());
		LoggerSysOut.print(FIIDIIdataModelMap.getfIIDIIDataModleData());
	}

	@PostConstruct
	public void init() {
		LoggerSysOut.print("StocksRin Init");
		String csvFile = APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_DAILYMAXPAIN_FILE2;
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
		//ReadData.readOptionData(APPConstant.OPTION_OI_Participant_FILE);
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
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile));) {
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (i != 0) {
					// use comma as separator
					if (!line.isEmpty()) {
						BankNiftyDailyMaxPain bankNiftyDailyMaxPain = CommonUtils.getMAXPAINFromCSV(line);
						// BankNiftyData.addDate(bankNiftyDailyMaxPain);
						BankNiftyData.getWeeklyMaxPain().add(bankNiftyDailyMaxPain);
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

	/*
	 * private static synchronized void pullMAXPAIN_data(String csvFile) {
	 * 
	 * String line = ""; try (BufferedReader br = new BufferedReader(new
	 * FileReader(csvFile));) { int i = 0; while ((line = br.readLine()) !=
	 * null) { if (i != 0) { // use comma as separator BankNiftyDailyMaxPain
	 * bankNiftyDailyMaxPain = CommonUtils.getMAXPAINFromCSV(line);
	 * BankNiftyData.addDate(bankNiftyDailyMaxPain); } i++; }
	 * 
	 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch
	 * (IOException e) { e.printStackTrace(); } }
	 */

}
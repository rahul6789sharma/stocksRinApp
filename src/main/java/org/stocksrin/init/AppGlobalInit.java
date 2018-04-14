package org.stocksrin.init;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.stocksrin.banknifty.BankNiftyData;
import org.stocksrin.fiidii.FIIDIIDataModle;
import org.stocksrin.fiidii.FIIDIIdataModelMap;
import org.stocksrin.option.model.BankNiftyDailyMaxPain;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;

@Singleton
@Startup
public class AppGlobalInit {

	public static void main(String[] args) {
		String csvFile = APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_DAILYMAXPAIN_FILE;
		pullMAXPAIN_data(csvFile);

		Set<String> keys = BankNiftyData.getMaxpPainSortedData().keySet();
		for (String string : keys) {
			System.out.println(string + " -> " + BankNiftyData.getMaxpPainSortedData().get(string));
		}
	}

	@PostConstruct
	public void init() {
		System.out.println("StocksRin Init");
		String csvFile = APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_DAILYMAXPAIN_FILE;
		pullMAXPAIN_data(csvFile);

		pullFII_DII_data();
		String month = DateUtils.getPreviousMonth(-1);
		String yr = DateUtils.getCurrentYear();
		String file = APPConstant.FILE_NAME_FII_DIR_MONTHLY + month + "_" + yr + ".csv";
		pullPreviousMonthFII_DII_data(file);
	}

	public static void pullPreviousMonthFII_DII_data(String file) {

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

	public static void pullFII_DII_data() {

		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(APPConstant.FILE_NAME_FII_DIR_MONTHLY + DateUtils.getCurrentMonth() + "_" + DateUtils.getCurrentYear() + ".csv"));) {
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (i != 0) {
					// use comma as separator
					FIIDIIDataModle fIIDIIDataModle = CommonUtils.getFIIModelFromCSV(line);
					FIIDIIdataModelMap.addData(fIIDIIDataModle.getDate(), fIIDIIDataModle);
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

	public static synchronized void pullMAXPAIN_data(String csvFile) {

		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile));) {
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (i != 0) {
					// use comma as separator
					BankNiftyDailyMaxPain bankNiftyDailyMaxPain = CommonUtils.getMAXPAINFromCSV(line);
					BankNiftyData.addDate(bankNiftyDailyMaxPain);
				}
				i++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

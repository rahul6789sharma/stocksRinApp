package org.stocksrin.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.stocksrin.banknifty.option.alog2.StrategyModel;
import org.stocksrin.banknifty.option.alog2.StrategyModel.OptionType;
import org.stocksrin.fiidii.FIIDIIDataModle;
import org.stocksrin.oi.future.NiftyOIDataModle;
import org.stocksrin.option.model.BankNiftyDailyMaxPain;

public class CommonUtils {

	public static void downloadFile(String url, String toFile) throws Exception {
		ReadableByteChannel rbc = null;

		try (FileOutputStream fos = new FileOutputStream(toFile);) {

			URL website = new URL(url);
			rbc = Channels.newChannel(website.openStream());

			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			rbc.close();

		} catch (IOException e) {
			throw new Exception("ERROR ! File download " + e.getMessage());
		} finally {

			if (rbc != null) {
				rbc.close();
			}
		}

	}

	private static final int MARKET_HR = 15;
	private static final int MARKET_MINUTE = 35;

	public static boolean getEveningTime() {
		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.HOUR_OF_DAY, MARKET_HR);
		today.set(Calendar.MINUTE, MARKET_MINUTE);
		today.set(Calendar.SECOND, 0);

		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("IST"));

		if (now.after(today)) {
			System.out.println("Time is more then 3:30 pm");
			return false;
		} else {
			return true;
		}

	}

	public static Map<Integer, List<StrategyModel>> getBankNiftyStrategy() {
		List<StrategyModel> lst = getBankNiftyStrategyModel();

		Map<Integer, List<StrategyModel>> strategy = new HashMap<>();

		for (StrategyModel strategyModel : lst) {
			Integer[] count = getStartegyCount(strategyModel.getStrategySerial());
			Integer integer = count[0];

			List<StrategyModel> lst1 = strategy.get(integer);
			if (lst1 == null) {
				List<StrategyModel> r = new ArrayList<>();
				r.add(strategyModel);
				strategy.put(integer, r);
			} else {
				lst1.add(strategyModel);
			}
		}

		return strategy;
	}

	private static Integer[] getStartegyCount(String strategyCount) {
		Integer[] count = new Integer[2];
		String[] a = strategyCount.split("\\.");
		count[0] = Integer.parseInt(a[0]);
		count[1] = Integer.parseInt(a[1]);
		return count;
	}

	public static List<StrategyModel> getBankNiftyStrategyModel() {
		List<StrategyModel> result = new ArrayList<>(5);
		List<String[]> lst = CommonUtils.getCSVData(APPConstant.STOCKSRIN_NSE_CONF_DIR_BANKNIFTY_BNIFTY_STRATEGY_FILE);
		for (String[] strings : lst) {
			StrategyModel strategyModel = new StrategyModel();
			strategyModel.setStrategySerial(strings[0]);
			strategyModel.setExpiry(strings[1]);

			if (strings[2].equals("CALL")) {
				strategyModel.setType(OptionType.CALL);
			} else {
				strategyModel.setType(OptionType.PUT);
			}

			strategyModel.setStrike(Double.parseDouble(strings[3]));
			strategyModel.setClose_price(Double.parseDouble(strings[4]));
			strategyModel.setQuantity(Integer.parseInt(strings[5]));
			strategyModel.setTarget(Double.parseDouble(strings[6]));
			strategyModel.setStopLoss(Double.parseDouble(strings[7]));
			strategyModel.setSpot_close(Double.parseDouble(strings[8]));
			strategyModel.setDes(strings[9]);
			result.add(strategyModel);

		}
		return result;
	}

	public static NiftyOIDataModle getOIModelFromCSV(String csvData) throws Exception {

		try {
			String cvsSplitBy = ",";
			String[] data = csvData.split(cvsSplitBy);

			NiftyOIDataModle niftyOIDataModle = new NiftyOIDataModle();
			niftyOIDataModle.setDate(data[0]);

			niftyOIDataModle.setVol1(Integer.parseInt(data[1]));
			niftyOIDataModle.setVol1PercentageChange(Double.parseDouble(data[2]));

			niftyOIDataModle.setOi1(Integer.parseInt(data[3]));
			niftyOIDataModle.setOi1PercentageChange(Double.parseDouble(data[4]));

			niftyOIDataModle.setVol2(Integer.parseInt(data[5]));
			niftyOIDataModle.setVol2PercentageChange(Double.parseDouble(data[6]));

			niftyOIDataModle.setOi2(Integer.parseInt(data[7]));
			niftyOIDataModle.setOi2PercentageChange(Double.parseDouble(data[8]));

			niftyOIDataModle.setVolTotal(Integer.parseInt(data[9]));
			niftyOIDataModle.setVolTotalPercentageChange(Double.parseDouble(data[10]));

			niftyOIDataModle.setOiTotal(Integer.parseInt(data[11]));
			niftyOIDataModle.setOiTotalPercentageChange(Double.parseDouble(data[12]));

			niftyOIDataModle.setNifty(Double.parseDouble(data[13]));
			niftyOIDataModle.setNiftyChange(Double.parseDouble(data[14]));

			return niftyOIDataModle;
		} catch (Exception e) {
			throw new Exception("ERROR! getOIModelFromCSV " + e.getMessage());
		}

	}

	public static BankNiftyDailyMaxPain getMAXPAINFromCSV(String csvData) {

		String cvsSplitBy = ",";
		BankNiftyDailyMaxPain bankNiftyDailyMaxPain = new BankNiftyDailyMaxPain();
		String[] data = csvData.split(cvsSplitBy);
		bankNiftyDailyMaxPain.setDate(data[0]);
		bankNiftyDailyMaxPain.setExpiry(data[1]);
		bankNiftyDailyMaxPain.setExpiryType(data[2]);
		bankNiftyDailyMaxPain.setMaxPain(Double.parseDouble(data[3]));
		bankNiftyDailyMaxPain.setTotalCEOI(data[4]);
		bankNiftyDailyMaxPain.setTotalPEOI(data[5]);
		bankNiftyDailyMaxPain.setPcr(data[6]);
		bankNiftyDailyMaxPain.setOi(data[7]);
		bankNiftyDailyMaxPain.setChangeInOI(data[8]);
		bankNiftyDailyMaxPain.setPerChangeInOI(data[9]);
		bankNiftyDailyMaxPain.setSpot(data[10]);
		bankNiftyDailyMaxPain.setChange(data[11]);

		return bankNiftyDailyMaxPain;
	}

	public static FIIDIIDataModle getFIIModelFromCSV(String csvData) {

		String cvsSplitBy = ",";
		FIIDIIDataModle fIIDIIDataModle = new FIIDIIDataModle();
		String[] data = csvData.split(cvsSplitBy);
		fIIDIIDataModle.setDate(data[0]);
		fIIDIIDataModle.setFii_BuyValue(data[1]);
		fIIDIIDataModle.setFii_SellValue(data[2]);
		fIIDIIDataModle.setFii_net(data[3]);
		fIIDIIDataModle.setDii_BuyValue(data[4]);
		fIIDIIDataModle.setDii_SellValue(data[5]);
		fIIDIIDataModle.setDii_net(data[6]);
		fIIDIIDataModle.setNiftyprice(data[7]);
		fIIDIIDataModle.setNiftyChange(data[8]);
		fIIDIIDataModle.setStocks_Advance(data[9]);
		fIIDIIDataModle.setStocks_Decline(data[10]);
		return fIIDIIDataModle;
	}

	public static int totalNumberOfLine(String filepath) {
		int linenumber = 0;
		try {

			File file = new File(filepath);

			if (file.exists()) {

				FileReader fr = new FileReader(file);
				LineNumberReader lnr = new LineNumberReader(fr);

				while (lnr.readLine() != null) {
					linenumber++;
				}

				System.out.println("Total number of lines : " + linenumber);

				lnr.close();

			} else {
				System.out.println("File does not exists!");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return linenumber;
	}

	public static void appendData2(String data, String fileName) {

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			File file = new File(fileName);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				throw new RuntimeException(fileName + " File not exist");
			}

			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);

			bw.write(data);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}

	}

	public static void appendData(String data, String fileName) {

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			File file = new File(fileName);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				throw new RuntimeException(fileName + " File not exist");
			}

			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);

			bw.write("\n" + data);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}

	}

	public static List<String[]> getCSVData(String csvFile) {

		String line = "";
		String cvsSplitBy = ",";
		List<String[]> lst = new ArrayList<>(2);
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile));) {

			int i = 0;
			while ((line = br.readLine()) != null) {
				if (i != 0) {
					// use comma as separator
					String[] data = line.split(cvsSplitBy);
					lst.add(data);
				}
				i++;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lst;
	}
}

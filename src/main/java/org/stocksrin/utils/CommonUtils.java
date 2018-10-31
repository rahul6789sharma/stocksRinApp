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
import java.util.Set;
import java.util.TimeZone;

import org.stocksrin.bhavcopy.BhavForRestModle;
import org.stocksrin.fiidii.FIIDIIDataModle;
import org.stocksrin.fiidii.FIIDIIDataYrModle;
import org.stocksrin.oi.future.NiftyOIDataModle;
import org.stocksrin.option.common.model.DailyMaxPain;
import org.stocksrin.option.common.model.Strategy;
import org.stocksrin.option.common.model.Strategy.UnderLying;
import org.stocksrin.option.common.model.StrategyModel;
import org.stocksrin.option.common.model.StrategyModel.OptionType;

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
			e.printStackTrace();
			throw new Exception("ERROR ! File download " + e.getMessage());
		} finally {

			if (rbc != null) {
				rbc.close();
			}
		}

	}

	private static final int MARKET_HR = 15;
	private static final int MARKET_MINUTE = 35;

	// return true in market hr// after market hr it will retunr false
	public static boolean getEveningTime() {
		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.HOUR_OF_DAY, MARKET_HR);
		today.set(Calendar.MINUTE, MARKET_MINUTE);
		today.set(Calendar.SECOND, 0);

		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("IST"));

		if (now.after(today)) {
			LoggerSysOut.print("Time is more then 3:30 pm");
			return false;

		} else {
			return true;
		}
	}

	// return true in market hr// after market hr it will retunr false
	public static boolean isTimeLessThen(Integer hr, Integer min) {
		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.HOUR_OF_DAY, hr);
		today.set(Calendar.MINUTE, min);
		today.set(Calendar.SECOND, 0);

		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("IST"));

		if (now.after(today)) {
			LoggerSysOut.print("Time is more then" + min);
			return false;
		} else {
			return true;
		}
	}

	// return true in market hr// after market hr it will retunr false
	public static boolean getEveningTimeForStrategy() {
		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		today.set(Calendar.HOUR_OF_DAY, MARKET_HR);
		today.set(Calendar.MINUTE, 15);
		today.set(Calendar.SECOND, 0);

		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("IST"));

		if (now.after(today)) {
			LoggerSysOut.print("Time is more then 3:30 pm");
			return false;
		} else {
			return true;
		}
	}

	public static void main(String[] args) throws Exception {
		Map<String, Strategy> data = getStrategy2(APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_TradeStrategy);
		System.out.println(data.keySet());
		Set<String> keys = data.keySet();
		for (String string : keys) {
			System.out.println(data.get(string));
		}
	}

	public static Map<String, Strategy> getStrategy2(String dir) {

		Map<String, List<StrategyModel>> map = getStrategy(dir);

		Map<String, Strategy> strategys = new HashMap<>();

		Set<String> keys = map.keySet();
		for (String string : keys) {
			List<StrategyModel> lst = map.get(string);
			StrategyModel data = null;
			if (!lst.isEmpty()) {
				data = lst.get(0);
			}

			String underlaying = string.split("_")[1];
			UnderLying underLyingName = UnderLying.OTHERS;
			if (underlaying.equals("BANKNIFTY.csv")) {
				underLyingName = UnderLying.BANKNIFTY;
			} else if (underlaying.equals("NIFTY.csv")) {
				underLyingName = UnderLying.NIFTY;
			}
			Strategy strategy = new Strategy(underLyingName);
			if (data != null) {
				strategy.setTradeDate(data.getTradeDate());
				strategy.setTradeSpotPrice(data.getSpot_close());
			}
			String name = string.split("-Strategy")[0];
			strategy.setStrategyModels(lst);
			strategy.setStrategyName(name);
			strategy.setDir(dir);
			strategy.setFileName(string);

			strategys.put(string, strategy);
		}

		return strategys;
	}

	public static Map<String, List<StrategyModel>> getStrategy(String dir) {
		Map<String, List<StrategyModel>> strategy = new HashMap<>();

		List<String> lst = FileUtils.listFilesForFolder(new File(dir));

		for (String string : lst) {

			String file = dir + string;
			List<StrategyModel> str = getStrategyModel(file);

			strategy.put(string, str);
		}

		return strategy;
	}
	/*
	 * public static synchronized List<StrategyModel> getStrategyModel(String
	 * file, String underlying) { List<StrategyModel> result = new
	 * ArrayList<>(5);
	 * 
	 * List<String[]> lst = CommonUtils.getCSVData(file);
	 * 
	 * try { for (String[] strings : lst) { StrategyModel strategyModel = new
	 * StrategyModel(); strategyModel.setUnderlying(underlying);
	 * strategyModel.setStrategySerial(strings[0]);
	 * strategyModel.setExpiry(strings[1]);
	 * 
	 * if (strings[2].equals("CALL")) { strategyModel.setType(OptionType.CALL);
	 * } else { strategyModel.setType(OptionType.PUT); }
	 * 
	 * strategyModel.setStrike(Double.parseDouble(strings[3]));
	 * strategyModel.setAvgPrice(Double.parseDouble(strings[4]));
	 * strategyModel.setQuantity(Integer.parseInt(strings[5]));
	 * strategyModel.setTarget(Double.parseDouble(strings[6]));
	 * strategyModel.setStopLoss(Double.parseDouble(strings[7]));
	 * strategyModel.setSpot_close(Double.parseDouble(strings[8]));
	 * strategyModel.setDes(strings[9]);
	 * strategyModel.setStatus(Boolean.parseBoolean(strings[10]));
	 * strategyModel.setTraded_IV(Double.parseDouble(strings[11]));
	 * strategyModel.setTradeDate(strings[12]); result.add(strategyModel); } }
	 * catch (Exception e) { e.printStackTrace(); } return result; }
	 */

	public static synchronized List<StrategyModel> getStrategyModel(String file) {
		List<StrategyModel> result = new ArrayList<>(5);

		List<String[]> lst = CommonUtils.getCSVData(file);

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
			strategyModel.setAvgPrice(Double.parseDouble(strings[4]));
			strategyModel.setQuantity(Integer.parseInt(strings[5]));
			strategyModel.setTarget(Double.parseDouble(strings[6]));
			strategyModel.setStopLoss(Double.parseDouble(strings[7]));
			strategyModel.setSpot_close(Double.parseDouble(strings[8]));
			strategyModel.setDes(strings[9]);
			strategyModel.setStatus(Boolean.parseBoolean(strings[10]));
			strategyModel.setTraded_IV(Double.parseDouble(strings[11]));
			strategyModel.setTradeDate(strings[12]);
			result.add(strategyModel);

		}
		return result;
	}

	/*
	 * private static Integer[] getStartegyCount(String strategyCount) {
	 * Integer[] count = new Integer[2]; String[] a =
	 * strategyCount.split("\\."); count[0] = Integer.parseInt(a[0]); count[1] =
	 * Integer.parseInt(a[1]); return count; }
	 */

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

	public static DailyMaxPain getMAXPAINFromCSV(String csvData) {

		String cvsSplitBy = ",";
		DailyMaxPain bankNiftyDailyMaxPain = new DailyMaxPain();
		String[] data = csvData.split(cvsSplitBy);
		bankNiftyDailyMaxPain.setDate(data[0]);
		bankNiftyDailyMaxPain.setExpiry(data[1]);
		bankNiftyDailyMaxPain.setMaxPain(Double.parseDouble(data[2]));
		bankNiftyDailyMaxPain.setTotalCEOI(Integer.parseInt(data[3]));
		bankNiftyDailyMaxPain.setTotalPEOI(Integer.parseInt(data[4]));
		bankNiftyDailyMaxPain.setSpot(Double.parseDouble(data[5]));
		bankNiftyDailyMaxPain.setChange(data[6]);

		bankNiftyDailyMaxPain.setHighest_oi_ce(Double.parseDouble(data[7]));
		bankNiftyDailyMaxPain.setHighest_oi_ce_value(Integer.parseInt(data[8]));

		bankNiftyDailyMaxPain.setHighest_oi_pe(Double.parseDouble(data[9]));
		bankNiftyDailyMaxPain.setHighest_oi_pe_value(Integer.parseInt(data[10]));

		bankNiftyDailyMaxPain.setHighest_change_oi_ce(Double.parseDouble(data[11]));
		bankNiftyDailyMaxPain.setHighest_change_oi_ce_value(Integer.parseInt(data[12]));

		bankNiftyDailyMaxPain.setHighest_change_oi_pe(Double.parseDouble(data[13]));
		bankNiftyDailyMaxPain.setHighest_change_oi_pe_value(Integer.parseInt(data[14]));

		return bankNiftyDailyMaxPain;
	}

	public static FIIDIIDataModle getFIIModelFromCSV(String csvData) {
		FIIDIIDataModle fIIDIIDataModle = new FIIDIIDataModle();
		String cvsSplitBy = ",";

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

	public static FIIDIIDataYrModle getFIIModelYearlyFromCSV(String csvData) {

		String cvsSplitBy = ",";
		FIIDIIDataYrModle fiiDIIDataYrModle = new FIIDIIDataYrModle();
		String[] data = csvData.split(cvsSplitBy);
		fiiDIIDataYrModle.setMonth(data[0]);
		fiiDIIDataYrModle.setFii_BuyValue(data[1]);
		fiiDIIDataYrModle.setFii_SellValue(data[2]);
		fiiDIIDataYrModle.setFii_net(data[3]);
		fiiDIIDataYrModle.setDii_BuyValue(data[4]);
		fiiDIIDataYrModle.setDii_SellValue(data[5]);
		fiiDIIDataYrModle.setDii_net(data[6]);
		fiiDIIDataYrModle.setNiftyStartMonth(data[7]);
		fiiDIIDataYrModle.setNiftyStartEnd(data[8]);
		fiiDIIDataYrModle.setNifty_change(data[9]);

		return fiiDIIDataYrModle;
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

				LoggerSysOut.print("Total number of lines : " + linenumber);

				lnr.close();

			} else {
				LoggerSysOut.print("File does not exists!");
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

		File file = new File(fileName);
		// if file doesnt exists, then create it
		if (!file.exists()) {
			throw new RuntimeException(fileName + " File not exist");
		}

		try (FileWriter fw = new FileWriter(file.getAbsoluteFile(), true); BufferedWriter bw = new BufferedWriter(fw);) {

			bw.write("\n" + data);

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public static String[] getCSVData_FirstLine(String csvFile) {

		String line = "";
		String cvsSplitBy = ",";
		List<String[]> lst = new ArrayList<>(2);
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile));) {

			int i = 0;
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] data = line.split(cvsSplitBy);
				return data;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	// ll not read rirst row
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

	public static List<BhavForRestModle> getStocksrinOIModel(String csvData) {
		List<BhavForRestModle> bhavForRestModles = new ArrayList<>();
		List<String[]> lst = CommonUtils.getCSVData(csvData);
		for (String[] strings : lst) {
			BhavForRestModle bhavForRestModle = new BhavForRestModle();
			// stocksrinOIModel.setDate(strings[0]);
			bhavForRestModle.setTimeStamp(Long.parseLong(strings[1]));

			bhavForRestModle.setClose(Double.parseDouble(strings[4]));

			bhavForRestModle.setTotalOI(Integer.parseInt(strings[14]));
			bhavForRestModle.setTotalVol(Integer.parseInt(strings[15]));

			bhavForRestModles.add(bhavForRestModle);
		}

		return bhavForRestModles;

	}

}
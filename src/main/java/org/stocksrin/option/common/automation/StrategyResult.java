package org.stocksrin.option.common.automation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.stocksrin.option.common.StrategyWrite;
import org.stocksrin.option.common.model.Strategy;
import org.stocksrin.option.common.model.StrategyModel;
import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.FileUtils;

public class StrategyResult {

	public static Map<String, List<StrategyModel>> strategies = new ConcurrentHashMap<>();
	public static Map<String, String> map = new ConcurrentHashMap<>();

	public static void main(String[] args) throws Exception {
		String dir = APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR;
		System.out.println(dir);
		writeStrategyFile("demo", dir);
	}

	public static void writeStrategyFile2(Strategy strategy) throws Exception {
		String date = DateUtils.dateToString(new Date(), "MMMyyyy");
		// String dir = APPConstant.STOCKSRIN__STRATEGY_DIR_RESULT + date +
		// File.separator;
		String dir1 = strategy.getDir();
		String dir = dir1.substring(0, dir1.length() - 1);// removing last //

		String name = strategy.getFileName().split(".csv")[0];
		String resultDir = dir + "_Result" + File.separator + date + File.separator;
		String file = resultDir + name + ".txt";

		FileUtils.makeDir(resultDir);
		FileUtils.makeFile(file);

		StringBuilder result = StrategyWrite.print(strategy);

		if (result != null) {
			appendData("\n********************************************************************************************\n", file);
			appendData(result.toString(), file);
		}

	}

	public static void writeStrategyFile(String strategyName, String outDIR) throws Exception {
		String date = DateUtils.dateToString(new Date(), "MMMyyyy");
		String dir = null;
		if (outDIR.equals(APPConstant.STOCKSRIN__STRATEGY_DIR)) {
			dir = APPConstant.STOCKSRIN__STRATEGY_DIR_RESULT + date + File.separator;
		} else if (outDIR.equals(APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR)) {
			dir = APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_RESULT + date + File.separator;
		} else if (outDIR.equals(APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_INTRADAY)) {
			dir = APPConstant.STOCKSRIN__STRATEGY_AUTO_DIR_INNTRADAY_RESULT + date + File.separator;
		}

		String file = dir + strategyName + ".txt";
		FileUtils.makeDir(dir);
		FileUtils.makeFile(file);
		String data = map.get(strategyName);
		if (data != null) {
			appendData("\n********************************************************************************************\n", file);
			appendData(map.get(strategyName), file);
		}

	}

	private static void appendData(String data, String fileName) {

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
}

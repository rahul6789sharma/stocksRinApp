package org.stocksrin.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FileUtils {

	private FileUtils() {

	}

	public static void delete(String filePath) {
		try {

			File file = new File(filePath);

			if (file.delete()) {
				LoggerSysOut.print(file.getName() + " is deleted!");
			} else {
				LoggerSysOut.print("Delete operation is failed.");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public static void backup(String sourceFile, String targetFile) throws IOException {
		Path source = Paths.get(sourceFile);
		Path target = Paths.get(targetFile);

		Files.copy(source, target);

	}

	public static boolean isFileExits(String filepath) {
		File file = new File(filepath);
		return file.exists();
	}

	public static boolean makeFile(String filepath) {
		try {

			File file = new File(filepath);
			if (file.createNewFile()) {
				LoggerSysOut.print("File is created! " + filepath);
				return true;
			} else {
				LoggerSysOut.print("File already exists." + filepath);
				return false;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean makeDir(String dirPath) {
		File file = new File(dirPath);
		if (!file.exists()) {
			if (file.mkdir()) {
				LoggerSysOut.print("Directory is created! " + dirPath);
				return true;
			} else {
				LoggerSysOut.print("Failed to create directory! " + dirPath);
				return false;
			}
		} else {
			return false;
		}

	}

	public static void writeDataAsJson(Object data, String fileName) {
		ObjectMapper mapper = new ObjectMapper();
		try {

			File file = new File(fileName);
			// file.createNewFile();
			mapper.writerWithDefaultPrettyPrinter().writeValue(file, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void copyFile(File source, File dest) throws IOException {
		Files.copy(source.toPath(), dest.toPath());
	}

	public static List<String> getSortedFileNamesfromFolder(final File folder) {
		List<Date> dates = new ArrayList<>();
		List<String> names = FileUtils.listFilesForFolder(folder);

		for (String string : names) {
			try {
				dates.add(DateUtils.stringToDate(string, "dd_MM_yyyy"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Collections.sort(dates);
		List<String> sortedFileName = new ArrayList<>();
		for (Date d : dates) {
			try {
				sortedFileName.add(DateUtils.dateToString(d, "dd_MM_yyyy"));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return sortedFileName;
	}

	public static List<String> listFilesForFolder(final File folder) {
		List<String> names = new ArrayList<>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				names.add(fileEntry.getName());
			}
		}
		return names;
	}

	public static List<String> getSortedFileNamesInFolder(final File folder) {
		List<Date> dates = new ArrayList<>();
		List<String> names = FileUtils.listFilesForFolder(folder);

		for (String string : names) {
			try {
				dates.add(DateUtils.stringToDate(string.substring(18, 28), "dd_MM_yyyy"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Collections.sort(dates);

		List<String> sortedFileName = new ArrayList<>();
		for (Date d : dates) {
			try {
				sortedFileName.add(APPConstant.STOCKSRIN_NSE_CONF_DIR_BHAVDIR + "sec_bhavdata_full_" + DateUtils.dateToString(d, "dd_MM_yyyy") + ".csv");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return sortedFileName;
	}

	public static List<String> readFnOList(String filePath) {
		List<String> result = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(new File(filePath));
			while (scanner.hasNextLine()) {

				String a = scanner.nextLine().toString();

				result.add(a);

			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static List<NseStock> readBhavCopycsvFile(String csvFile) {

		String line = "";
		String cvsSplitBy = ",";
		List<NseStock> lst = new ArrayList<>();
		int i = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] stock = line.split(cvsSplitBy);

				if (i != 0) {

					String symbol = stock[0].trim();
					String series = stock[1].trim();
					String date = stock[2].trim();
					String previous_close_price = stock[3].trim();
					String open_price = stock[4].trim();
					String high_price = stock[5].trim();
					String low_price = stock[6].trim();
					String CLOSE_PRICE = stock[8].trim();
					String TOTAL_TRADED_QUANTITY = stock[10].trim();
					String TOTAL_Delivery_QUANTITY = stock[13].trim();
					String DELIV_PER = stock[14].trim();

					if (previous_close_price.equals("-")) {
						previous_close_price = "0";
					}
					if (open_price.equals("-")) {
						open_price = "0";
					}
					if (high_price.equals("-")) {
						high_price = "0";
					}
					if (low_price.equals("-")) {
						low_price = "0";
					}
					if (CLOSE_PRICE.equals("-")) {
						CLOSE_PRICE = "0";
					}
					if (TOTAL_TRADED_QUANTITY.equals("-")) {
						TOTAL_TRADED_QUANTITY = "0";
					}
					if (TOTAL_Delivery_QUANTITY.equals("-")) {
						TOTAL_Delivery_QUANTITY = "0";
					}
					if (DELIV_PER.equals("-")) {
						DELIV_PER = "0";
					}

					NseStock nseStock = new NseStock(symbol, series, date, Float.parseFloat(previous_close_price), Float.parseFloat(open_price), Float.parseFloat(high_price),
							Float.parseFloat(low_price), Float.parseFloat(CLOSE_PRICE), Long.parseLong(TOTAL_TRADED_QUANTITY), Long.parseLong(TOTAL_Delivery_QUANTITY), Float.parseFloat(DELIV_PER));
					lst.add(nseStock);
				}

				i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return lst;
	}

	public static void main(String[] args) {
		String file = "C:\\nse\\fandO\\fno.txt";
		LoggerSysOut.print(readFnOList(file));

	}
}

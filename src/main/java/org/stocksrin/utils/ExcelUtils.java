package org.stocksrin.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class ExcelUtils {

	public static boolean verifyFileAndInternalDate(File file) throws Exception {
		boolean status = false;
		String line = "";
		String cvsSplitBy = ",";

		int i = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] stock = line.split(cvsSplitBy);

				if (i != 0) {

					String date = stock[2].trim();

					String d = DateUtils.dateToString(new Date(), "dd-MMM-yyyy");
					System.out.println("File Date" + date);
					System.out.println("internal  Date" + d);
					if (date.equals(d)) {
						status = true;
					}

				}
				if (i == 1) {
					break;
				}

				i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}
}

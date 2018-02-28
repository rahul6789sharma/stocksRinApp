package org.stocksrin.fiidii;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.CommonUtils;

@Singleton
@Startup
public class FIIDIIdataModelMap {

	private static Map<String, FIIDIIDataModle> fIIDIIDataModleData = new LinkedHashMap<>();

	@PostConstruct
	public void init() {
		String csvFile = APPConstant.FILE_NAME_FII_DII;
		pullFII_DII_data(csvFile);
	}

	public static void addData(String date, FIIDIIDataModle fIIDIIDataModle) {
		fIIDIIDataModleData.put(date, fIIDIIDataModle);

	}

	public static Map<String, FIIDIIDataModle> getfIIDIIDataModleData() {
		return fIIDIIDataModleData;
	}

	public static void setfIIDIIDataModleData(Map<String, FIIDIIDataModle> fIIDIIDataModleData) {
		FIIDIIdataModelMap.fIIDIIDataModleData = fIIDIIDataModleData;
	}

	public static void pullFII_DII_data(String csvFile) {

		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile));) {
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (i != 0) {
					// use comma as separator
					FIIDIIDataModle fIIDIIDataModle = CommonUtils.getModelFromCSV(line);
					addData(fIIDIIDataModle.getDate(), fIIDIIDataModle);
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

}
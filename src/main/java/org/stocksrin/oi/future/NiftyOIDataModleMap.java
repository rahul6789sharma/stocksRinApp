package org.stocksrin.oi.future;

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
import org.stocksrin.utils.LoggerSysOut;

@Singleton
@Startup
public class NiftyOIDataModleMap {

	private static Map<String, NiftyOIDataModle> oiDataMap = new LinkedHashMap<>();

	public static Map<String, NiftyOIDataModle> getOiDataMap() {
		return oiDataMap;
	}

	@PostConstruct
	public void init() {
		String csvFile = APPConstant.FILE_NAME_NIFTY_OI_FILE;
		//pullOIdata(csvFile);
	}

	public static void addData(String date, NiftyOIDataModle niftyOIDataModle) {
		oiDataMap.put(date, niftyOIDataModle);
	}

	private static void pullOIdata(String csvFile) throws Exception {

		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile));) {
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (i != 0) {
					// use comma as separator
					NiftyOIDataModle niftyOIDataModle = CommonUtils.getOIModelFromCSV(line);
					addData(niftyOIDataModle.getDate(), niftyOIDataModle);
				}
				i++;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		NiftyOIDataModleMap NiftyOIDataModleMap = new NiftyOIDataModleMap();
		NiftyOIDataModleMap.init();
		LoggerSysOut.print(oiDataMap);
	}
}

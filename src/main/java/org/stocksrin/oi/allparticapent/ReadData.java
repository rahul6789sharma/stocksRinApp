package org.stocksrin.oi.allparticapent;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.FileUtils;
import org.stocksrin.utils.LoggerSysOut;

public class ReadData {

	public static void main(String[] args) throws Exception {

	}

	public static void fetchData() {
		try {
			String date2 = DateUtils.dateToString(new Date(), "MMMyyyy");
			String dir = APPConstant.FO_OI_DIR + date2;
			LoggerSysOut.print("OI Participant Data dir : " + dir);
			List<String> lst = FileUtils.listFilesForFolder(new File(dir));
			LoggerSysOut.print(lst.size());
			for (String string : lst) {
				OIUtils.collectAllDateForDay(dir + File.separator + string);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

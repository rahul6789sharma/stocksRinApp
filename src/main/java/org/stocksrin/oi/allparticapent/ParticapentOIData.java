package org.stocksrin.oi.allparticapent;

import java.io.File;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.FileUtils;

public class ParticapentOIData {

	public static Map<String, List<ParticipantOIModle>> data = new LinkedHashMap<>();

	public static void main(String[] args) {
		String month;
		try {
			month = DateUtils.dateToString(new Date(), "MMMyyyy");
			String dir = APPConstant.FO_OI_DIR + month;
			System.out.println(dir);
			List<String> files =FileUtils.listFilesForFolder(new File(dir));
			for (String file : files) {
				System.out.println(dir+File.separator+file);
				OIUtils.collectAllDateForDay(dir+File.separator+file);
			}
			System.out.println(data.keySet());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

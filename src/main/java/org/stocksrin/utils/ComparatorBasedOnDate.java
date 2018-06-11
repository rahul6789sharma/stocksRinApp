package org.stocksrin.utils;

import java.util.Comparator;
import java.util.Date;

public class ComparatorBasedOnDate implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = DateUtils.stringToDate(o1, APPConstant.DATEFORMATE_BN_EXPIRY);
			date2 = DateUtils.stringToDate(o2, APPConstant.DATEFORMATE_BN_EXPIRY);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return date1.compareTo(date2);
	}

}

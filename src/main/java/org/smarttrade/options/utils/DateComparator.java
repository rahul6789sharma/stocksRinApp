package org.smarttrade.options.utils;

import java.util.Comparator;
import java.util.Date;

public class DateComparator implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
		Date date1=DateUtils.getDateFromString(o1);
		Date date2=DateUtils.getDateFromString(o2);
		return date1.compareTo(date2);
		
	}

	/*public static void main(String[] args) {
		Date date1=DateUtils.getDateFromString("28MAY2017");
		Date date2=DateUtils.getDateFromString("29MAY2017");
		LoggerSysOut.print(date1.compareTo(date2));
		//LoggerSysOut.print(d);
		List<String> lst = new ArrayList<>();
		lst.add("29JUNE2017");
		lst.add("28MAY2017");
		lst.add("20MAY2017");
		lst.add("29MAY2017");
		Collections.sort(lst new DateComparator());
		LoggerSysOut.print(lst);
	}
*/
}

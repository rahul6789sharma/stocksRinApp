package org.smarttrade.options.utils;

import java.util.Comparator;
import java.util.Date;

public class DateComparator2 implements Comparator<Date> {

	@Override
	public int compare(Date o1, Date o2) {

		return o1.compareTo(o2);

	}
}

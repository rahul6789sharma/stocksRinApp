package org.stocksrin.utils;

import java.util.SortedSet;
import java.util.TreeSet;

public class Test {

	public static void main(String[] args) {
		String a = "10MAY2018";
		String c = "26APR2018";
		String b = "19APR2018";
		String d = "3MAY2018";

		SortedSet<String> personsSortedByName = new TreeSet<>(new ComparatorBasedOnDate());
		personsSortedByName.add(a);
		personsSortedByName.add(b);
		personsSortedByName.add(c);
		personsSortedByName.add(d);
		
		LoggerSysOut.print(personsSortedByName);

	}

}

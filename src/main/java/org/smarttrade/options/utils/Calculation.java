package org.smarttrade.options.utils;

import java.util.ArrayList;
import java.util.List;

import org.option.currency.models.Column;
import org.option.currency.models.MaxPain;
import org.option.currency.models.MaxPains;

public class Calculation {

	public static MaxPains maxPain(List<Column> lst) {

		MaxPains maxPains = new MaxPains();
		Double strickDiff = 0.25;
		List<MaxPain> maxPainList = new ArrayList<MaxPain>();
		for (int i = 0; i < lst.size(); i++) {

			String a = lst.get(i).getStrike_Price().trim().substring(1, 6); // removing
																			// special
																			// character

			String b = lst.get(i).getPE_OI().replaceAll(",", "").trim();
			String c = lst.get(i).getCE_OI().replaceAll(",", "").trim();

			if (b.equals("-")) {
				b = "0";
			}

			if (c.equals("-")) {
				c = "0";
			}

			MaxPain maxPain = new MaxPain(Double.parseDouble(a), Double.parseDouble(c), Double.parseDouble(b));
			Double total = 0.0;

			Double PutCuresult = 0.0;
			for (int j = i; j < lst.size(); j++) {
				String a1 = lst.get(j).getCE_OI().replaceAll(",", "").trim();

				if (a1.equals("-")) {
					a1 = "0";
				}

				PutCuresult = PutCuresult + ((Double.parseDouble(a1)) * ((strickDiff * j) - (strickDiff * i)));
				maxPain.setCumulativePe(PutCuresult);
			}
			Double CallCuresult = 0.0;
			for (int j = 0; j < i; j++) {
				String a1 = lst.get(j).getCE_OI().replaceAll(",", "").trim();
				if (a1.equals("-")) {
					a1 = "0";
				}
				CallCuresult = CallCuresult + ((Double.parseDouble(a1)) * ((strickDiff * i) - (strickDiff * j)));
				maxPain.setCumulativeCe(CallCuresult);
			}

			total = PutCuresult + CallCuresult;
			maxPain.setTotal(total);
			maxPainList.add(maxPain);
		}
		maxPains.setDataSet(maxPainList);
		Double maxPainStrick = findMaxPain(maxPainList);
		maxPains.setMaxPainStrick(maxPainStrick);
		return maxPains;
	}

	public static Double findMaxPain(List<MaxPain> maxPainList) {
		Double smallest = maxPainList.get(0).getTotal();
		Double strickPrice = 0.0;
		for (MaxPain maxPain : maxPainList) {

			if (smallest > maxPain.getTotal()) {
				smallest = maxPain.getTotal();
				strickPrice = maxPain.getStrickPrice();
			}
		}
		return strickPrice;

	}
}
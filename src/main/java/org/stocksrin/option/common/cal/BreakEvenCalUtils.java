package org.stocksrin.option.common.cal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.stocksrin.option.common.model.Strategy;
import org.stocksrin.option.common.model.StrategyModel;
import org.stocksrin.option.common.model.StrategyModel.OptionType;

public class BreakEvenCalUtils {

	public static void findBreakEven(Strategy startgy) {

		Set<Double> keys1 = startgy.getAllStrikePNL().keySet();
		TreeSet<Double> breakEvenStrikes = new TreeSet<>();
		for (Double double1 : keys1) {
			if (startgy.getAllStrikePNL().get(double1) > 0) {
				breakEvenStrikes.add(double1);
			}
		}

		Double maxLossLowerSide = Double.MIN_VALUE;
		Double maxLossUpperSide = Double.MIN_VALUE;
		Double maxProfit = Double.MIN_VALUE;
		System.out.println(breakEvenStrikes);
		if(breakEvenStrikes.isEmpty()){
			return;
		}
		for (Double double1 : keys1) {
			if (double1 < breakEvenStrikes.first() && maxLossLowerSide > startgy.getAllStrikePNL().get(double1)) {
				maxLossLowerSide = startgy.getAllStrikePNL().get(double1);

			} else if (double1 > breakEvenStrikes.last() && maxLossLowerSide > startgy.getAllStrikePNL().get(double1)) {
				maxLossUpperSide = startgy.getAllStrikePNL().get(double1);
			} else if (double1 >= breakEvenStrikes.first() && double1 <= breakEvenStrikes.last() && maxProfit < startgy.getAllStrikePNL().get(double1)) {

				maxProfit = startgy.getAllStrikePNL().get(double1);

			}
		}
	/*	System.out.println("maxLossLowerSide " + maxLossLowerSide);
		System.out.println("maxLossUpperSide " + maxLossUpperSide);
		System.out.println("breakEvenStrikes : " + breakEvenStrikes);
		System.out.println("maxProfit : " + maxProfit);

		System.out.println("lower : " + breakEvenStrikes.first());
		System.out.println("higher : " + breakEvenStrikes.last());

		System.out.println("Range : " + (breakEvenStrikes.last() - breakEvenStrikes.first()));*/
		startgy.setMaxLossLowerSide(maxLossLowerSide);
		startgy.setMaxLossUpperSide(maxLossUpperSide);
		startgy.setMaxProfit(maxProfit);
		startgy.setLowerBreakEven(breakEvenStrikes.first());
		startgy.setUpperBreakEven(breakEvenStrikes.last());
	}

	public static void calculatebreakEven(Strategy startgy) {

		List<StrategyModel> lst = startgy.getStrategyModels();
		Map<Double, Double> totalPNL = new LinkedHashMap<>();

		List<Double> allStrikes = new ArrayList<>();

		double start = 20000;
		for (int i = 0; i < 100; i++) {
			allStrikes.add(start + (100 * i));

		}

		for (StrategyModel strategyModel : lst) {

			if (strategyModel.getType().equals(OptionType.CALL)) {

				callIntenricsValue(allStrikes, strategyModel.getStrike(), strategyModel.getIntenrsic());

				Set<Double> keys = strategyModel.getIntenrsic().keySet();
				for (Double double1 : keys) {
					double totalPl = 0;
					double intenresicValue = strategyModel.getIntenrsic().get(double1);
					if (strategyModel.getQuantity() < 0) {
						double pl = ((strategyModel.getAvgPrice() - intenresicValue) * Math.abs(strategyModel.getQuantity()));
						totalPl = totalPl + pl;
					} else {
						double pl = ((intenresicValue - strategyModel.getAvgPrice()) * Math.abs(strategyModel.getQuantity()));
						totalPl = totalPl + pl;
					}
					if (totalPNL.get(double1) == null) {
						totalPNL.put(double1, totalPl);
					} else {
						double p = totalPNL.get(double1) + totalPl;
						totalPNL.put(double1, p);
					}

				}
			}

			if (strategyModel.getType().equals(OptionType.PUT)) {

				putIntenricsValue(allStrikes, strategyModel.getStrike(), strategyModel.getIntenrsic());

				Set<Double> keys = strategyModel.getIntenrsic().keySet();
				for (Double double1 : keys) {
					double totalPl = 0;
					double intenresicValue = strategyModel.getIntenrsic().get(double1);
					if (strategyModel.getQuantity() < 0) {
						// sell
						double pl = ((strategyModel.getAvgPrice() - intenresicValue) * Math.abs(strategyModel.getQuantity()));
						totalPl = totalPl + pl;
					} else {
						// buy
						double pl = ((intenresicValue - strategyModel.getAvgPrice()) * Math.abs(strategyModel.getQuantity()));
						totalPl = totalPl + pl;
					}
					if (totalPNL.get(double1) == null) {
						totalPNL.put(double1, totalPl);
					} else {
						double p = totalPNL.get(double1) + totalPl;
						totalPNL.put(double1, p);
					}

				}
				// System.out.println("*******");
			}
		}
		// System.out.println("######################");
		startgy.setAllStrikePNL(totalPNL);

	}

	private static void callIntenricsValue(List<Double> allStrikes, double tradedStrike, Map<Double, Double> intenrsic) {

		for (Double double1 : allStrikes) {
			double inte = Math.max((double1 - tradedStrike), 0);
			intenrsic.put(double1, inte);
		}
	}

	private static void putIntenricsValue(List<Double> allStrikes, double tradedStrike, Map<Double, Double> intenrsic) {

		for (Double double1 : allStrikes) {
			double inte = Math.max((tradedStrike - double1), 0);
			intenrsic.put(double1, inte);
		}
	}
}

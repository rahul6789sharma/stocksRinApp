package org.stocksrin.option.common.automation;

import java.util.Map;
import java.util.Set;

import org.stocksrin.option.common.InMemoryStrategyies;
import org.stocksrin.option.common.cal.BreakEvenCalUtils;
import org.stocksrin.option.common.model.LockObject;
import org.stocksrin.option.common.model.Strategy;
import org.stocksrin.option.common.model.Strategy.UnderLying;
import org.stocksrin.utils.CommonUtils;

public class StrategyFileReader {

	public static synchronized void startManualStrategies(String starttegyDir) {
		try {
			try {

				LockObject.getWriteLock();

				// reading strategy frm file
				Map<String, Strategy> strategyMap = CommonUtils.getStrategy2(starttegyDir);
				Set<String> strategies = strategyMap.keySet();

				for (String string : strategies) {

					Strategy startgy = strategyMap.get(string);

					if (startgy.getUnderlying().equals(UnderLying.BANKNIFTY)) {

						try {
							BreakEvenCalUtils.calculatebreakEven(startgy);
							// System.out.println("All strikes" +
							// startgy.getAllStrikePNL());
							BreakEvenCalUtils.findBreakEven(startgy);
						} catch (Exception e) {
							e.printStackTrace();
						}

						// Result
						// update in inmemeory and after that we have to update
						// ltp
						// based on dir it ll be updated in intra day or
						// positional
						InMemoryStrategyies.put(string, startgy, starttegyDir);

					} else if (startgy.getUnderlying().equals(UnderLying.NIFTY)) {

						// Result
						InMemoryStrategyies.put(string, startgy, starttegyDir);

					} else {
						System.out.println("****** Underlying unknown ****");
					}

				}
			} finally {
				LockObject.realseWriteLock();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

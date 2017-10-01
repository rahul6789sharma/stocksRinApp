package org.option.currency.tasks;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.TimerTask;

import org.option.currency.models.Column;
import org.option.currency.models.Columns;
import org.option.currency.usdinr.UsdInrService;
import org.option.db.USDINRDbFacade;
import org.smarttrade.options.utils.DateUtils;

public class USDINROptionDBTask extends TimerTask {

	@Override
	public void run() {
		try {
			System.out.println("********** Started Executing USDINR DB Task at ********  " + DateUtils.getTodayDateTime());
			Calendar now = Calendar.getInstance(TimeZone.getTimeZone("IST"));

			if (now.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || now.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				System.out.println(" WeekEnds Not Inserting Data to DB");

			} else {

				Set<String> expiryList = UsdInrService.getInstance().getExpiries();

				for (String string : expiryList) {
					// Columns columns = UsdInrData.getData().get(string);
					Columns columns = UsdInrService.getInstance().getUSDINROC(string);

					List<Column> dataSet = columns.getDataset();

					for (Column column : dataSet) {
						int ceOiValue = 0;
						int peOiValue = 0;

						if (!column.getCE_OI().equals("-")) {
							String value = column.getCE_OI().replaceAll(",", "");
							ceOiValue = Integer.parseInt(value);
						}

						if (!column.getPE_OI().equals("-")) {
							String value = column.getPE_OI().replaceAll(",", "");
							peOiValue = Integer.parseInt(value);
						}

						String strick = column.getStrike_Price().trim().substring(1, 6);
						float strickPrice = Float.parseFloat(strick);
						Date expirye = DateUtils.getDateFromString(string);

						USDINRDbFacade.getInstance().save(expirye, strickPrice, ceOiValue, peOiValue);
					}

				}
			}
			System.out.println("******** Completed Executing USDINR DB Task at *********** " + DateUtils.getTodayDateTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

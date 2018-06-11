package org.stocksrin.oi.allparticapent;

import java.util.ArrayList;
import java.util.List;

import org.stocksrin.utils.CommonUtils;
import org.stocksrin.utils.DateUtils;

public class OIUtils {

	public static void collectAllDateForDay(String file) throws Exception {
		String date3 = getDatefromFileName(file);
		List<ParticipantOIModle> participantOIModles = new ArrayList<>(4);
		List<String[]> data = CommonUtils.getCSVData(file);
		for (String[] strings : data) {
			ParticipantOIModle participantOIModle = getData(strings, date3);
			if (participantOIModle != null) {
				participantOIModles.add(participantOIModle);
			}
		}
		ParticapentOIData.data.put(date3, participantOIModles);

	}

	public static ParticipantOIModle getData(String[] data, String date) throws Exception {

		if (data.length != 15) {
			throw new Exception("ERROR! ParticapentFnoOITask csv broke, data fields count should be 15,  but now it is: " + data.length);
		}

		if (data[0].equalsIgnoreCase("Client Type")) {
			if (!data[5].equalsIgnoreCase("Option Index Call Long") && !data[6].equalsIgnoreCase("Option Index Put Long") && !data[7].equalsIgnoreCase("Option Index Call Short")
					&& !data[8].equalsIgnoreCase("Option Index Put Short")) {
				throw new Exception("ERROR! ParticapentFnoOITask csv Broke ");
			}
		} else if (!data[0].equalsIgnoreCase("TOTAL")) {
			return getParticipantOIModle(date, ParticipantType.valueOf(data[0].toUpperCase()), data);

		}
		return null;

	}

	private static String getDatefromFileName(String fileName) throws Exception {
		String[] a = fileName.split("fao_participant_oi_");

		String date = a[1].substring(0, 8);
		String month = date.substring(2, 4);

		String m = DateUtils.getMonth(month);
		// 20May2018
		return date.substring(0, 2) + m + date.substring(4, date.length());

	}

	private static ParticipantOIModle getParticipantOIModle(String date, ParticipantType participantType, String[] data) throws Exception {
		ParticipantOIModle participantOIModle = new ParticipantOIModle(date, participantType);
		try {

			participantOIModle.setFutureIndexLong(Integer.parseInt(data[1]));
			participantOIModle.setFutureIndexShort(Integer.parseInt(data[2]));

			participantOIModle.setFutureStockLong(Integer.parseInt(data[3]));
			participantOIModle.setFutureStockShort(Integer.parseInt(data[4]));

			participantOIModle.setOptionIndexCallLong(Integer.parseInt(data[5]));
			participantOIModle.setOptionIndexPutLong(Integer.parseInt(data[6]));
			participantOIModle.setOptionIndexCallShort(Integer.parseInt(data[7]));
			participantOIModle.setOptionIndexPutShort(Integer.parseInt(data[8]));

			participantOIModle.setOptionStockCallLong(Integer.parseInt(data[9]));
			participantOIModle.setOptionStockPutLong(Integer.parseInt(data[10]));
			participantOIModle.setOptionStockCallShort(Integer.parseInt(data[11]));
			participantOIModle.setOptionStockPutShort(Integer.parseInt(data[12]));

			participantOIModle.setTotalLongContracts(Integer.parseInt(data[13]));
			participantOIModle.setTotalShortContracts(Integer.parseInt(data[14]));
		} catch (Exception e) {
			throw new Exception("ERROR! ParticapentFnoOITask csv Broke! can not parse csv" + e.getMessage());
		}
		return participantOIModle;
	}
}

package org.stocksrin.oi.allparticapent;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.stocksrin.utils.APPConstant;
import org.stocksrin.utils.DateUtils;
import org.stocksrin.utils.FileUtils;
import org.stocksrin.utils.LoggerSysOut;

public class ReadData {

	public static void main(String[] args) throws Exception {

	}

	public static void fetchData() {
		try {
			String date2 = DateUtils.dateToString(new Date(), "MMMyyyy");
			String dir = APPConstant.FO_OI_DIR + date2;
			LoggerSysOut.print("OI Participant Data dir : " + dir);
			List<String> lst = FileUtils.listFilesForFolder(new File(dir));
			LoggerSysOut.print(lst.size());
			for (String string : lst) {

				OIUtils.collectAllDateForDay(dir + File.separator + string);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public static synchronized void readOptionData(String csvFile) { String
	 * line = ""; try (BufferedReader br = new BufferedReader(new
	 * FileReader(csvFile));) { int i = 0; while ((line = br.readLine()) !=
	 * null) { if (i != 0) { // use comma as separator if (!line.isEmpty()) {
	 * 
	 * //LoggerSysOut.print(line); // LoggerSysOut.print(getFutureData(line));
	 * ParticipantOIOptionModel participantOIOptionModel = getOptionData(line);
	 * Map<String, List<ParticipantOIOptionModel>> map =
	 * ParticapentOIData.optionData; if
	 * (!map.containsKey(participantOIOptionModel.getDate())) {
	 * List<ParticipantOIOptionModel> lst = new ArrayList<>(4);
	 * lst.add(participantOIOptionModel);
	 * map.put(participantOIOptionModel.getDate(), lst); } else {
	 * List<ParticipantOIOptionModel> lst =
	 * map.get(participantOIOptionModel.getDate());
	 * lst.add(participantOIOptionModel); } } } i++; }
	 * 
	 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch
	 * (IOException e) { e.printStackTrace(); }
	 * 
	 * //LoggerSysOut.print(ParticapentOIData.optionData); Set<String> key =
	 * ParticapentOIData.optionData.keySet(); for (String string : key) {
	 * List<ParticipantOIOptionModel> lst =
	 * ParticapentOIData.optionData.get(string); for (ParticipantOIOptionModel
	 * participantOIFutureModel : lst) {
	 * //LoggerSysOut.print(participantOIFutureModel); } } }
	 * 
	 * public static synchronized void readFutureData(String csvFile) { String
	 * line = ""; try (BufferedReader br = new BufferedReader(new
	 * FileReader(csvFile));) { int i = 0; while ((line = br.readLine()) !=
	 * null) { if (i != 0) { // use comma as separator if (!line.isEmpty()) {
	 * 
	 * //LoggerSysOut.print(line); ParticipantOIFutureModel
	 * participantOIFutureModel = getFutureData(line); Map<String,
	 * List<ParticipantOIFutureModel>> map = ParticapentOIData.futureData; if
	 * (!map.containsKey(participantOIFutureModel.getDate())) {
	 * List<ParticipantOIFutureModel> lst = new ArrayList<>(4);
	 * lst.add(participantOIFutureModel);
	 * map.put(participantOIFutureModel.getDate(), lst); } else {
	 * List<ParticipantOIFutureModel> lst =
	 * map.get(participantOIFutureModel.getDate());
	 * lst.add(participantOIFutureModel); } } } i++; }
	 * 
	 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch
	 * (IOException e) { e.printStackTrace(); }
	 * 
	 * Set<String> key = ParticapentOIData.futureData.keySet(); for (String
	 * string : key) { List<ParticipantOIFutureModel> lst =
	 * ParticapentOIData.futureData.get(string); for (ParticipantOIFutureModel
	 * participantOIFutureModel : lst) { //
	 * LoggerSysOut.print(participantOIFutureModel); } } }
	 * 
	 * private static ParticipantOIOptionModel getOptionData(String csvData) {
	 * String cvsSplitBy = ","; String[] data = csvData.split(cvsSplitBy);
	 * ParticipantOIOptionModel participantOIOptionModel = new
	 * ParticipantOIOptionModel(data[0], ParticipantType.valueOf(data[1]),
	 * DerivativeProducts.valueOf(data[2]), Integer.parseInt(data[3]),
	 * Integer.parseInt(data[4]), Integer.parseInt(data[5]),
	 * Integer.parseInt(data[6])); return participantOIOptionModel; }
	 * 
	 * private static ParticipantOIFutureModel getFutureData(String csvData) {
	 * 
	 * String cvsSplitBy = ","; String[] data = csvData.split(cvsSplitBy);
	 * ParticipantOIFutureModel participantOIFutureModel = new
	 * ParticipantOIFutureModel(data[0], ParticipantType.valueOf(data[1]),
	 * DerivativeProducts.valueOf(data[2]), Integer.parseInt(data[3]),
	 * Integer.parseInt(data[4])); return participantOIFutureModel; }
	 */
}

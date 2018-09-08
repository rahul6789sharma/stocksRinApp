package org.stocksrin.bhavcopy;

public class StocksrinOIModel {

	private String date;
	private long timeStamp;
	private String instrument;
	private String symbol;
	private double close;

	private String expiry_dt1;
	private int open_int1;
	private int volume1;

	private String expiry_dt2;
	private int open_int2;
	private int volume2;

	private String expiry_dt3;
	private int open_int3;
	private int volume3;

	private int totalOI;
	private int totalVol;

	public String toCSV() {
		return date + "," + timeStamp + "," + instrument + "," + symbol + "," + close + "," + expiry_dt1 + "," + open_int1 + "," + volume1 + "," + expiry_dt2 + "," + open_int2 + "," + volume2 + ","
				+ expiry_dt3 + "," + open_int2 + "," + volume3 + "," + totalOI + "," + totalVol;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public String getExpiry_dt1() {
		return expiry_dt1;
	}

	public void setExpiry_dt1(String expiry_dt1) {
		this.expiry_dt1 = expiry_dt1;
	}

	public int getOpen_int1() {
		return open_int1;
	}

	public void setOpen_int1(int open_int1) {
		this.open_int1 = open_int1;
	}

	public int getVolume1() {
		return volume1;
	}

	public void setVolume1(int volume1) {
		this.volume1 = volume1;
	}

	public String getExpiry_dt2() {
		return expiry_dt2;
	}

	public void setExpiry_dt2(String expiry_dt2) {
		this.expiry_dt2 = expiry_dt2;
	}

	public int getOpen_int2() {
		return open_int2;
	}

	public void setOpen_int2(int open_int2) {
		this.open_int2 = open_int2;
	}

	public int getVolume2() {
		return volume2;
	}

	public void setVolume2(int volume2) {
		this.volume2 = volume2;
	}

	public String getExpiry_dt3() {
		return expiry_dt3;
	}

	public void setExpiry_dt3(String expiry_dt3) {
		this.expiry_dt3 = expiry_dt3;
	}

	public int getOpen_int3() {
		return open_int3;
	}

	public void setOpen_int3(int open_int3) {
		this.open_int3 = open_int3;
	}

	public int getVolume3() {
		return volume3;
	}

	public void setVolume3(int volume3) {
		this.volume3 = volume3;
	}

	public int getTotalOI() {
		return totalOI;
	}

	public void setTotalOI(int totalOI) {
		this.totalOI = totalOI;
	}

	public int getTotalVol() {
		return totalVol;
	}

	public void setTotalVol(int totalVol) {
		this.totalVol = totalVol;
	}

}

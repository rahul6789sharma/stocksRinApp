package org.stocksrin.bhavcopy;

public class BhavForRestModle {

	private long timeStamp;
	private double close;
	private int totalOI;
	private int totalVol;

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
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

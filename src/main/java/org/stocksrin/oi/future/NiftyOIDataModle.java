package org.stocksrin.oi.future;

public class NiftyOIDataModle {

	private String date;
	private Integer vol1;
	private double vol1PercentageChange;
	private Integer oi1;
	private double oi1PercentageChange;

	private Integer vol2;
	private double vol2PercentageChange;
	private Integer oi2;
	private double oi2PercentageChange;

	private Integer volTotal;
	private double volTotalPercentageChange;
	private Integer oiTotal;
	private double oiTotalPercentageChange;

	private double nifty;
	private double niftyChange;

	public NiftyOIDataModle() {

	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getVol1() {
		return vol1;
	}

	public void setVol1(Integer vol1) {
		this.vol1 = vol1;
	}

	public Integer getOi1() {
		return oi1;
	}

	public void setOi1(Integer oi1) {
		this.oi1 = oi1;
	}

	public Integer getVol2() {
		return vol2;
	}

	public void setVol2(Integer vol2) {
		this.vol2 = vol2;
	}

	public Integer getOi2() {
		return oi2;
	}

	public void setOi2(Integer oi2) {
		this.oi2 = oi2;
	}

	public Integer getVolTotal() {
		return volTotal;
	}

	public void setVolTotal(Integer volTotal) {
		this.volTotal = volTotal;
	}

	public double getVolTotalPercentageChange() {
		return volTotalPercentageChange;
	}

	public void setVolTotalPercentageChange(double volTotalPercentageChange) {
		this.volTotalPercentageChange = volTotalPercentageChange;
	}

	public Integer getOiTotal() {
		return oiTotal;
	}

	public void setOiTotal(Integer oiTotal) {
		this.oiTotal = oiTotal;
	}

	public double getOiTotalPercentageChange() {
		return oiTotalPercentageChange;
	}

	public void setOiTotalPercentageChange(double oiTotalPercentageChange) {
		this.oiTotalPercentageChange = oiTotalPercentageChange;
	}

	public double getNifty() {
		return nifty;
	}

	public void setNifty(double nifty) {
		this.nifty = nifty;
	}

	public double getNiftyChange() {
		return niftyChange;
	}

	public void setNiftyChange(double niftyChange) {
		this.niftyChange = niftyChange;
	}

	public double getVol1PercentageChange() {
		return vol1PercentageChange;
	}

	public void setVol1PercentageChange(double vol1PercentageChange) {
		this.vol1PercentageChange = vol1PercentageChange;
	}

	public double getOi1PercentageChange() {
		return oi1PercentageChange;
	}

	public void setOi1PercentageChange(double oi1PercentageChange) {
		this.oi1PercentageChange = oi1PercentageChange;
	}

	public double getVol2PercentageChange() {
		return vol2PercentageChange;
	}

	public void setVol2PercentageChange(double vol2PercentageChange) {
		this.vol2PercentageChange = vol2PercentageChange;
	}

	public double getOi2PercentageChange() {
		return oi2PercentageChange;
	}

	public void setOi2PercentageChange(double oi2PercentageChange) {
		this.oi2PercentageChange = oi2PercentageChange;
	}

	@Override
	public String toString() {
		return "NiftyOIDataModle [date=" + date + ", vol1=" + vol1 + ", vol1PercentageChange=" + vol1PercentageChange + ", oi1=" + oi1 + ", oi1PercentageChange=" + oi1PercentageChange + ", vol2="
				+ vol2 + ", vol2PercentageChange=" + vol2PercentageChange + ", oi2=" + oi2 + ", oi2PercentageChange=" + oi2PercentageChange + ", volTotal=" + volTotal + ", volTotalPercentageChange="
				+ volTotalPercentageChange + ", oiTotal=" + oiTotal + ", oiTotalPercentageChange=" + oiTotalPercentageChange + ", nifty=" + nifty + ", niftyChange=" + niftyChange + "]";
	}

	public String toCsv() {
		return date + "," + vol1 + "," + vol1PercentageChange + "," + oi1 + "," + oi1PercentageChange + "," + vol2 + "," + vol2PercentageChange + "," + oi2 + "," + oi2PercentageChange + "," + volTotal
				+ "," + volTotalPercentageChange + "," + oiTotal + "," + oiTotalPercentageChange + "," + nifty + "," + niftyChange;
	}

	public String toMail() {
		return "[oi1: " + oi1 + ", oi1Change: " + oi1PercentageChange + ", oi2: " + oi2 + ", oi2Change: " + oi2PercentageChange + "]";
	}
}

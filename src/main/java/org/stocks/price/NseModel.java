package org.stocks.price;

public class NseModel {
	
	private String nifty_nseOpen;
	private String nifty_nseHigh;
	private String nifty_nseLow;
	private String nifty_nseChange;
	private String nifty_nseClose;
	private String nifty_percantageChange;
	private String nifty_vol;
	
	private String bNifty_nseOpen;
	private String bNifty_nseHigh;
	private String bNifty_nseLow;
	private String bNifty_nseChange;
	private String bNifty_nseClose;
	private String bNifty_percantageChange;
	private String bNifty_vol;
	
	
	private String comments;
	private String date;
	
	public String getNifty_nseOpen() {
		return nifty_nseOpen;
	}
	public void setNifty_nseOpen(String nifty_nseOpen) {
		this.nifty_nseOpen = nifty_nseOpen;
	}
	public String getNifty_nseHigh() {
		return nifty_nseHigh;
	}
	public void setNifty_nseHigh(String nifty_nseHigh) {
		this.nifty_nseHigh = nifty_nseHigh;
	}
	public String getNifty_nseLow() {
		return nifty_nseLow;
	}
	public void setNifty_nseLow(String nifty_nseLow) {
		this.nifty_nseLow = nifty_nseLow;
	}
	public String getNifty_nseChange() {
		return nifty_nseChange;
	}
	public void setNifty_nseChange(String nifty_nseChange) {
		this.nifty_nseChange = nifty_nseChange;
	}
	public String getNifty_nseClose() {
		return nifty_nseClose;
	}
	public void setNifty_nseClose(String nifty_nseClose) {
		this.nifty_nseClose = nifty_nseClose;
	}
	public String getNifty_percantageChange() {
		return nifty_percantageChange;
	}
	public void setNifty_percantageChange(String nifty_percantageChange) {
		this.nifty_percantageChange = nifty_percantageChange;
	}
	public String getNifty_vol() {
		return nifty_vol;
	}
	public void setNifty_vol(String nifty_vol) {
		this.nifty_vol = nifty_vol;
	}
	public String getbNifty_nseOpen() {
		return bNifty_nseOpen;
	}
	public void setbNifty_nseOpen(String bNifty_nseOpen) {
		this.bNifty_nseOpen = bNifty_nseOpen;
	}
	public String getbNifty_nseHigh() {
		return bNifty_nseHigh;
	}
	public void setbNifty_nseHigh(String bNifty_nseHigh) {
		this.bNifty_nseHigh = bNifty_nseHigh;
	}
	public String getbNifty_nseLow() {
		return bNifty_nseLow;
	}
	public void setbNifty_nseLow(String bNifty_nseLow) {
		this.bNifty_nseLow = bNifty_nseLow;
	}
	public String getbNifty_nseChange() {
		return bNifty_nseChange;
	}
	public void setbNifty_nseChange(String bNifty_nseChange) {
		this.bNifty_nseChange = bNifty_nseChange;
	}
	public String getbNifty_nseClose() {
		return bNifty_nseClose;
	}
	public void setbNifty_nseClose(String bNifty_nseClose) {
		this.bNifty_nseClose = bNifty_nseClose;
	}
	public String getbNifty_percantageChange() {
		return bNifty_percantageChange;
	}
	public void setbNifty_percantageChange(String bNifty_percantageChange) {
		this.bNifty_percantageChange = bNifty_percantageChange;
	}
	public String getbNifty_vol() {
		return bNifty_vol;
	}
	public void setbNifty_vol(String bNifty_vol) {
		this.bNifty_vol = bNifty_vol;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "NseModel [nifty_nseOpen=" + nifty_nseOpen + " nifty_nseHigh=" + nifty_nseHigh + " nifty_nseLow=" + nifty_nseLow
				+ " nifty_nseChange=" + nifty_nseChange + " nifty_nseClose=" + nifty_nseClose + " nifty_percantageChange="
				+ nifty_percantageChange + " nifty_vol=" + nifty_vol + " bNifty_nseOpen=" + bNifty_nseOpen + " bNifty_nseHigh=" + bNifty_nseHigh
				+ " bNifty_nseLow=" + bNifty_nseLow + " bNifty_nseChange=" + bNifty_nseChange + " bNifty_nseClose=" + bNifty_nseClose
				+ " bNifty_percantageChange=" + bNifty_percantageChange + " bNifty_vol=" + bNifty_vol + " comments=" + comments + " date=" + date
				+ "]";
	}
	
}
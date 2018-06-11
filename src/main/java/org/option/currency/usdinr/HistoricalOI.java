package org.option.currency.usdinr;

import java.util.ArrayList;
import java.util.List;

public class HistoricalOI {

	private String expiry;
	private Float strick;
	private List<String> dates = new ArrayList<String>();
	private List<Integer> ce_oi = new ArrayList<Integer>();
	private List<Integer> pe_oi = new ArrayList<Integer>();
	//private List<Float> stricks= new ArrayList<Float>();

	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	public Float getStrick() {
		return strick;
	}
	public void setStrick(Float strick) {
		this.strick = strick;
	}
	public List<String> getDates() {
		return dates;
	}
	public void setDates(List<String> dates) {
		this.dates = dates;
	}
	public List<Integer> getCe_oi() {
		return ce_oi;
	}
	public void setCe_oi(List<Integer> ce_oi) {
		this.ce_oi = ce_oi;
	}
	public List<Integer> getPe_oi() {
		return pe_oi;
	}
	public void setPe_oi(List<Integer> pe_oi) {
		this.pe_oi = pe_oi;
	}
	@Override
	public String toString() {
		return "HistoricalOI [expiry=" + expiry + " strick=" + strick + " dates=" + dates + " ce_oi=" + ce_oi + " pe_oi=" + pe_oi + "]";
	}
	
}
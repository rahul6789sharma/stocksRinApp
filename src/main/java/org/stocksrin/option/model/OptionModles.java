package org.stocksrin.option.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OptionModles {

	private int total_ce_oi;
	private int total_pe_oi;
	private Date date;
	private String expiry;
	private List<String> expiryList = new ArrayList<>();
	private String lastDataUpdated;

	private String underlyingSpotPrice;
	private String change;

	private List<OptionModle> optionModle = new ArrayList<>();

	private String spot;
	
	public int getTotal_ce_oi() {
		return total_ce_oi;
	}

	public void setTotal_ce_oi(int total_ce_oi) {
		this.total_ce_oi = total_ce_oi;
	}

	public int getTotal_pe_oi() {
		return total_pe_oi;
	}

	public void setTotal_pe_oi(int total_pe_oi) {
		this.total_pe_oi = total_pe_oi;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public List<String> getExpiryList() {
		return expiryList;
	}

	public void setExpiryList(List<String> expiryList) {
		this.expiryList = expiryList;
	}

	public String getLastDataUpdated() {
		return lastDataUpdated;
	}

	public void setLastDataUpdated(String lastDataUpdated) {
		this.lastDataUpdated = lastDataUpdated;
	}

	public String getUnderlyingSpotPrice() {
		return underlyingSpotPrice;
	}

	public void setUnderlyingSpotPrice(String underlyingSpotPrice) {
		this.underlyingSpotPrice = underlyingSpotPrice;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public List<OptionModle> getOptionModle() {
		return optionModle;
	}

	public void setOptionModle(List<OptionModle> optionModle) {
		this.optionModle = optionModle;
	}

	
	public String getSpot() {
		return spot;
	}

	public void setSpot(String spot) {
		this.spot = spot;
	}

	@Override
	public String toString() {
		return "OptionModles [total_ce_oi=" + total_ce_oi + ", total_pe_oi=" + total_pe_oi + ", date=" + date
				+ ", expiry=" + expiry + ", expiryList=" + expiryList + ", lastDataUpdated=" + lastDataUpdated
				+ ", underlyingSpotPrice=" + underlyingSpotPrice + ", change=" + change + ", optionModle=" + optionModle
				+ "]";
	}

}

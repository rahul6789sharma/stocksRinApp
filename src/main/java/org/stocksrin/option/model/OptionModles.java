package org.stocksrin.option.model;

import java.util.ArrayList;
import java.util.List;

public class OptionModles {

	private String date;
	private String expiry;
	private List<String> expiryList = new ArrayList<>();
	private String lastDataUpdated;

	private String underlyingSpotPrice;
	private Double spot;

	private String change;
	private String type; // monthly or Weekly

	private int total_ce_oi;
	private int total_pe_oi;
	private Double maxPainStrick;
	private List<OptionModle> optionModle = new ArrayList<>();

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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

	public Double getSpot() {
		return spot;
	}

	public void setSpot(Double spot) {
		this.spot = spot;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getMaxPainStrick() {
		return maxPainStrick;
	}

	public void setMaxPainStrick(Double maxPainStrick) {
		this.maxPainStrick = maxPainStrick;
	}

	@Override
	public String toString() {
		return "OptionModles [date=" + date + ", expiry=" + expiry + ", expiryList=" + expiryList + ", lastDataUpdated=" + lastDataUpdated + ", underlyingSpotPrice=" + underlyingSpotPrice + ", spot="
				+ spot + ", change=" + change + ", type=" + type + ", total_ce_oi=" + total_ce_oi + ", total_pe_oi=" + total_pe_oi + ", maxPainStrick=" + maxPainStrick + ", optionModle=" + optionModle
				+ "]";
	}

}

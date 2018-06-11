package org.option.currency.models;

import java.util.ArrayList;
import java.util.List;

public class Columns {

	private int total_ce_oi;
	private int total_pe_oi;
	private String expiry;
	private String interestRate;
	private USDINRFuture uSDINRFuture;
	private String lastDataUpdated;
	private String underlyingSpotPrice;
	private String spotPrice;
	private List<String> expiryList = new ArrayList<>();
	private List<Column> dataset = new ArrayList<>();

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

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public USDINRFuture getuSDINRFuture() {
		return uSDINRFuture;
	}

	public void setuSDINRFuture(USDINRFuture uSDINRFuture) {
		this.uSDINRFuture = uSDINRFuture;
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

	public List<String> getExpiryList() {
		return expiryList;
	}

	public void setExpiryList(List<String> expiryList) {
		this.expiryList = expiryList;
	}

	public List<Column> getDataset() {
		return dataset;
	}

	public void setDataset(List<Column> dataset) {
		this.dataset = dataset;
	}

	public String getSpotPrice() {
		return spotPrice;
	}

	public void setSpotPrice(String spotPrice) {
		this.spotPrice = spotPrice;
	}

	@Override
	public String toString() {
		return "Columns [total_ce_oi=" + total_ce_oi + " total_pe_oi=" + total_pe_oi + " expiry=" + expiry
				+ " interestRate=" + interestRate + " uSDINRFuture=" + uSDINRFuture + " lastDataUpdated="
				+ lastDataUpdated + " underlyingSpotPrice=" + underlyingSpotPrice + " spotPrice=" + spotPrice
				+ " expiryList=" + expiryList + "]";
	}

}
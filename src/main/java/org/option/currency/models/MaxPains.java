package org.option.currency.models;

import java.util.ArrayList;
import java.util.List;

public class MaxPains {

	private String expiry;
	private String symbol;
	private Double maxPainStrick;
	private List<MaxPain> dataSet = new ArrayList<>();

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public List<MaxPain> getDataSet() {
		return dataSet;
	}

	public void setDataSet(List<MaxPain> dataSet) {
		this.dataSet = dataSet;
	}

	public Double getMaxPainStrick() {
		return maxPainStrick;
	}

	public void setMaxPainStrick(Double maxPainStrick) {
		this.maxPainStrick = maxPainStrick;
	}

	@Override
	public String toString() {
		return "MaxPains [expiry=" + expiry + ", symbol=" + symbol + ", maxPainStrick=" + maxPainStrick + ", dataSet=" + dataSet + "]";
	}

}

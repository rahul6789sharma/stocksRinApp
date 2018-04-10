package org.stocksrin.banknifty;

public class TickData {

	private Type type;
	private double strike;
	private double ltp;
	private double change;
	private double quantity;
	private double previousDayClosePrice; // previous day traded price

	private double underlyingIndexSpotPrice;
	private double underlyingIndexPreviousDayClosePrice; // bank niftypreviosu
															// day close price

	private String lastUpdateTime;
	public TickData(Type type, double strike, double ltp, double change, double underlyingIndexSpotPrice) {
		super();
		this.type = type;
		this.strike = strike;
		this.ltp = ltp;
		this.change = change;
		this.underlyingIndexSpotPrice = underlyingIndexSpotPrice;
	}

	public TickData(Type type, double strike, double ltp, double change, double previousDayClosePrice, double underlyingIndexSpotPrice, double underlyingIndexPreviousDayClosePrice) {
		super();
		this.type = type;
		this.strike = strike;
		this.ltp = ltp;
		this.change = change;
		this.previousDayClosePrice = previousDayClosePrice;
		this.underlyingIndexSpotPrice = underlyingIndexSpotPrice;
		this.underlyingIndexPreviousDayClosePrice = underlyingIndexPreviousDayClosePrice;
	}

	public enum Type {
		PUT, CALL
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public double getStrike() {
		return strike;
	}

	public void setStrike(double strike) {
		this.strike = strike;
	}

	public double getLtp() {
		return ltp;
	}

	public void setLtp(double ltp) {
		this.ltp = ltp;
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public double getUnderlyingIndexSpotPrice() {
		return underlyingIndexSpotPrice;
	}

	public void setUnderlyingIndexSpotPrice(double underlyingIndexSpotPrice) {
		this.underlyingIndexSpotPrice = underlyingIndexSpotPrice;
	}

	public double getPreviousDayClosePrice() {
		return previousDayClosePrice;
	}

	public void setPreviousDayClosePrice(double previousDayClosePrice) {
		this.previousDayClosePrice = previousDayClosePrice;
	}

	public double getUnderlyingIndexPreviousDayClosePrice() {
		return underlyingIndexPreviousDayClosePrice;
	}

	public void setUnderlyingIndexPreviousDayClosePrice(double underlyingIndexPreviousDayClosePrice) {
		this.underlyingIndexPreviousDayClosePrice = underlyingIndexPreviousDayClosePrice;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "TickData [type=" + type + ", strike=" + strike + ", ltp=" + ltp + ", change=" + change + ", quantity=" + quantity + ", previousDayClosePrice=" + previousDayClosePrice
				+ ", underlyingIndexSpotPrice=" + underlyingIndexSpotPrice + ", underlyingIndexPreviousDayClosePrice=" + underlyingIndexPreviousDayClosePrice + "]";
	}

}

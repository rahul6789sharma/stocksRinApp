package org.option.currency.models;

public class USDINRFuture {

	private String change;
	private String prevClose;
	private String percentageChange;
	private String lastPrice;
	private String lowPrice;
	private String highPrice;
	private String expiryDate;
	private String RBIrr;
	private String RBIrr_last_updated;

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getPrevClose() {
		return prevClose;
	}

	public void setPrevClose(String prevClose) {
		this.prevClose = prevClose;
	}

	public String getPercentageChange() {
		return percentageChange;
	}

	public void setPercentageChange(String percentageChange) {
		this.percentageChange = percentageChange;
	}

	public String getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(String lastPrice) {
		this.lastPrice = lastPrice;
	}

	public String getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(String lowPrice) {
		this.lowPrice = lowPrice;
	}

	public String getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(String highPrice) {
		this.highPrice = highPrice;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getRBIrr() {
		return RBIrr;
	}

	public void setRBIrr(String rBIrr) {
		RBIrr = rBIrr;
	}

	public String getRBIrr_last_updated() {
		return RBIrr_last_updated;
	}

	public void setRBIrr_last_updated(String rBIrr_last_updated) {
		RBIrr_last_updated = rBIrr_last_updated;
	}

	@Override
	public String toString() {
		return "USDINRFuture [change=" + change + ", prevClose=" + prevClose + ", percentageChange=" + percentageChange + ", lastPrice=" + lastPrice
				+ ", lowPrice=" + lowPrice + ", highPrice=" + highPrice + ", expiryDate=" + expiryDate + ", RBIrr=" + RBIrr + ", RBIrr_last_updated="
				+ RBIrr_last_updated + "]";
	}

}

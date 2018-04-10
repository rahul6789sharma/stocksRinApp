package org.stocksrin.option.model;

public class BankNiftyDailyMaxPain {

	private String date;
	private String expiry;
	private String expiryType;
	private Double maxPain;
	private String totalCEOI;
	private String totalPEOI;
	private String pcr;
	private String oi;
	private String changeInOI;
	private String perChangeInOI;
	private String spot;
	private String change;

	public BankNiftyDailyMaxPain() {
		super();
	}

	public BankNiftyDailyMaxPain(String date, String expiry, String expiryType, Double maxPain, String totalCEOI, String totalPEOI) {
		super();
		this.date = date;
		this.expiry = expiry;
		this.expiryType = expiryType;
		this.maxPain = maxPain;
		this.totalCEOI = totalCEOI;
		this.totalPEOI = totalPEOI;
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

	public String getExpiryType() {
		return expiryType;
	}

	public void setExpiryType(String expiryType) {
		this.expiryType = expiryType;
	}

	public Double getMaxPain() {
		return maxPain;
	}

	public void setMaxPain(Double maxPain) {
		this.maxPain = maxPain;
	}

	public String getTotalCEOI() {
		return totalCEOI;
	}

	public void setTotalCEOI(String totalCEOI) {
		this.totalCEOI = totalCEOI;
	}

	public String getTotalPEOI() {
		return totalPEOI;
	}

	public void setTotalPEOI(String totalPEOI) {
		this.totalPEOI = totalPEOI;
	}

	public String getPcr() {
		return pcr;
	}

	public void setPcr(String pcr) {
		this.pcr = pcr;
	}

	public String getSpot() {
		return spot;
	}

	public void setSpot(String spot) {
		this.spot = spot;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getOi() {
		return oi;
	}

	public void setOi(String oi) {
		this.oi = oi;
	}

	public String getChangeInOI() {
		return changeInOI;
	}

	public void setChangeInOI(String changeInOI) {
		this.changeInOI = changeInOI;
	}

	public String getPerChangeInOI() {
		return perChangeInOI;
	}

	public void setPerChangeInOI(String perChangeInOI) {
		this.perChangeInOI = perChangeInOI;
	}

	public String toCSV() {
		return date + "," + expiry + "," + expiryType + "," + maxPain + "," + totalCEOI + "," + totalPEOI + "," + pcr + "," + oi + "," + changeInOI + "," + perChangeInOI + "," + spot + "," + change;
	}

	@Override
	public String toString() {
		return "BankNiftyDailyMaxPain [date=" + date + ", expiry=" + expiry + ", expiryType=" + expiryType + ", maxPain=" + maxPain + ", totalCEOI=" + totalCEOI + ", totalPEOI=" + totalPEOI + ", pcr="
				+ pcr + ", oi=" + oi + ", changeInOI=" + changeInOI + ", perChangeInOI=" + perChangeInOI + ", spot=" + spot + ", change=" + change + "]";
	}

}

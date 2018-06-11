package org.stocksrin.option.model;

public class BankNiftyDailyMaxPain {

	private String date;
	private String expiry;
	private String expiryType;
	private Double maxPain;
	private Integer totalCEOI;
	private Integer totalPEOI;
	private Double pcr;
	private Double spot;
	private String change;
	private String day;
	private String isExpiryDay;

	public BankNiftyDailyMaxPain() {
		super();
	}

	public BankNiftyDailyMaxPain(String date, String expiry, String expiryType, Double maxPain, Integer totalCEOI, Integer totalPEOI) {
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

	public Integer getTotalCEOI() {
		return totalCEOI;
	}

	public void setTotalCEOI(Integer totalCEOI) {
		this.totalCEOI = totalCEOI;
	}

	public Integer getTotalPEOI() {
		return totalPEOI;
	}

	public void setTotalPEOI(Integer totalPEOI) {
		this.totalPEOI = totalPEOI;
	}

	public Double getPcr() {
		return pcr;
	}

	public void setPcr(Double pcr) {
		this.pcr = pcr;
	}

	public Double getSpot() {
		return spot;
	}

	public void setSpot(Double spot) {
		this.spot = spot;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getIsExpiryDay() {
		return isExpiryDay;
	}

	public void setIsExpiryDay(String isExpiryDay) {
		this.isExpiryDay = isExpiryDay;
	}

	public String toCSVForWeeklyEOD() {
		return date + "," + expiry + "," + expiryType + "," + maxPain + "," + totalCEOI + "," + totalPEOI + "," + pcr + "," + spot + "," + change + "," + day + "," + isExpiryDay;
	}

	public String toCSV() {
		return date + "," + expiry + "," + expiryType + "," + maxPain + "," + totalCEOI + "," + totalPEOI + "," + pcr + "," + spot + "," + change;
	}

	@Override
	public String toString() {
		return "BankNiftyDailyMaxPain [date=" + date + ", expiry=" + expiry + ", expiryType=" + expiryType + ", maxPain=" + maxPain + ", totalCEOI=" + totalCEOI + ", totalPEOI=" + totalPEOI + ", pcr="
				+ pcr + ", spot=" + spot + ", change=" + change + ", day=" + day + ", isExpiryDay=" + isExpiryDay + "]";
	}

}

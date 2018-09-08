package org.stocksrin.option.common.model;

public class DailyMaxPain {

	private String date;
	private String expiry;
	private Double maxPain;
	private Integer totalCEOI;
	private Integer totalPEOI;
	private Double spot;
	private String change;

	private Double highest_oi_ce;
	private Integer highest_oi_ce_value;

	private Double highest_oi_pe;
	private Integer highest_oi_pe_value;

	private Double highest_change_oi_ce;
	private Integer highest_change_oi_ce_value;

	private Double highest_change_oi_pe;
	private Integer highest_change_oi_pe_value;

	public DailyMaxPain() {
		super();
	}

	public DailyMaxPain(String date, String expiry, Double maxPain, Integer totalCEOI, Integer totalPEOI) {
		super();
		this.date = date;
		this.expiry = expiry;
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

	public Double getHighest_oi_ce() {
		return highest_oi_ce;
	}

	public void setHighest_oi_ce(Double highest_oi_ce) {
		this.highest_oi_ce = highest_oi_ce;
	}

	public Integer getHighest_oi_ce_value() {
		return highest_oi_ce_value;
	}

	public void setHighest_oi_ce_value(Integer highest_oi_ce_value) {
		this.highest_oi_ce_value = highest_oi_ce_value;
	}

	public Double getHighest_oi_pe() {
		return highest_oi_pe;
	}

	public void setHighest_oi_pe(Double highest_oi_pe) {
		this.highest_oi_pe = highest_oi_pe;
	}

	public Integer getHighest_oi_pe_value() {
		return highest_oi_pe_value;
	}

	public void setHighest_oi_pe_value(Integer highest_oi_pe_value) {
		this.highest_oi_pe_value = highest_oi_pe_value;
	}

	public Double getHighest_change_oi_ce() {
		return highest_change_oi_ce;
	}

	public void setHighest_change_oi_ce(Double highest_change_oi_ce) {
		this.highest_change_oi_ce = highest_change_oi_ce;
	}

	public Integer getHighest_change_oi_ce_value() {
		return highest_change_oi_ce_value;
	}

	public void setHighest_change_oi_ce_value(Integer highest_change_oi_ce_value) {
		this.highest_change_oi_ce_value = highest_change_oi_ce_value;
	}

	public Double getHighest_change_oi_pe() {
		return highest_change_oi_pe;
	}

	public void setHighest_change_oi_pe(Double highest_change_oi_pe) {
		this.highest_change_oi_pe = highest_change_oi_pe;
	}

	public Integer getHighest_change_oi_pe_value() {
		return highest_change_oi_pe_value;
	}

	public void setHighest_change_oi_pe_value(Integer highest_change_oi_pe_value) {
		this.highest_change_oi_pe_value = highest_change_oi_pe_value;
	}

	public String toCSVForWeeklyEOD() {
		return date + "," + expiry + "," + maxPain + "," + totalCEOI + "," + totalPEOI + "," + spot + "," + change + "," + highest_oi_ce + "," + highest_oi_ce_value + "," + highest_oi_pe + ","
				+ highest_oi_pe_value + "," + highest_change_oi_ce + "," + highest_change_oi_ce_value + "," + highest_change_oi_pe + "," + highest_change_oi_pe_value;
	}

	@Override
	public String toString() {
		return "DailyMaxPain [date=" + date + ", expiry=" + expiry + ", maxPain=" + maxPain + ", totalCEOI=" + totalCEOI + ", totalPEOI=" + totalPEOI + ", spot=" + spot + ", change=" + change
				+ ", highest_oi_ce=" + highest_oi_ce + ", highest_oi_ce_value=" + highest_oi_ce_value + ", highest_oi_pe=" + highest_oi_pe + ", highest_oi_pe_value=" + highest_oi_pe_value
				+ ", highest_change_oi_ce=" + highest_change_oi_ce + ", highest_change_oi_ce_value=" + highest_change_oi_ce_value + ", highest_change_oi_pe=" + highest_change_oi_pe
				+ ", highest_change_oi_pe_value=" + highest_change_oi_pe_value + "]";
	}

}

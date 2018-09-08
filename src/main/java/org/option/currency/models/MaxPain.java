package org.option.currency.models;

public class MaxPain {

	private Double strickPrice;
	private Integer ce_oi;
	private Integer pe_oi;
	private Double cumulativeCe;
	private Double cumulativePe;
	private Double total;

	public MaxPain() {

	}

	public MaxPain(Double strickPrice, Integer ce_oi, Integer pe_oi) {
		super();
		this.strickPrice = strickPrice;
		this.ce_oi = ce_oi;
		this.pe_oi = pe_oi;
	}

	public Double getStrickPrice() {
		return strickPrice;
	}

	public void setStrickPrice(Double strickPrice) {
		this.strickPrice = strickPrice;
	}

	public Integer getCe_oi() {
		return ce_oi;
	}

	public void setCe_oi(Integer ce_oi) {
		this.ce_oi = ce_oi;
	}

	public Integer getPe_oi() {
		return pe_oi;
	}

	public void setPe_oi(Integer pe_oi) {
		this.pe_oi = pe_oi;
	}

	public Double getCumulativeCe() {
		return cumulativeCe;
	}

	public void setCumulativeCe(Double cumulativeCe) {
		this.cumulativeCe = cumulativeCe;
	}

	public Double getCumulativePe() {
		return cumulativePe;
	}

	public void setCumulativePe(Double cumulativePe) {
		this.cumulativePe = cumulativePe;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "MaxPain [strickPrice=" + strickPrice + " ce_oi=" + ce_oi + " pe_oi=" + pe_oi + " cumulativeCe=" + cumulativeCe + " cumulativePe=" + cumulativePe + " total=" + total + "]";
	}

}

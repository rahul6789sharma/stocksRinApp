package org.stocksrin.option.model;

public class OptionModle {

	private Integer c_oi;
	private Integer c_change_oi;
	private Integer c_volume;
	private Double c_ltp;
	private Double c_net_change;
	private Double strike_price;
	private Double p_net_change;
	private Double p_ltp;
	private Integer p_volume;
	private Integer p_change_oi;
	private Integer p_oi;

	public Integer getC_oi() {
		return c_oi;
	}

	public void setC_oi(Integer c_oi) {
		this.c_oi = c_oi;
	}

	public Integer getC_change_oi() {
		return c_change_oi;
	}

	public void setC_change_oi(Integer c_change_oi) {
		this.c_change_oi = c_change_oi;
	}

	public Integer getC_volume() {
		return c_volume;
	}

	public void setC_volume(Integer c_volume) {
		this.c_volume = c_volume;
	}

	public Double getC_ltp() {
		return c_ltp;
	}

	public void setC_ltp(Double c_ltp) {
		this.c_ltp = c_ltp;
	}

	public Double getC_net_change() {
		return c_net_change;
	}

	public void setC_net_change(Double c_net_change) {
		this.c_net_change = c_net_change;
	}

	public Double getStrike_price() {
		return strike_price;
	}

	public void setStrike_price(Double strike_price) {
		this.strike_price = strike_price;
	}

	public Double getP_net_change() {
		return p_net_change;
	}

	public void setP_net_change(Double p_net_change) {
		this.p_net_change = p_net_change;
	}

	public Double getP_ltp() {
		return p_ltp;
	}

	public void setP_ltp(Double p_ltp) {
		this.p_ltp = p_ltp;
	}

	public Integer getP_volume() {
		return p_volume;
	}

	public void setP_volume(Integer p_volume) {
		this.p_volume = p_volume;
	}

	public Integer getP_change_oi() {
		return p_change_oi;
	}

	public void setP_change_oi(Integer p_change_oi) {
		this.p_change_oi = p_change_oi;
	}

	public Integer getP_oi() {
		return p_oi;
	}

	public void setP_oi(Integer p_oi) {
		this.p_oi = p_oi;
	}

	@Override
	public String toString() {
		return "OptionModle [c_oi=" + c_oi + ", c_change_oi=" + c_change_oi + ", c_volume=" + c_volume + ", c_ltp=" + c_ltp + ", c_net_change=" + c_net_change + ", strike_price=" + strike_price
				+ ", p_net_change=" + p_net_change + ", p_ltp=" + p_ltp + ", p_volume=" + p_volume + ", p_change_oi=" + p_change_oi + ", p_oi=" + p_oi + "]";
	}

}

package org.stocksrin.option.model;

public class OptionModle {

	private String c_oi;
	private String c_change_oi;
	private String c_volume;
	private String c_ltp;
	private String c_net_change;
	private String strike_price;
	private String p_net_change;
	private String p_ltp;
	private String p_volume;
	private String p_change_oi;
	private String p_oi;

	public String getC_oi() {
		return c_oi;
	}

	public void setC_oi(String c_oi) {
		this.c_oi = c_oi;
	}

	public String getC_change_oi() {
		return c_change_oi;
	}

	public void setC_change_oi(String c_change_oi) {
		this.c_change_oi = c_change_oi;
	}

	public String getC_volume() {
		return c_volume;
	}

	public void setC_volume(String c_volume) {
		this.c_volume = c_volume;
	}

	public String getC_ltp() {
		return c_ltp;
	}

	public void setC_ltp(String c_ltp) {
		this.c_ltp = c_ltp;
	}

	public String getC_net_change() {
		return c_net_change;
	}

	public void setC_net_change(String c_net_change) {
		this.c_net_change = c_net_change;
	}

	public String getStrike_price() {
		return strike_price;
	}

	public void setStrike_price(String strike_price) {
		this.strike_price = strike_price;
	}

	public String getP_net_change() {
		return p_net_change;
	}

	public void setP_net_change(String p_net_change) {
		this.p_net_change = p_net_change;
	}

	public String getP_ltp() {
		return p_ltp;
	}

	public void setP_ltp(String p_ltp) {
		this.p_ltp = p_ltp;
	}

	public String getP_volume() {
		return p_volume;
	}

	public void setP_volume(String p_volume) {
		this.p_volume = p_volume;
	}

	public String getP_change_oi() {
		return p_change_oi;
	}

	public void setP_change_oi(String p_change_oi) {
		this.p_change_oi = p_change_oi;
	}

	public String getP_oi() {
		return p_oi;
	}

	public void setP_oi(String p_oi) {
		this.p_oi = p_oi;
	}

	@Override
	public String toString() {
		return "OptionModle [c_oi=" + c_oi + ", c_change_oi=" + c_change_oi + ", c_volume=" + c_volume + ", c_ltp=" + c_ltp + ", c_net_change=" + c_net_change + ", strike_price=" + strike_price
				+ ", p_net_change=" + p_net_change + ", p_ltp=" + p_ltp + ", p_volume=" + p_volume + ", p_change_oi=" + p_change_oi + ", p_oi=" + p_oi + "]";
	}

}

package org.stocksrin.option.common.model;

public class OptionModle {

	private Integer c_oi;
	private Integer c_change_oi;
	private Integer c_volume;
	private Double c_iv;
	private Double c_ltp;
	private Double c_net_change;
	private Double strike_price;
	private Double p_net_change;
	private Double p_ltp;
	private Double p_iv;
	private Integer p_volume;
	private Integer p_change_oi;
	private Integer p_oi;

	public void setValue(int i, String value) {
		if (i == 0) {
			// CE_Chart = value;
		} else if (i == 1) {
			if (!value.equals("-")) {
				c_oi = Integer.parseInt(value.replace(",", ""));
			}

		} else if (i == 2) {
			if (!value.equals("-")) {
				c_change_oi = Integer.parseInt(value.replace(",", ""));
			}
		} else if (i == 3) {
			// CE_Volume = value;
		} else if (i == 4) {
			if (!value.equals("-")) {
				c_iv = Double.parseDouble(value);
			}
		} else if (i == 5) {
			if (!value.equals("-")) {
				c_ltp = Double.parseDouble(value.replace(",", ""));
			}
		} else if (i == 6) {
			// CE_Net_Change = value;
		} else if (i == 7) {
			// CE_Bid_Price = value;
		} else if (i == 8) {
			// CE_Ask_Price = value;
		} else if (i == 9) {
			// CE_Ask_Qty = value;
		} else if (i == 11) {
			strike_price = Double.parseDouble(value);
		} else if (i == 12) {
			// PE_Bid_Price = value;
		} else if (i == 13) {
			// PE_Ask_Price = value;
		} else if (i == 14) {
			// PE_Ask_Qty = value;
		} else if (i == 15) {
			// PE_Ask_Qty = value;
		} else if (i == 16) {
			if (!value.equals("-")) {
				p_net_change = Double.parseDouble(value.replace(",", ""));
			}
		} else if (i == 17) {
			if (!value.equals("-")) {
				p_ltp = Double.parseDouble(value.replace(",", ""));
			}
		} else if (i == 18) {
			if (!value.equals("-")) {
				p_iv = Double.parseDouble(value);
			}
		} else if (i == 19) {
			
		} else if (i == 20) {
			if (!value.equals("-")) {
				p_change_oi = Integer.parseInt(value.replace(",", ""));
			}
		} else if (i == 21) {
			if (!value.equals("-")) {
				p_oi = Integer.parseInt(value.replace(",", ""));
			}
		} else if (i == 22) {
			// PE_Chart = value;
		}
	}

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

	public Double getC_iv() {
		return c_iv;
	}

	public void setC_iv(Double c_iv) {
		this.c_iv = c_iv;
	}

	public Double getP_iv() {
		return p_iv;
	}

	public void setP_iv(Double p_iv) {
		this.p_iv = p_iv;
	}

	@Override
	public String toString() {
		return "OptionModle [c_oi=" + c_oi + ", c_change_oi=" + c_change_oi + ", c_volume=" + c_volume + ", c_iv=" + c_iv + ", c_ltp=" + c_ltp + ", c_net_change=" + c_net_change + ", strike_price="
				+ strike_price + ", p_net_change=" + p_net_change + ", p_ltp=" + p_ltp + ", p_iv=" + p_iv + ", p_volume=" + p_volume + ", p_change_oi=" + p_change_oi + ", p_oi=" + p_oi + "]";
	}

}

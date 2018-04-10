package org.stocksrin.banknifty.option.alog;

public class BNiftyOptionLastDayTrade {

	private String expiry;
	private double put_strike;
	private double put_close_price;
	private double call_strike;
	private double call_close_price;
	private double target;
	private double loss;
	private double spot_close;
	private String des;

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public double getPut_strike() {
		return put_strike;
	}

	public void setPut_strike(double put_strike) {
		this.put_strike = put_strike;
	}

	public double getPut_close_price() {
		return put_close_price;
	}

	public void setPut_close_price(double put_close_price) {
		this.put_close_price = put_close_price;
	}

	public double getCall_strike() {
		return call_strike;
	}

	public void setCall_strike(double call_strike) {
		this.call_strike = call_strike;
	}

	public double getCall_close_price() {
		return call_close_price;
	}

	public void setCall_close_price(double call_close_price) {
		this.call_close_price = call_close_price;
	}

	public double getTarget() {
		return target;
	}

	public void setTarget(double target) {
		this.target = target;
	}

	public double getLoss() {
		return loss;
	}

	public void setLoss(double loss) {
		this.loss = loss;
	}

	public double getSpot_close() {
		return spot_close;
	}

	public void setSpot_close(double spot_close) {
		this.spot_close = spot_close;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Override
	public String toString() {
		return "BNiftyOptionLastDayTrade [expiry=" + expiry + ", put_strike=" + put_strike + ", put_close_price=" + put_close_price + ", call_strike=" + call_strike + ", call_close_price="
				+ call_close_price + ", target=" + target + ", loss=" + loss + ", spot_close=" + spot_close + ", des=" + des + "]";
	}

}
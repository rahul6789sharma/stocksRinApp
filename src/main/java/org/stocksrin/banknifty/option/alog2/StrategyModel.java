package org.stocksrin.banknifty.option.alog2;

public class StrategyModel {


	private String strategySerial;
	private String expiry;

	private OptionType type;
	private double strike;
	private double close_price; // my last traded price
	private int quantity;

	private double target;
	private double stopLoss;
	private double spot_close;
	private String des;

	/*
	 * type of vertex in the graph.
	 */
	public enum OptionType {
		CALL, PUT
	}

	public String getStrategySerial() {
		return strategySerial;
	}

	public void setStrategySerial(String strategySerial) {
		this.strategySerial = strategySerial;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public OptionType getType() {
		return type;
	}

	public void setType(OptionType type) {
		this.type = type;
	}

	public double getStrike() {
		return strike;
	}

	public void setStrike(double strike) {
		this.strike = strike;
	}

	public double getClose_price() {
		return close_price;
	}

	public void setClose_price(double close_price) {
		this.close_price = close_price;
	}

	public double getTarget() {
		return target;
	}

	public void setTarget(double target) {
		this.target = target;
	}

	public double getStopLoss() {
		return stopLoss;
	}

	public void setStopLoss(double stopLoss) {
		this.stopLoss = stopLoss;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "StrategyModel [strategySerial=" + strategySerial + " expiry=" + expiry + " type=" + type + " strike=" + strike + " close_price=" + close_price + " quantity=" + quantity
				+ " target=" + target + " stopLoss=" + stopLoss + " spot_close=" + spot_close + " des=" + des + "]";
	}

}

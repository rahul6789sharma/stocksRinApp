package org.stocksrin.option.common.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class StrategyModel {

	private String strategySerial;
	private String expiry;

	private OptionType type;
	private double strike;
	private double avgPrice; // traded buy or sold price
	private int quantity;

	private double target;
	private double stopLoss;
	// spot at trade
	private double spot_close;
	private String des;
	private boolean status;
	private double traded_IV;
	private String tradeDate;

	private double current_IV;
	// trade result Data
	private String underlying;
	private double underlying_ltp;
	private double ltp;
	private double ltp_min;
	private double ltp_max;

	private Map<Double,Double> intenrsic= new LinkedHashMap<>();
	public String toCSV() {
		return strategySerial + "," + expiry + "," + type + "," + strike + "," + avgPrice + "," + quantity + "," + target + "," + stopLoss + "," + spot_close + "," + des + "," + status + ","
				+ traded_IV + "," + tradeDate;
	}

	public Map<Double, Double> getIntenrsic() {
		return intenrsic;
	}

	public void setIntenrsic(Map<Double, Double> intenrsic) {
		this.intenrsic = intenrsic;
	}

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public double getTraded_IV() {
		return traded_IV;
	}

	public void setTraded_IV(double traded_IV) {
		this.traded_IV = traded_IV;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String getUnderlying() {
		return underlying;
	}

	public void setUnderlying(String underlying) {
		this.underlying = underlying;
	}

	public double getUnderlying_ltp() {
		return underlying_ltp;
	}

	public void setUnderlying_ltp(double underlying_ltp) {
		this.underlying_ltp = underlying_ltp;
	}

	public double getLtp() {
		return ltp;
	}

	public void setLtp(double ltp) {
		this.ltp = ltp;
	}

	public double getCurrent_IV() {
		return current_IV;
	}

	public void setCurrent_IV(double current_IV) {
		this.current_IV = current_IV;
	}

	public double getLtp_min() {
		return ltp_min;
	}

	public void setLtp_min(double ltp_min) {
		this.ltp_min = ltp_min;
	}

	public double getLtp_max() {
		return ltp_max;
	}

	public void setLtp_max(double ltp_max) {
		this.ltp_max = ltp_max;
	}

	@Override
	public String toString() {
		return "StrategyModel [strategySerial=" + strategySerial + ", expiry=" + expiry + ", type=" + type + ", strike=" + strike + ", avgPrice=" + avgPrice + ", quantity=" + quantity + ", target="
				+ target + ", stopLoss=" + stopLoss + ", spot_close=" + spot_close + ", des=" + des + ", status=" + status + ", traded_IV=" + traded_IV + ", tradeDate=" + tradeDate + ", underlying="
				+ underlying + ", underlying_ltp=" + underlying_ltp + ", ltp=" + ltp + "]";
	}

}
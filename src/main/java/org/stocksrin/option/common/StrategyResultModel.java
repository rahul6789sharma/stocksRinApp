package org.stocksrin.option.common;

import org.stocksrin.option.common.model.StrategyModel.OptionType;

public class StrategyResultModel {

	private String strategySerial;
	private String expiry;

	private String underlying;
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

	// trade result Data
	private double ltp;

	// ****** Extra info ********
	private String algoName;
	private String lastDataUpdated;

	private String underlyingSpotPriceString;
	private String change;

	private String maxProfit;
	private String minProfit;

	private String underlyingdayHigh;
	private String underlyingdayLow;

	private String maxPLTime;
	private String maxPLSpot;

	private String minPLTime;
	private String minPLSpot;

	public StrategyResultModel(String strategySerial, String expiry, String underlying, OptionType type, double strike, double avgPrice, int quantity, double target, double stopLoss,
			double spot_close, String des, boolean status, double traded_IV, String tradeDate, String algoName) {
		super();
		this.strategySerial = strategySerial;
		this.expiry = expiry;
		this.underlying = underlying;
		this.type = type;
		this.strike = strike;
		this.avgPrice = avgPrice;
		this.quantity = quantity;
		this.target = target;
		this.stopLoss = stopLoss;
		this.spot_close = spot_close;
		this.des = des;
		this.status = status;
		this.traded_IV = traded_IV;
		this.tradeDate = tradeDate;
		this.algoName = algoName;
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

	public String getUnderlying() {
		return underlying;
	}

	public void setUnderlying(String underlying) {
		this.underlying = underlying;
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

	public double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public double getLtp() {
		return ltp;
	}

	public void setLtp(double ltp) {
		this.ltp = ltp;
	}

	public String getAlgoName() {
		return algoName;
	}

	public void setAlgoName(String algoName) {
		this.algoName = algoName;
	}

	public String getLastDataUpdated() {
		return lastDataUpdated;
	}

	public void setLastDataUpdated(String lastDataUpdated) {
		this.lastDataUpdated = lastDataUpdated;
	}

	public String getUnderlyingSpotPriceString() {
		return underlyingSpotPriceString;
	}

	public void setUnderlyingSpotPriceString(String underlyingSpotPriceString) {
		this.underlyingSpotPriceString = underlyingSpotPriceString;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getMaxProfit() {
		return maxProfit;
	}

	public void setMaxProfit(String maxProfit) {
		this.maxProfit = maxProfit;
	}

	public String getMinProfit() {
		return minProfit;
	}

	public void setMinProfit(String minProfit) {
		this.minProfit = minProfit;
	}

	public String getUnderlyingdayHigh() {
		return underlyingdayHigh;
	}

	public void setUnderlyingdayHigh(String underlyingdayHigh) {
		this.underlyingdayHigh = underlyingdayHigh;
	}

	public String getUnderlyingdayLow() {
		return underlyingdayLow;
	}

	public void setUnderlyingdayLow(String underlyingdayLow) {
		this.underlyingdayLow = underlyingdayLow;
	}

	public String getMaxPLTime() {
		return maxPLTime;
	}

	public void setMaxPLTime(String maxPLTime) {
		this.maxPLTime = maxPLTime;
	}

	public String getMaxPLSpot() {
		return maxPLSpot;
	}

	public void setMaxPLSpot(String maxPLSpot) {
		this.maxPLSpot = maxPLSpot;
	}

	public String getMinPLTime() {
		return minPLTime;
	}

	public void setMinPLTime(String minPLTime) {
		this.minPLTime = minPLTime;
	}

	public String getMinPLSpot() {
		return minPLSpot;
	}

	public void setMinPLSpot(String minPLSpot) {
		this.minPLSpot = minPLSpot;
	}

}

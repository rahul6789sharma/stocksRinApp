package org.stocksrin.option.common.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Strategy {

	public Strategy() {
		super();
	}

	public Strategy(UnderLying underlying) {
		this.underlying = underlying;
	}

	public enum UnderLying {
		BANKNIFTY, NIFTY, OTHERS
	}

	private UnderLying underlying;
	private double underlying_ltp;
	private double tradeSpotPrice;
	private String dataUpdatedAt;
	private String tradeDate;
	private String tradeDay;
	private double target;
	private double stopLoss;

	private String strategyName;
	private double totalPL;
	private double totalPLMin;
	private double totalPLMax;

	private String totalPLMinTime;
	private String totalPLMaxTime;

	private double totalPLMinSpot;
	private double totalPLMaxSpot;

	private String dir;
	private String fileName;

	private Map<Double, Double> allStrikePNL = new LinkedHashMap<>();

	private double maxLossLowerSide = Double.MIN_VALUE;
	private double maxLossUpperSide = Double.MIN_VALUE;

	private double lowerBreakEven;
	private double upperBreakEven;
	private double maxProfit = Double.MAX_VALUE;
	

	public Map<Double, Double> getAllStrikePNL() {
		return allStrikePNL;
	}

	public void setAllStrikePNL(Map<Double, Double> allStrikePNL) {
		this.allStrikePNL = allStrikePNL;
	}

	public Double getMaxLossLowerSide() {
		return maxLossLowerSide;
	}

	public void setMaxLossLowerSide(Double maxLossLowerSide) {
		this.maxLossLowerSide = maxLossLowerSide;
	}

	public Double getMaxLossUpperSide() {
		return maxLossUpperSide;
	}

	public void setMaxLossUpperSide(Double maxLossUpperSide) {
		this.maxLossUpperSide = maxLossUpperSide;
	}

	public Double getLowerBreakEven() {
		return lowerBreakEven;
	}

	public void setLowerBreakEven(Double lowerBreakEven) {
		this.lowerBreakEven = lowerBreakEven;
	}

	public Double getUpperBreakEven() {
		return upperBreakEven;
	}

	public void setUpperBreakEven(Double upperBreakEven) {
		this.upperBreakEven = upperBreakEven;
	}

	public Double getMaxProfit() {
		return maxProfit;
	}

	public void setMaxProfit(Double maxProfit) {
		this.maxProfit = maxProfit;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private List<StrategyModel> strategyModels = new ArrayList<>(4);

	public UnderLying getUnderlying() {
		return underlying;
	}

	public double getTradeSpotPrice() {
		return tradeSpotPrice;
	}

	public void setTradeSpotPrice(double tradeSpotPrice) {
		this.tradeSpotPrice = tradeSpotPrice;
	}

	public String getTradeDay() {
		return tradeDay;
	}

	public void setTradeDay(String tradeDay) {
		this.tradeDay = tradeDay;
	}

	public void setUnderlying(UnderLying underlying) {
		this.underlying = underlying;
	}

	public double getUnderlying_ltp() {
		return underlying_ltp;
	}

	public void setUnderlying_ltp(double underlying_ltp) {
		this.underlying_ltp = underlying_ltp;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
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

	public String getStrategyName() {
		return strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	public double getTotalPL() {
		return totalPL;
	}

	public void setTotalPL(double totalPL) {
		this.totalPL = totalPL;
	}

	public double getTotalPLMin() {
		return totalPLMin;
	}

	public void setTotalPLMin(double totalPLMin) {
		this.totalPLMin = totalPLMin;
	}

	public double getTotalPLMax() {
		return totalPLMax;
	}

	public void setTotalPLMax(double totalPLMax) {
		this.totalPLMax = totalPLMax;
	}

	public String getTotalPLMinTime() {
		return totalPLMinTime;
	}

	public void setTotalPLMinTime(String totalPLMinTime) {
		this.totalPLMinTime = totalPLMinTime;
	}

	public String getTotalPLMaxTime() {
		return totalPLMaxTime;
	}

	public void setTotalPLMaxTime(String totalPLMaxTime) {
		this.totalPLMaxTime = totalPLMaxTime;
	}

	public double getTotalPLMinSpot() {
		return totalPLMinSpot;
	}

	public void setTotalPLMinSpot(double totalPLMinSpot) {
		this.totalPLMinSpot = totalPLMinSpot;
	}

	public double getTotalPLMaxSpot() {
		return totalPLMaxSpot;
	}

	public void setTotalPLMaxSpot(double totalPLMaxSpot) {
		this.totalPLMaxSpot = totalPLMaxSpot;
	}

	public List<StrategyModel> getStrategyModels() {
		return strategyModels;
	}

	public void setStrategyModels(List<StrategyModel> strategyModels) {
		this.strategyModels = strategyModels;
	}

	public String getDataUpdatedAt() {
		return dataUpdatedAt;
	}

	public void setDataUpdatedAt(String dataUpdatedAt) {
		this.dataUpdatedAt = dataUpdatedAt;
	}

	@Override
	public String toString() {
		return "Strategy [underlying=" + underlying + ", underlying_ltp=" + underlying_ltp + ", tradeDate=" + tradeDate + ", target=" + target + ", stopLoss=" + stopLoss + ", Strategyname="
				+ strategyName + ", totalPL=" + totalPL + ", totalPLMin=" + totalPLMin + ", totalPLMax=" + totalPLMax + ", totalPLMinTime=" + totalPLMinTime + ", totalPLMaxTime=" + totalPLMaxTime
				+ ", totalPLMinSpot=" + totalPLMinSpot + ", totalPLMaxSpot=" + totalPLMaxSpot + ", strategyModels=" + strategyModels + "]";
	}

}

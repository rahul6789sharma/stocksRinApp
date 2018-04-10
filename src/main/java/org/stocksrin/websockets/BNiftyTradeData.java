package org.stocksrin.websockets;

import java.util.LinkedHashMap;
import java.util.Map;

public class BNiftyTradeData {

	private static Map<String, BNiftyTradeData> data = new LinkedHashMap<>();

	private String time;

	private String expiry;

	// put data
	private double putStrike;
	private double putClosePrice; // previous my traded value
	private double putLtp;
	private double putChange;

	// call data
	private double callStrike;
	private double callClosePrice;
	private double callLtp;
	private double callChange;

	private double underlyingIndexSpotPrice; // current bnifty

	private double target;
	private double loss;

	private double spotClose; // last day bnifty close
	private String des;

	public BNiftyTradeData getSampleData(Integer i) {

		time = "90:23:" + i;
		expiry = "15MAR2018";

		putStrike = 23700.00;
		putClosePrice = 45.34;
		putLtp = 23.5 + i;
		putChange = 23.33 - i;

		callStrike = 24500.00;

		callClosePrice = 45.34;
		callLtp = 23.5 + i;
		callChange = 23.33 - i;

		underlyingIndexSpotPrice = 24000 + i;
		spotClose = 23900;

		return this;
	}

	public static Map<String, BNiftyTradeData> getData() {
		return data;
	}

	public static void setData(Map<String, BNiftyTradeData> data) {
		BNiftyTradeData.data = data;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public double getPutStrike() {
		return putStrike;
	}

	public void setPutStrike(double putStrike) {
		this.putStrike = putStrike;
	}

	public double getPutClosePrice() {
		return putClosePrice;
	}

	public void setPutClosePrice(double putClosePrice) {
		this.putClosePrice = putClosePrice;
	}

	public double getPutLtp() {
		return putLtp;
	}

	public void setPutLtp(double putLtp) {
		this.putLtp = putLtp;
	}

	public double getPutChange() {
		return putChange;
	}

	public void setPutChange(double putChange) {
		this.putChange = putChange;
	}

	public double getCallStrike() {
		return callStrike;
	}

	public void setCallStrike(double callStrike) {
		this.callStrike = callStrike;
	}

	public double getCallClosePrice() {
		return callClosePrice;
	}

	public void setCallClosePrice(double callClosePrice) {
		this.callClosePrice = callClosePrice;
	}

	public double getCallLtp() {
		return callLtp;
	}

	public void setCallLtp(double callLtp) {
		this.callLtp = callLtp;
	}

	public double getCallChange() {
		return callChange;
	}

	public void setCallChange(double callChange) {
		this.callChange = callChange;
	}

	public double getUnderlyingIndexSpotPrice() {
		return underlyingIndexSpotPrice;
	}

	public void setUnderlyingIndexSpotPrice(double underlyingIndexSpotPrice) {
		this.underlyingIndexSpotPrice = underlyingIndexSpotPrice;
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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public double getSpotClose() {
		return spotClose;
	}

	public void setSpotClose(double spotClose) {
		this.spotClose = spotClose;
	}

	@Override
	public String toString() {
		return "BNiftyTradeData [time=" + time + ", expiry=" + expiry + ", putStrike=" + putStrike + ", putClosePrice=" + putClosePrice + ", putLtp=" + putLtp + ", putChange=" + putChange
				+ ", callStrike=" + callStrike + ", callClosePrice=" + callClosePrice + ", callLtp=" + callLtp + ", callChange=" + callChange + ", underlyingIndexSpotPrice=" + underlyingIndexSpotPrice
				+ ", target=" + target + ", loss=" + loss + ", spotClose=" + spotClose + ", des=" + des + "]";
	}

}

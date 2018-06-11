package org.stocksrin.banknifty.dataStore;

public class BNFChangeINOI {

	private Double strike;
	private int changeINOI;
	private int OI;
	private int nextTarget;
	private int nextNegetiveTarget;

	public BNFChangeINOI(Double strike, Integer changeINOI, Integer oI) {
		super();
		this.strike = strike;
		this.changeINOI = changeINOI;
		OI = oI;
	}

	public Double getStrike() {
		return strike;
	}

	public void setStrike(Double strike) {
		this.strike = strike;
	}

	public int getChangeINOI() {
		return changeINOI;
	}

	public void setChangeINOI(int changeINOI) {
		this.changeINOI = changeINOI;
	}

	public int getOI() {
		return OI;
	}

	public void setOI(int oI) {
		OI = oI;
	}

	public int getNextTarget() {
		return nextTarget;
	}

	public void setNextTarget(int nextTarget) {
		this.nextTarget = nextTarget;
	}

	public int getNextNegetiveTarget() {
		return nextNegetiveTarget;
	}

	public void setNextNegetiveTarget(int nextNegetiveTarget) {
		this.nextNegetiveTarget = nextNegetiveTarget;
	}

	@Override
	public String toString() {
		return "BNFChangeINOI [strike=" + strike + ", changeINOI=" + changeINOI + ", OI=" + OI + ", nextTarget=" + nextTarget + ", nextNegetiveTarget=" + nextNegetiveTarget + "]";
	}

}

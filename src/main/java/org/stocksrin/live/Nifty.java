package org.stocksrin.live;

public class Nifty {

	private String change;
	private String lastTradedPrice;

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getLastTradedPrice() {
		return lastTradedPrice;
	}

	public void setLastTradedPrice(String lastTradedPrice) {
		this.lastTradedPrice = lastTradedPrice;
	}

	@Override
	public String toString() {
		return "Nifty [change=" + change + ", lastTradedPrice=" + lastTradedPrice + "]";
	}

}

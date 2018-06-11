package org.stocksrin.live;

public class Rows {
	private String total;

	private String declines;

	private String unchanged;

	private String advances;

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getDeclines() {
		return declines;
	}

	public void setDeclines(String declines) {
		this.declines = declines;
	}

	public String getUnchanged() {
		return unchanged;
	}

	public void setUnchanged(String unchanged) {
		this.unchanged = unchanged;
	}

	public String getAdvances() {
		return advances;
	}

	public void setAdvances(String advances) {
		this.advances = advances;
	}

	@Override
	public String toString() {
		return "ClassPojo [total = " + total + " declines = " + declines + " unchanged = " + unchanged + " advances = " + advances + "]";
	}
}
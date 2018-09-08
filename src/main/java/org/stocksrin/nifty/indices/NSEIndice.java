package org.stocksrin.nifty.indices;

public class NSEIndice {

	private String date;
	private String change;
	private String imgFileName;
	private String name;
	private String pChange;
	private String lastPrice;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpChange() {
		return pChange;
	}

	public void setpChange(String pChange) {
		this.pChange = pChange;
	}

	public String getLastPrice() {
		return lastPrice.replace("", "");
	}

	public void setLastPrice(String lastPrice) {
		this.lastPrice = lastPrice;
	}

	public String toCsv() {
		return date + "," + change + "," + pChange + "," + lastPrice;
	}

	@Override
	public String toString() {
		return "NSEIndice [date=" + date + ", change=" + change + ", imgFileName=" + imgFileName + ", name=" + name + ", pChange=" + pChange + ", lastPrice=" + lastPrice + "]";
	}

}

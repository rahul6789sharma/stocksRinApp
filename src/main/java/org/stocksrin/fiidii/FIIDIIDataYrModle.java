package org.stocksrin.fiidii;

public class FIIDIIDataYrModle {

	private String month;
	private String fii_BuyValue;
	private String fii_SellValue;
	private String fii_net;

	private String dii_BuyValue;
	private String dii_SellValue;
	private String dii_net;

	private String niftyStartMonth;
	private String niftyStartEnd;
	private String nifty_change;

	public String toCsv() {
		return month + "" + fii_BuyValue + "" + fii_SellValue + "" + fii_net + "" + dii_BuyValue + "" + dii_SellValue + "" + dii_net + "" + niftyStartMonth + "" + niftyStartEnd + ""
				+ nifty_change;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getFii_BuyValue() {
		return fii_BuyValue;
	}

	public void setFii_BuyValue(String fii_BuyValue) {
		this.fii_BuyValue = fii_BuyValue;
	}

	public String getFii_SellValue() {
		return fii_SellValue;
	}

	public void setFii_SellValue(String fii_SellValue) {
		this.fii_SellValue = fii_SellValue;
	}

	public String getFii_net() {
		return fii_net;
	}

	public void setFii_net(String fii_net) {
		this.fii_net = fii_net;
	}

	public String getDii_BuyValue() {
		return dii_BuyValue;
	}

	public void setDii_BuyValue(String dii_BuyValue) {
		this.dii_BuyValue = dii_BuyValue;
	}

	public String getDii_SellValue() {
		return dii_SellValue;
	}

	public void setDii_SellValue(String dii_SellValue) {
		this.dii_SellValue = dii_SellValue;
	}

	public String getDii_net() {
		return dii_net;
	}

	public void setDii_net(String dii_net) {
		this.dii_net = dii_net;
	}

	public String getNiftyStartMonth() {
		return niftyStartMonth;
	}

	public void setNiftyStartMonth(String niftyStartMonth) {
		this.niftyStartMonth = niftyStartMonth;
	}

	public String getNiftyStartEnd() {
		return niftyStartEnd;
	}

	public void setNiftyStartEnd(String niftyStartEnd) {
		this.niftyStartEnd = niftyStartEnd;
	}

	public String getNifty_change() {
		return nifty_change;
	}

	public void setNifty_change(String nifty_change) {
		this.nifty_change = nifty_change;
	}

	@Override
	public String toString() {
		return "FIIDIIDataYrModle [month=" + month + " fii_BuyValue=" + fii_BuyValue + " fii_SellValue=" + fii_SellValue + " fii_net=" + fii_net + " dii_BuyValue=" + dii_BuyValue
				+ " dii_SellValue=" + dii_SellValue + " dii_net=" + dii_net + " niftyStartMonth=" + niftyStartMonth + " niftyStartEnd=" + niftyStartEnd + " nifty_change=" + nifty_change + "]";
	}

}

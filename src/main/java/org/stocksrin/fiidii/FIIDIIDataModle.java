package org.stocksrin.fiidii;

public class FIIDIIDataModle {

	private String date;
	private String fii_BuyValue;
	private String fii_SellValue;
	private String fii_net;

	private String dii_BuyValue;
	private String dii_SellValue;
	private String dii_net;

	private String niftyprice;
	private String niftyChange;
	private String stocks_Advance;
	private String stocks_Decline;

	public String toCsv() {
		return date + "," + fii_BuyValue + "," + fii_SellValue + "," + fii_net + "," + dii_BuyValue + ","
				+ dii_SellValue + "," + dii_net + "," + niftyprice + "," + niftyChange + "," + stocks_Advance + ","
				+ stocks_Decline;
	}

	public FIIDIIDataModle() {

	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getNiftyChange() {
		return niftyChange;
	}

	public void setNiftyChange(String niftyChange) {
		this.niftyChange = niftyChange;
	}

	public String getStocks_Advance() {
		return stocks_Advance;
	}

	public void setStocks_Advance(String stocks_Advance) {
		this.stocks_Advance = stocks_Advance;
	}

	public String getStocks_Decline() {
		return stocks_Decline;
	}

	public void setStocks_Decline(String stocks_Decline) {
		this.stocks_Decline = stocks_Decline;
	}

	public String getNiftyprice() {
		return niftyprice;
	}

	public void setNiftyprice(String niftyprice) {
		this.niftyprice = niftyprice;
	}

	@Override
	public String toString() {
		return "FIIDIIDataModle [date=" + date + " fii_BuyValue=" + fii_BuyValue + " fii_SellValue=" + fii_SellValue
				+ " fii_net=" + fii_net + " dii_BuyValue=" + dii_BuyValue + " dii_SellValue=" + dii_SellValue
				+ " dii_net=" + dii_net + " niftyprice=" + niftyprice + " niftyChange=" + niftyChange
				+ " stocks_Advance=" + stocks_Advance + " stocks_Decline=" + stocks_Decline + "]";
	}

}

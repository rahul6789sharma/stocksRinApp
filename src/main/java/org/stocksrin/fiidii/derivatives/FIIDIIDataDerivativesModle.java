package org.stocksrin.fiidii.derivatives;

public class FIIDIIDataDerivativesModle {

	private String date;
	private String derivativeProducts;
	private String buyContract;
	private String buyValue;
	private String sellContract;
	private String sellValue;

	public String toCsv() {
		return date + "," + derivativeProducts + "," + buyContract + "," + sellContract;
	}

	public FIIDIIDataDerivativesModle(String date, String derivativeProducts, String buyContract, String buyValue,
			String sellContract, String sellValue) {
		super();
		this.date = date;
		this.derivativeProducts = derivativeProducts;
		this.buyContract = buyContract;
		this.buyValue = buyValue;
		this.sellContract = sellContract;
		this.sellValue = sellValue;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDerivativeProducts() {
		return derivativeProducts;
	}

	public void setDerivativeProducts(String derivativeProducts) {
		this.derivativeProducts = derivativeProducts;
	}

	public String getBuyContract() {
		return buyContract;
	}

	public void setBuyContract(String buyContract) {
		this.buyContract = buyContract;
	}

	public String getBuyValue() {
		return buyValue;
	}

	public void setBuyValue(String buyValue) {
		this.buyValue = buyValue;
	}

	public String getSellContract() {
		return sellContract;
	}

	public void setSellContract(String sellContract) {
		this.sellContract = sellContract;
	}

	public String getSellValue() {
		return sellValue;
	}

	public void setSellValue(String sellValue) {
		this.sellValue = sellValue;
	}

	@Override
	public String toString() {
		return "FIIDIIDataDerivativesModle [date=" + date + ", derivativeProducts=" + derivativeProducts
				+ ", buyContract=" + buyContract + ", buyValue=" + buyValue + ", sellContract=" + sellContract
				+ ", sellValue=" + sellValue + "]";
	}

}

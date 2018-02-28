package org.stocksrin.utils;

public class NseStock {

	private String symbol;
	private String series;
	private String date;
	private float previous_close_price;
	private float open_price;
	private float high_price;
	private float low_price;
	private float close_price;
	private long total_traded_quantity;
	private long delivery_quantity;
	private float delivery_percentage;

	public NseStock(String symbol, String series, String date, float previous_close_price, float open_price, float high_price, float low_price,
			float close_price, long total_traded_quantity, long delivery_quantity, float delivery_percentage) {
		super();
		this.symbol = symbol;
		this.series = series;
		this.date = date;
		this.previous_close_price = previous_close_price;
		this.open_price = open_price;
		this.high_price = high_price;
		this.low_price = low_price;
		this.close_price = close_price;
		this.total_traded_quantity = total_traded_quantity;
		this.delivery_quantity = delivery_quantity;
		this.delivery_percentage = delivery_percentage;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getPrevious_close_price() {
		return previous_close_price;
	}

	public void setPrevious_close_price(float previous_close_price) {
		this.previous_close_price = previous_close_price;
	}

	public float getOpen_price() {
		return open_price;
	}

	public void setOpen_price(float open_price) {
		this.open_price = open_price;
	}

	public float getHigh_price() {
		return high_price;
	}

	public void setHigh_price(float high_price) {
		this.high_price = high_price;
	}

	public float getLow_price() {
		return low_price;
	}

	public void setLow_price(float low_price) {
		this.low_price = low_price;
	}

	public float getClose_price() {
		return close_price;
	}

	public void setClose_price(float close_price) {
		this.close_price = close_price;
	}

	public long getTotal_traded_quantity() {
		return total_traded_quantity;
	}

	public void setTotal_traded_quantity(long total_traded_quantity) {
		this.total_traded_quantity = total_traded_quantity;
	}

	public float getDelivery_percentage() {
		return delivery_percentage;
	}

	public void setDelivery_percentage(float delivery_percentage) {
		this.delivery_percentage = delivery_percentage;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public long getDelivery_quantity() {
		return delivery_quantity;
	}

	public void setDelivery_quantity(long delivery_quantity) {
		this.delivery_quantity = delivery_quantity;
	}

	@Override
	public String toString() {
		return "NseStock [symbol=" + symbol + ", series=" + series + ", date=" + date + ", previous_close_price=" + previous_close_price
				+ ", open_price=" + open_price + ", high_price=" + high_price + ", low_price=" + low_price + ", close_price=" + close_price
				+ ", total_traded_quantity=" + total_traded_quantity + ", delivery_quantity=" + delivery_quantity + ", delivery_percentage="
				+ delivery_percentage + "]";
	}

}
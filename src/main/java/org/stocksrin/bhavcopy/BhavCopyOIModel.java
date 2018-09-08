package org.stocksrin.bhavcopy;

public class BhavCopyOIModel {

	private String instrument;
	private String symbol;
	private String expiry_dt;
	private String strike_pr;
	private String option_typ;
	private double open;
	private double high;
	private double low;
	private double close;
	private double settle_pr;
	private int contracts;
	private double val_inlakh;
	private int open_int;
	private int chg_in_oi;
	private String timestamp;

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getExpiry_dt() {
		return expiry_dt;
	}

	public void setExpiry_dt(String expiry_dt) {
		this.expiry_dt = expiry_dt;
	}

	public String getStrike_pr() {
		return strike_pr;
	}

	public void setStrike_pr(String strike_pr) {
		this.strike_pr = strike_pr;
	}

	public String getOption_typ() {
		return option_typ;
	}

	public void setOption_typ(String option_typ) {
		this.option_typ = option_typ;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getSettle_pr() {
		return settle_pr;
	}

	public void setSettle_pr(double settle_pr) {
		this.settle_pr = settle_pr;
	}

	public int getContracts() {
		return contracts;
	}

	public void setContracts(int contracts) {
		this.contracts = contracts;
	}

	public double getVal_inlakh() {
		return val_inlakh;
	}

	public void setVal_inlakh(double val_inlakh) {
		this.val_inlakh = val_inlakh;
	}

	public int getOpen_int() {
		return open_int;
	}

	public void setOpen_int(int open_int) {
		this.open_int = open_int;
	}

	public int getChg_in_oi() {
		return chg_in_oi;
	}

	public void setChg_in_oi(int chg_in_oi) {
		this.chg_in_oi = chg_in_oi;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "BhavCopyOIModel [instrument=" + instrument + ", symbol=" + symbol + ", expiry_dt=" + expiry_dt + ", strike_pr=" + strike_pr + ", option_typ=" + option_typ + ", open=" + open
				+ ", high=" + high + ", low=" + low + ", close=" + close + ", settle_pr=" + settle_pr + ", contracts=" + contracts + ", val_inlakh=" + val_inlakh + ", open_int=" + open_int
				+ ", chg_in_oi=" + chg_in_oi + ", timestamp=" + timestamp + "]";
	}

}

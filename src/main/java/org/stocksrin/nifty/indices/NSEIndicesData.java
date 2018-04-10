package org.stocksrin.nifty.indices;

public class NSEIndicesData {

	private String preOpen;

	private String time;

	private String corrOpen;

	private String status;

	private String haltedStatus;

	private String mktOpen;

	private NSEIndice[] data;

	private String code;

	private String preClose;

	private String corrClose;

	private String mktClose;

	public String getPreOpen() {
		return preOpen;
	}

	public void setPreOpen(String preOpen) {
		this.preOpen = preOpen;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCorrOpen() {
		return corrOpen;
	}

	public void setCorrOpen(String corrOpen) {
		this.corrOpen = corrOpen;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHaltedStatus() {
		return haltedStatus;
	}

	public void setHaltedStatus(String haltedStatus) {
		this.haltedStatus = haltedStatus;
	}

	public String getMktOpen() {
		return mktOpen;
	}

	public void setMktOpen(String mktOpen) {
		this.mktOpen = mktOpen;
	}

	public NSEIndice[] getData() {
		return data;
	}

	public void setData(NSEIndice[] data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPreClose() {
		return preClose;
	}

	public void setPreClose(String preClose) {
		this.preClose = preClose;
	}

	public String getCorrClose() {
		return corrClose;
	}

	public void setCorrClose(String corrClose) {
		this.corrClose = corrClose;
	}

	public String getMktClose() {
		return mktClose;
	}

	public void setMktClose(String mktClose) {
		this.mktClose = mktClose;
	}

	@Override
	public String toString() {
		return "ClassPojo [preOpen = " + preOpen + ", time = " + time + ", corrOpen = " + corrOpen + ", status = "
				+ status + ", haltedStatus = " + haltedStatus + ", mktOpen = " + mktOpen + ", data = " + data
				+ ", code = " + code + ", preClose = " + preClose + ", corrClose = " + corrClose + ", mktClose = "
				+ mktClose + "]";
	}
}
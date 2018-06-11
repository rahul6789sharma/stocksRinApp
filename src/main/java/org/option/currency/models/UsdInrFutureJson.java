package org.option.currency.models;

public class UsdInrFutureJson {

	private String valid;
	private String lastUpdateTime;
	private String tradedDate;
	private String ocLink;
	private Data[] data;
	private String last_updated;
	private String RBIrr;

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getTradedDate() {
		return tradedDate;
	}

	public void setTradedDate(String tradedDate) {
		this.tradedDate = tradedDate;
	}

	public String getOcLink() {
		return ocLink;
	}

	public void setOcLink(String ocLink) {
		this.ocLink = ocLink;
	}

	public Data[] getData() {
		return data;
	}

	public void setData(Data[] data) {
		this.data = data;
	}

	public String getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}

	public String getRBIrr() {
		return RBIrr;
	}

	public void setRBIrr(String RBIrr) {
		this.RBIrr = RBIrr;
	}

	@Override
	public String toString() {
		return "ClassPojo [valid = " + valid + " lastUpdateTime = " + lastUpdateTime + " tradedDate = " + tradedDate + " ocLink = " + ocLink
				+ " data = " + data + " last_updated = " + last_updated + " RBIrr = " + RBIrr + "]";
	}
}

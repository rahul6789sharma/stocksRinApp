package org.stocksrin.oi.future;

public class NiftyFutureModel {

	private String valid;

	private boolean isinCode;

	private String lastUpdateTime;

	private String tradedDate;

	private String ocLink;

	private Data[] data;

	private String eqLink;

	private String companyName;

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public boolean isIsinCode() {
		return isinCode;
	}

	public void setIsinCode(boolean isinCode) {
		this.isinCode = isinCode;
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

	public String getEqLink() {
		return eqLink;
	}

	public void setEqLink(String eqLink) {
		this.eqLink = eqLink;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "ClassPojo [valid = " + valid + ", isinCode = " + isinCode + ", lastUpdateTime = " + lastUpdateTime + ", tradedDate = " + tradedDate + ", ocLink = " + ocLink + ", data = " + data
				+ ", eqLink = " + eqLink + ", companyName = " + companyName + "]";
	}
}

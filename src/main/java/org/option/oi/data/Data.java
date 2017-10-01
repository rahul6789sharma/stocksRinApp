package org.option.oi.data;

import java.util.Date;

public class Data {

	private Date date = new Date();
	private Long oiValue = 0l;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getOiValue() {
		return oiValue;
	}

	public void setOiValue(Long oiValue) {
		this.oiValue = oiValue;
	}

}

package org.stocksrin.oi.allparticapent;

public class ParticipantOIOptionModel {

	private String date;
	private ParticipantType participantType;
	private DerivativeProducts derivativeProducts;
	private int callLong;
	private int callShort;
	private int putLong;
	private int putShort;

	public String toCsv() {
		return date + "," + participantType.toString() + "," + derivativeProducts + "," + callLong + "," + callShort + "," + putLong + "," + putShort;
	}

	public ParticipantOIOptionModel(String date, ParticipantType participantType, DerivativeProducts derivativeProducts, int callLong, int callShort, int putLong, int putShort) {
		super();
		this.date = date;
		this.participantType = participantType;
		this.derivativeProducts = derivativeProducts;
		this.callLong = callLong;
		this.callShort = callShort;
		this.putLong = putLong;
		this.putShort = putShort;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ParticipantType getParticipantType() {
		return participantType;
	}

	public void setParticipantType(ParticipantType participantType) {
		this.participantType = participantType;
	}

	public DerivativeProducts getDerivativeProducts() {
		return derivativeProducts;
	}

	public void setDerivativeProducts(DerivativeProducts derivativeProducts) {
		this.derivativeProducts = derivativeProducts;
	}

	public int getCallLong() {
		return callLong;
	}

	public void setCallLong(int callLong) {
		this.callLong = callLong;
	}

	public int getCallShort() {
		return callShort;
	}

	public void setCallShort(int callShort) {
		this.callShort = callShort;
	}

	public int getPutLong() {
		return putLong;
	}

	public void setPutLong(int putLong) {
		this.putLong = putLong;
	}

	public int getPutShort() {
		return putShort;
	}

	public void setPutShort(int putShort) {
		this.putShort = putShort;
	}

	@Override
	public String toString() {
		return "ParticipantOIOptionModel [date=" + date + ", participantType=" + participantType + ", derivativeProducts=" + derivativeProducts + ", callLong=" + callLong + ", callShort=" + callShort
				+ ", putLong=" + putLong + ", putShort=" + putShort + "]";
	}

}

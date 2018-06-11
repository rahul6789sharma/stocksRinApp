package org.stocksrin.oi.allparticapent;

public class ParticipantOIFutureModel {

	private String date;
	private ParticipantType participantType;
	private DerivativeProducts derivativeProducts;
	private int buyContract;
	private int buyValue;
	private int sellContract;
	private int sellValue;

	public String toCsv() {
		return date + ","+participantType.toString()+"," + derivativeProducts + "," + buyContract + "," + sellContract;
	}

	public ParticipantOIFutureModel(String date, ParticipantType participantType, DerivativeProducts derivativeProducts, int buyContract, int sellContract) {
		super();
		this.date = date;
		this.participantType = participantType;
		this.derivativeProducts = derivativeProducts;
		this.buyContract = buyContract;
		this.sellContract = sellContract;
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

	public int getBuyContract() {
		return buyContract;
	}

	public void setBuyContract(int buyContract) {
		this.buyContract = buyContract;
	}

	public int getBuyValue() {
		return buyValue;
	}

	public void setBuyValue(int buyValue) {
		this.buyValue = buyValue;
	}

	public int getSellContract() {
		return sellContract;
	}

	public void setSellContract(int sellContract) {
		this.sellContract = sellContract;
	}

	public int getSellValue() {
		return sellValue;
	}

	public void setSellValue(int sellValue) {
		this.sellValue = sellValue;
	}

	@Override
	public String toString() {
		return "ParticipantOIFutureModel [date=" + date + ", participantType=" + participantType + ", derivativeProducts=" + derivativeProducts + ", buyContract=" + buyContract + ", buyValue="
				+ buyValue + ", sellContract=" + sellContract + ", sellValue=" + sellValue + "]";
	}


}
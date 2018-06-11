package org.stocksrin.oi.allparticapent;

public class ParticipantOIModle {

	private String date;
	private ParticipantType participantType;
	
	private int futureIndexLong;
	private int futureIndexShort;

	private int futureStockLong;
	private int futureStockShort;

	private int optionIndexCallLong;
	private int optionIndexPutLong;
	private int optionIndexCallShort;
	private int optionIndexPutShort;

	private int optionStockCallLong;
	private int optionStockPutLong;
	private int optionStockCallShort;
	private int optionStockPutShort;

	private int totalLongContracts;
	private int totalShortContracts;

	
	public ParticipantOIModle(String date, ParticipantType participantType) {
		super();
		this.date = date;
		this.participantType = participantType;
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

	public int getFutureIndexLong() {
		return futureIndexLong;
	}

	public void setFutureIndexLong(int futureIndexLong) {
		this.futureIndexLong = futureIndexLong;
	}

	public int getFutureIndexShort() {
		return futureIndexShort;
	}

	public void setFutureIndexShort(int futureIndexShort) {
		this.futureIndexShort = futureIndexShort;
	}

	public int getFutureStockLong() {
		return futureStockLong;
	}

	public void setFutureStockLong(int futureStockLong) {
		this.futureStockLong = futureStockLong;
	}

	public int getFutureStockShort() {
		return futureStockShort;
	}

	public void setFutureStockShort(int futureStockShort) {
		this.futureStockShort = futureStockShort;
	}

	public int getOptionIndexCallLong() {
		return optionIndexCallLong;
	}

	public void setOptionIndexCallLong(int optionIndexCallLong) {
		this.optionIndexCallLong = optionIndexCallLong;
	}

	public int getOptionIndexPutLong() {
		return optionIndexPutLong;
	}

	public void setOptionIndexPutLong(int optionIndexPutLong) {
		this.optionIndexPutLong = optionIndexPutLong;
	}

	public int getOptionIndexCallShort() {
		return optionIndexCallShort;
	}

	public void setOptionIndexCallShort(int optionIndexCallShort) {
		this.optionIndexCallShort = optionIndexCallShort;
	}

	public int getOptionIndexPutShort() {
		return optionIndexPutShort;
	}

	public void setOptionIndexPutShort(int optionIndexPutShort) {
		this.optionIndexPutShort = optionIndexPutShort;
	}

	public int getOptionStockCallLong() {
		return optionStockCallLong;
	}

	public void setOptionStockCallLong(int optionStockCallLong) {
		this.optionStockCallLong = optionStockCallLong;
	}

	public int getOptionStockPutLong() {
		return optionStockPutLong;
	}

	public void setOptionStockPutLong(int optionStockPutLong) {
		this.optionStockPutLong = optionStockPutLong;
	}

	public int getOptionStockCallShort() {
		return optionStockCallShort;
	}

	public void setOptionStockCallShort(int optionStockCallShort) {
		this.optionStockCallShort = optionStockCallShort;
	}

	public int getOptionStockPutShort() {
		return optionStockPutShort;
	}

	public void setOptionStockPutShort(int optionStockPutShort) {
		this.optionStockPutShort = optionStockPutShort;
	}

	public int getTotalLongContracts() {
		return totalLongContracts;
	}

	public void setTotalLongContracts(int totalLongContracts) {
		this.totalLongContracts = totalLongContracts;
	}

	public int getTotalShortContracts() {
		return totalShortContracts;
	}

	public void setTotalShortContracts(int totalShortContracts) {
		this.totalShortContracts = totalShortContracts;
	}

	@Override
	public String toString() {
		return "ParticipantOIModle [date=" + date + ", participantType=" + participantType + ", futureIndexLong=" + futureIndexLong
				+ ", futureIndexShort=" + futureIndexShort + ", futureStockLong=" + futureStockLong + ", futureStockShort=" + futureStockShort + ", optionIndexCallLong=" + optionIndexCallLong
				+ ", optionIndexPutLong=" + optionIndexPutLong + ", optionIndexCallShort=" + optionIndexCallShort + ", optionIndexPutShort=" + optionIndexPutShort + ", optionStockCallLong="
				+ optionStockCallLong + ", optionStockPutLong=" + optionStockPutLong + ", optionStockCallShort=" + optionStockCallShort + ", optionStockPutShort=" + optionStockPutShort
				+ ", totalLongContracts=" + totalLongContracts + ", totalShortContracts=" + totalShortContracts + "]";
	}

}

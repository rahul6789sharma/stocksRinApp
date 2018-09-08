package org.stocksrin.option.banknifty;

import java.util.ArrayList;
import java.util.List;

public class LiveMaxPainModle {

	private List<Double> maxPains = new ArrayList<>();

	private Integer totalCE;
	private Integer totalPE;

	private List<Integer> higestOICE = new ArrayList<>();
	private List<Integer> higestOIPE = new ArrayList<>();

	public List<Double> getMaxPains() {
		return maxPains;
	}

	public void setMaxPains(List<Double> maxPains) {
		this.maxPains = maxPains;
	}

	public Integer getTotalCE() {
		return totalCE;
	}

	public void setTotalCE(Integer totalCE) {
		this.totalCE = totalCE;
	}

	public Integer getTotalPE() {
		return totalPE;
	}

	public void setTotalPE(Integer totalPE) {
		this.totalPE = totalPE;
	}

	@Override
	public String toString() {
		return "LiveMaxPainModle [maxPains=" + maxPains + " totalCE=" + totalCE + " totalPE=" + totalPE + "]";
	}

}

package org.stocksrin.live;

public class AdvancedDeclinePojo {

	private String results;
	private String success;
	private Rows[] rows;

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Rows[] getRows() {
		return rows;
	}

	public void setRows(Rows[] rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "ClassPojo [results = " + results + " success = " + success + " rows = " + rows + "]";
	}
}
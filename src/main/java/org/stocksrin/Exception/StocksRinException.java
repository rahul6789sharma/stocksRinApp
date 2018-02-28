package org.stocksrin.Exception;

public class StocksRinException extends Exception {

	private static final long serialVersionUID = 1247255613805984353L;

	public StocksRinException(String msg) {
		super(msg);
	}

	public StocksRinException(Throwable msg) {
		super(msg);
	}

	public StocksRinException(String msg, Throwable t) {
		super(msg, t);
	}
}

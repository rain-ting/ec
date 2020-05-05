package com.newer.deal.exception;

public class NoCurrencyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoCurrencyException() {
		super("货币不足");
	}
	
	public NoCurrencyException(String msg) {
		super(msg);
	}

}

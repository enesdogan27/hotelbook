package com.ed.hotelbook.config;

public class CustomerNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1172895362057271563L;

	public CustomerNotFoundException(String message) {
		super(message, null);
	}
}
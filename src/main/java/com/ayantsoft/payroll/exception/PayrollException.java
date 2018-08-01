package com.ayantsoft.payroll.exception;

public class PayrollException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3362862268557829463L;
	
	public PayrollException(String message) {
		super( message );
	}

	public PayrollException(Throwable cause) {
		super( cause );
	}

	public PayrollException(String message, Throwable cause) {
		super( message, cause );
	}
}

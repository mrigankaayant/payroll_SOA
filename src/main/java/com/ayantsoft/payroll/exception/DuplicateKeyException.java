package com.ayantsoft.payroll.exception;

public class DuplicateKeyException extends PayrollException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7600608031236136131L;

	public DuplicateKeyException(String message) {
		super(message);
	}
}

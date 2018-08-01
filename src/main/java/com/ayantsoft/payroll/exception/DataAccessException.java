package com.ayantsoft.payroll.exception;

import org.apache.log4j.Logger;

public class DataAccessException extends PayrollException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8674263100264831720L;
	
	private Logger log = Logger.getLogger(DataAccessException.class);

	public DataAccessException(){
		super ("DATA ACCESS EXCEPTION");
		log.error("EXCEPTION DURING DATA ACCESS");
	}
}

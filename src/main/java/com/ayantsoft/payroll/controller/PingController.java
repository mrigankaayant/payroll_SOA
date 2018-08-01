package com.ayantsoft.payroll.controller;

import java.io.Serializable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2053643960679535444L;
	
	@RequestMapping(value = "/")
	public String doPing(){
		return "Payroll Management System";
	}
}

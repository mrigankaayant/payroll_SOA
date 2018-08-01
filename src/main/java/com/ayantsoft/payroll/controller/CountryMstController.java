package com.ayantsoft.payroll.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayantsoft.payroll.exception.PayrollException;
import com.ayantsoft.payroll.hibernate.pojo.CountryMst;
import com.ayantsoft.payroll.service.CountryMstService;

@RestController
public class CountryMstController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4183742927166100863L;
	
	private Logger log = Logger.getLogger(CountryMstController.class);
	
	@Autowired
	private CountryMstService countryMstService;
	
	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public ResponseEntity<?> getCountryMsts(){
		List<CountryMst> countryMsts = null;
		HttpStatus httpStatus = null;
		try{
			countryMsts = countryMstService.getCountryMsts();
			
			if(countryMsts == null || countryMsts.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.error("getCountryMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<CountryMst>>(countryMsts, httpStatus);
	}
	
	@RequestMapping(value = "/country/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCountryMstById(@PathVariable int id){
		CountryMst countryMst = null;
		HttpStatus httpStatus = null;
		try{
			countryMst = countryMstService.getCountryMstById(id);
			
			if(countryMst != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}
		}catch(PayrollException pe){
			log.error("getCountryMstById Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<CountryMst>(countryMst, httpStatus);
	}
}

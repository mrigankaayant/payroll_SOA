package com.ayantsoft.payroll.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayantsoft.payroll.exception.PayrollException;
import com.ayantsoft.payroll.hibernate.pojo.CityMst;
import com.ayantsoft.payroll.hibernate.pojo.StateMst;
import com.ayantsoft.payroll.service.CityMstService;

@RestController
public class CityMstController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5820756098519486303L;
	
	private Logger log = Logger.getLogger(CityMstController.class);
	
	@Autowired
	private CityMstService cityMstService;
	
	@RequestMapping(value = "/cities", method = RequestMethod.GET)
	public ResponseEntity<?> getCityMsts(){
		List<CityMst> cityMsts = null;
		HttpStatus httpStatus = null;
		try{
			cityMsts = cityMstService.getCityMsts();
			
			if(cityMsts == null || cityMsts.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.error("getCityMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<CityMst>>(cityMsts, httpStatus);
	}
	
	@RequestMapping(value = "/city/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCityMstById(@PathVariable int id){
		CityMst cityMst = null;
		HttpStatus httpStatus = null;
		try{
			cityMst = cityMstService.getCityMstById(id);
			
			if(cityMst != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}
		}catch(PayrollException pe){
			log.error("getCityMstById Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<CityMst>(cityMst, httpStatus);
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/state/{id}/cities",method=RequestMethod.GET)
	public ResponseEntity<?> getStateByCountryId(@PathVariable int id){
		
		List<CityMst> cityMsts = null;
		HttpStatus httpStatus = null;
		try{
			cityMsts =  cityMstService.getCityByStateId(id);
			if(cityMsts == null){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}

		}catch(PayrollException pe){
			log.error("getCityMstBy state Id Error : "+pe);
			httpStatus= HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<List<CityMst>>(cityMsts, httpStatus);
	}
}

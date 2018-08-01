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
import com.ayantsoft.payroll.hibernate.pojo.StateMst;
import com.ayantsoft.payroll.service.StateMstService;

@RestController
public class StateMstController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5472200953924391911L;
	
	private Logger log = Logger.getLogger(StateMstController.class);
	
	@Autowired
	private StateMstService stateMstService;
	
	@RequestMapping(value = "/states", method = RequestMethod.GET)
	public ResponseEntity<?> getStateMsts(){
		List<StateMst> stateMsts = null;
		HttpStatus httpStatus = null;
		try{
			stateMsts = stateMstService.getStateMsts();
			
			if(stateMsts == null || stateMsts.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.info("getStateMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<StateMst>>(stateMsts, httpStatus);
	}
	
	@RequestMapping(value = "/state/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getStateMstById(@PathVariable int id){
		StateMst stateMst = null;
		HttpStatus httpStatus = null;
		try{
			stateMst = stateMstService.getStateMstById(id);
			
			if(stateMst != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}
		}catch(PayrollException pe){
			log.info("getStateMstById : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<StateMst>(stateMst, httpStatus);
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/country/{id}/states",method=RequestMethod.GET)
	public ResponseEntity<?> getStateByCountryId(@PathVariable int id){
		
		List<StateMst> stateMsts = null;
		HttpStatus httpStatus = null;
		try{
			stateMsts =  stateMstService.getStateByCountryId(id);
			if(stateMsts == null){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}

		}catch(PayrollException pe){
			log.error("Payroll earning get By id : "+pe);
			httpStatus= HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<List<StateMst>>(stateMsts, httpStatus);
	}
}

package com.ayantsoft.payroll.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayantsoft.payroll.exception.PayrollException;
import com.ayantsoft.payroll.hibernate.pojo.ExemptionYear;
import com.ayantsoft.payroll.service.ExemptionYearService;

@RestController
public class ExemptionYearController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8339354301680017013L;
	
	private Logger log = Logger.getLogger(ExemptionYearController.class);
	
	@Autowired
	private ExemptionYearService exemptionYearService;	
	
	@RequestMapping(value = "/exemptionYears", method = RequestMethod.GET)
	public ResponseEntity<?> getExemptionYears(){
		List<ExemptionYear> exemptionYears = null;
		HttpStatus httpStatus = null;
		try{
			exemptionYears = exemptionYearService.getExemptionYears();
			
			if(exemptionYears == null || exemptionYears.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.error("getExemptionYears Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<ExemptionYear>>(exemptionYears, httpStatus);
	}
	
	@RequestMapping(value = "/exemptionYear/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getExemptionYearById(@PathVariable int id){
		ExemptionYear exemptionYear = null;
		HttpStatus httpStatus = null;
		try{
			exemptionYear = exemptionYearService.getExemptionYearById(id);
			
			if(exemptionYear != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}
		}catch(PayrollException pe){
			log.error("getExemptionYearById Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<ExemptionYear>(exemptionYear, httpStatus);
	}
	
	@RequestMapping(value = "/exemptionYears/search", method = RequestMethod.POST)
	public ResponseEntity<?> getExemptionYearsByProperties(@RequestBody ExemptionYear exemptionYear){
		List<ExemptionYear> exemptionYears = null;
		HttpStatus httpStatus = null;
		try{
			exemptionYears = exemptionYearService.getExemptionYearsByProperties(exemptionYear);
			
			if(exemptionYears != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}
		}catch(PayrollException pe){
			log.error("getExemptionYearsByProperties Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<ExemptionYear>>(exemptionYears, httpStatus);
	}
	
	/*CHECK searchValue WITH SERIALIZABLE DATATYPE*/
	@RequestMapping(value = "/exemptionYears/{searchKey}/{searchValue}", method = RequestMethod.GET)
	public ResponseEntity<?> getExemptionYearsByProperty(@PathVariable String searchKey, @PathVariable String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(value = "/exemptionYears", method = RequestMethod.POST)
	public ResponseEntity<?> insertExemptionYears(@RequestBody List<ExemptionYear> exemptionYears) {
		HttpStatus httpStatus = null;
		try{
			exemptionYearService.insertExemptionYears(exemptionYears);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.error("insertExemptionYears Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<ExemptionYear>>(exemptionYears, httpStatus);
	}
	
	@RequestMapping(value = "/exemptionYear", method = RequestMethod.POST)
	public ResponseEntity<?> insertExemptionYear(@RequestBody ExemptionYear exemptionYear){
		HttpStatus httpStatus = null;
		try{
			exemptionYearService.insertExemptionYear(exemptionYear);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.error("insertExemptionYear Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<ExemptionYear>(exemptionYear, httpStatus);
	}
	
	@RequestMapping(value = "/exemptionYears", method = RequestMethod.PUT)
	public ResponseEntity<?> updateExemptionYears(@RequestBody List<ExemptionYear> exemptionYears) {
		HttpStatus httpStatus = null;
		try{
			exemptionYearService.updateExemptionYears(exemptionYears);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.error("updateExemptionYears Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<ExemptionYear>>(exemptionYears, httpStatus);
	}
	
	@RequestMapping(value = "/exemptionYear", method = RequestMethod.PUT)
	public ResponseEntity<?> updateExemptionYear(@RequestBody ExemptionYear exemptionYear) {
		HttpStatus httpStatus = null;
		try{
			exemptionYearService.updateExemptionYear(exemptionYear);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.error("updateExemptionYear Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<ExemptionYear>(exemptionYear, httpStatus);
	}
	
	@RequestMapping(value = "/exemptionYears", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteExemptionYearById(@PathVariable int id) {
		HttpStatus httpStatus = null;
		try{
			exemptionYearService.deleteExemptionYearById(id);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.error("deleteExemptionYearById Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<HttpStatus>(httpStatus);
	}

}

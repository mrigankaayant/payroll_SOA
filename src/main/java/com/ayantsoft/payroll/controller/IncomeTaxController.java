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
import com.ayantsoft.payroll.hibernate.pojo.EmployeeMst;
import com.ayantsoft.payroll.hibernate.pojo.IncomeTax;
import com.ayantsoft.payroll.service.IncomeTaxService;

@RestController
public class IncomeTaxController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5410980640653139792L;
	
	private Logger log = Logger.getLogger(IncomeTaxController.class);
	
	@Autowired
	private IncomeTaxService incomeTaxService;
	
	@RequestMapping(value = "/incomeTaxs", method = RequestMethod.GET)
	public ResponseEntity<?> getIncomeTaxs(){
		List<IncomeTax> incomeTaxs = null;
		HttpStatus httpStatus = null;
		try{
			incomeTaxs = incomeTaxService.getIncomeTaxs();
			
			if(incomeTaxs == null || incomeTaxs.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.info("getIncomeTaxs Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<IncomeTax>>(incomeTaxs, httpStatus);
	}
	
	@RequestMapping(value = "/incomeTax/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getIncomeTaxById(@PathVariable int id){
		IncomeTax incomeTax = null;
		HttpStatus httpStatus = null;
		try{
			incomeTax = incomeTaxService.getIncomeTaxById(id);
			
			if(incomeTax != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}
		}catch(PayrollException pe){
			log.info("getIncomeTaxById Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<IncomeTax>(incomeTax, httpStatus);
	}
	
	@RequestMapping(value = "/incomeTaxs/search", method = RequestMethod.POST)
	public ResponseEntity<?> getIncomeTaxsByProperties(@RequestBody IncomeTax incomeTax){
		List<IncomeTax> incomeTaxs = null;
		HttpStatus httpStatus = null;
		try{			
			incomeTaxs = incomeTaxService.getIncomeTaxsByProperties(incomeTax);
			
			if(incomeTaxs == null || incomeTaxs.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.info("getIncomeTaxsByProperties Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<IncomeTax>>(incomeTaxs, httpStatus);
	}
	
	/*CHECK searchValue WITH SERIALIZABLE DATATYPE*/
	@RequestMapping(value = "/incomeTaxs/{searchKey}/{searchValue}", method = RequestMethod.GET)
	public ResponseEntity<?> getTaxPayerGroupMstsByProperty(@PathVariable String searchKey, @PathVariable String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(value = "/incomeTaxs", method = RequestMethod.POST)
	public ResponseEntity<?> insertIncomeTaxs(@RequestBody List<IncomeTax> incomeTaxs) {
		HttpStatus httpStatus = null;
		try{
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.info("insertIncomeTaxs Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<IncomeTax>>(incomeTaxs, httpStatus);
	}
	
	@RequestMapping(value = "/incomeTax", method = RequestMethod.POST)
	public ResponseEntity<?> insertIncomeTax(@RequestBody IncomeTax incomeTax){
		HttpStatus httpStatus = null;
		try{
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.info("insertIncomeTax Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<IncomeTax>(incomeTax, httpStatus);
	}
	
	
	@RequestMapping(value = "/incomeTaxs", method = RequestMethod.PUT)
	public ResponseEntity<?> updateIncomeTaxs(@RequestBody List<EmployeeMst> employeeMsts) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(value = "/incomeTax", method = RequestMethod.PUT)
	public ResponseEntity<?> updateIncomeTax(@RequestBody IncomeTax incomeTax) {
		HttpStatus httpStatus = null;
		try{
			incomeTaxService.updateIncomeTax(incomeTax);			
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.info("updateIncomeTax Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<IncomeTax>(incomeTax, httpStatus);
	}
	
	@RequestMapping(value = "/incomeTax", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteIncomeTaxById(@PathVariable int id) {
		HttpStatus httpStatus = null;
		try{
			incomeTaxService.deleteIncomeTaxById(id);
			httpStatus = HttpStatus.OK;
		}catch(Exception e){
			log.info("Error : ", e);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<HttpStatus>(httpStatus);
	}
}

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
import com.ayantsoft.payroll.hibernate.pojo.IncomeTaxExemptionMst;
import com.ayantsoft.payroll.service.IncomeTaxExemptionMstService;

@RestController
public class IncomeTaxExemptionMstController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1497889755843085451L;
	
	private Logger log = Logger.getLogger(IncomeTaxExemptionMstController.class);
	
	@Autowired
	private IncomeTaxExemptionMstService incomeTaxExemptionMstService;
	
	@RequestMapping(value = "/incomeTaxExemptions", method = RequestMethod.GET)
	public ResponseEntity<?> getIncomeTaxExemptionMsts(){
		List<IncomeTaxExemptionMst> incomeTaxExemptionMsts = null;
		HttpStatus httpStatus = null;
		try{
			incomeTaxExemptionMsts = incomeTaxExemptionMstService.getIncomeTaxExemptionMsts();
			
			if(incomeTaxExemptionMsts == null || incomeTaxExemptionMsts.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.error("getIncomeTaxExemptionMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<IncomeTaxExemptionMst>>(incomeTaxExemptionMsts, httpStatus);
	}
	
	@RequestMapping(value = "/incomeTaxExemption/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getIncomeTaxExemptionMstById(@PathVariable int id){
		IncomeTaxExemptionMst incomeTaxExemptionMst = null;
		HttpStatus httpStatus = null;
		try{
			incomeTaxExemptionMst = incomeTaxExemptionMstService.getIncomeTaxExemptionMstById(id);
			
			if(incomeTaxExemptionMst != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}
		}catch(PayrollException pe){
			log.error("getIncomeTaxExemptionMstById Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<IncomeTaxExemptionMst>(incomeTaxExemptionMst, httpStatus);
	}
	
	@RequestMapping(value = "/incomeTaxExemptions/search", method = RequestMethod.POST)
	public ResponseEntity<?> getIncomeTaxExemptionMstsByProperties(@RequestBody IncomeTaxExemptionMst incomeTaxExemptionMst){
		List<IncomeTaxExemptionMst> incomeTaxExemptionMsts = null;
		HttpStatus httpStatus = null;
		try{
			incomeTaxExemptionMsts = incomeTaxExemptionMstService.getIncomeTaxExemptionMstsByProperties(incomeTaxExemptionMst);
			
			if(incomeTaxExemptionMsts == null || incomeTaxExemptionMsts.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.error("getIncomeTaxExemptionMstsByProperties Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<IncomeTaxExemptionMst>>(incomeTaxExemptionMsts, httpStatus);
	}
	
	/*CHECK searchValue WITH SERIALIZABLE DATATYPE*/
	@RequestMapping(value = "/incomeTaxExemptions/{searchKey}/{searchValue}", method = RequestMethod.GET)
	public ResponseEntity<?> getIncomeTaxExemptionMstsByProperty(@PathVariable String searchKey, @PathVariable String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(value = "/incomeTaxExemptions", method = RequestMethod.POST)
	public ResponseEntity<?> insertIncomeTaxExemptionMsts(@RequestBody List<IncomeTaxExemptionMst> incomeTaxExemptionMsts) {
		HttpStatus httpStatus = null;
		try{
			incomeTaxExemptionMstService.insertIncomeTaxExemptionMsts(incomeTaxExemptionMsts);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.error("insertIncomeTaxExemptionMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<IncomeTaxExemptionMst>>(incomeTaxExemptionMsts, httpStatus);
	}
	
	@RequestMapping(value = "/incomeTaxExemption",method = RequestMethod.POST)
	public ResponseEntity<?> insertIncomeTaxExemptionMst(@RequestBody IncomeTaxExemptionMst incomeTaxExemptionMst){
		HttpStatus httpStatus = null;
		try{
			incomeTaxExemptionMstService.insertIncomeTaxExemptionMst(incomeTaxExemptionMst);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.error("insertIncomeTaxExemptionMst Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<IncomeTaxExemptionMst>(incomeTaxExemptionMst, httpStatus);
	}
	
	@RequestMapping(value = "/incomeTaxExemptions", method = RequestMethod.PUT)
	public ResponseEntity<?> updateIncomeTaxExemptionMsts(@RequestBody List<IncomeTaxExemptionMst> incomeTaxExemptionMsts) {
		HttpStatus httpStatus = null;
		try{
			incomeTaxExemptionMstService.updateIncomeTaxExemptionMsts(incomeTaxExemptionMsts);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.error("updateIncomeTaxExemptionMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<IncomeTaxExemptionMst>>(incomeTaxExemptionMsts, httpStatus);
	}
	
	@RequestMapping(value = "/incomeTaxExemption", method = RequestMethod.PUT)
	public ResponseEntity<?> updateIncomeTaxExemptionMst(@RequestBody IncomeTaxExemptionMst incomeTaxExemptionMst) {
		HttpStatus httpStatus = null;
		try{
			incomeTaxExemptionMstService.updateIncomeTaxExemptionMst(incomeTaxExemptionMst);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.error("updateIncomeTaxExemptionMst Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<IncomeTaxExemptionMst>(incomeTaxExemptionMst, httpStatus);
	}
	
	@RequestMapping(value = "/incomeTaxExemption", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteIncomeTaxExemptionMstById(@PathVariable int id) {
		HttpStatus httpStatus = null;
		try{
			incomeTaxExemptionMstService.deleteIncomeTaxExemptionMstById(id);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.error("deleteIncomeTaxExemptionMstById Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<HttpStatus>(httpStatus);
	}
}

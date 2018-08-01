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
import com.ayantsoft.payroll.hibernate.pojo.TaxPayerGroupMst;
import com.ayantsoft.payroll.service.TaxPayerGroupMstService;

@RestController
public class TaxPayerGroupMstController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3823668744353300637L;
	
	private Logger log = Logger.getLogger(TaxPayerGroupMstController.class);
	
	@Autowired
	private TaxPayerGroupMstService taxPayerGroupMstService;
	
	@RequestMapping(value = "/taxPayerGroups", method = RequestMethod.GET)
	public ResponseEntity<?> getTaxPayerGroupMsts(){
		List<TaxPayerGroupMst> taxPayerGroupMsts = null;
		HttpStatus httpStatus = null;
		try{
			taxPayerGroupMsts = taxPayerGroupMstService.getTaxPayerGroupMsts();
			
			if(taxPayerGroupMsts == null || taxPayerGroupMsts.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.info("getTaxPayerGroupMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<TaxPayerGroupMst>>(taxPayerGroupMsts, httpStatus);
	}
	
	@RequestMapping(value = "/taxPayerGroup/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getTaxPayerGroupMstById(@PathVariable int id){
		TaxPayerGroupMst taxPayerGroupMst = null;
		HttpStatus httpStatus = null;
		try{
			taxPayerGroupMst = taxPayerGroupMstService.getTaxPayerGroupMstById(id);
			
			if(taxPayerGroupMst != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}
		}catch(PayrollException pe){
			log.info("getTaxPayerGroupMstById Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<TaxPayerGroupMst>(taxPayerGroupMst, httpStatus);
	}
	
	@RequestMapping(value = "/taxPayerGroups/search", method = RequestMethod.POST)
	public ResponseEntity<?> getTaxPayerGroupMstsByProperties(@RequestBody TaxPayerGroupMst taxPayerGroupMst) {
		List<TaxPayerGroupMst> taxPayerGroupMsts = null;
		HttpStatus httpStatus = null;
		try{
			taxPayerGroupMsts = taxPayerGroupMstService.getTaxPayerGroupMstsByProperties(taxPayerGroupMst);
			
			if(taxPayerGroupMsts == null || taxPayerGroupMsts.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.info("getTaxPayerGroupMstsByProperties Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<TaxPayerGroupMst>>(taxPayerGroupMsts, httpStatus);
	}
	
	/*CHECK searchValue WITH SERIALIZABLE DATATYPE*/
	@RequestMapping(value = "/taxPayerGroups/{searchKey}/{searchValue}", method = RequestMethod.GET)
	public ResponseEntity<?> getTaxPayerGroupMstsByProperty(@PathVariable String searchKey, @PathVariable String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(value = "/taxPayerGroups", method = RequestMethod.POST)
	public ResponseEntity<?> insertTaxPayerGroupMsts(@RequestBody List<TaxPayerGroupMst> taxPayerGroupMsts){
		HttpStatus httpStatus = null;
		try{		
			taxPayerGroupMstService.insertTaxPayerGroupMsts(taxPayerGroupMsts);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.info("getTaxPayerGroupMstsByProperty Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<TaxPayerGroupMst>>(taxPayerGroupMsts, httpStatus);
	}
	
	@RequestMapping(value = "/taxPayerGroup",method = RequestMethod.POST)
	public ResponseEntity<?> insertTaxPayerGroupMst(@RequestBody TaxPayerGroupMst taxPayerGroupMst){
		HttpStatus httpStatus = null;
		try{		
			taxPayerGroupMstService.insertTaxPayerGroupMst(taxPayerGroupMst);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.info("insertTaxPayerGroupMst Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<TaxPayerGroupMst>(taxPayerGroupMst, httpStatus);
	}
	
	@RequestMapping(value = "/taxPayerGroups", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTaxPayerGroupMsts(@RequestBody List<TaxPayerGroupMst> taxPayerGroupMsts) {
		HttpStatus httpStatus = null;
		try{
			taxPayerGroupMstService.updateTaxPayerGroupMsts(taxPayerGroupMsts);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.error("updateTaxPayerGroupMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<TaxPayerGroupMst>>(taxPayerGroupMsts, httpStatus);
	}
	
	@RequestMapping(value = "/taxPayerGroup", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTaxPayerGroupMst(@RequestBody TaxPayerGroupMst taxPayerGroupMst) {
		HttpStatus httpStatus = null;
		try{
			taxPayerGroupMstService.updateTaxPayerGroupMst(taxPayerGroupMst);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.info("updateTaxPayerGroupMst Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<TaxPayerGroupMst>(taxPayerGroupMst, httpStatus);
	}
	
	@RequestMapping(value = "/taxPayerGroup", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTaxPayerGroupMstById(@PathVariable int id) {
		HttpStatus httpStatus = null;
		try{
			taxPayerGroupMstService.deleteTaxPayerGroupMstById(id);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.error("deleteTaxPayerGroupMstById Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<HttpStatus>(httpStatus);
	}
}

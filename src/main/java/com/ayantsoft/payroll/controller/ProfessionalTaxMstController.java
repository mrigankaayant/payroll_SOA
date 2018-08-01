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

import com.ayantsoft.payroll.exception.DataUpdationException;
import com.ayantsoft.payroll.exception.PayrollException;
import com.ayantsoft.payroll.hibernate.pojo.ProfessionalTaxMst;
import com.ayantsoft.payroll.service.ProfessionalTaxMstService;

@RestController
public class ProfessionalTaxMstController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5846735146229501317L;
	
	private Logger log = Logger.getLogger(ProfessionalTaxMstController.class);
	
	@Autowired
	private ProfessionalTaxMstService professionalTaxMstService;
	
	@RequestMapping(value = "/professionalTaxs", method = RequestMethod.GET)
	public ResponseEntity<?> getProfessionalTaxMsts(){
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		List<ProfessionalTaxMst> professionalTaxMsts = null;
		HttpStatus httpStatus = null;
		try{
			professionalTaxMsts = professionalTaxMstService.getProfessionalTaxMsts();
			
			if(professionalTaxMsts == null || professionalTaxMsts.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.info("getProfessionalTaxMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<ProfessionalTaxMst>>(professionalTaxMsts, httpStatus);
	}
	
	@RequestMapping(value = "/professionalTax/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getProfessionalTaxMstById(@PathVariable int id){
		ProfessionalTaxMst professionalTaxMst = null;
		HttpStatus httpStatus = null;
		try{
			professionalTaxMst = professionalTaxMstService.getProfessionalTaxMstById(id);
			
			if(professionalTaxMst != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}
		}catch(PayrollException pe){
			log.info("getProfessionalTaxMstById Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<ProfessionalTaxMst>(professionalTaxMst, httpStatus);
	}
	
	@RequestMapping(value = "/professionalTaxs/search", method = RequestMethod.POST)
	public ResponseEntity<?> getProfessionalTaxMstsByProperties(@RequestBody ProfessionalTaxMst professionalTaxMst){
		List<ProfessionalTaxMst> professionalTaxMsts = null;
		HttpStatus httpStatus = null;
		try{
			professionalTaxMsts = professionalTaxMstService.getProfessionalTaxMstsByProperties(professionalTaxMst);
			
			if(professionalTaxMst != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}
		}catch(PayrollException pe){
			log.info("getProfessionalTaxMstsByProperties Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<ProfessionalTaxMst>>(professionalTaxMsts, httpStatus);
	}
	
	/*CHECK searchValue WITH SERIALIZABLE DATATYPE*/
	@RequestMapping(value = "/professionalTaxs/{searchKey}/{searchValue}", method = RequestMethod.GET)
	public ResponseEntity<?> getProfessionalTaxMstsByProperty(@PathVariable String searchKey, @PathVariable String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(value = "/professionalTaxs", method = RequestMethod.POST)
	public ResponseEntity<?> insertProfessionalTaxMsts(@RequestBody List<ProfessionalTaxMst> professionalTaxMsts) {
		HttpStatus httpStatus = null;
		try{		
			professionalTaxMstService.insertProfessionalTaxMsts(professionalTaxMsts);
			httpStatus = HttpStatus.CREATED;
		}catch(PayrollException pe){
			log.info("insertProfessionalTaxMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<ProfessionalTaxMst>>(professionalTaxMsts, httpStatus);
	}
	
	@RequestMapping(value = "/professionalTax",method = RequestMethod.POST)
	public ResponseEntity<?> insertProfessionalTaxMst(@RequestBody ProfessionalTaxMst professionalTaxMst){
		HttpStatus httpStatus = null;
		try{		
			professionalTaxMstService.insertProfessionalTaxMst(professionalTaxMst);
			httpStatus = HttpStatus.CREATED;
		}catch(PayrollException pe){
			log.info("insertProfessionalTaxMst Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<ProfessionalTaxMst>(professionalTaxMst, httpStatus);
	}
	
	@RequestMapping(value = "/professionalTaxs", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProfessionalTaxMsts(@RequestBody List<ProfessionalTaxMst> professionalTaxMsts) {
		HttpStatus httpStatus = null;
		try{
			professionalTaxMstService.updateProfessionalTaxMsts(professionalTaxMsts);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.error("updateProfessionalTaxMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<ProfessionalTaxMst>>(professionalTaxMsts, httpStatus);
	}
	
	@RequestMapping(value = "/professionalTax", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProfessionalTaxMst(@RequestBody ProfessionalTaxMst professionalTaxMsts) {
		HttpStatus httpStatus = null;
		try{
			professionalTaxMstService.updateProfessionalTaxMst(professionalTaxMsts);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.info("updateProfessionalTaxMst Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<ProfessionalTaxMst>(professionalTaxMsts, httpStatus);
	}
	
	@RequestMapping(value = "/professionalTax", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProfessionalTaxMstById(@PathVariable int id) {
		HttpStatus httpStatus = null;
		try{
			professionalTaxMstService.deleteProfessionalTaxMstById(id);
			httpStatus = HttpStatus.OK;
		}catch(DataUpdationException due){
			log.error("deleteProfessionalTaxMstById Error : ", due);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<HttpStatus>(httpStatus);
	}
}
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

import com.ayantsoft.payroll.exception.DataAccessException;
import com.ayantsoft.payroll.exception.DataInsertionException;
import com.ayantsoft.payroll.exception.DataUpdationException;
import com.ayantsoft.payroll.hibernate.pojo.StateOfCountryMst;
import com.ayantsoft.payroll.service.StateOfCountryMstService;

@RestController
public class StateOfCountryMstController implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3514363414006023366L;
	
	private Logger log = Logger.getLogger(StateOfCountryMstController.class);
	
	@Autowired
	private StateOfCountryMstService stateOfCountryMstService;
	
	@RequestMapping(value = "/stateOfCountries", method = RequestMethod.GET)
	public ResponseEntity<?> getStateOfCountryMsts(){
		List<StateOfCountryMst> stateOfCountryMsts = null;
		HttpStatus httpStatus = null;
		try{
			stateOfCountryMsts = stateOfCountryMstService.getStateOfCountryMsts();
			
			if(stateOfCountryMsts == null || stateOfCountryMsts.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(DataAccessException dae){
			log.info("getStateOfCountryMsts Error : ", dae);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<StateOfCountryMst>>(stateOfCountryMsts, httpStatus);
	}
	
	@RequestMapping(value = "/stateOfCountryMst/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getStateOfCountryMstById(@PathVariable int id){
		StateOfCountryMst stateOfCountryMst = null;
		HttpStatus httpStatus = null;
		try{
			stateOfCountryMst = stateOfCountryMstService.getStateOfCountryMstById(id);
			
			if(stateOfCountryMst != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}
		}catch(DataAccessException dae){
			log.info("getStateOfCountryMstById Error : ", dae);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<StateOfCountryMst>(stateOfCountryMst, httpStatus);
	}
	
	@RequestMapping(value = "/stateOfCountryMsts/search", method = RequestMethod.POST)
	public ResponseEntity<?> getStateOfCountryMstsByProperties(@RequestBody StateOfCountryMst stateOfCountryMst){
		List<StateOfCountryMst> stateOfCountryMsts = null;
		HttpStatus httpStatus = null;
		try{
			stateOfCountryMsts = stateOfCountryMstService.getStateOfCountryMstsByProperties(stateOfCountryMst);
			
			if(stateOfCountryMsts != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}
		}catch(DataAccessException dae){
			log.info("getProfessionalTaxMstsByProperties Error : ", dae);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<StateOfCountryMst>>(stateOfCountryMsts, httpStatus);
	}
	
	/*CHECK searchValue WITH SERIALIZABLE DATATYPE*/
	@RequestMapping(value = "/stateOfCountryMsts/{searchKey}/{searchValue}", method = RequestMethod.GET)
	public ResponseEntity<?> getStateOfCountryMstsByProperty(@PathVariable String searchKey, @PathVariable String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(value = "/stateOfCountryMsts", method = RequestMethod.POST)
	public ResponseEntity<?> insertStateOfCountryMsts(@RequestBody List<StateOfCountryMst> stateOfCountryMsts) {
		HttpStatus httpStatus = null;
		try{
			stateOfCountryMstService.insertStateOfCountryMsts(stateOfCountryMsts);
			httpStatus = HttpStatus.OK;
		}catch (DataInsertionException die) {
			log.error("insertStateOfCountryMsts Error : " + die);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<StateOfCountryMst>>(stateOfCountryMsts, httpStatus);
	}
	
	@RequestMapping(value = "/stateOfCountryMst",method = RequestMethod.POST)
	public ResponseEntity<?> insertStateOfCountryMst(@RequestBody StateOfCountryMst stateOfCountryMst){
		HttpStatus httpStatus = null;
		try{
			stateOfCountryMstService.insertStateOfCountryMst(stateOfCountryMst);
			httpStatus = HttpStatus.OK;
		}catch (DataInsertionException die) {
			log.error("insertStateOfCountryMst Error : " + die);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<StateOfCountryMst>(stateOfCountryMst, httpStatus);
	}
	
	@RequestMapping(value = "/stateOfCountryMsts", method = RequestMethod.PUT)
	public ResponseEntity<?> updateStateOfCountryMsts(@RequestBody List<StateOfCountryMst> stateOfCountryMsts) {
		HttpStatus httpStatus = null;
		try{
			stateOfCountryMstService.updateStateOfCountryMsts(stateOfCountryMsts);
			httpStatus = HttpStatus.OK;
		}catch(DataUpdationException due){
			log.error("updateStateOfCountryMsts Error : ", due);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<StateOfCountryMst>>(stateOfCountryMsts, httpStatus);
	}
	
	@RequestMapping(value = "/stateOfCountryMst", method = RequestMethod.PUT)
	public ResponseEntity<?> updateStateOfCountryMst(@RequestBody StateOfCountryMst stateOfCountryMst) {
		HttpStatus httpStatus = null;
		try{
			stateOfCountryMstService.updateStateOfCountryMst(stateOfCountryMst);
			httpStatus = HttpStatus.OK;
		}catch(DataUpdationException due){
			log.info("updateStateOfCountryMst Error : ", due);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<StateOfCountryMst>(stateOfCountryMst, httpStatus);
	}
	
	@RequestMapping(value = "/stateOfCountryMst", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteStateOfCountryMstById(@PathVariable int id) {
		HttpStatus httpStatus = null;
		try{
			stateOfCountryMstService.deleteStateOfCountryMstById(id);
			httpStatus = HttpStatus.OK;
		}catch(DataUpdationException due){
			log.error("deleteStateOfCountryMstById Error : ", due);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<HttpStatus>(httpStatus);
	}
}

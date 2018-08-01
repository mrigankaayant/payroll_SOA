package com.ayantsoft.payroll.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayantsoft.payroll.dto.PayrollGroupMstDto;
import com.ayantsoft.payroll.dto.search.LazyDataRequestModel;
import com.ayantsoft.payroll.dto.search.LazyDataResponseModel;
import com.ayantsoft.payroll.exception.PayrollException;
import com.ayantsoft.payroll.hibernate.pojo.PayrollGroupMst;
import com.ayantsoft.payroll.service.PayrollGroupMstService;

@RestController
public class PayrollGroupMstController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -610904340314032066L;

	private Logger log = Logger.getLogger(PayrollGroupMstController.class);

	@Autowired
	private PayrollGroupMstService payrollGroupMstService;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/payrollGroups", method = RequestMethod.GET)
	public ResponseEntity<?> getPayrollGroupMsts(){
		List<PayrollGroupMst> payrollGroupMsts = null;
		HttpStatus httpStatus = null;
		try{
			payrollGroupMsts = payrollGroupMstService.getPayrollGroupMsts();

			if(payrollGroupMsts == null || payrollGroupMsts.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.error("getPayrollGroupMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PayrollGroupMst>>(payrollGroupMsts, httpStatus);
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/payrollGroupsWithEmp", method = RequestMethod.GET)
	public ResponseEntity<?> getPayrollGroupsWithEmp(){
		List<PayrollGroupMstDto> payrollGroupMstDtos = null;
		HttpStatus httpStatus = null;
		try{
			payrollGroupMstDtos = payrollGroupMstService.getPayrollGroupWithEmp();
			
			if(payrollGroupMstDtos == null || payrollGroupMstDtos.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.error("getPayrollGroupMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PayrollGroupMstDto>>(payrollGroupMstDtos, httpStatus);
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/payrollGroup/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPayrollGroupMstById(@PathVariable int id){
		PayrollGroupMst payrollGroupMst = null;
		HttpStatus httpStatus = null;
		try{
			payrollGroupMst = payrollGroupMstService.getPayrollGroupMstById(id);

			if(payrollGroupMst != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.NO_CONTENT;
			}
		}catch(PayrollException pe){
			log.error("getPayrollGroupMstById Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<PayrollGroupMst>(payrollGroupMst, httpStatus);
	}

	@RequestMapping(value = "/payrollGroups/search", method = RequestMethod.POST)
	public ResponseEntity<?> getPayrollGroupMstsByProperties(@RequestBody PayrollGroupMst payrollGroupMst) {

		List<PayrollGroupMst> payrollGroupMsts = null;
		HttpStatus httpStatus = null;
		try{
			payrollGroupMsts = payrollGroupMstService.getPayrollGroupMstsByProperties(payrollGroupMst);

			if(payrollGroupMsts == null || payrollGroupMsts.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.error("getPayrollGroupMstsByProperties Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PayrollGroupMst>>(payrollGroupMsts, httpStatus);
	}

	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/payrollGroups/lazySearchData", method = RequestMethod.POST)
	public ResponseEntity<?> getPayrollGroupMstsLazydata(@RequestBody LazyDataRequestModel lazyDataRequestModel) {
		log.info("come to payroll group mstt ");
		
		LazyDataResponseModel<PayrollGroupMst> lazyDataResponseModel = null;
		HttpStatus httpStatus = null;
		
		try{
			lazyDataResponseModel = payrollGroupMstService.getPayrollGroupMstsLazydata(lazyDataRequestModel);
			httpStatus = HttpStatus.OK;

		}catch(PayrollException pe){

			log.info("catch exception is"+pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<LazyDataResponseModel>(lazyDataResponseModel, httpStatus);
	}
	
	

	/*CHECK searchValue WITH SERIALIZABLE DATATYPE*/
	@RequestMapping(value = "/payrollGroups/{searchKey}/{searchValue}", method = RequestMethod.GET)
	public ResponseEntity<?> getPayrollGroupMstsByProperty(@PathVariable String searchKey, @PathVariable String searchValue) {
		
		List<PayrollGroupMst> payrollGroupMsts = null;
		HttpStatus httpStatus = null;
		try{
			
			payrollGroupMsts = payrollGroupMstService.getPayrollGroupMstByProperty(searchKey, searchValue);
			if(payrollGroupMsts == null || payrollGroupMsts.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.error("getPayrollGroupMstsByProperty Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PayrollGroupMst>>(payrollGroupMsts, httpStatus);
	}
	

	@RequestMapping(value = "/payrollGroups", method = RequestMethod.POST)
	public ResponseEntity<?> insertPayrollGroupMsts(@RequestBody List<PayrollGroupMst> payrollGroupMsts) {
		HttpStatus httpStatus = null;
		try{
			payrollGroupMstService.insertPayrollGroupMsts(payrollGroupMsts);
			httpStatus = HttpStatus.CREATED;
		}catch (PayrollException pe) {
			log.error("insertPayrollGroupMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PayrollGroupMst>>(payrollGroupMsts, httpStatus);
	}
	

	@RequestMapping(value = "/payrollGroup",method = RequestMethod.POST)
	public ResponseEntity<?> insertPayrollGroupMst(@RequestBody PayrollGroupMst payrollGroupMst){
		HttpStatus httpStatus = null;
		try{
			payrollGroupMstService.insertPayrollGroupMst(payrollGroupMst);
			httpStatus = HttpStatus.CREATED;
		}catch (PayrollException pe) {
			log.error("insertPayrollGroupMst Error : " + pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<PayrollGroupMst>(payrollGroupMst, httpStatus);
	}

	
	@RequestMapping(value = "/payrollGroups", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePayrollGroupMsts(@RequestBody List<PayrollGroupMst> payrollGroupMsts) {
		HttpStatus httpStatus = null;
		try{
			payrollGroupMstService.updatePayrollGroupMsts(payrollGroupMsts);
			httpStatus = HttpStatus.OK;
		}catch (PayrollException pe) {
			log.error("updatePayrollGroupMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PayrollGroupMst>>(payrollGroupMsts, httpStatus);
	}

	@RequestMapping(value = "/payrollGroup", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePayrollGroupMst(@RequestBody PayrollGroupMst payrollGroupMst) {
		HttpStatus httpStatus = null;
		try{
			payrollGroupMstService.updatePayrollGroupMst(payrollGroupMst);
			httpStatus = HttpStatus.OK;
		}catch (PayrollException pe) {
			log.info("updatePayrollGroupMst Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<PayrollGroupMst>(payrollGroupMst, httpStatus);
	}

	@RequestMapping(value = "/payrollGroup", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePayrollGroupMstById(@PathVariable int id) {
		HttpStatus httpStatus = null;
		try{
			payrollGroupMstService.deletePayrollGroupMstById(id);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.error("deletePayrollGroupMstById Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<HttpStatus>(httpStatus);
	}

}

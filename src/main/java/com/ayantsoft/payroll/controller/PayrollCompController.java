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

import com.ayantsoft.payroll.dto.PayrollGroupUtil;
import com.ayantsoft.payroll.dto.search.LazyDataResponseModel;
import com.ayantsoft.payroll.exception.DataInsertionException;
import com.ayantsoft.payroll.exception.DataUpdationException;
import com.ayantsoft.payroll.exception.PayrollException;
import com.ayantsoft.payroll.hibernate.pojo.EmployeeMst;
import com.ayantsoft.payroll.hibernate.pojo.PayrollComp;
import com.ayantsoft.payroll.hibernate.pojo.PayrollCompId;
import com.ayantsoft.payroll.hibernate.pojo.PayrollCompMst;
import com.ayantsoft.payroll.hibernate.pojo.PayrollGroupMst;
import com.ayantsoft.payroll.service.PayrollCompService;


@RestController
public class PayrollCompController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4094470062105384548L;

	private Logger log= Logger.getLogger(PayrollCompController.class);

	@Autowired
	PayrollCompService payrollCompService;

	@RequestMapping(value="/payrollEarnings",method=RequestMethod.GET)
	public ResponseEntity<?> getPayrollEarning(){
		List<PayrollComp> payrollComps = null;
		HttpStatus httpStatus = null;
		try{
			payrollComps = payrollCompService.getPayrollComp();
			if(payrollComps == null || payrollComps.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}

		}catch(PayrollException pe){
			log.error("payrollEarning get data error : "+ pe);
		}
		return new ResponseEntity<List<PayrollComp>>(payrollComps, httpStatus);
	}

	/*@RequestMapping(value="/payrollEarningsLazyDataById",method=RequestMethod.POST)
	public ResponseEntity<?> getpayrollEarningsLazyDataById(@RequestBody LazyDataRequestModel lazyDataRequestModel){

		LazyDataResponseModel<PayrollComp> lazyDataResponseModel = null;
		HttpStatus httpStatus = null;
		try{
			lazyDataResponseModel = payrollCompService.getPayrollEarningsLazydataById(lazyDataRequestModel);
			httpStatus = HttpStatus.OK;

		}catch(PayrollException pe){
			log.error("payroll earningsLazyData fetch error : "+pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<LazyDataResponseModel>(lazyDataResponseModel,httpStatus);
	}*/

	@RequestMapping(value="/payrollEarning/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getPayrollEarningById(@PathVariable int id){
		PayrollComp payrollComp = null;
		HttpStatus httpStatus = null;
		try{
			payrollComp = payrollCompService.getPayrollCompById(id);
			if(payrollComp == null){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}

		}catch(PayrollException pe){
			log.error("Payroll earning get By id : "+pe);
			httpStatus= HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<PayrollComp>(payrollComp,httpStatus);
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/payrollgroup/{id}/payrollEarningComps",method=RequestMethod.GET)
	public ResponseEntity<?> getPayrollEarningByPayrollGroupId(@PathVariable int id){
		
		List<PayrollComp> payrollComps = null;
		HttpStatus httpStatus = null;
		try{
			payrollComps =  payrollCompService.getPayrollEarningCompByPayrollGroupId(id);
			if(payrollComps == null){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}

		}catch(PayrollException pe){
			log.error("Payroll earning get By id : "+pe);
			httpStatus= HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<List<PayrollComp>>(payrollComps, httpStatus);
	}
	
	
	@RequestMapping(value = "/payrollComps", method = RequestMethod.POST)
	public ResponseEntity<?> insertPayrollComponents(@RequestBody List<PayrollComp> payrollComps) {

		HttpStatus httpStatus = null;
		try{

			payrollCompService.insertPayrollComps(payrollComps);
			httpStatus = HttpStatus.CREATED;

		}catch (DataInsertionException die) {
			die.printStackTrace();
			log.error("insertEmployeeMsts Error : " + die);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PayrollComp>>(payrollComps, httpStatus);
	    
	}
	
	
	@RequestMapping(value = "/payrollComp", method = RequestMethod.POST)
	public ResponseEntity<?> insertPayrollComponent(@RequestBody PayrollComp payrollComps) {

		HttpStatus httpStatus = null;
		try{
			payrollCompService.insertPayrollComp(payrollComps);
			httpStatus = HttpStatus.CREATED;

		}catch (DataInsertionException die) {
			die.printStackTrace();
			log.error("insertPayrollComponent Error : " + die);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<PayrollComp>(payrollComps, httpStatus);
	}
	
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/payrollgroup/{id}/payrollDeductions",method=RequestMethod.GET)
	public ResponseEntity<?> getPayrollpayrollDeductionsByPayrollGroupId(@PathVariable int id){
		
		List<PayrollComp> payrollComps = null;
		HttpStatus httpStatus = null;
		try{
			payrollComps =  payrollCompService.getPayrollpayrollDeductionsByPayrollGroupId(id);
			if(payrollComps == null){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}

		}catch(PayrollException pe){
			log.error("Payroll earning get By id : "+pe);
			httpStatus= HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<List<PayrollComp>>(payrollComps, httpStatus);
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/payrollComp/{searchKey}/{searchValue}", method = RequestMethod.GET)
	public ResponseEntity<?> getPayrollCompsByProperty(@PathVariable String searchKey, @PathVariable String searchValue) {
		
		List<PayrollComp> payrollComps = null;
		HttpStatus httpStatus = null;
		try{
			
			payrollComps = payrollCompService.getPayrollCompByProperty(searchKey, searchValue);
			if(payrollComps == null || payrollComps.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.error("getPayrollGroupMstsByProperty Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PayrollComp>>(payrollComps, httpStatus);
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/payrollEarning",method=RequestMethod.POST)
	public ResponseEntity<?> insertPayrollGroupEarning(@RequestBody PayrollComp payrollComp){
		HttpStatus httpStatus = null;
		try{
			PayrollGroupMst payrollGroupMst = new PayrollGroupMst();
			payrollGroupMst.setId(payrollComp.getPayrollGroupMst().getId());
			payrollComp.setPayrollGroupMst(payrollGroupMst);
			payrollCompService.insertPayrollComp(payrollComp);
			httpStatus= HttpStatus.CREATED;

		}catch(PayrollException pe){
			log.error("insertPayrollGroupEarning error"+pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<PayrollComp>(payrollComp,httpStatus);
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/payrollEarnings",method=RequestMethod.POST)
	public ResponseEntity<?> insertPayrollGroupEarnings(@RequestBody List<PayrollComp> payrollComps){
		
		HttpStatus httpStatus = null;
		try{
			payrollCompService.insertPayrollComps(payrollComps);
			httpStatus = HttpStatus.CREATED;

		}catch(PayrollException pe){
			log.error("insertPayrollGroupEarnings error : "+pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PayrollComp>>(payrollComps, httpStatus);
	}

	@RequestMapping(value="/payrollEarning",method=RequestMethod.PUT)
	public ResponseEntity<?> updatePayrollEarning(@RequestBody PayrollComp payrollComp){

		HttpStatus httpStatus = null;
		try{
			payrollCompService.updatePayrollComp(payrollComp);
			httpStatus = HttpStatus.OK;

		}catch(PayrollException pe){
			log.error("updatePayrollEarning edit error : "+pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<PayrollComp>(payrollComp,httpStatus);
	}
	
	@RequestMapping(value = "/payrollEarning/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePayrollEarningById(@PathVariable int id) {
		HttpStatus httpstatus = null;
		try{
			payrollCompService.deletePayrollCompById(id);
			httpstatus = HttpStatus.OK;

		}catch(DataUpdationException de){
			log.error("PayrollEarning delete error :"+de);
		}
		return new  ResponseEntity<PayrollComp>(httpstatus);
	}
	
	
	@RequestMapping(value = "/payrollCompsListByPayrollGroupId/{id}/{type}", method = RequestMethod.GET)
	public ResponseEntity<?> getPayrollCompsListByPayrollGroupId(@PathVariable int id,@PathVariable String type){
	
		List<PayrollComp> payrollComps = null;
		HttpStatus httpStatus = null;
		try{
			payrollComps = payrollCompService.getPayrollCompsByPayrollGroupId(id, type);
			if(payrollComps == null || payrollComps.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}

		}catch(PayrollException pe){
			log.error("getPayrollCompsListByPayrollGroupId get data error : "+ pe);
		}
		return new ResponseEntity<List<PayrollComp>>(payrollComps, httpStatus);
	}


}

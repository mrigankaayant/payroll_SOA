package com.ayantsoft.payroll.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import com.ayantsoft.payroll.dto.EmployeeMonthlyPayrollDto;
import com.ayantsoft.payroll.dto.EmployeeMstDto;
import com.ayantsoft.payroll.dto.search.LazyDataRequestModel;
import com.ayantsoft.payroll.dto.search.LazyDataResponseModel;
import com.ayantsoft.payroll.exception.PayrollException;
import com.ayantsoft.payroll.hibernate.pojo.EmpMonthlyPayroll;
import com.ayantsoft.payroll.hibernate.pojo.EmployeeMst;
import com.ayantsoft.payroll.hibernate.pojo.PayrollGroupMst;
import com.ayantsoft.payroll.service.EmpMonthlyPayrollService;

@RestController
public class EmpMonthlyPayrollController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3327698015532306129L;
	
	private Logger log = Logger.getLogger(EmpMonthlyPayrollController.class);
	@Autowired
	private EmpMonthlyPayrollService empMonthlyReportService;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/empMonthlyPayroll", method = RequestMethod.GET)
	public ResponseEntity<?> getEmpMonthlyPayroll(){
		
		List<EmployeeMstDto> employeeMstDto = null;
		HttpStatus httpStatus = null;
		try{
			employeeMstDto = empMonthlyReportService.getEmployeeMonthlyPayroll();
			
			if(employeeMstDto != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.NO_CONTENT;
			}
		}catch(PayrollException pe){
			log.error("get Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<EmployeeMstDto>>(employeeMstDto, httpStatus);
	}
	
	@RequestMapping(value = "/empMonthlyPayroll/search", method = RequestMethod.POST)
	public ResponseEntity<?> getMonthlyPayrollByProperties(@RequestBody EmpMonthlyPayroll empMonthlyPayroll) {

		List<EmployeeMstDto> employeeMstDtos = null;
		HttpStatus httpStatus = null;
		try{
			employeeMstDtos = empMonthlyReportService.getMonthlyPayrollByProperties(empMonthlyPayroll);

			if(employeeMstDtos == null || employeeMstDtos.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.error("getPayrollGroupMstsByProperties Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<EmployeeMstDto>>(employeeMstDtos, httpStatus);
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/getMonthlyPayrollLazyData", method = RequestMethod.POST)
	public ResponseEntity<?> employeeMonthlyPayrollLazyData(@RequestBody LazyDataRequestModel lazyDataRequestModel) {

		LazyDataResponseModel<EmployeeMst> lazyDataResponseModel = null;
		HttpStatus httpStatus = null;

		try{
			lazyDataResponseModel = empMonthlyReportService.getMonthlyPayrollLazyData(lazyDataRequestModel);
			httpStatus = HttpStatus.OK;

		}catch(PayrollException pe){

			log.info("catch exception is"+pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<LazyDataResponseModel>(lazyDataResponseModel, httpStatus);
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/empMonthlyPayroll", method = RequestMethod.POST)
	public ResponseEntity<?> insertEmployeePayrollReports() {
		
		HttpStatus httpStatus = null;
		try{
			empMonthlyReportService.processEmployeePayrollReports();
			httpStatus = HttpStatus.OK;

		}catch(PayrollException pe){
			log.info("catch exception is"+pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity(httpStatus);
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/employee/{id}/empMonthlyPayroll",method=RequestMethod.GET)
	public ResponseEntity<?> getEmployeesByPayrollDetails(@PathVariable int id){

		List<EmployeeMonthlyPayrollDto> employeeMonthlyPayrollDto = null;
		HttpStatus httpStatus = null;
		try{
			employeeMonthlyPayrollDto =  empMonthlyReportService.getEmployeesByPayrollDetails(id);
			if(employeeMonthlyPayrollDto == null){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}

		}catch(PayrollException pe){
			log.error(" Employee  get By payroll group id : "+pe);
			httpStatus= HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<List<EmployeeMonthlyPayrollDto>>(employeeMonthlyPayrollDto, httpStatus);
	}

}

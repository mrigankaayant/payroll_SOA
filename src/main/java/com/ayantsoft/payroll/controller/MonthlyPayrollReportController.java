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

import com.ayantsoft.payroll.exception.PayrollException;
import com.ayantsoft.payroll.hibernate.pojo.MonthlyPayrollReport;
import com.ayantsoft.payroll.hibernate.pojo.PayrollGroupMst;
import com.ayantsoft.payroll.service.MonthlyPayrollReportService;


@RestController
public class MonthlyPayrollReportController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7848806019713174687L;
	
	private Logger log = Logger.getLogger(MonthlyPayrollReportController.class);
	
	@Autowired
	private MonthlyPayrollReportService monthlyPayrollReportService;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/monthlyPayrollReport/{date}", method = RequestMethod.GET)
	public ResponseEntity<?> getPayrollGroupMstById(@PathVariable String  date){
		List<MonthlyPayrollReport> monthlyPayrollReport = null;
		HttpStatus httpStatus = null;
		try{
			monthlyPayrollReport = monthlyPayrollReportService.getMonthlyReportByDate(date);

			if(monthlyPayrollReport != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.NO_CONTENT;
			}
		}catch(PayrollException pe){
			log.error("getPayrollGroupMstById Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<MonthlyPayrollReport>>(monthlyPayrollReport, httpStatus);
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/monthlyPayrollReport", method = RequestMethod.POST)
	public ResponseEntity<?> insertMonthlyPayrollReports(@RequestBody List<PayrollGroupMst> payrollGroupMsts) {
		HttpStatus httpStatus = null;
		try{
			for(PayrollGroupMst obj:  payrollGroupMsts ){
				
				monthlyPayrollReportService.insertMonthlyPayrollReports(obj.getId());
			}

			httpStatus = HttpStatus.OK;

		}catch(PayrollException pe){
			log.info("catch exception is"+pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PayrollGroupMst>>(payrollGroupMsts, httpStatus);
	}
}

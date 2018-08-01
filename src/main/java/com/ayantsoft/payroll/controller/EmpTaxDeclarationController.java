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

import com.ayantsoft.payroll.exception.DataInsertionException;
import com.ayantsoft.payroll.exception.PayrollException;
import com.ayantsoft.payroll.hibernate.pojo.EmpTaxDeclaration;
import com.ayantsoft.payroll.service.EmpTaxDeclarationService;

@RestController
public class EmpTaxDeclarationController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7484327344207107131L;
	
	private Logger log = Logger.getLogger(EmpTaxDeclarationController.class);
	
	@Autowired
	private EmpTaxDeclarationService empTaxDeclarationService;
	
	@RequestMapping(value = "/empTaxDeclaration",method = RequestMethod.POST)
	public ResponseEntity<?> insertEmpHraDeclaration(@RequestBody EmpTaxDeclaration empTaxDeclaration){
		HttpStatus httpstatus = null;
		try{
			empTaxDeclarationService.insertEmpTaxDeclaration(empTaxDeclaration);
			httpstatus = HttpStatus.CREATED;
		}catch(DataInsertionException de){
			log.error("EmpTaxDeclaration insert exception :"+de);
			httpstatus= HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<EmpTaxDeclaration>(empTaxDeclaration,httpstatus);
	}
	
	
	/*@RequestMapping(value = "/empTaxDeclaration/{empMstId}", method = RequestMethod.GET)
	public ResponseEntity<?> getEmpTaxDeclarationByEmployeeMstId(@PathVariable int empMstId){
		List<EmpTaxDeclaration> empTaxDeclarations = null;
		HttpStatus httpStatus = null;
		try{
			empTaxDeclarations = empTaxDeclarationService.getEmpTaxDeclarationByEmployeeMstId(empMstId);
			
			if(empTaxDeclarations == null || empTaxDeclarations.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.info("getEmpTaxDeclarationByEmployeeMstId Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<EmpTaxDeclaration>>(empTaxDeclarations, httpStatus);
	}*/
}

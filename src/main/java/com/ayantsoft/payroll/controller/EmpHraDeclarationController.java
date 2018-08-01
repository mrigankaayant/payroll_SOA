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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ayantsoft.payroll.dto.EmployeeHrDeclarationDto;
import com.ayantsoft.payroll.exception.DataAccessException;
import com.ayantsoft.payroll.exception.DataInsertionException;
import com.ayantsoft.payroll.exception.DataUpdationException;
import com.ayantsoft.payroll.exception.PayrollException;
import com.ayantsoft.payroll.hibernate.pojo.EmpHraDeclaration;
import com.ayantsoft.payroll.service.EmpHraDeclarationService;

@RestController
public class EmpHraDeclarationController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2142254844006637065L;
	
	private Logger log = Logger.getLogger(EmpHraDeclarationController.class);
	
	@Autowired
	private EmpHraDeclarationService empHraDeclarationService;
	
	
	@RequestMapping(value = "/empHraDeclaration",method = RequestMethod.POST)
	public ResponseEntity<?> insertEmpHraDeclaration(@RequestBody EmpHraDeclaration empHraDeclaration){
		HttpStatus httpstatus = null;
		try{
			empHraDeclarationService.insertEmpHraDeclaration(empHraDeclaration);
			httpstatus = HttpStatus.CREATED;
		}catch(DataInsertionException de){
			log.error("empHraDeclaration insert exception :"+de);
			httpstatus= HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<EmpHraDeclaration>(empHraDeclaration,httpstatus);
	}
	
	
	@RequestMapping(value = "/empHraDeclarations", method = RequestMethod.POST)
	public ResponseEntity<?> insertEmpHraDeclaration(@RequestBody List<EmpHraDeclaration> empHraDeclarations) {
		HttpStatus httpStatus = null;
		try{
			empHraDeclarationService.insertEmpHraDeclarations(empHraDeclarations);
			httpStatus = HttpStatus.CREATED;

		}catch(DataInsertionException de){
			log.error("empHraDeclarations data insert exception :"+de);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<List<EmpHraDeclaration>>(empHraDeclarations,httpStatus);
	}
	
	
	@RequestMapping(value = "/empHraDeclaration/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getEmpHraDeclarationByEmployeeMstId(@PathVariable int id){

		EmployeeHrDeclarationDto employeeHrDeclarationDto = null;
		HttpStatus httpStatus = null;
		try{
			employeeHrDeclarationDto = empHraDeclarationService.getEmpHraDeclarationById(id);
			if(employeeHrDeclarationDto != null){
				httpStatus = HttpStatus.OK;
			}
			else
			{
				httpStatus = HttpStatus.NO_CONTENT;
			}
		}catch(DataAccessException ae){
			log.error("getEmpHraDeclarationByEmployeeMstId Error : ", ae);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<EmployeeHrDeclarationDto>(employeeHrDeclarationDto,httpStatus);
	}
	
	
	
	@RequestMapping(value = "/empHraDeclarations", method = RequestMethod.GET)
	public ResponseEntity<?> getempHraDeclarations(){
		List<EmployeeHrDeclarationDto> employeeHrDeclarationDtos = null;
		HttpStatus httpStatus = null;
		try{
			employeeHrDeclarationDtos = empHraDeclarationService.getEmpHraDeclarations();
			if(employeeHrDeclarationDtos!= null || employeeHrDeclarationDtos.isEmpty()){
				httpStatus = httpStatus.OK;
			}
			else {
				httpStatus = httpStatus.NO_CONTENT;
			}
		}catch(DataAccessException de){
			log.error("data insertion exception :"+de);
			httpStatus = httpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<List<EmployeeHrDeclarationDto>>(employeeHrDeclarationDtos, httpStatus);
	}

	@RequestMapping(value = "/empHraDeclaration", method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmpHraDeclaration(@RequestBody EmpHraDeclaration empHraDeclaration) throws IllegalArgumentException, IllegalAccessException{
		HttpStatus httpstatus = null;
		try{
			empHraDeclarationService.updateEmpHraDeclaration(empHraDeclaration, empHraDeclaration.getId());
			httpstatus = HttpStatus.OK;
		}catch(DataUpdationException de){
			log.error("EmpHraDeclaration data insert exception :"+de);
			httpstatus = HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<EmpHraDeclaration>(empHraDeclaration,httpstatus);
	}

	@RequestMapping(value="/empHraDeclaration/upload", method = RequestMethod.POST)
	public ResponseEntity<?> empHraDeclarationUpload(@RequestParam("uploadfile") MultipartFile uploadfile){
		log.info("the file data is: "+uploadfile);
		return null;
		
	}
	
}

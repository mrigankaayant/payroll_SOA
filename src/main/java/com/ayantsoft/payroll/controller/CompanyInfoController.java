package com.ayantsoft.payroll.controller;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayantsoft.payroll.exception.PayrollException;
import com.ayantsoft.payroll.hibernate.pojo.CompanyInformation;
import com.ayantsoft.payroll.service.CompanyInfoService;

@RestController
public class CompanyInfoController implements Serializable {

	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -9208204300285160708L;

	private Logger log = Logger.getLogger(CompanyInfoController.class);
	
	@Autowired
	private CompanyInfoService companyInfoService;
	
	@RequestMapping(value = "/companyInfo", method = RequestMethod.POST)
	public ResponseEntity<?> insertCompanyInfoMst(@RequestBody CompanyInformation companyInformation) {
		
		System.out.println("name: "+companyInformation.getName()+" address: "+companyInformation.getAddress()+" email: "
				+ ""+companyInformation.getEmail()+"   phone no: "+companyInformation.getPhoneNo());
		
		HttpStatus httpStatus = null;
		try{
			companyInfoService.insertCompanyInfo(companyInformation);
			httpStatus = HttpStatus.CREATED;

		}catch(NullPointerException ne){
			log.info("null pointer exception here: "+ne);

		}
		catch (PayrollException pe) {
			log.error("insert company info Error : " + pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<CompanyInformation>(companyInformation, httpStatus);
	}

}

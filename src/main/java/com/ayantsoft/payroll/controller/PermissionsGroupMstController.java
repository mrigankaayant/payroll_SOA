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
import com.ayantsoft.payroll.hibernate.pojo.PermissionGroupMst;
import com.ayantsoft.payroll.service.PermissionsGroupMstService;

@RestController
public class PermissionsGroupMstController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -801189332561982289L;
	
	private Logger log = Logger.getLogger(PermissionsGroupMstController.class);
	
	@Autowired
	private PermissionsGroupMstService permissionsGroupMstService;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/permissionGroups", method = RequestMethod.GET)
	public ResponseEntity<?> getPermissionsMsts(){
		List<PermissionGroupMst> permissionGroupMst = null;
		HttpStatus httpStatus = null;
		try{
			permissionGroupMst = permissionsGroupMstService.getPermissionsGroupMsts();
			
			if(permissionGroupMst == null || permissionGroupMst.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.error("getPermissionsGroupMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PermissionGroupMst>>(permissionGroupMst, httpStatus);
	}

	
}

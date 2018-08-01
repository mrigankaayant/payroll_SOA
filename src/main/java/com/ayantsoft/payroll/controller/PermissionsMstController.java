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

import com.ayantsoft.payroll.exception.PayrollException;
import com.ayantsoft.payroll.hibernate.pojo.PermissionsMst;
import com.ayantsoft.payroll.service.PermissionsMstService;

@RestController
public class PermissionsMstController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -801189332561982289L;
	
	private Logger log = Logger.getLogger(PermissionsMstController.class);
	
	@Autowired
	private PermissionsMstService permissionsMstService;
	
	@RequestMapping(value = "/permissions", method = RequestMethod.GET)
	public ResponseEntity<?> getPermissionsMsts(){
		List<PermissionsMst> permissionsMsts = null;
		HttpStatus httpStatus = null;
		try{
			permissionsMsts = permissionsMstService.getPermissionsMsts();
			
			if(permissionsMsts == null || permissionsMsts.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.error("getPermissionsMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PermissionsMst>>(permissionsMsts, httpStatus);
	}
	
	@RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPermissionsMstById(@PathVariable int id){
		PermissionsMst permissionsMst = null;
		HttpStatus httpStatus = null;
		try{
			permissionsMst = permissionsMstService.getPermissionsMstById(id);
			
			if(permissionsMst != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}
		}catch(PayrollException pe){
			log.error("getPermissionsMstById Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<PermissionsMst>(permissionsMst, httpStatus);
	}
	
	@RequestMapping(value = "/permissions/search", method = RequestMethod.POST)
	public ResponseEntity<?> getPermissionsMstsByProperties(@RequestBody PermissionsMst permissionsMst) {
		List<PermissionsMst> permissionsMsts = null;
		HttpStatus httpStatus = null;
		try{
			permissionsMsts = permissionsMstService.getPermissionsMstsByProperties(permissionsMst);
			
			if(permissionsMsts == null || permissionsMsts.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.error("getPermissionsMstsByProperties Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PermissionsMst>>(permissionsMsts, httpStatus);
	}
	
	/*CHECK searchValue WITH SERIALIZABLE DATATYPE*/
	@RequestMapping(value = "/permissions/{searchKey}/{searchValue}", method = RequestMethod.GET)
	public ResponseEntity<?> getPermissionMstsByProperty(@PathVariable String searchKey, @PathVariable String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(value = "/permissions", method = RequestMethod.POST)
	public ResponseEntity<?> insertPermissionsMsts(@RequestBody List<PermissionsMst> permissionsMsts) {
		HttpStatus httpStatus = null;
		try{
			permissionsMstService.insertPermissionsMsts(permissionsMsts);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.error("insertPermissionsMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PermissionsMst>>(permissionsMsts, httpStatus);
	}
	
	@RequestMapping(value = "/permission",method = RequestMethod.POST)
	public ResponseEntity<?> insertPermissionsMst(@RequestBody PermissionsMst permissionsMst){
		HttpStatus httpStatus = null;
		try{
			permissionsMstService.insertPermissionsMst(permissionsMst);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.error("insertPermissionsMst Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<PermissionsMst>(permissionsMst, httpStatus);
	}
	
	@RequestMapping(value = "/permissions", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePermissionsMsts(@RequestBody List<PermissionsMst> permissionsMsts) {
		HttpStatus httpStatus = null;
		try{
			permissionsMstService.updatePermissionsMsts(permissionsMsts);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.error("updatePermissionsMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PermissionsMst>>(permissionsMsts, httpStatus);
	}
	
	@RequestMapping(value = "/permission", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePermissionsMst(@RequestBody PermissionsMst permissionsMst) {
		HttpStatus httpStatus = null;
		try{
			permissionsMstService.updatePermissionsMst(permissionsMst);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.info("updatePermissionMst Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<PermissionsMst>(permissionsMst, httpStatus);
	}
	
	@RequestMapping(value = "/permission", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePermissionsMstById(@PathVariable int id) {
		HttpStatus httpStatus = null;
		try{
			permissionsMstService.deletePermissionsMstById(id);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.info("deletePermissionsMstById Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<HttpStatus>(httpStatus);
	}

}

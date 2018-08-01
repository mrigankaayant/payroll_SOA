package com.ayantsoft.payroll.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayantsoft.payroll.hibernate.pojo.RoleMst;

@RestController
public class RoleMstController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1298014912050450581L;
	
	private Logger log = Logger.getLogger(RoleMstController.class);
	
	@RequestMapping(value = "/roleMsts", method = RequestMethod.GET)
	public ResponseEntity<?> getRoleMsts(){
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(value = "/roleMst/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getRoleMstById(@PathVariable int id){
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(value = "/roleMsts/search", method = RequestMethod.POST)
	public ResponseEntity<?> getRoleMstsByProperties(@RequestBody RoleMst roleMst){
		// TODO Auto-generated method stub
		return null;
	}
	
	/*CHECK searchValue WITH SERIALIZABLE DATATYPE*/
	@RequestMapping(value = "/roleMsts/{searchKey}/{searchValue}", method = RequestMethod.GET)
	public ResponseEntity<?> getRoleMstsByProperty(@PathVariable String searchKey, @PathVariable String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(value = "/roleMsts", method = RequestMethod.POST)
	public ResponseEntity<?> insertRoleMsts(@RequestBody List<RoleMst> roleMsts) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(value = "/roleMst",method = RequestMethod.POST)
	public ResponseEntity<?> insertRoleMst(@RequestBody RoleMst roleMst){
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(value = "/roleMsts", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRoleMsts(@RequestBody List<RoleMst> roleMsts) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(value = "/roleMst", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRoleMst(@RequestBody RoleMst roleMst) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(value = "/roleMst", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRoleMstById(@PathVariable int id) {
		// TODO Auto-generated method stub
		return null;
	}
}

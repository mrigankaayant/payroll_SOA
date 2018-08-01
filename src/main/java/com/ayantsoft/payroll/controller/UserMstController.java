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

import com.ayantsoft.payroll.exception.DataAccessException;
import com.ayantsoft.payroll.exception.DataInsertionException;
import com.ayantsoft.payroll.exception.DataUpdationException;
import com.ayantsoft.payroll.exception.PayrollException;
import com.ayantsoft.payroll.hibernate.pojo.UserMst;
import com.ayantsoft.payroll.service.UserMstService;

@RestController
public class UserMstController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6390836349426735338L;

	private Logger log = Logger.getLogger(UserMstController.class);

	@Autowired 
	UserMstService  userMstService;

	@RequestMapping(value = "/userMsts", method = RequestMethod.GET)
	public ResponseEntity<?> getUserMsts(){
		List<UserMst> userMst = null;
		HttpStatus httpStatus = null;
		try{
			userMst = userMstService.getUserMsts();
			if(userMst!= null || userMst.isEmpty()){
				httpStatus = httpStatus.OK;
			}
			else {
				httpStatus = httpStatus.NO_CONTENT;
			}
		}catch(DataAccessException de){
			log.error("data insertion exception :"+de);
			httpStatus = httpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<List<UserMst>>(userMst, httpStatus);
	}

	@RequestMapping(value = "/userMst/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserMstById(@PathVariable int id){

		UserMst userMst = null;
		HttpStatus httpStatus = null;
		try{
			userMst = userMstService.getUserMstById(id);
			if(userMst != null){
				httpStatus = HttpStatus.OK;
			}
			else
			{
				httpStatus = HttpStatus.NO_CONTENT;
			}
		}catch(DataAccessException ae){

		}
		return new ResponseEntity<UserMst>(userMst,httpStatus);
	}

	@RequestMapping(value = "/userMsts/search", method = RequestMethod.POST)
	public ResponseEntity<?> getUserMstsByProperties(@RequestBody UserMst userMst){

		List<UserMst> userMsts = null;
		HttpStatus httpstatus = null;
		try{
			userMsts = userMstService.getUserMstsByProperties(userMst);
			httpstatus = HttpStatus.OK;

		}catch(PayrollException pe){
			log.error("getUserMstsByProperties error :"+pe);
		}
		return new ResponseEntity<List<UserMst>> (userMsts,httpstatus);
	}

	/*CHECK searchValue WITH SERIALIZABLE DATATYPE*/
	@RequestMapping(value = "/userMsts/{searchKey}/{searchValue}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserMstsByProperty(@PathVariable String searchKey, @PathVariable String searchValue) {
		List<UserMst> userMsts = null;
		HttpStatus httpStatus = null;
		try{
			userMsts = userMstService.getUserMstsByProperty(searchKey, searchValue);
			if(userMsts!=null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus =  HttpStatus.NO_CONTENT;
			}

		}catch(PayrollException pe){
			log.error("usermst data fetch by property error :"+pe);
		}
		return new ResponseEntity<List<UserMst>>(userMsts,httpStatus);
	}

	@RequestMapping(value = "/userMsts", method = RequestMethod.POST)
	public ResponseEntity<?> insertUserMsts(@RequestBody List<UserMst> userMsts) {
		HttpStatus httpStatus = null;
		try{
			userMstService.insertUserMsts(userMsts);
			httpStatus = HttpStatus.CREATED;

		}catch(DataInsertionException de){
			log.error("usermaster data insert exception :"+de);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<List<UserMst>>(userMsts,httpStatus);
	}

	@RequestMapping(value = "/userMst",method = RequestMethod.POST)
	public ResponseEntity<?> insertUserMst(@RequestBody UserMst userMst){
		HttpStatus httpstatus = null;
		try{
			userMstService.insertUserMst(userMst);
			httpstatus = HttpStatus.CREATED;

		}catch(DataInsertionException de){
			log.error("usermst insert exception :"+de);
			httpstatus= HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<UserMst>(userMst,httpstatus);
	}

	@RequestMapping(value = "/userMsts", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUserMsts(@RequestBody List<UserMst> userMst) {

		return null;
	}

	@RequestMapping(value = "/userMst", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUserMst(@RequestBody UserMst userMst) {
		HttpStatus httpstatus = null;
		try{
			userMstService.updateUserMst(userMst);
			httpstatus = HttpStatus.OK;

		}catch(DataUpdationException de){
			log.error("User data insert exception :"+de);
			httpstatus = HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<UserMst>(userMst,httpstatus);
	}

	@RequestMapping(value = "/userMst/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUserMstById(@PathVariable int id) {

		HttpStatus httpstatus = null;
		try{
			userMstService.deleteUserMstById(id);
			httpstatus = HttpStatus.OK;

		}catch(DataUpdationException de){
			log.error("user delete error :"+de);
		}
		return new  ResponseEntity<UserMst>(httpstatus);
	}
}

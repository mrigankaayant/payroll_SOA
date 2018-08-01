package com.ayantsoft.payroll.controller;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ayantsoft.payroll.dto.EmployeeDto;
import com.ayantsoft.payroll.exception.DataInsertionException;
import com.ayantsoft.payroll.exception.PayrollException;
import com.ayantsoft.payroll.hibernate.pojo.CityMst;
import com.ayantsoft.payroll.hibernate.pojo.CountryMst;
import com.ayantsoft.payroll.hibernate.pojo.EmployeeMst;
import com.ayantsoft.payroll.hibernate.pojo.PayrollComp;
import com.ayantsoft.payroll.hibernate.pojo.PayrollGroupMst;
import com.ayantsoft.payroll.hibernate.pojo.StateMst;
import com.ayantsoft.payroll.model.lazy.LazyDataModel;
import com.ayantsoft.payroll.service.EmployeeMstService;

@RestController
public class EmployeeMstController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 272177836645530886L;

	private Logger log = Logger.getLogger(EmployeeMstController.class);
	

	@Autowired
	private EmployeeMstService employeeMstService;
	
	
	
	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public ResponseEntity<?> insertEmployeeMst(@RequestBody EmployeeMst employeeMst) {
		HttpStatus httpStatus = null;
		Integer id = null;
		try{
			 employeeMstService.insertEmployeeMst(employeeMst);
			httpStatus = HttpStatus.CREATED;

		}catch(NullPointerException ne){
			log.info("null pointer exception here: "+ne);

		}
		catch (PayrollException pe) {
			log.error("insertEmployeeMst Error : " + pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<EmployeeMst>(employeeMst, httpStatus);
	}
	
	
	
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getEmployeeMstById(@PathVariable int id) {
		EmployeeMst employeeMst = null;
		HttpStatus httpStatus = null;
		try{
			employeeMst = employeeMstService.getEmployeeMstById(id);

			if(employeeMst != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}
		}catch(PayrollException pe){
			log.info("getEmployeeMstById Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<EmployeeMst>(employeeMst, httpStatus);
	}
	
	
	
	@RequestMapping(value = "/employees/lazySearchData", method = RequestMethod.POST)
	public ResponseEntity<?> getEmployeeLazyData(@RequestBody LazyDataModel lazyDataModel){
		
		if(lazyDataModel==null){
			 lazyDataModel=new LazyDataModel();
		}
		HttpStatus httpStatus = null;
		try{
			lazyDataModel = employeeMstService.getEmployeeLazydata(lazyDataModel);
			
			if(lazyDataModel == null){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException te){
			log.error("PayrollException Error : ", te);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<LazyDataModel>(lazyDataModel, httpStatus);
	}
	
	
	
	
	
/*

	
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public void uploadFile(@RequestParam MultipartFile myfile) {

		try{
			int i = new File("/home/ayant1/payroll/upload_employee").listFiles().length;
			String destination = "/home/ayant1/payroll/upload_employee/" +(i+1)+"_"+myfile.getOriginalFilename();
			File f = new File(destination);
			f.createNewFile();
		    File file = new File(destination);
		    myfile.transferTo(file);
		    
		    List<EmployeeMst> employeeList = new ArrayList<EmployeeMst>(0);
		    
		    XSSFWorkbook book = new XSSFWorkbook(myfile.getInputStream());
		    
		   
		    	XSSFSheet sheet = book.getSheetAt(0);
		    	Iterator<Row> rowIterator = sheet.iterator();
		    	rowIterator.next();
		    	while(rowIterator.hasNext()){
		    		Row row = rowIterator.next();
		    		
		    		String firstName = row.getCell(0).getStringCellValue();
	                String lastName = row.getCell(1).getStringCellValue();
	                String gender = row.getCell(2).getStringCellValue();
	                String dateOfBirth = row.getCell(3).getStringCellValue();
	                String fatherName = row.getCell(4).getStringCellValue();
	                String motherName = row.getCell(5).getStringCellValue();
	                String qualification = row.getCell(6).getStringCellValue();
	                String department = row.getCell(7).getStringCellValue();
	                String designation = row.getCell(8).getStringCellValue();
	                double payrollGroupId = row.getCell(9).getNumericCellValue();
	                String employeeCode = row.getCell(10).getStringCellValue();
	                double salary = row.getCell(11).getNumericCellValue();
	                String houseNo = row.getCell(12).getStringCellValue();
	                String street = row.getCell(13).getStringCellValue();
	                double countryId = row.getCell(14).getNumericCellValue();
	                double stateId = row.getCell(15).getNumericCellValue();
	                double cityId = row.getCell(16).getNumericCellValue();
	                double WorkingCityId = row.getCell(17).getNumericCellValue();
	                double zipcode = row.getCell(18).getNumericCellValue();
	                double phoneNo = row.getCell(19).getNumericCellValue();
	                double mobileNo = row.getCell(20).getNumericCellValue();
	                String email = row.getCell(21).getStringCellValue();
	                String bankName = row.getCell(22).getStringCellValue();
	                double accountNo = row.getCell(23).getNumericCellValue();
	                String accountType = row.getCell(24).getStringCellValue();
	                String branchName = row.getCell(25).getStringCellValue();
	                String branchAddress = row.getCell(26).getStringCellValue();
	                double ifscCode = row.getCell(27).getNumericCellValue();
	                double mircCode = row.getCell(28).getNumericCellValue();
	                double panNo = row.getCell(29).getNumericCellValue();
	                EmployeeMst employeeMst = new EmployeeMst();
	                employeeMst.setFirstName(firstName);
	                employeeMst.setLastName(lastName);
	                if("MALE".equalsIgnoreCase(gender)){
	                	employeeMst.setGender("M");
	                }
	                if("FEMALE".equalsIgnoreCase(gender)){
	                	employeeMst.setGender("F");
	                }
	                
	                SimpleDateFormat sf = new SimpleDateFormat("dd/MMM/yyyy");
	                Date dob = sf.parse(dateOfBirth);
	                employeeMst.setDob(dob);
	                
	                employeeMst.setFathersName(fatherName);
	                employeeMst.setMothersName(motherName);
	                employeeMst.setQualification(qualification);
	                employeeMst.setDeperment(department);
	                employeeMst.setDesignation(designation);
	                
	                PayrollGroupMst pgm = new PayrollGroupMst();
	                pgm.setId((int)payrollGroupId);
	                employeeMst.setPayrollGroupMst(pgm);
	                
	                employeeMst.setEmployeeCode(employeeCode);
	                employeeMst.setMonthlySalary(new BigDecimal(salary));
	                employeeMst.setHouseNo(houseNo);
	                employeeMst.setStreet(street);
	                
	                CountryMst countryMst = new CountryMst();
	                countryMst.setId((int)countryId);
	                employeeMst.setCountryMst(countryMst);
	                
	                StateMst stateMst = new StateMst();
	                stateMst.setId((int)stateId);
	                employeeMst.setStateMst(stateMst);
	                
	                CityMst cityMst = new CityMst();
	                cityMst.setId((int)cityId);
	                employeeMst.setCityMstByCityMstId(cityMst);
	                
	                CityMst workingCityMst = new CityMst();
	                workingCityMst.setId((int)WorkingCityId);
	                employeeMst.setCityMstByWorkingCity(workingCityMst);
	                
	                employeeMst.setZipCode((int)zipcode);
	                int k = (int)phoneNo;
	                employeeMst.setPhoneNo(new Integer(k).toString());
	                k=(int)mobileNo;
	                employeeMst.setMobileNo(new Integer(k).toString());
	                
	                employeeMst.setEmail(email);
	                employeeMst.setBankName(bankName);
	                k=(int)accountNo;
	                employeeMst.setBankAccountNo(new Integer(k).toString());
	                
	                employeeMst.setBankAccountType(accountType);
	                employeeMst.setBankBranchName(branchName);
	                employeeMst.setBankBranchAddress(branchAddress);
	                k=(int)ifscCode;
	                employeeMst.setBankIfsCode(new Integer(k).toString());
	                k=(int)mircCode;
	                employeeMst.setBankMicrCode(new Integer(k).toString());
	                
	                k=(int)panNo;
	                employeeMst.setPanNumber(new Integer(k).toString());
	                
	                employeeList.add(employeeMst);
	                
		    }
		    	
		   employeeMstService.insertEmployeeMsts(employeeList);
	       System.out.println("======= >>>> SUCCESS ");
		    	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public ResponseEntity<?> getEmployeeMsts() {
		List<EmployeeMst> employeeMsts = null;
		HttpStatus httpStatus = null;
		try{
			employeeMsts = employeeMstService.getEmployeeMsts();

			if(employeeMsts == null || employeeMsts.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.error("getEmployeeMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<EmployeeMst>>(employeeMsts, httpStatus);
	}
	

	

	
	@RequestMapping(value = "/employees/search", method = RequestMethod.POST)
	public ResponseEntity<?> getEmployeeMstsByProperties(@RequestBody EmployeeMst employeeMst) {
		
		List<EmployeeMst> employeeMsts = null;
		HttpStatus httpStatus = null;
		try{
			
			employeeMsts = employeeMstService.getEmployeeMstsByProperties(employeeMst);

			if(employeeMsts == null || employeeMsts.isEmpty()){
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.info("getEmployeeMstsByProperties Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<EmployeeMst>>(employeeMsts, httpStatus);
	}


	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/employees/lazySearchData", method = RequestMethod.POST)
	public ResponseEntity<?> getPayrollGroupMstsLazydata(@RequestBody LazyDataRequestModel lazyDataRequestModel) {
		log.info("come to payroll group mstt ");

		LazyDataResponseModel<EmployeeMst> lazyDataResponseModel = null;
		HttpStatus httpStatus = null;

		try{
			lazyDataResponseModel = employeeMstService.getEmployeeLazydata(lazyDataRequestModel);
			httpStatus = HttpStatus.OK;

		}catch(PayrollException pe){

			log.info("catch exception is"+pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<LazyDataResponseModel>(lazyDataResponseModel, httpStatus);
	}
	
	
	

	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/payrollgroup/{id}/employees",method=RequestMethod.GET)
	public ResponseEntity<?> getEmployeesByPayrollGroupId(@PathVariable int id){

		List<EmployeeMst> employeeMsts = null;
		HttpStatus httpStatus = null;
		try{
			employeeMsts =  employeeMstService.getEmployeesByPayrollGroupId(id);
			if(employeeMsts == null){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}

		}catch(PayrollException pe){
			log.error(" Employee  get By payroll group id : "+pe);
			httpStatus= HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return new ResponseEntity<List<EmployeeMst>>(employeeMsts, httpStatus);
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/employees/{searchKey}/{searchValue}", method = RequestMethod.GET)
	public ResponseEntity<?> getEmployeeMstsByProperty(@PathVariable String searchKey, @PathVariable String searchValue) {
		
		List<EmployeeMst> employeeMsts = null;
		HttpStatus httpStatus = null;
		try{
			
			employeeMsts = employeeMstService.getEmployeeMstsByProperty(searchKey, searchValue);
			if(employeeMsts == null || employeeMsts.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.error("getEmployeeMstsByProperty Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<EmployeeMst>>(employeeMsts, httpStatus);  
	}


	@RequestMapping(value = "/employees", method = RequestMethod.POST)
	public ResponseEntity<?> insertEmployeeMsts(@RequestBody List<EmployeeMst> employeeMsts) {

		HttpStatus httpStatus = null;
		try{

			employeeMstService.insertEmployeeMsts(employeeMsts);
			httpStatus = HttpStatus.CREATED;

		}catch (DataInsertionException die) {
			log.error("insertEmployeeMsts Error : " + die);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<EmployeeMst>>(employeeMsts, httpStatus);
	}

	
	

	
	@RequestMapping(value = "/employees", method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmployeeMsts(@RequestBody List<EmployeeMst> employeeMsts) {
		HttpStatus httpStatus = null;
		try{
			employeeMstService.updateEmployeeMsts(employeeMsts);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.error("updateEmployeeMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<EmployeeMst>>(employeeMsts, httpStatus);
	}

	@RequestMapping(value = "/employees/byPayrollGroup", method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmployeeMstsByPayrollGroup(@RequestBody List<EmployeeMst> employeeMsts) {
		HttpStatus httpStatus = null;
		try{
			employeeMstService.updateEmployeeMstsByPayrollGroup(employeeMsts);
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.error("updateEmployeeMsts Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<EmployeeMst>>(employeeMsts, httpStatus);
	}

	
	
	@RequestMapping(value = "/employee", method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmployeeMst(@RequestBody EmployeeMst employeeMst) throws IllegalArgumentException, IllegalAccessException {
		HttpStatus httpStatus = null;
		try{
			employeeMstService.updateEmployeeMst(employeeMst,employeeMst.getId());
			httpStatus = HttpStatus.OK;
		}catch(PayrollException pe){
			log.error("updateEmployeeMst Error : ", pe);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<EmployeeMst>(employeeMst, httpStatus);
	}
	
	

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmployeeMstById(@PathVariable int id) {
		HttpStatus httpStatus = null;
		try{
			employeeMstService.deleteEmployeeMstById(id);
			httpStatus = HttpStatus.OK;
		}catch(Exception e){
			log.info("Error : ", e);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<HttpStatus>(httpStatus);
	}
	
	
	@RequestMapping(value = "/employeePayrollGroupsUpdate", method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmployeePayrollGroup(@RequestBody List<EmployeeMst> employees) {
		
		HttpStatus httpStatus = null;
		try{
			employeeMstService.updatePayrollGroupsByEmpId(employees);
			httpStatus = HttpStatus.OK;
		}catch(Exception e){
			log.info("Error : ", e);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<HttpStatus>(httpStatus);
	}
	
	
	
	@RequestMapping(value = "/employeeListByPayrollGroupId/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPayrollCompsListByPayrollGroupId(@PathVariable int id){
		List<EmployeeMst> employees = null;
		HttpStatus httpStatus = null;
		try{
			employees = employeeMstService.getEmployeeListByPayrollGroupId(id);
			if(employees == null || employees.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(PayrollException pe){
			log.error("getPayrollCompsListByPayrollGroupId get data error : "+ pe);
		}
		return new ResponseEntity<List<EmployeeMst>>(employees, httpStatus);
	}
	*/

}
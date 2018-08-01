package com.ayantsoft.payroll.service;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.dto.search.LazyDataRequestModel;
import com.ayantsoft.payroll.dto.search.LazyDataResponseModel;
import com.ayantsoft.payroll.hibernate.pojo.EmployeeMst;
import com.ayantsoft.payroll.hibernate.pojo.PayrollGroupMst;
import com.ayantsoft.payroll.model.lazy.LazyDataModel;

public interface EmployeeMstService extends Serializable{
	EmployeeMst getEmployeeMstById(int id);
	void insertEmployeeMst(EmployeeMst employeeMst);
	LazyDataModel getEmployeeLazydata(LazyDataModel lazyDataModel);
	
	
	/*List<EmployeeMst> getEmployeeMsts();
	List<EmployeeMst> getEmployeeMstsByProperty(String searchKey,String searchValue);
	List<EmployeeMst> getEmployeeMstsByProperties(EmployeeMst employeeMst);
	List<EmployeeMst>getEmployeesByPayrollGroupId(int id);
	void insertEmployeeMsts(List<EmployeeMst> employeeMsts);
	void updateEmployeeMsts(List<EmployeeMst> employeeMsts);
	void updateEmployeeMstsByPayrollGroup(List<EmployeeMst> employeeMsts);
	void updateEmployeeMst(EmployeeMst employeeMst,int id) throws IllegalArgumentException, IllegalAccessException;
	void deleteEmployeeMstById(int id);
	void updatePayrollGroupsByEmpId(List<EmployeeMst> employees);
	List<EmployeeMst> getEmployeeListByPayrollGroupId(int id);*/

}
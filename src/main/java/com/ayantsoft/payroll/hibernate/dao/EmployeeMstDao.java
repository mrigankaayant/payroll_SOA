package com.ayantsoft.payroll.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.dto.search.LazyDataRequestModel;
import com.ayantsoft.payroll.dto.search.LazyDataResponseModel;
import com.ayantsoft.payroll.hibernate.pojo.EmployeeMst;
import com.ayantsoft.payroll.model.lazy.LazyDataModel;

public interface EmployeeMstDao extends Serializable{
	LazyDataModel getEmployeeLazydata(LazyDataModel lazyDataModel);
	void insertEmployeeMst(EmployeeMst employeeMst);
	EmployeeMst getEmployeeMstById(int id);
	
	/*List<EmployeeMst> getEmployeeMsts();
	List<EmployeeMst> getEmployeeMstsByProperty(String searchKey,String searchValue);
	List<EmployeeMst> getEmployeeMstsByProperties(EmployeeMst employeeMst);
	List<EmployeeMst>getEmployeesByPayrollGroupId(int id);
	void insertEmployeeMsts(List<EmployeeMst> employeeMsts);
	
	void updateEmployeeMsts(List<EmployeeMst> employeeMsts);
	void updateEmployeeMstsByPayrollGroup(List<EmployeeMst> employeeMsts);
	void deleteEmployeeMstById(int id);
	
	void updateEmployeeMst(EmployeeMst employeeMst, int id) throws IllegalArgumentException, IllegalAccessException;
	void updatePayrollGroupsByEmpId(List<EmployeeMst> employees);
	List<EmployeeMst> getEmployeeListByPayrollGroupId(int id);*/
}
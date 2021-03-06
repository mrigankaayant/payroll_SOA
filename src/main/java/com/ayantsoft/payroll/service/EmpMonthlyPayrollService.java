package com.ayantsoft.payroll.service;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.dto.EmployeeMonthlyPayrollDto;
import com.ayantsoft.payroll.dto.EmployeeMstDto;
import com.ayantsoft.payroll.dto.search.LazyDataRequestModel;
import com.ayantsoft.payroll.dto.search.LazyDataResponseModel;
import com.ayantsoft.payroll.hibernate.pojo.EmpMonthlyPayroll;
import com.ayantsoft.payroll.hibernate.pojo.EmployeeMst;

public interface EmpMonthlyPayrollService extends Serializable {

	void processEmployeePayrollReports();
	List<EmployeeMstDto> getEmployeeMonthlyPayroll();
	List<EmployeeMstDto> getMonthlyPayrollByProperties(EmpMonthlyPayroll empMonthlyPayroll);
	List<EmployeeMonthlyPayrollDto>getEmployeesByPayrollDetails(int id);
	LazyDataResponseModel<EmployeeMst> getMonthlyPayrollLazyData(LazyDataRequestModel lazyDataRequestModel);
	
}



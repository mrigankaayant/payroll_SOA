package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.dto.EmployeeMonthlyPayrollDto;
import com.ayantsoft.payroll.dto.EmployeeMstDto;
import com.ayantsoft.payroll.dto.search.LazyDataRequestModel;
import com.ayantsoft.payroll.dto.search.LazyDataResponseModel;
import com.ayantsoft.payroll.hibernate.dao.EmpMonthlyPayrollDao;
import com.ayantsoft.payroll.hibernate.pojo.EmpMonthlyPayroll;
import com.ayantsoft.payroll.hibernate.pojo.EmployeeMst;
import com.ayantsoft.payroll.service.EmpMonthlyPayrollService;

@Service
public class EmpMonthlyPayrollServiceImpl implements EmpMonthlyPayrollService,Serializable {

	@Autowired
	private EmpMonthlyPayrollDao empMonthlyReportDao;

	@Override
	public void processEmployeePayrollReports() {
		empMonthlyReportDao.processEmployeePayrollReports();
	}

	@Override
	public List<EmployeeMstDto> getEmployeeMonthlyPayroll() {
		return empMonthlyReportDao.getEmployeeMonthlyPayroll();
	}

	@Override
	public List<EmployeeMonthlyPayrollDto> getEmployeesByPayrollDetails(int id) {
		return empMonthlyReportDao.getEmployeesByPayrollDetails(id);
	}

	@Override
	public LazyDataResponseModel<EmployeeMst> getMonthlyPayrollLazyData(LazyDataRequestModel lazyDataRequestModel) {
		return empMonthlyReportDao.getMonthlyPayrollLazyData(lazyDataRequestModel);
	}

	@Override
	public List<EmployeeMstDto> getMonthlyPayrollByProperties(EmpMonthlyPayroll empMonthlyPayroll) {
		return empMonthlyReportDao.getMonthlyPayrollByProperties(empMonthlyPayroll);
	}

}


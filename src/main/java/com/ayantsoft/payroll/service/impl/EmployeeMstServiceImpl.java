package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.dto.search.LazyDataRequestModel;
import com.ayantsoft.payroll.dto.search.LazyDataResponseModel;
import com.ayantsoft.payroll.hibernate.dao.CityMstDao;
import com.ayantsoft.payroll.hibernate.dao.CountryMstDao;
import com.ayantsoft.payroll.hibernate.dao.EmployeeMstDao;
import com.ayantsoft.payroll.hibernate.dao.StateMstDao;
import com.ayantsoft.payroll.hibernate.pojo.EmployeeMst;
import com.ayantsoft.payroll.hibernate.pojo.PayrollGroupMst;
import com.ayantsoft.payroll.model.lazy.LazyDataModel;
import com.ayantsoft.payroll.service.EmployeeMstService;

@Service
public class EmployeeMstServiceImpl implements Serializable, EmployeeMstService{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7293509335066316709L;
	
	@Autowired
	private EmployeeMstDao employeeMstDao;
	@Autowired
	private CityMstDao cityMstDao;
	@Autowired
	private StateMstDao stateMstDao;
	@Autowired
	private CountryMstDao countryMstDao;
	
	
	@Override
	public EmployeeMst getEmployeeMstById(int id) {
		return employeeMstDao.getEmployeeMstById(id);
	}
	
	@Override
	public void insertEmployeeMst(EmployeeMst employeeMst) {
		 employeeMstDao.insertEmployeeMst(employeeMst);
	}
	
	@Override
	public LazyDataModel getEmployeeLazydata(LazyDataModel lazyDataModel) {
		return employeeMstDao.getEmployeeLazydata(lazyDataModel);
	}

	
	
	/*@Override
	public List<EmployeeMst> getEmployeeMsts() {
		return employeeMstDao.getEmployeeMsts();
	}
	
	
	
	@Override
	public List<EmployeeMst> getEmployeeMstsByProperties(EmployeeMst employeeMst) {
		return employeeMstDao.getEmployeeMstsByProperties(employeeMst);
	}
	
	@Override
	public void insertEmployeeMsts(List<EmployeeMst> employeeMsts) {
		employeeMstDao.insertEmployeeMsts(employeeMsts);
	}

	
	
	@Override
	public void updateEmployeeMsts(List<EmployeeMst> employeeMsts) {
		employeeMstDao.updateEmployeeMsts(employeeMsts);
	}

	@Override
	public void updateEmployeeMst(EmployeeMst employeeMst,int id) throws IllegalArgumentException, IllegalAccessException {
		 employeeMstDao.updateEmployeeMst(employeeMst,id);
	}

	@Override
	public void deleteEmployeeMstById(int id) {
		employeeMstDao.deleteEmployeeMstById(id);
	}

	
	@Override
	public void updateEmployeeMstsByPayrollGroup(List<EmployeeMst> employeeMsts) {
		employeeMstDao.updateEmployeeMstsByPayrollGroup(employeeMsts);
	}

	@Override
	public List<EmployeeMst> getEmployeesByPayrollGroupId(int id) {
		return employeeMstDao.getEmployeesByPayrollGroupId(id);
	}

	@Override
	public List<EmployeeMst> getEmployeeMstsByProperty(String searchKey, String searchValue) {
		return employeeMstDao.getEmployeeMstsByProperty(searchKey, searchValue);
	}

	@Override
	public void updatePayrollGroupsByEmpId(List<EmployeeMst> employees) {
		employeeMstDao.updatePayrollGroupsByEmpId(employees);
		
	}

	@Override
	public List<EmployeeMst> getEmployeeListByPayrollGroupId(int id) {
		return employeeMstDao.getEmployeeListByPayrollGroupId(id);
	}*/
}
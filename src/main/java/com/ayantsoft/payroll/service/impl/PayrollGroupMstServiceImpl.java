package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.dto.PayrollGroupMstDto;
import com.ayantsoft.payroll.dto.search.LazyDataRequestModel;
import com.ayantsoft.payroll.dto.search.LazyDataResponseModel;
import com.ayantsoft.payroll.hibernate.dao.PayrollGroupMstDao;
import com.ayantsoft.payroll.hibernate.pojo.PayrollGroupMst;
import com.ayantsoft.payroll.service.PayrollGroupMstService;

@Service
public class PayrollGroupMstServiceImpl implements Serializable, PayrollGroupMstService{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8127984980021840039L;
	
	@Autowired
	private PayrollGroupMstDao payrollGroupMstDao;
	
	@Override
	public List<PayrollGroupMst> getPayrollGroupMsts() {
		return payrollGroupMstDao.getPayrollGroupMsts();
	}
	
	
	@Override
	public PayrollGroupMst getPayrollGroupMstById(int id) {
		return payrollGroupMstDao.getPayrollGroupMstById(id);
	}
	
	@Override
	public List<PayrollGroupMst> getPayrollGroupMstsByProperties(PayrollGroupMst payrollGroupMst) {
		return payrollGroupMstDao.getPayrollGroupMstsByProperties(payrollGroupMst);
	}
	
	@Override
	public void insertPayrollGroupMsts(List<PayrollGroupMst> payrollGroupMsts) {
		payrollGroupMstDao.insertPayrollGroupMsts(payrollGroupMsts);
	}
	
	@Override
	public void insertPayrollGroupMst(PayrollGroupMst payrollGroupMst) {
		payrollGroupMstDao.insertPayrollGroupMst(payrollGroupMst);
	}
	
	@Override
	public void updatePayrollGroupMsts(List<PayrollGroupMst> payrollGroupMsts) {
		payrollGroupMstDao.updatePayrollGroupMsts(payrollGroupMsts);
	}

	@Override
	public void updatePayrollGroupMst(PayrollGroupMst payrollGroupMst) {
		payrollGroupMstDao.updatePayrollGroupMst(payrollGroupMst);
	}
	
	@Override
	public void deletePayrollGroupMstById(int id) {
		payrollGroupMstDao.deletePayrollGroupMstById(id);
	}

	@Override
	public LazyDataResponseModel<PayrollGroupMst> getPayrollGroupMstsLazydata(
			LazyDataRequestModel lazyDataRequestModel) {
		
		return payrollGroupMstDao.getPayrollGroupMstsLazydata(lazyDataRequestModel);
	}

	@Override
	public void insertPayslip(List<PayrollGroupMst> payrollGroupMsts) {
		payrollGroupMstDao.insertPayslip(payrollGroupMsts);
		
	}

	@Override
	public List<PayrollGroupMst> getPayrollGroupMstByProperty(String searchKey,String searchValue) {
		return payrollGroupMstDao.getPayrollGroupMstsByProperty(searchKey, searchValue);
	}


	@Override
	public List<PayrollGroupMstDto> getPayrollGroupWithEmp() {
		return payrollGroupMstDao.getPayrollGroupWithEmp();
	}
	
}
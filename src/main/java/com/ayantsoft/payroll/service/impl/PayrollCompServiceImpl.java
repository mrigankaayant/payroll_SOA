package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.controller.PayrollCompController;
import com.ayantsoft.payroll.dto.search.LazyDataRequestModel;
import com.ayantsoft.payroll.dto.search.LazyDataResponseModel;
import com.ayantsoft.payroll.hibernate.dao.PayrollCompDao;
import com.ayantsoft.payroll.hibernate.pojo.PayrollComp;
import com.ayantsoft.payroll.hibernate.pojo.PayrollGroupMst;
import com.ayantsoft.payroll.service.PayrollCompService;

@Service
public class PayrollCompServiceImpl  implements Serializable,PayrollCompService{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6341495710511154157L;

	@Autowired
	PayrollCompDao payrollCompDao;

	@Override
	public void insertPayrollComp(PayrollComp payrollComp) {
		payrollCompDao.insertPayrollComp(payrollComp);
	}
	
	@Override
	public List<PayrollComp> getPayrollComp() {
		return payrollCompDao.getPayrollComp();
	}

	@Override
	public PayrollComp getPayrollCompById(int id) {
		return payrollCompDao.getPayrollCompById(id);
	}

	@Override
	public void updatePayrollComp(PayrollComp payrollComp) {
		payrollCompDao.updatePayrollComp(payrollComp);
	}

	@Override
	public void insertPayrollComps(List<PayrollComp> payrollComps) {
		payrollCompDao.insertPayrollComps(payrollComps);
	}

	@Override
	public LazyDataResponseModel<PayrollComp> getPayrollEarningsLazydataById(
			LazyDataRequestModel lazyDataRequestModel) {
		return  payrollCompDao.getPayrollEarningsLazydataById(lazyDataRequestModel);
	}

	@Override
	public List<PayrollComp> getPayrollEarningCompByPayrollGroupId(int id) {
		return payrollCompDao.getPayrollEarningCompByPayrollGroupId(id);
	}
	
	@Override
	public void deletePayrollCompById(int id){
		payrollCompDao.deletePayrollCompById(id);
	}

	@Override
	public List<PayrollComp> getPayrollCompByProperty(String searchKey, String searchValue) {

		return payrollCompDao.getPayrollCompByProperty(searchKey, searchValue);
	}

	@Override
	public List<PayrollComp> getPayrollpayrollDeductionsByPayrollGroupId(int id) {
		return payrollCompDao.getPayrollpayrollDeductionsByPayrollGroupId(id);
	}

	@Override
	public List<PayrollComp> getPayrollCompsByPayrollGroupId(int id, String type) {
		return payrollCompDao.getPayrollCompsByPayrollGroupId(id, type);
	}


}

package com.ayantsoft.payroll.service;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.dto.search.LazyDataRequestModel;
import com.ayantsoft.payroll.dto.search.LazyDataResponseModel;
import com.ayantsoft.payroll.hibernate.pojo.PayrollComp;
import com.ayantsoft.payroll.hibernate.pojo.PayrollGroupMst;

public interface PayrollCompService extends Serializable{
	void insertPayrollComp(PayrollComp payrollComp);
	void insertPayrollComps(List<PayrollComp> payrollComps);
	List<PayrollComp> getPayrollComp();
	PayrollComp getPayrollCompById(int id);
	void updatePayrollComp(PayrollComp ayrollComp);
	LazyDataResponseModel<PayrollComp> getPayrollEarningsLazydataById(LazyDataRequestModel lazyDataRequestModel);
	List<PayrollComp> getPayrollEarningCompByPayrollGroupId(int id);
	List<PayrollComp> getPayrollpayrollDeductionsByPayrollGroupId(int id);
	void deletePayrollCompById(int id); 
	List<PayrollComp> getPayrollCompByProperty(String searchKey,String searchValue);
	List<PayrollComp> getPayrollCompsByPayrollGroupId(int id,String type);

}

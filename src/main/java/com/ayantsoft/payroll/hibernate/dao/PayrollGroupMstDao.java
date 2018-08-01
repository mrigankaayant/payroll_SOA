package com.ayantsoft.payroll.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.dto.PayrollGroupMstDto;
import com.ayantsoft.payroll.dto.search.LazyDataRequestModel;
import com.ayantsoft.payroll.dto.search.LazyDataResponseModel;
import com.ayantsoft.payroll.hibernate.pojo.PayrollGroupMst;

public interface PayrollGroupMstDao extends Serializable {
	List<PayrollGroupMst> getPayrollGroupMsts();
	PayrollGroupMst getPayrollGroupMstById(int id);
	List<PayrollGroupMst> getPayrollGroupMstsByProperties(PayrollGroupMst payrollGroupMst);
	void insertPayrollGroupMsts(List<PayrollGroupMst> payrollGroupMsts);
	void insertPayrollGroupMst(PayrollGroupMst payrollGroupMst);
	void updatePayrollGroupMsts(List<PayrollGroupMst> payrollGroupMsts);
	void updatePayrollGroupMst(PayrollGroupMst payrollGroupMst);
	void deletePayrollGroupMstById(int id);
	LazyDataResponseModel<PayrollGroupMst> getPayrollGroupMstsLazydata(LazyDataRequestModel lazyDataRequestModel);
	void insertPayslip(List<PayrollGroupMst> payrollGroupMsts);
	List<PayrollGroupMst> getPayrollGroupMstsByProperty(String searchKey,String searchValue);
	List<PayrollGroupMstDto> getPayrollGroupWithEmp();
	
}

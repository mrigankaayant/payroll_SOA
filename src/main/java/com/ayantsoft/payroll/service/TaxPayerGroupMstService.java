package com.ayantsoft.payroll.service;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.hibernate.pojo.TaxPayerGroupMst;

public interface TaxPayerGroupMstService extends Serializable {
	List<TaxPayerGroupMst> getTaxPayerGroupMsts();
	TaxPayerGroupMst getTaxPayerGroupMstById(int id);
	List<TaxPayerGroupMst> getTaxPayerGroupMstsByProperties(TaxPayerGroupMst taxPayerGroupMst);
	void insertTaxPayerGroupMsts(List<TaxPayerGroupMst> taxPayerGroupMsts);
	void insertTaxPayerGroupMst(TaxPayerGroupMst taxPayerGroupMst);
	void updateTaxPayerGroupMsts(List<TaxPayerGroupMst> taxPayerGroupMsts);
	void updateTaxPayerGroupMst(TaxPayerGroupMst taxPayerGroupMst);
	void deleteTaxPayerGroupMstById(int id);
}

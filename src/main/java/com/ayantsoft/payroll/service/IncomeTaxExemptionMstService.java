package com.ayantsoft.payroll.service;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.hibernate.pojo.IncomeTaxExemptionMst;

public interface IncomeTaxExemptionMstService extends Serializable {
	List<IncomeTaxExemptionMst> getIncomeTaxExemptionMsts();
	IncomeTaxExemptionMst getIncomeTaxExemptionMstById(int id);
	List<IncomeTaxExemptionMst> getIncomeTaxExemptionMstsByProperties(IncomeTaxExemptionMst incomeTaxExemptionMst);
	void insertIncomeTaxExemptionMsts(List<IncomeTaxExemptionMst> incomeTaxExemptionMsts);
	void insertIncomeTaxExemptionMst(IncomeTaxExemptionMst incomeTaxExemptionMst);
	void updateIncomeTaxExemptionMsts(List<IncomeTaxExemptionMst> incomeTaxExemptionMsts);
	void updateIncomeTaxExemptionMst(IncomeTaxExemptionMst incomeTaxExemptionMst);
	void deleteIncomeTaxExemptionMstById(int id);
}

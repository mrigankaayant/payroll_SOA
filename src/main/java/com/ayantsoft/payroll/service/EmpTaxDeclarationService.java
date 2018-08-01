package com.ayantsoft.payroll.service;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.hibernate.pojo.EmpTaxDeclaration;

public interface EmpTaxDeclarationService extends Serializable {
	void insertEmpTaxDeclaration(EmpTaxDeclaration empTaxDeclaration);
	//List<EmpTaxDeclaration> getEmpTaxDeclarationByEmployeeMstId(int empMstId);
}

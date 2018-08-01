package com.ayantsoft.payroll.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.hibernate.pojo.EmpTaxDeclaration;

public interface EmpTaxDeclarationDao extends Serializable {
	void insertEmpTaxDeclaration(EmpTaxDeclaration empTaxDeclaration);
	//List<EmpTaxDeclaration> getEmpTaxDeclarationByEmployeeMstId(int empMstId);
}

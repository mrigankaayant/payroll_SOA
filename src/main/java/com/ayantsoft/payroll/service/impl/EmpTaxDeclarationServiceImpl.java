package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.hibernate.dao.EmpTaxDeclarationDao;
import com.ayantsoft.payroll.hibernate.pojo.EmpTaxDeclaration;
import com.ayantsoft.payroll.service.EmpTaxDeclarationService;

@Service
public class EmpTaxDeclarationServiceImpl implements Serializable, EmpTaxDeclarationService {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8367669489573724137L;
	
	@Autowired
	private EmpTaxDeclarationDao empTaxDeclarationDao;

	@Override
	public void insertEmpTaxDeclaration(EmpTaxDeclaration empTaxDeclaration){
		empTaxDeclarationDao.insertEmpTaxDeclaration(empTaxDeclaration);
	}

}

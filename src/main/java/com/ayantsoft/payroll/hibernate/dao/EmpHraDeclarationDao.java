package com.ayantsoft.payroll.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.dto.EmployeeHrDeclarationDto;
import com.ayantsoft.payroll.hibernate.pojo.EmpHraDeclaration;

public interface EmpHraDeclarationDao extends Serializable {
	void insertEmpHraDeclaration(EmpHraDeclaration empHraDeclaration);
	void insertEmpHraDeclarations(List<EmpHraDeclaration> empHraDeclarations);
	EmployeeHrDeclarationDto getEmpHraDeclarationById(int id);
    List<EmployeeHrDeclarationDto> getEmpHraDeclarations();
    void updateEmpHraDeclaration(EmpHraDeclaration empHraDeclaration,int id) throws IllegalArgumentException, IllegalAccessException;
}

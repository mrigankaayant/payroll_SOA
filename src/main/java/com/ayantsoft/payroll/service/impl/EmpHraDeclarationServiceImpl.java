package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.dto.EmployeeHrDeclarationDto;
import com.ayantsoft.payroll.hibernate.dao.EmpHraDeclarationDao;
import com.ayantsoft.payroll.hibernate.pojo.EmpHraDeclaration;
import com.ayantsoft.payroll.service.EmpHraDeclarationService;

@Service
public class EmpHraDeclarationServiceImpl implements Serializable, EmpHraDeclarationService {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4748049579105136834L;
	
	@Autowired
	private EmpHraDeclarationDao empHraDeclarationDao;
	
	@Override
	public void insertEmpHraDeclaration(EmpHraDeclaration empHraDeclaration){
		empHraDeclarationDao.insertEmpHraDeclaration(empHraDeclaration);
	}
	
	@Override
	public void insertEmpHraDeclarations(List<EmpHraDeclaration> empHraDeclarations){
		empHraDeclarationDao.insertEmpHraDeclarations(empHraDeclarations);
	}
	
	
	@Override
	public EmployeeHrDeclarationDto getEmpHraDeclarationById(int id){
		return empHraDeclarationDao.getEmpHraDeclarationById(id);	
	}
	
	
	@Override
	public List<EmployeeHrDeclarationDto> getEmpHraDeclarations(){
		return empHraDeclarationDao.getEmpHraDeclarations(); 
	}
	
	
	@Override
	public void updateEmpHraDeclaration(EmpHraDeclaration empHraDeclaration,int id) throws IllegalArgumentException, IllegalAccessException{
		empHraDeclarationDao.updateEmpHraDeclaration(empHraDeclaration,id);
	}
}

package com.ayantsoft.payroll.hibernate.dao;

import java.io.Serializable;

import com.ayantsoft.payroll.hibernate.pojo.CompanyInformation;

public interface CompanyInfoDao extends Serializable {
	
	void insertCompanyInfo(CompanyInformation companyInformation); 

}

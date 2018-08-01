package com.ayantsoft.payroll.service;

import java.io.Serializable;

import com.ayantsoft.payroll.hibernate.pojo.CompanyInformation;

public interface CompanyInfoService extends Serializable {
	
	void insertCompanyInfo(CompanyInformation companyInformation);

}

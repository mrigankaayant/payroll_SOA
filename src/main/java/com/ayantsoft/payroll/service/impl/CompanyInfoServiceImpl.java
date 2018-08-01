package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ayantsoft.payroll.hibernate.dao.CompanyInfoDao;
import com.ayantsoft.payroll.hibernate.pojo.CompanyInformation;
import com.ayantsoft.payroll.service.CompanyInfoService;

@Service
public class CompanyInfoServiceImpl implements Serializable,CompanyInfoService {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 3777411866970424512L;
	
	@Autowired
	private CompanyInfoDao companyInfoDao;
	
	
	@Override
	public void insertCompanyInfo(CompanyInformation companyInformation) {
		companyInfoDao.insertCompanyInfo(companyInformation);
	}
	
	

}

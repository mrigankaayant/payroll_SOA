package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.hibernate.dao.CountryMstDao;
import com.ayantsoft.payroll.hibernate.pojo.CountryMst;
import com.ayantsoft.payroll.service.CountryMstService;

@Service
public class CountryMstServiceImpl implements Serializable, CountryMstService {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 120849657306079587L;
	
	@Autowired
	private CountryMstDao countryMstDao;

	@Override
	public List<CountryMst> getCountryMsts() {
		return countryMstDao.getCountryMsts();
	}

	@Override
	public CountryMst getCountryMstById(int id) {
		return countryMstDao.getCountryMstById(id);
	}

}

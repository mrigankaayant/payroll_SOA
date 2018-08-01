package com.ayantsoft.payroll.service;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.hibernate.pojo.CountryMst;

public interface CountryMstService extends Serializable {
	List<CountryMst> getCountryMsts();
	CountryMst getCountryMstById(int id);
}

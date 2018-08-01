package com.ayantsoft.payroll.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.hibernate.pojo.CountryMst;

public interface CountryMstDao extends Serializable {
	List<CountryMst> getCountryMsts();
	CountryMst getCountryMstById(int id);
}

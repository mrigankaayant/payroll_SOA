package com.ayantsoft.payroll.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.hibernate.pojo.CityMst;
import com.ayantsoft.payroll.hibernate.pojo.StateMst;

public interface CityMstDao extends Serializable {
	List<CityMst> getCityMsts();
	CityMst getCityMstById(int id);
	List<CityMst> getCityByStateId(int id);
}

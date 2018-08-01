package com.ayantsoft.payroll.service;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.hibernate.pojo.CityMst;

public interface CityMstService extends Serializable {
	List<CityMst> getCityMsts();
	CityMst getCityMstById(int id);
	List<CityMst> getCityByStateId(int id);
}

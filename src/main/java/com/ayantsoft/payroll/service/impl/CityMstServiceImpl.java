package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.hibernate.dao.CityMstDao;
import com.ayantsoft.payroll.hibernate.pojo.CityMst;
import com.ayantsoft.payroll.service.CityMstService;

@Service
public class CityMstServiceImpl implements Serializable, CityMstService {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5624474009774559782L;
	
	@Autowired
	private CityMstDao cityMstDao;

	@Override
	public List<CityMst> getCityMsts() {
		return cityMstDao.getCityMsts();
	}

	@Override
	public CityMst getCityMstById(int id) {
		return cityMstDao.getCityMstById(id);
	}

	@Override
	public List<CityMst> getCityByStateId(int id) {
		return cityMstDao.getCityByStateId(id);
	}

}

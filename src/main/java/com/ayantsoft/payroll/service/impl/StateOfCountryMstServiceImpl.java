package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.hibernate.dao.StateOfCountryMstDao;
import com.ayantsoft.payroll.hibernate.pojo.StateOfCountryMst;
import com.ayantsoft.payroll.service.StateOfCountryMstService;

@Service
public class StateOfCountryMstServiceImpl implements StateOfCountryMstService, Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -802115885737324979L;
	
	@Autowired
	private StateOfCountryMstDao stateOfCountryMstDao;
	
	@Override
	public List<StateOfCountryMst> getStateOfCountryMsts() {
		return stateOfCountryMstDao.getStateOfCountryMsts();
	}
	
	@Override
	public StateOfCountryMst getStateOfCountryMstById(int id) {
		return stateOfCountryMstDao.getStateOfCountryMstById(id);
	}
	
	@Override
	public List<StateOfCountryMst> getStateOfCountryMstsByProperties(StateOfCountryMst stateOfCountryMst) {
		return stateOfCountryMstDao.getStateOfCountryMstsByProperties(stateOfCountryMst);
	}

	@Override
	public void insertStateOfCountryMsts(List<StateOfCountryMst> stateOfCountryMsts) {
		stateOfCountryMstDao.insertStateOfCountryMsts(stateOfCountryMsts);
	}

	@Override
	public void insertStateOfCountryMst(StateOfCountryMst stateOfCountryMst) {
		stateOfCountryMstDao.insertStateOfCountryMst(stateOfCountryMst);
	}

	@Override
	public void updateStateOfCountryMsts(List<StateOfCountryMst> stateOfCountryMsts) {
		stateOfCountryMstDao.updateStateOfCountryMsts(stateOfCountryMsts);
	}

	@Override
	public void updateStateOfCountryMst(StateOfCountryMst stateOfCountryMst) {
		stateOfCountryMstDao.updateStateOfCountryMst(stateOfCountryMst);
	}

	@Override
	public void deleteStateOfCountryMstById(int id) {
		stateOfCountryMstDao.deleteStateOfCountryMstById(id);
	}
}

package com.ayantsoft.payroll.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.hibernate.pojo.StateOfCountryMst;

public interface StateOfCountryMstDao extends Serializable {
	List<StateOfCountryMst> getStateOfCountryMsts();
	StateOfCountryMst getStateOfCountryMstById(int id);
	List<StateOfCountryMst> getStateOfCountryMstsByProperties(StateOfCountryMst stateOfCountryMst);
	void insertStateOfCountryMsts(List<StateOfCountryMst> stateOfCountryMsts);
	void insertStateOfCountryMst(StateOfCountryMst stateOfCountryMst);
	void updateStateOfCountryMsts(List<StateOfCountryMst> stateOfCountryMsts);
	void updateStateOfCountryMst(StateOfCountryMst stateOfCountryMst);
	void deleteStateOfCountryMstById(int id);
}

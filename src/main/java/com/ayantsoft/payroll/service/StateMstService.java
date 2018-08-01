package com.ayantsoft.payroll.service;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.hibernate.pojo.StateMst;

public interface StateMstService extends Serializable {
	List<StateMst> getStateMsts();
	StateMst getStateMstById(int id);
	List<StateMst> getStateByCountryId(int id);
}

package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.hibernate.dao.StateMstDao;
import com.ayantsoft.payroll.hibernate.pojo.StateMst;
import com.ayantsoft.payroll.service.StateMstService;

@Service
public class StateMstServiceImpl implements Serializable, StateMstService{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 620536696159388985L;
	
	@Autowired
	private StateMstDao stateMstDao;

	@Override
	public List<StateMst> getStateMsts() {
		return stateMstDao.getStateMsts();
	}

	@Override
	public StateMst getStateMstById(int id) {
		return stateMstDao.getStateMstById(id);
	}

	@Override
	public List<StateMst> getStateByCountryId(int id) {
		
		return stateMstDao.getStateByCountryId(id);
	}
}

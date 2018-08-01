package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.hibernate.dao.IncomeTaxDao;
import com.ayantsoft.payroll.hibernate.pojo.IncomeTax;
import com.ayantsoft.payroll.service.IncomeTaxService;

@Service
public class IncomeTaxServiceImpl implements Serializable,  IncomeTaxService{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6802182200924668987L;
	
	@Autowired
	private IncomeTaxDao incomeTaxDao;

	@Override
	public List<IncomeTax> getIncomeTaxs() {
		return incomeTaxDao.getIncomeTaxs();
	}

	@Override
	public IncomeTax getIncomeTaxById(int id) {
		return incomeTaxDao.getIncomeTaxById(id);
	}

	@Override
	public List<IncomeTax> getIncomeTaxsByProperties(IncomeTax incomeTax) {
		return incomeTaxDao.getIncomeTaxsByProperties(incomeTax);
	}

	@Override
	public void updateIncomeTax(IncomeTax incomeTax) {
		incomeTaxDao.updateIncomeTax(incomeTax);
	}

	@Override
	public void deleteIncomeTaxById(int id) {
		incomeTaxDao.deleteIncomeTaxById(id);
	}
}
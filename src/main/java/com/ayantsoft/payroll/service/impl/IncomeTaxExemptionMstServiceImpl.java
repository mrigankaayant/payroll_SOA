package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.hibernate.dao.IncomeTaxExemptionMstDao;
import com.ayantsoft.payroll.hibernate.pojo.IncomeTaxExemptionMst;
import com.ayantsoft.payroll.service.IncomeTaxExemptionMstService;

@Service
public class IncomeTaxExemptionMstServiceImpl implements Serializable, IncomeTaxExemptionMstService {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6917044204458284980L;
	
	@Autowired
	private IncomeTaxExemptionMstDao incomeTaxExemptionMstDao;
	
	@Override
	public List<IncomeTaxExemptionMst> getIncomeTaxExemptionMsts() {
		return incomeTaxExemptionMstDao.getIncomeTaxExemptionMsts();
	}

	@Override
	public IncomeTaxExemptionMst getIncomeTaxExemptionMstById(int id) {
		return incomeTaxExemptionMstDao.getIncomeTaxExemptionMstById(id);
	}

	@Override
	public List<IncomeTaxExemptionMst> getIncomeTaxExemptionMstsByProperties(IncomeTaxExemptionMst incomeTaxExemptionMst) {
		return incomeTaxExemptionMstDao.getIncomeTaxExemptionMstsByProperties(incomeTaxExemptionMst);
	}

	@Override
	public void insertIncomeTaxExemptionMsts(List<IncomeTaxExemptionMst> incomeTaxExemptionMsts) {
		incomeTaxExemptionMstDao.insertIncomeTaxExemptionMsts(incomeTaxExemptionMsts);
	}

	@Override
	public void insertIncomeTaxExemptionMst(IncomeTaxExemptionMst incomeTaxExemptionMst) {
		incomeTaxExemptionMstDao.insertIncomeTaxExemptionMst(incomeTaxExemptionMst);
	}

	@Override
	public void updateIncomeTaxExemptionMsts(List<IncomeTaxExemptionMst> incomeTaxExemptionMsts) {
		incomeTaxExemptionMstDao.updateIncomeTaxExemptionMsts(incomeTaxExemptionMsts);
	}

	@Override
	public void updateIncomeTaxExemptionMst(IncomeTaxExemptionMst incomeTaxExemptionMst) {
		incomeTaxExemptionMstDao.updateIncomeTaxExemptionMst(incomeTaxExemptionMst);
	}

	@Override
	public void deleteIncomeTaxExemptionMstById(int id) {
		incomeTaxExemptionMstDao.deleteIncomeTaxExemptionMstById(id);
	}
}

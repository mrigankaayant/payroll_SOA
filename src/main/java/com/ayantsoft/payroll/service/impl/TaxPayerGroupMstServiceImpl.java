package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.hibernate.dao.TaxPayerGroupMstDao;
import com.ayantsoft.payroll.hibernate.pojo.TaxPayerGroupMst;
import com.ayantsoft.payroll.service.TaxPayerGroupMstService;

@Service
public class TaxPayerGroupMstServiceImpl implements Serializable, TaxPayerGroupMstService {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8191866993063188145L;
	
	private Logger log = Logger.getLogger(TaxPayerGroupMstServiceImpl.class);
	
	@Autowired
	private TaxPayerGroupMstDao taxPayerGroupMstDao;

	@Override
	public List<TaxPayerGroupMst> getTaxPayerGroupMsts() {
		return taxPayerGroupMstDao.getTaxPayerGroupMsts();
	}

	@Override
	public TaxPayerGroupMst getTaxPayerGroupMstById(int id) {
		return taxPayerGroupMstDao.getTaxPayerGroupMstById(id);
	}

	@Override
	public List<TaxPayerGroupMst> getTaxPayerGroupMstsByProperties(TaxPayerGroupMst taxPayerGroupMst) {
		return taxPayerGroupMstDao.getTaxPayerGroupMstsByProperties(taxPayerGroupMst);
	}
	
	@Override
	public void insertTaxPayerGroupMsts(List<TaxPayerGroupMst> taxPayerGroupMsts) {
		taxPayerGroupMstDao.insertTaxPayerGroupMsts(taxPayerGroupMsts);
	}

	@Override
	public void insertTaxPayerGroupMst(TaxPayerGroupMst taxPayerGroupMst) {
		taxPayerGroupMstDao.insertTaxPayerGroupMst(taxPayerGroupMst);
	}

	@Override
	public void updateTaxPayerGroupMsts(List<TaxPayerGroupMst> taxPayerGroupMsts) {
		taxPayerGroupMstDao.updateTaxPayerGroupMsts(taxPayerGroupMsts);
	}

	@Override
	public void updateTaxPayerGroupMst(TaxPayerGroupMst taxPayerGroupMst) {
		taxPayerGroupMstDao.updateTaxPayerGroupMst(taxPayerGroupMst);
	}

	@Override
	public void deleteTaxPayerGroupMstById(int id) {
		taxPayerGroupMstDao.deleteTaxPayerGroupMstById(id);
	}
}
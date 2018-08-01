package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.hibernate.dao.ExemptionYearDao;
import com.ayantsoft.payroll.hibernate.pojo.ExemptionYear;
import com.ayantsoft.payroll.service.ExemptionYearService;

@Service
public class ExemptionYearServiceImpl implements ExemptionYearService, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7994198196911678485L;
	
	@Autowired
	private ExemptionYearDao exemptionYearDao;

	@Override
	public List<ExemptionYear> getExemptionYears() {
		return exemptionYearDao.getExemptionYears();
	}

	@Override
	public ExemptionYear getExemptionYearById(int id) {
		return exemptionYearDao.getExemptionYearById(id);
	}
	
	@Override
	public List<ExemptionYear> getExemptionYearsByProperties(ExemptionYear exemptionYear) {
		return exemptionYearDao.getExemptionYearsByProperties(exemptionYear);
	}

	@Override
	public void insertExemptionYears(List<ExemptionYear> exemptionYears) {
		exemptionYearDao.insertExemptionYears(exemptionYears);
	}

	@Override
	public void insertExemptionYear(ExemptionYear exemptionYear) {
		exemptionYearDao.insertExemptionYear(exemptionYear);
	}

	@Override
	public void updateExemptionYears(List<ExemptionYear> exemptionYears) {
		exemptionYearDao.updateExemptionYears(exemptionYears);
	}

	@Override
	public void updateExemptionYear(ExemptionYear exemptionYear) {
		exemptionYearDao.updateExemptionYear(exemptionYear);
	}

	@Override
	public void deleteExemptionYearById(int id) {
		exemptionYearDao.deleteExemptionYearById(id);
	}
}

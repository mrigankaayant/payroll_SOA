package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.hibernate.dao.ProfessionalTaxMstDao;
import com.ayantsoft.payroll.hibernate.pojo.ProfessionalTaxMst;
import com.ayantsoft.payroll.service.ProfessionalTaxMstService;

@Service
public class ProfessionalTaxMstServiceImpl implements Serializable, ProfessionalTaxMstService {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8417782688170631976L;
	
	@Autowired
	private ProfessionalTaxMstDao professionalTaxMstDao;

	@Override
	public List<ProfessionalTaxMst> getProfessionalTaxMsts() {
		return professionalTaxMstDao.getProfessionalTaxMsts();
	}

	@Override
	public ProfessionalTaxMst getProfessionalTaxMstById(int id) {
		return professionalTaxMstDao.getProfessionalTaxMstById(id);
	}

	@Override
	public List<ProfessionalTaxMst> getProfessionalTaxMstsByProperties(ProfessionalTaxMst professionalTaxMst) {
		return professionalTaxMstDao.getProfessionalTaxMstsByProperties(professionalTaxMst);
	}

	@Override
	public void insertProfessionalTaxMsts(List<ProfessionalTaxMst> professionalTaxMsts) {
		professionalTaxMstDao.insertProfessionalTaxMsts(professionalTaxMsts);
	}

	@Override
	public void insertProfessionalTaxMst(ProfessionalTaxMst professionalTaxMst) {
		professionalTaxMstDao.insertProfessionalTaxMst(professionalTaxMst);
	}

	@Override
	public void updateProfessionalTaxMsts(List<ProfessionalTaxMst> professionalTaxMsts) {
		professionalTaxMstDao.updateProfessionalTaxMsts(professionalTaxMsts);
	}

	@Override
	public void updateProfessionalTaxMst(ProfessionalTaxMst professionalTaxMsts) {
		professionalTaxMstDao.updateProfessionalTaxMst(professionalTaxMsts);
	}

	@Override
	public void deleteProfessionalTaxMstById(int id) {
		professionalTaxMstDao.deleteProfessionalTaxMstById(id);
	}
}

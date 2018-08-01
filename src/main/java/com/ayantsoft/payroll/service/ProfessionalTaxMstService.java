package com.ayantsoft.payroll.service;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.hibernate.pojo.ProfessionalTaxMst;

public interface ProfessionalTaxMstService extends Serializable {
	List<ProfessionalTaxMst> getProfessionalTaxMsts();
	ProfessionalTaxMst getProfessionalTaxMstById(int id);
	List<ProfessionalTaxMst> getProfessionalTaxMstsByProperties(ProfessionalTaxMst professionalTaxMst);
	void insertProfessionalTaxMsts(List<ProfessionalTaxMst> professionalTaxMsts);
	void insertProfessionalTaxMst(ProfessionalTaxMst professionalTaxMst);
	void updateProfessionalTaxMsts(List<ProfessionalTaxMst> professionalTaxMsts);
	void updateProfessionalTaxMst(ProfessionalTaxMst professionalTaxMsts);
	void deleteProfessionalTaxMstById(int id);
}

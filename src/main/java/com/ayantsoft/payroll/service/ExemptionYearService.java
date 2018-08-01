package com.ayantsoft.payroll.service;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.hibernate.pojo.ExemptionYear;

public interface ExemptionYearService extends Serializable {
	List<ExemptionYear> getExemptionYears();
	ExemptionYear getExemptionYearById(int id);
	List<ExemptionYear> getExemptionYearsByProperties(ExemptionYear exemptionYear);
	void insertExemptionYears(List<ExemptionYear> exemptionYears);
	void insertExemptionYear(ExemptionYear exemptionYear);
	void updateExemptionYears(List<ExemptionYear> exemptionYears);
	void updateExemptionYear(ExemptionYear exemptionYear);
	void deleteExemptionYearById(int id);
}

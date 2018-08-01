package com.ayantsoft.payroll.service;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.hibernate.pojo.IncomeTax;

public interface IncomeTaxService extends Serializable {
	List<IncomeTax> getIncomeTaxs();
	IncomeTax getIncomeTaxById(int id);
	List<IncomeTax> getIncomeTaxsByProperties(IncomeTax incomeTax);
	void updateIncomeTax(IncomeTax incomeTax);
	void deleteIncomeTaxById(int id);
}

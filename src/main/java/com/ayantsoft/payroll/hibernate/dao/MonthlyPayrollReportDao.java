package com.ayantsoft.payroll.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.hibernate.pojo.MonthlyPayrollReport;

public interface MonthlyPayrollReportDao extends Serializable {

	void insertMonthlyPayrollReports(int id);
	List<MonthlyPayrollReport>getMonthlyReportByDate(String date);
}

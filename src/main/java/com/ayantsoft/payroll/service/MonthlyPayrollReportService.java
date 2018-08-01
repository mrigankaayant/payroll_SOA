package com.ayantsoft.payroll.service;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.hibernate.pojo.MonthlyPayrollReport;

public interface MonthlyPayrollReportService extends Serializable{ 
	
	void insertMonthlyPayrollReports(int id);
	List<MonthlyPayrollReport>getMonthlyReportByDate(String date);
}

package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.hibernate.dao.MonthlyPayrollReportDao;
import com.ayantsoft.payroll.hibernate.pojo.MonthlyPayrollReport;
import com.ayantsoft.payroll.hibernate.pojo.PayrollGroupMst;
import com.ayantsoft.payroll.service.MonthlyPayrollReportService;

@Service
public class MonthlyPayrollReportServiceImpl implements MonthlyPayrollReportService,Serializable {

	/**
	 * serialVersionUID = 934787919835815142L;
	 */
	private static final long serialVersionUID = 934787919835815142L;
	
	@Autowired
	private MonthlyPayrollReportDao monthlyPayrollReportDao;

	@Override
	public void insertMonthlyPayrollReports(int id) {
		monthlyPayrollReportDao.insertMonthlyPayrollReports(id);
		
	}

	@Override
	public List<MonthlyPayrollReport> getMonthlyReportByDate(String date) {
		return monthlyPayrollReportDao.getMonthlyReportByDate(date);
	}
	
	
}

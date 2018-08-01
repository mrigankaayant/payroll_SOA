package com.ayantsoft.payroll.service;

import java.math.BigDecimal;
import java.util.Date;

public interface PayrollCalculationService {
	
	BigDecimal calculateBasic(BigDecimal salary,BigDecimal compValue);
	BigDecimal calculateHra(BigDecimal basic,BigDecimal compValue);
	BigDecimal calculateFixedComp(BigDecimal compValue);
	BigDecimal calculateLta(BigDecimal basic,BigDecimal compValue);
	BigDecimal calculateOtherAllowances(BigDecimal salary,BigDecimal otherValue);
	BigDecimal calculateDeduction(BigDecimal basic,BigDecimal compValue);
	int caculateAge(Date dateOfBirth);
	
}

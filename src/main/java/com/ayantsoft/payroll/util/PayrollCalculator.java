package com.ayantsoft.payroll.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.service.PayrollCalculationService;


@Service
public class PayrollCalculator implements PayrollCalculationService {

	/*@Autowired
	private EmployeeMstDao employeeMstDao;*/

	@Override
	public BigDecimal calculateBasic(BigDecimal salary,BigDecimal compValue) {

		return salary.multiply(compValue).divide(new BigDecimal(100));

	}

	@Override
	public BigDecimal calculateHra(BigDecimal basic,BigDecimal compValue) {

		return basic.multiply(compValue).divide(new BigDecimal(100));
	}

	@Override
	public BigDecimal calculateFixedComp(BigDecimal compValue) {

		return compValue;
	}

	@Override
	public BigDecimal calculateLta(BigDecimal basic,BigDecimal compValue) {

		return basic.multiply(compValue).divide(new BigDecimal(100));
	}

	@Override
	public BigDecimal calculateOtherAllowances(BigDecimal salary,BigDecimal otherCompAmountTotal) {

		return (salary.subtract(otherCompAmountTotal));
	}

	@Override
	public BigDecimal calculateDeduction(BigDecimal salary,BigDecimal compValue) {

		return salary.multiply(compValue).divide(new BigDecimal(100));
	}

	@Override
	public int caculateAge(Date dateOfBirth) {

		int years = 0;
		Calendar cal = Calendar.getInstance();
		long currentTime = System.currentTimeMillis();
		cal.setTimeInMillis(currentTime);
		
		Calendar birthDay = Calendar.getInstance();
	    birthDay.setTimeInMillis(dateOfBirth.getTime());
	    years = cal.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
	    return years;
	}


}

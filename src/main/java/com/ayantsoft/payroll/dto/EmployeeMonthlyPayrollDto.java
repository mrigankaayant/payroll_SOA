package com.ayantsoft.payroll.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EmployeeMonthlyPayrollDto implements Serializable {
	
	/**
	 * serialVersionUID = -1041408596165263120L;
	 */
	private static final long serialVersionUID = -1041408596165263120L;
	
	private String employeeCode;
	private String firstName;
	private String lastName;
	private String gender;
	private Date employeeDob;
	private String bankIfsCode;
	private String bankMicrCode;
	private String bankAccountNo;
	private String componentName;
	private String componentDescription;
	private BigDecimal componentValue;
	private String componentType;
	private String componentCategory;
	private int componentOrderIndex;
	private String payrollMonth;
	private String designation;
	private int exemptionYear;
	private BigDecimal amount;
	BigDecimal monthlySalary;
	
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Date getEmployeeDob() {
		return employeeDob;
	}
	public void setEmployeeDob(Date employeeDob) {
		this.employeeDob = employeeDob;
	}
	
	public String getBankIfsCode() {
		return bankIfsCode;
	}
	public void setBankIfsCode(String bankIfsCode) {
		this.bankIfsCode = bankIfsCode;
	}
	public String getBankMicrCode() {
		return bankMicrCode;
	}
	public void setBankMicrCode(String bankMicrCode) {
		this.bankMicrCode = bankMicrCode;
	}
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getComponentDescription() {
		return componentDescription;
	}
	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
	}
	public BigDecimal getComponentValue() {
		return componentValue;
	}
	public void setComponentValue(BigDecimal componentValue) {
		this.componentValue = componentValue;
	}
	public String getComponentType() {
		return componentType;
	}
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}
	
	
	public String getComponentCategory() {
		return componentCategory;
	}
	public void setComponentCategory(String componentCategory) {
		this.componentCategory = componentCategory;
	}
	public int getComponentOrderIndex() {
		return componentOrderIndex;
	}
	public void setComponentOrderIndex(int componentOrderIndex) {
		this.componentOrderIndex = componentOrderIndex;
	}
	public String getPayrollMonth() {
		return payrollMonth;
	}
	public void setPayrollMonth(String payrollMonth) {
		this.payrollMonth = payrollMonth;
	}
	public int getExemptionYear() {
		return exemptionYear;
	}
	public void setExemptionYear(int exemptionYear) {
		this.exemptionYear = exemptionYear;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getMonthlySalary() {
		return monthlySalary;
	}
	public void setMonthlySalary(BigDecimal monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	

}

package com.ayantsoft.payroll.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EmployeeMstDto implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5541612021487117702L;
	
	private Integer employeeMstId;
	private String employeeCode;
	private String firstName;
	private String lastName;
	private String gender;
	private Date dob;
	private Integer payrollGroupMstId;
	private String groupName;
	private String description;
	private String bankAccountNo;
	private String bankAccountType;
	private String bankName;
	private String bankBranchName;
	private String bankBranchAddress;
	private String bankIfsCode;
	private String bankMicrCode;
	private String panNumber;
	private Integer stateOfCountryMstId;
	private String stateName;
	private String countryCode;
	private BigDecimal monthlySalary;
	
	/*GETTER SETTER*/
	public Integer getEmployeeMstId() {
		return employeeMstId;
	}
	public void setEmployeeMstId(Integer employeeMstId) {
		this.employeeMstId = employeeMstId;
	}
	
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Integer getPayrollGroupMstId() {
		return payrollGroupMstId;
	}
	public void setPayrollGroupMstId(Integer payrollGroupMstId) {
		this.payrollGroupMstId = payrollGroupMstId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public String getBankAccountType() {
		return bankAccountType;
	}
	public void setBankAccountType(String bankAccountType) {
		this.bankAccountType = bankAccountType;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankBranchName() {
		return bankBranchName;
	}
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	public String getBankBranchAddress() {
		return bankBranchAddress;
	}
	public void setBankBranchAddress(String bankBranchAddress) {
		this.bankBranchAddress = bankBranchAddress;
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
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public Integer getStateOfCountryMstId() {
		return stateOfCountryMstId;
	}
	public void setStateOfCountryMstId(Integer stateOfCountryMstId) {
		this.stateOfCountryMstId = stateOfCountryMstId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public BigDecimal getMonthlySalary() {
		return monthlySalary;
	}
	public void setMonthlySalary(BigDecimal monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	
}

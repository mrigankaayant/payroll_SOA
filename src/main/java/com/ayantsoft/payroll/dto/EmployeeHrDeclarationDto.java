package com.ayantsoft.payroll.dto;

import java.math.BigDecimal;
import java.util.Date;

public class EmployeeHrDeclarationDto {
	private Integer empId;
	private String employeeCode;
	private String firstName;
	private String lastName;
	private String gender;
	private Date dob;
	private Integer mobileNo;
	private String email;
	private String deperment;
	private String designation;
	
	private Integer empHrDeclarationId;
	private String ownerName;
	private String houseAddress;
	private String ownerPan;
	private BigDecimal monthlyRent;
	private String rentDocuments;
	private BigDecimal actualAmount;
	private boolean isActive;
	private Date createdDate;
	private boolean isDeleted;
	
	private Integer year;
	private int exemptionYearId;
	private String description;
	private boolean isActiveForexemptionYear;
	private Date createdDateForexemptionYear;
	private boolean isDeletedForexemptionYear;
	
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
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
	public Integer getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Integer mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDeperment() {
		return deperment;
	}
	public void setDeperment(String deperment) {
		this.deperment = deperment;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Integer getEmpHrDeclarationId() {
		return empHrDeclarationId;
	}
	public void setEmpHrDeclarationId(Integer empHrDeclarationId) {
		this.empHrDeclarationId = empHrDeclarationId;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getHouseAddress() {
		return houseAddress;
	}
	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}
	public String getOwnerPan() {
		return ownerPan;
	}
	public void setOwnerPan(String ownerPan) {
		this.ownerPan = ownerPan;
	}
	public BigDecimal getMonthlyRent() {
		return monthlyRent;
	}
	public void setMonthlyRent(BigDecimal monthlyRent) {
		this.monthlyRent = monthlyRent;
	}
	public String getRentDocuments() {
		return rentDocuments;
	}
	public void setRentDocuments(String rentDocuments) {
		this.rentDocuments = rentDocuments;
	}
	public BigDecimal getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public int getExemptionYearId() {
		return exemptionYearId;
	}
	public void setExemptionYearId(int exemptionYearId) {
		this.exemptionYearId = exemptionYearId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isActiveForexemptionYear() {
		return isActiveForexemptionYear;
	}
	public void setActiveForexemptionYear(boolean isActiveForexemptionYear) {
		this.isActiveForexemptionYear = isActiveForexemptionYear;
	}
	public Date getCreatedDateForexemptionYear() {
		return createdDateForexemptionYear;
	}
	public void setCreatedDateForexemptionYear(Date createdDateForexemptionYear) {
		this.createdDateForexemptionYear = createdDateForexemptionYear;
	}
	public boolean isDeletedForexemptionYear() {
		return isDeletedForexemptionYear;
	}
	public void setDeletedForexemptionYear(boolean isDeletedForexemptionYear) {
		this.isDeletedForexemptionYear = isDeletedForexemptionYear;
	}
	
	
	

}

package com.ayantsoft.payroll.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PayrollGroupUtil {
	
	private Integer payrollGroupMstId;
	
	private Integer payrollCompMstId;
	private String name;
	private String description;
	private byte isMandatory;
	private String type;
	private String category;
	private int orderIndex;
	private byte isActive;
	
	private BigDecimal compValue;
	private Date createdDate;
	private String remarks;
	
	
	public Integer getPayrollGroupMstId() {
		return payrollGroupMstId;
	}
	public void setPayrollGroupMstId(Integer payrollGroupMstId) {
		this.payrollGroupMstId = payrollGroupMstId;
	}
	public Integer getPayrollCompMstId() {
		return payrollCompMstId;
	}
	public void setPayrollCompMstId(Integer payrollCompMstId) {
		this.payrollCompMstId = payrollCompMstId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte getIsMandatory() {
		return isMandatory;
	}
	public void setIsMandatory(byte isMandatory) {
		this.isMandatory = isMandatory;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}
	public byte getIsActive() {
		return isActive;
	}
	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}
	public BigDecimal getCompValue() {
		return compValue;
	}
	public void setCompValue(BigDecimal compValue) {
		this.compValue = compValue;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	
}

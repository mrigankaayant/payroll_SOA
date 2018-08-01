package com.ayantsoft.payroll.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayrollDeductionDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2182744098951019796L;
	
	private Integer id;
	private Integer payrollGroupMstId;
	private String name;
	private String comType;
	private BigDecimal compValue;
	private Boolean isActive;
	private Integer orderIndex;
	private Date createdDate;
	private Date modifyDate;
	private Boolean isMandatory;
	private PayrollGroupMstDto payrollGroupMstDto;
	
	/*GETTER SETTER*/
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPayrollGroupMstId() {
		return payrollGroupMstId;
	}
	public void setPayrollGroupMstId(Integer payrollGroupMstId) {
		this.payrollGroupMstId = payrollGroupMstId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComType() {
		return comType;
	}
	public void setComType(String comType) {
		this.comType = comType;
	}
	public BigDecimal getCompValue() {
		return compValue;
	}
	public void setCompValue(BigDecimal compValue) {
		this.compValue = compValue;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Integer getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Boolean getIsMandatory() {
		return isMandatory;
	}
	public void setIsMandatory(Boolean isMandatory) {
		this.isMandatory = isMandatory;
	}
	public PayrollGroupMstDto getPayrollGroupMstDto() {
		return payrollGroupMstDto;
	}
	public void setPayrollGroupMstDto(PayrollGroupMstDto payrollGroupMstDto) {
		this.payrollGroupMstDto = payrollGroupMstDto;
	}

}

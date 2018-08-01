package com.ayantsoft.payroll.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ayantsoft.payroll.hibernate.pojo.PayrollComp;
import com.ayantsoft.payroll.hibernate.pojo.PayrollGroupMst;

public class PayrollGroupMstDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2472549830061637856L;
	
    private String groupName;
    private Integer groupId;
	private Long totalEmployee;
	private Integer payrollCompId;
	private String payrollCompName;
	private String description;
	private BigDecimal compValue;
	private String compType;
	private int orderIndex;
	private String payrollCompCategory;

	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Long getTotalEmployee() {
		return totalEmployee;
	}
	public void setTotalEmployee(Long totalEmployee) {
		this.totalEmployee = totalEmployee;
	}
	public Integer getPayrollCompId() {
		return payrollCompId;
	}
	public void setPayrollCompId(Integer payrollCompId) {
		this.payrollCompId = payrollCompId;
	}
	
	public String getPayrollCompName() {
		return payrollCompName;
	}
	public void setPayrollCompName(String payrollCompName) {
		this.payrollCompName = payrollCompName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getCompValue() {
		return compValue;
	}
	public void setCompValue(BigDecimal compValue) {
		this.compValue = compValue;
	}
	public String getCompType() {
		return compType;
	}
	public void setCompType(String compType) {
		this.compType = compType;
	}
	public int getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}
	public String getPayrollCompCategory() {
		return payrollCompCategory;
	}
	public void setPayrollCompCategory(String payrollCompCategory) {
		this.payrollCompCategory = payrollCompCategory;
	}
	
}

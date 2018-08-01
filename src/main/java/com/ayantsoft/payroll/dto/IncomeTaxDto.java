package com.ayantsoft.payroll.dto;

import java.io.Serializable;

public class IncomeTaxDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3715875103727771255L;
	
	private Integer id;
	private Integer taxPayerGroupMstId;
	private Integer lowerLimitAmount;
	private Integer upperLimitAmount;
	private String gender;
	private String taxRate;
	private Boolean isActive;	
	private TaxPayerGroupMstDto taxPayerGroupMstDto;
	
	
	/*GETTER SETTER*/
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTaxPayerGroupMstId() {
		return taxPayerGroupMstId;
	}
	public void setTaxPayerGroupMstId(Integer taxPayerGroupMstId) {
		this.taxPayerGroupMstId = taxPayerGroupMstId;
	}
	public Integer getLowerLimitAmount() {
		return lowerLimitAmount;
	}
	public void setLowerLimitAmount(Integer lowerLimitAmount) {
		this.lowerLimitAmount = lowerLimitAmount;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Integer getUpperLimitAmount() {
		return upperLimitAmount;
	}
	public void setUpperLimitAmount(Integer upperLimitAmount) {
		this.upperLimitAmount = upperLimitAmount;
	}
	public TaxPayerGroupMstDto getTaxPayerGroupMstDto() {
		return taxPayerGroupMstDto;
	}
	public void setTaxPayerGroupMstDto(TaxPayerGroupMstDto taxPayerGroupMstDto) {
		this.taxPayerGroupMstDto = taxPayerGroupMstDto;
	}
}

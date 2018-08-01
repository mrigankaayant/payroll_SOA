package com.ayantsoft.payroll.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProfessionalTaxMstDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1440635349926960413L;
	
	private Integer id;
	private Integer stateOfCountryMstId;
	private BigDecimal uperLimitAmount;
	private BigDecimal lowerLimitAmount;
	private Integer taxAmount;
	private Boolean isActive;
	
	/*GETTER SETTER*/
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStateOfCountryMstId() {
		return stateOfCountryMstId;
	}
	public void setStateOfCountryMstId(Integer stateOfCountryMstId) {
		this.stateOfCountryMstId = stateOfCountryMstId;
	}
	public BigDecimal getUperLimitAmount() {
		return uperLimitAmount;
	}
	public void setUperLimitAmount(BigDecimal uperLimitAmount) {
		this.uperLimitAmount = uperLimitAmount;
	}
	public BigDecimal getLowerLimitAmount() {
		return lowerLimitAmount;
	}
	public void setLowerLimitAmount(BigDecimal lowerLimitAmount) {
		this.lowerLimitAmount = lowerLimitAmount;
	}
	public Integer getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(Integer taxAmount) {
		this.taxAmount = taxAmount;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

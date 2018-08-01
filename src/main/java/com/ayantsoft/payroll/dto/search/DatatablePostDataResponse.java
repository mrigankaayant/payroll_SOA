package com.ayantsoft.payroll.dto.search;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.hibernate.pojo.PayrollGroupMst;

public class DatatablePostDataResponse implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4011299117438428863L;

	private Integer draw;
	private Integer recordsTotal;
	private Integer recordsFiltered;
	
	private List<PayrollGroupMst> data;


	public List<PayrollGroupMst> getData() {
		return data;
	}
	public void setData(List<PayrollGroupMst> data) {
		this.data = data;
	}
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Integer getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	


}





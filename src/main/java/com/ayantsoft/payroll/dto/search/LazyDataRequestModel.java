package com.ayantsoft.payroll.dto.search;

import java.util.List;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class LazyDataRequestModel {
	
	  private Integer draw;
	  private Integer id;
	  private Integer start;
	  private Integer length;
	  private List<ColumnMeta> columns;
	  private List<OrderMeta> Order;
	  private SearchMeta search;
	  
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public List<ColumnMeta> getColumns() {
		return columns;
	}
	public void setColumns(List<ColumnMeta> columns) {
		this.columns = columns;
	}
	public List<OrderMeta> getOrder() {
		return Order;
	}
	public void setOrder(List<OrderMeta> order) {
		Order = order;
	}
	public SearchMeta getSearch() {
		return search;
	}
	public void setSearch(SearchMeta search) {
		this.search = search;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	  
	
	
}

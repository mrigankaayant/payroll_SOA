package com.ayantsoft.payroll.model.lazy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LazyDataModel{
    private List<Object> data;
	private Integer first;
	private Integer rows;
	private String  sortField;
	private Integer sortOrder;
	private Integer totalRecord;
	private Set<SortMeta> multiSortMeta=new HashSet<>(0);
	private String globalFilter;
	private Map<String,FilterMeta> filters=new HashMap<>(0);
	
	
	//setter and getter
		
	public Integer getFirst() {
		return first;
	}
	public Integer getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
	public void setFirst(Integer first) {
		this.first = first;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Set<SortMeta> getMultiSortMeta() {
		return multiSortMeta;
	}
	public void setMultiSortMeta(Set<SortMeta> multiSortMeta) {
		this.multiSortMeta = multiSortMeta;
	}
	public String getGlobalFilter() {
		return globalFilter;
	}
	public void setGlobalFilter(String globalFilter) {
		this.globalFilter = globalFilter;
	}
	public Map<String, FilterMeta> getFilters() {
		return filters;
	}
	public void setFilters(Map<String, FilterMeta> filters) {
		this.filters = filters;
	}




}

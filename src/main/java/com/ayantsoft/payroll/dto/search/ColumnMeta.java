package com.ayantsoft.payroll.dto.search;

public class ColumnMeta {
	
	private String data;
	private String name;
	private Boolean searchable;
	private Boolean orderable;
	private SearchMeta search;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getSearchable() {
		return searchable;
	}
	public void setSearchable(Boolean searchable) {
		this.searchable = searchable;
	}
	public Boolean getOrderable() {
		return orderable;
	}
	public void setOrderable(Boolean orderable) {
		this.orderable = orderable;
	}
	public SearchMeta getSearch() {
		return search;
	}
	public void setSearch(SearchMeta search) {
		this.search = search;
	}

	
}

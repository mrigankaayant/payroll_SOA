package com.ayantsoft.payroll.dto.search;

public class SearchMeta {
	
	private String value;
	private Boolean regex;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Boolean getRegex() {
		return regex;
	}
	public void setRegex(Boolean regex) {
		this.regex = regex;
	}
	
}

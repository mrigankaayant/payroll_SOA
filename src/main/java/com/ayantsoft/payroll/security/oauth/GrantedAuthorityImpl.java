package com.ayantsoft.payroll.security.oauth;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5855305817056701380L;
	
	private final String authority;
	
	public GrantedAuthorityImpl(String authority){
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

}
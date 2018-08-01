package com.ayantsoft.payroll.security.oauth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.ayantsoft.payroll.hibernate.pojo.UserMst;

public class AuthUser extends User{

	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -198527081066513996L;
	
	private UserMst userMst;

	public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public UserMst getUserMst() {
		return userMst;
	}
	public void setUserMst(UserMst userMst) {
		this.userMst = userMst;
	}
}
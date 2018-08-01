package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.hibernate.dao.LoginDao;
import com.ayantsoft.payroll.hibernate.pojo.UserMst;
import com.ayantsoft.payroll.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4989674742772580094L;
	
	@Autowired
	private LoginDao loginDao;
	
	@Override
	public UserMst doLogin(UserMst userMst) {
		return loginDao.doLogin(userMst);
	}
}

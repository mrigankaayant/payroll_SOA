package com.ayantsoft.payroll.service;

import java.io.Serializable;

import com.ayantsoft.payroll.hibernate.pojo.UserMst;

public interface LoginService extends Serializable {
	UserMst doLogin(UserMst userMst);
}

package com.ayantsoft.payroll.hibernate.dao;

import java.io.Serializable;

import com.ayantsoft.payroll.hibernate.pojo.UserMst;

public interface LoginDao extends Serializable {
	UserMst doLogin(UserMst userMst);
}

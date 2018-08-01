package com.ayantsoft.payroll.service;

import java.io.Serializable;
import java.util.List;


import com.ayantsoft.payroll.hibernate.pojo.UserMst;

public interface UserMstService extends Serializable {
	
	UserMst getUserMstById(int id);
	List<UserMst> getUserMsts();
	List<UserMst> getUserMstsByProperties(UserMst userMst);
	List<UserMst> getUserMstsByProperty(String searchKey,String searchValue);
	void insertUserMsts(List<UserMst> userMsts);
	void insertUserMst(UserMst userMst);
	void updateUserMsts(List<UserMst> userMst);
	void updateUserMst(UserMst userMst);
	void deleteUserMstById(int id);
	UserMst getUserMstByUsername(String userName);

}

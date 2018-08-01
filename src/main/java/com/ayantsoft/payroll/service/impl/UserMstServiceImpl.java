package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.hibernate.dao.UserMstDao;
import com.ayantsoft.payroll.hibernate.pojo.UserMst;
import com.ayantsoft.payroll.service.UserMstService;

@Service
public class UserMstServiceImpl implements UserMstService, Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8131370536610611147L;
	@Autowired
	private UserMstDao userMstDao;

	@Override
	public UserMst getUserMstById(int id) {
		return userMstDao.getUserMstById(id);
	}

	@Override
	public List<UserMst> getUserMsts() {
		return userMstDao.getUserMsts();
	}

	@Override
	public List<UserMst> getUserMstsByProperties(UserMst userMst) {
		return userMstDao.getUserMstsByProperties(userMst);
	}

	@Override
	public void insertUserMst(UserMst userMst) {
		userMstDao.insertUserMst(userMst);

	}

	@Override
	public void updateUserMsts(List<UserMst> userMsts) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUserMst(UserMst userMst) {
		userMstDao.updateUserMst(userMst);

	}

	@Override
	public void deleteUserMstById(int id) {
		userMstDao.deleteUserMstById(id);

	}

	@Override
	public void insertUserMsts(List<UserMst> userMsts) {
		userMstDao.insertUserMsts(userMsts);
		
	}

	@Override
	public List<UserMst> getUserMstsByProperty(String searchKey, String searchValue) {
		return userMstDao.getUserMstsByProperty(searchKey, searchValue);
	}

	@Override
	public UserMst getUserMstByUsername(String userName) {
		return userMstDao.getUserMstByUsername(userName);
	}


}

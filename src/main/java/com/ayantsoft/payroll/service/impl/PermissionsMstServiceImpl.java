package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.hibernate.dao.PermissionsMstDao;
import com.ayantsoft.payroll.hibernate.pojo.PermissionsMst;
import com.ayantsoft.payroll.service.PermissionsMstService;

@Service
public class PermissionsMstServiceImpl implements PermissionsMstService, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4198539256503507807L;
	
	private Logger log = Logger.getLogger(PermissionsMstServiceImpl.class);
	
	@Autowired
	private PermissionsMstDao permissionsMstDao;

	@Override
	public List<PermissionsMst> getPermissionsMsts() {
		return permissionsMstDao.getPermissionsMsts();
	}

	@Override
	public PermissionsMst getPermissionsMstById(int id) {
		return permissionsMstDao.getPermissionsMstById(id);
	}

	@Override
	public List<PermissionsMst> getPermissionsMstsByProperties(PermissionsMst permissionsMst) {
		return permissionsMstDao.getPermissionsMstsByProperties(permissionsMst);
	}

	@Override
	public void insertPermissionsMsts(List<PermissionsMst> permissionsMsts) {
		permissionsMstDao.insertPermissionsMsts(permissionsMsts);
	}

	@Override
	public void insertPermissionsMst(PermissionsMst permissionsMst) {
		permissionsMstDao.insertPermissionsMsts(permissionsMst);
	}

	@Override
	public void updatePermissionsMsts(List<PermissionsMst> permissionsMsts) {
		permissionsMstDao.updatePermissionsMsts(permissionsMsts);
	}

	@Override
	public void updatePermissionsMst(PermissionsMst permissionsMst) {
		permissionsMstDao.updatePermissionsMst(permissionsMst);
	}

	@Override
	public void deletePermissionsMstById(int id) {
		permissionsMstDao.deletePermissionsMstById(id);
	}
}

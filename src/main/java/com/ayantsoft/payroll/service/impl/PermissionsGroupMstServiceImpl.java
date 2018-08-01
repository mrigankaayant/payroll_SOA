package com.ayantsoft.payroll.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.payroll.hibernate.dao.PermissionsGroupMstDao;
import com.ayantsoft.payroll.hibernate.pojo.PermissionGroupMst;
import com.ayantsoft.payroll.service.PermissionsGroupMstService;

@Service
public class PermissionsGroupMstServiceImpl implements PermissionsGroupMstService, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4198539256503507807L;
	
	private Logger log = Logger.getLogger(PermissionsGroupMstServiceImpl.class);
	
	@Autowired
	private PermissionsGroupMstDao permissionsGroupMstDao;

	@Override
	public List<PermissionGroupMst> getPermissionsGroupMsts() {
		
		return permissionsGroupMstDao.getPermissionsGroupMsts();
		
	}

		
}

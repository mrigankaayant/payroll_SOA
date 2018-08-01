package com.ayantsoft.payroll.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.hibernate.pojo.PermissionGroupMst;

public interface PermissionsGroupMstDao extends Serializable {
	List<PermissionGroupMst> getPermissionsGroupMsts();
	
}

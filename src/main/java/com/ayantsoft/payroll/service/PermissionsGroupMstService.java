package com.ayantsoft.payroll.service;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.hibernate.pojo.PermissionGroupMst;


public interface PermissionsGroupMstService extends Serializable {
	List<PermissionGroupMst> getPermissionsGroupMsts();
	
}

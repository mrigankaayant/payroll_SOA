package com.ayantsoft.payroll.service;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.hibernate.pojo.PermissionsMst;

public interface PermissionsMstService extends Serializable {
	List<PermissionsMst> getPermissionsMsts();
	PermissionsMst getPermissionsMstById(int id);
	List<PermissionsMst> getPermissionsMstsByProperties(PermissionsMst permissionsMst);
	void insertPermissionsMsts(List<PermissionsMst> permissionsMsts);
	void insertPermissionsMst(PermissionsMst permissionsMst);
	void updatePermissionsMsts(List<PermissionsMst> permissionsMsts);
	void updatePermissionsMst(PermissionsMst permissionsMst);
	void deletePermissionsMstById(int id);
}

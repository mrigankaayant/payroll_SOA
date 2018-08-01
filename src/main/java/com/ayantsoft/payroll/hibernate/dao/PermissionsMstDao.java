package com.ayantsoft.payroll.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import com.ayantsoft.payroll.hibernate.pojo.PermissionsMst;

public interface PermissionsMstDao extends Serializable {
	List<PermissionsMst> getPermissionsMsts();
	PermissionsMst getPermissionsMstById(int id);
	List<PermissionsMst> getPermissionsMstsByProperties(PermissionsMst permissionsMst);
	void insertPermissionsMsts(List<PermissionsMst> permissionsMsts);
	void insertPermissionsMsts(PermissionsMst permissionsMst);
	void updatePermissionsMsts(List<PermissionsMst> permissionsMsts);
	void updatePermissionsMst(PermissionsMst permissionsMst);
	void deletePermissionsMstById(int id);
}

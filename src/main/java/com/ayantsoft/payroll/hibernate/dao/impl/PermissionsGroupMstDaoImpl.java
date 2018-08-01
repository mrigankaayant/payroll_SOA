package com.ayantsoft.payroll.hibernate.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ayantsoft.payroll.exception.DataAccessException;
import com.ayantsoft.payroll.exception.DataUpdationException;
import com.ayantsoft.payroll.hibernate.dao.PermissionsGroupMstDao;
import com.ayantsoft.payroll.hibernate.pojo.PermissionGroupMst;
import com.ayantsoft.payroll.hibernate.pojo.PermissionsMst;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;

@Repository
public class PermissionsGroupMstDaoImpl implements PermissionsGroupMstDao {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8598314934012045934L;

	private Logger log = Logger.getLogger(PermissionsGroupMstDaoImpl.class);

	@Override
	public List<PermissionGroupMst> getPermissionsGroupMsts() {
		List<PermissionGroupMst> permissionGroupMst = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(PermissionGroupMst.class, "PGM")
					.createAlias("PGM.permissionSubGroupMsts","PSGM")
					.createAlias("PSGM.permissionsMsts","PM");
			
			
			permissionGroupMst = criteria.list();

		}catch (HibernateException he) {
			log.error("getPermissionsGroup Msts Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return permissionGroupMst;
	}


}

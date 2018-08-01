package com.ayantsoft.payroll.hibernate.dao.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.ayantsoft.payroll.exception.DataAccessException;
import com.ayantsoft.payroll.hibernate.dao.LoginDao;
import com.ayantsoft.payroll.hibernate.pojo.UserMst;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;

@Repository
public class LoginDaoImpl implements LoginDao, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1632203223959018285L;
	
	private Logger log = Logger.getLogger(LoginDaoImpl.class);

	@Override
	public UserMst doLogin(UserMst userMst) {
		UserMst resultUserMst = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(""
					+ "FROM "
					+ "UserMst "
					
					+ "WHERE "
					+ "id.userId = :userId "
					+ "AND password = :password "
					+ "AND isActive = :isActive "
					+ "AND isDeleted = :isDeleted");
			
			query.setParameter("userId", userMst.getUserId())
					.setParameter("password", userMst.getPassword())
					.setParameter("isActive", true)
					.setParameter("isDeleted", false);
					
			resultUserMst = (UserMst) query.uniqueResult();
			session.getTransaction().commit();
		}catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
	    	throw new DataAccessException();
	    }finally {
			session.close();
		}
		return resultUserMst;
	}
}

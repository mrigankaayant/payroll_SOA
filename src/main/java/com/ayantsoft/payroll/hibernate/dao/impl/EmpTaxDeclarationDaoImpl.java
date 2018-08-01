package com.ayantsoft.payroll.hibernate.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.ayantsoft.payroll.exception.DataInsertionException;
import com.ayantsoft.payroll.hibernate.dao.EmpTaxDeclarationDao;
import com.ayantsoft.payroll.hibernate.pojo.EmpTaxDeclaration;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;

@Repository
public class EmpTaxDeclarationDaoImpl implements Serializable, EmpTaxDeclarationDao {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1836414579014535214L;
	
	private Logger log = Logger.getLogger(EmpTaxDeclarationDaoImpl.class);

	@Override
	public void insertEmpTaxDeclaration(EmpTaxDeclaration empTaxDeclaration){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			empTaxDeclaration.setIsActive(true);
			empTaxDeclaration.setIsDeleted(false);
			empTaxDeclaration.setCreatedDate(new Date());
			session.save(empTaxDeclaration);
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insert EmpTaxDeclaration insert exception : "+he);
			throw new DataInsertionException("Insert EmpTaxDeclaration excetion");

		}finally {
			session.close();
		}

	}

}

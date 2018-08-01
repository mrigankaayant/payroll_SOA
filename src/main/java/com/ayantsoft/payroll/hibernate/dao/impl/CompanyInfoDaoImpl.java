package com.ayantsoft.payroll.hibernate.dao.impl;

import java.io.Serializable;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.ayantsoft.payroll.exception.DataInsertionException;
import com.ayantsoft.payroll.hibernate.dao.CompanyInfoDao;
import com.ayantsoft.payroll.hibernate.pojo.CompanyInformation;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;

@Repository
public class CompanyInfoDaoImpl implements Serializable,CompanyInfoDao {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3716473021276346838L;
	
	private Logger log = Logger.getLogger(CompanyInfoDaoImpl.class);

	@Override
	public void insertCompanyInfo(CompanyInformation companyInformation) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			companyInformation.setIsActive("1");
			session.save(companyInformation);
			session.getTransaction().commit();

		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("Insert Company Info HIBERNATE ERROR :"+he);
			throw new DataInsertionException("Insert Company Info excetion");

		}finally {
			session.close();
		}
		
	}

	

}

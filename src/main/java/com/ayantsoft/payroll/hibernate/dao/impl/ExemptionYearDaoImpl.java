package com.ayantsoft.payroll.hibernate.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ayantsoft.payroll.exception.DataAccessException;
import com.ayantsoft.payroll.exception.DataInsertionException;
import com.ayantsoft.payroll.exception.DataUpdationException;
import com.ayantsoft.payroll.hibernate.dao.ExemptionYearDao;
import com.ayantsoft.payroll.hibernate.pojo.ExemptionYear;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;

@Repository
public class ExemptionYearDaoImpl implements ExemptionYearDao, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4053215116168511613L;
	
	private Logger log = Logger.getLogger(ExemptionYearDaoImpl.class);

	@Override
	public List<ExemptionYear> getExemptionYears() {
		List<ExemptionYear> exemptionYears = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Query query = session.createQuery(""
					+ "FROM "
					+ "ExemptionYear "
					
					+ "WHERE "
					+ "isDeleted = :isDeleted "
					
					+ "ORDER BY id")
					
					.setParameter("isDeleted", false);
			
			exemptionYears = query.list();
		}catch (HibernateException he) {
	    	log.error("getExemptionYears Error : ", he);
	    	throw new DataAccessException();
	    }finally {
			session.close();
		}
		return exemptionYears;
	}

	@Override
	public ExemptionYear getExemptionYearById(int id) {
		ExemptionYear exemptionYear = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Query query = session.createQuery(""
					+ "FROM "
					+ "ExemptionYear "
					
					+ "WHERE "
					+ "id = :id "
					+ "AND isDeleted = :isDeleted "
					
					+ "ORDER BY id")
			
					.setParameter("id", id)
					.setParameter("isDeleted", false);
			
			exemptionYear = (ExemptionYear) query.uniqueResult();
		}catch (HibernateException he) {
	    	log.error("getExemptionYearById Error : ", he);
	    	throw new DataAccessException();
	    }finally {
			session.close();
		}
		return exemptionYear;
	}

	@Override
	public List<ExemptionYear> getExemptionYearsByProperties(ExemptionYear exemptionYear) {
		List<ExemptionYear> exemptionYears = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(ExemptionYear.class);
			
			if(exemptionYear.getYear()>0){
				criteria.add(Restrictions.eq("year", exemptionYear.getYear()));
			}
			
			if(exemptionYear.getDescription() != null){
				criteria.add(Restrictions.eq("description", exemptionYear.getDescription()));
			}
            
            criteria.setProjection(Projections.projectionList()
            		.add(Projections.property("id"))
            		.add(Projections.property("year"))
            		.add(Projections.property("description"))
    				.add(Projections.property("isActive"))
    				.add(Projections.property("createdDate")));
			
            exemptionYears = criteria.list();
			
		}catch (HibernateException he) {
	    	log.error("getExemptionYearsByProperties Error : ", he);
	    	throw new DataAccessException();
	    }finally {
			session.close();
		}
		return exemptionYears;
	}

	@Override
	public void insertExemptionYears(List<ExemptionYear> exemptionYears) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			for(ExemptionYear obj : exemptionYears){
				obj.setCreatedDate(new Date());
				obj.setIsActive(true);
				obj.setIsDeleted(false);
				
				session.save(obj);
			}
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insertExemptionYears HibernateException : ", he);
			throw new DataInsertionException("ExemptionYear MULTIPLE INSERT ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void insertExemptionYear(ExemptionYear exemptionYear) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			exemptionYear.setCreatedDate(new Date());
			exemptionYear.setIsActive(true);
			exemptionYear.setIsDeleted(false);
			
			session.save(exemptionYear);
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insertExemptionYear HibernateException : ", he);
			throw new DataInsertionException("ExemptionYear SINGLE INSERT ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void updateExemptionYears(List<ExemptionYear> exemptionYears) {
		String hql = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{			
			session.beginTransaction();
			
			for(ExemptionYear obj : exemptionYears){
				hql = null;
				hql = "UPDATE "
						+ "ExemptionYear "
						
						+ "SET ";
				
				if(obj.getYear()>0){
					hql += "year = :year ";
				}
				
				if(obj.getDescription() != null){
					hql += ",description = :description ";
				}
				
				hql += "WHERE id = :id";
				
				Query query = session.createQuery(hql);
				
				if(obj.getYear()>0){
					query.setParameter("year", obj.getYear());
				}
				
				if(obj.getDescription() != null){
					query.setParameter("description", obj.getDescription());
				}
				
				//query.setParameter("id", obj.getId());
				
				query.executeUpdate();
			}
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("updateExemptionYears HibernateException : ", he);
			throw new DataUpdationException("ExemptionYear MULTIPLE UPDATE ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void updateExemptionYear(ExemptionYear exemptionYear) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{			
			session.beginTransaction();
			
			String hql = "UPDATE "
					+ "ExemptionYear "
					
					+ "SET ";
			
			if(exemptionYear.getYear()>0){
				hql += "year = :year ";
			}
			
			if(exemptionYear.getDescription() != null){
				hql += ",description = :description ";
			}
			
			hql += "WHERE id = :id";
			
			Query query = session.createQuery(hql);
			
			if(exemptionYear.getYear()>0){
				query.setParameter("year", exemptionYear.getYear());
			}
			
			if(exemptionYear.getDescription() != null){
				query.setParameter("description", exemptionYear.getDescription());
			}
			
			//query.setParameter("id", exemptionYear.getId());
			
			query.executeUpdate();
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("updateExemptionYear HibernateException : ", he);
			throw new DataUpdationException("ExemptionYear SINGLE UPDATE ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void deleteExemptionYearById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(""
					+ "UPDATE "
					+ "ExemptionYear "
					
					+ "SET "
					+ "isDeleted = :isDeleted "
					
					+ "WHERE "
					+ "id = :id");
			
			query.setParameter("isDeleted", true)
				 .setParameter("id", id);
			
			query.executeUpdate();
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("deleteExemptionYearById HibernateException : ", he);
			throw new DataUpdationException("ExemptionYear DELETE ERROR");
		}finally {
			session.close();
		}
	}
}

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
import com.ayantsoft.payroll.hibernate.dao.TaxPayerGroupMstDao;
import com.ayantsoft.payroll.hibernate.pojo.PayrollGroupMst;
import com.ayantsoft.payroll.hibernate.pojo.TaxPayerGroupMst;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;

@Repository
public class TaxPayerGroupMstDaoImpl implements Serializable, TaxPayerGroupMstDao {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8711167120805219193L;
	
	private Logger log = Logger.getLogger(TaxPayerGroupMstDaoImpl.class);

	@Override
	public List<TaxPayerGroupMst> getTaxPayerGroupMsts() {
		List<TaxPayerGroupMst> taxPayerGroupMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Query query = session.createQuery(""
					+ "FROM "
					+ "TaxPayerGroupMst "
					
					+ "WHERE "
					+ "isDeleted = :isDeleted "
					
					+ "ORDER BY id")
					
					.setParameter("isDeleted", false);
			
			taxPayerGroupMsts = query.list();
		}catch (HibernateException he) {
	    	log.error("getTaxPayerGroupMsts Error : ", he);
	    	throw new DataAccessException();
	    }finally {
			session.close();
		}
		return taxPayerGroupMsts;
	}

	@Override
	public TaxPayerGroupMst getTaxPayerGroupMstById(int id) {
		TaxPayerGroupMst taxPayerGroupMst = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Query query = session.createQuery(""
					+ "FROM "
					+ "TaxPayerGroupMst "
					
					+ "WHERE "
					+ "id = :id "
					+ "AND isDeleted = :isDeleted "
					
					+ "ORDER BY id")
			
					.setParameter("id", id)
					.setParameter("isDeleted", false);
			
			taxPayerGroupMst = (TaxPayerGroupMst) query.uniqueResult();
		}catch (HibernateException he) {
	    	log.error("getTaxPayerGroupMstById Error : ", he);
	    	throw new DataAccessException();
	    }finally {
			session.close();
		}
		return taxPayerGroupMst;
	}
	
	@Override
	public List<TaxPayerGroupMst> getTaxPayerGroupMstsByProperties(TaxPayerGroupMst taxPayerGroupMst) {
		List<TaxPayerGroupMst> taxPayerGroupMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(TaxPayerGroupMst.class);
			
			if(taxPayerGroupMst.getId() != null){
				criteria.add(Restrictions.eq("id", taxPayerGroupMst.getId()));
			}
			
			if(taxPayerGroupMst.getUpperLimitAge() > 0){
				criteria.add(Restrictions.eq("upperLimitAge", taxPayerGroupMst.getUpperLimitAge()));
			}
			
			if(taxPayerGroupMst.getLowerLimitAge() > 0){
				criteria.add(Restrictions.eq("lowerLimitAge", taxPayerGroupMst.getLowerLimitAge()));
			}
			
			if(taxPayerGroupMst.getGroupName() != null){
				criteria.add(Restrictions.eq("groupName", taxPayerGroupMst.getGroupName()));
			}
            
            criteria.setProjection(Projections.projectionList()
            		.add(Projections.property("id"))
            		.add(Projections.property("upperLimitAge"))
            		.add(Projections.property("lowerLimitAge"))
    				.add(Projections.property("groupName"))
    				.add(Projections.property("isActive"))
    				.add(Projections.property("createdDate")));
			
            taxPayerGroupMsts = criteria.list();
			
		}catch (HibernateException he) {
	    	log.error("getTaxPayerGroupMstsByProperties Error : ", he);
	    	throw new DataAccessException();
	    }finally {
			session.close();
		}
		return taxPayerGroupMsts;
	}

	@Override
	public void insertTaxPayerGroupMsts(List<TaxPayerGroupMst> taxPayerGroupMsts) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{	
			session.beginTransaction();
			
			for(TaxPayerGroupMst obj : taxPayerGroupMsts){
				obj.setCreatedDate(new Date());
				obj.setIsActive(true);
				obj.setIsDeleted(false);
				
				session.save(obj);
			}
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insertTaxPayerGroupMsts Error : ", he);
			throw new DataInsertionException("TaxPayerGroupMst MULTIPLE INSERT ERROR");
		}finally {
			if(session != null){
				session.close();
			}
		}
	}

	@Override
	public void insertTaxPayerGroupMst(TaxPayerGroupMst taxPayerGroupMst) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{	
			session.beginTransaction();
			
			taxPayerGroupMst.setCreatedDate(new Date());
			taxPayerGroupMst.setIsDeleted(false);
			taxPayerGroupMst.setIsActive(true);
			
			session.save(taxPayerGroupMst);
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insertTaxPayerGroupMst Error : ", he);
			throw new DataInsertionException("TaxPayerGroupMst SINGLE INSERT ERROR");
		}finally {
			if(session != null){
				session.close();
			}
		}
	}

	@Override
	public void updateTaxPayerGroupMsts(List<TaxPayerGroupMst> taxPayerGroupMsts) {
		String hql = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{			
			session.beginTransaction();
			
			for(TaxPayerGroupMst obj : taxPayerGroupMsts){
				hql = null;
				hql = "UPDATE "
						+ "TaxPayerGroupMst "
						
						+ "SET ";
				
				if(obj.getUpperLimitAge() > 0){
					hql += "upperLimitAge = :upperLimitAge ";
				}
				
				if(obj.getLowerLimitAge() > 0){
					hql += ",lowerLimitAge = :lowerLimitAge ";
				}
				
				if(obj.getGroupName() != null){
					hql += ",groupName = :groupName ";
				}
				
				hql += "WHERE id = :id";
				
				Query query = session.createQuery(hql);
				
				if(obj.getUpperLimitAge() > 0){
					query.setParameter("upperLimitAge", obj.getUpperLimitAge());
				}
				
				if(obj.getLowerLimitAge() > 0){
					query.setParameter("lowerLimitAge", obj.getLowerLimitAge());
				}
				
				if(obj.getGroupName() != null){
					query.setParameter("groupName", obj.getGroupName());
				}
				
				query.setParameter("id", obj.getId());
				
				query.executeUpdate();
			}
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("updateTaxPayerGroupMsts HibernateException : ", he);
			throw new DataUpdationException("TaxPayerGroupMst MULTIPLE UPDATE ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void updateTaxPayerGroupMst(TaxPayerGroupMst taxPayerGroupMst) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			String hql = "UPDATE "
					+ "TaxPayerGroupMst "
					
					+ "SET ";
			
			if(taxPayerGroupMst.getUpperLimitAge() > 0){
				hql += "upperLimitAge = :upperLimitAge ";
			}
			
			if(taxPayerGroupMst.getLowerLimitAge() > 0){
				hql += ",lowerLimitAge = :lowerLimitAge ";
			}
			
			if(taxPayerGroupMst.getGroupName() != null){
				hql += ",groupName = :groupName ";
			}
			
			hql += "WHERE id = :id";
			
			Query query = session.createQuery(hql);
			
			if(taxPayerGroupMst.getUpperLimitAge() > 0){
				query.setParameter("upperLimitAge", taxPayerGroupMst.getUpperLimitAge());
			}
			
			if(taxPayerGroupMst.getLowerLimitAge() > 0){
				query.setParameter("lowerLimitAge", taxPayerGroupMst.getLowerLimitAge());
			}
			
			if(taxPayerGroupMst.getGroupName() != null){
				query.setParameter("groupName", taxPayerGroupMst.getGroupName());
			}
			
			query.setParameter("id", taxPayerGroupMst.getId());
			
			query.executeUpdate();
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("updateTaxPayerGroupMst HibernateException : ", he);
			throw new DataUpdationException("TaxPayerGroupMst UPDATE ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void deleteTaxPayerGroupMstById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(""
					+ "UPDATE "
					+ "TaxPayerGroupMst "
					
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
			log.error("deleteTaxPayerGroupMstById HibernateException : ", he);
			throw new DataUpdationException("TaxPayerGroupMst DELETE ERROR");
		}finally {
			session.close();
		}
	}
}

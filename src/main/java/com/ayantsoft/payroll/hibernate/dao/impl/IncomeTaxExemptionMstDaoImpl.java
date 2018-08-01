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
import com.ayantsoft.payroll.hibernate.dao.IncomeTaxExemptionMstDao;
import com.ayantsoft.payroll.hibernate.pojo.IncomeTaxExemptionMst;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;

@Repository
public class IncomeTaxExemptionMstDaoImpl implements Serializable, IncomeTaxExemptionMstDao {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -122154485624978731L;
	
	private Logger log = Logger.getLogger(IncomeTaxExemptionMstDaoImpl.class);
	
	@Override
	public List<IncomeTaxExemptionMst> getIncomeTaxExemptionMsts() {
		List<IncomeTaxExemptionMst> incomeTaxExemptionMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Query query = session.createQuery(""
					+ "FROM "
					+ "IncomeTaxExemptionMst "
					
					+ "WHERE "
					+ "isDeleted = :isDeleted "
					
					+ "ORDER BY id")
					
					.setParameter("isDeleted", false);
			
			incomeTaxExemptionMsts = query.list();
		}catch (HibernateException he) {
	    	log.error("getIncomeTaxExemptionMsts Error : ", he);
	    	throw new DataAccessException();
	    }finally {
			session.close();
		}
		return incomeTaxExemptionMsts;
	}
	
	@Override
	public IncomeTaxExemptionMst getIncomeTaxExemptionMstById(int id) {
		IncomeTaxExemptionMst incomeTaxExemptionMst = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Query query = session.createQuery(""
					+ "FROM "
					+ "IncomeTaxExemptionMst "
					
					+ "WHERE "
					+ "id = :id "
					+ "AND isDeleted = :isDeleted "
					
					+ "ORDER BY id")
			
					.setParameter("id", id)
					.setParameter("isDeleted", false);
			
			incomeTaxExemptionMst = (IncomeTaxExemptionMst) query.uniqueResult();
		}catch (HibernateException he) {
	    	log.error("getIncomeTaxExemptionMstById Error : ", he);
	    	throw new DataAccessException();
	    }finally {
			session.close();
		}
		return incomeTaxExemptionMst;
	}

	@Override
	public List<IncomeTaxExemptionMst> getIncomeTaxExemptionMstsByProperties(IncomeTaxExemptionMst incomeTaxExemptionMst) {
		List<IncomeTaxExemptionMst> incomeTaxExemptionMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(IncomeTaxExemptionMst.class);
			
			if(incomeTaxExemptionMst.getId() != null){
				criteria.add(Restrictions.eq("id", incomeTaxExemptionMst.getId()));
			}
			
			if(incomeTaxExemptionMst.getSection() != null){
				criteria.add(Restrictions.eq("section", incomeTaxExemptionMst.getSection()));
			}
			
			if(incomeTaxExemptionMst.getParticulars() != null){
				criteria.add(Restrictions.eq("particulars", incomeTaxExemptionMst.getParticulars()));
			}
			
			if(incomeTaxExemptionMst.getExemption() != null){
				criteria.add(Restrictions.eq("exemption", incomeTaxExemptionMst.getExemption()));
			}
			
			if(incomeTaxExemptionMst.getGender() != null){
				criteria.add(Restrictions.eq("gender", incomeTaxExemptionMst.getGender()));
			}
			
			if(incomeTaxExemptionMst.getPercentageOrFixed() != null){
				criteria.add(Restrictions.eq("percentageOrFixed", incomeTaxExemptionMst.getPercentageOrFixed()));
			}
			
			if(incomeTaxExemptionMst.getHigherLimitAmount() != null){
				criteria.add(Restrictions.eq("higherLimitAmount", incomeTaxExemptionMst.getHigherLimitAmount()));
			}
			
			if(incomeTaxExemptionMst.getOrderIndex() > 0){
				criteria.add(Restrictions.eq("orderIndex", incomeTaxExemptionMst.getOrderIndex()));
			}
            
            criteria.setProjection(Projections.projectionList()
            		.add(Projections.property("id"))
            		.add(Projections.property("section"))
            		.add(Projections.property("particulars"))            		
            		.add(Projections.property("exemption"))
            		.add(Projections.property("gender"))
            		.add(Projections.property("percentageOrFixed"))
            		.add(Projections.property("higherLimitAmount"))
            		.add(Projections.property("orderIndex"))            		
    				.add(Projections.property("isActive"))
    				.add(Projections.property("createdDate")));
			
            incomeTaxExemptionMsts = criteria.list();			
		}catch (HibernateException he) {
	    	log.error("getIncomeTaxExemptionMstsByProperties Error : ", he);
	    	throw new DataAccessException();
	    }finally {
			session.close();
		}
		return incomeTaxExemptionMsts;
	}
	
	@Override
	public void insertIncomeTaxExemptionMsts(List<IncomeTaxExemptionMst> incomeTaxExemptionMsts) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			for(IncomeTaxExemptionMst obj : incomeTaxExemptionMsts){
				obj.setCreatedDate(new Date());
				obj.setIsActive(true);
				obj.setIsDeleted(false);
				
				session.save(obj);
			}
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insertIncomeTaxExemptionMsts HibernateException : ", he);
			throw new DataInsertionException("IncomeTaxExemptionMst MULTIPLE INSERT ERROR");
		}finally {
			session.close();
		}
	}
	
	@Override
	public void insertIncomeTaxExemptionMst(IncomeTaxExemptionMst incomeTaxExemptionMst) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			
			incomeTaxExemptionMst.setCreatedDate(new Date());
			incomeTaxExemptionMst.setIsActive(true);
			incomeTaxExemptionMst.setIsDeleted(false);
			
			session.save(incomeTaxExemptionMst);
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insertIncomeTaxExemptionMst HibernateException : ", he);
			throw new DataInsertionException("IncomeTaxExemptionMst INSERT ERROR");
		}finally {
			session.close();
		}
	}
	
	@Override
	public void updateIncomeTaxExemptionMsts(List<IncomeTaxExemptionMst> incomeTaxExemptionMsts) {
		String hql = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{			
			session.beginTransaction();
			
			for(IncomeTaxExemptionMst obj : incomeTaxExemptionMsts){
				hql = null;
				hql = "UPDATE "
						+ "IncomeTaxExemptionMst "
						
						+ "SET ";
				
				if(obj.getSection() != null){
					hql += "section = :section ";
				}
				
				if(obj.getParticulars() != null){
					hql += ",particulars = :particulars ";
				}
				
				if(obj.getExemption() != null){
					hql += ",exemption = :exemption ";
				}
				
				if(obj.getGender() != null){
					hql += ",gender = :gender ";
				}
				
				if(obj.getPercentageOrFixed() != null){
					hql += ",percentageOrFixed = :percentageOrFixed ";
				}
				
				if(obj.getHigherLimitAmount() != null){
					hql += ",higherLimitAmount = :higherLimitAmount ";
				}
				
				if(obj.getOrderIndex() > 0){
					hql += ",orderIndex = :orderIndex ";
				}
				
				hql += "WHERE id = :id";
				
				Query query = session.createQuery(hql);
				
				if(obj.getSection() != null){
					query.setParameter("section", obj.getSection());
				}
				
				if(obj.getParticulars() != null){
					query.setParameter("particulars", obj.getParticulars());
				}
				
				if(obj.getExemption() != null){
					query.setParameter("exemption", obj.getExemption());
				}
				
				if(obj.getGender() != null){
					query.setParameter("gender", obj.getGender());
				}
				
				if(obj.getPercentageOrFixed() != null){
					query.setParameter("percentageOrFixed", obj.getPercentageOrFixed());
				}
				
				if(obj.getHigherLimitAmount() != null){
					query.setParameter("higherLimitAmount", obj.getHigherLimitAmount());
				}
				
				if(obj.getOrderIndex() > 0){
					query.setParameter("orderIndex", obj.getOrderIndex());
				}
				
				query.setParameter("id", obj.getId());
				
				query.executeUpdate();
			}
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("updateIncomeTaxExemptionMsts HibernateException : ", he);
			throw new DataUpdationException("IncomeTaxExemptionMst MULTIPLE UPDATE ERROR");
		}finally {
			session.close();
		}
	}
	
	@Override
	public void updateIncomeTaxExemptionMst(IncomeTaxExemptionMst incomeTaxExemptionMst) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{			
			session.beginTransaction();
			
			String hql = "UPDATE "
					+ "IncomeTaxExemptionMst "
					
					+ "SET ";
			
			if(incomeTaxExemptionMst.getSection() != null){
				hql += "section = :section ";
			}
			
			if(incomeTaxExemptionMst.getParticulars() != null){
				hql += ",particulars = :particulars ";
			}
			
			if(incomeTaxExemptionMst.getExemption() != null){
				hql += ",exemption = :exemption ";
			}
			
			if(incomeTaxExemptionMst.getGender() != null){
				hql += ",gender = :gender ";
			}
			
			if(incomeTaxExemptionMst.getPercentageOrFixed() != null){
				hql += ",percentageOrFixed = :percentageOrFixed ";
			}
			
			if(incomeTaxExemptionMst.getHigherLimitAmount() != null){
				hql += ",higherLimitAmount = :higherLimitAmount ";
			}
			
			if(incomeTaxExemptionMst.getOrderIndex() > 0){
				hql += ",orderIndex = :orderIndex ";
			}
			
			hql += "WHERE id = :id";
			
			Query query = session.createQuery(hql);
			
			if(incomeTaxExemptionMst.getSection() != null){
				query.setParameter("section", incomeTaxExemptionMst.getSection());
			}
			
			if(incomeTaxExemptionMst.getParticulars() != null){
				query.setParameter("particulars", incomeTaxExemptionMst.getParticulars());
			}
			
			if(incomeTaxExemptionMst.getExemption() != null){
				query.setParameter("exemption", incomeTaxExemptionMst.getExemption());
			}
			
			if(incomeTaxExemptionMst.getGender() != null){
				query.setParameter("gender", incomeTaxExemptionMst.getGender());
			}
			
			if(incomeTaxExemptionMst.getPercentageOrFixed() != null){
				query.setParameter("percentageOrFixed", incomeTaxExemptionMst.getPercentageOrFixed());
			}
			
			if(incomeTaxExemptionMst.getHigherLimitAmount() != null){
				query.setParameter("higherLimitAmount", incomeTaxExemptionMst.getHigherLimitAmount());
			}
			
			if(incomeTaxExemptionMst.getOrderIndex() > 0){
				query.setParameter("orderIndex", incomeTaxExemptionMst.getOrderIndex());
			}
			
			query.setParameter("id", incomeTaxExemptionMst.getId());
			
			query.executeUpdate();
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("updateIncomeTaxExemptionMst HibernateException : ", he);
			throw new DataUpdationException("IncomeTaxExemptionMst SINGLE UPDATE ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void deleteIncomeTaxExemptionMstById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(""
					+ "UPDATE "
					+ "IncomeTaxExemptionMst "
					
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
			he.printStackTrace();
			throw new DataUpdationException("IncomeTaxExemptionMst DELETE ERROR");
		}finally {
			session.close();
		}
	}
}
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
import com.ayantsoft.payroll.hibernate.dao.StateOfCountryMstDao;
import com.ayantsoft.payroll.hibernate.pojo.ProfessionalTaxMst;
import com.ayantsoft.payroll.hibernate.pojo.StateOfCountryMst;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;

@Repository
public class StateOfCountryMstDaoImpl implements StateOfCountryMstDao, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8746527075590930302L;
	
	private Logger log = Logger.getLogger(StateOfCountryMstDaoImpl.class);

	@Override
	public List<StateOfCountryMst> getStateOfCountryMsts() {
		List<StateOfCountryMst> stateOfCountryMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Query query = session.createQuery(""
					+ "FROM "
					+ "StateOfCountryMst "
					
					+ "WHERE "
					+ "isDeleted = :isDeleted "
					
					+ "ORDER BY id")
					
					.setParameter("isDeleted", false);
			
			stateOfCountryMsts = query.list();
		}catch (HibernateException he) {
	    	log.error("getStateOfCountryMsts Error : ", he);
	    	throw new DataAccessException();
	    }finally {
			session.close();
		}
		return stateOfCountryMsts;
	}

	@Override
	public StateOfCountryMst getStateOfCountryMstById(int id) {
		StateOfCountryMst stateOfCountryMst = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Query query = session.createQuery(""
					+ "FROM "
					+ "StateOfCountryMst "
					
					+ "WHERE "
					+ "id = :id "
					+ "AND isDeleted = :isDeleted "
					
					+ "ORDER BY id")
					
					.setParameter("isDeleted", false);
			
			query.setParameter("id", id);
			
			stateOfCountryMst = (StateOfCountryMst) query.uniqueResult();
		}catch (HibernateException he) {
	    	log.error("getStateOfCountryMstById Error : ", he);
	    	throw new DataAccessException();
	    }finally {
			session.close();
		}
		return stateOfCountryMst;
	}
	
	@Override
	public List<StateOfCountryMst> getStateOfCountryMstsByProperties(StateOfCountryMst stateOfCountryMst) {
		List<StateOfCountryMst> stateOfCountryMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(StateOfCountryMst.class);
			
			if(stateOfCountryMst.getId() != null){
				criteria.add(Restrictions.eq("id", stateOfCountryMst.getId()));
			}
			
			if(stateOfCountryMst.getStateName() != null){
				criteria.add(Restrictions.eq("stateName", stateOfCountryMst.getStateName()));
			}
			
			if(stateOfCountryMst.getCountryCode() != null){
				criteria.add(Restrictions.eq("countryCode", stateOfCountryMst.getCountryCode()));
			}
            
            criteria.setProjection(Projections.projectionList()
            		.add(Projections.property("id"))
            		.add(Projections.property("stateName"))
            		.add(Projections.property("countryCode"))
    				.add(Projections.property("isActive"))
    				.add(Projections.property("createdDate")));
			
            stateOfCountryMsts = criteria.list();			
		}catch (HibernateException he) {
	    	log.error("getStateOfCountryMstsByProperties Error : ", he);
	    	throw new DataAccessException();
	    }finally {
			session.close();
		}
		return stateOfCountryMsts;
	}

	@Override
	public void insertStateOfCountryMsts(List<StateOfCountryMst> stateOfCountryMsts) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			for(StateOfCountryMst obj : stateOfCountryMsts){
				obj.setCreatedDate(new Date());
				obj.setIsActive(true);
				obj.setIsDeleted(false);
				
				session.save(obj);
			}
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insertStateOfCountryMsts HibernateException : ", he);
			throw new DataInsertionException("StateOfCountryMst MULTIPLE INSERT ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void insertStateOfCountryMst(StateOfCountryMst stateOfCountryMst) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			
			stateOfCountryMst.setCreatedDate(new Date());
			stateOfCountryMst.setIsActive(true);
			stateOfCountryMst.setIsDeleted(false);
			
			session.save(stateOfCountryMst);
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insertStateOfCountryMst HibernateException : ", he);
			throw new DataInsertionException("StateOfCountryMst INSERT ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void updateStateOfCountryMsts(List<StateOfCountryMst> stateOfCountryMsts) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{			
			session.beginTransaction();
			
			for(StateOfCountryMst obj : stateOfCountryMsts){
				Query query = session.createQuery(""
						
						+ "UPDATE "
						+ "StateOfCountryMst "
						
						+ "SET "
						
						+ "stateName = :stateName,"
						+ "countryCode = :countryCode "
						
						+ "WHERE "
						+ "id = :id"
						);
						
					query.setParameter("stateName", obj.getStateName())
						 .setParameter("countryCode", obj.getCountryCode())
						 .setParameter("id", obj.getId());
				
				query.executeUpdate();
			}
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("updateStateOfCountryMsts HibernateException : ", he);
			throw new DataUpdationException("StateOfCountryMst MULTIPLE UPDATE ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void updateStateOfCountryMst(StateOfCountryMst stateOfCountryMst) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{			
			session.beginTransaction();
			
			Query query = session.createQuery(""
					
					+ "UPDATE "
					+ "StateOfCountryMst "
					
					+ "SET "
					+ "stateName = :stateName, "
			        + "countryCode = :countryCode "
					
					+ " WHERE "
					+ "id = :id");
			
					query.setParameter("stateName", stateOfCountryMst.getStateName())
						 .setParameter("countryCode", stateOfCountryMst.getCountryCode())
						 .setParameter("id", stateOfCountryMst.getId());
			
			query.executeUpdate();
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("updateStateOfCountryMst HibernateException : ", he);
			throw new DataUpdationException("StateOfCountryMst SINGLE UPDATE ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void deleteStateOfCountryMstById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(""
					
					+ "UPDATE "
					+ "StateOfCountryMst "
					
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
			log.error("deleteStateOfCountryMstById HibernateException : ", he);
			throw new DataUpdationException("StateOfCountryMst DELETE ERROR");
		}finally {
			session.close();
		}
	}
}
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
import com.ayantsoft.payroll.hibernate.dao.PermissionsMstDao;
import com.ayantsoft.payroll.hibernate.pojo.PermissionsMst;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;

@Repository
public class PermissionsMstDaoImpl implements PermissionsMstDao, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8598314934012045934L;
	
	private Logger log = Logger.getLogger(PermissionsMstDaoImpl.class);

	@Override
	public List<PermissionsMst> getPermissionsMsts() {
		List<PermissionsMst> permissionsMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Query query = session.createQuery(""
					+ "FROM "
					+ "PermissionsMst "
					
					+ "WHERE "
					+ "isDeleted = :isDeleted "
					
					+ "ORDER BY id")
					
					.setParameter("isDeleted", false);
			
			permissionsMsts = query.list();
		}catch (HibernateException he) {
	    	log.error("getPermissionsMsts Error : ", he);
	    	throw new DataAccessException();
	    }finally {
			session.close();
		}
		return permissionsMsts;
	}

	@Override
	public PermissionsMst getPermissionsMstById(int id) {
		PermissionsMst permissionsMst = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Query query = session.createQuery(""
					+ "FROM "
					+ "PermissionsMst "
					
					+ "WHERE "
					+ "id = :id "
					+ "AND isDeleted = :isDeleted "
					
					+ "ORDER BY id")
			
					.setParameter("id", id)
					.setParameter("isDeleted", false);
			
			permissionsMst = (PermissionsMst) query.uniqueResult();
		}catch (HibernateException he) {
	    	log.error("getPermissionsMstById Error : ", he);
	    	throw new DataAccessException();
	    }finally {
			session.close();
		}
		return permissionsMst;
	}

	@Override
	public List<PermissionsMst> getPermissionsMstsByProperties(PermissionsMst permissionsMst) {
		List<PermissionsMst> permissionsMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(PermissionsMst.class);
			
			if(permissionsMst.getId() != null){
				criteria.add(Restrictions.eq("id", permissionsMst.getId()));
			}
			
			if(permissionsMst.getPermission() != null){
				criteria.add(Restrictions.eq("permissionName", permissionsMst.getPermission()));
			}
			
			if(permissionsMst.getDescription() != null){
				criteria.add(Restrictions.eq("description", permissionsMst.getDescription()));
			}
			
			if(permissionsMst.getName() != null){
				criteria.add(Restrictions.eq("menuLabel", permissionsMst.getName()));
			}
			
			if(permissionsMst.getActionUrl() != null){
				criteria.add(Restrictions.eq("action", permissionsMst.getActionUrl()));
			}
            
            criteria.setProjection(Projections.projectionList()
            		.add(Projections.property("id"))
            		.add(Projections.property("permissionName"))
            		.add(Projections.property("description"))            		
            		.add(Projections.property("menuLabel"))
            		.add(Projections.property("action"))            		
    				.add(Projections.property("isActive"))
    				.add(Projections.property("createdDate")));
			
            permissionsMsts = criteria.list();
			
		}catch (HibernateException he) {
	    	log.error("getPayrollGroupMstsByProperties Error : ", he);
	    	throw new DataAccessException();
	    }finally {
			session.close();
		}
		return permissionsMsts;
	}

	@Override
	public void insertPermissionsMsts(List<PermissionsMst> permissionsMsts) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			for(PermissionsMst obj : permissionsMsts){
				obj.setCreatedDate(new Date());
				obj.setIsActive(true);
				obj.setIsDeleted(false);
				
				session.save(obj);
			}
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insertPermissionsMsts HibernateException : ", he);
			throw new DataInsertionException("PermissionsMst MULTIPLE INSERT ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void insertPermissionsMsts(PermissionsMst permissionsMst) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			
			permissionsMst.setCreatedDate(new Date());
			permissionsMst.setIsActive(true);
			permissionsMst.setIsDeleted(false);
			
			session.save(permissionsMst);
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insertPermissionsMsts HibernateException : ", he);
			throw new DataInsertionException("PermissionsMst INSERT ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void updatePermissionsMsts(List<PermissionsMst> permissionsMsts) {
		String hql = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{			
			session.beginTransaction();
			
			for(PermissionsMst obj : permissionsMsts){
				hql = null;
				hql = "UPDATE "
						+ "PermissionsMst "
						
						+ "SET ";
				
				if(obj.getPermission() != null){
					hql += "permissionName = :permissionName ";
				}
				
				if(obj.getDescription() != null){
					hql += ",description = :description ";
				}
				
				if(obj.getName() != null){
					hql += "menuLabel = :menuLabel ";
				}
				
				if(obj.getActionUrl()!= null){
					hql += ",action = :action ";
				}
				
				hql += "WHERE id = :id";
				
				Query query = session.createQuery(hql);
				
				if(obj.getPermission()!= null){
					query.setParameter("permissionName", obj.getPermission());
				}
				
				if(obj.getDescription() != null){
					query.setParameter("description", obj.getDescription());
				}
				
				if(obj.getName() != null){
					query.setParameter("menuLabel", obj.getName());
				}
				
				if(obj.getActionUrl() != null){
					query.setParameter("action", obj.getActionUrl());
				}
				
				query.setParameter("id", obj.getId());
				
				query.executeUpdate();
			}
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("updatePermissionsMsts HibernateException : ", he);
			throw new DataUpdationException("PermissionsMst MULTIPLE UPDATE ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void updatePermissionsMst(PermissionsMst permissionsMst) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{			
			session.beginTransaction();

			String hql = "UPDATE "
					+ "PermissionsMst "
					
					+ "SET ";
			
			if(permissionsMst.getPermission() != null){
				hql += "permissionName = :permissionName ";
			}
			
			if(permissionsMst.getDescription() != null){
				hql += ",description = :description ";
			}
			
			if(permissionsMst.getName() != null){
				hql += "menuLabel = :menuLabel ";
			}
			
			if(permissionsMst.getActionUrl() != null){
				hql += ",action = :action ";
			}
			
			hql += "WHERE id = :id";
			
			Query query = session.createQuery(hql);
			
			if(permissionsMst.getName() != null){
				query.setParameter("permissionName", permissionsMst.getName());
			}
			
			if(permissionsMst.getDescription() != null){
				query.setParameter("description", permissionsMst.getDescription());
			}
			
			if(permissionsMst.getName() != null){
				query.setParameter("menuLabel", permissionsMst.getName());
			}
			
			if(permissionsMst.getActionUrl() != null){
				query.setParameter("action", permissionsMst.getActionUrl());
			}
			
			query.setParameter("id", permissionsMst.getId());
			
			query.executeUpdate();
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("updatePermissionsMst HibernateException : ", he);
			throw new DataUpdationException("PermissionsMst SINGLE UPDATE ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void deletePermissionsMstById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(""
					+ "UPDATE "
					+ "PermissionsMst "
					
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
			log.error("deletePermissionsMstById HibernateException : ", he);
			throw new DataUpdationException("PermissionsMst DELETE ERROR");
		}finally {
			session.close();
		}
	}
}

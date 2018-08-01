package com.ayantsoft.payroll.hibernate.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ayantsoft.payroll.exception.DataAccessException;
import com.ayantsoft.payroll.exception.DataInsertionException;
import com.ayantsoft.payroll.exception.DataUpdationException;
import com.ayantsoft.payroll.hibernate.dao.ProfessionalTaxMstDao;
import com.ayantsoft.payroll.hibernate.pojo.CityMst;
import com.ayantsoft.payroll.hibernate.pojo.ProfessionalTaxMst;
import com.ayantsoft.payroll.hibernate.pojo.StateOfCountryMst;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;

@Repository
public class ProfessionalTaxMstDaoImpl implements Serializable, ProfessionalTaxMstDao {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3636162805838918723L;

	private Logger log = Logger.getLogger(ProfessionalTaxMstDaoImpl.class);

	@Override
	public List<ProfessionalTaxMst> getProfessionalTaxMsts() {
		List<ProfessionalTaxMst> professionalTaxMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(ProfessionalTaxMst.class, "PTM")
					.createAlias("PTM.stateOfCountryMst", "SOCM")
					.add(Restrictions.eq("PTM.isDeleted", false))
					.add(Restrictions.eq("SOCM.isDeleted", false))
					.addOrder(Order.asc("PTM.id"));

			professionalTaxMsts = criteria.list();
			System.out.println("============ "+professionalTaxMsts.size());
		}catch (HibernateException he) {
			log.error("getProfessionalTaxMsts Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return professionalTaxMsts;
	}

	@Override
	public ProfessionalTaxMst getProfessionalTaxMstById(int id) {
		ProfessionalTaxMst professionalTaxMst = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(ProfessionalTaxMst.class, "PTM")
					.createAlias("PTM.stateOfCountryMst", "SOCM")

					.add(Restrictions.eq("PTM.id", id))
					.add(Restrictions.eq("PTM.isDeleted", false))
					.add(Restrictions.eq("SOCM.isDeleted", false))

					.addOrder(Order.asc("PTM.id"));

			professionalTaxMst = (ProfessionalTaxMst) criteria.uniqueResult();
		}catch (HibernateException he) {
			log.error("getProfessionalTaxMstById Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return professionalTaxMst;
	}

	@Override
	public List<ProfessionalTaxMst> getProfessionalTaxMstsByProperties(ProfessionalTaxMst professionalTaxMst) {
		List<ProfessionalTaxMst> professionalTaxMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(ProfessionalTaxMst.class, "PTM")
					.createAlias("PTM.stateOfCountryMst", "SOCM");

			if(professionalTaxMst.getId() != null){
				criteria.add(Restrictions.eq("PTM.id", professionalTaxMst.getId()));
			}

			if(professionalTaxMst.getStateOfCountryMst().getId() != null){
				criteria.add(Restrictions.eq("SOCM.id", professionalTaxMst.getStateOfCountryMst().getId()));
			}

			if(professionalTaxMst.getUpperLimitAmount() != null){
				criteria.add(Restrictions.eq("PTM.upperLimitAmount", professionalTaxMst.getUpperLimitAmount()));
			}

			if(professionalTaxMst.getLowerLimitAmount() != null){
				criteria.add(Restrictions.eq("PTM.lowerLimitAmount", professionalTaxMst.getLowerLimitAmount()));
			}

			if(professionalTaxMst.getTaxAmount() > 0){
				criteria.add(Restrictions.eq("PTM.taxAmount", professionalTaxMst.getTaxAmount()));
			}

			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("PTM.id"))
					.add(Projections.property("SOCM.id"))
					.add(Projections.property("PTM.upperLimitAmount"))
					.add(Projections.property("PTM.lowerLimitAmount"))
					.add(Projections.property("PTM.taxAmount"))
					.add(Projections.property("PTM.isActive"))
					.add(Projections.property("PTM.createdDate")));

			professionalTaxMsts = criteria.list();			
		}catch (HibernateException he) {
			log.error("getProfessionalTaxMstsByProperties Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return professionalTaxMsts;
	}

	@Override
	public void insertProfessionalTaxMsts(List<ProfessionalTaxMst> professionalTaxMsts) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();			

			for(ProfessionalTaxMst obj : professionalTaxMsts){								
				obj.setCreatedDate(new Date());
				obj.setIsActive(true);
				obj.setIsDeleted(false);

				session.save(obj);
			}

			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insertProfessionalTaxMsts HibernateException : ", he);
			throw new DataInsertionException("ProfessionalTaxMst MULTIPLE INSERT ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void insertProfessionalTaxMst(ProfessionalTaxMst professionalTaxMst) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();		

			professionalTaxMst.setCreatedDate(new Date());
			professionalTaxMst.setIsActive(true);
			professionalTaxMst.setIsDeleted(false);

			session.save(professionalTaxMst);

			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insertProfessionalTaxMst HibernateException : ", he);
			throw new DataInsertionException("ProfessionalTaxMst INSERT ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void updateProfessionalTaxMsts(List<ProfessionalTaxMst> professionalTaxMsts) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{			
			session.beginTransaction();

			for(ProfessionalTaxMst obj : professionalTaxMsts){
				Query query= session.createQuery(""
					+ " UPDATE "
					+ " ProfessionalTaxMst "
					+ " SET "

					+ "stateOfCountryMst.id = :stateOfCountryMstId, "
					+ "upperLimitAmount = :upperLimitAmount, "
					+ "lowerLimitAmount = :lowerLimitAmount, "
					+ "taxAmount = :taxAmount" 

					+ " WHERE "
					+ "id = :id");

				query.setParameter("stateOfCountryMstId", obj.getStateOfCountryMst().getId())
				.setParameter("upperLimitAmount",obj.getUpperLimitAmount())
				.setParameter("lowerLimitAmount", obj.getLowerLimitAmount())
				.setParameter("taxAmount", obj.getTaxAmount())
				.setParameter("id", obj.getId());

				query.executeUpdate();
			}

			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("updateProfessionalTaxMsts HibernateException : ", he);
			throw new DataUpdationException("ProfessionalTaxMst MULTIPLE UPDATE ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void updateProfessionalTaxMst(ProfessionalTaxMst professionalTaxMsts) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{			
			session.beginTransaction();

			Query query= session.createQuery(""
					+ " UPDATE "
					+ " ProfessionalTaxMst "
					+ " SET "

					+ "stateOfCountryMst.id = :stateOfCountryMstId, "
					+ "upperLimitAmount = :upperLimitAmount, "
					+ "lowerLimitAmount = :lowerLimitAmount, "
					+ "taxAmount = :taxAmount" 

					+ " WHERE "
					+ "id = :id");
					


			query.setParameter("stateOfCountryMstId", professionalTaxMsts.getStateOfCountryMst().getId())
			.setParameter("upperLimitAmount",professionalTaxMsts.getUpperLimitAmount())
			.setParameter("lowerLimitAmount", professionalTaxMsts.getLowerLimitAmount())
			.setParameter("taxAmount", professionalTaxMsts.getTaxAmount())
			.setParameter("id", professionalTaxMsts.getId());

			query.executeUpdate();

			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("updateProfessionalTaxMst HibernateException : ", he);
			throw new DataUpdationException("ProfessionalTaxMst SINGLE UPDATE ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void deleteProfessionalTaxMstById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(""
					+ "UPDATE "
					+ "ProfessionalTaxMst "

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
			log.error("deleteProfessionalTaxMstById HibernateException : ", he);
			throw new DataUpdationException("ProfessionalTaxMst DELETE ERROR");
		}finally {
			session.close();
		}
	}
}

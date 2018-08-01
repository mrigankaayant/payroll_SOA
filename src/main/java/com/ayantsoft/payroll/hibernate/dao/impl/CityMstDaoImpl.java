package com.ayantsoft.payroll.hibernate.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ayantsoft.payroll.exception.DataAccessException;
import com.ayantsoft.payroll.hibernate.dao.CityMstDao;
import com.ayantsoft.payroll.hibernate.pojo.CityMst;
import com.ayantsoft.payroll.hibernate.pojo.StateMst;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;

@Repository
public class CityMstDaoImpl implements Serializable, CityMstDao {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8761686551618672194L;

	private Logger log = Logger.getLogger(CityMstDaoImpl.class);

	/*@Override
	public List<CityMst> getCityMsts() {
		List<CityMst> cityMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(CityMst.class, "CM")
					.add(Restrictions.eq("CM.isActive", true))
					.add(Restrictions.eq("CM.isDeleted", false))
					.add(Restrictions.eq("stateMstId", 150));
			
			 Criteria criteria = session.createCriteria(CityMst.class, "CM")
			.add(Restrictions.eq("CM.isActive", true))
			.add(Restrictions.eq("CM.isDeleted", false));

			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("id"))
					.add(Projections.property("cityName")));


			criteria.addOrder(Order.asc("CM.id"));

			cityMsts = criteria.list();
			log.info("the size value is "+cityMsts.size());
		}catch (HibernateException he) {
			log.error("getCityMsts Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return cityMsts;
	}*/
	
	
	@Override
	public List<CityMst> getCityMsts() {
		List<CityMst> stateMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(CityMst.class, "CM")
					.createAlias("CM.stateMst", "SM")
					.add(Restrictions.eq("CM.isDeleted", false))
					.add(Restrictions.eq("SM.isDeleted", false))
					.addOrder(Order.asc("CM.id"));

			stateMsts = criteria.list();
		}catch (HibernateException he) {
			log.error("getStateMsts Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return stateMsts;
	}
	
	

	@Override
	public CityMst getCityMstById(int id) {
		CityMst cityMst = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(CityMst.class, "CM")
					.createAlias("CM.stateMst", "SM")
					.createAlias("SM.countryMst", "COM")

					.add(Restrictions.eq("CM.id", id))
					.add(Restrictions.eq("CM.isActive", true))
					.add(Restrictions.eq("SM.isActive", true))
					.add(Restrictions.eq("COM.isActive", true))

					.addOrder(Order.asc("CM.id"));

			cityMst = (CityMst) criteria.uniqueResult();
		}catch (HibernateException he) {
			log.error("getCityMstById Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return cityMst;
	}

	/*@Override
	public List<CityMst> getCityByStateId(int id) {
		List<CityMst > CityMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(CityMst.class)

					.add(Restrictions.eq("stateMstId", id))
					.add(Restrictions.eq("isActive", true))
					.add(Restrictions.eq("isDeleted", false))

					.addOrder(Order.asc("id"));

			CityMsts = criteria.list();
		}catch (HibernateException he) {
			log.error("getCityMstById Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return CityMsts;
	}*/
	
	
	@Override
	public List<CityMst> getCityByStateId(int id) {
		List<CityMst> cityMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(CityMst.class, "CM")
					.add(Restrictions.eq("CM.stateMst.id", id))
					.add(Restrictions.eq("CM.isActive", true))
					.add(Restrictions.eq("CM.isDeleted", false))
					
					.addOrder(Order.asc("CM.id"));

			cityMsts =  criteria.list();
		}catch (HibernateException he) {
			log.error("getCityByStateId Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return cityMsts;
	}
	
	
	
	
	
	
}

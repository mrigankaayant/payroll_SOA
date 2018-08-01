package com.ayantsoft.payroll.hibernate.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ayantsoft.payroll.exception.DataAccessException;
import com.ayantsoft.payroll.hibernate.dao.StateMstDao;
import com.ayantsoft.payroll.hibernate.pojo.StateMst;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;

@Repository
public class StateMstDaoImpl implements Serializable, StateMstDao {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5236677851622725296L;

	private Logger log = Logger.getLogger(StateMstDaoImpl.class);

	@Override
	public List<StateMst> getStateMsts() {
		List<StateMst> stateMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(StateMst.class, "SM")
					.createAlias("SM.countryMst", "COM")

					.add(Restrictions.eq("SM.isDeleted", false))
					.add(Restrictions.eq("COM.isDeleted", false))

					.addOrder(Order.asc("SM.id"));

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
	public StateMst getStateMstById(int id) {
		StateMst stateMst = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(StateMst.class, "SM")
					.createAlias("SM.countryMst", "COM")
					.add(Restrictions.eq("COM.id", id))
					.add(Restrictions.eq("SM.isDeleted", false))
					.add(Restrictions.eq("COM.isDeleted", false))
					.addOrder(Order.asc("SM.id"));

			stateMst = (StateMst) criteria.uniqueResult();
		}catch (HibernateException he) {
			log.error("getStateMstById Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return stateMst;
	}

	@Override
	public List<StateMst> getStateByCountryId(int id) {
		List<StateMst> stateMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(StateMst.class, "SM")
					.add(Restrictions.eq("SM.countryMst.id", id))
					.add(Restrictions.eq("SM.isActive", true))
					.add(Restrictions.eq("SM.isDeleted", false))
					
					.addOrder(Order.asc("SM.id"));

			stateMsts =  criteria.list();
		}catch (HibernateException he) {
			log.error("getStateMstById Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return stateMsts;
	}
}

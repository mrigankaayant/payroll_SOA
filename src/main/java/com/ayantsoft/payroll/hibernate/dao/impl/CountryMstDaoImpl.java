package com.ayantsoft.payroll.hibernate.dao.impl;

import java.io.Serializable;
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
import com.ayantsoft.payroll.hibernate.dao.CountryMstDao;
import com.ayantsoft.payroll.hibernate.pojo.CityMst;
import com.ayantsoft.payroll.hibernate.pojo.CountryMst;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;

@Repository
public class CountryMstDaoImpl implements Serializable, CountryMstDao {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6441106893319306611L;

	private Logger log = Logger.getLogger(CountryMstDaoImpl.class);

	@Override
	public List<CountryMst> getCountryMsts() {
		List<CountryMst> countryMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{

			Criteria criteria = session.createCriteria(CountryMst.class,"CM")
					.add(Restrictions.eq("CM.isActive", true))
					.add(Restrictions.eq("CM.isDeleted", false));

			countryMsts = criteria.list();
			log.info("country list size is :"+countryMsts.size());

		}catch (HibernateException he) {
			log.error("getCountryMsts Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return countryMsts;
	}

	@Override
	public CountryMst getCountryMstById(int id) {
		CountryMst countryMst = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Query query = session.createQuery(""
					+ "FROM "
					+ "CountryMst "

					+ "WHERE "
					+ "id = :id "
					+ "AND isActive = 1 "

					+ "ORDER BY id");

			query.setParameter("id", id);

			countryMst = (CountryMst) query.uniqueResult();
		}catch (HibernateException he) {
			log.error("getCountryMstById Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return countryMst;
	}
}

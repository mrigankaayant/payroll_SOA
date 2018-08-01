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
import com.ayantsoft.payroll.hibernate.dao.IncomeTaxDao;
import com.ayantsoft.payroll.hibernate.pojo.IncomeTax;
import com.ayantsoft.payroll.hibernate.pojo.ProfessionalTaxMst;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;

@Repository
public class IncomeTaxDaoImpl implements Serializable,  IncomeTaxDao{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -724740713659101131L;

	private Logger log = Logger.getLogger(IncomeTaxDaoImpl.class);

	@Override
	public List<IncomeTax> getIncomeTaxs() {

		List<IncomeTax> incomeTaxs = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(IncomeTax.class, "IT")
					.createAlias("IT.taxPayerGroupMst", "TPGM")

					.add(Restrictions.eq("IT.isDeleted", false))
					.add(Restrictions.eq("TPGM.isDeleted", false))

					.addOrder(Order.asc("IT.id"));

			incomeTaxs = criteria.list();
		}catch (HibernateException he) {
			log.error("getIncomeTaxs Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return incomeTaxs;
	}

	@Override
	public IncomeTax getIncomeTaxById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IncomeTax> getIncomeTaxsByProperties(IncomeTax incomeTax) {
		List<IncomeTax> incomeTaxs = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(IncomeTax.class, "IT")
					.createAlias("taxPayerGroupMst", "ITG");

			if(incomeTax.getId() != null){
				criteria.add(Restrictions.eq("IT.id", incomeTax.getId()));
			}

			if(incomeTax.getLowerLimitAmount() != null){
				criteria.add(Restrictions.eq("IT.lowerLimitAmount", incomeTax.getLowerLimitAmount()));
			}

			if(incomeTax.getUpperLimitAmount() != null){
				criteria.add(Restrictions.eq("IT.upperLimitAmount", incomeTax.getUpperLimitAmount()));
			}

			if(incomeTax.getGender() != null){
				criteria.add(Restrictions.eq("IT.gender", incomeTax.getGender()));
			}

			if(incomeTax.getTaxRate() != null){
				criteria.add(Restrictions.eq("IT.taxRate", incomeTax.getTaxRate()));
			}

			criteria.add(Restrictions.eq("IT.isActive", true))
			.add(Restrictions.eq("ITG.isActive", true))

			.addOrder(Order.asc("IT.id"));

			incomeTaxs = criteria.list();
		}catch (Exception e) {
			log.info("Error : ", e);
			incomeTaxs = null;
		}finally {
			session.close();
		}
		return incomeTaxs;
	}

	@Override
	public void updateIncomeTax(IncomeTax incomeTax) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteIncomeTaxById(int id) {
		// TODO Auto-generated method stub
	}
}

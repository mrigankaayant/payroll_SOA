package com.ayantsoft.payroll.hibernate.dao.impl;

import java.io.Serializable;
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

import com.ayantsoft.payroll.exception.DataInsertionException;
import com.ayantsoft.payroll.exception.DataUpdationException;
import com.ayantsoft.payroll.exception.PayrollException;
import com.ayantsoft.payroll.hibernate.dao.UserMstDao;
import com.ayantsoft.payroll.hibernate.pojo.UserMst;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;
import org.apache.commons.beanutils.BeanUtilsBean;



@Repository
class UserMstDaoImpl implements UserMstDao, Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4808469122815749173L;

	private Logger log = Logger.getLogger(UserMstDaoImpl.class);

	@Override
	public UserMst getUserMstById(int id) {
		UserMst userMst = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			String hql = "from UserMst UM join fetch UM.id UMI where UMI.id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id",id);
			userMst = (UserMst) query.uniqueResult();
		}catch(HibernateException he){
			log.info("Fetch user details using id  HIBERNATE EXCEPTION :" +he);

		}finally {
			session.close();
		}
		return userMst;
	}

	@Override
	public List<UserMst> getUserMsts() {
		List<UserMst> userMst = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(UserMst.class,"UM")
					.add(Restrictions.eq("isDeleted", false))
					.addOrder(Order.asc("UM.id"));

			userMst = criteria.list();

		}catch(HibernateException he){
			log.info("Fetch user details HIBERNATE EXCEPTION :" +he);

		}finally {
			session.close();
		}
		return userMst;
	}

	@Override
	public List<UserMst> getUserMstsByProperties(UserMst userMst) {
		List<UserMst> userMsts = null;
		UserMst copyUserMsts = new UserMst();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		
			Criteria criteria = session.createCriteria(UserMst.class);
			criteria.add(Restrictions.eq("firstName",userMst.getFirstName()));
			criteria.add(Restrictions.eq("lastName",userMst.getLastName()));
			criteria.add(Restrictions.eq("emailId",userMst.getEmailId()));

			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("firstName"))
					.add(Projections.property("lastName"))
					.add(Projections.property("emailId")));

			userMsts = criteria.list();

		}catch(HibernateException he){
			log.error("User search by propertties HIBERNATE EXCEPTION "+he);

		}finally {
			session.close();

		}
		return userMsts;
	}

	@Override
	public void insertUserMsts(List<UserMst> userMsts) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			for(UserMst obj : userMsts){
				obj.setCreatedDate(new Date());
				obj.setIsActive(true);
				obj.setIsDeleted(false);

				session.save(obj);
			}
			session.getTransaction().commit();

		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insert user masters HIBERNATE EXCEPTION :"+he);
			throw new DataInsertionException("Users insert exception");

		}finally {
			session.close();
		}
	}

	@Override
	public void insertUserMst(UserMst userMst) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();

			userMst.setIsActive(true);
			userMst.setCreatedDate(new Date());
			userMst.setIsDeleted(false);
			session.save(userMst);

			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insert usermst insert exception : "+he);
			throw new DataInsertionException("Insert User excetion");

		}finally {
			session.close();
		}

	}

	@Override
	public void updateUserMsts(List<UserMst> userMst) {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateUserMst(UserMst userMst) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(""

					+ "UPDATE "
					+ "UserMst "
					+ "SET "
					+ "firstName =:firstName, "
					+ "lastName =:lastName "

					+ "WHERE "
					+ "id =:id"
					);

			query.setParameter("firstName", userMst.getFirstName())
			.setParameter("lastName", userMst.getLastName());

			query.executeUpdate();
			session.getTransaction().commit();

		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("Update usermaster HIBERNATE EXCEPTION ERROR :"+he); 
			throw new DataUpdationException("Usermaster data update error");
		}finally {
			session.close();
		}

	}

	@Override
	public void deleteUserMstById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();

			Query query = session.createQuery(""

					+ "UPDATE "
					+ "UserMst "

					+ "SET "

					+ "isDeleted =:isDeleted "

					+ "WHERE "
					+ "id.id =:id");

			query.setParameter("isDeleted", true)
			.setParameter("id", id);
			query.executeUpdate();

			session.getTransaction().commit();

		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("User delete HIBERNATE EXCEPTION :"+he);
			throw new DataUpdationException("user master delete exception");
		}
		finally{
			session.close();
		}

	}

	@Override
	public List<UserMst> getUserMstsByProperty(String searchKey, String searchValue) {
		List<UserMst> userMsts = null; 
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(UserMst.class);
			criteria.add(Restrictions.eq(searchKey, searchValue));

			userMsts = criteria.list();

		}catch(HibernateException he){
			log.error("userMst fetch by property HIBERNATE EXCEPTION :"+he);
			throw new PayrollException("getUserMstsByProperty fetch data exception");

		}finally {
			session.close();
		}
		return userMsts;
	}

	@Override
	public UserMst getUserMstByUsername(String userName) {
		UserMst userMst = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			
             Criteria criteria = session.createCriteria(UserMst.class, "UM")
					.add(Restrictions.eq("UM.userName", userName))
					.add(Restrictions.eq("UM.isActive", true))
					.add(Restrictions.eq("UM.isDeleted", false));

			userMst = (UserMst) criteria.uniqueResult();
			
		}catch(HibernateException he){
			he.printStackTrace();
			throw new PayrollException("Data access exception");
		}
		
		return userMst;
		
	}


}

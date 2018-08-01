package com.ayantsoft.payroll.hibernate.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ayantsoft.payroll.dto.search.ColumnMeta;
import com.ayantsoft.payroll.dto.search.LazyDataRequestModel;
import com.ayantsoft.payroll.dto.search.LazyDataResponseModel;
import com.ayantsoft.payroll.exception.DataAccessException;
import com.ayantsoft.payroll.exception.DataInsertionException;
import com.ayantsoft.payroll.exception.DataUpdationException;
import com.ayantsoft.payroll.hibernate.dao.PayrollCompDao;
import com.ayantsoft.payroll.hibernate.pojo.PayrollComp;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;

@Repository
public class PayrollCompDaoImpl implements Serializable,PayrollCompDao {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7814419135293791711L;

	Logger log= Logger.getLogger(PayrollComp.class);

	@Override
	public void insertPayrollComp(PayrollComp payrollComp) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			payrollComp.setCreatedDate(new Date());
			session.saveOrUpdate(payrollComp);
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insertPayrollGroupMst HibernateException : ", he);
			throw new DataInsertionException("PayrollGroupEarningComp INSERT ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public List<PayrollComp> getPayrollComp(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<PayrollComp> payrollComp = null;
		try{
			Query query = session.createQuery(""
					+ "FROM "
					+ "PayrollComp "

					+ "WHERE "
					+ "isDeleted=:isDeleted "

					+ "Order BY id")

					.setParameter("isDeleted", false);

			payrollComp = query.list();

		}catch(HibernateException he){
			log.error("PayrollEarningComp get data error : "+he);
			throw new DataAccessException();
		}finally{
			session.close();
		}
		return payrollComp;
	}

	@Override
	public PayrollComp getPayrollCompById(int id) {

		PayrollComp payrollComp = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Query query = session.createQuery(""
					+ "FROM "
					+ "PayrollComp "

					+ "WHERE "
					+ "id=:id "
					+ " AND isDeleted=:isDeleted "

					+ "Order BY id")

					.setParameter("id", id)
					.setParameter("isDeleted", false);

			payrollComp = (PayrollComp) query.uniqueResult();

		}catch(HibernateException he){
			log.error("payrollComp fetch data error"+he);
			throw new DataAccessException();

		}finally{
			session.close();

		}
		return payrollComp;
	}

	@Override
	public void updatePayrollComp(PayrollComp payrollComp) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			String hql = "UPDATE "
					+ "PayrollComp "

					+ "SET "
					+ "name = :name,"
					+ "description =:description, "
					+ "compValue =:compValue, "
					+ "compType = :compType, "
					+ "orderIndex=:orderIndex ";

			hql += "WHERE id = :id ";

			Query query = session.createQuery(hql);

			//query.setParameter("name", payrollComp.getName());
			//query.setParameter("description",payrollComp.getDescription());
			query.setParameter("compValue", payrollComp.getCompValue());
			//query.setParameter("compType", payrollComp.getCompType());
			//query.setParameter("orderIndex", payrollComp.getOrderIndex());
			query.setParameter("id", payrollComp.getId());

			query.executeUpdate();
			session.getTransaction().commit();

		}catch(HibernateException he){
			log.error("updatePayrollComp error : "+he);
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
	}

	@Override
	public void insertPayrollComps(List<PayrollComp> payrollComps) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			for(PayrollComp obj:payrollComps){
				obj.setCreatedDate(new Date());
				session.saveOrUpdate(obj);
			}
			session.getTransaction().commit();
			
		}catch(HibernateException he){
			log.error("insertPayrollComps insertHibernate exception :"+he);
			throw new DataInsertionException("insertPayrollComps INSERT ERROR");

		}finally{
			session.close();
		}
	}

	@Override
	public LazyDataResponseModel<PayrollComp> getPayrollEarningsLazydataById(
			LazyDataRequestModel lazyDataRequestModel) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		LazyDataResponseModel<PayrollComp> lazyDataResponseModel= new LazyDataResponseModel<>();
		try
		{
			Criteria criteria = session.createCriteria(PayrollComp.class,"pc");
			criteria.createAlias("pc.payrollGroupMst", "pgm");
			criteria.add(Restrictions.eq("pgm.id",lazyDataRequestModel.getId()));
			criteria.setProjection(Projections.rowCount());
			Long totalRowCount = (Long)criteria.uniqueResult();
			lazyDataResponseModel.setRecordsTotal(totalRowCount);

			List<ColumnMeta> filters = lazyDataRequestModel.getColumns();


			if (filters != null) {
				filters.forEach((e)->{
					if(!e.getData().isEmpty() && "isActive".equals(e.getData())){
						if(e.getData().equals("true")|| e.getData().equals("false")){
							Boolean isActive = Boolean.parseBoolean(e.getSearch().getValue());
							criteria.add(Restrictions.eq(e.getData(), isActive));
						}
					}else{
						if(e.getSearch().getValue()!=""){
							criteria.add(Restrictions.like(e.getData(), e.getSearch().getValue(),MatchMode.ANYWHERE));
						}

					}

				});
			}


			Long resultCount = (Long)criteria.uniqueResult();
			lazyDataResponseModel.setRecordsFiltered(resultCount);
			criteria.setProjection(null);

			lazyDataRequestModel.getOrder().forEach((e->{

				if(e.getDir().equals("asc")){
					criteria.addOrder(Order.asc(filters.get(e.getColumn()).getData()));
				}else if(e.getDir().equals("desc")){
					criteria.addOrder(Order.desc(filters.get(e.getColumn()).getData()));
				}
			}));


			criteria.setFirstResult(lazyDataRequestModel.getStart());
			criteria.setMaxResults(lazyDataRequestModel.getLength());

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<PayrollComp> payrollEarningComps = criteria.list();
			lazyDataResponseModel.setData(payrollEarningComps);
			lazyDataResponseModel.setDraw(lazyDataRequestModel.getDraw());
		}catch(Exception e){
			e.printStackTrace();
			throw new HibernateException("Data access exception.");
		}
		finally{
			session.close();
		}

		return lazyDataResponseModel;
	}

	@Override
	public List<PayrollComp> getPayrollEarningCompByPayrollGroupId(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<PayrollComp> payrollComps = null;
		try{

			Criteria criteria = session.createCriteria(PayrollComp.class,"pc");
			criteria.createAlias("pc.payrollGroupMst", "PGM");
			criteria.createAlias("pc.payrollCompMst", "PCM");
			criteria.add(Restrictions.eq("PGM.id",id));
			criteria.add(Restrictions.eq("PCM.type","Earning"));
			payrollComps = criteria.list();

		}catch(HibernateException he){
			log.error("getPayrollCompByPayrollGroupId Hibernate error :"+he);

		}finally {
			session.close();

		}
		return payrollComps;
	}


	@Override
	public void deletePayrollCompById(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();

			Query query = session.createQuery(""

					+ "UPDATE "
					+ "PayrollComp "

					+ "SET "

					+ "isDeleted =:isDeleted "

					+ "WHERE "
					+ "id =:id");

			query.setParameter("isDeleted", true)
			.setParameter("id", id);
			query.executeUpdate();

			session.getTransaction().commit();

		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("PayrollComp delete HIBERNATE EXCEPTION :"+he);
			throw new DataUpdationException("PayrollComp delete exception");
		}
		finally{
			session.close();
		}

	}

	@Override
	public List<PayrollComp> getPayrollCompByProperty(String searchKey, String searchValue) {
		List<PayrollComp> payrollComps = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(PayrollComp.class);
			if(searchKey.equals("id")){
				criteria.add(Restrictions.eq(searchKey, Integer.valueOf(searchValue)));
			}else if(searchKey.equals("isActive")){
				criteria.add(Restrictions.eq(searchKey, Boolean.valueOf(searchValue)));
			}else if(searchKey.equals("isDeleted")){
				criteria.add(Restrictions.eq(searchKey, Boolean.valueOf(searchValue)));
			}else if(searchKey.equals("createdDate")){
				//SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				//Date date = format.parse(searchValue);
				//criteria.add(Restrictions.eq(searchKey,date));
			}else{
				criteria.add(Restrictions.eq(searchKey,searchValue));
			}
			payrollComps = criteria.list();	
		}catch (HibernateException he) {
			log.error("getPayrollCompByProperty Error : ", he);
			throw new DataAccessException();
		}catch(Exception e){
			log.error("Dateformat exception in getPayrollCompByProperty: ", e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return payrollComps;
	}

	@Override
	public List<PayrollComp> getPayrollpayrollDeductionsByPayrollGroupId(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<PayrollComp> payrollComps = null;
		try{

			Criteria criteria = session.createCriteria(PayrollComp.class,"pc");
			criteria.createAlias("pc.payrollGroupMst", "PGM");
			criteria.createAlias("pc.payrollCompMst", "PCM");
			criteria.add(Restrictions.eq("PGM.id",id));
			criteria.add(Restrictions.eq("PCM.type","Deduction"));
			payrollComps = criteria.list();

		}catch(HibernateException he){
			log.error("getPayrollpayrollDeductionsByPayrollGroupId Hibernate error :"+he);

		}finally {
			session.close();

		}
		return payrollComps;
	}


	@Override
	public List<PayrollComp> getPayrollCompsByPayrollGroupId(int id,String type) {
		List<PayrollComp> payrollComps = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			String hql = "FROM PayrollComp pc "
					   + "JOIN FETCH pc.payrollCompMst pcm "
					   + "JOIN FETCH pc.payrollGroupMst pgm "
					   
					   + "WHERE pgm.id = :pgmId and pgm.isDeleted = :value and pcm.type = :type";
			
			Query query = session.createQuery(hql);
			query.setParameter("pgmId",id);
			query.setParameter("value",false);
			query.setParameter("type", type);
			payrollComps =  query.list();
		}catch (HibernateException he) {
			log.error("getPayrollCompsListByPayrollGroupId Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return payrollComps;
	}

}

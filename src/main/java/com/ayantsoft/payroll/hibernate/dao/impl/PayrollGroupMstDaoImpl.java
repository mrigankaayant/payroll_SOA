package com.ayantsoft.payroll.hibernate.dao.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.ayantsoft.payroll.dto.EmployeeHrDeclarationDto;
import com.ayantsoft.payroll.dto.PayrollGroupMstDto;
import com.ayantsoft.payroll.dto.search.ColumnMeta;
import com.ayantsoft.payroll.dto.search.LazyDataRequestModel;
import com.ayantsoft.payroll.dto.search.LazyDataResponseModel;
import com.ayantsoft.payroll.dto.search.SearchMeta;
import com.ayantsoft.payroll.exception.DataAccessException;
import com.ayantsoft.payroll.exception.DataInsertionException;
import com.ayantsoft.payroll.exception.DataUpdationException;
import com.ayantsoft.payroll.hibernate.dao.PayrollGroupMstDao;
import com.ayantsoft.payroll.hibernate.pojo.PayrollComp;
import com.ayantsoft.payroll.hibernate.pojo.PayrollCompMst;
import com.ayantsoft.payroll.hibernate.pojo.PayrollGroupMst;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;

@Repository
public class PayrollGroupMstDaoImpl implements Serializable, PayrollGroupMstDao {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5262076608452468044L;

	private Logger log = Logger.getLogger(PayrollGroupMstDaoImpl.class);

	@Override
	public List<PayrollGroupMst> getPayrollGroupMsts() {
		List<PayrollGroupMst> payrollGroupMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Query query = session.createQuery(""
					+ "FROM "
					+ "PayrollGroupMst "

					+ "WHERE "
					+ "isDeleted = :isDeleted "
					+ "AND groupName!=:groupName "

					+ "ORDER BY id")

					.setParameter("isDeleted", false)
					.setParameter("groupName", "Default");

			payrollGroupMsts = query.list();
			log.info("payroll group mst infoo "+payrollGroupMsts);
		}catch (HibernateException he) {
			log.error("getPayrollGroupMsts Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return payrollGroupMsts;
	}

	@Override
	public PayrollGroupMst getPayrollGroupMstById(int id) {
		PayrollGroupMst payrollGroupMst = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Query query = session.createQuery(""
					+ "FROM "
					+ "PayrollGroupMst "

					+ "WHERE "
					+ "id = :id "
					+ "AND isDeleted = :isDeleted "

					+ "ORDER BY id")

					.setParameter("id", id)
					.setParameter("isDeleted", false);

			payrollGroupMst = (PayrollGroupMst) query.uniqueResult();
		}catch (HibernateException he) {
			log.error("getPayrollGroupMstById Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return payrollGroupMst;
	}

	@Override
	public List<PayrollGroupMst> getPayrollGroupMstsByProperties(PayrollGroupMst payrollGroupMst) {
		List<PayrollGroupMst> payrollGroupMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(PayrollGroupMst.class);

			if(payrollGroupMst.getId() != null){
				criteria.add(Restrictions.eq("id", payrollGroupMst.getId()));
			}

			if(payrollGroupMst.getGroupName() != null){
				criteria.add(Restrictions.eq("groupName", payrollGroupMst.getGroupName()));
			}

			if(payrollGroupMst.getDescription() != null){
				criteria.add(Restrictions.eq("description", payrollGroupMst.getDescription()));
			}

			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("id"))
					.add(Projections.property("groupName"))
					.add(Projections.property("description"))
					.add(Projections.property("isActive"))
					.add(Projections.property("createdDate")));

			payrollGroupMsts = criteria.list();

		}catch (HibernateException he) {
			log.error("getPayrollGroupMstsByProperties Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return payrollGroupMsts;
	}

	@Override
	public void insertPayrollGroupMsts(List<PayrollGroupMst> payrollGroupMsts) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			for(PayrollGroupMst obj : payrollGroupMsts){
				obj.setCreatedDate(new Date());
				obj.setIsActive(true);
				obj.setIsDeleted(false);

				session.save(obj);
			}

			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insertPayrollGroupMsts HibernateException : ", he);
			throw new DataInsertionException("PayrollGroupMst MULTIPLE INSERT ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void insertPayrollGroupMst(PayrollGroupMst payrollGroupMst) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();

			if(payrollGroupMst.getId() == null){
				payrollGroupMst.setCreatedDate(new Date());
				payrollGroupMst.setIsActive(true);
				payrollGroupMst.setIsDeleted(false);
			}
			session.saveOrUpdate(payrollGroupMst);

			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insertPayrollGroupMst HibernateException : ", he);
			throw new DataInsertionException("PayrollGroupMst INSERT ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void updatePayrollGroupMsts(List<PayrollGroupMst> payrollGroupMsts) {
		String hql = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{			
			session.beginTransaction();

			for(PayrollGroupMst obj : payrollGroupMsts){
				hql = null;
				hql = "UPDATE "
						+ "PayrollGroupMst "

						+ "SET ";

				if(obj.getGroupName() != null){
					hql += "groupName = :groupName ";
				}

				if(obj.getDescription() != null){
					hql += ",description = :description ";
				}

				hql += "WHERE id = :id";

				Query query = session.createQuery(hql);

				if(obj.getGroupName() != null){
					query.setParameter("groupName", obj.getGroupName());
				}

				if(obj.getDescription() != null){
					query.setParameter("description", obj.getDescription());
				}

				query.setParameter("id", obj.getId());

				query.executeUpdate();
			}

			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("updatePayrollGroupMsts HibernateException : ", he);
			throw new DataUpdationException("PayrollGroupMst MULTIPLE UPDATE ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void updatePayrollGroupMst(PayrollGroupMst payrollGroupMst) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{

			session.beginTransaction();
			String hql = "UPDATE "
					+ "PayrollGroupMst "

					+ "SET ";

			if(payrollGroupMst.getGroupName() != null){
				hql += "groupName = :groupName ";
			}

			if(payrollGroupMst.getDescription() != null){
				hql += ",description = :description ";
			}

			hql += "WHERE id = :id";

			Query query = session.createQuery(hql);

			if(payrollGroupMst.getGroupName() != null){
				query.setParameter("groupName", payrollGroupMst.getGroupName());
			}

			if(payrollGroupMst.getDescription() != null){
				query.setParameter("description", payrollGroupMst.getDescription());
			}

			query.setParameter("id", payrollGroupMst.getId());

			query.executeUpdate();

			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("updatePayrollGroupMst HibernateException : ", he);
			throw new DataUpdationException("PayrollGroupMst UPDATE ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public void deletePayrollGroupMstById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(""
					+ "UPDATE "
					+ "PayrollGroupMst "

					+ "SET "
					+ "isDeleted = :isDeleted "

					+ "WHERE "
					+ "id = :id");
			query.setParameter("isDeleted",true)
			.setParameter("id", id);

			query.executeUpdate();

			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("deletePayrollGroupMstById HibernateException : ", he);
			throw new DataUpdationException("PayrollGroupMst DELETE ERROR");
		}finally {
			session.close();
		}
	}

	@Override
	public LazyDataResponseModel<PayrollGroupMst>  getPayrollGroupMstsLazydata(LazyDataRequestModel lazyDataRequestModel) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		LazyDataResponseModel<PayrollGroupMst> lazyDataResponseModel= new LazyDataResponseModel<>();
		try
		{
			Criteria criteria = session.createCriteria(PayrollGroupMst.class);
			criteria.setProjection(Projections.rowCount());
			criteria.add(Restrictions.ne("groupName", "Default"));
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
			List<PayrollGroupMst> payrollGroupMst = criteria.list();
			lazyDataResponseModel.setData(payrollGroupMst);
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
	public void insertPayslip(List<PayrollGroupMst> payrollGroupMsts) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			for(PayrollGroupMst obj : payrollGroupMsts){
				obj.setCreatedDate(new Date());
				obj.setIsActive(true);
				obj.setIsDeleted(false);
				//session.save(obj);
			}

			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insertPayslip HibernateException : ", he);
			throw new DataInsertionException("PAYSLIP MULTIPLE INSERT ERROR");
		}finally {
			session.close();
		}

	}
	
	
	@Override
	public List<PayrollGroupMst> getPayrollGroupMstsByProperty(String searchKey,String searchValue){
		List<PayrollGroupMst> payrollGroupMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(PayrollGroupMst.class);
			if(searchKey.equals("id")){
				criteria.add(Restrictions.eq(searchKey, Integer.valueOf(searchValue)));
			}else if(searchKey.equals("isActive")){
				criteria.add(Restrictions.eq(searchKey, Boolean.valueOf(searchValue)));
			}else if(searchKey.equals("isDeleted")){
				criteria.add(Restrictions.eq(searchKey, Boolean.valueOf(searchValue)));
			}else if(searchKey.equals("createdDate")){
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				Date date = format.parse(searchValue);
				criteria.add(Restrictions.eq(searchKey,date));
			}else{
				criteria.add(Restrictions.eq(searchKey,searchValue));
			}
			payrollGroupMsts = criteria.list();	
		}catch (HibernateException he) {
			log.error("getPayrollGroupMstsByProperty Error : ", he);
			throw new DataAccessException();
		}catch(Exception e){
			log.error("Dateformat exception in getPayrollGroupMstsByProperty: ", e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return payrollGroupMsts;
	}

	@Override
	public List<PayrollGroupMstDto> getPayrollGroupWithEmp() {
		List<PayrollGroupMstDto>payrollGroupMstDtos = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
						
			String hql="SELECT COUNT(*) as totalEmployee"
					+ ",PGM.groupName as groupName"
					+ ",PGM.id as groupId "
					+ "FROM EmployeeMst EM "
					+ "JOIN "
					+ "EM.payrollGroupMst PGM "
					
					+ "Group by PGM.id ";
			
			Query query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(PayrollGroupMstDto.class));
			payrollGroupMstDtos = (List<PayrollGroupMstDto>) query.list();
		}catch(HibernateException he){
			log.info("Fetch getPayrollGroupWithEmp details using id  HIBERNATE EXCEPTION :" +he);
		}finally {
			session.close();
		}
		return payrollGroupMstDtos;
	}

}
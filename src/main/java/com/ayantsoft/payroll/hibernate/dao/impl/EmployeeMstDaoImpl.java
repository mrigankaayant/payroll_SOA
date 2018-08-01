package com.ayantsoft.payroll.hibernate.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
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
import com.ayantsoft.payroll.hibernate.dao.EmployeeMstDao;
import com.ayantsoft.payroll.hibernate.pojo.EmployeeMst;
import com.ayantsoft.payroll.hibernate.pojo.PayrollComp;
import com.ayantsoft.payroll.hibernate.pojo.PayrollGroupMst;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;
import com.ayantsoft.payroll.model.lazy.FilterMeta;
import com.ayantsoft.payroll.model.lazy.LazyDataModel;

@Repository
public class EmployeeMstDaoImpl implements Serializable, EmployeeMstDao {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8398664462147808539L;

	private Logger log = Logger.getLogger(EmployeeMstDaoImpl.class);

	

	@Override
	public EmployeeMst getEmployeeMstById(int id) {
		System.out.println("FETCH EMPLOYEE");
		System.out.println("================== "+id);
		EmployeeMst employeeMst = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try{
			
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(EmployeeMst.class, "EM")
					.createAlias("EM.payrollGroupMst", "PGM")
					.createAlias("EM.cityMstByWorkingCity", "SOCM")
					.createAlias("EM.cityMstByCityMstId", "CM")
					.createAlias("EM.stateMst", "SM")
					.createAlias("SM.countryMst", "COM")

					.add(Restrictions.eq("EM.id", id))
					.add(Restrictions.eq("EM.isDeleted", false));
			
			       employeeMst = (EmployeeMst) criteria.uniqueResult();
			        System.out.println("EMPLOYEE HASHCODE:  "+employeeMst.hashCode());
			       System.out.println("FIRST NAME: "+employeeMst.getFirstName());
			session.getTransaction().commit();
			
		}catch (HibernateException he) {
			he.printStackTrace();
			session.getTransaction().rollback();
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return employeeMst;
	}
	
	
	
	@Override
	public void insertEmployeeMst(EmployeeMst employeeMst) {
		System.out.println("UPDATE EMPLOYEE");
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			employeeMst.setIsActive(true);
			employeeMst.setIsDeleted(false);
			employeeMst.setCreatedDate(new Date());
			
			session.saveOrUpdate(employeeMst);
			
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("INSERT EMPLOYEE HIBERNATE ERROR :"+he);
			throw new DataInsertionException("INSERT EMPLOYEE EXCEPTION");
		}finally {
			session.close();
		}
		
		/*// Work around for immediate SELECT after INSERT
		try{
		   Thread.sleep(2000);	
		}catch(Exception e){
			e.printStackTrace();
		}*/
	}
	
	
	@Override
	public LazyDataModel getEmployeeLazydata(LazyDataModel lazyDataModel) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		List<Object> employeeMsts = null;
		try
		{
		
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(EmployeeMst.class, "employeeMst")
					.createAlias("employeeMst.payrollGroupMst", "payrollGroupMst")
					.createAlias("employeeMst.countryMst", "countryMst")
					.createAlias("employeeMst.stateMst", "stateMst")
					.createAlias("employeeMst.cityMstByCityMstId", "cityMstByCityMstId")
					.createAlias("employeeMst.cityMstByWorkingCity", "cityMstByWorkingCity")
					.add(Restrictions.eq("employeeMst.isActive", true))
					.add(Restrictions.eq("employeeMst.isDeleted", false));

			criteria.setProjection(Projections.rowCount());
			
			Map<String,FilterMeta> filters = lazyDataModel.getFilters();

			if (filters != null) {
				filters.forEach((k,v)->{
						criteria.add(Restrictions.like(k, v.getValue(),MatchMode.ANYWHERE));
				});
			}
			
			
			Long resultCount = (Long)criteria.uniqueResult();
			lazyDataModel.setTotalRecord(resultCount.intValue());
			criteria.setProjection(null);

			
			if(lazyDataModel.getSortField()!=null && !lazyDataModel.getSortField().isEmpty()){
				if(lazyDataModel.getSortOrder()==1){
					criteria.addOrder(Order.asc(lazyDataModel.getSortField()));
				}else if(lazyDataModel.getSortOrder()==-1){
					criteria.addOrder(Order.desc(lazyDataModel.getSortField()));
				}
			}

			criteria.setFirstResult(lazyDataModel.getFirst());
			criteria.setMaxResults(lazyDataModel.getFirst()+lazyDataModel.getRows());

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			employeeMsts = criteria.list();
			lazyDataModel.setData(employeeMsts);
			
			session.getTransaction().commit();
			
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			throw new HibernateException("Data access exception.");
		}
		finally{
			session.close();
		}

		return lazyDataModel;
	}
	
	
	
	/*@Override
	public List<EmployeeMst> getEmployeeMsts() {
		List<EmployeeMst> employeeMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(EmployeeMst.class, "EM")
					.createAlias("EM.payrollGroupMst", "PGM")
					.createAlias("EM.cityMstByCityMstId", "CM")
					.createAlias("EM.stateMst", "SM")
					.createAlias("SM.countryMst", "COM")

					.add(Restrictions.eq("EM.isDeleted", false))
					.add(Restrictions.eq("PGM.isDeleted", false))
					.add(Restrictions.eq("COM.isDeleted", false))
					.add(Restrictions.eq("CM.isDeleted", false))
					.add(Restrictions.eq("SM.isDeleted", false))
					.add(Restrictions.eq("COM.isDeleted", false))

					.addOrder(Order.asc("EM.id"));

			employeeMsts = criteria.list();
		}catch (HibernateException he) {
			he.printStackTrace();
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return employeeMsts;
	}

	@Override
	public List<EmployeeMst> getEmployeeMstsByProperties(EmployeeMst employeeMst) {
		List<EmployeeMst> employeeMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(EmployeeMst.class, "EM")
					.createAlias("EM.payrollGroupMst", "PGM")
					.createAlias("EM.stateOfCountryMst", "SOCM")
					.createAlias("EM.cityMst", "CM")
					.createAlias("EM.stateMst", "SM")
					.createAlias("EM.countryMst", "COM");

			if(employeeMst.getId() > 0){
				criteria.add(Restrictions.eq("EM.id", employeeMst.getId()));
			}

			if(employeeMst.getFirstName() != null){
				criteria.add(Restrictions.eq("EM.firstName", employeeMst.getFirstName()));
			}

			if(employeeMst.getLastName() != null){
				criteria.add(Restrictions.eq("EM.lastName", employeeMst.getLastName()));
			}

			if(employeeMst.getGender() != null){
				criteria.add(Restrictions.eq("EM.gender", employeeMst.getGender()));
			}

			if(employeeMst.getDob() != null){
				criteria.add(Restrictions.eq("EM.dob", employeeMst.getDob()));
			}

			if(employeeMst.getQualification() != null){
				criteria.add(Restrictions.eq("EM.qualification", employeeMst.getQualification()));
			}

			if(employeeMst.getFathersName() != null){
				criteria.add(Restrictions.eq("EM.fathersName", employeeMst.getFathersName()));
			}

			if(employeeMst.getMothersName() != null){
				criteria.add(Restrictions.eq("EM.mothersName", employeeMst.getMothersName()));
			}

			if(employeeMst.getHouseNo() != null){
				criteria.add(Restrictions.eq("EM.houseNo", employeeMst.getHouseNo()));
			}

			if(employeeMst.getStreet() != null){
				criteria.add(Restrictions.eq("EM.street", employeeMst.getStreet()));
			}

			if(employeeMst.getZipCode() != null){
				criteria.add(Restrictions.eq("EM.zipCode", employeeMst.getZipCode()));
			}

			if(employeeMst.getPhoneNo() != null){				
				criteria.add(Restrictions.eq("EM.phoneNo", employeeMst.getPhoneNo()));
			}

			if(employeeMst.getMobileNo() != null){
				criteria.add(Restrictions.eq("EM.mobileNo", employeeMst.getMobileNo()));
			}

			if(employeeMst.getEmail() != null){
				criteria.add(Restrictions.eq("EM.email", employeeMst.getEmail()));
			}

			if(employeeMst.getBankAccountNo() != null){
				criteria.add(Restrictions.eq("EM.accountNo", employeeMst.getBankAccountNo()));
			}

			if(employeeMst.getBankAccountType() != null){
				criteria.add(Restrictions.eq("EM.accountType", employeeMst.getBankAccountType()));
			}

			if(employeeMst.getBankName() != null){
				criteria.add(Restrictions.eq("EM.bankName", employeeMst.getBankName()));
			}

			if(employeeMst.getBankBranchName() != null){
				criteria.add(Restrictions.eq("EM.branchName", employeeMst.getBankBranchName()));
			}

			if(employeeMst.getBankBranchAddress() != null){
				criteria.add(Restrictions.eq("EM.branchAddress", employeeMst.getBankBranchAddress()));
			}

			if(employeeMst.getBankIfsCode() != null){
				criteria.add(Restrictions.eq("EM.ifsCode", employeeMst.getBankIfsCode()));
			}

			if(employeeMst.getBankMicrCode() != null){
				criteria.add(Restrictions.eq("EM.micrCode", employeeMst.getBankMicrCode()));
			}

			if(employeeMst.getPanNumber() != null){
				criteria.add(Restrictions.eq("EM.panNumber", employeeMst.getPanNumber()));
			}

			if(employeeMst.getCreatedDate() != null){
				criteria.add(Restrictions.eq("EM.createdDate", employeeMst.getCreatedDate()));
			}

			if(employeeMst.getPayrollGroupMst().getId() != null){
				criteria.add(Restrictions.eq("PGM.id", employeeMst.getPayrollGroupMst().getId()));
			}

			if(employeeMst.getPayrollGroupMst().getGroupName() != null){
				criteria.add(Restrictions.eq("PGM.groupName", employeeMst.getPayrollGroupMst().getGroupName()));
			}

			if(employeeMst.getPayrollGroupMst().getDescription() != null){
				criteria.add(Restrictions.eq("PGM.description", employeeMst.getPayrollGroupMst().getDescription()));
			}

			if(employeeMst.getPayrollGroupMst().getCreatedDate() != null){
				criteria.add(Restrictions.eq("PGM.createdDate", employeeMst.getPayrollGroupMst().getCreatedDate()));
			}

			if(employeeMst.getStateOfCountryMst().getId() != null){
				criteria.add(Restrictions.eq("SOCM.id", employeeMst.getStateOfCountryMst().getId()));
			}

			if(employeeMst.getStateOfCountryMst().getStateName() != null){
				criteria.add(Restrictions.eq("SOCM.stateName", employeeMst.getStateOfCountryMst().getStateName()));
			}

			if(employeeMst.getStateOfCountryMst().getCountryCode() != null){
				criteria.add(Restrictions.eq("SOCM.countryCode", employeeMst.getStateOfCountryMst().getCountryCode()));
			}

			if(employeeMst.getStateOfCountryMst().getCreatedDate() != null){
				criteria.add(Restrictions.eq("SOCM.createdDate", employeeMst.getStateOfCountryMst().getCreatedDate()));
			}

			criteria.add(Restrictions.eq("EM.isDeleted", false))
			.add(Restrictions.eq("PGM.isDeleted", false))
			.add(Restrictions.eq("SOCM.isDeleted", false))

			.setProjection(Projections.projectionList()
					.add(Projections.property("EM.id"))
					.add(Projections.property("EM.firstName"))
					.add(Projections.property("EM.lastName"))
					.add(Projections.property("EM.gender"))
					.add(Projections.property("EM.dob"))
					.add(Projections.property("EM.qualification"))
					.add(Projections.property("EM.fathersName"))
					.add(Projections.property("EM.mothersName"))
					.add(Projections.property("EM.houseNo"))            		
					.add(Projections.property("EM.street"))
					.add(Projections.property("EM.zipCode"))
					.add(Projections.property("EM.phoneNo"))
					.add(Projections.property("EM.mobileNo"))
					.add(Projections.property("EM.email"))
					.add(Projections.property("EM.accountNo"))            		
					.add(Projections.property("EM.accountType"))
					.add(Projections.property("EM.bankName"))
					.add(Projections.property("EM.branchName"))
					.add(Projections.property("EM.branchAddress"))
					.add(Projections.property("EM.ifsCode"))
					.add(Projections.property("EM.micrCode"))
					.add(Projections.property("EM.panNumber"))            		
					.add(Projections.property("EM.isActive"))
					.add(Projections.property("EM.createdDate"))

					.add(Projections.property("PGM.id"))
					.add(Projections.property("PGM.groupName"))
					.add(Projections.property("PGM.description"))
					.add(Projections.property("PGM.isActive"))
					.add(Projections.property("PGM.createdDate"))

					.add(Projections.property("SOCM.id"))
					.add(Projections.property("SOCM.stateName"))            		
					.add(Projections.property("SOCM.countryCode"))
					.add(Projections.property("SOCM.isActive"))
					.add(Projections.property("SOCM.createdDate")));

			employeeMsts = criteria.list();
		}catch (HibernateException he) {
			log.error("getEmployeeMstsByProperties Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return employeeMsts;
	}

	@Override
	public void insertEmployeeMsts(List<EmployeeMst> employeeMsts) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			for(EmployeeMst obj:employeeMsts){

				obj.setIsActive(true);
				obj.setIsDeleted(false);
				obj.setCreatedDate(new Date());

				session.saveOrUpdate(obj);
			}
			session.getTransaction().commit();

		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("Insert employees HIBERNATE ERROR :"+he);
			throw new DataInsertionException("Insert Employees excetion");

		}finally {
			session.close();
		}
	}

	

	@Override
	public void updateEmployeeMsts(List<EmployeeMst> employeeMsts) {

		//Session session = HibernateUtil.getSessionFactory().openSession();
		try{

		}catch(HibernateException he){
			log.error("Edit employee");
		}
	}


	@Override
	public void updateEmployeeMst(EmployeeMst employeeMst,int id) throws IllegalArgumentException, IllegalAccessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Field[] fields = employeeMst.getClass().getDeclaredFields();
			for(Field obj : fields){
				String hql = "UPDATE EmployeeMst SET ";
				Query query = null;
				obj.setAccessible(true);
				Class<?> targetType = obj.getType();
				if(obj.get(employeeMst) !=null && obj.getType().getSimpleName().equals("Integer") && !obj.getName().equals("id")){
						Object value = obj.get(employeeMst);
						hql = hql+obj.getName()+" = :fieldName WHERE id = :id";	
						query = session.createQuery(hql);
						query.setParameter("fieldName", (Integer)value);
				}
				if(obj.get(employeeMst) !=null && obj.getType().getSimpleName().equals("String")){
						Object value = obj.get(employeeMst);
						hql = hql+obj.getName()+" = :fieldName WHERE id = :id ";		
						query = session.createQuery(hql);
						query.setParameter("fieldName", (String)value);
				}
				if(obj.get(employeeMst) !=null && obj.getType().getSimpleName().equals("Date")){
						Object value = obj.get(employeeMst);
						hql = hql+obj.getName()+" = :fieldName WHERE id = :id ";		
						query = session.createQuery(hql);
						query.setParameter("fieldName",(Date)value);
				}
				if(obj.get(employeeMst) !=null && obj.getType().getSimpleName().equals("boolean")){
						Object value = obj.get(employeeMst);
						hql = hql+obj.getName()+" = :fieldName WHERE id = :id ";	
						query = session.createQuery(hql);
						query.setParameter("fieldName", (boolean)value);
				}
				
				if(query != null){
					session.beginTransaction();
					query.setParameter("id",id);
					query.executeUpdate();
					session.getTransaction().commit();
				}
			}
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("EmployeeMst Update HIBERNATE EXCEPTION :"+he);
			throw new DataUpdationException("EmployeeMst exception");
		} 
		finally{
			session.close();
		}
	}


	@Override
	public void deleteEmployeeMstById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(""
					+ "UPDATE "
					+ "EmployeeMst "

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
			log.error("deleteEmployeeMstById HibernateException : ", he);
			throw new DataUpdationException("EmployeeMst DELETE ERROR");
		}finally {
			session.close();
		}
	}
	
	

	@Override
	public LazyDataResponseModel<EmployeeMst> getEmployeeLazydata(LazyDataRequestModel lazyDataRequestModel) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		LazyDataResponseModel<EmployeeMst> lazyDataResponseModel= new LazyDataResponseModel<>();
		try
		{
			Criteria criteria = session.createCriteria(EmployeeMst.class,"EMP");
			criteria.createAlias("EMP.payrollGroupMst", "PGM");
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
			List<EmployeeMst> employeeMst = criteria.list();
			lazyDataResponseModel.setData(employeeMst);
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
	public void updateEmployeeMstsByPayrollGroup(List<EmployeeMst> employeeMsts) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		try{
			for(EmployeeMst obj:employeeMsts){

				Query query = session.createQuery(""
						+ "UPDATE "
						+ "EmployeeMst "

						+ "SET "
						+ "payrollGroupMst.id = :payrollGroupMst "

						+ "WHERE "
						+ "id = :id");

				query.setParameter("payrollGroupMst", obj.getPayrollGroupMst().getId())
				.setParameter("id", obj.getId());

				query.executeUpdate();

			}
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			throw new DataUpdationException("Employee update exception");

		}finally {
			session.close();

		}
	}

	@Override
	public List<EmployeeMst> getEmployeesByPayrollGroupId(int id) {
		List<EmployeeMst> employeeMst = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(EmployeeMst.class,"EMP");
			criteria.add(Restrictions.eq("EMP.payrollGroupMst.id", id));

			employeeMst =  criteria.list();
			
		}catch(Exception e){
			throw new DataAccessException();

		}finally {
			session.close();
		}
		return employeeMst;
	}

	@Override
	public List<EmployeeMst> getEmployeeMstsByProperty(String searchKey, String searchValue) {
		List<EmployeeMst> employeeMsts = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(EmployeeMst.class);
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
			employeeMsts = criteria.list();	
		}catch (HibernateException he) {
			log.error("getEmployeeMstsByProperty Error : ", he);
			throw new DataAccessException();
		}catch(Exception e){
			log.error("Dateformat exception in getEmployeeMstsByProperty: ", e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return employeeMsts;
	}
	
	
	

	@Override
	public void updatePayrollGroupByEmpId(Integer empId, Integer payrollGroupId) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			 session.beginTransaction();
			 String hql = "Update EmployeeMst emp set emp.payrollGroupMst.id= :pid where emp.id= :eId";
			 Query query = session.createQuery(hql);
			 query.setParameter("pid", payrollGroupId);
			 query.setParameter("eId", empId);
			 query.executeUpdate();
			 session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("EmployeeMst Update HIBERNATE EXCEPTION :"+he);
			throw new DataUpdationException("EmployeeMst exception");
		} 
		finally{
			session.close();
		}
	}
	
	@Override
	public void updatePayrollGroupsByEmpId(List<EmployeeMst> employees) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			for(EmployeeMst obj:employees){
				 String hql = "Update EmployeeMst emp set emp.payrollGroupMst.id= :pid,emp.countryMst.id= :cid,emp.stateMst.id= :sid,"
			      + "emp.cityMstByCityMstId.id= :cityId,emp.cityMstByWorkingCity.id= :workingCityId where emp.id= :eId";
				 Query query = session.createQuery(hql);
				 query.setParameter("pid", obj.getPayrollGroupMst().getId());
				 query.setParameter("cid", obj.getCountryMst().getId());
				 query.setParameter("sid", obj.getStateMst().getId());
				 query.setParameter("cityId", obj.getCityMstByCityMstId().getId());
				 query.setParameter("workingCityId", obj.getCityMstByWorkingCity().getId());
				 query.setParameter("eId", obj.getId());
				 query.executeUpdate();
			}
			session.getTransaction().commit();

		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("EmployeeMst Update HIBERNATE EXCEPTION :"+he);
			throw new DataUpdationException("EmployeeMst exception");

		}finally {
			session.close();
		}
	}

	@Override
	public List<EmployeeMst> getEmployeeListByPayrollGroupId(int id) {
		List<EmployeeMst> employees = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			String hql = "FROM EmployeeMst em "
					   + "JOIN FETCH em.payrollGroupMst pgm "
					   
					   + "WHERE pgm.id = :pgmId and pgm.isDeleted = :value";
			
			Query query = session.createQuery(hql);
			query.setParameter("pgmId",id);
			query.setParameter("value",false);
			employees =  query.list();
		}catch (HibernateException he) {
			log.error("getEmployeeListByPayrollGroupId Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return employees;
	}*/

}
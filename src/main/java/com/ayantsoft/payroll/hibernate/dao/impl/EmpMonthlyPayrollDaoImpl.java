package com.ayantsoft.payroll.hibernate.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ayantsoft.payroll.dto.EmployeeMonthlyPayrollDto;
import com.ayantsoft.payroll.dto.EmployeeMstDto;
import com.ayantsoft.payroll.dto.PayrollGroupMstDto;
import com.ayantsoft.payroll.dto.search.ColumnMeta;
import com.ayantsoft.payroll.dto.search.LazyDataRequestModel;
import com.ayantsoft.payroll.dto.search.LazyDataResponseModel;
import com.ayantsoft.payroll.exception.DataAccessException;
import com.ayantsoft.payroll.hibernate.dao.EmpMonthlyPayrollDao;
import com.ayantsoft.payroll.hibernate.pojo.EmpMonthlyPayroll;
import com.ayantsoft.payroll.hibernate.pojo.EmployeeMst;
import com.ayantsoft.payroll.hibernate.pojo.PayrollComp;
import com.ayantsoft.payroll.hibernate.pojo.PayrollGroupMst;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;
import com.ayantsoft.payroll.util.PayrollCalculator;

import javassist.bytecode.LineNumberAttribute.Pc;


@Repository
public class EmpMonthlyPayrollDaoImpl implements EmpMonthlyPayrollDao,Serializable {

	/**
	 * serialVersionUID = -5084913232717383979L;
	 */
	private static final long serialVersionUID = -5084913232717383979L;

	private Logger log = Logger.getLogger(EmpMonthlyPayrollDaoImpl.class);

	@Autowired
	private PayrollCalculator payrollCalculation;

	@Override
	public void processEmployeePayrollReports() {

		List<Integer> payrollGroupMstIds = null;
		List<PayrollGroupMstDto> payrollGroupMstDtos = null;
		List<EmployeeMstDto> employeeMstDtos = null;
		BigDecimal basic = new BigDecimal("0.00");
		BigDecimal hra;
		BigDecimal lta;
		BigDecimal otherAllowances;
		BigDecimal compAmount = new BigDecimal("0.00");
		BigDecimal deductionCompvalue = new BigDecimal("0.00");
		Date now = new Date();
		Session session = HibernateUtil.getSessionFactory().openSession();

		try{
			session.beginTransaction();
			Query query = session.createQuery(""
					+ "SELECT "
					+ "id "

					+ "FROM "
					+ "PayrollGroupMst");
			payrollGroupMstIds = query.list();

			if(payrollGroupMstIds != null && !payrollGroupMstIds.isEmpty()){
				Query empQuery = session.createQuery(""
						+ "SELECT "
						+ "em.id as employeeMstId, "
						+ "em.monthlySalary as monthlySalary, "
						+ "em.dob as dob, "
						+ "pgm.id as payrollGroupMstId "

						+ "FROM "
						+ "EmployeeMst em "
						+ "INNER JOIN em.payrollGroupMst pgm "

						+ "WHERE "
						+ "pgm.id IN :payrollGroupMstIds")

						.setParameterList("payrollGroupMstIds", payrollGroupMstIds)					
						.setResultTransformer(Transformers.aliasToBean(EmployeeMstDto.class));

				employeeMstDtos = empQuery.list();

				Query parollCompQuery = session.createQuery(""
						+ "SELECT "
						+ "pgm.id as groupId,"
						+ "pc.id as payrollCompId, "
						+ "pc.name as payrollCompName, "
						+ "pc.compValue as compValue, "
						+ "pc.compType as compType,"
						+ "pc.compCategory as payrollCompCategory "

						+ "FROM "
						+ "PayrollComp pc "
						+ "INNER JOIN pc.payrollGroupMst pgm "

						+ "WHERE "
						+ "pgm.id IN :payrollGroupMstIds")

						.setParameterList("payrollGroupMstIds", payrollGroupMstIds)
						.setResultTransformer(Transformers.aliasToBean(PayrollGroupMstDto.class));

				payrollGroupMstDtos = parollCompQuery.list();

				for(Integer pgmObj : payrollGroupMstIds){
					for(EmployeeMstDto empObj : employeeMstDtos){
						if(empObj.getPayrollGroupMstId().equals(pgmObj)){
							EmployeeMst employeeMst = new EmployeeMst();
							BigDecimal otherCompAmountTotal = new BigDecimal("0.00");
							employeeMst.setId(empObj.getEmployeeMstId());

							int employeeAge = payrollCalculation.caculateAge(empObj.getDob());

							for(PayrollGroupMstDto pcObj : payrollGroupMstDtos){
								if(pcObj.getGroupId().equals(pgmObj)){
									PayrollComp payrollComp = new PayrollComp();
								//	payrollComp.setId(pcObj.getPayrollCompId());
									EmpMonthlyPayroll empMonthlyPayroll = new EmpMonthlyPayroll();
									empMonthlyPayroll.setEmployeeMst(employeeMst);
									//empMonthlyPayroll.setPayrollComp(payrollComp);

									if(pcObj.getPayrollCompName()!=null){

										if(pcObj.getPayrollCompCategory().equals("Earning")){

											if(pcObj.getPayrollCompName().equals("Basic")){
												basic = payrollCalculation.calculateBasic(empObj.getMonthlySalary(), pcObj.getCompValue());
												empMonthlyPayroll.setAmount(basic);
												otherCompAmountTotal =(otherCompAmountTotal.add(basic));
											}

											if(pcObj.getPayrollCompName().equals("Hra")){
												basic = payrollCalculation.calculateBasic(empObj.getMonthlySalary(), pcObj.getCompValue());
												hra = payrollCalculation.calculateHra(basic,pcObj.getCompValue());
												empMonthlyPayroll.setAmount(hra);
												otherCompAmountTotal =(otherCompAmountTotal.add(hra));
											}

											if(pcObj.getCompType().equals("fixed")){
												compAmount = payrollCalculation.calculateFixedComp(pcObj.getCompValue());
												empMonthlyPayroll.setAmount(compAmount);
												otherCompAmountTotal =(otherCompAmountTotal.add(compAmount));
											}

											if(pcObj.getPayrollCompName().equals("LTA")){
												basic = payrollCalculation.calculateBasic(empObj.getMonthlySalary(), pcObj.getCompValue());
												lta = payrollCalculation.calculateLta(basic,pcObj.getCompValue());
												empMonthlyPayroll.setAmount(lta);
											}

											if(pcObj.getPayrollCompName().equals("Other Allowances")){
												otherAllowances = payrollCalculation.calculateOtherAllowances(empObj.getMonthlySalary(),otherCompAmountTotal);
												empMonthlyPayroll.setAmount(otherAllowances);
											}
										}

										if(pcObj.getPayrollCompCategory().equals("Deduction")){

											log.info("component type is :"+pcObj.getCompType());
											basic = payrollCalculation.calculateBasic(basic, pcObj.getCompValue());
											deductionCompvalue = payrollCalculation.calculateDeduction(empObj.getMonthlySalary(),pcObj.getCompValue());
											empMonthlyPayroll.setAmount(deductionCompvalue);
										}
									}

									Calendar cal = Calendar.getInstance();
									empMonthlyPayroll.setExemptionYear(cal.get(Calendar.YEAR));
									empMonthlyPayroll.setPayrollMonth(Integer.toString(cal.get(Calendar.MONTH)));
									empMonthlyPayroll.setCreatedDate(now);
									session.save(empMonthlyPayroll);
								}
							}
						}
					}
				}
			}

			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.info("processEmployeePayrollReports HIBERNATE EXCEPTION :" +he);

		}finally {
			session.close();
		}
	}

	@Override
	public List<EmployeeMstDto> getEmployeeMonthlyPayroll() {

		List<EmployeeMstDto> employeeMstDto = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{		
			String hql="SELECT "
					+ "EM.id as employeeMstId,"
					+ "EM.firstName as firstName,"
					+ "EM.employeeCode as employeeCode,"
					+ "EM.lastName as lastName,"
					+ "EM.bankAccountNo as bankAccountNo,"
					+ "EM.bankIfsCode as bankIfsCode,"
					+ "EM.bankMicrCode as bankMicrCode "

					+ "FROM "
					+ "EmpMonthlyPayroll EMP "
					+ "INNER JOIN EMP.employeeMst EM "

					+ "Group by EM.id ";

			Query query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(EmployeeMstDto.class));
			employeeMstDto = query.list();
			log.info("employeeMstDto size is%%%%%%%%%%%%%%%%%%%%%%%%%%%%% :"+employeeMstDto.size());


		}catch (HibernateException he) {
			log.error("getEmployeeMonthlyPayroll report Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return employeeMstDto;
	}

	@Override
	public LazyDataResponseModel<EmployeeMst> getMonthlyPayrollLazyData(LazyDataRequestModel lazyDataRequestModel) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		LazyDataResponseModel<EmployeeMst> lazyDataResponseModel= new LazyDataResponseModel<>();
		try
		{
			Criteria criteria = session.createCriteria(EmpMonthlyPayroll.class,"EMP")
					.createAlias("EMP.employeeMst","EM")
					.setProjection(Projections.projectionList()

							.add(Projections.property("EM.employeeCode"),"employeeCode")
							.add(Projections.property("EM.firstName"),"firstName")
							.add(Projections.property("EM.lastName"),"lastName")
							.add(Projections.property("EM.bankAccountNo"),"bankAccountNo")
							.add(Projections.groupProperty("EM.id")));

			long totalRowCount = criteria.list().size();
			lazyDataResponseModel.setRecordsTotal(totalRowCount);
			
			List<ColumnMeta> filters = lazyDataRequestModel.getColumns();
			log.info("myfilter value is :"+filters);

			if (filters != null) {
				filters.forEach((e)->{
					if(!e.getData().isEmpty() && "isActive".equals(e.getData())){
						log.info("come here1111111111111111111");
						if(e.getData().equals("true")|| e.getData().equals("false")){
							Boolean isActive = Boolean.parseBoolean(e.getSearch().getValue());
							criteria.add(Restrictions.eq(e.getData(), isActive));
						}
					}else{
						log.info("come here222222222222");
						if(e.getSearch().getValue()!=""){
							log.info("come here 33333333 :" +e.getData());
							log.info("come here 44444444 :" +e.getSearch().getValue());
							criteria.add(Restrictions.like("EM."+e.getData(), "EM."+e.getSearch().getValue(),MatchMode.ANYWHERE));
						}
					}
				});
			}

			long resultCount = criteria.list().size();
			lazyDataResponseModel.setRecordsFiltered(resultCount);
			//criteria.setProjection(null);
			lazyDataRequestModel.getOrder().forEach((e->{

				if(e.getDir().equals("asc")){
					criteria.addOrder(Order.asc("EM."+filters.get(e.getColumn()).getData()));

				}else if(e.getDir().equals("desc")){
					criteria.addOrder(Order.desc("EM."+filters.get(e.getColumn()).getData()));
				}
			}));
			
			criteria.setFirstResult(lazyDataRequestModel.getStart());
			criteria.setMaxResults(lazyDataRequestModel.getLength());
			//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.setResultTransformer(Transformers.aliasToBean(EmployeeMstDto.class));

			List<EmployeeMst> employeeMstDtos = criteria.list();
			
			lazyDataResponseModel.setData(employeeMstDtos);
			
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
	public List<EmployeeMonthlyPayrollDto> getEmployeesByPayrollDetails(int id) {
		List<EmployeeMonthlyPayrollDto> employeeMonthlyPayrollDto = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{		
			String hql="SELECT EM.employeeCode as employeeCode,"
					+ "EM.firstName as firstName,"

					+ "EM.lastName as lastName,"
					+ "EM.gender as gender,"
					+ "EM.lastName as lastName,"
					+ "EM.dob as employeeDob,"
					+ "EM.bankAccountNo as bankAccountNo,"
					+ "EM.bankIfsCode as bankIfsCode,"
					+ "EM.bankMicrCode as bankMicrCode, "
					+ "EM.monthlySalary as monthlySalary, "
					+ "EM.designation as designation, "
					+ "PC.name as componentName, "
					+ "PC.orderIndex as componentOrderIndex,"
					+ "PC.compCategory as componentCategory,"
					+ "EMP.amount as amount "

					+ "FROM EmpMonthlyPayroll EMP "
					+ "INNER JOIN "

					+ "EMP.employeeMst EM "
					+ "INNER JOIN "
					+ "EMP.payrollComp PC "

					+ "WHERE EM.id =:id ";

			Query query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(EmployeeMonthlyPayrollDto.class));
			query.setParameter("id", id);

			employeeMonthlyPayrollDto = query.list();

		}catch (HibernateException he) {
			log.error("getEmployeesByPayrollDetails report Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}

		return employeeMonthlyPayrollDto;
	}

	@Override
	public List<EmployeeMstDto> getMonthlyPayrollByProperties(EmpMonthlyPayroll empMonthlyPayroll) {
		List<EmployeeMstDto> employeeMstDtos = null;

		Session session = HibernateUtil.getSessionFactory().openSession();
		try{

			String hql="SELECT EM.id as employeeMstId,"
					+ "EM.firstName as firstName,"

					+ "EM.employeeCode as employeeCode,"
					+ "EM.lastName as lastName,"
					+ "EM.bankAccountNo as bankAccountNo,"
					+ "EM.bankIfsCode as bankIfsCode,"
					+ "EM.bankMicrCode as bankMicrCode "

					+ "FROM EmpMonthlyPayroll EMP "
					+ "JOIN "
					+ "EMP.employeeMst EM "

					+ "Group by EM.id ";

			Query query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(EmployeeMstDto.class));
			employeeMstDtos = query.list();


		}catch (HibernateException he) {
			log.error("getEmployeeMonthlyPayroll report Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return employeeMstDtos;
	}


}

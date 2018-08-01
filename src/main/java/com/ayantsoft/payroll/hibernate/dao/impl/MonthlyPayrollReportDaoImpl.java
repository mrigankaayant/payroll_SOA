package com.ayantsoft.payroll.hibernate.dao.impl;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ayantsoft.payroll.exception.DataAccessException;
import com.ayantsoft.payroll.exception.DataInsertionException;
import com.ayantsoft.payroll.hibernate.dao.MonthlyPayrollReportDao;
import com.ayantsoft.payroll.hibernate.pojo.EmployeeMst;
import com.ayantsoft.payroll.hibernate.pojo.ExemptionYear;
import com.ayantsoft.payroll.hibernate.pojo.MonthlyPayrollReport;
import com.ayantsoft.payroll.hibernate.pojo.MonthlyPayrollReportId;
import com.ayantsoft.payroll.hibernate.pojo.PayrollGroupMst;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;


@Repository
public class MonthlyPayrollReportDaoImpl implements MonthlyPayrollReportDao,Serializable {

	/**
	 * serialVersionUID = -5084913232717383979L;
	 */
	private static final long serialVersionUID = -5084913232717383979L;

	private Logger log = Logger.getLogger(MonthlyPayrollReportDaoImpl.class);

	@Override
	public void insertMonthlyPayrollReports(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MonthlyPayrollReport> getMonthlyReportByDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public void insertMonthlyPayrollReports(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		MonthlyPayrollReport monthlyPayrollReport = null;
		//EmpPayrollReport empPayrollReport = null;
		List<Integer> employeeMst = null;
		MonthlyPayrollReportId monthlyPayrollReportId = null;
		//EmpPayrollReportId empPayrollReportId = null;
		List<PayrollEarningComp> payrollEarningComps = null;
		ObjectMapper mapper = new ObjectMapper();
		try{
			session.beginTransaction();
			BigDecimal earningCmpResultVal;
			double totalSalaray = 20000.00;
			double result,earningResultValue,earningValue,deductionResultValue,deductionValue;
			String monthlyReportJson = null;

			String emphql = "select em.id from PayrollGroupMst pgm join pgm.employeeMsts em where pgm.id=:payrollId";
			Query empquery = session.createQuery(emphql);
			empquery.setParameter("payrollId", id);
			employeeMst = empquery.list();
			
			for (int i=0;i<employeeMst.size();i++) {
				
				String hql = "select pec.name,pec.compValue,pec.compType,pdc.name,pdc.compValue,pdc.compType,em.firstName,em.dob from PayrollGroupMst pgm join pgm.payrollEarningComps pec join pgm.payrollDeductions pdc join pgm.employeeMsts em where pgm.id=:payrollId ";
				Query query = session.createQuery(hql);
				query.setParameter("payrollId", id);
				payrollEarningComps = query.list();
				Iterator itr = payrollEarningComps.iterator();
				HashMap<String ,Double> hmap = new HashMap<String ,Double>();
				
				while(itr.hasNext()){

					Object[] obj = (Object[]) itr.next();
					String earningCompName = String.valueOf(obj[0]); 
					String earningCompValue =  String.valueOf(obj[1]);

					earningValue = Double.parseDouble(earningCompValue);
					String earningCompType = String.valueOf(obj[2]);

					if(earningCompType.equals("percentage")){
						earningResultValue = ((totalSalaray*earningValue)/100);
					}else{
						earningResultValue = earningValue;
					}

					String deductionCompName = String.valueOf(obj[3]);
					String deductionCompValue = String.valueOf(obj[4]);

					deductionValue = Double.parseDouble(deductionCompValue);
					String deductionCompType = String.valueOf(obj[5]); 

					if(deductionCompType.equals("percentage")){
						deductionResultValue = ((totalSalaray*deductionValue)/100);
					}else{
						deductionResultValue = deductionValue;
					}
					
					String dob = String.valueOf(obj[7]);
					//CALCULATE THE AGE USING DATE OF BIRTH 
					hmap.put(earningCompName, earningResultValue);
					hmap.put(deductionCompName, deductionResultValue);
					Criteria criteria = session.createCriteria(ExemptionYear.class);
					List<ExemptionYear> exemptionYearList = criteria.list();

					ExemptionYear exemptionYear = exemptionYearList.get(0);

					monthlyPayrollReportId = new MonthlyPayrollReportId();
					monthlyPayrollReportId.setExemptionYear(exemptionYearList.get(0).getYear());
					monthlyPayrollReport = new MonthlyPayrollReport();
					monthlyPayrollReport.setId(monthlyPayrollReportId);
					monthlyPayrollReport.setCreatedDate(new Date());
					
					//monthlyPayrollReport.setExemptionYear(exemptionYearList.get(0));
					monthlyReportJson = mapper.writeValueAsString(hmap);
					//monthlyPayrollReport.setMonthlyReport(monthlyReportJson);
				}
				Criteria criteria = session.createCriteria(ExemptionYear.class);
				List<ExemptionYear> exemptionYearList = criteria.list();
				empPayrollReportId = new EmpPayrollReportId();
				empPayrollReport = new EmpPayrollReport();
				empPayrollReportId.setEmployeeMstId(employeeMst.get(i));
				//empPayrollReportId.setExemptionYearId(exemptionYearList.get(0).getYear());
				empPayrollReport.setId(empPayrollReportId);
				
				String testJson = mapper.writeValueAsString(empPayrollReportId);
				empPayrollReport.setCreatedDate(new Date());
				//empPayrollReport.setPayrollReport(monthlyReportJson);
				session.save(empPayrollReport);
			}
			session.save(monthlyPayrollReport);
			session.getTransaction().commit();

		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insertMonthlyPayrollReports HibernateException : ", he);
			throw new DataInsertionException("InsertMonthlyPayrollReports MULTIPLE INSERT ERROR");

		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	@Override
	public List<MonthlyPayrollReport> getMonthlyReportByDate(String date) {

		int month = 1;
		List<MonthlyPayrollReport> monthlyPayrollReports = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Criteria criteria = session.createCriteria(MonthlyPayrollReport.class);

			criteria.add(Restrictions.eq("id.month", month));
			//criteria.add(Restrictions.eq("exemptionYear.year", dateFormat[2]));

			monthlyPayrollReports = criteria.list();

		}catch (HibernateException he) {
			log.error("getMonthly payroll report Error : ", he);
			throw new DataAccessException();
		}finally {
			session.close();
		}
		return monthlyPayrollReports;
	}*/
}

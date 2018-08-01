package com.ayantsoft.payroll.hibernate.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.ayantsoft.payroll.dto.EmployeeHrDeclarationDto;
import com.ayantsoft.payroll.exception.DataInsertionException;
import com.ayantsoft.payroll.exception.DataUpdationException;
import com.ayantsoft.payroll.hibernate.dao.EmpHraDeclarationDao;
import com.ayantsoft.payroll.hibernate.pojo.EmpHraDeclaration;
import com.ayantsoft.payroll.hibernate.util.HibernateUtil;

@Repository
public class EmpHraDeclarationDaoImpl implements Serializable, EmpHraDeclarationDao {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7394415903474146183L;

	private Logger log = Logger.getLogger(EmpHraDeclarationDaoImpl.class);


	@Override
	public void insertEmpHraDeclaration(EmpHraDeclaration empHraDeclaration) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			empHraDeclaration.setIsActive(true);
			empHraDeclaration.setIsDeleted(false);
			empHraDeclaration.setCreatedDate(new Date());
			session.save(empHraDeclaration);
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insertEmpHraDeclaration insert exception : "+he);
			throw new DataInsertionException("Insert User excetion");

		}finally {
			session.close();
		}
	}


	@Override
	public void insertEmpHraDeclarations(List<EmpHraDeclaration> empHraDeclarations){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			for(EmpHraDeclaration obj : empHraDeclarations){
				
				obj.setIsActive(true);
				obj.setIsDeleted(false);
				obj.setCreatedDate(new Date());
				session.save(obj);
				
			}
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			log.error("insertEmpHraDeclarations HIBERNATE EXCEPTION :"+he);
			throw new DataInsertionException("insertEmpHraDeclarations insert exception");

		}finally {
			session.close();
		}
	}
	
	
	@Override
	public EmployeeHrDeclarationDto getEmpHraDeclarationById(int id){
		EmployeeHrDeclarationDto employeeHrDeclarationDto = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			String hql="SELECT "
					+ "EHD.id as empHrDeclarationId,"
					+ "EHD.ownerName as ownerName,"
					+ "EHD.houseAddress as houseAddress,"
					+ "EHD.ownerPan as ownerPan,"
					+ "EHD.monthlyRent as monthlyRent,"
					+ "EHD.rentDocuments as rentDocuments,"
					+ "EHD.actualAmount as actualAmount,"
					+ "EHD.isActive as isActive,"
					+ "EHD.createdDate as createdDate,"
					+ "EHD.isDeleted as isDeleted,"
					+ "EMP.id as empId,"
					+ "EMP.employeeCode as employeeCode,"
					+ "EMP.firstName as firstName,"
					+ "EMP.lastName as lastName,"
					+ "EMP.gender as gender,"
					+ "EMP.dob as dob,"
					+ "EMP.mobileNo as mobileNo,"
					+ "EMP.email as email,"
					+ "EMP.deperment as deperment,"
					+ "EMP.designation as designation,"
					+ "YE.year as year,"
					+ "YE.id as exemptionYearId,"
					+ "YE.description as description,"
					+ "YE.isActive as isActiveForexemptionYear,"
					+ "YE.createdDate as createdDateForexemptionYear,"
					+ "YE.isDeleted as isDeletedForexemptionYear "
					
					+ "FROM EmpHraDeclaration as EHD "
					+ "JOIN EHD.employeeMst as EMP "
					+ "JOIN EHD.exemptionYear as YE "
					+ "WHERE EHD.id= :id ";
			Query query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(EmployeeHrDeclarationDto.class));
			query.setParameter("id", id);
			employeeHrDeclarationDto = (EmployeeHrDeclarationDto) query.uniqueResult();
		}catch(HibernateException he){
			log.info("Fetch getEmpHraDeclarationById details using id  HIBERNATE EXCEPTION :" +he);
		}finally {
			session.close();
		}
		return employeeHrDeclarationDto;
	}
	
	
	@Override
	public List<EmployeeHrDeclarationDto> getEmpHraDeclarations(){
		List<EmployeeHrDeclarationDto> employeeHrDeclarationDtos = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			String hql="SELECT "
					+ "EHD.id as empHrDeclarationId,"
					+ "EHD.ownerName as ownerName,"
					+ "EHD.houseAddress as houseAddress,"
					+ "EHD.ownerPan as ownerPan,"
					+ "EHD.monthlyRent as monthlyRent,"
					+ "EHD.rentDocuments as rentDocuments,"
					+ "EHD.actualAmount as actualAmount,"
					+ "EHD.isActive as isActive,"
					+ "EHD.createdDate as createdDate,"
					+ "EHD.isDeleted as isDeleted,"
					+ "EMP.id as empId,"
					+ "EMP.employeeCode as employeeCode,"
					+ "EMP.firstName as firstName,"
					+ "EMP.lastName as lastName,"
					+ "EMP.gender as gender,"
					+ "EMP.dob as dob,"
					+ "EMP.mobileNo as mobileNo,"
					+ "EMP.email as email,"
					+ "EMP.deperment as deperment,"
					+ "EMP.designation as designation,"
					+ "YE.year as year,"
					+ "YE.id as exemptionYearId,"
					+ "YE.description as description,"
					+ "YE.isActive as isActiveForexemptionYear,"
					+ "YE.createdDate as createdDateForexemptionYear,"
					+ "YE.isDeleted as isDeletedForexemptionYear "
					
					+ "FROM EmpHraDeclaration as EHD "
					+ "JOIN EHD.employeeMst as EMP "
					+ "JOIN EHD.exemptionYear as YE ";
			
			Query query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(EmployeeHrDeclarationDto.class));
			employeeHrDeclarationDtos = query.list(); 
		}catch(HibernateException he){
			log.info("Fetch getEmpHraDeclarationById details using id  HIBERNATE EXCEPTION :" +he);
		}finally {
			session.close();
		}
		return employeeHrDeclarationDtos;
	}
	
	
	@Override
	public void updateEmpHraDeclaration(EmpHraDeclaration empHraDeclaration,int id) throws IllegalArgumentException, IllegalAccessException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Field[] fields = empHraDeclaration.getClass().getDeclaredFields();
			for(Field obj : fields){
				String hql = "UPDATE EmpHraDeclaration SET ";
				Query query = null;
				obj.setAccessible(true);
				Class<?> targetType = obj.getType();
				if(obj.get(empHraDeclaration) !=null && obj.getType().getSimpleName().equals("Integer") && !obj.getName().equals("id")){
					
						Object value = obj.get(empHraDeclaration);
						hql = hql+obj.getName()+" = :fieldName WHERE id = :id";	
						query = session.createQuery(hql);
						query.setParameter("fieldName", (Integer)value);
				}
				if(obj.get(empHraDeclaration) !=null && obj.getType().getSimpleName().equals("String")){
						Object value = obj.get(empHraDeclaration);
						hql = hql+obj.getName()+" = :fieldName WHERE id = :id ";		
						query = session.createQuery(hql);
						query.setParameter("fieldName", (String)value);
				}
				if(obj.get(empHraDeclaration) !=null && obj.getType().getSimpleName().equals("Date")){
						Object value = obj.get(empHraDeclaration);
						hql = hql+obj.getName()+" = :fieldName WHERE id = :id ";		
						query = session.createQuery(hql);
						query.setParameter("fieldName",(Date)value);
				}
				if(obj.get(empHraDeclaration) !=null && obj.getType().getSimpleName().equals("BigDecimal")){
						Object value = obj.get(empHraDeclaration);
						hql = hql+obj.getName()+" = :fieldName WHERE id = :id ";	
						query = session.createQuery(hql);
						query.setParameter("fieldName", (BigDecimal)value);
				}
				if(obj.get(empHraDeclaration) !=null && obj.getType().getSimpleName().equals("boolean")){
						Object value = obj.get(empHraDeclaration);
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
			log.error("UpdateEmpHraDeclaration HIBERNATE EXCEPTION :"+he);
			throw new DataUpdationException("EmpHraDeclaration exception");
		} 
		finally{
			session.close();
		}
	}
	
	
	

}

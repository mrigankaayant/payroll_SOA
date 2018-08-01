package com.ayantsoft.payroll.util;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NumberFormatUtil implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4110926089315905247L;

	public static String doubleToString(Double number){
		DecimalFormat df = new DecimalFormat("#.00");
		try{
			return df.format(number);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String longToString(Long number){
		DecimalFormat df = new DecimalFormat("#.00");
		try{
			return df.format(number);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	 public static Date stringTodate(String date){
		 try{
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			 return sdf.parse(date);
		 }catch(Exception e){
			 e.printStackTrace();
			 return null;
		 }
	 }
	 
	 public static Calendar dateToCalendar(Date date){ 
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 return cal;
	}	
}

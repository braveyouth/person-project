package com.example.util;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class ParameterCalibration {
	
	/**
	 * 定长数字字符串校验
	 * @param code
	 * @param length
	 * @return
	 */
   public  static boolean digitallengthCheck(String code,int length ){
	   if(StringUtils.isNumeric(code)&&code.length()==length){
		   return true;
	   }
	return false;
   }
  
    /**
     * 判断时间是否是合法日期格式
     * @param date
     * @param format
     * @return
     */
   public static boolean checkDate(String date,String format){
	   if(StringUtils.isBlank(date)){
		   return false;
	   }
	   
	   
	   SimpleDateFormat sdf=new SimpleDateFormat(format);
	  try {
		  sdf.setLenient(false);
		  sdf.parse(date);
	} catch (ParseException e) {
		 
		return false;
	}
	return true;
   }
   
  
   public static boolean contains(int a,Integer ...objs){
	   for(int arg:objs){
		   if(a==arg){
			   return true;
		   }
	   }
	  return false;
   }
   public static boolean contains(String arg,String ...objs){
	   if(StringUtils.isEmpty(arg)){
		   return false;
		   
	   }
	   for(String obj:objs){
		   if(arg.equals(obj)){
			   return true;
		   }
	   }
	  return false;
   } 
   public static boolean check(String ...args){
	   for(String arg:args){
		   if(StringUtils.isBlank(arg)){
			   return false;
		   }
	   }
	   return true;
   }
   
   

 
  
}

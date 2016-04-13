package com.tcs.toolultimate.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class Utility {

	/**
	 * 
	 * @param object
	 * @return true or false
	 */
	public static boolean isEmptyOrNull(Object object){
		if(object instanceof Collection){
			@SuppressWarnings({ "rawtypes" })
			Collection collection = (Collection) object;
			return collection == null || collection.isEmpty();
		} else if(object instanceof String){
			String string = (String) object;
			return string == null || string.length() == 0;
		} else{
			return object == null;
		}		
	}	
	
	/**
	 * 
	 * @param date
	 * @param dateFormat
	 * @return formatted date
	 * @throws ParseException
	 */
	public static String formatDate(Object date, String dateFormat) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);		
		if(date instanceof Date){
			return format.format(date);
		} else if(date instanceof String){
			String stringDate = (String) date;
			Date parsedDate = format.parse(stringDate);
			return format.format(parsedDate);
		} else {
			return Constants.EMPTY_STRING; 	
		}		
	}
}

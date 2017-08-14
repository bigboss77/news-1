package com.lipop.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 将时间转换为字符串
	 * @param date  时间
	 * @param formatString  格式
	 * @return
	 */
		public static String DateTime (Date date,String formatString){
			return new SimpleDateFormat(formatString).format(date);
		}
		
		/**
		 * 将字符串转时间
		 * @param dataString
		 * @param formatString
		 * @return
		 * @throws ParseException
		 */
		public static Date DateString (String dataString ,String formatString) throws ParseException{
			return new SimpleDateFormat(formatString).parse(dataString);
		}
}

package com.lipop.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * ��ʱ��ת��Ϊ�ַ���
	 * @param date  ʱ��
	 * @param formatString  ��ʽ
	 * @return
	 */
		public static String DateTime (Date date,String formatString){
			return new SimpleDateFormat(formatString).format(date);
		}
		
		/**
		 * ���ַ���תʱ��
		 * @param dataString
		 * @param formatString
		 * @return
		 * @throws ParseException
		 */
		public static Date DateString (String dataString ,String formatString) throws ParseException{
			return new SimpleDateFormat(formatString).parse(dataString);
		}
}

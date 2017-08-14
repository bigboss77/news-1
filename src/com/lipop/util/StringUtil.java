package com.lipop.util;

/**
 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñÎª¿Õ
 * @author zhonglunsheng
 *
 */
public class StringUtil {
		public static boolean IsEmpty(String str){
			if (str==null||"".equals(str)) {
				return true;
			}else {
				return false;
			}
		}
		
		public static boolean IsNotEmpty(String str){
			if (str!=null && !"".equals(str)) {
				return true;
			}else{
				return false;
			}
		}
}

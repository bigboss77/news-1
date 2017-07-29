package com.lipop.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
		public static String getValue(String key){
			Properties pro = new Properties();
			InputStream in=new PropertiesUtil().getClass().getResourceAsStream("/news.properties");
			try {
				pro.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return pro.getProperty(key);
		}
}

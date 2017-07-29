package com.lipop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	
		public Connection getCon() throws Exception{
				Class.forName(PropertiesUtil.getValue("JdbcName"));
				Connection con=DriverManager.getConnection(PropertiesUtil.getValue("Url"), PropertiesUtil.getValue("UserName"), PropertiesUtil.getValue("Password"));
				return con;
		}
		
		public void getClose(Connection con){
			if (con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
}

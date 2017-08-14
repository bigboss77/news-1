package com.lipop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lipop.model.User;

public class UserDao {
		public User login(Connection con,User currentUser)throws Exception{
			User user = null;
			String sql = "select * from t_user where userName=? and password=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, currentUser.getUserName());
			statement.setString(2, currentUser.getPassword());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserName("userName");
				user.setPassword("password");
			}
			return user;
			
		}
}

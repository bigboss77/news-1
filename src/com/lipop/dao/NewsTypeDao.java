package com.lipop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.lipop.model.NewsType;

public class NewsTypeDao {
		public List<NewsType> show(Connection con)throws Exception{
			List<NewsType> newsTypesList =new ArrayList<NewsType>();
			String sql = "select* from t_newsType";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while(rs.next()){
				NewsType newsType = new NewsType();
				newsType.setNewsTypeId(rs.getInt("newsTypeId"));
				newsType.setTypeName(rs.getString("TypeName"));
				newsTypesList.add(newsType);
			}
			return newsTypesList;
		}
		
		public NewsType newsTypeName(Connection con,int newsTypeId)throws Exception{
			NewsType newsType = new NewsType();
			String sql = "select * from t_newsType where newsTypeId=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, newsTypeId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				newsType.setTypeName(rs.getString("typeName"));
				newsType.setNewsTypeId(rs.getInt("newsTypeId"));
			}
			return newsType;
		}
		
		/**
		 * 后台添加新闻类别
		 * @param con
		 * @param newsType
		 * @return
		 * @throws Exception
		 */
		public int addNewsType(Connection con,String newsTypeName)throws Exception{
			String sql = "insert into t_newsType values(null,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, newsTypeName);
			return statement.executeUpdate();
		}
		
		public boolean newsTypeExistNews (Connection con,int newsTypeId) throws Exception{
			int total = 0;
			String sql = "SELECT COUNT(*) as total FROM t_news,t_newstype WHERE t_news.`typeId`=t_newstype.`newsTypeId` AND t_newstype.`newsTypeId`=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, newsTypeId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				total = rs.getInt("total");
			}
			if (total>0) {
				return true;
			} else {
				return false;
			}
		}
		/**
		 * 后台删除新闻类别
		 * @param con
		 * @param newsTypeId
		 * @return
		 * @throws Exception
		 */
		public int deleteNewsType(Connection con,int newsTypeId)throws Exception{
			String sql = "delete from t_newsType where newsTypeId=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, newsTypeId);
			return statement.executeUpdate();
		}
		
		/**
		 * 后台新闻类别的修改
		 * @param con
		 * @param newsTypeId
		 * @return
		 * @throws Exception
		 */
		public int updateNewsType(Connection con,NewsType newsType)throws Exception{
			String sql = "update t_newsType set typeName=? where newsTypeId=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, newsType.getTypeName());
			statement.setInt(2, newsType.getNewsTypeId());
			return statement.executeUpdate();
		}
		
		public NewsType typeById(Connection con,int newsTypeId)throws Exception{
			NewsType newsType = new NewsType();
			String sql = "select * from t_newsType where newsTypeId=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, newsTypeId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				newsType.setNewsTypeId(rs.getInt("newsTypeId"));
				newsType.setTypeName(rs.getString("typeName"));
			}
			return newsType;
		}
}

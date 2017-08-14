package com.lipop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lipop.model.News;
import com.lipop.model.PageBean;
import com.lipop.util.DateUtil;
import com.lipop.util.PropertiesUtil;
import com.lipop.util.StringUtil;
import com.sun.crypto.provider.RSACipher;

public class NewsDao {
		public List<News> show(Connection con,String sql)throws Exception{
			List<News> newsList = new ArrayList<News>();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				News news = new News();
				news.setNewId(rs.getInt("newId"));
				news.setAuthor(rs.getString("author"));
				news.setClick(rs.getInt("click"));
				news.setImageName(PropertiesUtil.getValue("imagePath")+rs.getString("imageName"));
				news.setIsHead(rs.getInt("isHead"));
				news.setIsHot(rs.getInt("isHot"));
				news.setIsImage(rs.getInt("isImage"));
				news.setPublishDate(DateUtil.DateString(rs.getString("publishDate"), "yyyy-MM-hh hh:mm:ss"));
				news.setContent(rs.getString("content"));
				news.setTitle(rs.getString("title"));
				news.setTypeId(rs.getInt("typeId"));
				newsList.add(news);
			}
			return newsList;
		}
		
		public List<News> newsListWithType (Connection con,PageBean pageBean,News news)throws Exception{
			List<News> newList = new ArrayList<News>();
			
			String sql = "SELECT * FROM t_news,t_newsType WHERE t_news.typeId=t_newsType.newsTypeId AND typeId=? ORDER BY publishDate DESC LIMIT ?,?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, news.getTypeId());
			statement.setInt(2, pageBean.getPageStart());
			statement.setInt(3, pageBean.getPageSize());
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				News news2 = new News();
				news2.setNewId(rs.getInt("newId"));
				news2.setTitle(rs.getString("title"));
				news2.setPublishDate(DateUtil.DateString(rs.getString("publishDate"), "yyyy-MM-dd"));
				newList.add(news2);
			}
			return newList;
		}
		
		public int newsTotalWithType(Connection con,News news)throws Exception{
			int total =0;
			String sql = "SELECT count(*) as total FROM t_news,t_newsType WHERE t_news.typeId=t_newsType.newsTypeId AND typeId=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, news.getTypeId());
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				total = rs.getInt("total");
			}
			return total;
		}
		
		public News newsShow(Connection con,int newId)throws Exception{
			News news = new News();
			String sql = "SELECT * FROM t_news,t_newsType WHERE t_news.`typeId`=t_newsType.`newsTypeId` AND t_news.`newId` = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, newId);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				news.setNewId(rs.getInt("newId"));
				news.setAuthor(rs.getString("author"));
				news.setClick(rs.getInt("click"));
				news.setTypeName(rs.getString("typeName"));
				news.setImageName(PropertiesUtil.getValue("imagePath")+rs.getString("imageName"));
				news.setIsHead(rs.getInt("isHead"));
				news.setIsHot(rs.getInt("isHot"));
				news.setIsImage(rs.getInt("isImage"));
				news.setPublishDate(DateUtil.DateString(rs.getString("publishDate"), "yyyy-MM-hh hh:mm:ss"));
				news.setContent(rs.getString("content"));
				news.setTitle(rs.getString("title"));
				news.setTypeId(rs.getInt("typeId"));
			}
			return news;
		}
		
		public void newsClick (Connection con,int newId)throws Exception{
			String sql = "update t_news set click=click+1 where newId=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, newId);
			statement.executeUpdate();
		}
		
		public boolean isExistIp(Connection con,String AddressName,int newId)throws Exception{
			String sql = "select * from t_ip,t_news where t_ip.newId=t_news.newId and t_ip.AddressName=? AND t_news.`newId`=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, AddressName);
			statement.setInt(2, newId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return true;
			}else {
				return false;
			}
			
		}
		
		public void addNewsIp(Connection con,String AddressName,int newId)throws Exception{
			String sql = "insert into t_ip values(null,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, newId);
			statement.setString(2, AddressName);
			statement.executeUpdate();
		}
		
		/**
		 * 后台添加新闻
		 * @param con
		 * @param news
		 * @return
		 * @throws Exception
		 */
		public int addNews(Connection con,News news)throws Exception{
			String sql = "insert into t_news values(null,?,?,now(),?,?,?,?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, news.getTitle());
			statement.setString(2, news.getContent());
			statement.setString(3, news.getAuthor());
			statement.setInt(4, news.getTypeId());
			statement.setInt(5, news.getClick());
			statement.setInt(6, news.getIsHead());
			statement.setInt(7, news.getIsImage());
			statement.setString(8, news.getImageName());
			statement.setInt(9, news.getIsHot());
			return statement.executeUpdate();
		}
		
		/**
		 * 删除新闻
		 * @param con
		 * @param newId
		 * @return
		 * @throws Exception
		 */
		public int deleteNews(Connection con,int newId)throws Exception{
			String sql = "delete from t_news where newId = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, newId);
			return statement.executeUpdate();
		}
		
		//更新新闻
		public int updateNews(Connection con,News news)throws Exception{
			String sql = "update t_news set title=?,content=?,publishDate=now(),author=?,"
					+ "typeId=?,click=0,isHead=?,isImage=?,imageName=?,isHot=? where newId=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, news.getTitle());
			statement.setString(2, news.getContent());
			statement.setString(3, news.getAuthor());
			statement.setInt(4, news.getTypeId());
			statement.setInt(5, news.getIsHead());
			statement.setInt(6, news.getIsImage());
			statement.setString(7, news.getImageName());
			statement.setInt(8, news.getIsHot());
			statement.setInt(9, news.getNewId());
			return statement.executeUpdate();
		}
		
		/**
		 * 后台显示新闻列表加查询分页
		 * @param con
		 * @param sql
		 * @return
		 * @throws Exception
		 */
		public List<News> newsShow(Connection con,String beginTime,String endTime,String title,PageBean pageBean)throws Exception{
			StringBuffer sb = new StringBuffer("select * from t_news,t_newsType");
			if (StringUtil.IsNotEmpty(beginTime)) {
				sb.append(" and TO_DAYS(publishDate)>=TO_DAYS('"+beginTime+"')");
			}
			if (StringUtil.IsNotEmpty(endTime)) {
				sb.append(" and TO_DAYS(publishDate)<=TO_DAYS('"+endTime+"')");
			}
			if (StringUtil.IsNotEmpty(title)) {
				sb.append(" and title like'%"+title+"%'");
			}
			sb.append(" and t_news.typeId=t_newsType.newsTypeId order by publishDate desc");
			if (pageBean!=null) {
				sb.append(" limit ?,?");
			}
			List<News> newsList = new ArrayList<News>();
			PreparedStatement statement = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
			statement.setInt(1, pageBean.getPageStart());
			statement.setInt(2, pageBean.getPageSize());
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				News news = new News();
				news.setNewId(rs.getInt("newId"));
				news.setPublishDate(DateUtil.DateString(rs.getString("publishDate"), "yyyy-MM-dd hh:mm:ss"));
				news.setTitle(rs.getString("title"));
				news.setTypeName(rs.getString("typeName"));
				newsList.add(news);
			}
			return newsList;
		}
		
		/**
		 * 根据查询条件的不同查总数
		 * @param con
		 * @param beginTime
		 * @param endTime
		 * @param title
		 * @return
		 * @throws Exception
		 */
		public int newsTotalWithPage(Connection con,String beginTime,String endTime,String title)throws Exception{
			StringBuffer sb = new StringBuffer("select count(*) as total from t_news,t_newsType");
			int total = 0;
			if (StringUtil.IsNotEmpty(beginTime)) {
				sb.append(" and TO_DAYS(publishDate)>=TO_DAYS('"+beginTime+"')");
			}
			if (StringUtil.IsNotEmpty(endTime)) {
				sb.append(" and TO_DAYS(publishDate)<=TO_DAYS('"+endTime+"')");
			}
			if (StringUtil.IsNotEmpty(title)) {
				sb.append(" and title like'%"+title+"%'");
			}
			sb.append(" and t_news.typeId=t_newsType.newsTypeId order by publishDate desc");
			PreparedStatement statement = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				total=rs.getInt("total");
			}
			return total;
		}
		
		public List<News> getUpAndDownPageId(Connection con,int NewId)throws Exception{
			List<News> newsList = new ArrayList<>();
			String sql = "select * from t_news where newId>? order by newId ASC limit 1";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, NewId);
			ResultSet rs = statement.executeQuery();
			News news = new News();
			if (rs.next()) {
				news.setTitle(rs.getString("title"));
				news.setNewId(rs.getInt("newId"));
				newsList.add(news);
			}else{
				news.setTitle("");
				news.setNewId(-1);
				newsList.add(news);
			}
			
			sql ="select * from t_news where newId<? order by newId desc limit 1";
			statement = con.prepareStatement(sql);
			statement.setInt(1, NewId);
			rs=statement.executeQuery();
			News news2 = new News();
			if (rs.next()) {
				news2.setTitle(rs.getString("title"));
				news2.setNewId(rs.getInt("newId"));
				newsList.add(news2);
			}else{
				news2.setTitle("");
				news2.setNewId(-1);
				newsList.add(news2);
			}
			return newsList;
		}
		
}



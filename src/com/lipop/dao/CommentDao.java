package com.lipop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lipop.model.Comment;
import com.lipop.model.PageBean;
import com.lipop.util.DateUtil;
import com.lipop.util.StringUtil;

public class CommentDao {
	/**
	 * 新闻列表下显示评论
	 * @param con
	 * @param comment
	 * @return
	 * @throws Exception
	 */
		public List<Comment> commentShow(Connection con,Comment comment)throws Exception{
			List<Comment> coList = new ArrayList<Comment>();
			StringBuffer sb = new StringBuffer("select * from t_comment");
			if (comment.getNewId()!=-1) {
				sb.append(" and newId=?");
			}
			sb.append(" order by commentDate desc");
			PreparedStatement statement = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
			statement.setInt(1, comment.getNewId());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Comment com = new Comment();
				com.setUserIp(rs.getString("userIp"));
				com.setContent(rs.getString("content"));
				com.setCommentDate(DateUtil.DateString(rs.getString("commentDate"), "yyyy-MM-dd HH:mm:ss"));
				coList.add(com);
			}
			return coList;
		}
		
		public void addComment(Connection con,Comment comment)throws Exception{
			String sql = "insert into t_comment values(null,?,?,now(),?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, comment.getNewId());
			statement.setString(2, comment.getUserIp());
			statement.setString(3, comment.getContent());
			statement.executeUpdate();
		}
		
		/**
		 * 后台全部评论列表
		 * @param con
		 * @param comment
		 * @return
		 * @throws Exception
		 */
		public List<Comment> commentList(Connection con,String begainTime,String endTime,PageBean page)throws Exception{
			List<Comment> coList = new ArrayList<Comment>();
			StringBuffer sb = new StringBuffer("select * from t_comment,t_news");
			sb.append(" and t_comment.newId=t_news.newId");
			if (StringUtil.IsNotEmpty(begainTime)) {
				sb.append(" and TO_DAYS(commentDate)>=TO_DAYS('"+begainTime+"')");
			}
			if (StringUtil.IsNotEmpty(endTime)) {
				sb.append(" and TO_DAYS(commentDate)<=TO_DAYS('"+endTime+"')");
			}
			sb.append(" order by commentDate desc");
			if (page.getPage()!=-1) {
				sb.append(" limit ?,?");
			}
			PreparedStatement statement = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
			statement.setInt(1, page.getPageStart());
			statement.setInt(2, page.getPageSize());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Comment com = new Comment();
				com.setCommentId(rs.getInt("commentId"));
				com.setNewId(rs.getInt("newId"));
				com.setNewsName(rs.getString("title"));
				com.setUserIp(rs.getString("userIp"));
				com.setContent(rs.getString("content"));
				com.setCommentDate(DateUtil.DateString(rs.getString("commentDate"), "yyyy-MM-dd HH:mm:ss"));
				coList.add(com);
			}
			return coList;
		}
		
		public int commentsTotal(Connection con,String begainTime,String endTime)throws Exception{
			int total=0;
			StringBuffer sb = new StringBuffer("select count(*) as total from t_comment");
			if (StringUtil.IsNotEmpty(begainTime)) {
				sb.append(" and TO_DAYS(commentDate)>=TO_DAYS('"+begainTime+"')");
			}
			if (StringUtil.IsNotEmpty(endTime)) {
				sb.append(" and TO_DAYS(commentDate)<=TO_DAYS('"+endTime+"')");
			}
			PreparedStatement statement = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				total=rs.getInt("total");
			}
			return total;
		} 
		
		
		//有问题，怎么数据库里面能删除多条，这里只能删除一条呢？？
		public int deleteComment(Connection con,String commentId)throws Exception{
			String sql = "delete from t_comment where commentId in ("+commentId+")";
			PreparedStatement statement = con.prepareStatement(sql);
			//statement.setString(1, commentId);
			return statement.executeUpdate();
		}
}

package com.lipop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lipop.model.Link;

public class LinkDao {
	/**
	 * �������ӵ���ʾ
	 * @param con
	 * @param sql
	 * @return
	 * @throws Exception
	 */
		public List<Link> show(Connection con)throws Exception{
			List<Link>linkList = new ArrayList<Link>();
			String sql = "select * from t_link order by orderNum";
			PreparedStatement pre = con.prepareStatement(sql);
			ResultSet rs=pre.executeQuery();
			while(rs.next()){
				Link link = new Link();
				link.setLinkId(rs.getInt("linkId"));
				link.setLinkName(rs.getString("linkName"));
				link.setLinkUrl(rs.getString("linkUrl"));
				link.setLinkEmail(rs.getString("linkEmail"));
				link.setOrderNum(rs.getInt("orderNum"));
				linkList.add(link);
			}
			return linkList;
		}
		
		/**
		 * �������ӵ����
		 * @param con
		 * @param link
		 * @return
		 * @throws Exception
		 */
		public int addLink (Connection con,Link link)throws Exception{
			String sql = "insert into t_link values(null,?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, link.getLinkName());
			statement.setString(2, link.getLinkUrl());
			statement.setString(3, link.getLinkEmail());
			statement.setInt(4, link.getOrderNum());
			return statement.executeUpdate();
		}
		
		/**
		 * �޸���������
		 * @param con
		 * @param link
		 * @return
		 * @throws Exception
		 */
		public int updateLink(Connection con,Link link)throws Exception{
			String sql = "update t_link set linkName=?,linkUrl=?,linkEmail=?,orderNum=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, link.getLinkName());
			statement.setString(2, link.getLinkUrl());
			statement.setString(3, link.getLinkEmail());
			statement.setInt(4, link.getOrderNum());
			return statement.executeUpdate();
		}
		
		/**
		 * ͨ����������id��ȡ��Ϣ
		 * @param con
		 * @param linkId
		 * @return
		 * @throws Exception
		 */
		public Link getLinkById(Connection con,int linkId)throws Exception{
			String sql = "select * from t_link where linkId=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, linkId);
			ResultSet rs = statement.executeQuery();
			Link link = null;
			if (rs.next()) {
				link = new Link();
				link.setLinkId(rs.getInt("linkId"));
				link.setLinkName(rs.getString("linkName"));
				link.setLinkUrl(rs.getString("linkUrl"));
				link.setLinkEmail(rs.getString("linkEmail"));
				link.setOrderNum(rs.getInt("orderNum"));
			}
			return link;
		}
		
		public int deleteLink (Connection con,int linkId)throws Exception{
			String sql = "delete from t_link where linkId=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, linkId);
			return statement.executeUpdate();
		}
}

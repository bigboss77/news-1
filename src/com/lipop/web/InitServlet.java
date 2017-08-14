package com.lipop.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lipop.dao.NewsDao;
import com.lipop.dao.NewsTypeDao;
import com.lipop.model.News;
import com.lipop.model.NewsType;
import com.lipop.util.DbUtil;
import com.lipop.util.ResponseUtil;

import net.sf.json.JSONObject;

public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil = new DbUtil();
	NewsTypeDao newsTypeDao = new NewsTypeDao();
	NewsDao newsDao = new NewsDao();
	Connection con = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext application = config.getServletContext();
		refreshSystem(application);
	}
	
	private void refreshSystem(ServletContext application){
		try {
			con=dbUtil.getCon();
			
			//新闻分类列表
			List<NewsType> newsTypeList = newsTypeDao.show(con);
			application.setAttribute("newsTypeList", newsTypeList);
			
			//热点新闻
			String sql = "select * from t_news order by click desc limit 0,8";
			List<News> HotNewsList = newsDao.show(con, sql);
			application.setAttribute("HotNewsList", HotNewsList);
			
			//最新新闻
			sql = "select * from t_news order by publishDate desc limit 0,8";
			List<News> updateNews = newsDao.show(con, sql);
			application.setAttribute("updateNews", updateNews);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbUtil.getClose(con);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext application = session.getServletContext();
		this.refreshSystem(application);
		JSONObject object = new JSONObject();
		object.put("success", true);
		try {
			ResponseUtil.write(object, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
			
}

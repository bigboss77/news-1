package com.lipop.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lipop.dao.LinkDao;
import com.lipop.dao.NewsDao;
import com.lipop.dao.NewsTypeDao;
import com.lipop.model.Link;
import com.lipop.model.News;
import com.lipop.model.NewsType;
import com.lipop.util.DbUtil;


public class IndexServlet extends HttpServlet {
	NewsDao newsDao = new NewsDao();
	NewsTypeDao newsTypeDao = new NewsTypeDao();
	LinkDao linkDao = new LinkDao();
	DbUtil dbUtil = new DbUtil();
	Connection con = null;
	String sql=null;
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			con = dbUtil.getCon();
			
			//新闻分类列表
			List<NewsType> newsTypeList = newsTypeDao.show(con);
			
			//新闻图片显示
			sql ="select * from t_news where isImage=1 order by publishDate desc limit 0,5";
			List<News> imageNews = newsDao.show(con, sql);
			request.setAttribute("imageNews", imageNews);
			
			//新闻头条
			sql ="select * from t_news where isHead=1 order by publishDate desc"; 
			List<News> headNews = newsDao.show(con, sql);
			News aHeadNews = headNews.get(0);
			request.setAttribute("aHeadNews", aHeadNews);
			
			
			//热点新闻
			sql ="select * from t_news where isHot=1 order by publishDate desc limit 0,8";
			List<News> HotNews = newsDao.show(con, sql);
			request.setAttribute("HotNews", HotNews);
			
			//新闻列表
			@SuppressWarnings("rawtypes")
			List newsList = new ArrayList();
			if (newsTypeList!=null &&  newsTypeList.size()>0) {
				for(int i=0; i<newsTypeList.size(); i++){
					NewsType newsType = newsTypeList.get(i);
					sql="select * from t_news,t_newsType where typeId=newsTypeId and typeId="+newsType.getNewsTypeId()+" limit 0,8";
					List<News> news = newsDao.show(con, sql);
					newsList.add(news);
				}
			request.setAttribute("allIndexNewsList", newsList);
			}
			
			//友情链接
			List<Link> linkList = new ArrayList<Link>();
			linkList = linkDao.show(con);
			request.setAttribute("linkList", linkList);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbUtil.getClose(con);
		}
	}

}

package com.lipop.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lipop.dao.NewsTypeDao;
import com.lipop.model.NewsType;
import com.lipop.util.DbUtil;
import com.lipop.util.NavUtil;
import com.lipop.util.ResponseUtil;
import com.lipop.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class NewsTypeServlet
 */
public class NewsTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    DbUtil dbUtil = new DbUtil();
    NewsTypeDao newsTypeDao = new NewsTypeDao();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if ("preSave".equals(action)) {
			preSave(request,response);
		}else if ("save".equals(action)) {
			save(request,response);
		}else if ("backList".equals(action)) {
			newsTypeBackList(request,response);
		}else if("delete".equals(action)){
			delete(request,response);
		}
	}
	
	/**
	 * 新闻类别查看
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void preSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newsTypeId = request.getParameter("newsTypeId");
		String navCode = null;
		Connection con = null;
		if (StringUtil.IsNotEmpty(newsTypeId)) {
			navCode = NavUtil.genNewsManageNavigation("新闻类别管理", "新闻类别修改");
			try {
				con = dbUtil.getCon();
				NewsType newsType = newsTypeDao.typeById(con, Integer.parseInt(newsTypeId));
				request.setAttribute("newsType", newsType);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				dbUtil.getClose(con);
			}
		}else {
			navCode = NavUtil.genNewsManageNavigation("新闻类别管理", "新闻类别添加");
		}
		request.setAttribute("navCode", navCode);
		request.setAttribute("mainPage", "/background/newsType/newsTypeSave.jsp");
		request.getRequestDispatcher("background/mainTemp.jsp").forward(request, response);
	}
	
	/**
	 * 新闻类别增加和更新
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newsTypeId = request.getParameter("newsTypeId");
		String typeName = request.getParameter("typeName");
		Connection con = null;
		try {
			con=dbUtil.getCon();
			if (StringUtil.IsNotEmpty(newsTypeId)) {
				NewsType newsType = new NewsType(Integer.parseInt(newsTypeId),typeName);
				newsTypeDao.updateNewsType(con, newsType);
			}else{
				newsTypeDao.addNewsType(con, typeName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbUtil.getClose(con);
		}
		request.getRequestDispatcher("newsType?action=backList").forward(request, response);
	}
	
	/**
	 * 新闻类别的列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void newsTypeBackList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con=null;
		String navCode = null;
		try {
			con=dbUtil.getCon();
			List<NewsType> typeList = newsTypeDao.show(con);
			navCode = NavUtil.genNewsManageNavigation("新闻类别管理", "新闻类别维护");
			request.setAttribute("navCode", navCode);
			request.setAttribute("newsTypeBackList", typeList);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbUtil.getClose(con);
		}
		request.setAttribute("mainPage", "/background/newsType/newsTypeList.jsp");
		request.getRequestDispatcher("background/mainTemp.jsp").forward(request, response);
	}
	
	/**
	 * 新闻删除
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newsTypeId = request.getParameter("newsTypeId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			JSONObject object = new JSONObject();
			if (newsTypeDao.newsTypeExistNews(con, Integer.parseInt(newsTypeId))) {
				object.put("errorMsg", "改类别下有新闻，不能删除");
			}else {
				newsTypeDao.deleteNewsType(con, Integer.parseInt(newsTypeId));
				object.put("success", true);
			}
			ResponseUtil.write(object, response);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbUtil.getClose(con);
		}
	}


}

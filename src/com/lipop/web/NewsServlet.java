package com.lipop.web;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.lipop.dao.CommentDao;
import com.lipop.dao.NewsDao;
import com.lipop.dao.NewsTypeDao;
import com.lipop.model.Comment;
import com.lipop.model.News;
import com.lipop.model.NewsType;
import com.lipop.model.PageBean;
import com.lipop.util.DbUtil;
import com.lipop.util.IpUtil;
import com.lipop.util.NavUtil;
import com.lipop.util.PageUtil;
import com.lipop.util.PropertiesUtil;
import com.lipop.util.ResponseUtil;
import com.lipop.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class NewsServlet
 */
public class NewsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    DbUtil dbUtil = new DbUtil();
    NewsDao newsDao = new NewsDao();
    NewsTypeDao newsTypeDao = new NewsTypeDao();
    CommentDao commentDao = new CommentDao();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if ("list".equals(action)) {
			newsList(request,response);
		}else if ("show".equals(action)) {
			newsShow(request,response);
		}else if ("preSave".equals(action)) {
			preSave(request,response);
		}else if ("save".equals(action)) {
			save(request,response);
		}else if ("backList".equals(action)) {
			backList(request,response);
		}else if ("delete".equals(action)) {
			delete(request,response);
		}
	}

	private void newsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int newsTypeId =Integer.parseInt(request.getParameter("typeId"));
		String page = request.getParameter("page");
		String pageCode = null;
		News news = new News();
		PageBean pageBean = new PageBean();
		Connection con=null;
		try {
			con=dbUtil.getCon();
			news.setTypeId(newsTypeId);
			int total = newsDao.newsTotalWithType(con, news);
			if (StringUtil.IsEmpty(page)) {
				page="1";
			}
			pageCode=PageUtil.page(total,Integer.parseInt(page),newsTypeId);
			pageBean.setPage(Integer.parseInt(page));
			pageBean.setPageSize(Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			List<News> newestNewsListWithType = newsDao.newsListWithType(con, pageBean, news);
			
			NewsType newsType = newsTypeDao.newsTypeName(con,newsTypeId);
	        String navCode = NavUtil.navCode(newsType);
			request.setAttribute("newestNewsListWithType", newestNewsListWithType);
			request.setAttribute("navCode", navCode);
			request.setAttribute("pageCode", pageCode);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbUtil.getClose(con);
		}
		request.setAttribute("mainPage", "/foreground/news/newsList.jsp");
		request.getRequestDispatcher("foreground/news/newsTemple.jsp").forward(request, response);
	}
	
	private void newsShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newsId = request.getParameter("newsId");
		String ip = IpUtil.getIp(request);
		String navCode = null;
		String pageCode = null;
		Connection con = null;
		try {
			con=dbUtil.getCon();
			if (StringUtil.IsNotEmpty(ip)) {
				if (!newsDao.isExistIp(con, ip, Integer.parseInt(newsId))) {
					newsDao.newsClick(con, Integer.parseInt(newsId));
					newsDao.addNewsIp(con, ip, Integer.parseInt(newsId));;
				}
			}
			News news = newsDao.newsShow(con, Integer.parseInt(newsId));
			navCode = NavUtil.genNewsNavigation(news.getTypeName(),news.getTypeId(),news.getTitle());
			pageCode = getUpAndDownPageCode(newsDao.getUpAndDownPageId(con, Integer.parseInt(newsId)));
			Comment comment = new Comment();
			comment.setNewId(Integer.parseInt(newsId));
			List<Comment> coList = commentDao.commentShow(con, comment);
			request.setAttribute("news", news);
			request.setAttribute("coList", coList);
		} catch (Exception e) {
			e.printStackTrace();
			dbUtil.getClose(con);
		}
		request.setAttribute("pageCode", pageCode);
		request.setAttribute("navCode", navCode);
		request.setAttribute("mainPage", "/foreground/news/newsShow.jsp");
		request.getRequestDispatcher("foreground/news/newsTemple.jsp").forward(request, response);
	}
	
	/**
	 * 后台新闻添加的跳转
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void preSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newId = request.getParameter("newId");
		String navCode = null;
		Connection con = null;
		if (StringUtil.IsEmpty(newId)) {
			navCode = NavUtil.genNewsManageNavigation("新闻管理", "新闻添加");
		}else{
			navCode = NavUtil.genNewsManageNavigation("新闻管理", "新闻修改");
			try {
				con = dbUtil.getCon();
				News news = newsDao.newsShow(con, Integer.parseInt(newId));
				request.setAttribute("news", news);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				dbUtil.getClose(con);
			}
		}
		request.setAttribute("navCode", navCode);
		request.setAttribute("mainPage", "/background/news/newsSave.jsp");
		request.getRequestDispatcher("background/mainTemp.jsp").forward(request, response);
	}
	
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		Connection con = null;
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> items = upload.parseRequest(request);
			News news = new News();
			for (FileItem fileItem : items) {
				if (fileItem.isFormField()) {
					String fileName = fileItem.getFieldName();
					if ("title".equals(fileName)) {
						news.setTitle(fileItem.getString("utf-8"));
					}
					if ("author".equals(fileName)) {
						news.setAuthor(fileItem.getString("utf-8"));
					}
				   if ("typeId".equals(fileName)) {
						news.setTypeId(Integer.parseInt(fileItem.getString("utf-8")));
					}
				   if ("isHead".equals(fileName)) {
					   news.setIsHead(Integer.parseInt(fileItem.getString("utf-8")));
				   }
				   if ("isHot".equals(fileName)) {
					   news.setIsHot(Integer.parseInt(fileItem.getString("utf-8")));
				   }
				   if ("content".equals(fileName)) {
					   news.setContent(fileItem.getString("utf-8"));
				   }
				   if ("isImage".equals(fileName)) {
					   news.setIsImage(Integer.parseInt(fileItem.getString("utf-8")));
				   }
				   if ("newId".equals(fileName)) {
					   if (StringUtil.IsNotEmpty(fileItem.getString("utf-8"))) {
						   news.setNewId(Integer.parseInt(fileItem.getString("utf-8")));
					}
				}
				}else if (StringUtil.IsNotEmpty(fileItem.getName())){
					File file = new File(PropertiesUtil.getValue("HeadImagePath")+fileItem.getName());
					news.setImageName(fileItem.getName());
					fileItem.write(file);
				}
			}
			con=dbUtil.getCon();
			if (news.getNewId()!=0) {
				newsDao.updateNews(con, news);
			}else {
				newsDao.addNews(con, news);
			}
			response.sendRedirect("news?action=backList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbUtil.getClose(con);
		}
	}
	
	/**
	 * 后台新闻列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void backList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		HttpSession session = request.getSession();
		String page = request.getParameter("page");
		String begainTime = request.getParameter("s_bPublishDate");
		String endTime = request.getParameter("s_aPublishDate");
		String s_title = request.getParameter("s_title");
		if (StringUtil.IsEmpty(page)) {
			page="1";
			session.setAttribute("begainTime", begainTime);
			session.setAttribute("endTime", endTime);
			session.setAttribute("s_title", s_title);
		}else {
			begainTime = (String)session.getAttribute("begainTime");
			endTime = (String)session.getAttribute("endTime");
			s_title = (String)session.getAttribute("s_title");
		}
		
		try {
			con=dbUtil.getCon();
			int total = newsDao.newsTotalWithPage(con, begainTime, endTime, s_title);
			PageBean pageBean = new PageBean();
			pageBean.setPage(Integer.parseInt(page));
			pageBean.setPageSize(Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			List<News> newsList = newsDao.newsShow(con,begainTime,endTime,s_title,pageBean);
			String navCode = NavUtil.genNewsManageNavigation("新闻管理", "新闻维护");
			String pageCode = PageUtil.newsPage(total, Integer.parseInt(page));
			request.setAttribute("navCode", navCode);
			request.setAttribute("newsBackList", newsList);
			request.setAttribute("pageCode", pageCode);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbUtil.getClose(con);
		}
		request.setAttribute("mainPage", "/background/news/newsList.jsp");
		request.getRequestDispatcher("background/mainTemp.jsp").forward(request, response);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newId = request.getParameter("newId");
		Connection con =null;
		try {
			con = dbUtil.getCon();
			if (StringUtil.IsNotEmpty(newId)) {
				int result = newsDao.deleteNews(con, Integer.parseInt(newId));
				JSONObject object = new JSONObject();
				if (result>0) {
					object.put("success", true);
				}else{
					object.put("errorMsg", "删除失败");
				}
				ResponseUtil.write(object, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbUtil.getClose(con);
		}
	}
	
	protected String getUpAndDownPageCode(List<News> newsList){
		StringBuffer pageCode = new StringBuffer();
		News downNews = newsList.get(0);
		News upNews = newsList.get(1);
		
		if (upNews.getNewId()==-1) {
			pageCode.append("<p>上一篇：没有了</p>");
		}else{
			pageCode.append("<p><a href='news?action=show&newsId="+upNews.getNewId()+"'>上一篇："+upNews.getTitle()+"</a></p>");
		}
		
		if (downNews.getNewId()==-1) {
			pageCode.append("<p>下一篇：没有了</p>");
		}else{
			pageCode.append("<p><a href='news?action=show&newsId="+downNews.getNewId()+"'>下一篇："+downNews.getTitle()+"</a></p>");
		}
		
		return pageCode.toString();
	}
	
	
}

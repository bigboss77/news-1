package com.lipop.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lipop.dao.CommentDao;
import com.lipop.model.Comment;
import com.lipop.model.PageBean;
import com.lipop.util.DbUtil;
import com.lipop.util.IpUtil;
import com.lipop.util.NavUtil;
import com.lipop.util.PageUtil;
import com.lipop.util.PropertiesUtil;
import com.lipop.util.ResponseUtil;
import com.lipop.util.StringUtil;

import net.sf.json.JSONObject;


public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    DbUtil dbUtil = new DbUtil();
    CommentDao commentDao = new CommentDao();   
    public CommentServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if ("save".equals(action)) {
			commentSave(request,response);
		}else if ("backList".equals(action)) {
			commentList(request,response);
		}else if ("delete".equals(action)) {
			deleteList(request,response);
		}
	}


	private void commentSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		String content = request.getParameter("content");
		String newId = request.getParameter("newsId");
		String userIp = IpUtil.getIp(request);
		Connection con = null;
		try {
			con=dbUtil.getCon();
			Comment comment = new Comment(Integer.parseInt(newId),userIp,content);
			commentDao.addComment(con, comment);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbUtil.getClose(con);
		}
		request.getRequestDispatcher("news?action=show&newsId="+Integer.parseInt(newId)).forward(request, response);
	}
	
	protected void commentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String navCode = null;
		Connection con = null;
		String begainTime = request.getParameter("s_bCommentDate");
		String endTime = request.getParameter("s_aCommentDate");
		String page = request.getParameter("page");
		PageBean pageBean = new PageBean();
		HttpSession session = request.getSession();
		if (StringUtil.IsEmpty(page)) {
			page="1";
			session.setAttribute("s_bCommentDate", begainTime);
			session.setAttribute("s_aCommentDate", endTime);
		}else{
			begainTime = (String)session.getAttribute("s_bCommentDate");
			endTime = (String)session.getAttribute("s_aCommentDate");
		}
		pageBean.setPage(Integer.parseInt(page));
		pageBean.setPageSize(Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		
		List<Comment> commentBackList = null;
		try {
			con=dbUtil.getCon();
			commentBackList =commentDao.commentList(con,begainTime,endTime,pageBean);
			navCode = NavUtil.genNewsManageNavigation("新闻评论管理", "新闻评论维护");
			int total = commentDao.commentsTotal(con,begainTime,endTime);
			String pageCode = PageUtil.commentPage(total, Integer.parseInt(page));
			request.setAttribute("navCode", navCode);
			request.setAttribute("pageCode", pageCode);
			request.setAttribute("commentBackList", commentBackList);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbUtil.getClose(con);
		}
		request.setAttribute("mainPage", "/background/comment/commentList.jsp");
		request.getRequestDispatcher("background/mainTemp.jsp").forward(request, response);
	}
	
	/**
	 * 评论删除
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commentId = request.getParameter("commentId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			JSONObject object = new JSONObject();
			int delNums = commentDao.deleteComment(con,commentId);
			if (delNums>0) {
				object.put("success", true);
				object.put("delNums", delNums);
			}else{
				object.put("errorMsg", "删除失败");
			}
			ResponseUtil.write(object, response);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbUtil.getClose(con);
		}
	}

}

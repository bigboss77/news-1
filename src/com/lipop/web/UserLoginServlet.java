package com.lipop.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lipop.dao.UserDao;
import com.lipop.model.User;
import com.lipop.util.DbUtil;
import com.lipop.util.EncoderPwdByMd5;

/**
 * Servlet implementation class UserLoginServlet
 */
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil = new DbUtil();
	UserDao userDao = new UserDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		 	String action = request.getParameter("action");
		 	if ("login".equals(action)) {
				userLogin(request,response);
			}else if ("logout".equals(action)) {
				logout(request,response);
			}
	}
	
	protected void userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			
			Connection con = null;
			try {
				con=dbUtil.getCon();
				User currentUser = new User(userName,EncoderPwdByMd5.pwdByMd5(password));
				User user = userDao.login(con, currentUser);
				if (user!=null) {
					request.setAttribute("user", user);
					request.setAttribute("mainPage", "/background/default.jsp");
					request.getRequestDispatcher("background/mainTemp.jsp").forward(request, response);
				}else{
					request.setAttribute("userName", userName);
					request.setAttribute("password", password);
					request.setAttribute("error", "用户名或密码错误");
					request.getRequestDispatcher("background/login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				dbUtil.getClose(con);
			}
	}
	
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	request.getSession().invalidate();
	 	response.sendRedirect("background/login.jsp");
}


}

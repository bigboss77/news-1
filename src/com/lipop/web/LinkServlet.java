package com.lipop.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.lipop.dao.LinkDao;
import com.lipop.model.Link;
import com.lipop.util.DbUtil;
import com.lipop.util.NavUtil;
import com.lipop.util.ResponseUtil;
import com.lipop.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class LinkServlet
 */
public class LinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil = new DbUtil();
	LinkDao linkDao = new LinkDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LinkServlet() {
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
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if ("preSave".equals(action)) {
			preSave(request,response);
		}else if ("save".equals(action)) {
			save(request,response);
		}else if ("backList".equals(action)) {
			linkBackList(request,response);
		}else if ("delete".equals(action)) {
			linkDelete(request,response);
		}
	}
	
	/**
	 * ��������ɾ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void linkDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String linkId = request.getParameter("linkId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			if (StringUtil.IsNotEmpty(linkId)) {
				JSONObject object = new JSONObject();
				int delNums = linkDao.deleteLink(con, Integer.parseInt(linkId));
				if (delNums>0) {
					object.put("success", true);
				}else {
					object.put("errorMsg", "ɾ��ʧ��");
				}
				ResponseUtil.write(object, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbUtil.getClose(con);
		}
	}
	/**
	 * �������ӵ�Ԥ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void preSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String linkId = request.getParameter("linkId");
		String navCode = null;
		Connection con = null;
		try {
			con = dbUtil.getCon();
			if (StringUtil.IsNotEmpty(linkId)) {
				Link link = linkDao.getLinkById(con, Integer.parseInt(linkId));
				request.setAttribute("link", link);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbUtil.getClose(con);
		}
		if (StringUtil.IsNotEmpty(linkId)) {
			navCode = NavUtil.genNewsManageNavigation("�������ӹ���", "���������޸�");
		}else{
			navCode = NavUtil.genNewsManageNavigation("�������ӹ���", "�����������");
		}
		request.setAttribute("navCode", navCode);
		request.setAttribute("mainPage", "/background/link/linkSave.jsp");
		request.getRequestDispatcher("background/mainTemp.jsp").forward(request, response);
	}
	
	/**
	 * ���������޸�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void linkBackList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		try {
			con=dbUtil.getCon();
			List<Link> linkList = linkDao.show(con);
			request.setAttribute("linkBackList", linkList);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbUtil.getClose(con);
		}
		String navCode = NavUtil.genNewsManageNavigation("�������ӹ���", "��������ά��");
		request.setAttribute("navCode", navCode);
		request.setAttribute("mainPage", "/background/link/linkList.jsp");
		request.getRequestDispatcher("background/mainTemp.jsp").forward(request, response);
	}
	
	/**
	 * �������ӵ����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		String linkId = request.getParameter("linkId");
		String linkName = request.getParameter("linkName");
		String linkUrl = request.getParameter("linkUrl");
		String linkEmail = request.getParameter("linkEmail");
		String orderNum = request.getParameter("orderNum");
		
		Link link = new Link(linkName, linkUrl, linkEmail, Integer.parseInt(orderNum));
		try {
			con = dbUtil.getCon();
			if (StringUtil.IsEmpty(linkId)) {
				linkDao.addLink(con, link);
				request.setAttribute("error", "����������ӳɹ�");
			}else{
				linkDao.updateLink(con, link);
				request.getRequestDispatcher("link?action=backList").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", "�����������ʧ��");
			request.setAttribute("link", link);
			e.printStackTrace();
		}finally{
			dbUtil.getClose(con);
		}
		request.getRequestDispatcher("link?action=preSave").forward(request, response);
	}

}

package com.lipop.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.lipop.util.PropertiesUtil;


/**
 * Servlet implementation class FileUploadServlet
 */
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem fileItem : items) {
				File file = new File(PropertiesUtil.getValue("NewsImages")+fileItem.getName());
				String newPath = PropertiesUtil.getValue("newImagePath")+fileItem.getName();
				fileItem.write(file);
				String callback = request.getParameter("CKEditorFuncNum");
				out.println("<script type=\"text/javascript\">");
				out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + newPath + "',''" + ")");
				out.println("</script>");
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}

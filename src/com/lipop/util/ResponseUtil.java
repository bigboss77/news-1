package com.lipop.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
		public static void write(Object o,HttpServletResponse response){
			response.setContentType("text/html;charset=utf-8");
			try {
				PrintWriter out = response.getWriter();
				out.println(o.toString());
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}

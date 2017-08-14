package com.lipop.util;

import com.lipop.model.NewsType;

public class NavUtil {
		public static String navCode(NewsType newsType){
			String navcode = null;
			if (newsType!=null) {
				navcode="当前位置：&nbsp;&nbsp;<a href='goIndex'>首页</a>&nbsp;&nbsp;>&nbsp;&nbsp;<a href='news?action=list&typeId="+newsType.getNewsTypeId()+"'>"+newsType.getTypeName()+"</a>";
			}
			return navcode;
		}
		
		public static String genNewsNavigation(String typeName,int typeId,String newsName){
			StringBuffer navCode=new StringBuffer();
			navCode.append("当前位置：&nbsp;&nbsp;");
			navCode.append("<a href='goIndex'>主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;");
			navCode.append("<a href='news?action=list&typeId="+typeId+"'>"+typeName+"</a>&nbsp;&nbsp;>&nbsp;&nbsp;"+newsName);
			return navCode.toString();
		}
		
		public static String genNewsManageNavigation(String modName,String actionName){
			StringBuffer navCode=new StringBuffer();
			navCode.append("当前位置：&nbsp;&nbsp;");
			navCode.append("主页&nbsp;&nbsp;>&nbsp;&nbsp;");
			navCode.append(modName+"&nbsp;&nbsp;>&nbsp;&nbsp;");
			navCode.append(actionName+"&nbsp;&nbsp;");
			return navCode.toString();
		}
}

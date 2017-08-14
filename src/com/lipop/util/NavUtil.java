package com.lipop.util;

import com.lipop.model.NewsType;

public class NavUtil {
		public static String navCode(NewsType newsType){
			String navcode = null;
			if (newsType!=null) {
				navcode="��ǰλ�ã�&nbsp;&nbsp;<a href='goIndex'>��ҳ</a>&nbsp;&nbsp;>&nbsp;&nbsp;<a href='news?action=list&typeId="+newsType.getNewsTypeId()+"'>"+newsType.getTypeName()+"</a>";
			}
			return navcode;
		}
		
		public static String genNewsNavigation(String typeName,int typeId,String newsName){
			StringBuffer navCode=new StringBuffer();
			navCode.append("��ǰλ�ã�&nbsp;&nbsp;");
			navCode.append("<a href='goIndex'>��ҳ</a>&nbsp;&nbsp;>&nbsp;&nbsp;");
			navCode.append("<a href='news?action=list&typeId="+typeId+"'>"+typeName+"</a>&nbsp;&nbsp;>&nbsp;&nbsp;"+newsName);
			return navCode.toString();
		}
		
		public static String genNewsManageNavigation(String modName,String actionName){
			StringBuffer navCode=new StringBuffer();
			navCode.append("��ǰλ�ã�&nbsp;&nbsp;");
			navCode.append("��ҳ&nbsp;&nbsp;>&nbsp;&nbsp;");
			navCode.append(modName+"&nbsp;&nbsp;>&nbsp;&nbsp;");
			navCode.append(actionName+"&nbsp;&nbsp;");
			return navCode.toString();
		}
}

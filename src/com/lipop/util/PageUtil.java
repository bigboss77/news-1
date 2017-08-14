package com.lipop.util;

public class PageUtil {
	public static String page(int total,int page,int newsTypeId) {
		StringBuffer pageCode = new StringBuffer();
		int pageSize = Integer.parseInt(PropertiesUtil.getValue("pageSize"));
		int totalPageSize = total%pageSize==0?total/pageSize:(total/pageSize+1);
			if (page==1) {
				pageCode.append("<li><a href='#'>��һҳ</a></li>");
			}else{
				pageCode.append("<li><a href='news?action=list&typeId="+newsTypeId+"&page="+(page+-1)+"'>��һҳ</a></li>");
			}
			
			if (page==totalPageSize) {
				pageCode.append("<li><a href='#'>��һҳ</a></li>");
			}else{
				pageCode.append("<li><a href='news?action=list&typeId="+newsTypeId+"&page="+(page+1)+"'>��һҳ</a></li>");
			}
		return pageCode.toString();
	}
	
	public static String commentPage(int total,int page){
		StringBuffer pageCode = new StringBuffer();
		int pageSize = Integer.parseInt(PropertiesUtil.getValue("pageSize"));
		int totalPageSize = total%pageSize==0?total/pageSize:(total/pageSize+1);
			if (page==1) {
				pageCode.append("<li><a href='#'>��һҳ</a></li>");
			}else{
				pageCode.append("<li><a href='comment?action=backList&page="+(page-1)+"'>��һҳ</a></li>");
			}
			for(int i=page-1;i<=page+1;i++){
				if (i<1 || i>totalPageSize) {
					continue;
				}
				if (i==page) {
					pageCode.append("<li><a href='#'>"+i+"</a></li>");
				}else{
					pageCode.append("<li><a href='comment?action=backList&page="+i+"'>"+i+"</a></li>");
				}
			}
			if (page==totalPageSize) {
				pageCode.append("<li><a href='#'>��һҳ</a></li>");
			}else{
				pageCode.append("<li><a href='comment?action=backList&page="+(page+1)+"'>��һҳ</a></li>");
			}
		return pageCode.toString();
	}
	
	public static String newsPage(int total,int page){
		StringBuffer pageCode = new StringBuffer();
		int pageSize = Integer.parseInt(PropertiesUtil.getValue("pageSize"));
		int totalPageSize = total%pageSize==0?total/pageSize:(total/pageSize+1);
		pageCode.append("<li><a href='news?action=backList&page=1'>��ҳ</a></li>");
			if (page==1) {
				pageCode.append("<li><a href='#'>��һҳ</a></li>");
			}else{
				pageCode.append("<li><a href='news?action=backList&page="+(page-1)+"'>��һҳ</a></li>");
			}
			for(int i=page-1;i<=page+1;i++){
				if (i<1 || i>totalPageSize) {
					continue;
				}
				if (i==page) {
					pageCode.append("<li><a href='#'>"+i+"</a></li>");
				}else{
					pageCode.append("<li><a href='news?action=backList&page="+i+"'>"+i+"</a></li>");
				}
			}
			if (page==totalPageSize) {
				pageCode.append("<li><a href='#'>��һҳ</a></li>");
			}else{
				pageCode.append("<li><a href='news?action=backList&page="+(page+1)+"'>��һҳ</a></li>");
			}
		pageCode.append("<li><a href='news?action=backList&page="+totalPageSize+"'>βҳ</a></li>");
		return pageCode.toString();
	}
}

package com.lipop.model;


public class PageBean {
		private int pageSize;//分页显示的条数
		private int page=-1; //当前分页
		private int pageStart; //分页开始
		
		public int getPageSize() {
			return pageSize;
		}
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		public int getPageStart() {
			pageStart=(page-1)*pageSize;
			return pageStart;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		
}

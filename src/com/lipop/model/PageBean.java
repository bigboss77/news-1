package com.lipop.model;


public class PageBean {
		private int pageSize;//��ҳ��ʾ������
		private int page=-1; //��ǰ��ҳ
		private int pageStart; //��ҳ��ʼ
		
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

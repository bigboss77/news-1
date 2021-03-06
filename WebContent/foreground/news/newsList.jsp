<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="data_list">
	<div class="dataHeader navi">
		${navCode}
	</div>
	<div class="datas news_type_list">
		<ul>
			<c:forEach var="newestNews" items="${newestNewsListWithType }">
				<li><span>[<fmt:formatDate value="${newestNews.publishDate }" type="date" pattern="yyyy-MM-dd"/>]</span>&nbsp;&nbsp;<a href="news?action=show&newsId=${newestNews.newId }" title="${newestNews.title }">${fn:substring(newestNews.title,0,42) }</a></li>
			</c:forEach>
		</ul>
		<ul class="pager">
			${pageCode }
		</ul>
	</div>
</div>
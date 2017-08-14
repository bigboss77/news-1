<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<link href="${pageContext.request.contextPath}/style/news.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath }/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath }/bootstrap/js/jquery.min.js"></script>


</head>
<body>
<div class="container">
<jsp:include page="/foreground/common/head.jsp"></jsp:include>
<div class="row-fluid">
	<div class="span8">
		<jsp:include page="${mainPage }"/>
	</div>
	<div class="span4">
		<div class="data_list right_news_list">
			<div class="dataHeader">最新新闻</div>
			<div class="datas">
				<ul>
					<c:forEach var="newestNews" items="${updateNews }">
						<li><a href="news?action=show&newsId=${newestNews.newId }" title="${newestNews.title }">${fn:substring(newestNews.title,0,22) }</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="data_list right_news_list">
			<div class="dataHeader">热门新闻</div>
			<div class="datas">
				<ul>
					<c:forEach var="hotNews" items="${HotNewsList }">
						<li><a href="news?action=show&newsId=${hotNews.newId }" title="${hotNews.title }">${fn:substring(hotNews.title,0,22) }</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/foreground/common/foot.jsp"></jsp:include>
</div>
</body>
</html>
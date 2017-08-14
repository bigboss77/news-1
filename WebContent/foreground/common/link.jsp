<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="row">
		<div class="span12">
			<h4 class="text-left">
				友情链接
			</h4><hr>
			<ul class="inline">
			<c:forEach items="${linkList }" var="linkList">
			<li><a href="${linkList.linkUrl }">${linkList.linkName }</a></li>
			</c:forEach>
			</ul>
		</div>
	</div>
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


<SCRIPT>
var auto;
var index=0;
$('.tuhuo ul li').hover(function(){
	clearTimeout(auto);
	index=$(this).index()
	
	move(index);
	},function(){
		auto=setTimeout('autogo('+index+')',3000)
		});
		
	function autogo(){
		
		
		if(index<5)
		{
				move(index);
				
				index++;
				
			}
			else{
				
				index=0;	
				move(index);
				
				index++;
				
				}
			
		}
		function move(l)
		{
			
		
			var src=$('.tu_img').eq(index).attr('src');
			
			$("#fou_img").css({  "opacity": "0"  });
			
			$('#fou_img').attr('src',src);
			
			$('#fou_img').stop(true).animate({ opacity: '1'},1000);
			$('.tuhuo ul li').removeClass('fouce');
			$('.tuhuo ul li').eq(index).addClass('fouce');
			$('.tuhuo p').hide();
			$('.tuhuo p').eq(index).show();
			var ao=$('.tuhuo p').eq(index).children('a').attr('href');
			$('#fou_img').parent('a').attr("href",ao);
			}
			autogo();
			setInterval('autogo()',3000);
</SCRIPT>
</head>
<body>
<div class="container">
<jsp:include page="/foreground/common/head.jsp"></jsp:include>

<div class="row-fluid">
	<div class="span9">
		<div>
			<DIV style="width: 330px; height: 228px;" class="tuhuo">
				<A href="" target="_blank"><IMG style="width: 330px; height: 208px;" id="fou_img" src=""></A>
					<c:forEach var="imageNews" items="${imageNews }">
					<A href="news?action=show&newsId=${imageNews.newId }"> 
						<IMG style="display: none;" class="tu_img" src="${imageNews.imageName }" width="330" height="208" />
					</A>
					</c:forEach>
					<c:forEach var="imageNews" items="${imageNews }">
			    		<P style="height: 20px;" class="tc"><A href="news?action=show&newsId=${imageNews.newId }" target="_blank" title="${imageNews.title }">${imageNews.title }</A></P>
					</c:forEach>
<UL>
  <LI class="fouce">1</LI>
  <LI>2</LI>
  <LI>3</LI>
  <LI>4</LI>
  <LI>5</LI></UL></DIV>
			
		</div>
		<div class="newsHeader_list">
			<div class="newsHeader">
				<h3><a href="news?action=show&newsId=${aHeadNews.newId }" title="${aHeadNews.title }">${fn:substring(aHeadNews.title,0,10)}</a></h3>
				<p>${fn:substring(aHeadNews.content,0,40) }...<a href="news?action=show&newsId=${aHeadNews.newId }" >[查看全文]</a></p>
			</div>
			<div class="currentUpdate">
				<div class="currentUpdateHeader">最近更新</div>
				<div class="currentUpdateDatas">
				<table width="100%">
					<c:forEach var="updateNews" items="${updateNews }" varStatus="status">
						<c:if test="${status.index%2==0 }">
							<tr>
						</c:if>
							<td width="50%">
								<a href="news?action=show&newsId=${updateNews.newId }" title="${updateNews.title }">${fn:substring(updateNews.title,0,15) }</a>
							</td>
						<c:if test="${status.index%2==1 }">
							</tr>
						</c:if>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
	</div>
	<div class="span3">
		<div class="data_list hotspot_news_list">
			<div class="dataHeader">热点新闻</div>
			<div class="datas">
				<ul>
				<c:forEach items="${HotNews }" var="HotNews">
					<li><a href="news?action=show&newsId=${HotNews.newId }" title="${HotNews.title }">${fn:substring(HotNews.title,0,10) }</a></li>
				</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>


<c:forEach var="allIndexNews" items="${allIndexNewsList }" varStatus="allStatus">
	<c:if test="${allStatus.index%3==0 }">
		<div class="row-fluid">
	</c:if>
	<c:forEach var="indexNews" items="${allIndexNews }" varStatus="oneStatus">
		<c:if test="${oneStatus.first }">
			<div class="span4">
			<div class="data_list news_list" >
				<div class="dataHeader">${newsTypeList.get(allStatus.index).typeName }<span class="more"><a href="news?action=list&typeId=${newsTypeList.get(allStatus.index).newsTypeId }">更多...</a></span></div>
				<div class="datas">
					<ul>
		</c:if>
		<li><fmt:formatDate value="${indexNews.publishDate }" type="date" pattern="MM-dd"/>&nbsp;<a href="news?action=show&newsId=${indexNews.newId }" title="${indexNews.title }">${fn:substring(indexNews.title,0,18) }</a></li>
		<c:if test="${oneStatus.last }">
					</ul>
						</div>
					</div>
				</div>
		</c:if>
	</c:forEach>
	<c:if test="${allStatus.index%3==2 || allStatus.last }">
		</div>
	</c:if>
</c:forEach>
		
	
<jsp:include page="/foreground/common/link.jsp"></jsp:include>
<jsp:include page="/foreground/common/foot.jsp"></jsp:include>
</div>
</body>
</html>
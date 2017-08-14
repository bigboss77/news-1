<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
</head>
<script type="text/javascript">
	/* $(document).ready(function(){
		$(checkedAll).click(function() {
			if ($(this).prop("checked")==true) {
				$("input[name='newsIds']").prop("checked",true);
			}else{
				$("input[name='newsIds']").prop("checked",false);
			}
		});
	}); */

	function newsDelete(newIds){
		if (confirm("确认删除这条新闻？")) {
			$.post("news?action=delete",{newId:newIds},
					function(result){
					var result = eval('('+result+')');
					if (result.success) {
						alert('删除成功');
						window.location.href="news?action=backList";
					}else{
						alert(result.errorMsg);
					}
			});
		}
	}
</script>
<body>
<div class="data_list backMain">
	<div class="dataHeader navi">
		${navCode}
	</div>
	<div class="search_content" style="vertical-align: middle;">
		<form action="${pageContext.request.contextPath}/news?action=backList" method="post">
			新闻标题：<input type="text" id="s_title" name="s_title" style="width:180px" value="${s_title }"/>&nbsp;&nbsp;
			发布日期：<input type="text" id="s_bPublishDate" name="s_bPublishDate" class="Wdate" onclick="WdatePicker()" style="width: 100px;" value="${begainTime }"/>
			&nbsp;到&nbsp;<input type="text" id="s_aPublishDate" name="s_aPublishDate" class="Wdate" onclick="WdatePicker()" style="width: 100px;" value="${endTime }"/>
			&nbsp;&nbsp;<button class="btn btn-mini btn-primary" type="submit" style="margin-top: -8px;">查询</button>
		</form>
	</div>
	<div class="data_content">
		<table class="table table-hover table-bordered">
			<tr>
				<!-- <th><input type="checkbox" id="checkedAll"/></th> -->
				<th>序号</th>
				<th>新闻标题</th>
				<th>新闻类别</th>
				<th>发布时间</th>
				<th>操作</th>
			</tr>
			<c:forEach var="newsBack" items="${newsBackList }" varStatus="status">
				<tr>
					<%-- <td><input type="checkbox" name="newsIds" value="${newsBack.newId}"/></td> --%>
					<td>${status.index+1 }</td>
					<td>${newsBack.title }</td>
					<td>${newsBack.typeName }</td>
					<td><fmt:formatDate value="${newsBack.publishDate }" type="date" pattern="yyyy-MM-dd"/></td>
					<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location.href='news?action=preSave&newId=${newsBack.newId}'">修改</button>&nbsp;<button class="btn btn-mini btn-danger" type="button" onclick="newsDelete(${newsBack.newId})">删除</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div class="pagination pagination-centered">
  		<ul>
  			${pageCode }
  		</ul>
  	</div>
</div>
</body>
</html>
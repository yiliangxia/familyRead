<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

    <title>绘本管理</title>
	<%@ include file="../include/tag.jsp"%>
</head>
<body>
	<input id="ctx" type="hidden" value="${ctx}"/>
    <div id="wrapper">
        <!-- Navigation -->
		<%@ include file="../include/nav.jsp"%>
        <div id="page-wrapper">
        </div>
        
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

</body>
<script type="text/javascript">
//加载用户列表页
function loadBookList(pageNo, pageSize) {
	var timestamp = (new Date()).valueOf();  
	if (pageNo == null || pageNo == "") {
		pageNo = 1;
	}
	var pageSize;
	if(pageSize==undefined){
		pageSize = $("#pageSize").val();
	}
	$.ajax({
		type : "post",
		url : "${ctx}/bookList",
		data : {
			pageNo : pageNo,
			pageSize : pageSize,
			timestamp:timestamp
		},
		dataType : "html",
		success : function(msg) {
			$("#page-wrapper").empty();
			$("#page-wrapper").append(msg);
			support();// 设置翻页按钮底部居中
		}
	});

}
$(function() {
	loadBookList(1, "");
});
</script>
</html>


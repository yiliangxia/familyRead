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
    <meta charset="utf-8">
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

    <title>管理后台首页</title>
	<%@ include file="../include/tag.jsp"%>
	<style type="text/css">
		font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
	    font-size: 14px;
	    line-height: 1.42857143;
	    color: #333;
	</style>
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
function loadImgList(pageNo, pageSize) {
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
		url : "${ctx}/imgManageList",
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

function deleteFile(fileId,fileName,pageNo){
	var fileInfo = new FileInfo();
	fileInfo.id = fileId;
	fileInfo.fileName=fileName;
	$.ajax({
		type : "post",
		url : $("#ctx").val() + "/deleteFile",
		data : fileInfo,
		dataType : "text",
		success : function(text) {
			if (text == "success") {
				alert("删除成功");
				loadImgList(pageNo, "");
			} else {
				alert(text);
			}
		}
	});
}
$(function() {
	loadImgList(1, "");
});

var FileInfo = function() {
}
</script>
</html>


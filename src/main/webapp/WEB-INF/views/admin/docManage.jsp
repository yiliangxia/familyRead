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
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>管理后台首页</title>
	<%@ include file="../include/tag.jsp"%>
</head>
<body>
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
function loadDocList(pageNo, name) {
	if (pageNo == null || pageNo == "") {
		pageNo = 1;
	}
	var pageSize = $("#pageSize").val();
	$.ajax({
		type : "post",
		url : "${ctx}/toDocList",
		data : {
			pageNo : pageNo,
			pageSize : pageSize
		},
		async : false,
		dataType : "html",
		success : function(msg) {
			$("#page-wrapper").empty();
			$("#page-wrapper").append(msg);
			support();// 设置翻页按钮底部居中
		}
	});

}

$(function() {
	loadDocList(1, "");
});


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
				loadDocList(pageNo, "");
			} else {
				alert(text);
			}
		}
	});
}
var FileInfo = function() {
}
</script>
</html>


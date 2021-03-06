<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<input id="ctx" type="hidden" value="${ctx}"/>
    <div id="wrapper">

        <!-- Navigation -->
		<%@ include file="../include/nav.jsp"%>
        <div id="page-wrapper">
        	<div style="width:100%;height:600px;">
				<input id="fileId" name="fileId" type="file" accept="*" multiple>
			</div>
			<script>
				$("#fileId").fileinput({
					uploadUrl : "${ctx}/upload",
					autoReplace : true,
					maxFileCount : 5,
					allowedFileExtensions : [ "jpg", "png", "gif","pdf","mp3","xlsx","rmvb","wmv","avi" ]
				});
				
				$("#fileId").on('filebatchuploadsuccess', function(event, data, previewId, index) {
		           alert(data);
		    });    
			</script>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

</body>
<script type="text/javascript">
</script>
</html>


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
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="${ctx}/assets/js/jquery.min.js"></script>
<script src="${ctx}/assets/js/index.js"></script>
  </head>
  
  <body>
    <div>

        <!-- Navigation -->
		<%@ include file="../include/nav.jsp"%>
        <div id="page-wrapper">
        	<div id="list">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           	图片管理
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>序号</th>
                                            <th>文件名字</th>
                                            <th>创建者</th>
                                            <th>所属组</th>
                                             <th>所属绘本</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                            <c:choose>
								<c:when test="${empty page.result }">
										<tr>
											<td colspan="5">请上传文件进行操作！</td>
										</tr>
								</c:when>
								<c:otherwise>
									<c:forEach var="imgs" items="${page.result }" varStatus="index">
                                        <tr>
                                            <td>${index.index+1}</td>
                                            <td><a href="${ctx}/assets/upload/${imgs.fileName }" target="blank">${imgs.fileName }</a></td>
                                            <td>${imgs.createBy }</td>
                                            <td>${imgs.groupName }</td>
                                            <td>${imgs.bookName }</td>
                                            <td>
                                            	<c:choose>
													<c:when test="${empty user.groupName}">
														<a href="javaScript:void(0)" onclick="manageFile('${imgs.id}')">绑定绘本</a>
													</c:when>
													<c:otherwise>
														已绑绘本
													</c:otherwise>
												</c:choose>
                                            
                                            </td>
                                        </tr>
                                    </c:forEach>
								</c:otherwise>
							</c:choose>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        
                        <!-- /.panel-body -->
                    </div>
                      <div class="supp"></div>
		                    <div class="Paging" >
								<div class="pag">
									<tags:imgManage page="${page}" paginationSize="${page.pageSize}" />
								</div>
							</div>
                    <!-- /.panel -->
                </div>
                
        </div>
        
        <!-- /#page-wrapper -->

    </div>
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
		url : "${ctx}/toImgManage",
		data : {
			pageNo : pageNo,
			pageSize : pageSize,
			timestamp:timestamp
		},
		dataType : "html",
		success : function(msg) {
			$("#right").empty();
			$("#right").append(msg);
			support();// 设置翻页按钮底部居中
		}
	});

}
$(function() {
	loadImgList(1, "");
});
</script>
</html>

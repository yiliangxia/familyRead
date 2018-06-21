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

    <title>绘本管理</title>
	<%@ include file="../include/tag.jsp"%>
</head>
<body>
<input id="ctx" type="hidden" value="${ctx}"/>
    <div id="wrapper">

        <!-- Navigation -->
		<%@ include file="../include/nav.jsp"%>
        <div id="page-wrapper">
        	<div class="ibox-content">
               <div class="row">
               		<span>${status}</span>
                  <form role="form" action="${ctx}/saveBook" method="post">
                   <div class="col-sm-5"><h3 class="m-t-none m-b">创建绘本</h3>
                           <div class="form-group"><label class="col-sm-4 control-label">绘本名称</label> <input type="text" name="bookName" placeholder="绘本名称" class="form-control"></div>
                           <div class="form-group" style="height:30px;">
                           <div class="col-sm-4 control-label">
	                           <label>选择所属组</label>
                           </div>
                                    <div class="col-sm-8">
                                    	 <c:forEach  var="group" items="${groupList}" varStatus="index">
                                       		 <label class="checkbox-inline i-checks"> <input type="checkbox" name="group${index.index}" value="${group.id}">${group.groupName }</label>
                                        </c:forEach>
                                     </div>
                                </div>
                                <br/>
                           <div class="form-group">
	                           <label class="col-sm-4 control-label">绘本描述</label> 
	                           <input type="text" name="remark" placeholder="绘本描述" class="form-control">
                           </div>
                           <c:if test="${fileInfo.fileType==1}">
                           		<div class="form-group"><label>图片</label> 
                           			<input type="text" class="form-control" value="${fileInfo.fileName}" readonly="readonly">
                           		<input type="hidden" name="fileImgId" class="form-control" value="${fileInfo.id}"></div>
                           </c:if>
                           <c:if test="${fileInfo.fileType!=1}">
                           <div class="form-group">
                           	<label class="col-sm-4 control-label" style="margin-top:10px;">图片</label>
                                    <div style="margin-top:10px;">
                                    	<select class="form-control m-b" name="fileImgId">
                                        <c:forEach  var="img" items="${imgList}">
                                       		 <option value="${img.id}">${img.fileName }</option>
                                        </c:forEach>
                                    </select>
                                    </div>
                                </div>
                             </c:if>
                              <c:if test="${fileInfo.fileType==2}">
                           		<div class="form-group"><label>文档</label>
									<input type="text" class="form-control" value="${fileInfo.fileName}" readonly="readonly">
                           			<input type="hidden" name="filePdfId" class="form-control" value="${fileInfo.id}"></div>
                           </c:if>
                           <c:if test="${fileInfo.fileType!=2}">
                           <div class="form-group">
                           		<label class="col-sm-2 control-label" style="margin-top:10px;">文档</label>
                                    <div style="margin-top:10px;">
                                    <select class="form-control m-b" name="filePdfId">
                                        <c:forEach  var="doc" items="${docList}">
                                       		 <option value="${doc.id}">${doc.fileName }</option>
                                        </c:forEach>
                                    </select>
                                    </div>
                                </div>
                             </c:if>
                            <c:if test="${fileInfo.fileType==3}">
                           		<div class="form-group"><label>音频</label> <input type="text" class="form-control" value="${fileInfo.fileName}" readonly="readonly">
                           		<input type="hidden" name="fileMp3Id" class="form-control" value="${fileInfo.id}"></div>
                           </c:if>
                           <c:if test="${fileInfo.fileType!=3}">
                           <label class="col-sm-2 control-label" style="margin-top:10px;">音频</label>
                           <div class="form-group">
                                    <div style="margin-top:10px;"><select class="form-control m-b" name="fileMp3Id">
                                        <c:forEach  var="vedio" items="${vedioList}">
                                       		 <option value="${vedio.id}">${vedio.fileName }</option>
                                        </c:forEach>
                                    </select>
                                    </div>
                                </div>
                             </c:if>
                           <div  class="form-group">
                               <button class="btn btn-block btn-primary" type="submit"><strong>提交</strong></button>
                           </div>
                   </div>
              </form>
               </div>
           </div>
        
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

</body>
<script type="text/javascript">
	
</script>
</html>


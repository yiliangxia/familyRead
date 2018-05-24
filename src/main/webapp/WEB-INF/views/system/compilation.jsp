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
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>组管合集</title>
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
	function loadGroupsList(pageNo, name) {
		if (pageNo == null || pageNo == "") {
			pageNo = 1;
		}
		var pageSize = $("#pageSize").val();
		$.ajax({
			type : "post",
			url : "${ctx}/compilationList",
			data : {
				pageNo : pageNo,
				pageSize : pageSize
			},
			dataType : "html",
			success : function(msg) {
				$("#page-wrapper").empty();
				$("#page-wrapper").append(msg);
				support();// 设置翻页按钮底部居中
			}
		});
	
	}
	
	function deleteGroup(id,dat){
		var group = new Groups();
		group.id=id;
		$.ajax({
			type : "post",
			url : $("#ctx").val() + "/deleteGroup",
			data : group,
			dataType : "text",
			success : function(text) {
				if (text == "success") {
					alert("删除成功");
					$(dat).parent().parent().remove();
				} else {
					alert(text);
				}
			}
		});
	}
	
	function clearModel(){
		$("#id").val("");
		$("#groupName").val("");
		$("#remark").val("");
	}
	
	function updateGroup(id,parentId,dat){
		$("#addModel").modal('show');
		$("#groupName").val($(dat).parent().siblings()[1].innerText);
		$("#remark").val($(dat).parent().siblings()[2].innerText);
		$("#parentId option").each(function(){ //遍历全部option  
			  var val = $(this).val(); //获取option的text  
			  if(val===parentId){
				  $(this).attr("selected","selected");
			  }
		});
		$("#id").val(id);
		$("#title").text("修改合集");
	}
	
	function shutDownModal(){
		$("#addModel").modal('hide');
	}
	function saveGroup(formId,pageNo){
		var group = new Groups();
		group.groupName=$("#groupName").val();
		group.parentId=$("#parentId").val();
		group.remark=$("#remark").val();
		group.id=$("#id").val();
		$.ajax({
			type : "post",
			url : $("#ctx").val() + "/saveGroup",
			data : group,
			dataType : "text",
			success : function(text) {
				if (text == "success") {
					alert("保存成功");
					loadGroupsList(pageNo, "");
					shutDownModal();
					$(".modal-backdrop").remove();
				} else {
					alert(text);
				}
			}
		});
	}
	
	$(function() {
		loadGroupsList(1, "");
	});
	var Groups = function() {
	}
</script>
</html>


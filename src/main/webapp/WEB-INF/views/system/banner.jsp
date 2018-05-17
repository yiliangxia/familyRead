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

    <title>banner管理</title>
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
	function loadBannerList(pageNo, name) {
		if (pageNo == null || pageNo == "") {
			pageNo = 1;
		}
		var pageSize = $("#pageSize").val();
		$.ajax({
			type : "post",
			url : "${ctx}/bannerList",
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
	
	function deleteBanner(id,dat){
		var banner = new Banner();
		banner.id=id;
		$.ajax({
			type : "post",
			url : $("#ctx").val() + "/deleteBanner",
			data : banner,
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
		$("#imgName").val("");
		$("#remark").val("");
	}
	
	function updateBanner(id,dat){
		$("#addModel").modal('show');
		$("#bannerType option").each(function(){ //遍历全部option  
			  var text = $(this).text(); //获取option的text  
			  if(text===$(dat).parent().siblings()[1].innerText){
				  $(this).attr("selected","selected");
			  }
		});
		$("#imgName").val($(dat).parent().siblings()[2].innerText);
		$("#remark").val($(dat).parent().siblings()[3].innerText);
		$("#imgUrl").val($(dat).parent().siblings()[4].innerText);
		$("#fileId option").each(function(){ //遍历全部option  
			  var text = $(this).text(); //获取option的text  
			  if(text===$(dat).parent().siblings()[2].innerText){
				  $(this).attr("selected","selected");
			  }
		});
		$("#id").val(id);
		$("#title").text("修改banner");
	}
	
	function updateImgInfo(dat){
		var imgName = $(dat).find("option:selected").text();
		$("#imgName").val(imgName);
		$("#imgUrl").val("/assets/upload/"+imgName);
	}
	
	function shutDownModal(){
		$("#addModel").modal('hide');
	}
	function savebanner(formId,pageNo){
		var banner = new Banner();
		banner.imgName=$("#imgName").val();
		banner.bannerType=$("#bannerType").val();
		banner.fileId=$("#fileId").val();
		banner.imgUrl=$("#imgUrl").val();
		banner.remark=$("#remark").val();
		banner.id=$("#id").val();
		$.ajax({
			type : "post",
			url : $("#ctx").val() + "/saveOrUpdateBanner",
			data : banner,
			dataType : "text",
			success : function(text) {
				if (text == "success") {
					alert("保存成功");
					loadBannerList(pageNo, "");
					shutDownModal();
					$(".modal-backdrop").remove();
				} else {
					alert(text);
				}
			}
		});
	}
	
	$(function() {
		loadBannerList(1, "");
	});
	var Banner = function() {
	}
</script>
</html>


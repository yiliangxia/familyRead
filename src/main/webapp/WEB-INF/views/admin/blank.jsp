<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<script src="${ctx}/assets/js/morris.min.js"></script>
	<script src="${ctx}/assets/js/morris-data.js"></script>
</head>
<body>
    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">妈宝管理后台</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i>个人信息</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> 个人设置</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> 注销</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 文件管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="${ctx}/toUpload" onclick="showContent('toUpload')">上传文件</a>
                                </li>
                                 <li>
                                    <a href="javaScript:void(0)" onclick="showContent('img')">图片管理</a>
                                </li>
                                <li>
                                    <a href="javaScript:void(0)" onclick="showContent('vedio')">音频管理</a>
                                </li>
                                <li>
                                    <a href="javaScript:void(0)" onclick="showContent('doc')">文档管理</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="javaScript:void(0)" onclick="showContent('customer')"><i class="fa fa-table fa-fw"></i>用户管理</a>
                        </li>
                        <li>
                            <a href="javaScript:void(0)" onclick="showContent('create')"><i class="fa fa-edit fa-fw"></i> 创建绘本</a>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
        	<div style="width:100%;height:600px;">
				<input id="fileId" name="fileId" type="file" accept="*" multiple>
			</div>
			<script>
				$("#fileId").fileinput({
					uploadUrl : "${ctx}/upload",
					autoReplace : true,
					maxFileCount : 5,
					allowedFileExtensions : [ "jpg", "png", "gif","pdf","mp3","xlsx" ]
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


<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="javaScript:void(0)"><b>妈宝管理后台</b></a>
                <span>你好：${sessionScope.customer.userName}</span>
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
                            <a href="javaScript:void(0)"><i class="fa fa-bar-chart-o fa-fw"></i> 文件管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="${ctx}/toUpload">上传文件</a>
                                </li>
                                 <li>
                                    <a href="${ctx}/toImgManage" >图片管理</a>
                                </li>
                                <li>
                                    <a href="${ctx}/toVedioManage">音频管理</a>
                                </li>
                                <li>
                                    <a href="${ctx}/toDocManage">文档管理</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="javaScript:void(0)"><i class="fa fa-table fa-fw"></i>系统管理<span class="fa arrow"></span></a>
	                            <ul class="nav nav-second-level">
	                                <li>
	                                    <a href="${ctx}/bannerPage">banner管理</a>
	                                </li>
	                                <li>
	                                    <a href="${ctx}/groupPage">组管理</a>
	                                </li>
	                                 <li>
	                                    <a href="###" >用户管理</a>
	                                </li>
	                                <li>
	                                    <a href="###">权限管理</a>
	                                </li>
	                            </ul>
                        </li>
                        <li>
                            <a href="javaScript:void(0)"><i class="fa fa-edit fa-fw"></i> 绘本管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
	                                <li>
	                                    <a href="${ctx}/toBookManage">创建绘本</a>
	                                </li>
	                                <li>
	                                    <a href="${ctx}/bookPage">绘本列表</a>
	                                </li>
	                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
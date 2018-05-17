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
<div id="list">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           	banner列表  &nbsp;&nbsp;&nbsp;&nbsp;<a data-toggle="modal" class="btn btn-primary" href="javaScript:void()" data-target="#addModel" onclick="clearModel()">新增</a>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                            	<!-- add modal-form -->
	                            	<div id="addModel" class="modal fade" aria-hidden="true">
	                                <div class="modal-dialog">
	                                    <div class="modal-content">
	                                        <div class="modal-body">
	                                            <div class="row">
	                                                <div class="col-sm-10 b-r"><h3 class="m-t-none m-b" id="title">新增组</h3>
	                                                    <form role="form" id="addBannerForm" action="">
	                                                    	<input type="hidden" value="" name="id" id="id"/>
	                                                        <div class="form-group"><label>banner图名称</label> <input type="text" name="imgName" id="imgName" placeholder="banner图名称..." class="form-control" readonly="readonly"></div>
	                                                        <div class="form-group"><label>banner类型</label> 
	                                                        	<select name="bannerType" id="bannerType" placeholder="类型..." class="form-control">
	                                                        		<option value="0">首页banner</option>
	                                                        		<option value="1">菜单banner</option>
	                                                        		<option value="2">子菜单banner</option>
	                                                        		<option value="3">脚banner</option>
	                                                        		<option value="4">其他banner</option>
	                                                        	</select></div>
	                                                        	<select name="fileId" id="fileId" placeholder="类型..." class="form-control" onchange="updateImgInfo(this)">
	                                                        		<c:forEach var="img" items="${imgList}">
	                                                        			<option value="${img.id }">${img.fileName }</option>
	                                                        		</c:forEach>
	                                                        	</select>
	                                                        <div class="form-group"><label>路径</label> <input type="text" name="imgUrl" id="imgUrl" placeholder="路径..." class="form-control" readonly="readonly"></div>
	                                                        <div class="form-group"><label>banner描述</label> <textarea name="remark" id="remark" placeholder="组描述..." class="form-control"></textarea></div>
	                                                        <div>
	                                                        <button class="btn btn-sm btn-primary pull-right m-t-n-xs" type="button" onclick="shutDownModal();"><strong>关闭</strong></button>
	                                                            <button class="btn btn-sm btn-primary pull-right m-t-n-xs" type="button" onclick="savebanner('addBannerForm',${page.pageNo})"><strong>保存</strong></button>
	                                                        </div>
	                                                    </form>
	                                                </div>
	                                        </div>
	                                    </div>
	                                    </div>
	                                </div>
	                        </div>
	                        <!-- add modal-form end-->
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>序号</th>
                                            <th>banner类型</th>
                                            <th>名称</th>
                                            <th>描述</th>
                                            <th>路径</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                            <c:choose>
								<c:when test="${empty page.result }">
										<tr>
											<td colspan="6">请配置banner进行操作！</td>
										</tr>
								</c:when>
								<c:otherwise>
									<c:forEach var="banner" items="${page.result }" varStatus="index">
                                        <tr>
                                            <td>${index.index+1}</td>
                                            <td>
                                            	<c:if test="${banner.bannerType eq 0}">首页banner</c:if>
                                            	<c:if test="${banner.bannerType eq 1}">菜单banner</c:if>
                                            	<c:if test="${banner.bannerType eq 2}">子菜单banner</c:if>
                                            	<c:if test="${banner.bannerType eq 3}">脚banner</c:if>
                                            	<c:if test="${banner.bannerType eq 4}">其他banner</c:if>
                                           	</td>
                                            <td>${banner.imgName }</td>
                                            <td>${banner.remark }</td>
                                            <td><a href="${ctx}/assets/upload/${banner.imgName }" target="blank">${ctx}/assets/upload/${banner.imgName }</a></td>
                                            <td>
                                            	<a data-toggle="modal" class="btn btn-primary" href="javaScript:void()" onclick="deleteBanner(${banner.id},this)">删除</a>
                                            	<a data-toggle="modal" class="btn btn-primary" href="javaScript:void()" onclick="updateBanner(${banner.id},this)">修改</a>
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
									<tags:bannerList page="${page}" paginationSize="${page.pageSize}" />
								</div>
							</div>
                    <!-- /.panel -->
                </div>
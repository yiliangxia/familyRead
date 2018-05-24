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
                           	合集列表 &nbsp;&nbsp;&nbsp;&nbsp;<a data-toggle="modal" class="btn btn-outline btn-success btn-xs" href="javaScript:void(0)" data-target="#addModel" onclick="clearModel()">新增</a>
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
	                                                <div class="col-sm-10 b-r"><h3 class="m-t-none m-b" id="title">新增合集</h3>
	                                                    <form role="form" id="addGroupForm" action="">
	                                                    	<input type="hidden" value="" name="id" id="id"/>
	                                                        <div class="form-group"><label>合集名称</label> <input type="text" name="groupName" id="groupName" placeholder="合集名称..." class="form-control"></div>
	                                                        <div class="form-group"><label>合集名称</label> 
	                                                        	<select class="form-control m-b" id="parentId">
							                                        <c:forEach  var="compilation" items="${groups}">
							                                        	<c:if test="${empty compilation.parentId}">
							                                       		 	<option value="${compilation.id}">${compilation.groupName }</option>
							                                        	</c:if>
							                                        </c:forEach>
							                                    </select>
	                                                        </div>
	                                                        <div class="form-group"><label>合集描述</label> <textarea name="remark" id="remark" placeholder="合集描述..." class="form-control"></textarea></div>
	                                                        <div>
	                                                            <button class="btn btn-primary btn-rounded btn-xs" type="button" onclick="saveGroup('addGroupForm',${page.pageNo})"><strong>保存</strong></button>
	                                                        <button class="btn btn-danger btn-rounded btn-xs" type="button" onclick="shutDownModal();"><strong>关闭</strong></button>
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
                                            <th>合集名称</th>
                                            <th>描述</th>
                                            <th>所属组</th>
                                            <th>创建时间</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                            <c:choose>
								<c:when test="${empty page.result }">
										<tr>
											<td colspan="5">请创建合集进行操作！</td>
										</tr>
								</c:when>
								<c:otherwise>
									<c:forEach var="group" items="${page.result }" varStatus="index">
                                        <tr>
                                            <td>${index.index+1}</td>
                                            <td>${group.groupName }</td>
                                            <td>${group.remark }</td>
                                            <td>${group.parentName }</td>
                                            <td>${group.createTime }</td>
                                            <td>
                                            	<a data-toggle="modal" class="btn btn-outline btn-primary btn-xs" href="javaScript:void(0)" onclick="updateGroup(${group.id},${group.parentId},this)">修改</a>
                                            	<a data-toggle="modal" class="btn btn-outline btn-danger btn-xs" href="javaScript:void(0)" onclick="deleteGroup(${group.id},this)">删除</a>
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
									<tags:groupsList page="${page}" paginationSize="${page.pageSize}" />
								</div>
							</div>
                    <!-- /.panel -->
                </div>
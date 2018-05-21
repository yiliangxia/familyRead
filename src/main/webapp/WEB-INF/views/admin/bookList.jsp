<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<input id="ctx" type="hidden" value="${ctx}"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div id="list">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           	绘本列表
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>序号</th>
                                            <th>绘本名称</th>
                                            <th>绘本描述</th>
                                            <th>图片</th>
                                             <th>文档</th>
                                             <th>音频</th>
                                             <th>所属组</th>
                                             <th>创建时间</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                            <c:choose>
								<c:when test="${empty page.result }">
										<tr>
											<td colspan="9">请上传文件进行操作！</td>
										</tr>
								</c:when>
								<c:otherwise>
									<c:forEach var="book" items="${page.result }" varStatus="index">
                                        <tr>
                                            <td>${index.index+1}</td>
                                            <td>${book.bookName }</td>
                                            <td>${book.remark }</td>
                                           	<td>${book.imgName }</td>
                                           	<td>${book.docName }</td>
                                           	<td>${book.vedioName }</td>
                                           	<td>${book.groupName }</td>
                                            <td>${book.createTime }</td>
                                            <td>
                                            	<button data-toggle="modal" class="btn btn-outline btn-primary btn-xs" onclick="updateBook(${book.id},this)">修改</button>                                            
												<button data-toggle="modal" class="btn btn-outline btn-danger btn-xs" onclick="deleteBook(${book.id},this)">删除</button>
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
							<tags:bookList page="${page}" paginationSize="${page.pageSize}" />
						</div>
					</div>
                    <!-- /.panel -->
                </div>
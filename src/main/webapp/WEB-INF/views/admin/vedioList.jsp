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
                           	音频管理
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
											<td colspan="6">请上传文件进行操作！</td>
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
													<c:when test="${empty imgs.groupName}">
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
                    <div class="Paging">
						<div class="pag">
							<tags:vedioManage page="${page}" paginationSize="${page.pageSize}" />
						</div>
					</div>
                    <!-- /.panel -->
                </div>
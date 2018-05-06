<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="com.familyRead.util.Page"
	required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer"
	required="true"%>
<%@ attribute name="parameter" type="java.lang.String"
	required="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	int current = page.getPageNo();
	long begin = Math.max(1, current - paginationSize / 2);
	long end = Math.min(begin + (paginationSize - 1),
			page.getTotalPages());
	if(end<1){
		end = 1;
	}
	request.setAttribute("current", current);
	request.setAttribute("begin", begin);
	request.setAttribute("end", end);
	String path = request.getContextPath();
%>
<div style="text-align: center;">
	<ul class="fancy pagination">
		<c:choose>
			<c:when test="${page.pageNo > 1}">
				<li><a
					href="javascript:loadImgList(${page.prePage})"><<</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="javascript:void(0)"><<</a></li>
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${begin}" end="${end}">
			<c:choose>
				<c:when test="${i == current}">
					<li><a class="active"
						href="javascript:loadImgList(${i})">${i}</a></li>
				</c:when>
				<c:otherwise>
					<li><a
						href="javascript:loadImgList(${i})">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${page.hasNext}">
				<li><a
					href="javascript:loadImgList(${page.nextPage})">>></a></li>
			</c:when>
			<c:otherwise>
				<li><a href="javascript:void(0)">>></a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>


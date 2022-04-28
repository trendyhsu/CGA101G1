<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forum.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>

<%
ForumService forumSvc = new ForumService();
List<ForumVO> list = forumSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>討論區列表</title>

<style type="text/css">
th {
	border-bottom-style: solid;
	background-color: #b2cdcc;
}

td {
	border-bottom-style: solid;
}

h3 {
	font-weight: bold;
	color: #547492;
}
</style>

</head>
<body>

	<div id="forum-content"
		style="position: absolute; left: 230px; top: 80px; width: 80%">

		<table id="table-1">
			<tr>

				<h3>討論區列表</h3>

			</tr>
		</table>
		<div style="display: inline-block;">
			<a href="addForum.jsp">
				<button>新增討論區</button>
			</a>
		</div>
		<div style="height: 10px"></div>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message.key}:${message.value}</li>
				</c:forEach>
			</ul>
		</c:if>

		<table class="showPanel" style="table-layout: fixed; color: black;">
			<tr>
				<th>討論區編號&emsp;</th>
				<th>討論區名稱&emsp;</th>
				<th>討論區狀態&emsp;</th>
				<th>版主名稱&emsp;</th>
				<th></th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="forumVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					<td>${forumVO.forumNo}</td>
					<td><div
							style="width: 500px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							${forumVO.forumName}</div></td>

					<td><c:if test="${forumVO.forumType == 0}" var="condition">
							<c:out value="0不顯示" escapeXml="false"></c:out>
						</c:if> <c:if test="${forumVO.forumType == 1}" var="condition">
							<c:out value="1顯示" escapeXml="false"></c:out>
						</c:if></td>

					<td>${forumVO.memVO.memName}&emsp;</td>

					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/forum/forumOneEditServlet"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改"> <input type="hidden"
								name="forumNo" value="${forumVO.forumNo}">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>
</body>
</html>
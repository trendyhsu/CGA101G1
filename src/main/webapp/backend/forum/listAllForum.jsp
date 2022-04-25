<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forum.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>
<%-- <%@include file="/backend/bid/assets/headerCDN.txt" %> --%>

<%
ForumService forumSvc = new ForumService();
List<ForumVO> list = forumSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有討論區</title>

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

				<h3>所有討論區資料</h3>

			</tr>
		</table>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<table class="showPanel" style="table-layout: fixed; color: black;">
			<tr>
				<th>討論區編號&emsp;</th>
				<th>討論區名稱&emsp;</th>
				<th>討論區狀態&emsp;</th>
				<th>版主會員編號&emsp;</th>
				<th>討論區圖片&emsp;</th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="forumVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					<td>${forumVO.forumNo}</td>
					<td><div
							style="width: 200px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							${forumVO.forumName}&emsp;</div></td>

					<td><c:if test="${forumVO.forumType == 0}" var="condition">
							<c:out value="0不顯示" escapeXml="false"></c:out>
						</c:if> <c:if test="${forumVO.forumType == 1}" var="condition">
							<c:out value="1顯示" escapeXml="false"></c:out>
						</c:if></td>
					<td>${forumVO.memNo}</td>

					<td>${forumVO.forumImg}&emsp;</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>


</body>
</html>
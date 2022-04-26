<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forumpost.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>

<%
ForumPostService forumPostSvc = new ForumPostService();
List<ForumPostVO> list = forumPostSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有討論區文章</title>

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

				<h3>所有討論區文章資料</h3>

			</tr>
		</table>
		<div>
			<a href="selectReportHome.jsp">
				<button>返回檢舉首頁</button>
			</a>
		</div>
		<div style="height: 10px"></div>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message.key} : ${message.value}</li>
				</c:forEach>
			</ul>
		</c:if>

		<table class="showPanel" style="table-layout: fixed; color: black;">
			<tr>
				<th>文章編號&emsp;</th>
				<th>討論區編號&emsp;</th>
				<th>討論區名稱&emsp;</th>
				<th>文章分類&emsp;</th>
				<th>會員編號&emsp;</th>
				<th>文章狀態&emsp;</th>
				<th>文章標題&emsp;</th>
				<!--<th>文章內容&emsp;</th> -->
				<th>發表時間&emsp;</th>
				<th>文章精選&emsp;</th>
				<th></th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="forumPostVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					<td>${forumPostVO.forumPostNo}</td>
					<td>${forumPostVO.forumNo}</td>
					<td>
						<div
							style="width: 125px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							${forumPostVO.forumVO.forumName}&emsp;</div>
					</td>
					<td><c:if test="${forumPostVO.forumPostType == 0}"
							var="condition">
							<c:out value="0管理員文章" escapeXml="false"></c:out>
						</c:if> <c:if test="${forumPostVO.forumPostType == 1}" var="condition">
							<c:out value="1版主文章" escapeXml="false"></c:out>
						</c:if> <c:if test="${forumPostVO.forumPostType == 2}" var="condition">
							<c:out value="2攻略文章" escapeXml="false"></c:out>
						</c:if> <c:if test="${forumPostVO.forumPostType == 3}" var="condition">
							<c:out value="3情報" escapeXml="false"></c:out>
						</c:if> <c:if test="${forumPostVO.forumPostType == 4}" var="condition">
							<c:out value="4閒聊" escapeXml="false"></c:out>
						</c:if> <c:if test="${forumPostVO.forumPostType == 5}" var="condition">
							<c:out value="5其他" escapeXml="false"></c:out>
						</c:if></td>
					<td>${forumPostVO.memNo}</td>
					<td><c:if test="${forumPostVO.forumPostState == 0}"
							var="condition">
							<c:out value="0不顯示" escapeXml="false"></c:out>
						</c:if> <c:if test="${forumPostVO.forumPostState == 1}" var="condition">
							<c:out value="1顯示" escapeXml="false"></c:out>
						</c:if></td>
					<td>
						<div
							style="width: 275px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							${forumPostVO.forumPostTitle}&emsp;</div>
					</td>
					<td><fmt:formatDate value="${forumPostVO.forumPostTime}"
							pattern="yyyy-MM-dd HH:mm:ss" />&emsp;</td>
					<td><c:if test="${forumPostVO.forumPostFeatured == 0}"
							var="condition">
							<c:out value="0一般" escapeXml="false"></c:out>
						</c:if> <c:if test="${forumPostVO.forumPostFeatured == 1}"
							var="condition">
							<c:out value="1精選" escapeXml="false"></c:out>
						</c:if></td>

					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/forum/forumPostOneEditServlet"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改狀態"> <input type="hidden"
								name="forumPostNo" value="${forumPostVO.forumPostNo}">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>
</body>
</html>
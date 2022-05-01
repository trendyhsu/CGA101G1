<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forummsgreport.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>

<%
ForumMsgReportService forumMsgReportSvc = new ForumMsgReportService();
List<ForumMsgReportVO> list = forumMsgReportSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有討論區留言檢舉</title>

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

				<h3>討論區留言檢舉列表</h3>

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
				<th>留言檢舉編號&emsp;</th>
				<th>留言編號&emsp;</th>
				<th>留言內容&emsp;</th>
				<th>檢舉會員編號&emsp;</th>
				<th>留言檢舉狀態&emsp;</th>
				<th>檢舉原因&emsp;</th>
				<th>檢舉時間&emsp;</th>
				<th></th>
			</tr>
			<%@ include file="page3.file"%>
			<c:forEach var="forumMsgReportVO" items="${list}"
				begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

				<tr>
					<td>${forumMsgReportVO.forumMsgReportNo}</td>
					<td>${forumMsgReportVO.forumMsgNo}</td>
					<td><div
							style="width: 125px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							${forumMsgReportVO.forumMsgVO.forumMsg}&emsp;</div></td>
					<td>${forumMsgReportVO.memNo}</td>
					<td><c:if test="${forumMsgReportVO.forumMsgReportType == 0}"
							var="condition">
							<c:out value="0未處理" escapeXml="false"></c:out>
						</c:if> <c:if test="${forumMsgReportVO.forumMsgReportType == 1}"
							var="condition">
							<c:out value="1檢舉成功" escapeXml="false"></c:out>
						</c:if> <c:if test="${forumMsgReportVO.forumMsgReportType == 2}"
							var="condition">
							<c:out value="2檢舉失敗" escapeXml="false"></c:out>
						</c:if></td>
					<td>
						<div
							style="width: 200px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							${forumMsgReportVO.forumMsgReportWhy}&emsp;</div>

					</td>
					<td><fmt:formatDate
							value="${forumMsgReportVO.forumMsgReportTime}"
							pattern="yyyy-MM-dd HH:mm:ss" />&emsp;</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/forum/forumMsgReportOneEdit"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改狀態"> <input type="hidden"
								name="forumMsgReportNo"
								value="${forumMsgReportVO.forumMsgReportNo}">
						</FORM>
					</td>

				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>
</body>
</html>
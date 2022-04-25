<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.forummsgreport.model.ForumMsgReportVO"%>
<%@page import="com.forummsgreport.model.ForumMsgReportService"%>
<%@ page import="com.forummsgreport.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>

<%
// 取得來自 ForumMsgReportEditUpdateServlet 的 ForumMsgReportVO
ForumMsgReportVO forumMsgReportVO = (ForumMsgReportVO) request.getAttribute("forumMsgReportVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改留言檢舉</title>

<style>
table {
	/* 	background-color: white; */
	margin-bottom: 5px;
	font-size: 13px;
	color: black;
}

table, th, td {
	border-bottom: 1px solid #CCC;
	font-weight: bold;
}

th, td {
	padding: 5px;
	text-align: left;
}

.uploadedImg {
	padding: 10px
}

h3 {
	font-weight: bold;
	color: #547492;
}
</style>

</head>
<body>
	<section id="main-content">
		<section class="wrapper">


			<div id="forum-content">

				<table id="table-1">
					<tr>
						<td>
							<h3>修改留言檢舉資料</h3>
						</td>
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

				<!-- 		主要修改資訊區 -->
				<table>
					<tr>
						<td>留言檢舉編號</td>
						<td>${forumMsgReportVO.forumMsgReportNo}</td>
					</tr>
					<tr>
						<td>留言編號</td>
						<td>${forumMsgReportVO.forumMsgNo}</td>
					</tr>
					<tr>
						<td>留言內容</td>
						<td>${forumMsgReportVO.forumMsgVO.forumMsg}</td>
					</tr>
					<tr>
						<td>檢舉會員編號</td>
						<td>${forumMsgReportVO.memNo}</td>
					</tr>

					<tr>
						<td>留言檢舉狀態</td>
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
					</tr>
					<tr>
						<td>檢舉原因</td>
						<td>${forumMsgReportVO.forumMsgReportWhy}</td>
					</tr>
					<tr>
						<td>檢舉時間</td>
						<td>${forumMsgReportVO.forumMsgReportTime}</td>
					</tr>
					<tr>
					</tr>

				</table>

				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/forum/forumMsgReportOneEditServlet"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改狀態"> <input type="hidden"
						name="forumMsgReportNo"
						value="${forumMsgReportVO.forumMsgReportNo}">
				</FORM>
			</div>
		</section>

		<!--main content end-->
		<div style="display: inline-block; padding-left: 15px">
			<a
				href="<%=request.getContextPath()%>/backend/forum/listAllForumMsgReport.jsp">
				<button>返回留言檢舉列表</button>
			</a>
		</div>
		<div style="display: inline-block;">
			<a
				href="<%=request.getContextPath()%>/backend/forum/selectReportHome.jsp">
				<button>返回檢舉首頁</button>
			</a>
		</div>


	</section>
</body>
</html>

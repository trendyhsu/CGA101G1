<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forummsgreport.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>

<%
// 取得來自 ForumMsgReportOneEditServlet 的 ForumMsgReportVO
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
				<!-- main content -->

				<form method="post"
					action="<%=request.getContextPath()%>/forum/forumMsgReportEditUpdate"
					name="form1">
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
							<td><select size="1" name="forumMsgReportType">
									<option value="0"
										<c:if test="${forumMsgReportVO.forumMsgReportType == 0}"><c:out value="selected"></c:out></c:if>>0未處理</option>
									<option value="1"
										<c:if test="${forumMsgReportVO.forumMsgReportType == 1}"><c:out value="selected"></c:out></c:if>>1檢舉成功</option>
									<option value="2"
										<c:if test="${forumMsgReportVO.forumMsgReportType == 2}"><c:out value="selected"></c:out></c:if>>2檢舉失敗</option>
							</select></td>
						</tr>
						<tr>
							<td>檢舉原因</td>
							<td>${forumMsgReportVO.forumMsgReportWhy}</td>
						</tr>
						<tr>
							<td>檢舉時間</td>
							<td><fmt:formatDate
									value="${forumMsgReportVO.forumMsgReportTime}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>

					</table>

					<input type="hidden" name="forumMsgReportNo"
						value="${forumMsgReportVO.forumMsgReportNo}"> <input
						type="submit" value="確認修改">
				</form>

				<div style="height: 15px"></div>

				<div style="display: inline-block;">
					<a
						href="<%=request.getContextPath()%>/backend/forum/listAllForumMsgReport.jsp">
						<button>返回留言檢舉列表</button>
					</a>
				</div>
			</div>
		</section>

		<!--main content end-->

	</section>

</body>
</html>

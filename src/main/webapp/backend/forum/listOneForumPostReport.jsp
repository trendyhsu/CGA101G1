<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forumpostreport.model.*"%>
<%@ page import="com.forumpost.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>

<%
// 取得來自 ForumPostReportEditUpdateServlet 的 ForumPostReportVO
ForumPostReportVO forumPostReportVO = (ForumPostReportVO) request.getAttribute("forumPostReportVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改文章檢舉</title>

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
							<h3>修改文章檢舉資料</h3>
						</td>
					</tr>
				</table>
				<!-- main content -->
				<table>
					<tr>
						<td>文章檢舉編號</td>
						<td>${forumPostReportVO.forumPostReportNo}</td>
					</tr>
					<tr>
						<td>文章編號</td>
						<td>${forumPostReportVO.forumPostNo}</td>
					</tr>
					<tr>
						<td>文章標題</td>
						<td><a
							href="<%= request.getContextPath()%>/forum/forumPostOneEditServlet?forumPostNo=${forumPostReportVO.forumPostNo}">${forumPostReportVO.forumPostVO.forumPostTitle}</a></td>

					</tr>

					<tr>
						<td>文章內容</td>
						<td>${forumPostReportVO.forumPostVO.forumPostContent}</td>
					</tr>
					<tr>
						<td>檢舉會員編號</td>
						<td>${forumPostReportVO.memNo}</td>
					</tr>
					<tr>
						<td>文章檢舉狀態</td>
						<td><c:if
								test="${forumPostReportVO.forumPostReportType == 0}"
								var="condition">
								<c:out value="0未處理" escapeXml="false"></c:out>
							</c:if> <c:if test="${forumPostReportVO.forumPostReportType == 1}"
								var="condition">
								<c:out value="1檢舉成功" escapeXml="false"></c:out>
							</c:if> <c:if test="${forumPostReportVO.forumPostReportType == 2}"
								var="condition">
								<c:out value="2檢舉失敗" escapeXml="false"></c:out>
							</c:if></td>
					</tr>
					<tr>
						<td>檢舉原因</td>
						<td>${forumPostReportVO.forumPostReportWhy}</td>
					</tr>
					<tr>
						<td>檢舉時間</td>
						<td><fmt:formatDate
								value="${forumPostReportVO.forumPostReportTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
					<tr>
					</tr>

				</table>

				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/forum/forumPostReportOneEditServlet"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改狀態"> <input type="hidden"
						name="forumPostReportNo"
						value="${forumPostReportVO.forumPostReportNo}">
				</FORM>
			</div>
		</section>

		<!--main content end-->
		<div style="display: inline-block; padding-left: 15px">
			<a
				href="<%=request.getContextPath()%>/backend/forum/listAllForumPostReport.jsp">
				<button>返回文章檢舉列表</button>
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

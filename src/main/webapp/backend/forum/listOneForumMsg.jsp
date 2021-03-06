<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forummsg.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>

<%
// 取得來自 ForumMsgEditUpdateServlet 的 ForumMsgVO
ForumMsgVO forumMsgVO = (ForumMsgVO) request.getAttribute("forumMsgVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改留言存取</title>

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
							<h3>修改留言-存取成功!</h3>
						</td>
					</tr>
				</table>
				<!-- main content -->
				<table>
					<tr>
						<td>留言編號</td>
						<td>${forumMsgVO.forumMsgNo}</td>
					</tr>
					<tr>
						<td>會員編號</td>
						<td>${forumMsgVO.memNo}</td>
					</tr>
					<tr>
						<td>文章編號</td>
						<td>${forumMsgVO.forumPostNo}</td>
					</tr>
					<tr>
						<td>文章標題</td>
						<td>${forumMsgVO.forumPostVO.forumPostTitle}</td>
					</tr>

					<tr>
						<td>留言狀態</td>
						<td><c:if test="${forumMsgVO.forumMsgType == 0}"
								var="condition">
								<c:out value="0不顯示" escapeXml="false"></c:out>
							</c:if> <c:if test="${forumMsgVO.forumMsgType == 1}" var="condition">
								<c:out value="1顯示" escapeXml="false"></c:out>
							</c:if></td>
							<!--<td>${errorMsgs.forumMsgType}</td>-->
					</tr>
					<tr>
						<td>留言內容</td>
						<td>${forumMsgVO.forumMsg}</td>
					</tr>
					<tr>
						<td>發表時間</td>
						<td><fmt:formatDate value="${forumMsgVO.forumMsgTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</table>

				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/forum/forumMsgOneEdit"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改狀態"> <input type="hidden"
						name="forumMsgNo" value="${forumMsgVO.forumMsgNo}">
				</FORM>
			</div>
		</section>

		<!--main content end-->
		<div style="display: inline-block; padding-left: 15px">
			<a
				href="<%=request.getContextPath()%>/backend/forum/listAllForumMsg.jsp">
				<button>返回留言列表</button>
			</a>
		</div>

		<div style="display: inline-block;">
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

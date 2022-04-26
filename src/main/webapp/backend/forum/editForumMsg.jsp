<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forummsg.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>

<%
// 取得來自 ForumMsgOneEditServlet 的 ForumMsgVO
ForumMsgVO forumMsgVO = (ForumMsgVO) request.getAttribute("forumMsgVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改留言</title>

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
							<h3>修改留言資料</h3>
						</td>
					</tr>
				</table>
				<!-- main content -->

				<form method="post"
					action="<%=request.getContextPath()%>/forum/forumMsgEditUpdate"
					name="form1">
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
							<td><select size="1" name="forumMsgType">
									<option value="0"
										<c:if test="${forumMsgVO.forumMsgType == 0}"><c:out value="selected"></c:out></c:if>>0不顯示</option>
									<option value="1"
										<c:if test="${forumMsgVO.forumMsgType == 1}"><c:out value="selected"></c:out></c:if>>1顯示</option>
							</select></td>
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

					<input type="hidden" name="forumMsgNo"
						value="${forumMsgVO.forumMsgNo}"> <input type="submit"
						value="確認修改">
				</form>

				<div style="height: 15px"></div>

				<div style="display: inline-block;">
					<a
						href="<%=request.getContextPath()%>/backend/forum/listAllForumMsg.jsp">
						<button>返回留言檢舉列表</button>
					</a>
				</div>
			</div>
		</section>

		<!--main content end-->

	</section>
</body>
</html>

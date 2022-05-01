<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>

<%
// 取得來自 ForumMsgEditUpdateServlet 的 ForumMsgVO
MemVO memVO = (MemVO) request.getAttribute("memVO");
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
							<h3>修改會員禁言狀態-存取成功!</h3>
						</td>
					</tr>
				</table>
				<!-- main content -->
				<table>
					<tr>
						<td>會員編號</td>
						<td>${memVO.memNo}</td>
					</tr>
					<tr>
						<td>會員名稱</td>
						<td>${memVO.memName}</td>
					</tr>
					<tr>
					<td>禁言狀態</td>
						<td><c:if test="${memVO.isMute == 0}" var="condition">
								<c:out value="0正常" escapeXml="false"></c:out>
							</c:if> <c:if test="${memVO.isMute == 1}" var="condition">
								<c:out value="1禁言" escapeXml="false"></c:out>
							</c:if></td>
					</tr>
				</table>

				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/forum/forumGetOneBadMem"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改狀態"> <input type="hidden"
						name="memNo" value="${memVO.memNo}">
				</FORM>
			</div>
		</section>

		<!--main content end-->
		<div style="display: inline-block; padding-left: 15px">
			<a
				href="<%=request.getContextPath()%>/backend/forum/listAllBadMemNo.jsp">
				<button>返回會員列表</button>
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

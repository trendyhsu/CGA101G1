<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>

<%
// 取得來自 ForumMsgOneEditServlet 的 ForumMsgVO
MemVO memVO = (MemVO) request.getAttribute("memVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改禁言狀態</title>

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
							<h3>修改禁言狀態</h3>
						</td>
					</tr>
				</table>
				<!-- main content -->

				<form method="post"
					action="<%=request.getContextPath()%>/forum/forumBadMemEditUpdate"
					name="form1">
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
							<td><select size="1" name="isMute">
									<option value="0"
										<c:if test="${memVO.isMute == 0}"><c:out value="selected"></c:out></c:if>>0正常</option>
									<option value="1"
										<c:if test="${memVO.isMute == 1}"><c:out value="selected"></c:out></c:if>>1禁言</option>
							</select></td>
						</tr>
					</table>

					<input type="hidden" name="memNo"
						value="${memVO.memNo}"> <input type="submit"
						value="確認修改">
				</form>

				<div style="height: 15px"></div>

				<div style="display: inline-block;">
					<a
						href="<%=request.getContextPath()%>/backend/forum/listAllBadMemNo.jsp">
						<button>返回會員列表</button>
					</a>
				</div>
			</div>
		</section>

		<!--main content end-->

	</section>
</body>
</html>

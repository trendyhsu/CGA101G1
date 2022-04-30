<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>

<%
MemService memSvc = new MemService();
List<MemVO> list = memSvc.listAllMem();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>禁言會員狀態</title>

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

				<h3>會員列表</h3>

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
				<th>會員編號&emsp;</th>
				<th>會員名稱&emsp;</th>
				<th>禁言狀態&emsp;</th>
				<th></th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="memVO" items="${list}"
				begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

				<tr>
					<td>${memVO.memNo}&emsp;</td>
					<td>${memVO.memName}&emsp;</td>
					<td><c:if test="${memVO.isMute == 0}"
							var="condition">
							<c:out value="0正常" escapeXml="false"></c:out>
						</c:if> <c:if test="${memVO.isMute == 1}"
							var="condition">
							<c:out value="1禁言" escapeXml="false"></c:out>
						</c:if></td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/forum/forumGetBadMem"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改狀態"> <input type="hidden"
								name="memNo"
								value="${memVO.memNo}">
						</FORM>
					</td>

				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>
</body>
</html>
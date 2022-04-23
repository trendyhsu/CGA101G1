<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forummsg.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>
<%-- <%@include file="/backend/bid/assets/headerCDN.txt" %> --%>

<%
ForumMsgService forumMsgSvc = new ForumMsgService();
List<ForumMsgVO> list = forumMsgSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有討論區留言</title>

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

	<div id="bid-content"
		style="position: absolute; left: 230px; top: 80px; width: 80%">

		<table id="table-1">
			<tr>

				<h3>所有討論區留言資料</h3>

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

		<table class="showPanel" style="table-layout: fixed; color: black;">
			<tr>
				<th>留言編號</th>
				<th>會員編號</th>
				<th>文章編號</th>
				<th>留言狀態</th>
				<th>留言內容</th>
				<th>發表時間</th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="forumMsgVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr>
					<td>${forumMsgVO.forumMsgNo}</td>
					<td>${forumMsgVO.memNo}</td>
					<td>${forumMsgVO.forumPostNo}</td>
					<td><c:if test="${forumMsgVO.forumMsgType == 0}" var="condition">
							<c:out value="0<br>不顯示" escapeXml="false"></c:out>
						</c:if> <c:if test="${forumMsgVO.forumMsgType == 1}" var="condition">
							<c:out value="1<br>顯示" escapeXml="false"></c:out>
						</c:if></td>
					<td>${forumMsgVO.forumMsg}</td>
					<td><fmt:formatDate value="${forumMsgVO.forumMsgTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>

					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/BidRecordGetOneByBidProductNo"
						style="margin-bottom: 0px;">
						<input type="submit" value="查看"> <input type="hidden"
							name="bidProductNo" value="${bidProductVO.bidProductNo}">
					</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/bidProductEdit"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改"> <input type="hidden"
								name="bidProductNo" value="${bidProductVO.bidProductNo}">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/xxxx"
							style="margin-bottom: 0px;">
							<input type="submit" value="撥付"> <input type="hidden"
								name="bidProductNo" value="${bidProductVO.bidProductNo}">
							<input type="hidden" name="action" value="pay">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>


</body>
</html>
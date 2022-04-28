<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bidproduct.model.*"%>
<%@ page import="com.bidpic.model.*"%>
<%@ page import="com.bidrecord.model.*"%>

<%@include file="/backend/share.jsp"%>
<%-- <%@include file="/backend/bid/assets/headerCDN.txt" %> --%>

<%
	List<BidRecordVO> list = (List<BidRecordVO>) request.getAttribute("bidRecordVO");
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>單一競標商品出價紀錄</title>
<style type="text/css">
table.showPanel {
	font-family: verdana,arial,sans-serif;
	font-size:12px;
	color:black;
	border-color: #666666;
	border-collapse: collapse;
	width: 100%;
}
table.showPanel th {
	border-width: 1px;
	padding: 8px;
	border-bottom-style:solid;
	border-color: #666666;
	background-color: #b2cdcc;
	vertical-align: middle;
	text-align: center;
}
table.showPanel td {
	border-width: 1px;
	padding: 8px;
	border-bottom-style:solid;
	border-color: #666666;
	background-color: #ffffff;
}
h3{
font-weight: bold;
color: #547492;
}
</style>
</head>
<body>
<!--main content start-->
<section id="main-content">
	<section class="wrapper">

	<div id="bid-content">
		
		<table id="table-1">
			<tr>
				<td>
			 		<h3>單一競標商品競標紀錄</h3>
				</td>
			</tr>
		</table>	
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table class="showPanel">
	<tr>
		<th>競標商品編號</th>
		<th>出價編號</th>
		<th>出價會員</th>
		<th>出價金額</th>
		<th>出價時間</th>
	</tr>
<%@ include file="page1.file" %> 
	<c:if test="${list == null}">
	<tr>
		<td colspan="5">
		目前尚無資料!
		</td>
	</tr>
	</c:if>
	<c:forEach var="bidRecordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr align='center' valign="middle">
			<td>${bidRecordVO.bidProductNo}</td>
			<td>${bidRecordVO.bidRecordNo}</td>
			<td>${bidRecordVO.memVO.memName}</td>
			<td>${bidRecordVO.bidPrice}</td>
			<td><fmt:formatDate value="${bidRecordVO.bidTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
<div style="text-align: center;">
	<a href="<%=request.getContextPath()%>/backend/bid/listAllBid.jsp" style="font-size: 1.5rem">回競標商品列表</a>
</div>
</div>
	</section>

	<!--main content end-->

</section>
</body>
</html>
<%@page import="com.bidapplylist.model.BidApplyListVO"%>
<%@page import="com.bidapplylist.model.BidApplyListService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.bidproduct.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>
<%-- <%@include file="/backend/bid/assets/headerCDN.txt" %> --%>

<%
BidApplyListService bidApplyListSvc = new BidApplyListService();
List<BidApplyListVO> list = bidApplyListSvc.getAll();
pageContext.setAttribute("list", list);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有競標商品申請單</title>

<style type="text/css">
th{
	border-bottom-style:solid;
	background-color: #b2cdcc;
}
td{
	border-bottom-style:solid;
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

			 		<h3>所有競標商品申請單</h3>

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
		<%-- 成功表列 --%>
			<p style="color: red;">${successMsg}</p>

		<table class="showPanel" style="table-layout: fixed; color: black;">
			<tr>
				<th>申請單編號</th>
				<th>賣家編號</th>
				<th>商品名稱</th>
				<th>商品敘述</th>
				<th>遊戲公司編號</th>
				<th>遊戲種類編號</th>
				<th>遊戲平台編號</th>
				<th>起標價格</th>
				<th>最低增額</th>
				<th>起標時間</th>
				<th>截標時間</th>
				<th>UPC Number</th>
				<th>申請狀態</th>
				<th>上架</th>
				<th>退貨</th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="bidApplyListVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr>
					<td>${bidApplyListVO.bidApplyListNo}</td>
					<td>${bidApplyListVO.memNo}</td>
					<td>${bidApplyListVO.bidName}</td>
					<td>
						<div
							style="width: 100px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							${bidApplyListVO.bidProdDescription}</div>
					</td>
					<td>${bidApplyListVO.gameCompanyNo}</td>
					<td>${bidApplyListVO.gameTypeNo}</td>
					<td>${bidApplyListVO.gamePlatformNo}</td>
					<td>${bidApplyListVO.initialPrice}</td>
					<td>${bidApplyListVO.bidPriceIncrement}</td>
					<td><fmt:formatDate value="${bidApplyListVO.bidLaunchedTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${bidApplyListVO.bidSoldTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${bidApplyListVO.upcNum}</td>
					<td><c:if test="${bidApplyListVO.applyState == 0}" var="condition">
							<c:out value="0<br>待處理" escapeXml="false"></c:out>
						</c:if> <c:if test="${bidApplyListVO.applyState == 1}" var="condition">
							<c:out value="1<br>已上架" escapeXml="false"></c:out>
						</c:if> <c:if test="${bidApplyListVO.applyState == 2}" var="condition">
							<c:out value="2<br>已退貨" escapeXml="false"></c:out>
						</c:if></td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/backend/bid/addBid.jsp"
							style="margin-bottom: 0px;">
							<input type="submit" value="上架"> <input type="hidden"name="bidApplyListNo" value="${bidApplyListVO.bidApplyListNo}">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/bid/bidApplyListReturn"
							style="margin-bottom: 0px;">
							<input type="submit" value="退貨">
							<input type="hidden" name="bidApplyListNo" value="${bidApplyListVO.bidApplyListNo}">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>
	</section>

	<!--main content end-->

</section>

</body>
</html>
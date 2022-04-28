<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.bidproduct.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>
<%-- <%@include file="/backend/bid/assets/headerCDN.txt" %> --%>

<%
BidProductService bidsBidProductSvc = new BidProductService();
List<BidProductVO> list = bidsBidProductSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>競標訂單管理</title>

<style type="text/css">
th{
	border-bottom-style:solid;
	background-color: #b2cdcc;
	vertical-align: middle;
	text-align: center;
}
td{
	border-bottom-style:solid;
}
h3{
font-weight: bold;
color: #547492;
}
#pageNumber, #dataNumber, #pageChange{
float: right;
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

			 		<h3>競標訂單管理</h3>

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
			<tr align='center' valign="middle">
				<th>競標商品編號</th>
				<th>申請單編號</th>
				<th style="width: 10%">商品名稱</th>
				<th>賣家</th>
				<th>得標價</th>
				<th>起標時間</th>
				<th>截標時間</th>
				<th>得標會員</th>
				<th>競標狀態</th>
				<th>收件人姓名</th>
				<th>收件人地址</th>
				<th>收件人電話</th>
				<th>商品狀態</th>
				<th>出貨</th>
				<th>代收金撥付</th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="bidProductVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<c:if test="${bidProductVO.bidState == 1}" var="condition">
					<tr align='center' valign="middle">
						<td>${bidProductVO.bidProductNo}</td>
						<td>${bidProductVO.bidApplyListNo}</td>
						<td>${bidProductVO.bidName}</td>
						<td>${bidProductVO.getMemVOBySellerNo().memName}</td>
						<td>${bidProductVO.bidWinnerPrice}</td>
						<td>
							<fmt:formatDate value="${bidProductVO.bidLaunchedTime}"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>
							<fmt:formatDate value="${bidProductVO.bidSoldTime}"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>${bidProductVO.getMemVOByBuyerNo().memName}</td>
						<td>
							<c:if test="${bidProductVO.bidState == 0}" var="condition">
								<c:out value="0<br>競標中" escapeXml="false"></c:out>
							</c:if> <c:if test="${bidProductVO.bidState == 1}" var="condition">
								<c:out value="1<br>截標" escapeXml="false"></c:out>
							</c:if> <c:if test="${bidProductVO.bidState == 2}" var="condition">
								<c:out value="2<br>流標" escapeXml="false"></c:out>
							</c:if> <c:if test="${bidProductVO.bidState == 3}" var="condition">
								<c:out value="3<br>棄標" escapeXml="false"></c:out>
							</c:if>
						</td>
						<td>${bidProductVO.receiverName}</td>
						<td>${bidProductVO.receiverAddress}</td>
						<td>${bidProductVO.receiverPhone}</td>
						<td>
							<c:if test="${bidProductVO.orderState == 0}">
								<c:out value="0<br>未出貨" escapeXml="false"></c:out>
							</c:if> <c:if test="${bidProductVO.orderState == 1}">
								<c:out value="1<br>訂單處理中" escapeXml="false"></c:out>
							</c:if> <c:if test="${bidProductVO.orderState == 2}">
								<c:out value="2<br>已出貨" escapeXml="false"></c:out>
							</c:if> <c:if test="${bidProductVO.orderState == 3}">
								<c:out value="3<br>取回處理中" escapeXml="false"></c:out>
							</c:if><c:if test="${bidProductVO.orderState == 4}">
								<c:out value="4<br>已重新申請上架" escapeXml="false"></c:out>
							</c:if><c:if test="${bidProductVO.orderState == 5}">
								<c:out value="5<br>已收貨" escapeXml="false"></c:out>
							</c:if><c:if test="${bidProductVO.orderState == 6}">
								<c:out value="6<br>已撥款" escapeXml="false"></c:out>
							</c:if>
						</td>
						<td>
							<c:if test="${bidProductVO.orderState == 1}">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/bid/bidProductShipping"
									style="margin-bottom: 0px;">
									<input type="submit" value="出貨"> <input type="hidden"name="bidProductNo" value="${bidProductVO.bidProductNo}">
								</FORM>
							</c:if>
						</td>
						<td>
							<c:if test="${bidProductVO.orderState == 5}">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/bid/bidProductPay"
									style="margin-bottom: 0px;">
									<input type="submit" value="撥付"> <input type="hidden" name="bidProductNo" value="${bidProductVO.bidProductNo}">
									<input type="hidden" name="action" value="pay">
								</FORM>
							</c:if>
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>

	</section>

	<!--main content end-->

</section>
</body>
</html>
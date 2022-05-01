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

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap" rel="stylesheet">
<style type="text/css">
th{
	border-bottom-style:solid;
	background-color: rgb(179,205,204);
	vertical-align: middle;
	text-align: center;
}
td{
	border-bottom-style:solid;
	font-family: 'Noto Sans TC', sans-serif;
}
h3{
font-weight: bold;
color: #547492;
}
#pageNumber, #dataNumber, #pageChange{
float: right;
font-weight: bold;
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
									style="margin-bottom: 0px;" id="shippingForm">
									<input class="btn btn-primary btn-sm" type="button" value="出貨" id="shippingBtn">
									<input type="hidden"name="bidProductNo" value="${bidProductVO.bidProductNo}">
								</FORM>
							</c:if>
						</td>
						<td>
							<c:if test="${bidProductVO.orderState == 5}">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/bid/bidProductPay"
									style="margin-bottom: 0px;" id="payForm">
									<input class="btn btn-primary btn-sm" type="button" value="撥付" id="pay">
									<input type="hidden" name="bidProductNo" value="${bidProductVO.bidProductNo}">
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
<script src="https://cdn.bootcdn.net/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
<script type="text/javascript">

	// 代收金撥付
let pay = document.querySelector("#pay");
if(pay){
	pay.addEventListener("click",function(){
		swal({
				title: "是否確認撥付？",
				text: "", 
				icon: "info",
			})
			.then(() => {
				swal("撥付成功！", {
				  icon: "success",
				})
			})
			.then(() => {
				setTimeout(pay, 1200);
				function pay(){
					document.querySelector("#payForm").submit();
				}
			 })
		})
}

	// 出貨
let shippingBtn = document.querySelector("#shippingBtn");
if(shippingBtn){
	shippingBtn.addEventListener("click",function(){
		swal({
				title: "確認出貨？",
				text: "", 
				icon: "info",
			})
			.then(() => {
				swal("出貨成功！", {
				  icon: "success",
				})
			})
			.then(() => {
				setTimeout(shipping, 1200);
				function shipping(){
					document.querySelector("#shippingForm").submit();
				}
			 })
	})
}
</script>
</body>
</html>
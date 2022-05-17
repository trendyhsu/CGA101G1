<%@page import="com.bidapplylist.model.BidApplyListVO"%>
<%@page import="com.bidapplylist.model.BidApplyListService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.bidproduct.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>

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

		<table class="showPanel" style="table-layout: fixed; color: black">
			<tr align='center' valign="middle">
				<th style="width: 4%">申請單編號</th>
				<th>賣家</th>
				<th style="width: 12%">商品名稱</th>
				<th>商品敘述</th>
				<th style="width: 6%">遊戲公司</th>
				<th style="width: 6%">遊戲種類</th>
				<th style="width: 6%">遊戲平台</th>
				<th style="width: 6%">起標價格</th>
				<th style="width: 6%">最低增額</th>
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

				<tr align='center' valign="middle">
					<td>${bidApplyListVO.bidApplyListNo}</td>
					<td>${bidApplyListVO.memVO.memName}</td>
					<td>${bidApplyListVO.bidName}</td>
					<td>
						<div
							style="width: 100px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							${bidApplyListVO.bidProdDescription}</div>
					</td>
					<td>${bidApplyListVO.gameCompanyVO.gameCompanyName}</td>
					<td>${bidApplyListVO.gameTypeVO.gameTypeName}</td>
					<td>${bidApplyListVO.gamePlatformTypeVO.gamePlatformName}</td>
					<td>${bidApplyListVO.initialPrice}</td>
					<td>${bidApplyListVO.bidPriceIncrement}</td>
					<td><fmt:formatDate value="${bidApplyListVO.bidLaunchedTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${bidApplyListVO.bidSoldTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${bidApplyListVO.upcNum}</td>
					<td><c:if test="${bidApplyListVO.applyState == 0}" var="condition">
							<c:out value="待處理" escapeXml="false"></c:out>
						</c:if> <c:if test="${bidApplyListVO.applyState == 1}" var="condition">
							<c:out value="已上架" escapeXml="false"></c:out>
						</c:if> <c:if test="${bidApplyListVO.applyState == 2}" var="condition">
							<c:out value="已退貨" escapeXml="false"></c:out>
						</c:if></td>
					<td>
						<c:if test="${bidApplyListVO.applyState == 0}" var="condition">
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/backend/bid/addBid.jsp"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary btn-sm" type="submit" value="上架">
							<input type="hidden"name="bidApplyListNo" value="${bidApplyListVO.bidApplyListNo}">
						</FORM>
						</c:if>
					</td>
					<td>
						<c:if test="${bidApplyListVO.applyState == 0}" var="condition">
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/bid/bidApplyListReturn"
							style="margin-bottom: 0px;" id="returnForm${bidApplyListVO.bidApplyListNo}">
							<input class="btn btn-primary btn-sm returnBtn" type="button" value="退貨" id="${bidApplyListVO.bidApplyListNo}">
							<input type="hidden" name="bidApplyListNo" value="${bidApplyListVO.bidApplyListNo}">
						</FORM>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>
	</section>

	<!--main content end-->

</section>
<script src="https://cdn.bootcdn.net/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
<script>
	// 退貨
let returnBtn = $(".returnBtn");
var targetNum = 0;
if(returnBtn){
	returnBtn.click(function(e){
		targetNum = e.target.id;
		swal({ 
			  title: "確定退貨嗎？", 
			  text: "", 
			  type: "warning",
			  showCancelButton: true, 
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "確定刪除！", 
			  cancelButtonText: "取消刪除！",
			  closeOnConfirm: false, 
			  closeOnCancel: false  
			}).then(
			function(isConfirm){ 
			  if (isConfirm) {
			    swal("退貨成功！", "商品狀態已更新。","success")
			    .then(() => {
				setTimeout(returnGame, 100);
				function returnGame(){
					let returnFormName = "#returnForm"+targetNum;
					document.querySelector(returnFormName).submit();
				}
			 }); 
			  } else { 
			    swal("退貨取消！", "商品狀態未更新",
			"error"); 
			  } 
			});
	})
}
</script>
</body>
</html>
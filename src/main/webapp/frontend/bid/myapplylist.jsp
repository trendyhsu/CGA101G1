<%@page import="com.bidapplylist.model.BidApplyListVO"%>
<%@page import="com.bidproduct.model.BidProductVO"%>
<%@page import="java.util.List"%>
<%@page import="com.bidproduct.model.BidProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/frontend/fronthead.jsp" %>

<%
List<BidApplyListVO> list = (List<BidApplyListVO>)request.getAttribute("bidApplyListVOs");
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的申請單狀態</title>
<style type="text/css">
table{
font-weight: bold;
}
table tr td,th{
border-bottom: solid;
}
th{
background-color: #b2cdcc;
}
#pageNumber, #dataNumber{
float: right;
}
</style>
</head>
<body>
                    <!-- End Profile Menu -->
                    <!-- Content 主要內容區 要修改的部分都塞在這個裡面 -->
					<!-- 內容直接寫在<div class="table-responsive fs-md mb-4">裡面 -->
                    <div class="col-lg-9 col-xxl-9">
                        <div class="table-responsive fs-md mb-4">

	<div id="bid-content">
		
		<table id="table-1">
			<tr>

			 		<h3>我的申請單</h3>

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
				<th>申請單編號</th>
				<th>商品名稱</th>
				<th>商品敘述</th>
				<th>遊戲公司</th>
				<th>遊戲種類</th>
				<th>遊戲平台</th>
				<th>起標價格</th>
				<th>最低增額</th>
				<th>起標時間</th>
				<th>截標時間</th>
				<th>UPC Number</th>
				<th>申請狀態</th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="bidApplyListVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr align='center' valign="middle">
					<td>${bidApplyListVO.bidApplyListNo}</td>
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
					<td style="width:10%; word-wrap: break-word"><fmt:formatDate value="${bidApplyListVO.bidLaunchedTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td style="width:10%; word-wrap: break-word"><fmt:formatDate value="${bidApplyListVO.bidSoldTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${bidApplyListVO.upcNum}</td>
					<td><c:if test="${bidApplyListVO.applyState == 0}" var="condition">
							<c:out value="0<br>待處理" escapeXml="false"></c:out>
						</c:if> <c:if test="${bidApplyListVO.applyState == 1}" var="condition">
							<c:out value="1<br>已上架" escapeXml="false"></c:out>
						</c:if> <c:if test="${bidApplyListVO.applyState == 2}" var="condition">
							<c:out value="2<br>已退貨" escapeXml="false"></c:out>
						</c:if></td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>

                        </div>
					</div>
                    <!-- End Content -->
               	</div>
            </div>
        </div>
        <!--Table -->
    </main>
    <!-- End Main -->
</body>
</html>
<%@include file="/frontend/frontfoot.jsp" %>
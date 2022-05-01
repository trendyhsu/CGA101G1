<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memCoupon.model.*"  %>
<%@include file="/backend/share.jsp"%>

<%
	MemCouponService memCouponService = new MemCouponService();
	List<MemCouponVO> list = memCouponService.showAllMemCoupon();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
th{
	border-bottom-style:solid;
	background-color: #b2cdcc;
	vertical-align: middle;
	text-align: center;
}

td{
	border-bottom-style:solid;
	  padding: 5px;
    text-align: center;
}

h3{
font-weight: bold;
color: #547492;
}

#pageNumber, #dataNumber, #pageChange{
float: right;
}
.showPanel{
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
</style>

</head>
<body bgcolor='white'>
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<div id="bid-content">
<!-- <h4>此頁練習採用 EL 的寫法取值:</h4> -->
<table id="table-1">
	<tr><td>
		 <h3>已發放給所有會員的優惠券</h3>
	</td></tr>
</table>


<li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/memCoupon/ShowOneCouponServelet" >
        <b>輸入優惠券編號查詢那些會員擁有 (如:13001):</b>
<%--         <input type="hidden" name="couponTypeNo"  value="${memCouponVO.couponTypeNo}"> --%>
        <input type="text" name="couponTypeNo" value="${param.couponTypeNo}">
        <input type="submit" value="送出"><font color=red>${errorMsgs.couponTypeNo}</font>
    </FORM>
</li>
<li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/memCoupon/ShowOneMemCouponServlet" >
        <b>輸入會員編號查詢會員擁有那些優惠券 (如:11001):</b>
        <input type="text" name="memNo" value="">
        <input type="submit" value="送出"><font color=red>${errorMsgs.memNo}</font>
    </FORM>
</li>
  
<table class="showPanel" style="table-layout: fixed; color: black;">
	<tr align='center' valign="middle">
		<th>優惠券種類編號</th>
		<th>會員優惠券名稱</th>
		<th>優惠券折價金額</th>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>使用狀態</th>
		<th>優惠券時效</th>
<!-- 		<th>刪除優惠券</th> -->
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="memCouponVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >

		<input type="hidden" name="memCouponNo"  value="${memCouponVO.memCouponNo}">
		<input type="hidden" name="couponTypeNo"  value="${memCouponVO.couponTypeNo}">
		<input type="hidden" name="memNo"  value="${memCouponVO.memNo}">
		<tr>
			<td>${memCouponVO.couponTypeNo}</td>
			<td>${memCouponVO.couponTypeVO.couponName}</td>
			<td>${memCouponVO.couponTypeVO.discountPrice}</td>
			<td>${memCouponVO.memNo}</td>
			<td>${memCouponVO.memVO.memName}</td>
			<td>${memCouponVO.couponState}</td>
			<td>${memCouponVO.couponTypeVO.couponDeadline}</td>
		</tr>
	</c:forEach>
</table>

<%@ include file="page2.file" %>

	</div>
	</section>
</section>
	<!--main content end-->
</body>

</html>
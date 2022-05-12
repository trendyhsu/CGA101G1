<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memCoupon.model.*"  %>
<%@include file="/backend/share.jsp"%>

<%
List<MemCouponVO> list = (List<MemCouponVO>)request.getAttribute("list");//存入req的物件
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
	<c:forEach var="memCouponVO" items="${list}" begin="0" end="0">
		 <h3>${memCouponVO.couponTypeVO.couponName} 優惠券有哪些會員擁有</h3>
	</c:forEach>
	</td></tr>
</table>

<table class="showPanel" style="table-layout: fixed; color: black;">
	<tr align='center' valign="middle">
		<th>優惠券種類編號</th>
		<th>會員優惠券名稱</th>
		<th>優惠券折價金額</th>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>使用狀態</th>
		<th>優惠券時效</th>
	</tr>
<%-- 	<%@ include file="page1.file" %>  --%>
<c:forEach var="memCouponVO" items="${list}" >
<%-- 這行原本在上面 		begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" --%>
		<tr>
			<td>${memCouponVO.couponTypeNo}</td>
			<td>${memCouponVO.couponTypeVO.couponName}</td>
			<td>${memCouponVO.couponTypeVO.discountPrice}</td>
			<td>${memCouponVO.memNo}</td>
			<td>${memCouponVO.memVO.memName}</td>
			<td>
			<c:if test="${memCouponVO.couponState == 0}">
			未使用
			</c:if>
			<c:if test="${memCouponVO.couponState == 1}">
			已使用
			</c:if>
			<c:if test="${memCouponVO.couponState == 2}">
			已過期
			</c:if>
			</td>
			<td>${memCouponVO.couponTypeVO.couponDeadline}</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/couponType/DeleteCouponTypeServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="memCouponNo"  value="${memCouponVO.memCouponNo}"> --%>
<%-- 			   	 <input type="hidden" name="couponTypeNo"  value="${memCouponVO.couponTypeNo}"> --%>
<%-- 				 <input type="hidden" name="memNo"  value="${memCouponVO.memNo}"> --%>
<!-- 			   </FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/memCoupon/DeleteMemCouponServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除此種優惠券">
			   	 <input type="hidden" name="couponTypeNo"  value="${couponTypeNo}">
			   </FORM>
<%-- <%@ include file="page2.file" %> --%>
	</div>
	</section>
</section>
	<!--main content end-->
</body>

</html>
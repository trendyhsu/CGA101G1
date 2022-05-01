<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.couponType.model.*"  %>
<%@include file="/backend/share.jsp"%>

<%
	CouponTypeService couponTypeSvc = new CouponTypeService();
	List<CouponTypeVO> list = couponTypeSvc.showAllCouponType();
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
		 <h3>所有優惠券</h3>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message.key} : ${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>
<!-- 搜尋優惠券 -->
<li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/couponType/GetOneCouponTypeServlet" >
        <b>輸入優惠券編號查詢 (如:13001):</b>
        <input type="text" name="couponTypeNo" value=""><font color=red>${errorMsgs.couponTypeNo}</font>
        <input type="submit" value="送出">
    </FORM>
</li>
  
<table class="showPanel" style="table-layout: fixed; color: black;">
			<tr align='center' valign="middle">
		<th>優惠券種類編號</th>
		<th>優惠券名稱</th>
		<th>優惠券折價價格</th>
		<th>優惠券時效</th>
		<th>優惠券數量</th>
		<th>優惠券描述</th>
		<th>更改優惠券</th>
		<th>刪除優惠券</th>
	</tr>
	
	<%@ include file="page1.file" %> 
	<c:forEach var="couponTypeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

		<input type="hidden" name="couponTypeNo"  value="${couponTypeVO.couponTypeNo}">
		<tr>
			<td>${couponTypeVO.couponTypeNo}</td>
			<td>${couponTypeVO.couponName}</td>
			<td>${couponTypeVO.discountPrice}</td>
			<td>${couponTypeVO.couponDeadline}</td>
			<td>${couponTypeVO.couponQuantity}</td>
			<td>${couponTypeVO.couponDescription}</td> 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/couponType/GetWhichOneUpdateServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="couponTypeNo"  value="${couponTypeVO.couponTypeNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/couponType/DeleteCouponTypeServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="couponTypeNo"  value="${couponTypeVO.couponTypeNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
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
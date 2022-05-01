<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.couponType.model.*"  %>
<%@include file="/backend/share.jsp"%>

<%
CouponTypeVO couponTypeVO = (CouponTypeVO) request.getAttribute("couponTypeVO");//存入req的物件
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
<body>
 <!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<div id="bid-content">
		
<table id="table-1">
	<tr><td>
		 <h3>優惠券</h3>
	</td></tr>
</table>

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
		<tr>
			<td>${couponTypeVO.couponTypeNo}</td>
			<td>${couponTypeVO.couponName}</td>
			<td>${couponTypeVO.discountPrice}</td>
			<td>${couponTypeVO.couponDeadline}</td>
			<td>${couponTypeVO.couponQuantity}</td>
			<td>${couponTypeVO.couponDescription}</td> 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/couponType/GetWhichOneUpdateServlet" style="margin-bottom: 0px;">
				 <input type="hidden" name="couponTypeNo"  value="${couponTypeVO.couponTypeNo}">
				 <input type="hidden" name="couponName"  value="${couponTypeVO.couponName}">
			     <input type="submit" value="修改">
			     <input type="hidden" name="couponTypeNo"  value="${couponTypeVO.couponTypeNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/couponType/DeleteCouponTypeServlet" style="margin-bottom: 0px;">
			     <input type="hidden" name="couponTypeNo"  value="${couponTypeVO.couponTypeNo}">
				 <input type="hidden" name="couponName"  value="${couponTypeVO.couponName}">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="couponTypeNo"  value="${couponTypeVO.couponTypeNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
</table>
	</div>
	</section>
</section>
	<!--main content end-->

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memCoupon.model.*"  %>    
<%@include file="/backend/share.jsp"%>

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
		
<table id="table-1">
	<tr><td>
		 <h3>優惠券發放</h3></td><td>
	</td></tr>
</table>

<div style=" justify-content: center;">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/memCoupon/SendMemCouponServlet" name="form1">
<table>
	<tr>
		<td>發放數量:</td>
		<td><input type="TEXT" name="couponQuantity" size="45" 
			 value="${param.couponQuantity}"/></td><td>${errorMsgs.couponQuantity}</td>
	</tr>
	
	<jsp:useBean id="couponTypeService" scope="page" class="com.couponType.model.CouponTypeService" />
	<tr>
		<td>發放優惠券種類:<font color=red><b>*</b></font></td>
		<td><select size="1" name="couponTypeNo">
			<c:forEach var="couponTypeVO" items="${couponTypeService.showAllCouponType()}">
				<option value="${couponTypeVO.couponTypeNo}" ${(param.couponTypeNo==couponTypeVO.couponTypeNo)? 'selected':'' } >${couponTypeVO.couponName}
			</c:forEach>
		</select></td>
	</tr>

</table>

<br>
<input type="submit" value="發放"></FORM>
</div>

	</div>
	</section>
</section>
	<!--main content end-->
</body>
</html>
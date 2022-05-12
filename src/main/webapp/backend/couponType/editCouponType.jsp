<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.couponType.model.*"%>
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
<body bgcolor='white'>
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<div id="bid-content">
		
	<table id="table-1">
		<tr>
			<td>
				<h3>優惠券修改</h3>
			</td>
		</tr>
	</table>
	<%-- 錯誤表列 --%>
	<%-- <c:if test="${not empty errorMsgs}"> --%>
	<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
	<!-- 	<ul> -->
	<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
	<%-- 			<li style="color:red">${message.value}</li> --%>
	<%-- 		</c:forEach> --%>
	<!-- 	</ul> -->
	<%-- </c:if> --%>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/couponType/EditCouponTypeServlet"
		name="form1">

		<table>
			<tr>
				<td>優惠券編號:<font color=red><b>*</b></font></td>
				<td>${param.couponTypeNo}</td>
			</tr>
			<tr>
				<td>優惠券名稱:</td>
				<td><input type="TEXT" name="couponName" size="45"
					value="${param.couponName}" /></td>
				<td>${errorMsgs.couponName}</td>
			</tr>
			<tr>
				<td>優惠券折價價格:</td>
				<td><input type="TEXT" name="discountPrice" size="45"
					value="${param.discountPrice}" /></td>
				<td>${errorMsgs.discountPrice}</td>
			</tr>
			<tr>
				<td>優惠券時效:</td>
				<td><input name="couponDeadline" id="couponDeadline"
					type="text" /></td>
				<td>${errorMsgs.couponDeadline}</td>
			</tr>
			<tr>
				<td>優惠券數量:</td>
				<td><input type="TEXT" name="couponQuantity" size="45"
					value="${param.couponQuantity}" /></td>
				<td>${errorMsgs.couponQuantity}</td>
			</tr>
			<tr>
				<td>優惠券描述:</td>
				<td><textarea name="couponDescription"
						style="min-height: 100px; min-width: 300px; max-height: 50px; max-width: 70px;"></textarea></td>
				<td>${errorMsgs.comm}</td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> 
		<input type="hidden" name="couponTypeNo" value="${param.couponTypeNo}">
		<input type="submit" value="送出修改"><td>${errorMsgs.Dup}</td>
	</FORM>
	
	</div>
	</section>
</section>
	<!--main content end-->
</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
java.sql.Date couponDeadline = null;
try {
	couponDeadline = java.sql.Date.valueOf(request.getParameter("couponDeadline").trim());
} catch (Exception e) {
	couponDeadline = new java.sql.Date(System.currentTimeMillis());
}
%>
<!-- <link rel="stylesheet" type="text/css" -->
<%-- 	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" /> --%>
<%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script> --%>
<!-- <script -->
<%-- 	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script> --%>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#couponDeadline').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: new Date()  		<%-- // value:  <%--  new Date(), '<%=hiredate%>' --%>
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
//         1.以下為某一天之前的日期無法選擇
          var somedate1 = new Date();
                  $('#couponDeadline').datetimepicker({
                      beforeShowDay: function(date) {
                    	  if (  date.getYear() <  somedate1.getYear() || 
             		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
             		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                          ) {
                               return [false, ""]
                          }
                          return [true, ""];
                  }});
        </script>
</html>
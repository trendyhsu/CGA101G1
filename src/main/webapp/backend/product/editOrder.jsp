<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/backend/share.jsp"%>
<!-- 匯入java -->
<%@ page import="java.util.*"%>
<%@ page import="com.order.model.OrderVO"%>
<%@ page import="com.order.model.*"%>
<%
OrderVO orderVO = (OrderVO) request.getAttribute("orderVO"); //EmpServlet.java (Concroller) 存入req的orderVO物件 (包括幫忙取出的orderVO, 也包括輸入資料錯誤時的orderVO物件)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<title>POP.Game ServerSide</title>

<!-- **********************************************************************************************************************************************************
      MAIN CONTENT 內容寫在這裡面
      *********************************************************************************************************************************************************** -->
<!--main content start-->
<section id="main-content">
	<section class="wrapper">

		<div class="row">

			<div class="col-lg-9 main-chart">
				<%=orderVO == null%>
				--${orderVO.orderNo}--
				<h3>訂單資料修改:</h3>

				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<FORM METHOD="post" ACTION="/CGA101G1/porduct/modOrder" name="form1">
					<table>
						<tr>
							<td>訂單編號:<font color=red><b>*</b></font></td>
							<td><input type="number" name="OrderNo"
								value="<%=orderVO.getOrderNo()%>" readonly/></td>
						</tr>
						<tr>
							<td>訂購金額:</td>
						    <td><input type="number" name="OrderTotalPrice"
								value="<%=orderVO.getOrderTotalPrice()%>"  readonly style="border: 0"/></td>
						</tr>
						<tr>
							<td>訂購日期:</td>
							<td><input type="text" name="TranTime"
								value="<%=orderVO.getTranTime()%>"  readonly style="border: 0"/></td>
						</tr>
						<tr>
							<td>訂單狀態</td>
							<td><select size="1" name="OrderState" required>
									<option value="0"
										<c:if test="${orderVO.orderState == 0}"><c:out value="selected"></c:out></c:if>>尚未出貨</option>
									<option value="1"
										<c:if test="${orderVO.orderState == 1}"><c:out value="selected"></c:out></c:if>>已出貨</option>
									<option value="2"
										<c:if test="${orderVO.orderState == 2}"><c:out value="selected"></c:out></c:if>>完成訂單</option>
									<option value="3"
										<c:if test="${orderVO.orderState == 3}"><c:out value="selected"></c:out></c:if>>退貨</option>
									<option value="4"
										<c:if test="${orderVO.orderState == 4}"><c:out value="selected"></c:out></c:if>>訂單作廢</option>
							</select></td>
						</tr>
						<tr>
							<td>收件人姓名:</td>
							<td><input type="TEXT" name="ReceiverName" size="45"
								value="<%=orderVO.getReceiverName()%>" /></td>
						</tr>
						<tr>
							<td>收件人地址:</td>
							<td><input type="TEXT" name="ReceiverAddress" size="45"
								value="<%=orderVO.getReceiverAddress()%>" /></td>
						</tr>
						<tr>
							<td>收件人電話:</td>
							<td><input type="number" name="ReceiverPhone" size="45"
								value="<%=orderVO.getReceiverPhone()%>" /></td>
						</tr>
						<tr>
							<td>取貨方式:</td>
							<td><select size="1" name="PickupMethod" required>
									<option value="0"
										<c:if test="${orderVO.pickupMethod == 0}"><c:out value="selected"></c:out></c:if>>宅配</option>
									<option value="1"
										<c:if test="${orderVO.pickupMethod == 1}"><c:out value="selected"></c:out></c:if>>超商取貨</option>
							</select></td>
						</tr>
						<tr>
							<td>物流編號:</td>
							<td><input type="number" name="TrackingNum" size="45"
								value="<%=orderVO.getTrackingNum()%>" required />如果訂單作廢請填0</td>
						</tr>

					</table>
					<br> <input type="hidden" name="action" value="update">
					<input type="hidden" name="empno" value="<%=orderVO.getOrderNo()%>">
					<input type="submit" value="送出修改">
				</FORM>
			</div>
			<!-- /col-lg-9 END SECTION MIDDLE -->

		</div>
	</section>
</section>

<!--main content end-->

</section>
</body>


<!--如果需要編輯日期使用jquery的日期套件-->

<!-- <link rel="stylesheet" type="text/css" -->
<%-- 	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" /> --%>
<%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script> --%>
<!-- <script -->
<%-- 	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script> --%>

<!-- <style> -->
<!-- /* .xdsoft_datetimepicker .xdsoft_datepicker { */ -->
<!-- /* 	width: 300px; /* width:  300px; */ */ -->
<!-- /* } */ -->

<!-- /* .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box { */ -->
<!-- /* 	height: 151px; /* height:  151px; */ */ -->
<!-- /* } */ -->
<!-- </style> -->

<!-- <script> -->
<!-- //         $.datetimepicker.setLocale('zh'); -->
<!-- //         $('#f_date1').datetimepicker({ -->
<!-- //            theme: '',              //theme: 'dark', -->
<!-- //  	       timepicker:false,       //timepicker:true, -->
<!-- //  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘) -->
<!-- //  	       format:'Y-m-d',         //format:'Y-m-d H:i:s', -->
<%--  		   value: '<%=orderVO.getPickupTime()%> --%>
<!-- // 	', // value:   new Date(), -->
<!-- // 	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含 -->
<!-- // 	//startDate:	            '2017/07/10',  // 起始日 -->
<!-- // 	//minDate:               '-1970-01-01', // 去除今日(不含)之前 -->
<!-- // 	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後 -->
<!-- // 	}); -->

<!-- 	// ----------------------------------------------------------以下用來排定無法選擇的日期----------------------------------------------------------- -->

<!-- 	//      1.以下為某一天之前的日期無法選擇 -->
<!-- 	//      var somedate1 = new Date('2017-06-15'); -->
<!-- 	//      $('#f_date1').datetimepicker({ -->
<!-- 	//          beforeShowDay: function(date) { -->
<!-- 	//        	  if (  date.getYear() <  somedate1.getYear() ||  -->
<!-- 	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) ||  -->
<!-- 	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate()) -->
<!-- 	//              ) { -->
<!-- 	//                   return [false, ""] -->
<!-- 	//              } -->
<!-- 	//              return [true, ""]; -->
<!-- 	//      }}); -->

<!-- 	//      2.以下為某一天之後的日期無法選擇 -->
<!-- 	//      var somedate2 = new Date('2017-06-15'); -->
<!-- 	//      $('#f_date1').datetimepicker({ -->
<!-- 	//          beforeShowDay: function(date) { -->
<!-- 	//        	  if (  date.getYear() >  somedate2.getYear() ||  -->
<!-- 	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) ||  -->
<!-- 	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate()) -->
<!-- 	//              ) { -->
<!-- 	//                   return [false, ""] -->
<!-- 	//              } -->
<!-- 	//              return [true, ""]; -->
<!-- 	//      }}); -->

<!-- 	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期) -->
<!-- 	//      var somedate1 = new Date('2017-06-15'); -->
<!-- 	//      var somedate2 = new Date('2017-06-25'); -->
<!-- 	//      $('#f_date1').datetimepicker({ -->
<!-- 	//          beforeShowDay: function(date) { -->
<!-- 	//        	  if (  date.getYear() <  somedate1.getYear() ||  -->
<!-- 	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) ||  -->
<!-- 	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate()) -->
<!-- 	//		             || -->
<!-- 	//		            date.getYear() >  somedate2.getYear() ||  -->
<!-- 	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) ||  -->
<!-- 	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate()) -->
<!-- 	//              ) { -->
<!-- 	//                   return [false, ""] -->
<!-- 	//              } -->
<!-- 	//              return [true, ""]; -->
<!-- 	//      }}); -->
</script>

</html>
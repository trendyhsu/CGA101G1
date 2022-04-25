<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/frontend/fronthead.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>競標商品申請</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<style>
table {
/* 	background-color: white; */
	margin-bottom: 5px;
	font-size: 13px;
	color:black;
}

table, th, td {
	border-bottom: 1px solid #CCC;
	font-weight:bold;
}

th, td {
	padding: 5px;
	text-align: left;
}
.uploadedImg{
padding: 10px
}

h3{
font-weight: bold;
color: #547492;
}
input, select{
	border-style:solid;
	border-color: gray;
	border-radius: 5px;
	width: 170px
}
textarea {
	border-style:solid;
	border-color: gray;
	border-radius: 5px;
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
					<td>
				 		<h3>競標商品申請</h3>
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
		
<!-- 		主要修改資訊區 -->

		<form method="post"
			action="<%=request.getContextPath()%>/bid/bidApplyListInsert"
			name="form1">
			<table>
				<tr>
					<td>商品名稱</td>
					<td>
					<input type="text" name="bidName" value="${param.bidName}" />
					</td>
					<td>${errorMsgs.bidName}</td>
				</tr>
				<tr>
					<td>商品敘述</td>
					<td><textArea name="bidProdDescription" id="bidProdDescription" rows="10" cols="22" 
					style="resize:none">${param.bidProdDescription}</textArea>
					</td>
					<td>${errorMsgs.bidProdDescription}</td>
				</tr>
				<tr>
					<td>遊戲公司</td>
					<td><input type="text" name="gameCompanyNo" value="${param.gameCompanyNo}" />
					</td>
					<td>${errorMsgs.gameCompanyNo}</td>
				</tr>
				<tr>
		<jsp:useBean id="gameTypeSvc" scope="page" class="com.gametype.model.GameTypeService" />
					<td>遊戲類型</td>
					<td>
				       <select size="1" name="gameTypeNo" style="width: 170px">
	         				<c:forEach var="gameTypeVO" items="${gameTypeSvc.all}" > 
	          					<option value="${gameTypeVO.gameTypeNo}" ${(param.gameTypeNo == gameTypeVO.gameTypeNo) ? "selected" : ""}> ${gameTypeVO.gameTypeName}
	         				</c:forEach>
	       				</select>
					</td>
					<td>${errorMsgs.gameTypeNo}</td>
				</tr>
		<jsp:useBean id="gamePlatformTypeSvc" scope="page" class="com.gameplatformtype.model.GamePlatformTypeService" />
				<tr>
					<td>遊戲平台</td>
					<td>
				       <select size="1" name="gamePlatformNo" style="width: 170px">
	         				<c:forEach var="gamePlatformTypeVO" items="${gamePlatformTypeSvc.all}" > 
	          					<option value="${gamePlatformTypeVO.gamePlatformNo}" ${(param.gamePlatformNo == gamePlatformTypeVO.gamePlatformNo) ? "selected" : ""}> ${gamePlatformTypeVO.gamePlatformName}
	         				</c:forEach>
	       				</select>
					</td>
					<td>${errorMsgs.gamePlatformNo}</td>
				</tr>
				<tr>
					<td>起標價</td>
					<td><input type="number" name="initialPrice" size="45" min="0"
						value="${param.initialPrice}" />
					</td>
					<td>${errorMsgs.initialPrice}</td>
				</tr>
				<tr>
					<td>最低增額</td>
					<td><input type="number" name="bidPriceIncrement" size="45"
						min="0" value="${param.bidPriceIncrement}" />
					</td>
					<td>${errorMsgs.bidPriceIncrement}</td>
				</tr>
				<tr>
					<td>UPC編號</td>
					<td><input name="upcNum" id="upcNum" type="text"
						value="${param.upcNum}">
					</td>
					<td>${errorMsgs.upcNum}</td>
				</tr>
				<tr>
					<td>起標時間</td>
					<td><input name="bidLaunchedTime" id="bidLaunchedTime" type="text">
					</td>
					<td>${errorMsgs.bidLaunchedTime}</td>
				</tr>
				<tr>
					<td>截標時間</td>
					<td><input name="bidSoldTime" id="bidSoldTime" type="text">
					</td>
					<td>${errorMsgs.bidSoldTime}</td>
				</tr>
				<tr>
					<td>
			<input type="submit" value="新增">
			<input type="reset" value="重設">
					</td>
				</tr>
			</table>
		</form>
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
    
<% 
  java.sql.Date bidLaunchedTime = null;
  try {
	  bidLaunchedTime = java.sql.Date.valueOf(request.getParameter("bidLaunchedTime").trim());
   } catch (Exception e) {
	   bidLaunchedTime = new java.sql.Date(System.currentTimeMillis());
   }
  java.sql.Date bidSoldTime = null;
  try {
	  bidSoldTime = java.sql.Date.valueOf(request.getParameter("bidSoldTime").trim());
   } catch (Exception e) {
	   bidSoldTime = new java.sql.Date(System.currentTimeMillis());
   }
%>

<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript">
		$.datetimepicker.setLocale("zh");
		$("#bidLaunchedTime").datetimepicker({
			theme : '', // theme: 'dark', 
			timepicker : true, // timepicker:true,
			step : 1, //step: 60 設定時間時分的間隔
			format : 'Y-m-d H:i:s', //format:'Y-m-d H:i:s',
			value : new Date() // value: new Date(), 會帶入現在時間
		});
		$.datetimepicker.setLocale("zh");
		$("#bidSoldTime").datetimepicker({
			theme : '', // theme: 'dark', 
			timepicker : true, // timepicker:true,
			step : 1, //step: 60 設定時間時分的間隔
			format : 'Y-m-d H:i:s', //format:'Y-m-d H:i:s',
			value : new Date()
		// value:   new Date() 會帶入現在時間
		// rtl: false,                    // false   預設顯示方式   true timepicker和datepicker位置變換
		// format:    'Y/m/d H:i',        // 設定時間年月日時分的格式 如: 2016/11/15 18:00
		// formatTime:    'H:i',          // 設定時間時分的格式
		// formatDate:    'Y/m/d',        // 設定時間年月日的格式
		// startDate:    false,           // new Date(), '1986/12/08', '-1970/01/05','-1970/01/05',
		// step: 10,                      // 設定時間時分的間隔
		// closeOnDateSelect: false,      // true 設定datepicker可點選   false 設定datepicker不可點選 實際上可以雙擊
		// closeOnTimeSelect: true,       // true 設定timepicker可點選   false 設定timepicker不可點選 
		// closeOnWithoutClick: true,     // true 設定點選input可以隱藏datetimepicker   false 設定點選input不可以隱藏datetimepicker  
		// closeOnInputClick: true,       // true 設定點選input可以隱藏datetimepicker   false 設定點選input不可以隱藏datetimepicker  (會有閃動 先隱藏 再顯示)
		// timepicker: true,              // true 顯示timepicker   false 隱藏timepicker
		// datepicker: true,              // true 顯示datepicker   false 隱藏datepicker
		// weeks: false,                  // true 顯示週數   false 隱藏週數
		// defaultTime: false,            // 如果輸入值為空 可用來設定預設顯示時間 use formatTime format (ex. '10:00' for formatTime:    'H:i') 
		// defaultDate: false,            // 如果輸入值為空 可用來設定預設顯示日期 use formatDate format (ex new Date() or '1986/12/08' or '-1970/01/05' or '-1970/01/05')
		// minDate: false,                // 設定datepicker最小的限制日期   如：2016/08/15
		// maxDate: false,                // 設定datepicker最大的限制日期   如：2016/11/15
		// minTime: false,                // 設定timepicker最小的限制時間   如：08:00
		// maxTime: false,                // 設定timepicker最大的限制時間   如：18:00
		// allowTimes: [],                // 設定timepicker顯示的時間   如：allowTimes:['09:00','11:00','12:00','21:00']
		// opened: false,                 // false預設開啟datetimepicker可關閉  true開啟datetimepicker後不可關閉
		// initTime: true,                // 設定timepicker預設時間   如：08:00
		// inline: false,                 // ture設定datetimepicker一直顯示
		// theme: '',                     // ture設定datetimepicker顯示樣式 如: 'dark'
		// withoutCopyright: true,        // ture預設隱藏左下角'xdsoft.net'連結  false 顯示左下角'xdsoft.net'連結 
		// inverseButton: false,          // false 預設   true datepicker的上一月和下一月功能互換  timepicker的上下可點選按鈕功能互換
		// hours12: false,                // true設定12小時格式  false設定24小時格式
		// next: 'xdsoft_next',           // 設定datepicker上一月按鈕的樣式
		// prev : 'xdsoft_prev',          // 設定datepicker下一月按鈕的樣式
		// dayOfWeekStart: 0,             // 設定預設第-列為周幾 如：0 週日  1 週一
		// parentID: 'body',              // 設定父級選擇器
		// timeHeightInTimePicker: 25,    // 設定timepicker的行高
		// timepickerScrollbar: true,     // ture設定timepicker顯示滑動條  false設定timepicker不顯示滑動條
		// todayButton: true,             // ture顯示今天按鈕  false不顯示今天按鈕   位置在datepicker左上角
		// prevButton: true,              // ture顯示上一月按鈕  false不顯示上一月按鈕   位置在datepicker左上角
		// nextButton: true,              // ture顯示下一月按鈕  false不顯示下一月按鈕   位置在datepicker又上角
		// scrollMonth: true,             // ture 設定datepicker的月份可以滑動  false設定datepicker的月份不可以滑動
		// lazyInit: false,               // 翻譯： 初始化外掛發生只有當使用者互動。大大加速外掛與大量的領域的工作
		// mask: false,                   // 使用輸入掩碼。真正的-自動生成一個欄位的“格式”的面具，從0到9的數字，設定為值的最高可能的數字。例如：第一個小時的數字不能大於2，而第一位數字不能大於5  如：{mask:'9999/19/39 29:59',format:'Y/m/d H:i'}
		// validateOnBlur: true,          // 失去焦點時驗證datetime值輸入,。如果值是無效的datetime,然後插入當前日期時間值
		// yearStart: 1950,               // 設定最小的年份   
		// yearEnd: 2050,                 // 設定最大的年份
		// monthStart: 0,                 // 設定最小的月份
		// monthEnd: 11,                  // 設定最大的月份
		// roundTime: 'round',            // 設定timepicker的計算方式  round四捨五入 ceil向上取整 floor向下取整
		// allowDateRe : null,            // 設定正規表示式檢查日期 如：{format:'Y-m-d',allowDateRe:'\d{4}-(03-31|06-30|09-30|12-31)' }
		// disabledDates : [],            // 設定不可點選的日期  如：disabledDates: ['21.11.2016','22.11.2016','23.11.2016','24.11.2016','25.11.2016','26.11.2016']
		// disabledWeekDays: [],          // 設定不可點選的星期  如：disabledWeekDays:[0,3,4]
		// yearOffset: 0,                 // 設定偏移年份  如：2 代表當前年份加2  -2  代表當前年份減2
		// beforeShowDay: null,           // 顯示datetimepicker之前可呼叫的方法  {beforeShowDay:function(d) {console.log("bsd"); } }
		// enterLikeTab: true,            // tab按鍵均可使datetimepicker關閉  true點選回車鍵可使datetimepicker關閉 false點選回車鍵不可使datetimepicker關閉 
		// showApplyButton: false         // 相當於確定按鈕  true顯示  false隱藏
		});

	</script>
</body>
</html>
<%@include file="/frontend/frontfoot.jsp" %>
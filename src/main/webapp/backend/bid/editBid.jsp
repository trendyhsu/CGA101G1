<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.bidpic.model.BidPicVO"%>
<%@page import="com.bidpic.model.BidPicService"%>
<%@ page import="com.bidproduct.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改競標商品</title>

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
padding: 10px;
border-radius: 10px;
}
.imgCss{
margin: 10px;
border-radius: 10px;
}

h3{
font-weight: bold;
color: #547492;
}

.file {
    position: relative;
    display: inline-block;
    background: #428bca;
    border: 1px solid #99D3F5;
    border-radius: 4px;
    padding: 6px 16px;
    overflow: hidden;
    color: #FFFFFF;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
}
.file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover {
    background: #3071a9;
    border-color: #78C3F3;
    color: #FFFFFF;
    text-decoration: none;
}
</style>

</head>
<body>
<section id="main-content">
	<section class="wrapper">


	<div id="bid-content">

		<table id="table-1">
			<tr>
				<td>
			 		<h3>修改競標商品資料</h3>
				</td>
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
			action="<%=request.getContextPath()%>/bid/bidProductEditUpdate"
			name="form1">
			<table>
				<tr>
					<td>競標商品編號</td>
					<td>${bidProductVO.bidProductNo}</td>
				</tr>
				<tr>
					<td>申請單編號</td>
					<td>${bidProductVO.bidApplyListNo}</td>
				<tr>
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
					<td>一般商品名稱</td>
					<td>
				       <select size="1" name="productNo">
	         					<option value="0" ${(productVO.upcNum != bidProductVO.bidApplyListVO.upcNum) ? "selected" : ""} >無對應遊戲
	         				<c:forEach var="productVO" items="${productSvc.GetAllProducts()}" > 
	          					<option value="${productVO.productNo}" ${(productVO.upcNum == bidProductVO.bidApplyListVO.upcNum) ? "selected" : ""} > ${productVO.productName}
	         				</c:forEach>
	       				</select>
					</td>
				</tr>
				<tr>
					<td>商品名稱</td>
					<td><input type="text" name="bidName" size="45"
						value="${bidProductVO.bidName}" /></td>
				</tr>
				<tr>
					<td>商品敘述</td>
					<td><textArea name="bidProdDescription"
							id="bidProdDescription" rows="6" cols="45" style="resize:none;">${bidProductVO.bidProdDescription}</textArea></td>
				</tr>
				<tr>
					<td>賣家</td>
					<td>${bidProductVO.getMemVOBySellerNo().memName}</td>
				</tr>
				<tr>
					<td>起標價</td>
					<td><input type="number" name="initialPrice" size="45" min="0"
						value="${bidProductVO.initialPrice}" /></td>
				</tr>
				<tr>
					<td>最低增額</td>
					<td><input type="number" name="bidPriceIncrement" size="45"
						min="0" value="${bidProductVO.bidPriceIncrement}" /></td>
				</tr>
				<tr>
					<td>起標時間</td>
					<td><input name="bidLaunchedTime" id="bidLaunchedTime"
						type="text" value="${bidProductVO.bidLaunchedTime}"></td>
				</tr>
				<tr>
					<td>截標時間</td>
					<td><input name="bidSoldTime" id="bidSoldTime" type="text"
						value="${bidProductVO.bidSoldTime}"></td>
				</tr>
				<tr>
					<td>得標價</td>
					<td>${bidProductVO.bidWinnerPrice}</td>
				</tr>
				<tr>
					<td>得標會員</td>
					<td>${bidProductVO.getMemVOByBuyerNo().memName}</td>
				</tr>
				<tr>
					<td>競標狀態</td>
					<td>
						<select size="1" name="bidState">
							<option value="0"
								<c:if test="${bidProductVO.bidState == 0}"><c:out value="selected"></c:out></c:if>>競標中</option>
							<option value="1"
								<c:if test="${bidProductVO.bidState == 1}"><c:out value="selected"></c:out></c:if>>截標</option>
							<option value="2"
								<c:if test="${bidProductVO.bidState == 2}"><c:out value="selected"></c:out></c:if>>流標</option>
							<option value="3"
								<c:if test="${bidProductVO.bidState == 3}"><c:out value="selected"></c:out></c:if>>棄標</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>收件人姓名</td>
					<td><input type="text" name="receiverName" size="45"
						value="${bidProductVO.receiverName}" /></td>
				</tr>
				<tr>
					<td>收件人地址</td>
					<td><input type="text" name="receiverAddress" size="45"
						value="${bidProductVO.receiverAddress}" /></td>
				</tr>
				<tr>
					<td>收件人電話</td>
					<td><input type="text" name="receiverPhone" size="45"
						value="${bidProductVO.receiverPhone}" /></td>
				</tr>
				<tr>
					<td>商品狀態</td>
					<td>
						<select size="1" name="orderState">
							<option value="0"
								<c:if test="${bidProductVO.orderState == 0}"><c:out value="selected"></c:out></c:if>>未出貨</option>
							<option value="1"
								<c:if test="${bidProductVO.orderState == 1}"><c:out value="selected"></c:out></c:if>>訂單處理中</option>
							<option value="2"
								<c:if test="${bidProductVO.orderState == 2}"><c:out value="selected"></c:out></c:if>>已出貨</option>
							<option value="3"
								<c:if test="${bidProductVO.orderState == 3}"><c:out value="selected"></c:out></c:if>>取回處理中</option>
							<option value="4"
								<c:if test="${bidProductVO.orderState == 4}"><c:out value="selected"></c:out></c:if>>已重新申請上架</option>
							<option value="5"
								<c:if test="${bidProductVO.orderState == 5}"><c:out value="selected"></c:out></c:if>>已收貨</option>
							<option value="6"
								<c:if test="${bidProductVO.orderState == 6}"><c:out value="selected"></c:out></c:if>>已撥款</option>
						</select>
					</td>
				</tr>

			</table>
			<input type="hidden" name="bidProductNo"value="${bidProductVO.bidProductNo}">
			<input type="hidden"name="bidApplyListNo" value="${bidProductVO.bidApplyListNo}">
			<input type="hidden" name="sellerNo"value="${bidProductVO.sellerNo}">
			<input class="btn btn-primary" type="submit" value="修改">
			<input class="btn btn-primary" type="reset" value="重設">

		</form>
		
<!-- 		圖片顯示區及刪除 -->

	<div id="delete-form" style="position: relative; left: 480px ;bottom: 750px" >
		<form method="post" ACTION="<%=request.getContextPath()%>/bid/bidPicDelete" onsubmit="return checkConfirm();" style="display: flex;align-items: center">
			<br>
				<c:if test="${list.size() != 0}">
				<c:forEach var="bidPicVO" items="${list}">
					<img src="<%=request.getContextPath()%>/bid/bidPicGetOneByProdPicNo?bidProdPicNo=${bidPicVO.bidProdPicNo}" height="128px" width="128px" class="uploadedImg">
					<input type="hidden" name="bidProdPicNo" value="${bidPicVO.bidProdPicNo}">
					<input class="form-check-input" style="width:15px;height:15px;" type="checkbox" name="bidProdPicNos" value="${bidPicVO.bidProdPicNo}" class="delete_checkbox">
				</c:forEach>
		
					<input type="hidden" name="bidProductNo"value="${bidProductVO.bidProductNo}">
					<input type="hidden" name="bidApplyListNo"value="${bidProductVO.bidApplyListNo}">
					<input type="hidden" name="productNo"value="${bidProductVO.productNo}">
					<input type="hidden" name="bidName"value="${bidProductVO.bidName}">
					<input type="hidden" name="bidProdDescription"value="${bidProductVO.bidProdDescription}">
					<input type="hidden" name="sellerNo"value="${bidProductVO.sellerNo}">
					<input type="hidden" name="initialPrice"value="${bidProductVO.initialPrice}">
					<input type="hidden" name="bidPriceIncrement"value="${bidProductVO.bidPriceIncrement}">
					<input type="hidden" name="bidLaunchedTime"value="${bidProductVO.bidLaunchedTime}">
					<input type="hidden" name="bidSoldTime"value="${bidProductVO.bidSoldTime}">
					<input type="hidden" name="bidWinnerPrice"value="${bidProductVO.bidWinnerPrice}">
					<input type="hidden" name="buyerNo"value="${bidProductVO.buyerNo}">
					<input type="hidden" name="bidState"value="${bidProductVO.bidState}">
					<input type="hidden" name="orderState"value="${bidProductVO.orderState}">
					<input type="hidden"name="receiverName" value="${bidProductVO.receiverName}">
					<input type="hidden" name="receiverAddress"value="${bidProductVO.receiverAddress}">
					<input type="hidden" name="receiverPhone"value="${bidProductVO.receiverPhone}">
					<input class="btn btn-primary" type="submit" style="margin-left: 10px;" value="刪除圖片">				
				</c:if>
			<br>
		</FORM>
	</div>

<!-- 	上傳圖片區 -->
	<div style="position: relative; left: 490px ;bottom: 700px">
		<form id="upload" action="<%=request.getContextPath()%>/bid/bidPicInsertMulti" method="POST" enctype="multipart/form-data" name="form2" onsubmit="return ">
		<a href="javascript:;" class="file">選擇圖片
	        <input type="file" name="upfile1" multiple id="upfile">
	        </a>
  					<input type="hidden" name="bidProductNo"value="${bidProductVO.bidProductNo}">
					<input type="hidden" name="bidApplyListNo"value="${bidProductVO.bidApplyListNo}">
					<input type="hidden" name="productNo"value="${bidProductVO.productNo}">
					<input type="hidden" name="bidName"value="${bidProductVO.bidName}">
					<input type="hidden" name="bidProdDescription"value="${bidProductVO.bidProdDescription}">
					<input type="hidden" name="sellerNo"value="${bidProductVO.sellerNo}">
					<input type="hidden" name="initialPrice"value="${bidProductVO.initialPrice}">
					<input type="hidden" name="bidPriceIncrement"value="${bidProductVO.bidPriceIncrement}">
					<input type="hidden" name="bidLaunchedTime"value="${bidProductVO.bidLaunchedTime}">
					<input type="hidden" name="bidSoldTime"value="${bidProductVO.bidSoldTime}">
					<input type="hidden" name="bidWinnerPrice"value="${bidProductVO.bidWinnerPrice}">
					<input type="hidden" name="buyerNo"value="${bidProductVO.buyerNo}">
					<input type="hidden" name="bidState"value="${bidProductVO.bidState}">
					<input type="hidden" name="orderState"value="${bidProductVO.orderState}">
					<input type="hidden"name="receiverName" value="${bidProductVO.receiverName}">
					<input type="hidden" name="receiverAddress"value="${bidProductVO.receiverAddress}">
					<input type="hidden" name="receiverPhone"value="${bidProductVO.receiverPhone}">
					<input class="btn btn-primary" type="submit" value="上傳圖片" class="button" style="margin: 0 0 27px 10px">
		</form>
		<div id="picPreview" style="position: absolute ;top: 80px ; display: flex; flex-wrap: wrap; width: 450px "></div>
	</div>
</div>

	</section>

	<!--main content end-->

</section>
	<!-- 為了要去除下面從資料庫取 timestamp 資料會有 nano 小數點的問題 -->

	
	<script type="text/javascript">
		$.datetimepicker.setLocale("zh");
		$("#bidLaunchedTime").datetimepicker({
			theme : '', // theme: 'dark', 
			timepicker : true, // timepicker:true,
			step : 1, //step: 60 設定時間時分的間隔
			format : 'Y-m-d H:i:s', //format:'Y-m-d H:i:s',
			value : '${bidProductVO.bidLaunchedTime}', // value: new Date(), 會帶入現在時間
		});
		$.datetimepicker.setLocale("zh");
		$("#bidSoldTime").datetimepicker({
			theme : '', // theme: 'dark', 
			timepicker : true, // timepicker:true,
			step : 1, //step: 60 設定時間時分的間隔
			format : 'Y-m-d H:i:s', //format:'Y-m-d H:i:s',
			value : '${bidProductVO.bidSoldTime}'
		// value:   new Date(), 會帶入現在時間
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
		
		
		var filereader_support = typeof FileReader != 'undefined';

		if (!filereader_support) {
			alert("No FileReader support");
		}

		acceptedTypes = {
				'image/png' : true,
				'image/jpeg' : true,
				'image/gif' : true
		};
		

		let upfile = document.getElementById("upfile");
		upfile.addEventListener("change", function(event) {
			let files = event.target.files || event.dataTransfer.files;
			for (let i = 0; i < files.length; i++) {
				previewfile(files[i])
			}
		}, false);
		
		function previewfile(file) {
			if (filereader_support === true && acceptedTypes[file.type] === true) {
				let reader = new FileReader();
				reader.onload = function(event) {
					let image = new Image();
					image.src = event.target.result;
					image.width = 128;
					image.classList.add("imgCss");
					picPreview.appendChild(image);
				};
				reader.readAsDataURL(file);
			} else {
				picPreview.innerHTML += "<p>" + "filename: <strong>" + file.name
						+ "</strong><br>" + "ContentTyp: <strong>" + file.type
						+ "</strong><br>" + "size: <strong>" + file.size
						+ "</strong> bytes</p>";
			}
		}
		// 當upload重新選擇 清空舊有資料
		$("#upload").change(function(){
		    $("#picPreview").empty() // 清空當下預覽
		    previewfile(this.files) // this即為<input>元素
		})

	</script>
</body>
</html>
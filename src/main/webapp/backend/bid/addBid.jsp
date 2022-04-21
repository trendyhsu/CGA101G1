<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.bidapplylist.model.BidApplyListVO"%>
<%@page import="com.bidapplylist.model.BidApplyListService"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.bidpic.model.BidPicVO"%>
<%@page import="com.bidpic.model.BidPicService"%>
<%@ page import="com.bidproduct.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>

<%
Integer bidApplyListNo = Integer.parseInt(request.getParameter("bidApplyListNo").trim());
BidApplyListService bidApplyListSvc = new BidApplyListService();
BidApplyListVO bidApplyListVO = bidApplyListSvc.getOneBidApplyList(bidApplyListNo);
pageContext.setAttribute("bidApplyListVO", bidApplyListVO);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>二手遊戲驗收管理</title>

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
</style>

</head>
<body>


	<div id="bid-content"
		style="position: absolute; left: 230px; top: 80px">

		<table id="table-1">
			<tr>
				<td>
			 		<h3>二手遊戲驗收管理</h3>
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
		
<!-- 		主要修改資訊區 -->

		<form method="post"
			action="<%=request.getContextPath()%>/bid/bidProductInsert"
			name="form1" enctype="multipart/form-data">
			<table>
				<tr>
					<td>申請單編號</td>
					<td><input type="number" name="bidApplyListNo" size="45"
						value="${bidApplyListVO.bidApplyListNo}"></td>
				<tr>
					<td>一般商品編號</td>
					<td><input type="text" name="productNo" size="45"
						value="<%=2>1 ? "21001" : "21002"%>" /></td>
				</tr>
				<tr>
					<td>商品名稱</td>
					<td><input type="text" name="bidName" size="45"
						value="${bidApplyListVO.bidName}" /></td>
				</tr>
				<tr>
					<td>商品敘述</td>
					<td><textArea name="bidProdDescription"
							id="bidProdDescription" rows="6" cols="45" style="resize:none;">${bidApplyListVO.bidProdDescription}</textArea></td>
				</tr>
				<tr>
					<td>賣家編號</td>
					<td><input type="number" name="sellerNo" size="45"
						value="${bidApplyListVO.memNo}"></td>
				</tr>
				<tr>
					<td>起標價</td>
					<td><input type="number" name="initialPrice" size="45" min="0"
						value="${bidApplyListVO.initialPrice}" /></td>
				</tr>
				<tr>
					<td>最低增額</td>
					<td><input type="number" name="bidPriceIncrement" size="45"
						min="0" value="${bidApplyListVO.bidPriceIncrement}" /></td>
				</tr>
				<tr>
					<td>起標時間</td>
					<td><input name="bidLaunchedTime" id="bidLaunchedTime"
						type="text" value="${bidApplyListVO.bidLaunchedTime}"></td>
				</tr>
				<tr>
					<td>截標時間</td>
					<td><input name="bidSoldTime" id="bidSoldTime" type="text"
						value="${bidApplyListVO.bidSoldTime}"></td>
				</tr>
<!-- 	上傳圖片區 -->
				<tr style="position: absolute; left: 480px;top: 36px">
					<td>
	        		<input type="file" name="upfile1" onclick="previewImage()" multiple id="upfile">
					</td>
				</tr>
				<tr>
					<td>
			<input type="submit" value="新增">
			<input type="reset" value="重設">
					</td>
				</tr>
			</table>
		</form>
<div id="picPreview" style="position: absolute; top: 90px ; left:480px;display: flex; width: 550px ;flex-wrap:wrap;"></div>

</div>



	<!-- 為了要去除下面從資料庫取 timestamp 資料會有 nano 小數點的問題 -->
<%
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String bidLaunchedTime = df.format(bidApplyListVO.getBidLaunchedTime());
	String bidSoldTime = df.format(bidApplyListVO.getBidSoldTime());
%>


	<script type="text/javascript">
		$.datetimepicker.setLocale("zh");
		$("#bidLaunchedTime").datetimepicker({
			theme : '', // theme: 'dark', 
			timepicker : true, // timepicker:true,
			step : 1, //step: 60 設定時間時分的間隔
			format : 'Y-m-d H:i:s', //format:'Y-m-d H:i:s',
			value : '<%=bidLaunchedTime%>' // value: new Date(), 會帶入現在時間
		});
		$.datetimepicker.setLocale("zh");
		$("#bidSoldTime").datetimepicker({
			theme : '', // theme: 'dark', 
			timepicker : true, // timepicker:true,
			step : 1, //step: 60 設定時間時分的間隔
			format : 'Y-m-d H:i:s', //format:'Y-m-d H:i:s',
			value : '<%=bidSoldTime%>'
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
		
		
		function previewImage() {
			var upfile = document.getElementById("upfile");
			upfile.addEventListener("change", function(event) {
				var files = event.target.files || event.dataTransfer.files;
				for (var i = 0; i < files.length; i++) {
					previewfile(files[i])
				}
			}, false);
		}
		
		function previewfile(file) {
			if (filereader_support === true && acceptedTypes[file.type] === true) {
				var reader = new FileReader();
				reader.onload = function(event) {
					var image = new Image();
					image.src = event.target.result;
					image.width = 128;
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
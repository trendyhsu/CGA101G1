<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.manager.model.*"%>
<%@include file="/backend/share.jsp"%>
<%@page import="com.manager.model.ManagerVO"%>
<%@page import="com.manager.model.ManagerService"%>


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
padding: 10px
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
			 		<h3>修改管理員資料</h3>
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

		<form method="post" action="<%=request.getContextPath()%>/manager/managerEditUpdate"
			name="form1" enctype="multipart/form-data">
			<table>
				<tr>
					<td>管理員編號</td>
					<td>${managerVO.managerNo}</td>
				</tr>
				<tr>
					<td>管理員帳號</td>
					<td>${managerVO.managerAccount}</td>
				<tr>
<jsp:useBean id="managerSvc" scope="page" class="com.manager.model.ManagerService" />
					<td>管理員密碼</td>
					<td>
				       <input type="text" name="managerPassword" value="${managerVO.managerPassword}">
					</td>
				</tr>
				<tr>
					<td>管理員名稱</td>
					<td><input type="text" name="managerName" size="45"
						value="${managerVO.managerName}" /></td>
				</tr>
				<tr>
					<td>管理員手機</td>
					<td><input type="text" name="managerPhone" size="45"
						value="${managerVO.managerPhone}" /></td>
				</tr>
				<tr>
					<td>狀態</td>
					<td>
						<select size="1" name="managerState">
							<option value="0"
								<c:if test="${managerVO.managerState == 0}"><c:out value="selected"></c:out></c:if>>在職</option>
							<option value="1"
								<c:if test="${bidProductVO.bidState == 1}"><c:out value="selected"></c:out></c:if>>離職</option>
						</select>
					</td>
				</tr>
				<td>管理員大頭照</td>

							<td id="upload"><input type="file" name="upfile1"
								onclick="previewImage()" multiple id="upfile"></td>
						</tr>
					</table>


				<div id="picPreview"
					style="display: flex; width: 100%; height: 100%; flex-wrap: wrap; position: relative;">

					<img
						src="<%=request.getContextPath()%>/manager/managerPic?managerNo=${managerVO.managerNo}"
						class="uploadedImg" style="width: 300px; padding-left: 0px">
				</div>
				
			</table>
			<input type="hidden" name="managerNo"value="${managerVO.managerNo}">
			<input type="hidden" name="managerAccount"value="${managerVO.managerAccount}">
			<input type="hidden" name="managerState"value="${managerVO.managerState}">
			<input class="btn btn-primary" type="submit" value="修改" style="margin-top:10px;" >
			<input class="btn btn-primary" type="reset" value="重設" style="margin-top:10px;">

		</form>
		
	</div>
</div>

	</section>

	<!--main content end-->

</section>

	<script type="text/javascript">
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
					image.width = 300;
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
<%@page import="java.util.List"%>
<%@page import="com.managerauthrizationfunction.model.ManagerAuthrizationFunctionService"%>
<%@page import="com.managerauth.model.ManagerAuthService"%>
<%@page import="com.managerauthrizationfunction.model.ManagerAuthrizationFunctionVO"%>
<%@page import="com.managerauth.model.ManagerAuthVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/backend/share.jsp"%>


<%
Integer managerNo = Integer.valueOf(request.getParameter("managerNo"));

ManagerAuthrizationFunctionService managerAuthrizationFunctionService = new ManagerAuthrizationFunctionService();
List<ManagerAuthrizationFunctionVO> list = managerAuthrizationFunctionService.getAll();
pageContext.setAttribute("list", list);

// ManagerAuthService managerAuthService = new ManagerAuthService();
// List<ManagerAuthVO> list2 = managerAuthService.getFunction(managerNo);
// pageContext.setAttribute("list2", list2);

// List<Integer> lists3 = ;
// pageContext.setAttribute("list2", list2);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>權限管理</title>

<style>
table {
	border-collapse: collapse;
	
}

th {
	font-size: larger;
	margin: 1em auto;
}

th, td {
	padding: .65em;
}

th {
	background: rgb(84,117,147);
	color: #fff;
	width:500px;
}

th:first-child {
	border-radius: 9px 0 0 0;
}

th:last-child {
	border-radius: 0 9px 0 0;
}

tr:last-child td:first-child {
	border-radius: 0 0 0 9px;
}

tr:last-child td:last-child {
	border-radius: 0 0 9px 0;
}

tbody tr:hover {
	background: linear-gradient(#E0E7E9, #B2CDCC);
}

#checkbox1 {
	width:20px;
	height:20px;
	margin-top:30px;
	margin-right:300px;
}

#button1, #button2 {
	width:80px;
	height:30px;
}

input {
	background-color: #E0E7E9;
	border-radius: 5px;
}

input:active {
	background-color: #fff;
}

.button1 {
	display: inline-block;
	padding: 3px 7px;
	font-size: 10px;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	outline: none;
	color: #fff;
	background-color: #547492;
	border: none;
	border-radius: 7px;
}

.button1:hover {
	background-color: #A3C6C4
}

.button1:active {
	background-color: #E0E7E9;
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
							<h3>權限管理</h3>
							<%=managerNo %>
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
				<div class="">
					<form method="post"
						action="<%=request.getContextPath()%>/managerAuth/managerAuthCR"
						name="form1">
						<table class="table table-striped">
						
						<thead>
							<tr>
								<th><i class="fa-solid fa-align-left"></i> 權限種類</th>
								<th><i class="fa-solid fa-check"></i> 新增權限</th>
							</tr>
						</thead>
						
<jsp:useBean id="managerAuthSvc" scope="page" class="com.managerauthrizationfunction.model.ManagerAuthrizationFunctionService" />
							<tr>
							
								<c:forEach var="map" items="${map}">
								<tr>
								<td class="hidden-phone" style="line-height:100px;">
									${managerAuthSvc.getOneManagerAuthrizationFunction(map.key).managerAuthrizationFunction}
								</td>
								<td>
    								<input id="checkbox1" type="checkbox" class="cktoggle_checkbox" name="authName" value="${map.key}" ${(map.value == 1) ? "checked" : ""}>
								</td>
								</tr>
								</c:forEach>
							
							</tr>

							<tr>
								<td>
							<input type="hidden" name="managerNo" value="<%=managerNo%>">
								<input id="button1" type="submit" value="新增"> <input
									id="button2" type="reset" value="重設"></td>
							</tr>
							<!-- 	上傳圖片區 
				<tr>
					<td>
	        		<input type="file" name="upfile1" onclick="previewImage()" multiple id="upfile"
	        		style="position: relative; left:480px ;bottom: 530px;">
					</td>
				</tr> -->
						</table>
					</form>
					<div id="picPreview"
						style="display: flex; width: 400px; flex-wrap: wrap; position: relative; left: 480px; bottom: 530px"></div>
				</div>
			</div>

		</section>

		<!--main content end-->

	</section>
	<!-- 為了要去除下面從資料庫取 timestamp 資料會有 nano 小數點的問題 -->



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
			if (filereader_support === true
					&& acceptedTypes[file.type] === true) {
				var reader = new FileReader();
				reader.onload = function(event) {
					var image = new Image();
					image.src = event.target.result;
					image.width = 128;
					picPreview.appendChild(image);
				};
				reader.readAsDataURL(file);
			} else {
				picPreview.innerHTML += "<p>" + "filename: <strong>"
						+ file.name + "</strong><br>" + "ContentTyp: <strong>"
						+ file.type + "</strong><br>" + "size: <strong>"
						+ file.size + "</strong> bytes</p>";
			}
		}
		// 當upload重新選擇 清空舊有資料
		$("#upload").change(function() {
			$("#picPreview").empty() // 清空當下預覽
			previewfile(this.files) // this即為<input>元素
		})
	</script>
</body>
</html>
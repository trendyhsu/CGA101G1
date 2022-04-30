<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/backend/share.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">

<title>POP.Game ServerSide</title>

<link
	href="<%=request.getContextPath()%>/backend/news/assets/game-news.css"
	rel="stylesheet">

</head>

<body>
		<!-- **********************************************************************************************************************************************************
      MAIN CONTENT 內容寫在這裡面
      *********************************************************************************************************************************************************** -->
		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
				<h3 class="funcTitle02">
					<i class="fa-solid fa-gear"></i> 新聞修改
				</h3>
				<div class="row mt">
					<jsp:useBean id="mngSvc" scope="page" class="com.manager.model.ManagerService" />	
					<jsp:useBean id="gpftSvc" scope="page" class="com.gameplatformtype.model.GamePlatformTypeService" />	
					<form action="<%=request.getContextPath()%>/gamenews/gamenews.do"
						method="post" class="formQuery" enctype="multipart/form-data">

						<ul>
							<li><label style="text-decoration: line-through;">新聞編號</label></li>
							<li><input type="text" disabled value="${gameNewsVO.gameNewsNo}"></li>
							<li><label>遊戲平台</label></li>
							 <li><select size="1" name="gamePlatformNo">
							 		<option value="">請選擇
                                    <c:forEach var="platformVO" items="${gpftSvc.all}">
                                    	<option value="${platformVO.gamePlatformNo}" ${(gameNewsVO.gamePlatformNo == platformVO.gamePlatformNo)? 'selected':'' } >${platformVO.gamePlatformName}
                                    </c:forEach>
                                </select></li>
							<li><label>編輯者</label></li>
							 <li><select size="1" name="managerNo">
							 		<option value="">請選擇
                                    <c:forEach var="managerVO" items="${mngSvc.all}">
                                    	<option value="${managerVO.managerNo}" ${(gameNewsVO.managerNo == managerVO.managerNo)? 'selected':'' } >${managerVO.managerName}
                                    </c:forEach>
                                </select></li>
							<li><label>新聞標題</label></li>
							<li><input type="text" name="gameNewsTitle" value="${gameNewsVO.gameNewsTitle}"></li>
							<li> <label>新聞封面照片</label></li>
                            <li>
                            <img class="showPic" src="<%=request.getContextPath()%>/gameNews/gameNewsPic?gameNewsNo=${gameNewsVO.gameNewsNo}" >
                            </li>
                            <li></li>
                            <li><input type="file" accept="image/jpeg" name="gameNewsPic" onclick="previewImage()" id="upfile"></li>
							<li><label>新聞內容</label></li>
							<li><textarea name="gameNewsContent" id="" cols="30"
									rows="10">${gameNewsVO.gameNewsContent}</textarea></li>
						
						</ul>
						
						<input type="hidden" name="gameNewsNo" value="${gameNewsVO.gameNewsNo}">
						<input type="hidden" name="requestURL" value="<%=request.getRequestURI()%>">
						<button type="submit" class="cusBtn">
							<i class="fa-solid fa-pencil"></i>修改
						</button>
						<input type="hidden" name="action" value="update">
					</form>
				
				</div>
			</section>
		</section>

		<!--main content end-->



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
			console.log("qqq");
			previewfile(files[i])
		}
	}, false);
}

function previewfile(file) {
	if (filereader_support === true && acceptedTypes[file.type] === true) {
		var reader = new FileReader();
		reader.onload = function(event) {
			var image = document.querySelector(".showPic");
			image.src = event.target.result;		
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
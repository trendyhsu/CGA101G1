<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum.model.*"%>
<%@include file="/backend/share.jsp"%>

<%
ForumVO forumVO = (ForumVO) request.getAttribute("forumVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增討論區</title>

<style>
table {
	/* 	background-color: white; */
	margin-bottom: 5px;
	font-size: 13px;
	color: black;
}

table, th, td {
	border-bottom: 1px solid #CCC;
	font-weight: bold;
}

th, td {
	padding: 5px;
	text-align: left;
}

.uploadedImg {
	padding: 10px
}

h3 {
	font-weight: bold;
	color: #547492;
}
</style>

</head>
<body>
	<section id="main-content">
		<section class="wrapper">


			<div id="forum-content">

				<table id="table-1">
					<tr>
						<td>
							<h3>新增討論區</h3>
						</td>
					</tr>
				</table>

				<!-- Main -->

				<form method="post"
					action="<%=request.getContextPath()%>/forum/forumInsert"
					name="form1" enctype="multipart/form-data">
					<table>
						<tr>
							<td>討論區名稱</td>
							<td><input type="TEXT" name="forumName" size="45"
								value="${forumVO.forumName}" /></td>
							<td>${errorMsgs.forumName}</td>
						</tr>
						<tr>
							<td>討論區狀態</td>
							<td><select size="1" name="forumType">
									<option value="1"
										<c:if test="${forumVO.forumType == 1}"><c:out value="selected"></c:out></c:if>>1顯示</option>
									<option value="0"
										<c:if test="${forumVO.forumType == 0}"><c:out value="selected"></c:out></c:if>>0不顯示</option>
							</select></td>
						</tr>
						<tr>
							<jsp:useBean id="memJDBCDAO" scope="page"
								class="com.member.model.MemJDBCDAO" />
							<td>版主</td>
							<td><select size="1" name="memNo">
									<option value="0"
										${(memVO.memNo != forumVO.memNo) ? "selected" : ""}>無版主
										<c:forEach var="memVO" items="${memJDBCDAO.getAll()}">
											<option value="${memVO.memNo}"
												${(forumVO.memNo==memVO.memNo) ? "selected" : ""}>
												${memVO.memName}
										</c:forEach>
							</select></td>
						</tr>
						<tr>

							<!-- 上傳圖片區 -->
							<td>討論區圖片</td>

							<td><input type="file" name="upfile1"
								onclick="previewImage()" multiple id="upfile"></td>
						</tr>
					</table>
					<input type="submit" value="新增"> <input type="reset"
						value="重設">
				</form>
				<div style="height: 15px"></div>

				<div style="display: inline-block;">
					<a
						href="<%=request.getContextPath()%>/backend/forum/listAllForum.jsp">
						<button>返回討論區列表</button>
					</a>
				</div>

				<div style="height: 15px"></div>
				
				<div id="picPreview"
					style="display: flex; width: 100%; height: 100%; flex-wrap: wrap; position: relative; left: 0 px; bottom: 0px"></div>
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
					image.width = 450;
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

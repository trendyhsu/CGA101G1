<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forum.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>討論區檢舉管理</title>

<style type="text/css">
th {
	border-bottom-style: solid;
	background-color: #b2cdcc;
}

td {
	border-bottom-style: solid;
}

h3 {
	font-weight: bold;
	color: #547492;
}
</style>

</head>
<body>



	<div id="forum-content"
		style="position: absolute; left: 230px; top: 80px; width: 80%">

		<table id="table-1">
			<tr>

				<h3>討論區檢舉管理</h3>

			</tr>
		</table>
		<div>
			<a href="listAllForumPost.jsp">
				<button>文章列表</button>
			</a> <a href="listAllForumPostReport.jsp">
				<button>文章檢舉列表</button>
			</a> <a href="listAllForumMsg.jsp">
				<button>留言列表</button>
			</a><a href="listAllForumMsgReport.jsp">
				<button>留言檢舉列表</button>
			</a> <a href="listAllBadMemNo.jsp">
				<button>禁言會員列表</button>
			</a>
		</div>
	</div>
</body>
</html>
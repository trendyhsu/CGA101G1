<%@page import="com.gameplatformtype.model.GamePlatformTypeVO"%>
<%@page import="com.gameplatformtype.model.GamePlatformTypeService"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/backend/share.jsp"%>

<%

GamePlatformTypeService gamePlatformTypeSer = new GamePlatformTypeService();
List<GamePlatformTypeVO> list = gamePlatformTypeSer.getAll();
pageContext.setAttribute("list", list);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>遊戲種類</title>

<style type="text/css">
th{
	border-bottom-style:solid;
	background-color: #b2cdcc;
}
td{
	border-bottom-style:solid;
}
h3{
font-weight: bold;
color: #547492;
}
</style>

</head>
<body>
<!--main content start-->
<section id="main-content">
	<section class="wrapper">

	<div id="bid-content" >
		
		<table id="table-1">
			<tr>

			 		<h3>遊戲平台及種類管理</h3>

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
		<%-- 成功表列 --%>
			<p style="color: red;">${successMsg}</p>
		<div class="showback">
	
			<h4><i class="fa fa-angle-right"></i>遊戲平台種類</h4>
			<%@ include file="page1.file"%>
		 	<c:forEach var="gamePlatformTypeVO" items="${list}" begin="<%=pageIndex%>"  
		  		end="<%=pageIndex+rowsPerPage-1%>">  

			<span class="label label-primary" style="margin:1.5px; padding:10px; font-size:15px;">
			${gamePlatformTypeVO.gamePlatformName}</span>
			<!-- 	<tr>
				
					<td>${gameTypeVO.gameTypeNo}</td>
					<td>${gameTypeVO.gameTypeName}</td>
					
				</tr>
				-->
				</c:forEach>
				<div style="margin-top:30px;">
			<td sytle="">
				<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/backend/gameplatformtype/addGamePlatformType.jsp"
				style="margin-bottom: 0px;display:inline;">
				<input type="submit" value="新增" class="btn btn-success btn-beside">
				<input type="hidden"name="gamePlatformNo" value="${gamePlatformTypeVO.gamePlatformNo}">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post"
					  ACTION="<%=request.getContextPath()%>/backend/gameplatformtype/editGamePlatformType.jsp"
				      style="margin-bottom: 0px;display:inline;">
					<input type="submit" value="修改" class="btn btn-success btn-beside">
					<input type="hidden" name="gamePlatformNo" value="${gamePlatformTypeVO.gamePlatformNo}">
				</FORM>
			</td>	
				<div>
					<%@ include file="page2.file"%>
					</div>
		
		</div>
		
	</div>
            </section>
            <!--/wrapper -->
        </section><!-- /MAIN CONTENT -->

        <!--main content end-->
    </section>
</body>
</html>
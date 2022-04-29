<%@page import="com.gamenews.model.GameNewsVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/backend/share.jsp"%>
<%@page import="java.util.*"%>
<%@page import="com.gamenews.model.*" %>
<%
	GameNewsService gnSvc = new GameNewsService();
	List<GameNewsVO> list = gnSvc.getAll();
	pageContext.setAttribute("list", list);
	
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">

<link
	href="<%=request.getContextPath()%>/backend/news/assets/game-news.css"
	rel="stylesheet">


<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>

<body>
		<!-- **********************************************************************************************************************************************************
      MAIN CONTENT 內容寫在這裡面
      *********************************************************************************************************************************************************** -->
		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
				<h3 class="funcTitle02">
					<i class="fa-solid fa-gear"></i> 新聞管理
				</h3>

				<div class="row">
					<div class="col-md-12">
						<div class="content-panel">
							<table class="table table-striped table-advance table-hover"
								id="myTable">
								<h4 class="funcTitle02">
									<i class="fa fa-angle-right"></i> 查詢結果 
								</h4>
								<%@ include file="page1.file"%>
								<hr>
								
								<thead>
                                    <tr>
                                        <th><i class="fa fa-solid fa-hashtag"></i> 新聞編號</th>
                                        <th><i class="fa fa-solid fa-hashtag"></i> 平台編號
                                        </th>
                                        <th><i class="fa fa-solid fa-hashtag"></i> 編輯者編號
                                        </th>
                                        <th class="hidden-phone"><i class="fa fa-question-circle"></i> 新聞標題</th>
                                        <th><i class="fa fa-bookmark"></i> 新聞內容</th>
                                        <th class="hidden-phone"><i class="fa-solid fa-image"></i>新聞首圖</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                
                             
                                <c:forEach var="gameNews" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                                    <tr>
                                    
				
											<td>${gameNews.gameNewsNo}</td>
											<td>${gameNews.gamePlatformNo}</td>
											<td>${gameNews.managerNo}</td>
											<td>${gameNews.gameNewsTitle}</td>
											<td>${gameNews.gameNewsContent}</td>
											<td>
												<c:if test="${gameNews.gameNewsPic!=null}">
													<a href="<%=request.getContextPath()%>/gameNews/gameNewsPic?gameNewsNo=${gameNews.gameNewsNo}" title="點擊查看" target="_blank">查看</a>
												</c:if>
											</td>
											<td>
											<form method="post" action="<%=request.getContextPath()%>/gamenews/gamenews.do">
													<button class="btn btn-primary btn-xs edit">
														<i class="fa fa-pencil"></i>
													</button>
													<input type="hidden" name="gameNewsNo" value="${gameNews.gameNewsNo}">
													<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> <!--送出本網頁的路徑給Controller-->
													<input type="hidden" name="action" value="get_one_to_update"> 
												</form>
												
												<form method="post" action="<%=request.getContextPath()%>/gamenews/gamenews.do">
													<button class="btn btn-danger btn-xs" type="submit">
														<i class="fa fa-trash-o "></i>
													</button>
													<input type="hidden" name="gameNewsNo" value="${gameNews.gameNewsNo}">
													<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> <!--送出本網頁的路徑給Controller-->
													<input type="hidden" name="action" value="delete"> 
												</form>

											</td>
											
                                    </tr>
                                    </c:forEach>
                                  
                                </tbody>
                               
							</table>
							  <%@ include file="page2.file"%>
						</div>
						 
						<!-- /content-panel -->
					</div>
					<!-- /col-md-12 -->
				</div>
				<!-- /row -->
			</section>
		</section>

		<!--main content end-->

</body>

</html>
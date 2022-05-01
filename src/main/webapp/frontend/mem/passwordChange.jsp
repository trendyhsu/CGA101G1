<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@include file="/frontend/fronthead.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>

 <!-- End Profile Menu -->
                    <!-- Content 主要內容區 要修改的部分都塞在這個裡面 -->
					<!-- 內容直接寫在<div class="table-responsive fs-md mb-4">裡面 -->
                    <div class="col-lg-9 col-xxl-9">
                        <div class="table-responsive fs-md mb-4">
                        
<div id=passwordChange>


<!-- login page -->
        <div class="section">
            <div class="container">
                <div class="justify-content-center row">
                    <div class="col-lg-5 col-xxl-4">
                        <div class="card">
                            <div class="card-header bg-transparent py-3">
                                <h3 class="h4 mb-0" style="text-align: center;  color: cadetblue;">修改密碼</h3>
                            </div>
                            <div class="card-body">


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/MemPasswordEditServlet" name="form1">

 <div>
 		<div class="form-group mb-3">
             <div class="row align-items-center">
                <label class="form-label col" for="oldPassword">舊密碼:
                <span class="text-danger">*</span></label>
                  </div>
					<input type="password" class="form-control" id="oldPassword" placeholder="輸入舊密碼"
                         style="text-transform: none;" name="oldPassword"> 
                  </div>
					<label style="color: red; font-size: 15;">${errorMsgs.oldPassword}</label>
					
		<div class="form-group mb-3">
             <div class="row align-items-center">
                <label class="form-label col" for="newPassword">新密碼:
                <span class="text-danger">*</span></label>
                  </div>
					<input type="password" class="form-control" id="newPassword" placeholder="輸入新密碼"
                         style="text-transform: none;" name="newPassword"> 
                  </div>
					<label style="color: red; font-size: 15;">${errorMsgs.newPassword}</label>                  

		<div class="form-group mb-3">
             <div class="row align-items-center">
                <label class="form-label col" for="conNewPassword">確認新密碼:
                <span class="text-danger">*</span></label>
                  </div>
					<input type="password" class="form-control" id="conNewPassword" placeholder="輸入新密碼"
                         style="text-transform: none;" name="conNewPassword"> 
                  </div>
					<label style="color: red; font-size: 15;">${errorMsgs.conNewPassword}</label>
     
        <div id="errorMsgs" class="error"></div>
 </div>
    

	  <div class="form-group text-center">
		<input type="hidden" name="action" value="update">
		<input type="submit" class="btn btn-primary w-100" id="btn1" value="送出"></FORM>
	 </div>
	 	<label>${errorMsgs.memVO}</label>

</div>

                     </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end login -->


    </div>
					</div>
                    <!-- End Content -->
</body>
</html>
<%@include file="/frontend/frontfoot.jsp" %>
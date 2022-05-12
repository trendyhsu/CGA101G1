<%@page import="com.manager.model.ManagerVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>POP.Game 後臺管理系統</title>

    <!-- font awesome cdn link -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/assets/weiwei/css/allStyle.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/assets/weiwei/css/headerFunctionStyle.css">
    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/assets/weiwei/css/loginStyle.css">
</head>

<body>


    <!--Login starts-->

    <section id="login-form">


        <div class="form-box">
            <div class="button-box">
                <div id="btn"></div>
                <button type="button" class="toggle-btn" onclick="login()">login</button>
                <button type="button" class="toggle-btn" onclick="register()">register</button>
            </div>

            <div id="login" class="input-group">
                <div class="input-div">
                    <div class="icon-div"><i class="fas fa-solid fa-user-astronaut"></i></div>
                    <input id="account" type="text" class="input-field" placeholder="Username" required>
                </div>
                <div class="input-div">
                    <div for="exampleInputPassword01" class="icon-div"> <i class="fas fa-solid fa-lock"></i></div>
                    <input id="exampleInputPassword01" type="password" 
                    class="input-field" placeholder="Password" required>
                </div>

                <div class="others">
                    <input type="checkbox" class="check-box">
                    <span>Remeber Password</span>
                </div>

                <button type="button" class="submit-btn" id="btn1">Log in</button>
            </div>

            <div id="register" class="input-group">
                <div class="input-div">
                    <div class="icon-div"><i class="fas fa-solid fa-user-astronaut"></i></div>

                    <input id="registerAccount" for="managerAccount" type="text" class="input-field" placeholder="Account" required>
                    <label id="l1" style="color: red; font-size: 15;"></label>
                </div>
                <div class="input-div">
                    <div class="icon-div"><i class="fas fa-solid fa-lock"></i></div>
                    <input id="registerPassword" type="password" class="input-field" placeholder="Password" required>
                </div>

                <div class="input-div">
                    <div class="icon-div"><i class="fa-solid fa-id-card"></i></div>

                    <input id="registerName" type="text" class="input-field" placeholder="Name" required>
                </div>

                <!-- <div class="others">
                    <input type="checkbox" class="check-box">
                    <span>I agree to the terms & conditions</span>
                </div> -->

                <button type="button" class="submit-btn" id="btn2">Register</button>
            </div>

        </div>

    </section>


    <!-- Login ends -->
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="<%=request.getContextPath()%>/backend/assets/weiwei/js/loginScript.js"></script>
    <script>
    
    $("#btn1").click(() => {
    
            $.ajax({
                type: 'POST',
                url: "/CGA101G1/manager/managerLogin",
                contentType: "application/json",
                data: JSON.stringify({
                    managerAccount: account.value,
                    managerPassword: exampleInputPassword01.value
                }),
                success: data => {
                    const { successful, message, initlocation } = data;
                    if (successful) {
                        let { managerNo, managerName, managerStatus } = data;
                        sessionStorage.setItem("managerNo", managerNo);
                        sessionStorage.setItem("managerName", managerName);
                        sessionStorage.setItem("managerStatus", managerStatus);
                      
                        if (initlocation !== undefined) {
                            location = initlocation;
                        } else {
                            location = "/CGA101G1/backend/index.jsp";
                        }
                        initlocation = "/CGA101G1/backend/index.jsp"; 
                    } else {
                        $("#errMsg").html(message);
               
                    }
                },

            });
        });
    
    $("#btn2").click(() => {
    	
    	 $.ajax({
             type: 'POST',
             url: "/CGA101G1/manager/managerRegister",
             contentType: "application/json",
             data: JSON.stringify({
                 managerAccount: registerAccount.value,
                 managerPassword: registerPassword.value,
                 managerName: registerName.value
             }),
            
             success: data => {
                 const { successful, message } = data;
                 if (successful) {
                     alert(message);

                 } else {
                     $("#l8").html(message);
            
                 }
             },

         });
     });
     </script>
  <!--   <script src="<%=request.getContextPath()%>/backend/managerlogin/assets/js/login.js"></script> --> 
</body>

</html>
package com.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemService;
import com.member.model.MemVO;

@WebServlet("/mem/NewPasswordServlet")
public class NewPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		request.setAttribute("errorMsgs", errorMsgs);
		/***************************1.接收請求參數****************************************/
		//新密碼
		String memNewPassword = request.getParameter("memNewPassword");
		String Reg ="^[A-Za-z\\d]{6,12}$";
		if(memNewPassword.length() == 0) {
			errorMsgs.put("memNewPassword", "請輸入新密碼!!");
			
		}else if (!memNewPassword.matches(Reg)) {
			errorMsgs.put("memNewPassword", "請輸入英文及數字，且密碼長度需大於5小於13字數!");
		}
		//確認新密碼
		String confNewPassword=request.getParameter("confNewPassword");
		if(!confNewPassword.equals(memNewPassword)) {
			errorMsgs.put("confNewPassword", "密碼不一致，請重新輸入!!");
		}
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = request
					.getRequestDispatcher("/frontend/mem/ForgetPasswordChange.jsp");
			failureView.forward(request, response);
			return; //程式中斷
		}
		//取得會員
		MemService memService =new MemService();
		String memEmail = request.getParameter("memEmail");
		 memService.insertPassword( memNewPassword, memEmail);
		
		
		 /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			String url = "/frontend/memLogin/login.html";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		
	}

}

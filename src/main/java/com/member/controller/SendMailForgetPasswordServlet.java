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

import com.core.utils.MailService;
import com.member.model.MemService;
import com.member.model.MemVO;

@WebServlet("/mem/SendMailForgetPasswordServlet")
public class SendMailForgetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		/*************************** 1.接收請求參數 ****************************************/
		String memEmail = request.getParameter("memEmail");
		String Reg = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		MemService memService = new MemService();
		MemVO memVO = memService.getMemVOByEmail(memEmail);
		
		if (memEmail.length() == 0) {
			errorMsgs.put("memEmail", "請輸入Email!!");

		}else if (!memEmail.trim().matches(Reg)) {
			errorMsgs.put("memEmail", "請輸入正確Email格式!!");
		}else if (memVO == null) {
			errorMsgs.put("memEmail", "您還不是本網站的會員唷!!");
		}

		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = request.getRequestDispatcher("/frontend/mem/EmailForChangePassword.jsp");
			failureView.forward(request, response);
			return; // 程式中斷
		}
		// 寄信
		String subject = "POPGAME密碼修改";
		String messageText = "Hello!! " + memVO.getMemName() + "  以下是修改新密碼的網址: " + "\n" + request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/frontend" + "/mem"
				+ "/ForgetPasswordChange.jsp" + "?" + "memEmail" + "=" + memEmail;
		MailService mail = new MailService();
		mail.sendMail(memVO.getMemEmail(), subject, messageText);
		/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

		String url = "/frontend/memLogin/login.html";
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);

	}

}

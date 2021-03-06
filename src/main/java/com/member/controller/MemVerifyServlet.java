package com.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.member.utils.MemeberConstants.MEM_SERVICE;
@WebServlet("/mem/MemVerify")
public class MemVerifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
//		if (request.getSession() !=null) {	
//			MemVO mem =(MemVO)request.getSession().getAttribute("memVO");
			String memAccount=request.getParameter("memAccount");
			//修改會員信箱驗證狀態
			MEM_SERVICE.updateVerify(memAccount);
			//修改會員信箱驗證狀態的時間是何時
			MEM_SERVICE.updateVerifyTime(memAccount);
			response.sendRedirect(request.getContextPath()+"/frontend/memLogin/login.html");
//		}else {
//			response.sendRedirect(request.getContextPath()+"/frontend/mem/register.html");
//		}
		
	}
	
}
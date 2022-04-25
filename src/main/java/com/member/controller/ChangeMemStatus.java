package com.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.member.utils.MemeberConstants.MEM_SERVICE;
@WebServlet("/mem/ChangeMemStatus")
public class ChangeMemStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		 
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		String memAccount=request.getParameter("memAccount").trim();
		String opt = request.getParameter("status").trim();
		Integer status=Integer.parseInt(opt);
		MEM_SERVICE.memStatusEdit(memAccount, status);
		/***************************3.修改完成,準備轉交(Send the Success view)*************/
		String url = "/backend/mem/lookUpMem.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交index.html
		successView.forward(request, response);
		
	}

}

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

import com.member.model.MemVO;

import static com.member.utils.MemeberConstants.MEM_SERVICE;
@WebServlet("/mem/GetOneMem")
public class GetOneMem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		String action = request.getParameter("action");
		//用手機號碼查
		if("getOne_By_Mobile".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String memMobile = request.getParameter("memMobile");
			String reg="^09[0-9]{8}$";
			if (!memMobile.matches(reg)){
				errorMsgs.put("memMobile","手機號碼格式錯誤");
			}else if(memMobile == null || memMobile.trim().length() == 0){
				errorMsgs.put("memMobile","請輸入手機號碼");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request
						.getRequestDispatcher("/backend/mem/listAllMem.jsp");
				failureView.forward(request, response);
				return;
			}
		/***************************2.開始查資料*****************************************/
		MemVO memVO=MEM_SERVICE.getOneMemberByMemMobile(memMobile);
		if(memVO == null) {
			errorMsgs.put("memMobile","查無此會員");
		}
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = request
					.getRequestDispatcher("/backend/mem/listAllMem.jsp");
			failureView.forward(request, response);
			return;
		}
		
		
		/***************************3.完成,準備轉交(Send the Success view)*************/
		request.setAttribute("memVO", memVO);
		String url = "/backend/mem/listOneMem.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url); // 成功後,轉交
		successView.forward(request, response);
		}
		
		
		//用會員帳號查
		if ("getOne_By_MemAccount".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String memAccount = request.getParameter("memAccount");
			if(memAccount == null || memAccount.trim().length() == 0){
				errorMsgs.put("memAccount","請輸入會員帳號");			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request
						.getRequestDispatcher("/backend/mem/listAllMem.jsp");
				failureView.forward(request, response);
				return;
			}
			/***************************2.開始查資料*****************************************/
			MemVO memVO=MEM_SERVICE.getOneMemberByMemAccount(memAccount);
			if(memVO == null) {
				errorMsgs.put("memAccount","查無此會員");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request
						.getRequestDispatcher("/backend/mem/listAllMem.jsp");
				failureView.forward(request, response);
				return;
			}
			/***************************3.完成,準備轉交(Send the Success view)*************/
			request.setAttribute("memVO", memVO);
			String url = "/backend/mem/listOneMem.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 成功後,轉交
			successView.forward(request, response);
		}
	}

}

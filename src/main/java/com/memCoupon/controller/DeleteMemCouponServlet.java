package com.memCoupon.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.memCoupon.model.MemCouponService;

@WebServlet("/memCoupon/DeleteMemCouponServlet")
public class DeleteMemCouponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		String str = request.getParameter("couponTypeNo");
		Integer couponTypeNo = null;
		try {
			couponTypeNo = Integer.parseInt(str);
		} catch (Exception e) {
			errorMsgs.put("couponTypeNo", "刪除失敗");
		}
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/memCoupon/showMemCoupon.jsp");
			failureView.forward(request, response);
			return;
		}
	
		/*************************** 2.開始處理資料 *****************************************/
		MemCouponService memCouponService = new MemCouponService();
		 memCouponService.deleteOneCoupon(couponTypeNo);
	
		/***************************3.查詢完成,準備轉交(Send the Success view)*************/
		 errorMsgs.clear();
		 String url = "/backend/memCoupon/showMemCoupon.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); 
			successView.forward(request, response);
	
	}

}

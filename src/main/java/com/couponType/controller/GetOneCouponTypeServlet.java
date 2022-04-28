package com.couponType.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.couponType.model.CouponTypeService;
import com.couponType.model.CouponTypeVO;

@WebServlet("/couponType/GetOneCouponTypeServlet")
public class GetOneCouponTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		request.setAttribute("errorMsgs", errorMsgs);
		/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		String str=request.getParameter("couponTypeNo");   
		CouponTypeService couponTypeService=new CouponTypeService();
		
		Integer couponTypeNo = null;
		try {
			couponTypeNo = Integer.parseInt(str);
		} catch (Exception e) {
			errorMsgs.put("couponTypeNo","優惠券號碼格式不正確");
		}
	
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = request
					.getRequestDispatcher("/backend/couponType/listAllCouponType.jsp");
			failureView.forward(request, response);
			return;
		}
	/***************************2.開始查詢資料*****************************************/
	CouponTypeVO couponTypeVO=couponTypeService.listOneCouponType(couponTypeNo);
		if (couponTypeVO == null) {
			errorMsgs.put("couponTypeNo","查無資料");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = request
					.getRequestDispatcher("/backend/couponType/listAllCouponType.jsp");
			failureView.forward(request, response);
			return;//程式中斷
		}
		/***************************3.查詢完成,準備轉交(Send the Success view)*************/
		request.setAttribute("couponTypeVO", couponTypeVO); 
		String url = "/backend/couponType/listOneCouponType.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url); 
		successView.forward(request, response);

	}

}

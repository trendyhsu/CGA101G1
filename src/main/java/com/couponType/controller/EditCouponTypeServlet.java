package com.couponType.controller;

import java.io.IOException;
import java.sql.Date;
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

@WebServlet("/couponType/EditCouponTypeServlet")
public class EditCouponTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		request.setAttribute("errorMsgs", errorMsgs);
	
		/***************************1.接收請求參數****************************************/
		String couponName = request.getParameter("couponName");
		CouponTypeService couponTypeService = new CouponTypeService();
		
		CouponTypeVO couponTypeVO=couponTypeService.selectOneName(couponName);
		if(couponTypeVO != null) {
			errorMsgs.put("Dup", "優惠券名稱不得重複");
		}
		if (couponName == null || couponName.trim().length() == 0) {
			errorMsgs.put("couponName", "請勿空白");
		}
		Integer discountPrice = null;
		try {
			discountPrice = Integer.valueOf(request.getParameter("discountPrice").trim());
		} catch (NumberFormatException e) {
			errorMsgs.put("discountPrice", "折價金額請填數字");
		}
		Date couponDeadline = null;
		try {
			couponDeadline = Date.valueOf(request.getParameter("couponDeadline").trim());
		} catch (IllegalArgumentException e) {
			errorMsgs.put("couponDeadline", "日期錯誤");
		}
		Integer couponQuantity = null;
		try {
			couponQuantity = Integer.valueOf(request.getParameter("couponQuantity").trim());
		} catch (NumberFormatException e) {
			errorMsgs.put("couponQuantity", "數量請填數字");
		}

		String couponDescription = request.getParameter("couponDescription");
		Integer couponTypeNo = Integer.valueOf(request.getParameter("couponTypeNo"));
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = request
					.getRequestDispatcher("/couponType/GetWhichOneUpdate");
			failureView.forward(request, response);
			return; //程式中斷
		}
		
		/*************************** 2.開始修改資料 ***************************************/
		couponTypeVO=couponTypeService.editCouponType(couponName, discountPrice, couponDeadline, couponQuantity,
				couponDescription, couponTypeNo);
		/***************************3.修改完成,準備轉交(Send the Success view)*************/
		request.setAttribute("CouponTypeVO", couponTypeVO); // 資料庫update成功
		String url = "/backend/couponType/listAllCouponType.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交
		successView.forward(request, response);
	
	}

}
package com.couponType.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.couponType.model.CouponTypeService;
import com.couponType.model.CouponTypeVO;
import com.memCoupon.model.MemCouponService;
import com.memCoupon.model.MemCouponVO;

@WebServlet("/couponType/AddCouponTypeServlet")
public class AddCouponTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		
		CouponTypeService couponTypeService = new CouponTypeService();
		String couponName = request.getParameter("couponName");
		
		List<CouponTypeVO> list=couponTypeService.selectOneName(couponName);
		if(list.size() > 0) {
			errorMsgs.put("Dup", "優惠券名稱不得重複");
		}
		if (couponName == null || couponName.trim().length() == 0) {
			errorMsgs.put("couponName", "名稱請勿空白");
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

		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = request
					.getRequestDispatcher("/backend/couponType/addCouponType.jsp");
			failureView.forward(request, response);
			return; //程式中斷
		}
		/*************************** 2.開始新增資料 ***************************************/
		couponTypeService.addNewCouponTypeVO(couponName, discountPrice, couponDeadline, couponQuantity,
				couponDescription);
		
		/***************************3.新增完成,準備轉交(Send the Success view)***********/
		String url = "/backend/couponType/listAllCouponType.jsp";
		MemCouponService memCouponService = new MemCouponService();
		List<MemCouponVO> list1 = memCouponService.showAllMemCoupon();
		getServletContext().setAttribute("list", list1);
		RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交
		successView.forward(request, response);
		

	}

}
package com.couponType.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.couponType.model.CouponTypeService;

@WebServlet("/couponType/DeleteCouponTypeServlet")
public class DeleteCouponTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/***************************1.接收請求參數***************************************/
		Integer couponTypeNo = Integer.valueOf(request.getParameter("couponTypeNo"));
		
		/***************************2.開始刪除資料***************************************/
		CouponTypeService couponTypeService = new CouponTypeService();
		couponTypeService.deleteCouponType(couponTypeNo);
		/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
		String url = "/backend/couponType/listAllCouponType.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
		successView.forward(request, response);
	}

}

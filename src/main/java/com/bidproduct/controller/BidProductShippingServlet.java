package com.bidproduct.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidproduct.model.BidProductService;
import com.bidproduct.model.BidProductVO;

@WebServlet("/bid/bidProductShipping")
public class BidProductShippingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		
		// 取得商品編號
		Integer bidProductNo = Integer.valueOf(request.getParameter("bidProductNo").trim());

		// 判斷該商品狀態orderState
		BidProductService bidProductSvc = new BidProductService();
		BidProductVO bidProductVO = bidProductSvc.getOneBid(bidProductNo);
		if (bidProductVO.getOrderState() == 2) {
			errorMsgs.put("商品狀態","已出貨，無法重複出貨!");
		}

		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/bid/listAllBidOrder.jsp");
			failureView.forward(request, response);
			return; // 程式中斷
		}
		
		/*************************** 2.開始新增資料 ***************************************/
		// 開始修改競標訂單狀態 2 已出貨
		Integer orderState = new Integer(2);
		bidProductSvc.updateOrderState(orderState, bidProductNo);

		/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
		request.setAttribute("successMsg", "編號 " + bidProductNo + " 商品已出貨！");
		String url = "/backend/bid/listAllBidOrder.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllBidOrder.jsp
		successView.forward(request, response);

	}

}

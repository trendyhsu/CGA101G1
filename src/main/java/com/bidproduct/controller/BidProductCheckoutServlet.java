package com.bidproduct.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidapplylist.model.BidApplyListService;
import com.bidproduct.model.BidProductService;

@WebServlet("/bid/bidProductCheckout")
public class BidProductCheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);

//		try {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			// 取得會員編號
//			MemVO memVO = (memVO)request.getSession().getAttribute("memVO");
			Integer memNo = 11002;
			
			// 取得商品編號
			Integer bidProductNo = Integer.valueOf(request.getParameter("bidProductNo").trim());

			// 判斷收件人姓名
			String receiverName = request.getParameter("receiverName").trim();
			if (receiverName == null || receiverName.trim().length() == 0) {
				errorMsgs.put("receiverName","收件人姓名: 請勿空白");
			}
			System.out.println(receiverName);
			
			// 判斷收件人住址
			String receiverAddressCity = request.getParameter("receiverAddressCity").trim();
			String receiverAddressDist = request.getParameter("receiverAddressDist").trim();
			String receiverAddressDetail = request.getParameter("receiverAddressDetail").trim();
			if (receiverAddressDetail == null || receiverAddressDetail.trim().length() == 0) {
				errorMsgs.put("receiverAddressDetail","收件人住址: 請勿空白");
			}
			receiverAddressDetail = receiverAddressCity + receiverAddressDist + receiverAddressDetail;
			System.out.println(receiverAddressDetail);
			
			// 判斷收件人電話
			String receiverPhone = request.getParameter("receiverPhone").trim();
			if (receiverPhone == null || receiverPhone.trim().length() == 0) {
				errorMsgs.put("receiverPhone","收件人電話: 請勿空白");
			}
			System.out.println(receiverPhone);
			
			// 判斷信用卡資訊
			String creditcardNum = request.getParameter("creditcardNum").trim();
			if (creditcardNum == null || creditcardNum.trim().length() == 0) {
				errorMsgs.put("creditcardNum","信用卡資訊電話: 請勿空白");
			}
			System.out.println(creditcardNum);
			
			// 定義orderState為1 訂單處理中
			Integer orderState = new Integer(1);

			// 錯誤處理回傳
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request
						.getRequestDispatcher("/frontend/bid/bidcheckout.jsp");
				failureView.forward(request, response);
				return;//程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/
			// 開始修改競標訂單狀態
			BidProductService bidProductSvc = new BidProductService();
			bidProductSvc.updateReceiverAndOrderState(orderState, receiverName, 
					receiverAddressDetail, receiverPhone, bidProductNo);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/bid/bidProductWonByMemNo";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交bidProductWonByMem
			successView.forward(request, response);

			/*************************** 其他可能的錯誤處理 **********************************/
//		} catch (Exception e) {
//			errorMsgs.put("新增失敗",e.getMessage());
//			RequestDispatcher failureView = request
//					.getRequestDispatcher("/frontend/bid/bidcheckout.jsp");
//			failureView.forward(request, response);
//		}
	}
}
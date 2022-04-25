package com.bidapplylist.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bidapplylist.model.BidApplyListService;
import com.bidpic.model.BidPicService;
import com.bidproduct.model.BidProductService;
import com.bidproduct.model.BidProductVO;

@WebServlet("/bid/bidApplyListInsert")
public class BidApplyListInsertServlet extends HttpServlet {
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
			Integer memNo = 11001;
			
			// 判斷商品名稱
			String bidName = request.getParameter("bidName");
			String bidNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_:)(\\-\\)]{1,30}$";
			if (bidName == null || bidName.trim().length() == 0) {
				errorMsgs.put("bidName","商品名稱: 請勿空白");
			} else if (!bidName.trim().matches(bidNameReg)) {
				errorMsgs.put("bidName","商品名稱: 只能包含中文、英文大小寫、數字和底線及冒號 , 且長度須在1到30之間");
			}
			System.out.println(bidName);

			// 判斷商品敘述
			String bidProdDescription = request.getParameter("bidProdDescription").trim();
			if (bidProdDescription == null || bidProdDescription.trim().length() == 0) {
				errorMsgs.put("bidProdDescription","商品敘述: 請勿空白");
			}
			System.out.println(bidProdDescription);
			
			// 判斷遊戲公司
			Integer gameCompanyNo = null;
			try {
				gameCompanyNo = Integer.parseInt(request.getParameter("gameCompanyNo").trim());
			} catch (NumberFormatException e) {
				gameCompanyNo = 61001;
				errorMsgs.put("gameCompanyNo","遊戲公司請填數字.");
			}
			
			// 判斷遊戲類型
			Integer gameTypeNo = null;
			try {
				gameTypeNo = Integer.parseInt(request.getParameter("gameTypeNo").trim());
			} catch (NumberFormatException e) {
				gameTypeNo = 63001;
				errorMsgs.put("gameTypeNo","遊戲類型請填數字.");
			}
			
			// 判斷遊戲平台
			Integer gamePlatformNo = null;
			try {
				gamePlatformNo = Integer.parseInt(request.getParameter("gamePlatformNo").trim());
			} catch (NumberFormatException e) {
				gamePlatformNo = 64001;
				errorMsgs.put("gamePlatformNo","遊戲平台請填數字.");
			}
			
			// 判斷起標價
			Integer initialPrice = null;
			try {
				initialPrice = Integer.valueOf(request.getParameter("initialPrice").trim());
			} catch (NumberFormatException e) {
				initialPrice = 0;
				errorMsgs.put("initialPrice","起標價需為數字");
			}
			if (initialPrice < 0 || initialPrice > 100000) {
				errorMsgs.put("initialPrice","起標價應在0 - 100000之間");
			}
			System.out.println(initialPrice);
			
			// 判斷最低增額
			Integer bidPriceIncrement = null;
			try {
				bidPriceIncrement = Integer.valueOf(request.getParameter("bidPriceIncrement").trim());
			} catch (NumberFormatException e) {
				bidPriceIncrement = 0;
				errorMsgs.put("bidPriceIncrement","最低增額應為數字");
			}
			if (initialPrice < 0 || initialPrice > 100000) {
				errorMsgs.put("bidPriceIncrement","起標價應在0 - 100000之間");
			}
			System.out.println(bidPriceIncrement);
			
			// 判斷UPC編號
			String upcNum = request.getParameter("upcNum").trim();
			if (upcNum == null || upcNum.trim().length() == 0) {
				errorMsgs.put("upcNum","UPC編號: 請勿空白");
			}
			System.out.println(upcNum);

			// 判斷起標時間
			Timestamp bidLaunchedTime = null;
			try {
				bidLaunchedTime = Timestamp.valueOf(request.getParameter("bidLaunchedTime").trim());
			} catch (IllegalArgumentException e) {
				bidLaunchedTime = new Timestamp(System.currentTimeMillis());
				errorMsgs.put("bidLaunchedTime","請輸入起標時間!");
			}
			System.out.println(bidLaunchedTime);
			if (bidLaunchedTime.before(new Timestamp(System.currentTimeMillis()))) {
				bidLaunchedTime = new Timestamp(System.currentTimeMillis());
				errorMsgs.put("bidLaunchedTime","起標時間早於目前時間，請重新輸入！");
			}

			// 判斷截標時間
			java.sql.Timestamp bidSoldTime = null;
			try {
				bidSoldTime = java.sql.Timestamp.valueOf(request.getParameter("bidSoldTime").trim());
			} catch (IllegalArgumentException e) {
				bidSoldTime = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.put("bidSoldTime","請輸入截標時間!");
			}
			if (bidSoldTime.before(bidLaunchedTime) || bidSoldTime.equals(bidLaunchedTime)) {
				bidSoldTime = new Timestamp(System.currentTimeMillis() + 600000);
				errorMsgs.put("bidSoldTime","截標時間應晚於起標時間，請重新輸入！");
			}
			System.out.println(bidSoldTime);
			
			// 定義bidState為0
			Integer bidState = new Integer(0);

			// 錯誤處理回傳
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request
						.getRequestDispatcher("/frontend/bid/addbidapplylist.jsp");
				failureView.forward(request, response);
				return;//程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/
			// 開始新增競標申請單
			BidApplyListService bidApplyListSvc = new BidApplyListService();
			bidApplyListSvc.addBidApplyList(memNo, bidName, bidProdDescription, gameCompanyNo,
										gameTypeNo, gamePlatformNo, initialPrice, bidPriceIncrement, 
										upcNum, bidLaunchedTime, bidSoldTime, bidState);
			

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/bid/bidApplyListSeller";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交BidProductseller
			successView.forward(request, response);

			/*************************** 其他可能的錯誤處理 **********************************/
//		} catch (Exception e) {
//			errorMsgs.put("新增失敗",e.getMessage());
//			RequestDispatcher failureView = request
//					.getRequestDispatcher("/frontend/bid/addbidapplylist.jsp");
//			failureView.forward(request, response);
//		}
	}
}

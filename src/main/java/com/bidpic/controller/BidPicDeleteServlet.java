package com.bidpic.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidpic.model.BidPicService;
import com.bidproduct.model.BidProductVO;

@WebServlet("/BidPicDeleteServlet")
public class BidPicDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			/*************************** 1.接收請求參數 ***************************************/
			// 取得多選圖片的陣列 用 for 迴圈取得bidProPicNo 刪除
			BidPicService bidPicSvc = new BidPicService();
			String[] bidProdPicNos = request.getParameterValues("bidProdPicNos");
			if (bidProdPicNos != null) {
				for (String bidProdPicNo : bidProdPicNos) {
					bidPicSvc.deleteBidPic(new Integer(bidProdPicNo));
				}
			}

			// 讀取 BidVO 資料 封裝後再丟回editBid.jsp頁面
			// 競標商品編號
			Integer bidProductNo = null;
			try {
				bidProductNo = Integer.parseInt(request.getParameter("bidProductNo").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("競標商品編號需為數字");
			}
			System.out.println(bidProductNo);
			// 申請單編號
			Integer bidApplyListNo = null;
			try {
				bidApplyListNo = Integer.parseInt(request.getParameter("bidApplyListNo").trim());

			} catch (NumberFormatException e) {
				errorMsgs.add("申請單編號需為數字");
			}
			// 商品編號
			Integer productNo = null;
			try {
				productNo = Integer.parseInt(request.getParameter("productNo").trim());
			} catch (NumberFormatException e) {
				productNo = 0;
				errorMsgs.add("商品編號需為數字");
			}

			// 判斷商品名稱
			String bidName = request.getParameter("bidName");
			String bidNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_:)]{1,30}$";
			if (bidName == null || bidName.trim().length() == 0) {
				errorMsgs.add("商品名稱: 請勿空白");
			} else if (!bidName.trim().matches(bidNameReg)) {
				errorMsgs.add("商品名稱: 只能包含中文、英文大小寫、數字和底線及冒號 , 且長度須在1到30之間");
			}
			// 商品介紹
			String bidProdDescription = request.getParameter("bidProdDescription").trim();

			// 判斷起標價
			Integer initialPrice = null;
			try {
				initialPrice = Integer.parseInt(request.getParameter("initialPrice").trim());
			} catch (NumberFormatException e) {
				initialPrice = 0;
				errorMsgs.add("起標價需為數字");
			}
			if (initialPrice < 0 || initialPrice > 100000) {
				errorMsgs.add("起標價應在0 - 100000之間");
			}
			System.out.println(initialPrice);

			// 判斷競標狀態
			Integer bidState = null;
			try {
				bidState = Integer.parseInt(request.getParameter("bidState").trim());
			} catch (NumberFormatException e) {
				bidState = 0;
				errorMsgs.add("競標狀態格式錯誤(0:未結束 1:截標 2:流標)");
			}
			if (bidState < 0 || bidState > 2) {
				errorMsgs.add("競標狀態輸入錯誤(0:未結束 1:截標 2:流標)");
			}
			System.out.println(bidState);

			// 判斷最低出價
			Integer bidPriceIncrement = null;
			try {
				bidPriceIncrement = Integer.parseInt(request.getParameter("bidPriceIncrement").trim());
			} catch (NumberFormatException e) {
				bidPriceIncrement = 0;
				errorMsgs.add("最低增額應為數字");
			}
			if (bidPriceIncrement < 0) {
				errorMsgs.add("最低增額應大於0");
			}
			System.out.println(bidPriceIncrement);

			// 判斷起標時間
			Timestamp bidLaunchedTime = null;
			try {
				bidLaunchedTime = Timestamp.valueOf(request.getParameter("bidLaunchedTime").trim());
			} catch (IllegalArgumentException e) {
				bidLaunchedTime = new Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入起標時間!");
			}
			System.out.println(bidLaunchedTime);
			if (bidLaunchedTime.before(new Timestamp(System.currentTimeMillis()))) {
				bidLaunchedTime = new Timestamp(System.currentTimeMillis());
				errorMsgs.add("起標時間早於目前時間，請重新輸入！");
			}

			// 判斷截標時間
			java.sql.Timestamp bidSoldTime = null;
			try {
				bidSoldTime = java.sql.Timestamp.valueOf(request.getParameter("bidSoldTime").trim());
			} catch (IllegalArgumentException e) {
				bidSoldTime = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入截標時間!");
			}
			if (bidSoldTime.before(bidLaunchedTime) || bidSoldTime.equals(bidLaunchedTime)) {
				bidSoldTime = new Timestamp(System.currentTimeMillis() + 600000);
				errorMsgs.add("截標時間應晚於起標時間，請重新輸入！");
			}
			System.out.println(bidSoldTime);

			// 判斷商品狀態
			Integer orderState = null;
			try {
				orderState = Integer.parseInt(request.getParameter("orderState").trim());
			} catch (NumberFormatException e) {
				orderState = 0;
				errorMsgs.add("競標商品狀態應為數字(0:未出貨 1:已出貨 2:已收貨 4:作廢)");
			}
			if (orderState < 0 || orderState > 5) {
				orderState = 0;
				errorMsgs.add("競標商品狀態輸入錯誤(0:未出貨 1:已出貨 2:已收貨 4:作廢)");
			}
			System.out.println(orderState);

			// 判斷收件人資料
			String receiverName = null;
			String receiverAddress = null;
			String receiverPhone = null;
			try {
				receiverName = new String(request.getParameter("receiverName").trim());
			} catch (IllegalArgumentException e) {
				receiverName = "";
			}
			try {
				receiverAddress = new String(request.getParameter("receiverAddress").trim());
			} catch (IllegalArgumentException e) {
				receiverAddress = "";
			}
			try {
				receiverPhone = new String(request.getParameter("receiverPhone").trim());
			} catch (IllegalArgumentException e) {
				receiverPhone = "";
			}

			BidProductVO bidProductVO = new BidProductVO();

			bidProductVO.setBidProductNo(bidProductNo);
			bidProductVO.setBidApplyListNo(bidApplyListNo);
			bidProductVO.setProductNo(productNo);
			bidProductVO.setBidName(bidName);
			bidProductVO.setBidProdDescription(bidProdDescription);
			bidProductVO.setInitialPrice(initialPrice);
			bidProductVO.setBidState(bidState);
			bidProductVO.setBidPriceIncrement(bidPriceIncrement);
			bidProductVO.setBidLaunchedTime(bidLaunchedTime);
			bidProductVO.setBidSoldTime(bidSoldTime);
			bidProductVO.setOrderState(orderState);
			bidProductVO.setReceiverName(receiverName);
			bidProductVO.setReceiverAddress(receiverAddress);
			bidProductVO.setReceiverPhone(receiverPhone);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/

			request.setAttribute("bidProductVO", bidProductVO);		
			String url = "/backend/bid/editBid.jsp";
			// 刪除成功後,轉交回送出刪除的來源網頁
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);

		} catch (Exception e) {
			errorMsgs.add("刪除資料失敗:" + e.getMessage());
			e.printStackTrace();
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/bid/editBid.jsp");
			failureView.forward(request, response);
		}
	}

}

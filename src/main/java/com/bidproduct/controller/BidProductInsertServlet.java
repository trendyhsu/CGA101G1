package com.bidproduct.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bidapplylist.model.BidApplyListService;
import com.bidapplylist.model.BidApplyListVO;
import com.bidpic.model.BidPicService;
import com.bidpic.model.BidPicVO;
import com.bidproduct.model.BidProductService;
import com.bidproduct.model.BidProductVO;

@WebServlet("/bid/bidProductInsert")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class BidProductInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		request.setAttribute("errorMsgs", errorMsgs);

		try {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			BidProductVO bidProductVO = new BidProductVO();

			// 申請單編號
			Integer bidApplyListNo = null;
			try {
				bidApplyListNo = Integer.valueOf(request.getParameter("bidApplyListNo").trim());

			} catch (NumberFormatException e) {
				errorMsgs.add("申請單編號需為數字");
			}
			System.out.println(bidApplyListNo);
			// 商品編號
			Integer productNo = null;
			try {
				productNo = Integer.valueOf(request.getParameter("productNo").trim());
			} catch (NumberFormatException e) {
				productNo = 0;
				errorMsgs.add("商品編號需為數字");
			}
			System.out.println(productNo);
			// 賣家編號
			Integer sellerNo = null;
			try {
				sellerNo = Integer.valueOf(request.getParameter("sellerNo").trim());
			} catch (NumberFormatException e) {
				sellerNo = 0;
				errorMsgs.add("賣家編號需為數字");
			}
			System.out.println(sellerNo);
			// 判斷商品名稱
			String bidName = request.getParameter("bidName");
			String bidNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_:)(\\-\\)]{1,30}$";
			if (bidName == null || bidName.trim().length() == 0) {
				errorMsgs.add("商品名稱: 請勿空白");
			} else if (!bidName.trim().matches(bidNameReg)) {
				errorMsgs.add("商品名稱: 只能包含中文、英文大小寫、數字和底線及冒號 , 且長度須在1到30之間");
			}
			System.out.println(bidName);

			// 判斷商品介紹
			String bidProdDescription = null;
			try {
				bidProdDescription = request.getParameter("bidProdDescription").trim();
			} catch (IllegalArgumentException e) {
				bidProdDescription = "";
			}
			System.out.println(bidProdDescription);
			// 判斷起標價
			Integer initialPrice = null;
			try {
				initialPrice = Integer.valueOf(request.getParameter("initialPrice").trim());
			} catch (NumberFormatException e) {
				initialPrice = 0;
				errorMsgs.add("起標價需為數字");
			}
			if (initialPrice < 0 || initialPrice > 100000) {
				errorMsgs.add("起標價應在0 - 100000之間");
			}
			System.out.println(initialPrice);

			// 定義bidState為0
			Integer bidState = new Integer(0);

			// 判斷最低出價
			Integer bidPriceIncrement = null;
			try {
				bidPriceIncrement = Integer.valueOf(request.getParameter("bidPriceIncrement").trim());
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

			// 設定orderState為0
			Integer orderState = new Integer(0);

			// 將取得圖片資料裝入 List<byte[]> 物件
			Collection<Part> list = request.getParts();
			List<byte[]> picList = new ArrayList<byte[]>();
			InputStream is = null;
			BufferedInputStream bis = null;
			byte[] bidProdPicContent = null;

			// 使用 (is.available() > 1024) 過濾一起帶過來的文字資料
			for (Part part : list) {
				bis = new BufferedInputStream(part.getInputStream());
				if (bis.available() > 1024) {
					bidProdPicContent = new byte[bis.available()];
					bis.read(bidProdPicContent);
					picList.add(bidProdPicContent);
				}
			}

			// 錯誤處理回傳bidProductVO
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("bidProductVO", bidProductVO); // 含有輸入格式錯誤的bidProductVO物件,也存入req
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/bid/addBid.jsp");
				failureView.forward(request, response);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			// 新增競標商品資料 並回傳新商品編號
			BidProductService bidProductSvc = new BidProductService();
			Integer nextBidProductNo = bidProductSvc.addBidProduct(bidApplyListNo, productNo, bidName,
					bidProdDescription, sellerNo, initialPrice, bidState,
					bidLaunchedTime, bidSoldTime, bidPriceIncrement, orderState);

			// 新增圖片資料
			BidPicService bidPicSvc = new BidPicService();
			for (int i = 0; i < picList.size(); i++) {
				bidPicSvc.addBidPic(nextBidProductNo, picList.get(i));
			}

			// 修改BidApplyList 的 ApplyState 為 1 已上架
			BidApplyListService bidApplyListSvc = new BidApplyListService();
			bidApplyListSvc.updateApplyState(bidApplyListNo, new Integer(1));

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/backend/bid/listAllBid.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllBid.jsp
			successView.forward(request, response);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/bid/addBid.jsp");
			failureView.forward(request, response);
		}
	}
}

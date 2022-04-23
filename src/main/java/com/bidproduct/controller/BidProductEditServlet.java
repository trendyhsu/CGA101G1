package com.bidproduct.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidproduct.model.BidProductService;
import com.bidproduct.model.BidProductVO;

@WebServlet("/bid/bidProductEdit")
public class BidProductEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	// 處理來自listAllBid.jsp 修改競標商品資訊請求
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		List<String> errorMsgs = new LinkedList<String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);

		try {
			// 1.接收請求參數
			Integer bidProductNo = Integer.valueOf(request.getParameter("bidProductNo"));

			// 2.開始查詢資料
			BidProductService bidSvc = new BidProductService();
			BidProductVO bidProductVO = bidSvc.getOneBid(bidProductNo);

			// 3.查詢完成,準備轉交(Send the Success view)
			// 從資料庫取 bidProductVO 物件, 存入 request 中
			request.setAttribute("bidProductVO", bidProductVO);
			String url = "/backend/bid/editBid.jsp";
			// 成功轉交 editBid.jsp
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			
			// 其他可能的錯誤處理
		} catch (Exception e) {
			String url = "/backend/bid/listAllBid.jsp";
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			RequestDispatcher failureView = request.getRequestDispatcher(url);
			failureView.forward(request, response);
		}

	}

}

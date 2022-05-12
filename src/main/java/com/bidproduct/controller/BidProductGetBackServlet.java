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

@WebServlet("/bid/bidProductGetBack")
public class BidProductGetBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		
//		try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer bidProductNo = Integer.valueOf(request.getParameter("bidProductNo").trim());

			// 判斷該商品狀態orderState
			BidProductService bidProductSvc = new BidProductService();
			BidProductVO bidProductVO = bidProductSvc.getOneBid(bidProductNo);
			if (bidProductVO.getOrderState() == 3) {
				errorMsgs.put("商品狀態","取回處理中，無法重複取回");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("/bid/bidProductSeller");
				failureView.forward(request, response);
				return; // 程式中斷
			}
			/*************************** 2.開始修改資料 *****************************************/
			// 開始修改競標訂單狀態 3 取回處理中
			Integer orderState = new Integer(3);
			bidProductSvc.updateOrderState(orderState, bidProductNo);
			
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			request.setAttribute("successMsg", "編號" + bidProductNo + "商品取回已提出申請");
			String url = "/bid/bidProductSeller";
			// 修改成功後,轉交listAllBidApplyList.jsp
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);

			/*************************** 其他可能的錯誤處理 *************************************/
//		} catch (Exception e) {
//			e.printStackTrace();
//			errorMsgs.put("退貨失敗:", e.getMessage());
//			RequestDispatcher failureView = request.getRequestDispatcher("/bid/bidProductSeller");
//			failureView.forward(request, response);
//		}
	}
}

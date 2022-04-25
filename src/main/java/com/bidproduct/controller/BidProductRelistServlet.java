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

import com.bidapplylist.model.BidApplyListService;
import com.bidapplylist.model.BidApplyListVO;
import com.bidproduct.model.BidProductService;
import com.bidproduct.model.BidProductVO;

@WebServlet("/bid/bidProductRelist")
public class BidProductRelistServlet extends HttpServlet {
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
			Integer orderState = Integer.valueOf(request.getParameter("orderState").trim());
			if (orderState == 4) {
				errorMsgs.put("orderState","商品狀態為重新申請上架，無法重複申請!");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("/bid/bidProductSeller");
				failureView.forward(request, response);
				return; // 程式中斷
			}
			/*************************** 2.開始修改資料 *****************************************/
//			orderState = new Integer(4);
//			BidProductService bidProductSvc = new BidProductService();
//			bidProductSvc.updateOrderStateGetbackAndRelist(orderState, bidProductNo);
			
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			BidProductService bidProductSvc = new BidProductService();
			BidProductVO bidProductVO = bidProductSvc.getOneBid(bidProductNo);			
			BidApplyListVO bidApplyListVO = bidProductVO.getBidApplyListVO();
			
			String param = "?bidName="+ bidApplyListVO.getBidName()+
						"&bidProdDescription="+ bidApplyListVO.getBidProdDescription()+
						"&bidPriceIncrement="+ bidApplyListVO.getBidPriceIncrement()+
						"&bidLaunchedTime="+ bidApplyListVO.getBidLaunchedTime()+
						"&bidSoldTime="+ bidApplyListVO.getBidSoldTime()+
						"&gameCompanyNo="+ bidApplyListVO.getGameCompanyNo()+
						"&gamePlatformNo="+ bidApplyListVO.getGamePlatformNo()+
						"&gameTypeNo="+ bidApplyListVO.getGameTypeNo()+
						"&upcNum="+ bidApplyListVO.getUpcNum()+
						"&initialPrice="+ bidApplyListVO.getInitialPrice();
						
			String url = "/frontend/bid/addbidapplylist.jsp"+param;
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

package com.bidapplylist.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidapplylist.model.BidApplyListService;
import com.bidapplylist.model.BidApplyListVO;
import com.bidproduct.model.BidProductService;

@WebServlet("/bid/bidApplyListReturn")
public class BidApplyListReturnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		List<String> errorMsgs = new LinkedList<String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);

		try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer bidApplyListNo = Integer.valueOf(request.getParameter("bidApplyListNo").trim());
			
			/*************************** 2.開始修改資料 *****************************************/

			BidApplyListService bidApplyListSvc = new BidApplyListService();
			BidApplyListVO bidApplyListVO = new BidApplyListVO();
			// 設定申請單編號 及 退貨狀態2
			bidApplyListVO.setBidApplyListNo(bidApplyListNo);
			bidApplyListVO.setApplyState(2);
			bidApplyListSvc.updateApplyState(bidApplyListVO);
			
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			request.setAttribute("successMsg", "編號"+bidApplyListNo+"商品已退貨");
			String url = "/backend/bid/listAllBidApplyList.jsp";
			// 修改成功後,轉交listAllBidApplyList.jsp
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);

			/*************************** 其他可能的錯誤處理 *************************************/
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add("退貨失敗:" + e.getMessage());
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/bid/listAllBidApplyList.jsp");
			failureView.forward(request, response);
		}
	}

}

package com.bidproduct.controller;

import java.io.IOException;
import java.io.Writer;
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

@WebServlet("/bid/bidProductBackendSearch")
public class BidProductBackendSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		List<String> errorMsgs = new LinkedList<String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);
		
		String keyword = request.getParameter("keyword");
		BidProductService bidProductSvc = new BidProductService();
		List<BidProductVO> list = bidProductSvc.getAllByBidName(keyword);
		if(list.size()==0) {
			String url = "/backend/bid/listAllBid.jsp";
			errorMsgs.add("查無資料");
		}
		
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/bid/listAllBid.jsp");
			failureView.forward(request, response);
			return;
		}
		
		/*************************** 3.準備轉交(Send the Success view) ***********/
		request.setAttribute("list", list);
		RequestDispatcher successView = request.getRequestDispatcher("/backend/bid/listSearchBid.jsp");
		successView.forward(request, response);
	
	}

}

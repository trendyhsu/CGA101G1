package com.bidapplylist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidapplylist.model.BidApplyListService;
import com.bidapplylist.model.BidApplyListVO;
import com.member.model.MemVO;

@WebServlet("/bid/bidApplyListSeller")
public class BidApplyListSellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 取得會員編號
		MemVO memVO = (MemVO)request.getSession().getAttribute("memVO");
//		Integer memNo = memVO.getMemNo();
		
		Integer memNo = 11001;
		
		BidApplyListService bidApplyListSvc = new BidApplyListService();
		List<BidApplyListVO> bidApplyListVOs = bidApplyListSvc.getAllBidApplyListByMemNo(memNo);
		
		request.setAttribute("bidApplyListVOs", bidApplyListVOs);
		
		
		// 顯示數量
		
		// 多少頁
		
		
		RequestDispatcher successView = request.getRequestDispatcher("/frontend/bid/myapplylist.jsp");
		successView.forward(request, response);
	}

}

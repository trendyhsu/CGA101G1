package com.bidproduct.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidproduct.model.BidProductService;
import com.bidproduct.model.BidProductVO;
import com.member.model.MemVO;

@WebServlet("/bid/bidProductWonByMemNo")
public class BidProductWonByMemNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 取得會員編號
		MemVO memVO = (MemVO)request.getSession().getAttribute("memVO");
//		Integer memNo = memVO.getMemNo();
		
		Integer memNo = 11002;
		
		BidProductService bidProductSvc = new BidProductService();
		List<BidProductVO> bidProductVOs = bidProductSvc.getAllByBuyNo(memNo);
		
		request.setAttribute("bidProductVOs", bidProductVOs);
		
		RequestDispatcher successView = request.getRequestDispatcher("/frontend/bid/mybidorder.jsp");
		successView.forward(request, response);
	}

}

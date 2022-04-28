package com.bidapplylist.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidapplylist.model.BidApplyListService;
import com.bidapplylist.model.BidApplyListVO;

@WebServlet("/bid/bidApplyListGetAll")
public class BidApplyListGetAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);

		BidApplyListService bidApplyListSvc = new BidApplyListService();
		List<BidApplyListVO> list = bidApplyListSvc.getAll();
		request.setAttribute("list", list);
		
		/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
		String url = "/frontend/bid/listAllApplyList.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllApplyList.jsp
		successView.forward(request, response);


	}

}

package com.forummsgreport.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forummsgreport.model.ForumMsgReportService;
import com.forummsgreport.model.ForumMsgReportVO;




@WebServlet("/forum/forumMsgReportOneEdit")
public class ForumMsgReportOneEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);

			// 1.接收請求參數
			Integer forumMsgReportNo = Integer.valueOf(request.getParameter("forumMsgReportNo"));

			// 2.開始查詢資料
			ForumMsgReportService forumMsgReportSvc = new ForumMsgReportService();
			ForumMsgReportVO forumMsgReportVO = forumMsgReportSvc.getOneForumMsgReport(forumMsgReportNo);

			// 3.查詢完成,準備轉交(Send the Success view)
			// 從資料庫取forumMsgReportVO 物件, 存入 request 中
			request.setAttribute("forumMsgReportVO", forumMsgReportVO);
			String url = "/backend/forum/editForumMsgReport.jsp";
			// 成功轉交 editForumMsgReport.jsp
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);

	}	

}

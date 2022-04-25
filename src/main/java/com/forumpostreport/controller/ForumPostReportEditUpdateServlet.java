package com.forumpostreport.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.forumpostreport.model.ForumPostReportService;
import com.forumpostreport.model.ForumPostReportVO;

@WebServlet("/forum/forumPostReportEditUpdate")
public class ForumPostReportEditUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// 處理來自editForumPostReport.jsp 送出修改商品請求

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		List<String> errorMsgs = new LinkedList<String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);

		try {
			// 1.接收請求參數
			Integer forumPostReportNo = Integer.valueOf(request.getParameter("forumPostReportNo"));
			Integer forumPostReportType = Integer.valueOf(request.getParameter("forumPostReportType"));

			// 2.開始查詢資料
			ForumPostReportService forumPostReportSvc = new ForumPostReportService();
			ForumPostReportVO forumPostReportVO = forumPostReportSvc.getOneForumPostReport(forumPostReportNo);

			forumPostReportVO.setForumPostReportType(forumPostReportType);

			// 回傳錯誤訊息
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("forumPostReportVO", forumPostReportVO);
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/forum/editForumPostReport.jsp");
				failureView.forward(request, response);
				return; // 程式中斷
			}

			// 3.開始修改資料

			forumPostReportSvc.updateForumPostReport(forumPostReportNo, forumPostReportType);

			// 4.查詢完成,準備轉交(Send the Success view)

			request.setAttribute("forumPostReportVO", forumPostReportVO); // 資料庫update成功後,正確的的forumPostReportVO物件,存入request
			String url = "/backend/forum/listOneForumPostReport.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneForumPostReport.jsp
			successView.forward(request, response);

			/*************************** 其他可能的錯誤處理 *************************************/
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add("修改資料失敗:" + e.getMessage());
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/forum/editForumPostReport.jsp");
			failureView.forward(request, response);
		}
	}

}

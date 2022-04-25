package com.forummsgreport.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forummsgreport.model.ForumMsgReportService;
import com.forummsgreport.model.ForumMsgReportVO;

@WebServlet("/forum/forumMsgReportEditUpdate")
public class ForumMsgReportEditUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// 處理來自editForumMsgReport.jsp 送出修改商品請求

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		List<String> errorMsgs = new LinkedList<String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);

		try {
			// 1.接收請求參數
			Integer forumMsgReportNo = Integer.valueOf(request.getParameter("forumMsgReportNo"));
			Integer forumMsgReportType = Integer.valueOf(request.getParameter("forumMsgReportType"));

			// 2.開始查詢資料
			ForumMsgReportService forumMsgReportSvc = new ForumMsgReportService();
			ForumMsgReportVO forumMsgReportVO = forumMsgReportSvc.getOneForumMsgReport(forumMsgReportNo);

			forumMsgReportVO.setForumMsgReportType(forumMsgReportType);

			// 回傳錯誤訊息
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("forumMsgReportVO", forumMsgReportVO);
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/forum/editForumMsgReport.jsp");
				failureView.forward(request, response);
				return; // 程式中斷
			}

			// 3.開始修改資料

			forumMsgReportSvc.updateForumMsgReport(forumMsgReportNo, forumMsgReportType);

			// 4.查詢完成,準備轉交(Send the Success view)

			request.setAttribute("forumMsgReportVO", forumMsgReportVO); // 資料庫update成功後,正確的的forumMsgReportVO物件,存入request
			String url = "/backend/forum/listOneForumMsgReport.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneForumMsgReport.jsp
			successView.forward(request, response);

			/*************************** 其他可能的錯誤處理 *************************************/
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add("修改資料失敗:" + e.getMessage());
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/forum/editForumMsgReport.jsp");
			failureView.forward(request, response);
		}
	}

}

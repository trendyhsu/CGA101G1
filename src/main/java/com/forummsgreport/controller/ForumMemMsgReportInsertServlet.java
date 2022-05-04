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

import com.member.model.MemVO;

@WebServlet("/forum/forumMemMsgReportInsert")
public class ForumMemMsgReportInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);

		// session 取得會員編號
		MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
//		Integer memNo = memVO.getMemNo();

		Integer memNo = 11003;

		// 1.接收請求參數
		Integer forumMsgReportType = Integer.valueOf(request.getParameter("forumMsgReportType").trim());
		Integer forumPostNo = Integer.valueOf(request.getParameter("forumPostNo").trim());
		Integer forumMsgNo = Integer.valueOf(request.getParameter("forumMsgNo").trim());

		String forumMsgReportWhy = request.getParameter("forumMsgReportWhy").trim();
		if (forumMsgReportWhy == null || forumMsgReportWhy.trim().length() == 0) {
			errorMsgs.put("forumMsgReportWhy", ": 請勿空白");
		}

		if (!errorMsgs.isEmpty()) {

			request.setAttribute("forumPostNo", forumPostNo);
			request.setAttribute("forumMsgNo", forumMsgNo);

			RequestDispatcher failureView = request.getRequestDispatcher("/frontend/forum/editForumMsgReport.jsp");
			failureView.forward(request, response);
			return;
		}

		// 2.開始新增資料
		ForumMsgReportService forumMsgReportSvc = new ForumMsgReportService();
		forumMsgReportSvc.addForumMsgReport(forumMsgNo, memNo, forumMsgReportType, forumMsgReportWhy);

		// 3.查詢完成,準備轉交(Send the Success view)

		request.setAttribute("forumPostNo", forumPostNo);
		String url = "/forum/selectOnePostAllMsg";
//		// 成功轉交 editForumPostReport.jsp
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);

	}

}

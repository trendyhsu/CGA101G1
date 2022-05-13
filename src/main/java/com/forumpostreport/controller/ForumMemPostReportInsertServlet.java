package com.forumpostreport.controller;

import java.io.IOException;
import java.util.LinkedHashMap;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forumpostreport.model.ForumPostReportService;
import com.member.model.MemVO;

@WebServlet("/forum/forumMemPostReportInsert")
public class ForumMemPostReportInsertServlet extends HttpServlet {
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
		Integer memNo = memVO.getMemNo();

		// 1.接收請求參數
		Integer forumPostReportType = Integer.valueOf(request.getParameter("forumPostReportType").trim());
		Integer forumPostNo = Integer.valueOf(request.getParameter("forumPostNo").trim());
		String forumPostReportWhy = request.getParameter("forumPostReportWhy").trim();
		if (forumPostReportWhy == null || forumPostReportWhy.trim().length() == 0) {
			errorMsgs.put("forumPostReportWhy", ": 請勿空白");
		}

		if (!errorMsgs.isEmpty()) {

			request.setAttribute("forumPostNo", forumPostNo);

			RequestDispatcher failureView = request.getRequestDispatcher("/frontend/forum/editForumPostReport.jsp");
			failureView.forward(request, response);
			return;
		}

		// 2.開始新增資料
		ForumPostReportService forumPostReportSvc = new ForumPostReportService();
		forumPostReportSvc.addForumPostReport(forumPostNo, memNo, forumPostReportType, forumPostReportWhy);

		// 3.查詢完成,準備轉交(Send the Success view)

		request.setAttribute("forumPostNo", forumPostNo);
		String url = "/forum/selectOnePostAllMsg";
//		// 成功轉交 editForumPostReport.jsp
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);

	}

}

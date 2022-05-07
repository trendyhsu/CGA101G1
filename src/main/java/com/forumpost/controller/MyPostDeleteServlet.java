package com.forumpost.controller;

import java.io.IOException;
import java.util.LinkedHashMap;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forumpost.model.ForumPostService;
import com.member.model.MemVO;

@WebServlet("/forum/myPostDelete")
public class MyPostDeleteServlet extends HttpServlet {
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

		// 1.接收請求參數
		Integer forumPostNo = Integer.valueOf(request.getParameter("forumPostNo").trim());
		Integer forumPostState = Integer.valueOf(0);
		// 2.開始修改資料
		ForumPostService forumPostSvc = new ForumPostService();
		forumPostSvc.updateMemPostState(forumPostNo, forumPostState);

		// 3.查詢完成,準備轉交(Send the Success view)
		// 從資料庫取forumPostReportVO 物件, 存入 request 中
		request.setAttribute("memVO", memVO);
		String url = "/forum/forumPostMyPostMemNo";
		// 成功轉交 editForumPost.jsp
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);

	}

}

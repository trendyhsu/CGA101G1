package com.forumpost.controller;

import java.io.IOException;

import java.util.LinkedHashMap;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum.model.ForumService;
import com.forum.model.ForumVO;

@WebServlet("/forum/forumPostInsert")
public class ForumPostInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);

		/*********************** 1.接收請求參數 *************************/
		ForumService forumSvc = new ForumService();
		ForumVO forumVO = new ForumVO();

		// 討論區編號
		Integer forumNo = Integer.valueOf(request.getParameter("forumNo").trim());

		/*************************** 2.開始新增資料 ***************************************/
		// 新增討論區資料 並回傳新討論區編號

		/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

		forumVO = forumSvc.getOneForum(forumNo);

		request.setAttribute("forumVO", forumVO);
		String url = "/frontend/forum/addForumPost.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);

	}
}

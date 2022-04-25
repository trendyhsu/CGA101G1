package com.forummsg.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forummsg.model.ForumMsgService;
import com.forummsg.model.ForumMsgVO;


@WebServlet("/forum/forumMsgOneEditServlet")
public class ForumMsgOneEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		List<String> errorMsgs = new LinkedList<String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);
		
		try {
			// 1.接收請求參數
			Integer forumMsgNo = Integer.valueOf(request.getParameter("forumMsgNo"));

			// 2.開始查詢資料
			ForumMsgService forumMsgSvc = new ForumMsgService();
			ForumMsgVO forumMsgVO = forumMsgSvc.getOneForumMsg(forumMsgNo);

			// 3.查詢完成,準備轉交(Send the Success view)
			// 從資料庫取forumMsgVO 物件, 存入 request 中
			request.setAttribute("forumMsgVO", forumMsgVO);
			String url = "/backend/forum/editForumMsg.jsp";
			// 成功轉交 editForumMsg.jsp
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			
			// 其他可能的錯誤處理
		} catch (Exception e) {
			String url = "/backend/forum/listAllForumMsg.jsp";
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			RequestDispatcher failureView = request.getRequestDispatcher(url);
			failureView.forward(request, response);
		}

	}	

}

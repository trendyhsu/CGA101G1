package com.forumpost.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forumpost.model.ForumPostService;
import com.forumpost.model.ForumPostVO;



@WebServlet("/forum/forumMasterPostOneEditServlet")
public class ForumMasterPostOneEditServlet extends HttpServlet {
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
			Integer forumPostNo = Integer.valueOf(request.getParameter("forumPostNo"));

			// 2.開始查詢資料
			ForumPostService forumPostSvc = new ForumPostService();
			ForumPostVO forumPostVO = forumPostSvc.getOneForumPost(forumPostNo);

			// 3.查詢完成,準備轉交(Send the Success view)
			// 從資料庫取forumPostReportVO 物件, 存入 request 中
			request.setAttribute("forumPostVO", forumPostVO);
			String url = "/backend/forum/editForumMasterPost.jsp";
			// 成功轉交 editForumPost.jsp
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			
	}	

}

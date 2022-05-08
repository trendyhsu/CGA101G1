package com.forumpost.controller;

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

import com.forumpost.model.ForumPostService;
import com.forumpost.model.ForumPostVO;
import com.forumpostpic.model.ForumPostPicService;
import com.forumpostpic.model.ForumPostPicVO;

@WebServlet("/forum/forumMemPostOne")
public class ForumMemPostOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// 處理來自editForumPost.jsp 送出修改商品請求

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);

		/*************************** 1.接收請求參數 **********************/

		Integer forumPostNo = Integer.valueOf(request.getParameter("forumPostNo"));

		// 2.開始查詢資料
		ForumPostService forumPostSvc = new ForumPostService();
		ForumPostVO forumPostVO = forumPostSvc.getOneForumPost(forumPostNo);

		ForumPostPicService forumPostPicSvc = new ForumPostPicService();

		List<ForumPostPicVO> forumPostPicVOs = forumPostPicSvc.getOneForumTotalPostPic(forumPostNo);

		// 3.查詢完成,準備轉交(Send the Success view)
		// 從資料庫取forumPostVO 物件, 存入 request 中
		request.setAttribute("forumPostVO", forumPostVO);
		request.setAttribute("forumPostPicVOs", forumPostPicVOs);

		String url = "/frontend/forum/editForumPost.jsp";
		// 成功轉交 editForumPost.jsp
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);

	}
}

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
import com.forumpost.model.ForumPostVO;

@WebServlet("/forum/forumMasterPostEditUpdate")
public class ForumMasterPostEditUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// 處理來自editForumMasterPost.jsp 送出修改商品請求

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);

		/*************************** 1.接收請求參數 **********************/
		Integer forumPostNo = Integer.valueOf(request.getParameter("forumPostNo").trim());
		Integer forumPostState = Integer.valueOf(request.getParameter("forumPostState").trim());

		// 回傳錯誤訊息
		if (!errorMsgs.isEmpty()) {
			
			ForumPostService forumPostSvc = new ForumPostService();
			ForumPostVO forumPostVO = forumPostSvc.getOneForumPost(forumPostNo);
			
			request.setAttribute("forumPostVO", forumPostVO);
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/forum/editForumPost.jsp");
			failureView.forward(request, response);
			return; // 程式中斷
		}

		/*************************** 2.開始修改資料 *****************************************/
		ForumPostService forumPostSvc = new ForumPostService();
		ForumPostVO forumPostVO = forumPostSvc.updateAdminPost(forumPostNo,forumPostType,forumPostState);


		/*************************** 3.修改完成,準備轉交(Send the Success view) *************/

		request.setAttribute("forumPostVO", forumPostVO); // 資料庫update成功後,正確的的forumPostVO物件,存入request
		String url = "/backend/forum/listOneForumPost.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneForumPost.jsp
		successView.forward(request, response);

	}

}

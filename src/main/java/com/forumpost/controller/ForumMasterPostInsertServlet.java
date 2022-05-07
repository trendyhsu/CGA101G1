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
import com.manager.model.ManagerVO;

@WebServlet("/forum/forumMasterPostInsert")
public class ForumMasterPostInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);

		/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		ForumPostService forumPostSvc = new ForumPostService();
		ForumPostVO forumPostVO = new ForumPostVO();
		
		ManagerVO mangerVO = (ManagerVO) request.getSession().getAttribute("managerVO");
		Integer managerNo = mangerVO.getManagerNo();
		

		Integer forumNo = Integer.valueOf(request.getParameter("forumNo").trim());

		if (forumNo == 0) {
			errorMsgs.put("forumNo", "請選擇討論區");
		}

		Integer forumPostState = Integer.valueOf(request.getParameter("forumPostState").trim());
		Integer forumPostType = Integer.valueOf(request.getParameter("forumPostType").trim());
		Integer forumPostFeatured = Integer.valueOf(request.getParameter("forumPostFeatured").trim());

		String forumPostTitle = request.getParameter("forumPostTitle").trim();

		if (forumPostTitle == null || forumPostTitle.trim().length() == 0) {
			errorMsgs.put("forumPostTitle", "請勿空白");
		} else if (forumPostTitle.trim().length() > 100) {
			errorMsgs.put("forumPostTitle", "文章標題:長度必需在1到100之間");
		}

		String forumPostContent = request.getParameter("forumPostContent").trim();
		if (forumPostContent == null || forumPostContent.trim().length() == 0) {
			errorMsgs.put("forumPostContent", "請勿空白");
		}

		
		// 錯誤處理回傳forumPostVO
		if (!errorMsgs.isEmpty()) {

			forumPostVO.setManagerNo(managerNo);
			forumPostVO.setForumNo(forumNo);
			forumPostVO.setForumPostState(forumPostState);
			forumPostVO.setForumPostTitle(forumPostTitle);
			forumPostVO.setForumPostContent(forumPostContent);

			request.setAttribute("forumPostVO", forumPostVO); // 含有輸入格式錯誤的forumVO物件,也存入req
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/forum/addForumMasterPost.jsp");
			failureView.forward(request, response);
			return;
		}

		/*************************** 2.開始新增資料 ***************************************/
		// 新增資料

		Integer forumPostNo = null;

		forumPostNo = forumPostSvc.addForumMasterPost(forumNo, forumPostType, managerNo, forumPostState, forumPostTitle,
				forumPostContent, forumPostFeatured);

		/*************************** 3.收尋資料 ***************************************/

		forumPostVO = forumPostSvc.getOneForumPost(forumPostNo);

		/*************************** 4.新增完成,準備轉交(Send the Success view) ***********/

		request.setAttribute("forumPostVO", forumPostVO);
		String url = "/backend/forum/listOneForumMasterPost.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);

	}
}

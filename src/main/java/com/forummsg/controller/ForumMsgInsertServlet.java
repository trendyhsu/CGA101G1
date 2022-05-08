package com.forummsg.controller;

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

import com.forummsg.model.ForumMsgService;
import com.forummsg.model.ForumMsgVO;
import com.forumpost.model.ForumPostService;
import com.forumpost.model.ForumPostVO;
import com.member.model.MemVO;

@WebServlet("/forum/forumMsgInsert")
public class ForumMsgInsertServlet extends HttpServlet {
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

		MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
		Integer memNo = memVO.getMemNo();

		ForumMsgService forumMsgSvc = new ForumMsgService();

		Integer forumPostNo = Integer.valueOf(request.getParameter("forumPostNo").trim());
		Integer forumMsgType = Integer.valueOf(1);
		String forumMsg = request.getParameter("forumMsg".trim());

		if (forumMsg == null || forumMsg.trim().length() == 0) {
			errorMsgs.put("forumMsg", ":留言請勿空白");
		}

		// 錯誤處理回傳forumPostVO
		if (!errorMsgs.isEmpty()) {

			ForumPostService forumPostSvc = new ForumPostService();
			ForumPostVO forumPostVO = forumPostSvc.getOneForumPost(forumPostNo);

			List<ForumMsgVO> forumMsgVOs = forumMsgSvc.getOnePostAllMsg(forumPostNo);

			request.setAttribute("forumPostVO", forumPostVO);
			request.setAttribute("forumMsgVOs", forumMsgVOs);

			String url = "/frontend/forum/onePostAllMsg.jsp";

			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);

			return;
		}

		/*************************** 2.開始新增資料 ***************************************/
		// 新增資料
		forumMsgSvc.addForumMsg(memNo, forumPostNo, forumMsgType, forumMsg);

		/*************************** 3.收尋資料 ***************************************/
		ForumPostService forumPostSvc = new ForumPostService();
		ForumPostVO forumPostVO = forumPostSvc.getOneForumPost(forumPostNo);

		List<ForumMsgVO> forumMsgVOs = forumMsgSvc.getOnePostAllMsg(forumPostNo);

		/*************************** 4.新增完成,準備轉交(Send the Success view) ***********/
		request.setAttribute("forumPostVO", forumPostVO);
		request.setAttribute("forumMsgVOs", forumMsgVOs);

		String url = "/frontend/forum/onePostAllMsg.jsp";

		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);

	}
}

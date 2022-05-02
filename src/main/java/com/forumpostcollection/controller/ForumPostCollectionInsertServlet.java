package com.forumpostcollection.controller;

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

import com.forumpostcollection.model.ForumPostCollectionService;
import com.forumpostcollection.model.ForumPostCollectionVO;
import com.member.model.MemVO;

@WebServlet("/forum/forumPostCollectionInsert")
public class ForumPostCollectionInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);

		// session 取得會員編號
		MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
//				Integer memNo = memVO.getMemNo();

		Integer memNo = 11003;

		/*********************** 1.接收請求參數 *************************/
		ForumPostCollectionService forumPostCollectionSvc = new ForumPostCollectionService();

		Integer forumPostNo = Integer.valueOf(request.getParameter("forumPostNo").trim());

		ForumPostCollectionVO forumPostCollectionVO = forumPostCollectionSvc.getOnePostCollection(memNo, forumPostNo);

		// 已防有加過文章
		if (forumPostCollectionVO != null) {

			request.setAttribute("forumPostNo", forumPostNo);
			String url = "/forum/selectOnePostAllMsg";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			return;
		}

		/*************************** 2.開始新增資料 ***************************************/
		// 新增資料
		forumPostCollectionSvc.addForumPostCollection(memNo, forumPostNo);

		/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

		request.setAttribute("forumPostNo", forumPostNo);
		String url = "/forum/selectOnePostAllMsg";
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);

	}
}

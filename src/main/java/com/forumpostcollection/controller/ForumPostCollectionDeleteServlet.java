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


@WebServlet("/forum/forumPostCollectionDelete")
public class ForumPostCollectionDeleteServlet extends HttpServlet {
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
		ForumPostCollectionService forumPostCollectionSvc = new ForumPostCollectionService();
		ForumPostCollectionVO forumPostCollectionVO = new ForumPostCollectionVO();

		Integer memNo = Integer.valueOf(request.getParameter("memNo").trim());
		Integer forumPostNo = Integer.valueOf(request.getParameter("forumPostNo").trim());

		// 錯誤處理回傳forumPostVO
		if (!errorMsgs.isEmpty()) {

			forumPostCollectionVO.setMemNo(memNo);
			forumPostCollectionVO.setForumPostNo(forumPostNo);

			request.setAttribute("forumPostCollectionVO", forumPostCollectionVO); // 含有輸入格式錯誤的forumPostCollectionVO物件,也存入req
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/forum/myFavouritePost.jsp");
			failureView.forward(request, response);
			return;
		}

		/*************************** 2.開始刪除資料 ***************************************/
		// 刪除資料
		forumPostCollectionSvc.deleteForumPostCollection(memNo,forumPostNo);

		/*************************** 3.收尋資料 ***************************************/

		List<ForumPostCollectionVO> forumPostCollectionVOs = forumPostCollectionSvc.getOwenrAllPostCollection(memNo);

		/*************************** 4.新增完成,準備轉交(Send the Success view) ***********/

		request.setAttribute("forumPostCollectionVOs", forumPostCollectionVOs);
		String url = "/frontend/forum/myFavouritePost.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);

	}
}

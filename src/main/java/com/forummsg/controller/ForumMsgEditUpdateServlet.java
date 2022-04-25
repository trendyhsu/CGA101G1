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


@WebServlet("/forum/forumMsgEditUpdate")
public class ForumMsgEditUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// 處理來自editForumMsgReport.jsp 送出修改商品請求

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		List<String> errorMsgs = new LinkedList<String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);

		try {
			// 1.接收請求參數
			Integer forumMsgNo = Integer.valueOf(request.getParameter("forumMsgNo"));
			Integer forumMsgType = Integer.valueOf(request.getParameter("forumMsgType"));

			// 2.開始查詢資料
			ForumMsgService forumMsgSvc = new ForumMsgService();
			ForumMsgVO forumMsgVO = forumMsgSvc.getOneForumMsg(forumMsgNo);

			forumMsgVO.setForumMsgType(forumMsgType);

			// 回傳錯誤訊息
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("forumMsgVO", forumMsgVO);
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/forum/editForumMsg.jsp");
				failureView.forward(request, response);
				return; // 程式中斷
			}

			// 3.開始修改資料

			forumMsgSvc.updateMsgType(forumMsgNo, forumMsgType);

			// 4.查詢完成,準備轉交(Send the Success view)

			request.setAttribute("forumMsgVO", forumMsgVO); // 資料庫update成功後,正確的的forumMsgVO物件,存入request
			String url = "/backend/forum/listOneForumMsg.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneForumMsg.jsp
			successView.forward(request, response);

			/*************************** 其他可能的錯誤處理 *************************************/
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add("修改資料失敗:" + e.getMessage());
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/forum/editForumMsg.jsp");
			failureView.forward(request, response);
		}
	}

}

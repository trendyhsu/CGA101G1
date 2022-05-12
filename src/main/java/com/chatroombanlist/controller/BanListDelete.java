package com.chatroombanlist.controller;

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

import com.chatroombanlist.model.ChatRoomBanListService;
import com.chatroombanlist.model.ChatRoomBanListVO;
import com.forumpostcollection.model.ForumPostCollectionService;
import com.forumpostcollection.model.ForumPostCollectionVO;
import com.gametype.model.GameTypeVO;
import com.member.model.MemVO;

@WebServlet("/chatroom/banListDelete")
public class BanListDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		request.setAttribute("errorMsgs", errorMsgs);

		/*********************** 1.接收請求參數 *************************/
		ChatRoomBanListService chatRoomBanListService = new ChatRoomBanListService();

		Integer memNo = Integer.valueOf(request.getParameter("memNo").trim());
		Integer memNo_Baned = Integer.valueOf(request.getParameter("memNo_Baned").trim());

		/*************************** 2.開始刪除資料 ***************************************/
		// 刪除資料
		chatRoomBanListService.deleteOneMemBanList(memNo, memNo_Baned);

		/*************************** 4.新增完成,準備轉交(Send the Success view) ***********/

		String url = "/frontend/chatroom/chatroomignorelist.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);

	}
}

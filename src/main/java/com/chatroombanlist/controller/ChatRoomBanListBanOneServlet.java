package com.chatroombanlist.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chatroombanlist.model.ChatRoomBanListService;
import com.member.model.MemService;
import com.member.model.MemVO;

@WebServlet("/chat/chatRoomBanListBanOne")
public class ChatRoomBanListBanOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 取得連線進來的會員編號
		MemVO memVO = (MemVO)request.getSession().getAttribute("memVO");
		Integer memNo = memVO.getMemNo();

		// 取得要ban的會員編號
		String banName = request.getParameter("banName");
		
		MemService memSvc = new MemService();
		
		// 調用方法
//		ChatRoomBanListService chatRoomBanListSvc = new ChatRoomBanListService();
//		chatRoomBanListSvc.addBanList(memNo, null);
	}

}

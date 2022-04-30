package com.chatroombanlist.controller;

import java.io.IOException;
import java.io.Writer;

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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Writer out = response.getWriter();
		
		// 取得連線進來的會員編號
		MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
		Integer memNo = memVO.getMemNo();

		// 取得要ban的會員編號
		String banName = request.getParameter("banName");

		MemService memSvc = new MemService();
		MemVO memVOBan = memSvc.getByMemName(banName);

		// 調用方法
		if (memVOBan != null) {
			ChatRoomBanListService chatRoomBanListSvc = new ChatRoomBanListService();
			chatRoomBanListSvc.addBanList(memNo, memVOBan.getMemNo());
			out.write("封鎖成功");
		}
		else {
			out.write("封鎖失敗，請確認輸入姓名是否正確");
		}
	}

}

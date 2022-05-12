package com.chatroombanlist.controller;

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
import javax.servlet.http.HttpSession;

import com.chatroombanlist.model.ChatRoomBanListService;
import com.chatroombanlist.model.ChatRoomBanListVO;
import com.member.model.MemVO;



@WebServlet("/chatroom/banListGetAll")
public class BanListGetAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		HttpSession session = request.getSession();
		MemVO memVO = (MemVO) (session.getAttribute("memVO"));
		Integer memNo = memVO.getMemNo();

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		
		ChatRoomBanListService chatRoomBanListService = new ChatRoomBanListService();
		List<ChatRoomBanListVO> chatRoomBanListVOs= chatRoomBanListService.getOneMemBanList(memNo);

		request.setAttribute("chatRoomBanListVOs", chatRoomBanListVOs);

		RequestDispatcher successView = request.getRequestDispatcher("/frontend/chatroom/chatroomignorelist.jsp");
		successView.forward(request, response);

	}
}
	

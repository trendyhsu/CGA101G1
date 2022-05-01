package com.forumpost.controller;

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

import com.forum.model.ForumService;
import com.forum.model.ForumVO;
import com.forumpost.model.ForumPostService;
import com.forumpost.model.ForumPostVO;

@WebServlet("/forum/selectOneForumAllPost")
public class SelectOneForumAllPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 取得會員編號

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);

		Integer forumNo = Integer.valueOf(request.getParameter("forumNo").trim());

		System.out.println(forumNo);

		ForumPostService forumPostSvc = new ForumPostService();
		List<ForumPostVO> forumPostVOs = forumPostSvc.findOneForumAllPost(forumNo);
		
		ForumService forumSvc = new ForumService();
		ForumVO forumVO = forumSvc.getOneForum(forumNo);
		

		// 將過濾好的VO放置於request scope內
		request.setAttribute("forumPostVOs", forumPostVOs);
		request.setAttribute("forumVO",forumVO);

		RequestDispatcher successView = request.getRequestDispatcher("/frontend/forum/oneForumAllPost.jsp");
		successView.forward(request, response);
	}

}

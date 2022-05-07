package com.forumpostpic.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forumpostpic.model.ForumPostPicService;

@WebServlet("/forum/forumPostPicDelete")
public class ForumPostPicDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("image/gif");

		Integer forumPostPicNo = Integer.valueOf(request.getParameter("forumPostPicNo").trim());
		Integer forumPostNo = Integer.valueOf(request.getParameter("forumPostNo"));

		ForumPostPicService forumPostPicSvc = new ForumPostPicService();
		forumPostPicSvc.deleteForumPostPic(forumPostPicNo);

		request.setAttribute("forumPostNo", forumPostNo);
		String url = "/forum/forumMemPostOne";
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);

	}

}

package com.forum.controller;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import com.forum.model.ForumService;
import com.forum.model.ForumVO;

@WebServlet("/forum/forumPicGetByForumNo")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ForumPicGetByForumNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("image/gif");

		Integer forumNo = Integer.valueOf(request.getParameter("forumNo"));

		ForumService forumSvc = new ForumService();
		ForumVO forumVO = forumSvc.getOneForum(forumNo);

		ServletOutputStream out = response.getOutputStream();
		out.write(forumVO.getForumImg());
	}

}

package com.forumpostpic.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forumpostpic.model.ForumPostPicService;
import com.forumpostpic.model.ForumPostPicVO;

@WebServlet("/forum/forumPostPicGetOneByPicNo")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ForumPostPicGetOneByPicNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("image/gif");

		Integer forumPostPicNo = Integer.valueOf(request.getParameter("forumPostPicPicNo").trim());

		ForumPostPicService forumPostPicSvc = new ForumPostPicService();
		ForumPostPicVO forumPostPicVO = forumPostPicSvc.getOneForumPostPic(forumPostPicNo);

		ServletOutputStream out = response.getOutputStream();
		out.write(forumPostPicVO.getForumPic());
		
		out.close();
	}

}

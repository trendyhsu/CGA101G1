package com.forum.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum.model.ForumService;
import com.forum.model.ForumVO;
import com.google.gson.Gson;

@WebServlet("/ForumGetAllServlet")
public class ForumGetAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ForumGetAllServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		ForumService forumSvc = new ForumService();
		List<ForumVO> list = forumSvc.getAll();
		Writer out = response.getWriter();
;
	}

}

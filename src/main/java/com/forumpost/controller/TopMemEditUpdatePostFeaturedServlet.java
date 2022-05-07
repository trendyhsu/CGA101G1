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

@WebServlet("/forum/topMemEditUpdatePostFeaturedServlet")
public class TopMemEditUpdatePostFeaturedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);

		Integer forumPostNo = Integer.valueOf(request.getParameter("forumPostNo").trim());

		Integer forumPostFeatured = Integer.valueOf(request.getParameter("forumPostFeatured").trim());

		Integer forumNo = Integer.valueOf(request.getParameter("forumNo").trim());

		ForumPostService forumPostSvc = new ForumPostService();

		forumPostSvc.updateMasterPostFeatured(forumPostNo, forumPostFeatured);

		List<ForumPostVO> forumPostPowerVOs = forumPostSvc.findTopMemAllPost(forumNo);

		ForumService forumSvc = new ForumService();
		ForumVO forumPowerVO = forumSvc.getOneForum(forumNo);

		// 將過濾好的VO放置於request scope內
		request.getSession().setAttribute("forumPostPowerVOs", forumPostPowerVOs);
		request.getSession().setAttribute("forumPowerVO", forumPowerVO);

		RequestDispatcher successView = request.getRequestDispatcher("/frontend/forum/topMemOneForumAllPost.jsp");
		successView.forward(request, response);
	}

}

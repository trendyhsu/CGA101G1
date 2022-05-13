package com.forumpostpic.controller;

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
import com.forumpostpic.model.ForumPostPicService;
import com.forumpostpic.model.ForumPostPicVO;

@WebServlet("/forum/forumPostPicDelete")
public class ForumPostPicDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("image/gif");
		
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);

		ForumPostVO forumPostVO = new ForumPostVO();

		ForumPostPicService forumPostPicSvc = new ForumPostPicService();

		Integer forumNo = Integer.valueOf(request.getParameter("forumNo").trim());
		Integer forumPostNo = Integer.valueOf(request.getParameter("forumPostNo").trim());
		Integer forumPostType = Integer.valueOf(request.getParameter("forumPostType").trim());

		String forumPostTitle = request.getParameter("forumPostTitle").trim();

		if (forumPostTitle == null || forumPostTitle.trim().length() == 0) {
			errorMsgs.put("forumPostTitle", "文章標題: 請勿空白");
		} else if (forumPostTitle.trim().length() > 100) {
			errorMsgs.put("forumPostTitle", "文章標題:長度必需在1到100之間");
		}

		String forumPostContent = request.getParameter("forumPostContent").trim();
		if (forumPostContent == null || forumPostContent.trim().length() == 0) {
			errorMsgs.put("forumPostContent", "文章內容: 請勿空白");
		}

		List<ForumPostPicVO> forumPostPicVOs = forumPostPicSvc.getOneForumTotalPostPic(forumPostNo);

		// 錯誤處理回傳forumPostVO
		if (!errorMsgs.isEmpty()) {

			ForumService forumSvc = new ForumService();
			ForumVO forumVO = forumSvc.getOneForum(forumNo);

			forumPostVO.setForumPostState(forumPostType);
			forumPostVO.setForumPostTitle(forumPostTitle);
			forumPostVO.setForumPostContent(forumPostContent);

			request.setAttribute("forumPostVO", forumPostVO);
			request.setAttribute("forumVO", forumVO);// 含有輸入格式錯誤的forumVO物件,也存入req
			request.setAttribute("forumPostPicVOs", forumPostPicVOs);
			RequestDispatcher failureView = request.getRequestDispatcher("/frontend/forum/editForumPost.jsp");
			failureView.forward(request, response);
			return;
		}

		String[] forumPostPicNos = request.getParameterValues("forumPostPicNos");
		if (forumPostPicNos != null) {
			for (String forumPostPicNo : forumPostPicNos) {
				forumPostPicSvc.deleteForumPostPic(new Integer(forumPostPicNo));
			}
		}

		request.setAttribute("forumPostNo", forumPostNo);
		String url = "/forum/forumMemPostOne";
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);

	}

}

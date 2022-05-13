package com.forumpost.controller;

import java.io.BufferedInputStream;
import java.io.IOException;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.forum.model.ForumService;
import com.forum.model.ForumVO;
import com.forumpost.model.ForumPostService;
import com.forumpost.model.ForumPostVO;
import com.forumpostpic.model.ForumPostPicService;
import com.forumpostpic.model.ForumPostPicVO;

@WebServlet("/forum/forumMemPostEditUpdate")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ForumMemPostEditUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);

		/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		ForumPostService forumPostSvc = new ForumPostService();
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

		/*************************** 2.開始新增資料 ***************************************/
		// 修改資料

		forumPostVO = forumPostSvc.updateForumPost(forumPostNo, forumPostType, forumPostTitle, forumPostContent);

		// 將取得圖片資料裝入 List<byte[]> 物件

		Collection<Part> list = request.getParts();

		BufferedInputStream bis = null;
		byte[] forumPostImgs = null;

		// 使用 (is.available() > 1024) 過濾一起帶過來的文字資料
		for (Part part : list) {
			bis = new BufferedInputStream(part.getInputStream());
			if (bis.available() > 10240) {
				forumPostImgs = new byte[bis.available()];
				bis.read(forumPostImgs);
				forumPostPicSvc.addForumPostPic(forumPostNo, forumPostImgs);
			}
		}
		bis.close();

		/*************************** 4.新增完成,準備轉交(Send the Success view) ***********/

		String url = "/forum/selectOnePostAllMsg?forumPostNo=" + forumPostNo;
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);

	}
}

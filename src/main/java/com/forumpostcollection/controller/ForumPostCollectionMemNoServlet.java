package com.forumpostcollection.controller;

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

import com.forumpostcollection.model.ForumPostCollectionService;
import com.forumpostcollection.model.ForumPostCollectionVO;
import com.member.model.MemVO;

@WebServlet("/forum/forumPostCollectionMemNo")
public class ForumPostCollectionMemNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 取得會員編號
		MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
		Integer memNo = memVO.getMemNo();

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);

		ForumPostCollectionService forumPostCollectionSvc = new ForumPostCollectionService();
		List<ForumPostCollectionVO> forumPostCollectionVOs = forumPostCollectionSvc.getOwenrAllPostCollection(memNo);

		// 將過濾好的VO放置於request scope內
		request.setAttribute("forumPostCollectionVOs", forumPostCollectionVOs);

		RequestDispatcher successView = request.getRequestDispatcher("/frontend/forum/myFavouritePost.jsp");
		successView.forward(request, response);
	}

}

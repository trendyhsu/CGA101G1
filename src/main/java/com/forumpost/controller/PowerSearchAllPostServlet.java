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

import com.forumpost.model.ForumPostService;
import com.forumpost.model.ForumPostVO;

@WebServlet("/forum/powerSearchAllPost")
public class PowerSearchAllPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);

		/*************************** 1.將輸入資料轉為Map **********************************/
		// 採用Map<String,String[]> getParameterMap()的方法
		// 注意:an immutable java.util.Map
		Map<String, String[]> map = request.getParameterMap();

		/*************************** 2.開始複合查詢 ***************************************/
		ForumPostService forumPostSvc = new ForumPostService();
		List<ForumPostVO> powerList = forumPostSvc.getAllPowerSearch(map);

		/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
		request.getSession().setAttribute("powerList", powerList); // 資料庫取出的list物件,存入request
		RequestDispatcher successView = request.getRequestDispatcher("/frontend/forum/powerSearchAllPost.jsp"); // 成功轉交powerSearchAllPost
		successView.forward(request, response);
	}

}

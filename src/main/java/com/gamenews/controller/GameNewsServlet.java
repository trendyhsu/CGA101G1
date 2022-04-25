package com.gamenews.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fqkeyword.model.FQKeyWordService;
import com.gamenews.model.GameNewsService;
import com.gamenews.model.GameNewsVO;

public class GameNewsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		String action = req.getParameter("action");

		if ("AllQuery".equals(action)) {
			/*************************** 1.將輸入資料轉為Map **********************************/
			// 採用Map<String,String[]> getParameterMap()的方法
			// 注意:an immutable java.util.Map
			Map<String, String[]> map = req.getParameterMap();
			/*************************** 2.開始複合查詢 ***************************************/
			GameNewsService gnSvc = new GameNewsService();
			List<GameNewsVO> list = gnSvc.getAll(map);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("AllQuery", list);
			RequestDispatcher successView = req.getRequestDispatcher("/backend/news/GameNews-table.jsp");
			successView.forward(req, resp);
		}

		if ("Insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer gamePlatformNo = null;
				try {
					gamePlatformNo = Integer.valueOf(req.getParameter("gamePlatformNo").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("平台編號請填數字");
				}

				Integer managerNo = null;
				try {
					managerNo = Integer.valueOf(req.getParameter("managerNo"));
				} catch (NumberFormatException e) {
					errorMsgs.add("編輯者編號請填數字");
				}

				String gameNewsTitle = req.getParameter("gameNewsTitle");
				if (gameNewsTitle.trim().length() == 0 || gameNewsTitle == null) {
					errorMsgs.add("新聞標題不可為空白");
				}

				String gameNewsContent = req.getParameter("gameNewsContent");

				byte[] gameNewsPic = null;
				try {
					gameNewsPic = req.getParameter("gameNewsPic").getBytes();
				} catch (Exception e) {
					errorMsgs.add("此圖片無法上傳");
				}

				GameNewsVO gameNewsVO = new GameNewsVO();
				gameNewsVO.setGamePlatformNo(gamePlatformNo);
				gameNewsVO.setManagerNo(managerNo);
				gameNewsVO.setGameNewsTitle(gameNewsTitle);
				gameNewsVO.setGameNewsContent(gameNewsContent);
				gameNewsVO.setGameNewsPic(gameNewsPic);
				/*************************** 2.開始新增資料 ***************************************/
				GameNewsService gnSvc = new GameNewsService();
				gnSvc.addGameNews(gamePlatformNo, managerNo, gameNewsTitle, gameNewsContent, gameNewsPic);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				String url = "/gameNews/listAllNews.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, resp);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		if ("delete".equals(action)) {
			
		}
		
		
		
		

	}

}

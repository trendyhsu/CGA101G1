package com.gamenews.controller;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fqkeyword.model.FQKeyWordService;
import com.fqkeyword.model.FQKeyWordVO;
import com.gamenews.model.GameNewsService;
import com.gamenews.model.GameNewsVO;


@WebServlet("/gamenews/gamenews.do")
@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)
public class GameNewsServlet extends HttpServlet {

	/*
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

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer gamePlatformNo = Integer.valueOf(req.getParameter("gamePlatformNo").trim());
				Integer managerNo = Integer.valueOf(req.getParameter("managerNo"));
				String gameNewsTitle = req.getParameter("gameNewsTitle");
				String gameNewsContent = req.getParameter("gameNewsContent");
				// 將取得圖片資料
				byte[] gameNewsPic = null;
				Part part = req.getPart("gameNewsPic");
				gameNewsPic = part.getInputStream().readAllBytes();
				if(gameNewsPic.length==0) gameNewsPic = null;
			
				GameNewsVO gameNewsVO = new GameNewsVO();
				gameNewsVO.setGamePlatformNo(gamePlatformNo);
				gameNewsVO.setManagerNo(managerNo);
				gameNewsVO.setGameNewsTitle(gameNewsTitle);
				gameNewsVO.setGameNewsContent(gameNewsContent);
				gameNewsVO.setGameNewsPic(gameNewsPic);
				
				/*************************** 2.開始新增資料 ***************************************/
				if (gameNewsTitle.trim().length() != 0 || gameNewsTitle != null) {
					GameNewsService gnSvc = new GameNewsService();
					gnSvc.addGameNews(gamePlatformNo, managerNo, gameNewsTitle, gameNewsContent, gameNewsPic);
					
				}
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/backend/news/GameNews-listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, resp);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		if ("delete".equals(action)) {
			
			
			/***************************1.接收請求參數***************************************/
			Integer gameNewsNo = Integer.parseInt(req.getParameter("gameNewsNo"));

			/***************************2.開始刪除資料***************************************/
			GameNewsService gnSvc = new GameNewsService();
			gnSvc.deleteGameNews(gameNewsNo);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/
			
			RequestDispatcher successView = req.getRequestDispatcher("/backend/news/GameNews-listAll.jsp");
			successView.forward(req, resp);
			
		}
		
		if("get_one_to_update".equals(action)) {
			//1.取得參數
			Integer gameNewsNo = Integer.valueOf(req.getParameter("gameNewsNo"));
			//2.取得一筆資料，即將轉送去修改
			GameNewsService gnSvc = new GameNewsService();
			GameNewsVO gameNewsVO = gnSvc.getOne(gameNewsNo);
			//3.轉送修改頁面
			req.setAttribute("gameNewsVO", gameNewsVO);
			RequestDispatcher successView = req.getRequestDispatcher("/backend/news/GameNews-update.jsp");	
			successView.forward(req, resp);
		}
		
		if("update".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*--------------------1.取得參數-------------------*/
			Integer gameNewsNo = Integer.valueOf(req.getParameter("gameNewsNo"));
			Integer gamePlatformNo = Integer.valueOf(req.getParameter("gamePlatformNo"));
			Integer managerNo = Integer.valueOf(req.getParameter("managerNo"));
			String gameNewsTitle = req.getParameter("gameNewsTitle");
			if ( gameNewsTitle == null ||  gameNewsTitle.trim().length() == 0) {
				errorMsgs.put("gameNewsTitle","【標題】請勿空白");
			} 
			
			String gameNewsContent = req.getParameter("gameNewsContent");
			if (gameNewsContent == null || gameNewsContent.trim().length() == 0) {
				errorMsgs.put("gameNewsContent","【內容】請勿空白");
			}
			
			// 圖片
			byte[] gameNewsPic = null;
			Part part = req.getPart("gameNewsPic");
			gameNewsPic = part.getInputStream().readAllBytes();
			if(gameNewsPic.length == 0) {
				gameNewsPic = null;
			}
			
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("backend/news/GameNews-update.jsp");
				failureView.forward(req, resp);
				return; //程式中斷
			}
			/*--------------------2.執行修改-------------------*/
			GameNewsService gnSvc = new GameNewsService();
			gnSvc.updateGameNews(gamePlatformNo, managerNo, gameNewsTitle, gameNewsContent, gameNewsPic, gameNewsNo);
			/*--------------------3.成功返回-------------------*/
			String url = "/backend/news/GameNews-listAll.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, resp);
		
		}
		
		

	}

}

package com.gamecompany.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamecompany.model.GameCompanyService;
import com.gamecompany.model.GameCompanyVO;
import com.google.gson.Gson;

/**
 * Servlet implementation class GameTypeEdit
 */
@WebServlet("/gamecompany/gameCompanyEdit")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class GameCompanyEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接資料
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		GameCompanyService gameCompanyService =new GameCompanyService();
		
		Gson gson = new Gson();
		String json = gson.toJson(gameCompanyService.getAll());
		out.print(json);
		System.out.println(json);
		
		//
		
		List<String> errorMsgs = new LinkedList<String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);

		try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			//種類編號
			Integer gameCompanyNo = null;
			try {
				gameCompanyNo = Integer.valueOf(request.getParameter("gameCompanyNo").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("編號需為數字");
			}
			// 種類名稱
			GameCompanyVO gameCompanyVO = new GameCompanyVO();
			String gameCompanyName = request.getParameter("gameCompanyName");
			String gameCompanyReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_:)(\\-\\)]{1,30}$";
			if (gameCompanyName == null || gameCompanyName.trim().length() == 0) {
				errorMsgs.add("名稱: 請勿空白");
			} else if (!gameCompanyName.trim().matches(gameCompanyReg)) {
				errorMsgs.add("名稱: 只能包含中文、英文大小寫、數字和底線及冒號 , 且長度須在1到30之間");
			}
			System.out.println(gameCompanyName);

			// 將取得資料裝入 bidProductVO 物件
			gameCompanyVO.setGameCompanyNo(gameCompanyNo);
			gameCompanyVO.setGameCompanyName(gameCompanyName);

			// 回傳錯誤訊息
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("gameCompanyVO", gameCompanyVO);
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/gamecompany/editGameCompany.jsp");
				failureView.forward(request, response);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			GameCompanyService gameCompanyService2 = new GameCompanyService();
			gameCompanyService2.updateGameCompany(gameCompanyName,gameCompanyNo);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			request.setAttribute("gameCompanyVO", gameCompanyVO); // 資料庫update成功後,正確的的bidProductVO物件,存入request
			String url = "/backend/gamecompany/GetAllGameCompany.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneBid.jsp
			successView.forward(request, response);

			/*************************** 其他可能的錯誤處理 *************************************/
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add("修改資料失敗:" + e.getMessage());
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/gamecompany/editGameCompany.jsp");
			failureView.forward(request, response);
		}
		
//		List<String> errorMsgs = new LinkedList<String>();
//		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
//		request.setAttribute("errorMsgs", errorMsgs);
//
//		try {
//			// 1.接收請求參數
//			Integer gameTypeNo = Integer.valueOf(request.getParameter("gameTypeNo"));
//
//			// 2.開始查詢資料
//			GameTypeService gameTypeService = new GameTypeService();
//			GameTypeVO gameTypeVO = gameTypeService.getOneGameType(gameTypeNo);
//
//			// 3.查詢完成,準備轉交(Send the Success view)
//			// 從資料庫取 bidProductVO 物件, 存入 request 中
//			request.setAttribute("gameTypeVO", gameTypeVO);
//			String url = "/backend/gametype/editGameType.jsp";
//			// 成功轉交 editBid.jsp
//			RequestDispatcher successView = request.getRequestDispatcher(url);
//			successView.forward(request, response);
//			
//			// 其他可能的錯誤處理
//		} catch (Exception e) {
//			String url = "/backend/gametype/getAllGameType.jsp";
//			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//			RequestDispatcher failureView = request.getRequestDispatcher(url);
//			failureView.forward(request, response);
////		}
//
	}

}

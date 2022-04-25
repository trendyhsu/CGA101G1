package com.gametype.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.gametype.model.GameTypeService;
import com.gametype.model.GameTypeVO;

/**
 * Servlet implementation class GameTypeInsert
 */
@WebServlet("/gametype/gameTypeInsert")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class GameTypeInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		request.setAttribute("errorMsgs", errorMsgs);

		try {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			GameTypeVO gameTypeVO = new GameTypeVO();

		
			// 判斷名稱
			String gameTypeName = request.getParameter("gameTypeName");
			String bidNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_:)(\\-\\)]{1,30}$";
			if (gameTypeName == null || gameTypeName.trim().length() == 0) {
				errorMsgs.add("名稱: 請勿空白");
			} else if (!gameTypeName.trim().matches(bidNameReg)) {
				errorMsgs.add("名稱: 只能包含中文、英文大小寫、數字和底線及冒號 , 且長度須在1到30之間");
			}
			System.out.println(gameTypeName);

			// 將取得資料裝入 bidProductVO 物件
			gameTypeVO.setGameTypeName(gameTypeName);
			
			// 將取得圖片資料裝入 List<byte[]> 物件 日後有機會優化用
//			Collection<Part> list = request.getParts();
//			List<byte[]> picList = new ArrayList<byte[]>();
//			InputStream is = null;
//			BufferedInputStream bis = null;
//			byte[] bidProdPicContent = null;
//
//			// 使用 (is.available() > 1024) 過濾一起帶過來的文字資料
//			for (Part part : list) {
//				bis = new BufferedInputStream(part.getInputStream());
//				if (bis.available() > 1024) {
//					bidProdPicContent = new byte[bis.available()];
//					bis.read(bidProdPicContent);
//					picList.add(bidProdPicContent);
//				}
//			}

			// 錯誤處理回傳bidProductVO
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("gameTypeVO", gameTypeVO); // 含有輸入格式錯誤的bidProductVO物件,也存入req
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/gametype/addGameType.jsp");
				failureView.forward(request, response);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			// 新增資料 並回傳新編號
			GameTypeService gameTypeService = new GameTypeService();
			gameTypeService.addGameType(gameTypeName);

			// 新增圖片資料 日後可優化用
//			BidPicService bidPicSvc = new BidPicService();
//			for (int i = 0; i < picList.size(); i++) {
//				BidPicVO bidPicVO = new BidPicVO();
//				bidPicVO.setBidProductNo(nextBidProductNo);
//				bidPicVO.setBidProdPicContent(picList.get(i));
//				bidPicSvc.addBidPic(bidPicVO);
//			}

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/backend/gametype/GetAllGameType.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAll.jsp
			successView.forward(request, response);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/gametype/addGameType.jsp");
			failureView.forward(request, response);
		}
	}
}

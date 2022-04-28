package com.gamecompany.controller;

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

import com.gamecompany.model.GameCompanyService;
import com.gamecompany.model.GameCompanyVO;
import com.gametype.model.GameTypeService;
import com.gametype.model.GameTypeVO;

/**
 * Servlet implementation class GameTypeInsert
 */
@WebServlet("/gamecompany/gameCompanyInsert")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class GameCompanyInsert extends HttpServlet {
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
			GameCompanyVO gameCompanyVO = new GameCompanyVO();

		
			// 判斷名稱
			String gameCompanyName = request.getParameter("gameCompanyName");
			String gameCompanyReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_:)(\\-\\)]{1,20}$";
			if (gameCompanyName == null || gameCompanyName.trim().length() == 0) {
				errorMsgs.add("名稱: 請勿空白");
			} else if (!gameCompanyName.trim().matches(gameCompanyReg)) {
				errorMsgs.add("名稱: 只能包含中文、英文大小寫、數字和底線及冒號 , 且長度須在1到20之間");
			}
			System.out.println(gameCompanyName);

			// 將取得資料裝入 bidProductVO 物件
			gameCompanyVO.setGameCompanyName(gameCompanyName);
			
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
				request.setAttribute("gameCompanyVO", gameCompanyVO); // 含有輸入格式錯誤的bidProductVO物件,也存入req
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/gameCompany/addGameCompany.jsp");
				failureView.forward(request, response);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			// 新增資料 並回傳新編號
			GameCompanyService gameCompanyService = new GameCompanyService();
			gameCompanyService.addGameCompany(gameCompanyName);

			// 新增圖片資料 日後可優化用
//			BidPicService bidPicSvc = new BidPicService();
//			for (int i = 0; i < picList.size(); i++) {
//				BidPicVO bidPicVO = new BidPicVO();
//				bidPicVO.setBidProductNo(nextBidProductNo);
//				bidPicVO.setBidProdPicContent(picList.get(i));
//				bidPicSvc.addBidPic(bidPicVO);
//			}

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/backend/gamecompany/GetAllGameCompany.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAll.jsp
			successView.forward(request, response);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/gamecompany/addGameCompany.jsp");
			failureView.forward(request, response);
		}
	}
}

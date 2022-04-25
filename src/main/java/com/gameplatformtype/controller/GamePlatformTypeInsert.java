package com.gameplatformtype.controller;

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

import com.gameplatformtype.model.GamePlatformTypeService;
import com.gameplatformtype.model.GamePlatformTypeVO;
import com.gametype.model.GameTypeService;
import com.gametype.model.GameTypeVO;

/**
 * Servlet implementation class GameTypeInsert
 */
@WebServlet("/gameplatformtype/gamePlatformTypeInsert")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class GamePlatformTypeInsert extends HttpServlet {
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
			GamePlatformTypeVO gamePlatformTypeVO = new GamePlatformTypeVO();

		
			// 判斷商品名稱
			String gamePlatformName = request.getParameter("gamePlatformName");
			String bidNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_:)(\\-\\)]{1,30}$";
			if (gamePlatformName == null || gamePlatformName.trim().length() == 0) {
				errorMsgs.add("商品名稱: 請勿空白");
			} else if (!gamePlatformName.trim().matches(bidNameReg)) {
				errorMsgs.add("商品名稱: 只能包含中文、英文大小寫、數字和底線及冒號 , 且長度須在1到30之間");
			}
			System.out.println(gamePlatformName);

			// 將取得資料裝入 bidProductVO 物件
			gamePlatformTypeVO.setGamePlatformName(gamePlatformName);
			
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
				request.setAttribute("gamePlatformTypeVO", gamePlatformTypeVO); // 含有輸入格式錯誤的bidProductVO物件,也存入req
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/gameplatformtype/addGamePlatformType.jsp");
				failureView.forward(request, response);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			// 新增競標商品資料 並回傳新商品編號
			GamePlatformTypeService gamePlatformTypeSer = new GamePlatformTypeService();
			gamePlatformTypeSer.addGamePlatformType(gamePlatformName);

			// 新增圖片資料 日後可優化用
//			BidPicService bidPicSvc = new BidPicService();
//			for (int i = 0; i < picList.size(); i++) {
//				BidPicVO bidPicVO = new BidPicVO();
//				bidPicVO.setBidProductNo(nextBidProductNo);
//				bidPicVO.setBidProdPicContent(picList.get(i));
//				bidPicSvc.addBidPic(bidPicVO);
//			}

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/backend/gameplatformtype/GetAllGamePlatformType.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllBid.jsp
			successView.forward(request, response);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/gameplatformtype/addGamePlatformType.jsp");
			failureView.forward(request, response);
		}
	}
}

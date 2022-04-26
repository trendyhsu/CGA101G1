package com.manager.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import com.manager.model.ManagerService;
import com.manager.model.ManagerVO;

/**
 * Servlet implementation class GameTypeInsert
 */
@WebServlet("/manager/managerInsert")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ManagerInsert extends HttpServlet {
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
			ManagerVO managerVO = new ManagerVO();

			// 判斷帳號
			String managerAccount = request.getParameter("managerAccount");
			String managerAccountReg = "^[(a-zA-Z0-9)(\\-\\)]{6,30}$";
			if (managerAccount == null || managerAccount.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			} else if (!managerAccount.trim().matches(managerAccountReg)) {
				errorMsgs.add("帳號只能英文大小寫或數字, 且長度須在6到30之間");
			}
			System.out.println(managerAccount);
			// 判斷密碼
			String managerPassword = request.getParameter("managerPassword");
			String managerPasswordReg = "^[(a-zA-Z0-9)(\\-\\)]{8,30}$";
			if (managerPassword == null || managerPassword.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			} else if (!managerPassword.trim().matches(managerPasswordReg)) {
				errorMsgs.add("密碼只能英文大小寫或數字, 且長度須在8到30之間");
			}
			System.out.println(managerPassword);
			// 判斷名字
			String managerName = request.getParameter("managerName");
			String managerNameReg = "^[(\u4e00-\u9fa5)(\\-\\)]{1,30}$";
			if (managerName == null || managerName.trim().length() == 0) {
				errorMsgs.add("名字請勿空白");
			} else if (!managerName.trim().matches(managerNameReg)) {
				errorMsgs.add("名字只能包含中文、英文大小寫 , 且長度須在1到30之間");
			}
			System.out.println(managerName);
			// 判斷電話
			String managerPhone = request.getParameter("managerPhone");
			String managerPhoneReg = "^[(0-9)(\\-\\)]{10,10}$";
			if (!managerPhone.trim().matches(managerPhoneReg)) {
				errorMsgs.add("電話只能數字 , 且長度須在10之間");
			}
			System.out.println(managerPhone);
			// 將取得圖片資料裝入 List<byte[]> 物件
			Collection<Part> list = request.getParts();
			List<byte[]> picList = new ArrayList<byte[]>();
			InputStream is = null;
			BufferedInputStream mis = null;
			byte[] myManagerPic = null;
			// 使用 (is.available() > 1024) 過濾一起帶過來的文字資料
			for (Part part : list) {
				mis = new BufferedInputStream(part.getInputStream());
				if (mis.available() > 1024) {
					myManagerPic = new byte[mis.available()];
					mis.read(myManagerPic);
					picList.add(myManagerPic);
				}
			}
			// 設定State為1
			Integer managerState = new Integer(1);

			// 將取得資料裝入 bidProductVO 物件
			managerVO.setManagerAccount(managerAccount);
			managerVO.setManagerPassword(managerPassword);
			managerVO.setManagerName(managerName);
			managerVO.setManagerPhone(managerPhone);
			managerVO.setMyManagerPic(myManagerPic);
			managerVO.setManagerState(managerState);

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
				request.setAttribute("managerVO", managerVO); // 含有輸入格式錯誤的bidProductVO物件,也存入req
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/manager/addManager.jsp");
				failureView.forward(request, response);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			// 新增資料 並回傳新編號
			ManagerService managerService = new ManagerService();
			managerService.addManager(managerAccount,managerPassword,managerName,managerPhone,myManagerPic,managerState);

			// 新增圖片資料 日後可優化用
//			BidPicService bidPicSvc = new BidPicService();
//			for (int i = 0; i < picList.size(); i++) {
//				BidPicVO bidPicVO = new BidPicVO();
//				bidPicVO.setBidProductNo(nextBidProductNo);
//				bidPicVO.setBidProdPicContent(picList.get(i));
//				bidPicSvc.addBidPic(bidPicVO);
//			}

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/backend/manager/getAllMamager.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAll.jsp
			successView.forward(request, response);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/manager/addManager.jsp");
			failureView.forward(request, response);
		}
	}
}

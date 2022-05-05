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

@WebServlet("/manager/managerEditUpdate")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ManagerEditUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	// 處理來自managerEdit.jsp 送出修改請求

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		List<String> errorMsgs = new LinkedList<String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);
		try {		
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/	
			ManagerVO managerVO = new ManagerVO();
			ManagerService managerService = new ManagerService();
			// 判斷編號
			Integer managerNo = null;
			try {
				managerNo = Integer.valueOf(request.getParameter("managerNo").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("競標需為數字");
			}
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
			String managerNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)(\\-\\)]{1,30}$";
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
				is = part.getInputStream();
				mis = new BufferedInputStream(is);
				if (mis.available() > 1024) {
					myManagerPic = new byte[mis.available()];
					mis.read(myManagerPic);
					picList.add(myManagerPic);
				}
			}
			// 判斷管理員狀態
			Integer managerState = null;
			try {
				managerState = Integer.valueOf(request.getParameter("managerState").trim());
			} catch (NumberFormatException e) {
				managerState = 0;
				errorMsgs.add("狀態應為數字(0:在職 1:離職");
			}
			if (managerState < 0 || managerState > 2) {
				managerState = 0;
				errorMsgs.add("狀態輸入錯誤(0:在職 1:離職");
			}
			managerVO.setManagerNo(managerNo);
			managerVO.setManagerAccount(managerAccount);
			managerVO.setManagerPassword(managerPassword);
			managerVO.setManagerName(managerName);
			managerVO.setManagerPhone(managerPhone);
			managerVO.setMyManagerPic(myManagerPic);
			managerVO.setManagerState(managerState);
			// 回傳錯誤訊息
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("managerVO", managerVO);
				RequestDispatcher failureView = request.getRequestDispatcher("/backend/manager/editManager.jsp");
				failureView.forward(request, response);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			managerService.updateManager(managerNo,managerAccount,managerPassword,managerName,managerPhone,
					myManagerPic,managerState);

			/*************************** 3.修改完成,準備轉交(Send the Success vi	ew) *************/
			for (int i = 0; i < picList.size(); i++) {
				managerService.addManagerPic(managerNo, picList.get(i));
			}
			managerVO = managerService.getOneManager(managerNo);
			request.setAttribute("managerVO", managerVO); // 資料庫update成功後,正確的的bidProductVO物件,存入request
			String url = "/backend/manager/getAllManager.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneBid.jsp
			successView.forward(request, response);

			/*************************** 其他可能的錯誤處理 *************************************/
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add("修改資料失敗:" + e.getMessage());
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/manager/editManager.jsp");
			failureView.forward(request, response);
		}
	}

}

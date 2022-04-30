package com.manager.controller;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.manager.model.ManagerService;
import com.manager.model.ManagerVO;

@WebServlet("/manager/managerEdit")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ManagerEdit extends HttpServlet {
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
			/*************************** 1.接收參數  **********************/
			Integer managerNo = Integer.valueOf(request.getParameter("managerNo"));

			// 2.開始查詢資料
			ManagerService managerService = new ManagerService();
			ManagerVO managerVO = managerService.getOneManager(managerNo);

			// 3.查詢完成,準備轉交(Send the Success view)
			// 從資料庫取 managerVO 物件, 存入 request 中
			request.setAttribute("managerVO", managerVO);
			
			String url = "/backend/manager/editManager.jsp";
			// 成功轉交 editManager.jsp
			RequestDispatcher successView = request.getRequestDispatcher(url);
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

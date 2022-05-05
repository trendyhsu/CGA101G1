package com.managerauth.controller;

import java.io.IOException;
import java.util.Iterator;
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
import com.managerauth.model.ManagerAuthService;
import com.managerauth.model.ManagerAuthVO;
import com.managerauthrizationfunction.model.ManagerAuthrizationFunctionService;
import com.managerauthrizationfunction.model.ManagerAuthrizationFunctionVO;

/**
 * Servlet implementation class ManagerEditAuth
 */
@WebServlet("/managerauth/managerEditAuth")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ManagerEditAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	// 處理來自authManager.jsp 送出修改請求
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
			
			ManagerAuthService managerAuthService = new ManagerAuthService();
			List<ManagerAuthVO> lists = managerAuthService.getFunction(managerNo);
			
			ManagerAuthrizationFunctionService managerAuthrizationFunctionService = new ManagerAuthrizationFunctionService();
			List<ManagerAuthrizationFunctionVO> lists2 = managerAuthrizationFunctionService.getAll();
			List<Integer> lists3 = null;
			int[] arr = null;
			int[] arr2 = null;
			
			for(int i=0; i<lists.size(); i++) {
				arr[i] = lists.get(i).getManagerAuthrizationFunctionNo();
			}
			for(int i=0; i<lists2.size(); i++) {
				arr2[i]= lists2.get(i).getManagerAuthrizationFunctionNo();
			}
			
 			for(int i=0; i<lists2.size(); i++) {
 				for (int j = 0; j<lists.size(); j++) {
					arr[j] = lists.get(j).getManagerAuthrizationFunctionNo();
					if(arr2[i] == arr[j]) {
						lists3.add(1);
					}else {
						lists3.add(0);
					}
 				}
 				System.out.println(lists3);
			}
			// 3.查詢完成,準備轉交(Send the Success view)
			// 從資料庫取 managerVO 物件, 存入 request 中
			request.setAttribute("managerVO", managerVO);
			request.setAttribute("list", list);
			String url = "/backend/manager/authManager.jsp";
			// 成功轉交 authManager.jsp
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			
			
			/*************************** 其他可能的錯誤處理 *************************************/
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add("修改資料失敗:" + e.getMessage());
			RequestDispatcher failureView = request.getRequestDispatcher("/backend/manager/authManager.jsp");
			failureView.forward(request, response);
		}
	}
	

}

package com.managerauth.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	// 處理來自authManager.jsp 送出修改請求
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		List<String> errorMsgs = new LinkedList<String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);

		try {
			/*************************** 1.接收參數 **********************/
			Integer managerNo = Integer.valueOf(request.getParameter("managerNo"));

			// 2.開始查詢資料
			ManagerService managerService = new ManagerService();
			ManagerVO managerVO = managerService.getOneManager(managerNo);

			// 全部的
			ManagerAuthrizationFunctionService managerAuthrizationFunctionService = new ManagerAuthrizationFunctionService();
			List<ManagerAuthrizationFunctionVO> auList = managerAuthrizationFunctionService.getAll();
			// System.out.println(auList.size());
			// 會員有的
			ManagerAuthService managerAuthService = new ManagerAuthService();
			List<ManagerAuthVO> managerList = managerAuthService.getFunction(managerNo);
			// System.out.println(managerList.size());

			Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

			// 會員跑回圈
			  for (int j = 0; j < managerList.size(); j++) {
			//   System.out.println(managerAuthVO.getManagerAuthrizationFunctionNo());
			   // 全部的迴圈
			   for (int i = 0; i < auList.size(); i++) {
			    if (managerList.get(j).getManagerAuthrizationFunctionNo()
			      + 0 == auList.get(i).getManagerAuthrizationFunctionNo() + 0) {
			     map.put(auList.get(i).getManagerAuthrizationFunctionNo(), 1);
			    }

			   }

			  }
			  System.out.println(map);
			  // 補 0 進去
			  for (int i = 0; i < auList.size(); i++) {
			   if (map.get(auList.get(i).getManagerAuthrizationFunctionNo()) == null) {
			    map.put(auList.get(i).getManagerAuthrizationFunctionNo(), 0);
			   }

			  }
			 
			
			
			HttpSession session = request.getSession();
			request.setAttribute("managerVO", managerVO);
			session.setAttribute("map", map);
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

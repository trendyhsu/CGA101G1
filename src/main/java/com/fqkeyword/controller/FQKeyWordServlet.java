package com.fqkeyword.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fqkeyword.model.FQKeyWordDAOImpl;
import com.fqkeyword.model.FQKeyWordService;
import com.fqkeyword.model.FQKeyWordVO;



@WebServlet("/FQKeyWordServlet")
public class FQKeyWordServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		String action = req.getParameter("action");
//		FQKeyWordDAOImpl dao = new FQKeyWordDAOImpl();

		
		if("AllQuery".equals(action)) {
			/***************************1.將輸入資料轉為Map**********************************/ 
			//採用Map<String,String[]> getParameterMap()的方法 
			//注意:an immutable java.util.Map 
			Map<String, String[]> map = req.getParameterMap();
			/***************************2.開始複合查詢***************************************/
			FQKeyWordService fqSvc = new FQKeyWordService();
			List<FQKeyWordVO> list = fqSvc.getAll(map);
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("AllQuery", list);
			RequestDispatcher successView = req.getRequestDispatcher("/frontend/Test02/KeyWord-table.jsp");
			successView.forward(req, resp);
		}
		
		
		if("Insert".equals(action)) {
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String fqKeyWordContent = req.getParameter("fqKeyWordContent");
			String answerContent = req.getParameter("answerContent");
			if(fqKeyWordContent.trim().length()!=0) {
				/***************************2.開始新增資料***************************************/
				FQKeyWordService fqSvc = new FQKeyWordService();
//				FQKeyWordVO fqKeyWordVO = new FQKeyWordVO();
				fqSvc.addKeyWord(fqKeyWordContent, answerContent);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/

			}
		}
		
		if("delete".equals(action)) {
			String requestURL = req.getParameter("requestURL");
			System.out.println(requestURL);
			/***************************1.接收請求參數***************************************/
			Integer fqKeyWordNo = Integer.parseInt(req.getParameter("fqKeyWordNo"));
			Map<String, String[]> map = req.getParameterMap();
			/***************************2.開始刪除資料***************************************/
			FQKeyWordService fqSvc = new FQKeyWordService();
			fqSvc.deleteKeyWord(fqKeyWordNo);
			List<FQKeyWordVO> list = fqSvc.getAll(map);
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/
			req.setAttribute("AllQuery", list);
			RequestDispatcher successView = req.getRequestDispatcher(requestURL);
			successView.forward(req, resp);
			
		
		}
	}
	
	

}

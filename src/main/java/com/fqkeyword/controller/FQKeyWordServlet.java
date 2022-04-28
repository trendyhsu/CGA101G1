package com.fqkeyword.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fqkeyword.model.FQKeyWordService;
import com.fqkeyword.model.FQKeyWordVO;



@WebServlet("/fqkeyword/fqkeyword.do")
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
			RequestDispatcher successView = req.getRequestDispatcher("/backend/fq/KeyWord-table.jsp");
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
				RequestDispatcher successView = req.getRequestDispatcher("/backend/fq/KeyWord-listAll.jsp");
				successView.forward(req, resp);
				
			}
		}
		
		if("delete".equals(action)) {
			String requestURL = req.getParameter("requestURL");
			System.out.println(requestURL);
			/***************************1.接收請求參數***************************************/
			Integer fqKeyWordNo = Integer.parseInt(req.getParameter("fqKeyWordNo"));
		
			/***************************2.開始刪除資料***************************************/
			FQKeyWordService fqSvc = new FQKeyWordService();
			fqSvc.deleteKeyWord(fqKeyWordNo);
		
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/
			
			RequestDispatcher successView = req.getRequestDispatcher("/backend/fq/KeyWord-listAll.jsp");
			successView.forward(req, resp);
			
		
		}
		
		if ("get_one_to_update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer fqKeyWordNo = Integer.valueOf(req.getParameter("fqKeyWordNo"));
				
				/***************************2.開始查詢資料****************************************/
				FQKeyWordService fqSvc = new FQKeyWordService();
				FQKeyWordVO fqKeyWordVO = fqSvc.getOne(fqKeyWordNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				HttpSession session = req.getSession();
				req.setAttribute("fqKeyWordVO", fqKeyWordVO);      
				String url = "/backend/fq/KeyWord-update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交到修改畫面
				successView.forward(req, resp);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("無法取得要修改的資料",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/fq/KeyWord-listAll.jsp");
				failureView.forward(req, resp);
			}
		}
		
		if("update".equals(action)) {
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer fqKeyWordNo = Integer.valueOf(req.getParameter("fqKeyWordNo").trim());
				String fqKeyWordContent = req.getParameter("fqKeyWordContent");
				
				if (fqKeyWordContent == null || fqKeyWordContent.trim().length() == 0) {
					errorMsgs.put("fqKeyWordContent","【關鍵字】請勿空白");
				} 
				
				String answerContent = req.getParameter("answerContent").trim();
				if (answerContent == null || answerContent.trim().length() == 0) {
					errorMsgs.put("answerContent","【回應內容】請勿空白");
				}
												
				// Send the use back to the form, if there were errors
				String location = req.getParameter("requestURL");
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(location);
					failureView.forward(req, resp);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				FQKeyWordService fqSvc = new FQKeyWordService();
//				FQKeyWordVO fqKeyWordVO = fqSvc.updateKeyWord(fqKeyWordNo, fqKeyWordContent, answerContent);
				fqSvc.updateKeyWord(fqKeyWordNo, fqKeyWordContent, answerContent);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute ("FQKeyWordVO", fqKeyWordVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/fq/KeyWord-listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, resp);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.put("修改資料失敗",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/fq/KeyWord-update.jsp");
				failureView.forward(req, resp);
			}
			
			
		}
	}
	
	

}

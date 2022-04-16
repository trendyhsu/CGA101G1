package com.bidrecord.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidrecord.model.BidRecordService;
import com.bidrecord.model.BidRecordVO;

@WebServlet("/BidRecordGetOneByBidProductNo")
public class BidRecordGetOneByBidProductNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer bidProductNo = null;
				try {
					bidProductNo = Integer.parseInt(request.getParameter("bidProductNo").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("競標商品編號需為數字");
				}
				System.out.println(bidProductNo);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/backend/bid/listAllBid.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BidRecordService bidRecordSvc = new BidRecordService();
				List<BidRecordVO> bidRecordVO = bidRecordSvc.getAllBidRecordByBidProductNo(bidProductNo);
				if (bidRecordVO == null) {
					errorMsgs.add("查無該商品競標資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/backend/bid/listAllBid.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				request.setAttribute("bidRecordVO", bidRecordVO); // 資料庫取出的bidRecordVO物件,存入request
				String url = "/backend/bid/listAllBidRecordByBidProductNo.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneBid.jsp
				successView.forward(request, response);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage() + "這裡是bidrecordservlet");
				RequestDispatcher failureView = request
						.getRequestDispatcher("/backend/bid/listAllBid.jsp");
				failureView.forward(request, response);
			}
		}
	}
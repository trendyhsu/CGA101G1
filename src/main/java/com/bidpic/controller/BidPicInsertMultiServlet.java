package com.bidpic.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import com.bidpic.model.BidPicService;
import com.bidpic.model.BidPicVO;

@WebServlet("/bid/bidPicInsertMulti")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class BidPicInsertMultiServlet extends HttpServlet {
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
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			BidPicService bidPicSvc = new BidPicService();
			BidPicVO bidPicVO = new BidPicVO();
			Integer bidProductNo = Integer.valueOf(request.getParameter("bidProductNo").trim());
			
			System.out.println(bidProductNo);
			
			Collection<Part> list = request.getParts();
			
			BufferedInputStream bis = null;
			byte[] bidProdPicContent = null;
			
			System.out.println("總共上傳的圖片張數：" + list.size());
			
			for (Part part : list) {
				bis = new BufferedInputStream(part.getInputStream());
				if (bis.available() > 1024) {
					bidProdPicContent = new byte[bis.available()];
					bis.read(bidProdPicContent);
					bidPicVO = bidPicSvc.addBidPic(bidProductNo, bidProdPicContent);
				}
			}
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("bidPicVO", bidPicVO); // 含有輸入格式錯誤的bidPicVO物件,也存入req
				RequestDispatcher failureView = request
						.getRequestDispatcher("/backend/bid/listAllBid.jsp");
				failureView.forward(request, response);
				return;
			}
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/backend/bid/listAllBid.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllBidPic.jsp
			successView.forward(request, response);
			
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = request
					.getRequestDispatcher("/backend/bid/listAllBid.jsp");
			failureView.forward(request, response);
		}
		
	}

}

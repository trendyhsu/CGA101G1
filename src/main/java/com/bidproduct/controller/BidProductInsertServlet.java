package com.bidproduct.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidproduct.model.BidProductService;
import com.bidproduct.model.BidProductVO;

@WebServlet("/BidProductInsertServlet")
public class BidProductInsertServlet extends HttpServlet {
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
//			Integer bidProdNo = new Integer(req.getParameter("bidProdNo").trim());
    		BidProductVO bidProductVO = new BidProductVO();
    		String bidProdName = request.getParameter("bidProdName");
    		String bidProdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_:)(\\-\\)]{1,50}$";
    		if (bidProdName == null || bidProdName.trim().length() == 0) {
    			errorMsgs.add("商品名稱: 請勿空白");
    		} else if(!bidProdName.trim().matches(bidProdNameReg)) { //以下練習正則(規)表示式(regular-expression)
    			errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度須在1到50之間");
    		}
    		
    		
    		String bidProdDescription = null;
    		try {
    			bidProdDescription = request.getParameter("bidProdDescription").trim();
    		} catch (IllegalArgumentException e) {
    			bidProdDescription = "";
    		}
    		
    		Integer bidProdStartPrice = null;
    		try {
    			bidProdStartPrice = Integer.parseInt(request.getParameter("bidProdStartPrice").trim());
    		} catch (NumberFormatException e) {
    			bidProdStartPrice = 0;
    			errorMsgs.add("起標價應為數字");
    		}
    		if (bidProdStartPrice < 0 || bidProdStartPrice > 99000) {
    			errorMsgs.add("起標價應在0 - 99000之間");
    		}
    		
    		Integer bidState = new Integer(0);
    		
    		Integer bidPriceIncrement = null;
    		try {
    			bidPriceIncrement = Integer.parseInt(request.getParameter("bidPriceIncrement").trim());
    		} catch (NumberFormatException e) {
    			bidPriceIncrement = 0;
    			errorMsgs.add("最低增額應為數字");
    		}
    		if (bidPriceIncrement <= 0) {
    			errorMsgs.add("最低增額應大於0");
    		}
    		
    		Timestamp bidProdStartTime = null;
    		try {
    			bidProdStartTime = Timestamp.valueOf(request.getParameter("bidProdStartTime").trim());
    		} catch (IllegalArgumentException e) {
    			bidProdStartTime = new java.sql.Timestamp(System.currentTimeMillis() + 60000);
    			errorMsgs.add("請輸入起標時間!");
    		}
    		if (bidProdStartTime.before(new java.sql.Timestamp(System.currentTimeMillis()))) {
    			bidProdStartTime = new java.sql.Timestamp(System.currentTimeMillis() + 60000);
    			errorMsgs.add("起標時間早於目前時間，請重新輸入！");
    		}
    		
    		java.sql.Timestamp bidProdEndTime = null;
    		try {
    			bidProdEndTime = java.sql.Timestamp.valueOf(request.getParameter("bidProdEndTime").trim());
    		} catch (IllegalArgumentException e) {
    			bidProdEndTime = new java.sql.Timestamp(System.currentTimeMillis() + 600000);
    			errorMsgs.add("請輸入截標時間!");
    		}
    		if (bidProdEndTime.before(bidProdStartTime) || bidProdEndTime.equals(bidProdStartTime)) {
    			bidProdEndTime = new java.sql.Timestamp(System.currentTimeMillis() + 600000);
    			errorMsgs.add("截標時間應晚於起標時間，請重新輸入！");
    		}
    		
    		Integer OrderState = new Integer(0);
    		
    		bidVO.setBidProdName(bidProdName);
    		bidVO.setKindNo(kindNo);
    		bidVO.setBidProdDescription(bidProdDescription);
    		bidVO.setBidProdStartPrice(bidProdStartPrice);
    		bidVO.setBidState(bidState);
    		bidVO.setBidPriceIncrement(bidPriceIncrement);
    		bidVO.setBidProdStartTime(bidProdStartTime);
    		bidVO.setBidProdEndTime(bidProdEndTime);
    		bidVO.setBidProdState(bidProdState);
    		
//    		測試同時上傳圖片 2221
//    		BidPicService bidPicSvc = new BidPicService();
			
//			Integer bidProdNo = new Integer(req.getParameter("bidProdNo").trim());
//			System.out.println(bidProdNo);
    		BidService bidSvc = new BidProductService();
			List<Part> list = (List<Part>) req.getParts();
			List<byte[]> listPic = new ArrayList<byte[]>();
			InputStream is = null;
			BufferedInputStream bis = null;
			byte[] bidProdPicContent = null;
			
//			Context ctx = new javax.naming.InitialContext();
//			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G1");
//			Connection conn = ds.getConnection();
			for (Part part : list) {
				is = part.getInputStream();
				bis = new BufferedInputStream(is);
				if (is.available() > 1024) {
					bidProdPicContent = new byte[bis.available()];
					bis.read(bidProdPicContent);
					listPic.add(bidProdPicContent);
				}

			}
			
			
//			END HERE
    		
    		
    		// Send the use back to the form, if there were errors
    		if (!errorMsgs.isEmpty()) {
    			req.setAttribute("bidVO", bidVO); // 含有輸入格式錯誤的bidVO物件,也存入req
    			RequestDispatcher failureView = req
    					.getRequestDispatcher("/backend/bid/addBid.jsp");
    			failureView.forward(req, res);
    			return;
    		}
    		
    		bidSvc.addBid2(kindNo, bidProdName, bidProdDescription, bidProdStartPrice, bidState, bidProdStartTime, bidProdEndTime, bidPriceIncrement, bidProdState, listPic);
    		
    		/***************************2.開始新增資料***************************************/

//    		好像用不到
//    		BidService bidSvc = new BidService();
//    		bidVO = bidSvc.addBid(kindNo, bidProdName, bidProdDescription, bidProdStartPrice, bidState, bidProdStartTime, bidProdEndTime, bidPriceIncrement, bidProdState);
    		
    		/***************************3.新增完成,準備轉交(Send the Success view)***********/
    		String url = "/backend/bid/listAllBid.jsp";
    		RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllBid.jsp
    		successView.forward(req, res);				
    		
    		/***************************其他可能的錯誤處理**********************************/
    	} catch (Exception e) {
    		e.printStackTrace();
    		errorMsgs.add(e.getMessage());
    		RequestDispatcher failureView = req
    				.getRequestDispatcher("/backend/bid/addBid.jsp");
    		failureView.forward(req, res);
    	}
    }
	}

}

package com.order.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.model.OrderService;
import com.order.model.OrderVO;



@WebServlet("/product/turnModOrder")
public class TurnModOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public TurnModOrder() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		List<String> errorMsgs = new LinkedList<String>();
		// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
		request.setAttribute("errorMsgs", errorMsgs);

		try {
			// 1.接收請求參數
			Integer orderNo = Integer.valueOf(request.getParameter("orderNo"));

			System.out.println(orderNo);
			// 2.開始查詢資料
			OrderService orderService = new OrderService();
			OrderVO orderVO = orderService.findOneOrderByOrderNo(orderNo);

			// 3.查詢完成,準備轉交(Send the Success view)
			// 從資料庫取 orderVO 物件, 存入 request 中
			request.setAttribute("orderVO", orderVO);
			String url = "/backend/product/editOrder.jsp";
			// 成功轉交 editOrder.jsp
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			
			// 其他可能的錯誤處理
		} catch (Exception e) {
			System.out.println("沒有資料");
			String url = "/backend/product/order.jsp";
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			RequestDispatcher failureView = request.getRequestDispatcher(url);
			failureView.forward(request, response);
		}
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

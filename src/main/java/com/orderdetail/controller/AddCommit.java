package com.orderdetail.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orderdetail.model.OrderDetailService;
import com.orderdetail.model.OrderDetailVO;


@WebServlet("/orderDetail/addCommit")
public class AddCommit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddCommit() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");	
		
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		/*******取產品編號*******/
		String productNo_str = request.getParameter("productNo");
		Integer productNo = Integer.valueOf(productNo_str);
		orderDetailVO.setProductNo(productNo);
		System.out.println(productNo);
		
		/*******取訂單編號*******/
		String orderNo_str = request.getParameter("orderNo");
		Integer orderNo = Integer.valueOf(orderNo_str);
		orderDetailVO.setOrderNo(orderNo);
		System.out.println(orderNo);
		
		/*******取評論文章*******/
		String commentCotent = request.getParameter("commentCotent");
		orderDetailVO.setCommentCotent(commentCotent);
		System.out.println(commentCotent);
		
		/*******取評論星星*******/
		String commentStar_str = request.getParameter("CommentStar");
		Integer commentStar = Integer.valueOf(commentStar_str);
		orderDetailVO.setCommentStar(commentStar);
		System.out.println(commentStar);
		
		
		/*********開始更新************/
		OrderDetailService orderDetailService = new OrderDetailService();
		orderDetailService.AddComment(orderDetailVO);
		
		
		
		/*********重導回訂單頁面*************/
		response.sendRedirect("/CGA101G1/frontend/Product/account-order.html");
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

package com.orderdetail.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.orderdetail.model.OrderDetailService;
import com.orderdetail.model.OrderDetailVO;

@WebServlet("/product/showProductCaledComment")
public class ShowProductCaledComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowProductCaledComment() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		OrderDetailService orderDetailService = new OrderDetailService();
		Integer productNo = Integer.valueOf(request.getParameter("ProductNo"));
		OrderDetailVO orderDetailVO = orderDetailService.showCommentAfterCaled(productNo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productNo", orderDetailVO.getProductNo());
		
		Integer countComment =orderDetailVO.getProductSales();
		map.put("countComment", countComment);
		Integer sumCommentStar =orderDetailVO.getCommentStar();
		map.put("sumCommentStar", sumCommentStar);
		Integer avgC = (int) Math.floor((sumCommentStar/countComment));
		map.put("avgCommentStar", avgC);
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		out.write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

package com.orderdetail.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.orderdetail.model.OrderDetailService;
import com.orderdetail.model.OrderDetailVO;


@WebServlet("/product/showOneProductAllComments")
public class ShowOneProductAllComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShowOneProductAllComments() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		OrderDetailService orderDetailService = new OrderDetailService();
		
		Integer productNo = Integer.valueOf(request.getParameter("ProductNo"));
		
		List<OrderDetailVO> list = orderDetailService.AllCommentByProductNo(productNo);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		out.write(json);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

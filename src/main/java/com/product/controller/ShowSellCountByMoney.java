package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;


@WebServlet("/product/showSellCountByMoney")
public class ShowSellCountByMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShowSellCountByMoney() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Integer lowPrice =Integer.valueOf(request.getParameter("lowPrice"));
		Integer highPrice =Integer.valueOf(request.getParameter("highPrice"));
		
		ProductService productService = new ProductService();
		Integer count = productService.showSellCountByMoney(lowPrice, highPrice);
		out.print(count);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

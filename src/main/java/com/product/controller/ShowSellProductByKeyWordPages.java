package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;


@WebServlet("/product/showSellProductByKeyWordPages")
public class ShowSellProductByKeyWordPages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShowSellProductByKeyWordPages() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String keyWord =request.getParameter("keyWord");
		ProductService productService = new ProductService();
		Integer count = productService.ShowSellCountByKeyword(keyWord);
		out.print(count);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

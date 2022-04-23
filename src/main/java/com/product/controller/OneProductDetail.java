package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.product.model.ProductService;

/**
 * Servlet implementation class OnlyOneProductToJson
 */
@WebServlet("/product/OneProductDetail")
public class OneProductDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public OneProductDetail() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		
		Integer productNo = Integer.valueOf(request.getParameter("ProductNo"));
		ProductService productService = new ProductService();
		
		
		Gson gson = new Gson();
		String json = gson.toJson(productService.GetOne(productNo));
		out.print(json);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

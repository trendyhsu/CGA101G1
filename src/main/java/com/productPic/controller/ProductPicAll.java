package com.productPic.controller;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.productPic.model.ProductPicService;

/**
 * Servlet implementation class ProductPicAll
 */
@WebServlet("/product/ProductPicAll")
public class ProductPicAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ProductPicAll() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ProductPicService productPicService=new ProductPicService();
		String productno = request.getParameter("ProductNo");
		Integer productNo = Integer.valueOf(productno);
		
		Gson gson = new Gson();
		String json = gson.toJson(productPicService.productAllPics(productNo));
		out.print(json);
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

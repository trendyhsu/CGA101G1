package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.product.model.ProductService;


@WebServlet("/product/showPageProduct")
public class ShowPageProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShowPageProduct() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ProductService productService = new ProductService();
		
		String pageStr =request.getParameter("Page");
		if(pageStr==null) {

			/****** 首頁為0 *******/
			List<Object> list = productService.GetAllSelledProductsByMap(1);
			Gson gson = new Gson();
			String json = gson.toJson(list);
			out.print(json);
			
			
		}else {
			Integer page = Integer.valueOf(pageStr);
			List<Object> list = productService.GetAllSelledProductsByMap(page);
			Gson gson = new Gson();
			String json = gson.toJson(list);
			out.print(json);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

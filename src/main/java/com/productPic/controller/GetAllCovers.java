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
 * Servlet implementation class GetAllCovers
 */
@WebServlet("/product/GetAllCovers")
public class GetAllCovers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GetAllCovers() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ProductPicService productPicService = new ProductPicService();	
		Gson gson = new Gson();
		String json = gson.toJson(productPicService.getAllCovers(request));
		out.print(json);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

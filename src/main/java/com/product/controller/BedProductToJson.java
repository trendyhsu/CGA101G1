package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.product.model.ProductVO;

/**
 * Servlet implementation class BedProductToJson
 */
@WebServlet("/product/bedProductToJson")
public class BedProductToJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BedProductToJson() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		ProductVO productVO =(ProductVO) session.getAttribute("checkedProductVO");
		Gson gson = new Gson();
		String json = gson.toJson(productVO);
		out.write(json);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

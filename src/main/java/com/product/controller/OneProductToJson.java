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

import com.google.gson.Gson;
import com.product.model.ProductDAO;
import com.product.model.ProductDAO_interface;
import com.product.model.ProductService;
import com.product.model.ProductVO;

/**
 * Servlet implementation class OneProductToJson
 */
@WebServlet("/product/OneProductToJson")
public class OneProductToJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OneProductToJson() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		List<ProductVO> productsList = new ArrayList<ProductVO>();
		ProductDAO_interface dao = new ProductDAO();
		productsList = dao.getAll();
		Gson gson = new Gson();
		String json = gson.toJson(productsList);
		out.write(json);		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

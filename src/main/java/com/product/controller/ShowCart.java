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


@WebServlet("/product/showCart")
public class ShowCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShowCart() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
//		if(session.getAttribute("member").getMemNo()==null) {
//			response.sendRedirect("登入畫面");
//		}else {
//			String memNo = session.getAttribute("member").getMemNo();
		String memNo = "11001";
//		List<Orderdetail> orderList = new ArrayList<Orderdetail>();
		List<Cartdetail> orderList = ((List<Cartdetail>) session.getAttribute("shoppingCart")==null?new ArrayList<Cartdetail>():(List<Cartdetail>) session.getAttribute("shoppingCart"));
		
			Gson gson = new Gson();
			String json = gson.toJson(orderList);
			System.out.println(json);
			out.print(json);
//			response.sendRedirect("frontend/product/HomePageinshop.html");
	

//		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

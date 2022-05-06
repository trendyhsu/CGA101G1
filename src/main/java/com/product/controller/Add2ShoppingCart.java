package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.test.Cartdetail;



@WebServlet("/product/add2ShoppingCart")
public class Add2ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Add2ShoppingCart() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();


	    /*****************有會員身分開始加入購物車****************/

		List<Cartdetail> orderList = ((List<Cartdetail>) session.getAttribute("shoppingCart")==null?new ArrayList<Cartdetail>():(List<Cartdetail>) session.getAttribute("shoppingCart"));
		if (session.getAttribute("shoppingCart") == null) {
			Cartdetail orderdetail = new Cartdetail();


			orderdetail.setProductName(request.getParameter("ProductName"));
			orderdetail.setProductNo(request.getParameter("ProductNo"));
			orderdetail.setProductSales(request.getParameter("ProductSales") == null ? 1
					: Integer.parseInt(request.getParameter("ProductSales")));
			orderdetail.setProductTotalPrice(Integer.parseInt(request.getParameter("ProductTotalPrice")));
			
			orderList.add(orderdetail);
			session.setAttribute("shoppingCart", orderList);
			Gson gson = new Gson();
			String json = gson.toJson(orderList);

			out.print(json);

		} else {

			Cartdetail newOrderdetail =getCartdetail(request);		
			if(orderList.contains(newOrderdetail)) {
				Cartdetail innerOrderdetail =orderList.get(orderList.indexOf(newOrderdetail));				
				innerOrderdetail.setProductSales(innerOrderdetail.getProductSales()+newOrderdetail.getProductSales());
				innerOrderdetail.setProductTotalPrice(innerOrderdetail.getProductTotalPrice()+newOrderdetail.getProductTotalPrice());
				
			}else {				
				orderList.add(newOrderdetail);
			}
			
			Gson gson = new Gson();
			String json = gson.toJson(session.getAttribute("shoppingCart"));

			out.print(json);

		}



	}
	public Cartdetail getCartdetail(HttpServletRequest request) {
	
		Cartdetail cartdetail=new Cartdetail();
		cartdetail.setProductName(request.getParameter("ProductName"));
		cartdetail.setProductNo(request.getParameter("ProductNo"));
		cartdetail.setProductSales(Integer.parseInt(request.getParameter("ProductSales")));
		cartdetail.setProductTotalPrice(Integer.parseInt(request.getParameter("ProductTotalPrice")) );
		
		return cartdetail ;
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}


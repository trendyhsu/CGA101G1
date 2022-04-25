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

/**
 * Servlet implementation class ShoppingCartModAdd
 */
@WebServlet("/product/shoppingCartModAdd")
public class ShoppingCartModAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ShoppingCartModAdd() {
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
		if (session.getAttribute("shoppingCart") == null) {
			System.out.println("session is null");
			Cartdetail orderdetail = new Cartdetail();
			System.out.println(request.getParameter("ProductNo"));
			System.out.println(request.getParameter("ProductTotalPrice"));

			orderdetail.setProductName(request.getParameter("ProductName"));
			orderdetail.setProductNo(request.getParameter("ProductNo"));
			orderdetail.setProductSales(request.getParameter("ProductSales") == null ? 1
					: Integer.parseInt(request.getParameter("ProductSales")) + 0.0);
			orderdetail.setProductTotalPrice(Integer.parseInt(request.getParameter("ProductTotalPrice")) + 0.0);
			
			System.out.println(orderdetail.getProductName());
			System.out.println(orderdetail.getProductNo());
			System.out.println(orderdetail.getProductSales());
			System.out.println(orderdetail.getProductTotalPrice());
			orderList.add(orderdetail);
			session.setAttribute("shoppingCart", orderList);
			Gson gson = new Gson();
			String json = gson.toJson(orderList);
			System.out.println(json);
//			out.print(json);
			response.sendRedirect("/CGA101G1/frontend/Product/shopping-cart.html");
		} else {
			System.out.println("有購物車");
			Cartdetail newOrderdetail =getCartdetail(request);
			System.out.println(newOrderdetail.getProductNo());
			System.out.println(newOrderdetail.getProductName());

			
			System.out.println(orderList.contains(newOrderdetail));
		
			if(orderList.contains(newOrderdetail)) {
				System.out.println("添加舊的書在原本的購物車上");
				Cartdetail innerOrderdetail =orderList.get(orderList.indexOf(newOrderdetail));
				System.out.println("增加的數量："+newOrderdetail.getProductSales());
				System.out.println("購物車上的數量："+innerOrderdetail.getProductSales());
				
				innerOrderdetail.setProductSales(innerOrderdetail.getProductSales()+newOrderdetail.getProductSales());
				innerOrderdetail.setProductTotalPrice(innerOrderdetail.getProductTotalPrice()+newOrderdetail.getProductTotalPrice());
				
			}else {
				System.out.println("添加新的書在原本的購物車上");
				
				orderList.add(newOrderdetail);
			}
			
			Gson gson = new Gson();
			String json = gson.toJson(session.getAttribute("shoppingCart"));
			System.out.println(json);
//			out.print(json);
			response.sendRedirect("/CGA101G1/frontend/Product/shopping-cart.html");
		}

//		}
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	
	
	
	
	public Cartdetail getCartdetail(HttpServletRequest request) {
		
		Cartdetail cartdetail=new Cartdetail();
		cartdetail.setProductName(request.getParameter("ProductName"));
		cartdetail.setProductNo(request.getParameter("ProductNo"));
		cartdetail.setProductSales(Integer.parseInt(request.getParameter("ProductSales"))+0.0);
		cartdetail.setProductTotalPrice(Integer.parseInt(request.getParameter("ProductTotalPrice")) + 0.0);
		
		return cartdetail ;
		
	}

}

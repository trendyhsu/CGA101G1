package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.test.Cartdetail;

@WebServlet("/product/shoppingCartReduce")
public class ShoppingCartReduce extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShoppingCartReduce() {
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
//		List<Cartdetail> orderList = ((List<Cartdetail>) session.getAttribute("shoppingCart")==null?new ArrayList<Cartdetail>():(List<Cartdetail>) session.getAttribute("shoppingCart"));
		List<Cartdetail> cartList = ((List<Cartdetail>) session.getAttribute("shoppingCart"));

		String productNo = request.getParameter("ProductNo");

		System.out.println("有購物車");
		//判斷有該物件
		Cartdetail cartdetail = getCartdetail(request);
		System.out.println(cartdetail.getProductNo());
		System.out.println(cartdetail.getProductName());

		System.out.println(cartList.contains(cartdetail));

		if (cartList.contains(cartdetail)) {
			System.out.println("開始刪除");
			Cartdetail innerCartdetail = cartList.get(cartList.indexOf(cartdetail));
			System.out.println("要刪除的數量：" + cartdetail.getProductSales());
			System.out.println("目前購物車上的數量：" + innerCartdetail.getProductSales());

			if(innerCartdetail.getProductSales()>1) {
				innerCartdetail.setProductSales(innerCartdetail.getProductSales() - cartdetail.getProductSales());
				innerCartdetail.setProductTotalPrice(innerCartdetail.getProductTotalPrice() - cartdetail.getProductTotalPrice());}
			else if(innerCartdetail.getProductSales()==1){
				cartList.remove(innerCartdetail);
			}

		} else {
			System.out.println("沒有這個物品欸");

			response.sendRedirect("/CGA101G1/frontend/Product/shopping-cart.html");
		}

		Gson gson = new Gson();
		String json = gson.toJson(session.getAttribute("shoppingCart"));
		System.out.println(json);
//			out.print(json);
		response.sendRedirect("/CGA101G1/frontend/Product/shopping-cart.html");

//		}

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	public Cartdetail getCartdetail(HttpServletRequest request) {
		
		Cartdetail cartdetail = new Cartdetail();
		cartdetail.setProductName(request.getParameter("ProductName"));
		cartdetail.setProductNo(request.getParameter("ProductNo"));
		cartdetail.setProductSales(Integer.parseInt(request.getParameter("ProductSales")));
		cartdetail.setProductTotalPrice(Integer.parseInt(request.getParameter("ProductTotalPrice")));
		
		return cartdetail;
		
	}
}

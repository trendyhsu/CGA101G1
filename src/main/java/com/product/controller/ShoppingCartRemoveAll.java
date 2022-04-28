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
import com.member.model.MemVO;
import com.test.Cartdetail;





@WebServlet("/product/shoppingCartRemoveAll")
public class ShoppingCartRemoveAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShoppingCartRemoveAll() {
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

		MemVO memVO = (MemVO) (session.getAttribute("memVO"));
		Integer memNo = memVO.getMemNo();
		System.out.println("現在登入的會員編號是：" + memNo);
//		String memNo = "11001";
//		List<Orderdetail> orderList = new ArrayList<Orderdetail>();
//		List<Cartdetail> orderList = ((List<Cartdetail>) session.getAttribute("shoppingCart")==null?new ArrayList<Cartdetail>():(List<Cartdetail>) session.getAttribute("shoppingCart"));
		List<Cartdetail> cartList = ((List<Cartdetail>) session.getAttribute("shoppingCart"));


		System.out.println("有購物車");
		//判斷有該物件
		Cartdetail cartdetail = getCartdetail(request);
		System.out.println(cartdetail.getProductNo());
		System.out.println(cartdetail.getProductName());

		System.out.println(cartList.contains(cartdetail));

		if (cartList.contains(cartdetail)) {
			System.out.println("開始直接移除全部");
			Cartdetail innerCartdetail = cartList.get(cartList.indexOf(cartdetail));
		    cartList.remove(innerCartdetail);


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

	public Cartdetail getCartdetail(HttpServletRequest request) {

		Cartdetail cartdetail = new Cartdetail();
		cartdetail.setProductName(request.getParameter("ProductName"));
		cartdetail.setProductNo(request.getParameter("ProductNo"));
		cartdetail.setProductSales(Integer.parseInt(request.getParameter("ProductSales")));
		cartdetail.setProductTotalPrice(Integer.parseInt(request.getParameter("ProductTotalPrice")));

		return cartdetail;

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

package com.product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemVO;

@WebServlet("/product/MemCheckToShopping")
public class MemCheckToShopping extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemCheckToShopping() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (((MemVO) (session.getAttribute("memVO"))).getMemNo() == null) {
			response.sendRedirect("/CGA101G1/frontend/mem/login.html");
		} else {
			response.sendRedirect("/CGA101G1/frontend/Product/shopping-cart.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

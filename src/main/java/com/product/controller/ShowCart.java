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
import com.member.model.MemService;
import com.member.model.MemVO;
import com.test.Cartdetail;

@WebServlet("/product/showCart")
public class ShowCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowCart() {
		super();

	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
//		MemVO memVO = (MemVO) (session.getAttribute("memVO"));
//		Integer memNo = memVO.getMemNo();
//		
//		MemService memService = new MemService(); 
		
		

		List<Cartdetail> orderList = ((List<Cartdetail>) session.getAttribute("shoppingCart") == null
				? new ArrayList<Cartdetail>()
				: (List<Cartdetail>) session.getAttribute("shoppingCart"));

		Gson gson = new Gson();
		String json = gson.toJson(orderList);
		out.print(json);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

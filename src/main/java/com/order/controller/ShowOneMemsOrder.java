package com.order.controller;

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
import com.order.model.OrderService;
import com.order.model.OrderVO;

@WebServlet("/order/showOneMemsOrder")
public class ShowOneMemsOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowOneMemsOrder() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		MemVO memVO = (MemVO) (session.getAttribute("memVO"));
		Integer memNo = memVO.getMemNo();
//		System.out.println("現在查看訂單的會員編號是："+memNo);
//		Integer memNo = 11001;
		OrderService orderService = new OrderService();
		List<OrderVO> list = orderService.findAllOrdersByMemNo(memNo);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		out.print(json);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

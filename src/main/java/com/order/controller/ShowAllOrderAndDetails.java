package com.order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.order.model.OrderService;
import com.order.model.OrderVO;
import com.orderdetail.model.OrderDetailVO;

@WebServlet("/product/showAllOrderAndDetails")
public class ShowAllOrderAndDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowAllOrderAndDetails() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();

		OrderService orderService = new OrderService();
		List<OrderVO> Orderlist = orderService.findAllOrders();
		List<Object> list = new ArrayList<Object>();

		/*********** 時間格式 ************/
		java.text.DateFormat rule = new java.text.SimpleDateFormat("yyyy.MM.dd  '-' HH:mm:ss");

		for (OrderVO order : Orderlist) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("OrderNo", order.getOrderNo().toString());
			map.put("MemNo", order.getMemNo().toString());
			map.put("OrderState", order.getOrderState().toString());
			map.put("OrderTotalPrice", order.getOrderTotalPrice().toString());
			map.put("PickupMethod", order.getPickupMethod().toString());
			map.put("TranTime", rule.format(order.getTranTime()));
			map.put("ReceiverName", order.getReceiverName().toString());
			map.put("ReceiverAddress", order.getReceiverAddress().toString());
			map.put("ReceiverPhone", order.getReceiverPhone().toString());

			List<OrderDetailVO> orderDetaillist = new ArrayList<OrderDetailVO>();
			orderDetaillist = order.GetAllDetailByOrderNo(order.getOrderNo());
			map.put("OrderDetails", orderDetaillist);
			list.add(map);
		}
		Gson gson = new Gson();
		String json = gson.toJson(list);
		out.write(json);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

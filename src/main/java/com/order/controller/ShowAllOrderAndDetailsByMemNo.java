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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.member.model.MemVO;
import com.order.model.OrderService;
import com.order.model.OrderVO;
import com.orderdetail.model.OrderDetailVO;


@WebServlet("/product/showAllOrderAndDetailsByMemNo")
public class ShowAllOrderAndDetailsByMemNo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShowAllOrderAndDetailsByMemNo() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		/****** 查看訂單的會員**********/
		HttpSession session = request.getSession();
		MemVO memVO = (MemVO) (session.getAttribute("memVO"));
		Integer memNo = memVO.getMemNo();
//		Integer memNo = 11003;
		System.out.println("現在查看訂單的會員編號是："+memNo);
		

		OrderService orderService = new OrderService();
		List<OrderVO> Orderlist = orderService.findAllOrdersByMemNo(memNo);
		List<Object> list = new ArrayList<Object>();

		/*********** 時間格式 ************/
		java.text.DateFormat rule = new java.text.SimpleDateFormat("yyyy.MM.dd  '-' HH:mm:ss");

		for (OrderVO order : Orderlist) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			
			map.put("orderNo", order.getOrderNo().toString());
			map.put("memNo", memNo);
			Integer orderState = Integer.valueOf(order.getOrderState());
			map.put("orderState", orderState);
			if(orderState==0) {
				map.put("orderStateStr", "未出貨");
			}else if(orderState==1){
				map.put("orderStateStr", "已出貨");
			}else if(orderState==2){
				map.put("orderStateStr", "完成訂單");
			}else if(orderState==3){
				map.put("orderStateStr", "退貨");
			}else if(orderState==4){
				map.put("orderStateStr", "訂單作廢");
			}
			
			map.put("orderTotalPrice", order.getOrderTotalPrice().toString());
			map.put("pickupMethod", order.getPickupMethod().toString());
			map.put("tranTime", rule.format(order.getTranTime()));
			map.put("receiverName", order.getReceiverName().toString());
			map.put("receiverAddress", order.getReceiverAddress().toString());
			map.put("receiverPhone", order.getReceiverPhone().toString());
			map.put("openOrderDetail", false);
			
			
			List<OrderDetailVO> orderDetaillist = new ArrayList<OrderDetailVO>();
			orderDetaillist = order.getAllDetailByOrderNo(order.getOrderNo());
			map.put("orderDetail", orderDetaillist);
			list.add(map);
		}
		Gson gson = new Gson();
		String json = gson.toJson(list);
		out.write(json);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

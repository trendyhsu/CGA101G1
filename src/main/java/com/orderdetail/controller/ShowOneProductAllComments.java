package com.orderdetail.controller;

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
import com.member.model.MemVO;
import com.order.model.OrderVO;
import com.orderdetail.model.OrderDetailService;
import com.orderdetail.model.OrderDetailVO;


@WebServlet("/product/showOneProductAllComments")
public class ShowOneProductAllComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShowOneProductAllComments() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		OrderDetailService orderDetailService = new OrderDetailService();
		
		Integer productNo = Integer.valueOf(request.getParameter("ProductNo"));
		List<Object> list = new ArrayList<Object>();
		List<OrderDetailVO> orderDetailList = orderDetailService.AllCommentByProductNo(productNo);
		for(OrderDetailVO orderDetailVO : orderDetailList) {
			Map<String,Object> map = new HashMap<>();
			map.put("commentCotent", orderDetailVO.getCommentCotent());
			map.put("commentStar", orderDetailVO.getCommentStar());
			map.put("commentTime", orderDetailVO.getCommentTime());
			Integer orderNo = orderDetailVO.getOrderNo();
			map.put("orderNo", orderNo);
			map.put("productNo", productNo);
			OrderVO orderVO = orderDetailVO.findMemNoByOrderNo(orderNo);
			Integer memNo= orderVO.getMemNo();
			MemVO memVO =orderVO.getMemVObyMemNo(memNo);
			map.put("memAccount", memVO.getMemAccount());
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

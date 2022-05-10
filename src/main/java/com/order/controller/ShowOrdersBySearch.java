package com.order.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
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

/**
 * Servlet implementation class ShowOrdersBySearch
 */
@WebServlet("/orders/showOrdersBySearch")
public class ShowOrdersBySearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowOrdersBySearch() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
		if (request.getParameter("whichPage") == null) {
			Map<String, String[]> map1 = new HashMap<String, String[]>(request.getParameterMap());
			session.setAttribute("map", map1);
			map = map1;
		}
		OrderService orderService = new OrderService();

		/********* 如果有搜尋姓名會先找會員編號 ************/
		if (map.get("memName")[0].trim().length() != 0) {
			OrderVO orderVO = new OrderVO();
			MemVO memVO = orderVO.getByMemName(map.get("memName")[0]);
			if (memVO != null) {
				Integer memNo = memVO.getMemNo();
				map.get("memNo")[0] = memNo.toString();
			}else {
				map.get("memNo")[0] = "1";
			}
		}

		List<OrderVO> list = orderService.findOrdersBysearch(map);

		request.setAttribute("CompositeQuery", list); // 資料庫取出的list物件,存入request
		RequestDispatcher successView = request.getRequestDispatcher("/backend/product/orderQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
		successView.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

package com.product.controller;

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

import com.gametype.model.GameTypeVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orderdetail.model.OrderDetailDAO;
import com.orderdetail.model.OrderDetailDAO_interface;
import com.orderdetail.model.OrderDetailService;
import com.orderdetail.model.OrderDetailVO;
import com.product.model.ProductDAO;
import com.product.model.ProductDAO_interface;
import com.product.model.ProductVO;

@WebServlet("/product/showTop9Product")
public class ShowTop9Product extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowTop9Product() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();

		List<Object> list = new ArrayList<Object>();
		List<OrderDetailVO> orderDetailList = new ArrayList<OrderDetailVO>();

		OrderDetailService orderDetailService = new OrderDetailService();
		orderDetailList = orderDetailService.GetTop9Product();
//		for (int i = 0; i < productsList.size(); i++) {
		for (OrderDetailVO orderDetailVO : orderDetailList) {
			Integer productNo = orderDetailVO.getProductNo();
		
			ProductVO productVO = orderDetailVO.getProductVO(productNo);
			String productName = productVO.getProductName();
			Integer productPrice = productVO.getProductPrice();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("productName", productName);
			map.put("productPrice", productPrice);
			map.put("commentStar", orderDetailVO.getCommentStar());
			map.put("productSales", orderDetailVO.getProductSales());
			map.put("countComment",orderDetailVO.getProductTotalPrice());
			map.put("productNo", productNo);
			map.put("imgURL","/CGA101G1/product/showOneCover?ProductNO="+productNo);
			map.put("productDetailPageURL","/CGA101G1/frontend/Product/HomePageinProduct.html?ProductNo="+productNo);
			list.add(map);
		}
//			
//		}

		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String json = gson.toJson(list);
		out.write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

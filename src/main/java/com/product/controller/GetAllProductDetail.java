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
import com.product.model.ProductDAO;
import com.product.model.ProductDAO_interface;
import com.product.model.ProductVO;

@WebServlet("/GetAllProductDetail")
public class GetAllProductDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetAllProductDetail() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();

		List<ProductVO> productsList = new ArrayList<ProductVO>();
		ProductDAO_interface dao = new ProductDAO();
		productsList = dao.getAll();
		for (ProductVO product : productsList) {
			Integer gameTypeNo = product.getGameTypeNo();
			GameTypeVO gameTypeVO = product.getOneGameType(gameTypeNo);
			String gameTypeName =gameTypeVO.getGameTypeName();
			
			Map<Object, String> map = new HashMap<Object, String>();
			map.put(product, gameTypeName);
			
		}

		Gson gson = new Gson();
		String json = gson.toJson(productsList);
		out.write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

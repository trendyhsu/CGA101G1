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

import com.gameplatformtype.model.GamePlatformTypeDAO_interface;
import com.gameplatformtype.model.GamePlatformTypeVO;
import com.gametype.model.GameTypeVO;
import com.google.gson.Gson;
import com.product.model.ProductDAO;
import com.product.model.ProductDAO_interface;
import com.product.model.ProductVO;

@WebServlet("/product/getAllProductDetail")
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

		List<Object> list = new ArrayList<Object>();
		List<ProductVO> productsList = new ArrayList<ProductVO>();

		ProductDAO_interface dao = new ProductDAO();
		productsList = dao.getAll();

//		for (int i = 0; i < productsList.size(); i++) {
			for (ProductVO product : productsList) {
				Integer gameTypeNo = product.getGameTypeNo();
				GameTypeVO gameTypeVO = product.getOneGameType(gameTypeNo);
				String gameTypeName = gameTypeVO.getGameTypeName();
			
				Map<String, String> map = new HashMap<String, String>();
				map.put("productName", product.getProductName());
				map.put("productNo", product.getProductNo().toString());
				map.put("gameTypeName", gameTypeName);
				map.put("gameTypeNo", product.getGameTypeNo().toString());
				map.put("gamePlatformNo", product.getGamePlatformNo().toString());
				map.put("gamePlatformName", product.getOneGamePlatformType(product.getGamePlatformNo()).getGamePlatformName());
				map.put("itemProdDescription", product.getItemProdDescription());
				map.put("upcNum", product.getUpcNum());
				map.put("productPrice", product.getProductPrice().toString());
				map.put("productState",product.getProductState().toString());
				list.add(map);
			}
//			
//		}

		Gson gson = new Gson();
		String json = gson.toJson(list);
		out.write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

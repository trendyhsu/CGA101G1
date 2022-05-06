package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.product.model.ProductService;
import com.product.model.ProductVO;

/**
 * Servlet implementation class OnlyOneProductToJson
 */
@WebServlet("/product/OneProductDetail")
public class OneProductDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public OneProductDetail() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		
		Integer productNo = Integer.valueOf(request.getParameter("ProductNo"));
		ProductService productService = new ProductService();
		Map<String, Object> map = new HashMap<String, Object>();
		ProductVO productVO=productService.GetOne(productNo);
		
		map.put("productName", productVO.getProductName());
		map.put("productNo", productVO.getProductNo().toString());
		map.put("gameTypeName", productVO.getOneGameType(productVO.getGameTypeNo()).getGameTypeName());
//		map.put("gameTypeNo", productVO.getGameTypeNo().toString());
//		map.put("gamePlatformNo", productVO.getGamePlatformNo().toString());
		map.put("gamePlatformName", productVO.getOneGamePlatformType(productVO.getGamePlatformNo()).getGamePlatformName());
//		map.put("gameCompanyNo", productVO.getGameCompanyNo().toString());
		map.put("gameCompanyName", productVO.getOneGameCompanyVO(productVO.getGameCompanyNo()).getGameCompanyName());
		map.put("itemProdDescription", productVO.getItemProdDescription());
//		map.put("upcNum", productVO.getUpcNum());
		map.put("productPrice", productVO.getProductPrice().toString());
//		map.put("productState",productVO.getProductState().toString());
		
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		out.print(json);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

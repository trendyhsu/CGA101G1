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

import com.gameplatformtype.model.GamePlatformTypeVO;
import com.google.gson.Gson;
import com.orderdetail.model.OrderDetailService;
import com.orderdetail.model.OrderDetailVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;

/**
 * Servlet implementation class ShowToShop
 */
@WebServlet("/product/ShowToShop")
public class ShowToShop extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowToShop() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ProductService productService = new ProductService();

		List<ProductVO> productList = productService.GetAllSelledProducts();
		List<Object> list = new ArrayList<Object>();

		for (ProductVO productVO : productList) {
			Map<String, Object> map = new HashMap<String, Object>();
			Integer gamePlatformNo = productVO.getGamePlatformNo();
			GamePlatformTypeVO gamePlatformTypeVO = productVO.getOneGamePlatformType(gamePlatformNo);
			String gamePlatformTypeName = gamePlatformTypeVO.getGamePlatformName();

			map.put("gamePlatformNo", gamePlatformNo);
			map.put("gamePlatformTypeName", gamePlatformTypeName);

			map.put("gameCompanyNo", productVO.getGameCompanyNo());
			map.put("gameTypeNo", productVO.getGameTypeNo());
			map.put("productName", productVO.getProductName());
			map.put("productPrice", productVO.getProductPrice());
			map.put("itemProdDescription", productVO.getItemProdDescription());

			Integer productNo = productVO.getProductNo();
			map.put("productNo", productNo);
			map.put("imgURL","/CGA101G1/product/showOneCover?ProductNO="+productNo);


			OrderDetailService orderDetailService = new OrderDetailService();
			OrderDetailVO orderDetailVO = orderDetailService.showCommentAfterCaled(productNo);
			/*********** 該商品沒有評論時 orderDetailVO 會是null的處理 ***************/
			if (orderDetailVO == null) {
				map.put("avgCommentStar", 0);
				list.add(map);
			} else {
				Integer avgC = (int) Math.floor((orderDetailVO.getCommentStar() / orderDetailVO.getProductSales()));

				map.put("avgCommentStar", avgC);
				list.add(map);
			}
		}

		Gson gson = new Gson();
		String json = gson.toJson(list);
		out.print(json);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

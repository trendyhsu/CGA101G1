package com.bidproduct.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.bidproduct.model.BidProductDAO_interface;
import com.bidproduct.model.BidProductJDBCDAO;
import com.bidproduct.model.BidProductService;
import com.bidproduct.model.BidProductVO;
import com.bidrecord.model.BidRecordService;
import com.bidrecord.model.BidRecordVO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.product.model.ProductDAO;
import com.product.model.ProductDAO_interface;
import com.product.model.ProductService;
import com.product.model.ProductVO;

@WebServlet("/bid/bidProductGetOne")
public class BidProductGetOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=UTF-8");
//		Writer out = response.getWriter();
//		Integer bidProductNo = Integer.valueOf(request.getParameter("bidProductNo"));
//		BidProductService bidProductSvc = new BidProductService();
//		BidProductVO bidProductVO = bidProductSvc.getOneBid(bidProductNo);
//		Gson gson = new Gson();
//		String json = gson.toJson(bidProductVO);
//		out.write(json);

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Writer out = response.getWriter();
		// 從網址列取得該商品編號
		Integer bidProductNo = Integer.valueOf(request.getParameter("bidProductNo"));
		
		// 取得該競標商品VO
		BidProductService bidProductSvc = new BidProductService();
		BidProductVO bidProductVO = bidProductSvc.getOneBid(bidProductNo);
		JSONObject jsonObject1 = new JSONObject(bidProductVO);
		
		// 取得該商品最高出價
		BidRecordService bidRecordSvc = new BidRecordService();
		BidRecordVO bidRecordVO = bidRecordSvc.getHighestByBidProductNo(bidProductNo);
		
		// 取得一手商品VO
		ProductDAO_interface productDAO = new ProductDAO();
		ProductVO productVO = productDAO.findByPrimaryKey(bidProductVO.getProductNo());
		
		// 創建JSONObject物件 裝入上述三個物件
		JSONObject bidAll = new JSONObject();

		bidAll.put("bidProductVO", jsonObject1);
		
		if (bidRecordVO != null) {
			JSONObject jsonObject2 = new JSONObject(bidRecordVO);
			bidAll.put("bidRecordVO", jsonObject2);
		}
		if(productVO != null) {
			JSONObject jsonObject3 = new JSONObject(productVO);
			bidAll.put("productVO", jsonObject3);
		}

		out.write(bidAll.toString());
	}

}

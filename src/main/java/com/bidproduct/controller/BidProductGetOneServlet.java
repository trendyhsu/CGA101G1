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

/**
 * Servlet implementation class BidProductGetOneServlet
 */
@WebServlet("/bid/bidProductGetOne")
public class BidProductGetOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BidProductGetOneServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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
		Integer bidProductNo = Integer.valueOf(request.getParameter("bidProductNo"));
		BidProductService bidProductSvc = new BidProductService();
		BidProductVO bidProductVO = bidProductSvc.getOneBid(bidProductNo);
		JSONObject jsonObject1 = new JSONObject(bidProductVO);
		
		BidRecordService bidRecordSvc = new BidRecordService();
		BidRecordVO bidRecordVO = bidRecordSvc.getHighestByBidProductNo(bidProductNo);
		
		JSONObject bidAll = new JSONObject();			
		
		if(bidRecordVO != null) {
			JSONObject jsonObject2 = new JSONObject(bidRecordVO);
			bidAll.put("bidRecordVO", jsonObject2);
		}
		bidAll.put("bidProductVO", jsonObject1);

		out.write(bidAll.toString());
	}

}

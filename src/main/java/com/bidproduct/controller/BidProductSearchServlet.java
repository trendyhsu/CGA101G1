package com.bidproduct.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidproduct.model.BidProductService;
import com.bidproduct.model.BidProductVO;
import com.google.gson.Gson;

@WebServlet("/bid/bidProductSearch")
public class BidProductSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String keyword = request.getParameter("keyword");
		Writer out = response.getWriter();
		BidProductService bidProductSvc = new BidProductService();
		List<BidProductVO> list = bidProductSvc.getAllByBidName(keyword);
		
		if(list.size()==0) {
			list = bidProductSvc.getAll();
		}
		Gson gson = new Gson();
		String json = gson.toJson(list);
		out.write(json);
	}

}

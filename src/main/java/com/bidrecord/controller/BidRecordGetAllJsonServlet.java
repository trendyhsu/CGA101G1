package com.bidrecord.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidrecord.model.BidRecordService;
import com.bidrecord.model.BidRecordVO;
import com.google.gson.Gson;

@WebServlet("/bid/bidRecordGetAllJson")
public class BidRecordGetAllJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Writer out = response.getWriter();
		BidRecordService bidRecordSvc = new BidRecordService();
		List<BidRecordVO> bidRecordVOs = bidRecordSvc.getAll();
		Gson gson = new Gson();
		String json = gson.toJson(bidRecordVOs);
		out.write(json);
	}

}

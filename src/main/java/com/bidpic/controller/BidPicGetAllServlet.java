package com.bidpic.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidpic.model.BidPicDAO_interface;
import com.bidpic.model.BidPicJDBCDAO;
import com.bidpic.model.BidPicService;
import com.bidpic.model.BidPicVO;
import com.google.gson.Gson;

/**
 * Servlet implementation class BidPicGetLengthServlet
 */
@WebServlet("/bid/bidPicGetAll")
public class BidPicGetAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BidPicGetAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Integer bidProductNo = Integer.valueOf(request.getParameter("bidProductNo"));
		BidPicService bidPicSvc = new BidPicService();
		List<BidPicVO> list = bidPicSvc.getAllBidPicByBidProductNo(bidProductNo);
		Writer out = response.getWriter();
		Gson gson = new Gson();
		String json = gson.toJson(list);
		out.write(json);
	}

}

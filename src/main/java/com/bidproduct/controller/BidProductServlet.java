package com.bidproduct.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidproduct.model.BidProductDAO_interface;
import com.bidproduct.model.BidProductJDBCDAO;
import com.bidproduct.model.BidProductVO;
import com.google.gson.Gson;

/**
 * Servlet implementation class BidProductServlet
 */
@WebServlet("/bid/bidProduct")
public class BidProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BidProductServlet() {
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
		Writer out = response.getWriter();
		BidProductDAO_interface dao = new BidProductJDBCDAO();
		List<BidProductVO> list = dao.getAll();
		Gson gson = new Gson();
		String json = gson.toJson(list);
		out.write(json);
		
	}

}

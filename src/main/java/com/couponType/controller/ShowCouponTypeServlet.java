package com.couponType.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.couponType.model.CouponTypeJDBCDAO;
import com.couponType.model.CouponTypeVO;
import com.google.gson.Gson;

@WebServlet("/couponType/CouponTypeServlet")
public class ShowCouponTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		CouponTypeJDBCDAO dao=new CouponTypeJDBCDAO();
		List<CouponTypeVO> list=dao.getAll();
		Gson gson=new Gson();
		String josn=gson.toJson(list);
		System.out.println(josn);
		out.write(josn);
	
	}

}

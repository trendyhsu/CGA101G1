package com.orderdetail.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orderdetail.model.OrderDetailService;


@WebServlet("/product/showTop9ByMap")
public class ShowTop9ByMap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ShowTop9ByMap() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = response.getWriter();
		OrderDetailService orderDetailService = new OrderDetailService();
		List<Object> list = orderDetailService.GetTop9ProductByMap();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String json = gson.toJson(list);
		out.write(json);
	
	
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
